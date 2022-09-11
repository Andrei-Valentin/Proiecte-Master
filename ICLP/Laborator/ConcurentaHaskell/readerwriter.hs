import Control.Concurrent 
import Control.Monad 

{-
Reader-Writer problem 
- mai multe thread-uri au acces la o resursa
- unele threaduri au rolul de Writer (scriu), altele rolul de Reader (citesc)
- resursa poate fi accesata simultan de mai multi cititori  
- resursa poate fi accesata de un singur scriitor 
- resursa nu poate fi simultan accesata si de cititori, si de scriitori 

Folosim:
- un semafor binar care da acces la citit sau la scris, writeL 
- un monitor in care se inregistreaza nr de cititori: readL 
-}

type MyLock = MVar () 

newLock = newMVar () 
aquireLock m = takeMVar m 
releaseLock m = putMVar m ()

data MyRWLock = MyRWL { readL :: MVar Int, writeL :: MyLock }

newMyRWLock :: IO MyRWLock 
newMyRWLock = do 
    readL <- newMVar 0 
    writeL <- newLock 
    return (MyRWL readL writeL)

aquireWrite :: MyRWLock -> IO ()
aquireWrite (MyRWL readL writeL) = aquireLock writeL 

releaseWrite :: MyRWLock -> IO ()
releaseWrite (MyRWL readL writeL) = releaseLock writeL 

aquireRead :: MyRWLock -> IO ()
aquireRead (MyRWL readL writeL) = do 
    n <- takeMVar readL 
    if n == 0 then do 
        aquireLock writeL 
        putMVar readL 1
    else 
        putMVar readL (n + 1) 

releaseRead :: MyRWLock -> IO ()
releaseRead (MyRWL readL writeL) = do 
    n <- takeMVar readL 
    if n == 1 then do 
        releaseLock writeL 
        putMVar readL 0 
    else 
        putMVar readL (n - 1)

takeprint :: MVar String -> IO ()
takeprint stdo = do 
    s <- takeMVar stdo 
    print s 

reader stdo rwl buf i = do 
    aquireRead rwl 
    threadDelay 1000000
    c <- readMVar buf  -- takeMVar buf 
    putMVar stdo $ "Readerul " ++ (show i) ++ " citeste: " ++ (show c)
    releaseRead rwl 

writer stdo rwl buf i = do 
    aquireWrite rwl 
    threadDelay 2000000
    putMVar stdo $ "Writerul " ++ (show i) ++ " scrie " ++ (show i)
    c <- takeMVar buf 
    putMVar buf i 
    releaseWrite rwl 
    threadDelay 2000000

-- genread :: Int -> MyRWLock -> MVar Int -> IO () 
-- genread n rwl buf = if n == 0 then 
--     putStrLn "nu mai sunt readeri"
--     else do 
--         reader n rwl buf 
--         threadDelay 20
--         genread (n - 1) rwl buf 

-- genwriter n rwl buf = if n == 0 then 
--     putStrLn "nu mai sunt writeri"
--     else do 
--         writer n rwl buf 
--         threadDelay 100 
--         genwriter (n - 1) rwl buf 

main = do 
    buf <- newMVar 0 
    rwl <- newMyRWLock 
    stdo <- newEmptyMVar 
    -- forkIO $ genread 10 rwl buf 
    -- forkIO $ genwriter 5 rwl buf 
    
    let readers = 10 
    let writers = 5 

    -- mapM_ (forkIO . writer rwl buf) [1..writers]    
    -- mapM_ (forkIO . reader rwl buf) [1..readers]

    forkIO $ writer stdo rwl buf 1 
    forkIO $ reader stdo rwl buf 1 
    forkIO $ reader stdo rwl buf 2
    forkIO $ writer stdo rwl buf 2
    forkIO $ reader stdo rwl buf 3 
    forkIO $ reader stdo rwl buf 4 

    forkIO $ forever $ takeprint stdo 

    getLine 