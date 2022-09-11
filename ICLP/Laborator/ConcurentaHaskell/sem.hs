import Control.Concurrent 
import Control.Monad 

{-
import Control.Concurrent.QSem

data QSem 

newQSem :: Int -> IO QSem 

waitQSem :: QSem -> IO ()   -- aquire
signalQSem :: QSem -> IO () -- release 

qs <- newQSem n             -- creez un semafor care sincronizeaza accesul la n resurse in paralel 
-}

{-
Sa se sincronizeze accesul la 3 resurse pentru 5 workeri in paralel 
-}

takeprint :: MVar String -> IO ()
takeprint stdo = do 
    s <- takeMVar stdo 
    print s 

worker :: QSem -> MVar String -> Int -> IO ()
worker q stdo indexWorker = do 
    waitQSem q 
    putMVar stdo $ "Workerul " ++ (show indexWorker) ++ " a ocupat semaforul"
    threadDelay 2000000
    putMVar stdo $ "Workerul " ++ (show indexWorker) ++ " va elibera semaforul"
    signalQSem q 

main = do 
    q <- newQSem 3 
    stdo <- newEmptyMVar 
    let workers = 5 
    let prints = 2 * workers
    mapM_ (forkIO . worker q stdo) [1..workers]
    replicateM_ prints $ takeprint stdo 