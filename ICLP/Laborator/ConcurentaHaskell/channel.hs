import Control.Concurrent 
import Control.Monad 
import Data.Char 

{-
Canale de comunicare - pentru comunicare intre threaduri 
Ele sunt implementate tot prin MVar 

newChan :: IO (Chan a)
writeChan :: Chan a -> a -> IO () -- nu se blocheaza niciodata 
readChan :: Chan a -> IO a -- se blocheaza cand canalul este gol 
-}

{-
Avem doua canale, wordsIn si wordsOut 

threadul principal citeste siruri pana intalneste sirul "exit" si le pune in wordsIn 
un thread citeste de pe wordsIn, sparge sirurile in cuvinte, si le pune in wordsOut 
un alt thread citeste de pe wordsOut si scrie la iesire cuvintele cu majuscule 
-}

load wordsIn = forever $ do 
    str <- getLine 
    if str == "exit" then return ()
    else do 
        writeChan wordsIn str 

move wordsIn wordsOut = do 
    str <- readChan wordsIn 
    let ls = words str 
    mapM_ (writeChan wordsOut) ls 

writeStdOut wordsOut = do 
    str <- readChan wordsOut 
    putStrLn $ map toUpper str 

main = do 
    wordsIn <- newChan 
    wordsOut <- newChan 

    forkIO $ forever $ move wordsIn wordsOut 
    forkIO $ forever $ writeStdOut wordsOut 

    load wordsIn 