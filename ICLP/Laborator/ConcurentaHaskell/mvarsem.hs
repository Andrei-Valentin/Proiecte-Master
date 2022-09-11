import Control.Concurrent 

{-
Sa se scrie un program concurent care primeste un mesaj de la stdin 
un thread care preia mesajul si il directioneaza unui alt thread, printr-o zona partajata de memorie, spre a-l modifica, iar modificarea va fi pusa in alta locatie de memorie  
dupa ce se termina modificarea, afiseaza mesajul la stdout 
-}

-- stdin          aMVar                bMVar
-- sir in main
-- threadA:       str 
-- threadB        citeste de aici       strstr 

threadA aMVar bMVar msg = do 
    putMVar aMVar msg 
    y <- takeMVar bMVar 
    putStrLn $ "Raspuns: " ++ y 

threadB aMVar bMVar = do 
    x <- takeMVar aMVar 
    let y = x ++ x 
    putMVar bMVar y 

main = do 
    aMVar <- newEmptyMVar 
    bMVar <- newEmptyMVar 

    putStrLn "Introduceti un mesaj"
    msg <- getLine 

    forkIO $ threadA aMVar bMVar msg 
    forkIO $ threadB aMVar bMVar 