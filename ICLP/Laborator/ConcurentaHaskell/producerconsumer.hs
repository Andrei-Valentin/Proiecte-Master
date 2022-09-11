import Control.Concurrent 
import Control.Monad 

{-
Problema Producator-Consumator 

MVar ca monitor 

Producatorul va citi incontinuu mesaje de la stdin si le va pune intr-o locatie partajata de memorie 
Un numar finit de consumatori vor afisa mesajele respective la stdout 
-}

producer :: MVar String -> IO ()
producer m = forever $ do 
    mes <- getLine 
    putMVar m mes 

consumer :: MVar String -> Int -> IO ()
consumer m n = if n == 0 
    then return () 
    else do 
        mes <- takeMVar m 
        putStrLn $ ">" ++ mes 
        consumer m (n - 1)

main = do 
    m <- newEmptyMVar 
    forkIO $ producer m 
    consumer m 5 