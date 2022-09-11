import Control.Concurrent 
import Control.Concurrent.STM 
{-
STM - Software Transactional Memory 
-}

type Account = MVar Int 

deposit :: Account -> Int -> IO ()
deposit acc amount = do 
    currentAmount <- takeMVar acc 
    putMVar acc (currentAmount + amount)

withdraw :: Account -> Int -> IO ()
withdraw acc amount = do 
    currentAmount <- takeMVar acc 
    putMVar acc (currentAmount - amount)

showBalance :: Account -> String -> IO () 
showBalance acc accountName = do 
    currentAmount <- takeMVar acc 
    putMVar acc currentAmount
    putStrLn $ "In contul " ++ accountName ++ " avem: " ++ (show currentAmount)

transfer :: Account -> Account -> Int -> IO ()
transfer from to amount = do 
    --withdraw from amount 
    -- in locul acesta apare un data race 
    -- avem o stare intermediara in care banii nu se gasesc in niciunul dintre cele doua conturi 
    --deposit to amount 
    amountFrom <- takeMVar from 
    amountTo <- takeMVar to 
    putMVar from (amountFrom - amount)
    putMVar to (amountTo + amount)

-- main = do 
--     a <- newMVar 1000 
--     b <- newMVar 1000 
--     forkIO $ transfer a b 300 
--     showBalance a "a"
--     showBalance b "b"

{-
tranzactie - secventa de operatii executate ca un tot unitar, care respecta 4 proprietati (ACID): 
    A - Atomicitate - o tranzactie trebuie sa fie atomica; ori se modifica toate datele necesare, ori niciuna 
    C - Consistenta - tranzactia lasa toate datele intr-o stare consistenta 
    I - Izolare - modificarile pe care le facem intr-o tranzactie concurenta trebuie sa fie izolate (independente) de moficiarile facute de orice alta tranzactie concurenta 
    D - Durabilitate - dupa ce am terminat de executat o tranzactie, rezultatele trebuie sa fie permanetizate in sistem 
-}

{-
implementarea atomicitatii 
- sau prin variabile atomice (IORef a), care sunt implementate afolosind instructiuni hardware compare-and-swap 
- sau prin STM - sincronizare fara lacate, blocuri de instructiuni executate atomic 
-}

{-
vom lucra in monada STM - asemanatoare monadei IO 
nu mai avem MVar, ci TVar (variabile tranzactionale)
in loc de takeMVar avem readTVar 
in loc de putMVar avem writeTVar 
avem functia atomically :: STM a -> IO a care executa atomic o actiune din STM 
retry :: STM a -- daca apelam retry, tranzactia curenta este abandonata si va fi reincercata la un moment de timp ulterior 
-}

type AccountSTM = TVar Int 

depositSTM :: AccountSTM -> Int -> STM () 
depositSTM acc amount = do 
    currentAmount <- readTVar acc 
    writeTVar acc (currentAmount + amount)

withdrawSTM :: AccountSTM -> Int -> STM ()
withdrawSTM acc amount = do 
    currentAmount <- readTVar acc 
    if amount > 0 && amount > currentAmount then retry 
    else writeTVar acc (currentAmount - amount)

showBalanceSTM :: AccountSTM -> String -> IO ()
showBalanceSTM acc accountName = do 
    currentAmount <- atomically $ readTVar acc 
    putStrLn $ "In contul " ++ accountName ++ " avem: " ++ (show currentAmount)

transferSTM :: AccountSTM -> AccountSTM -> Int -> IO ()
transferSTM from to amount = atomically $ do 
    withdrawSTM from amount
    depositSTM to amount 

main :: IO ()
main = do 
    (a, b) <- atomically $ do
        a <- newTVar 1000 
        b <- newTVar 1000 
        return (a, b)
    forkIO $ transferSTM a b 1200 
    threadDelay 5000000
    showBalanceSTM a "a"
    showBalanceSTM b "b"