import Control.Concurrent
import Control.Monad  

{-
:! ghc --make nume.hs 
cmd: nume 

Concurenta in Haskell. Threaduri. Memorie partajata

- concurenta are loc in monada IO 

forkIO :: IO () -> IO ThreadId 

data MVar a 

newEmptyMVar :: IO (MVar a) -- imi permite sa creez o locatie goala de memorie 
                            -- m <- newEmptyMVar

newMVar :: a -> IO (MVar a) -- creeaza o locatie de memorie care contine o valoare specificata
                            -- m <- newMVar v

takeMVar :: MVar a -> IO a  -- v <- takeMVar m 
                            -- intoarce in v valoarea din locatia de memorie m 
                            -- daca m este o locatie goala, atunci se blocheaza thread-ul 

putMVar :: MVar a -> a -> IO () 
                            -- putMVar m v 
                            -- pune in m valoarea v 
                            -- blocheaza thread-ul daca locatia de memorie este plina 

-}

inc1 :: MVar Int -> MVar String -> IO () 
inc1 m ms1 = do 
    replicateM_ 1000 $ do
        x <- takeMVar m 
        putMVar m (x + 1)
    putMVar ms1 "finished"

inc2 :: MVar Int -> MVar String -> IO () 
inc2 m ms2 = do 
    replicateM_ 1000 $ do
        x <- takeMVar m 
        putMVar m (x + 1)
    putMVar ms2 "finished"

main = do 
    m <- newMVar 0 

    ms1 <- newEmptyMVar 
    ms2 <- newEmptyMVar 

    threadId1 <- forkIO $ inc1 m ms1 
    threadId2 <- forkIO $ inc2 m ms2 

    takeMVar ms1 
    takeMVar ms2 

    x <- takeMVar m 

    print threadId1 
    print threadId2 
    print x 