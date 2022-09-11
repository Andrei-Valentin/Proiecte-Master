import Control.Concurrent 
import Control.Monad 
import Data.List 
import Data.Maybe 

{-
Problema

Un restaurant are N locuri. 
Initial, restaurantul este gol => toate locurile sunt libere. 
Cand un client soseste, se uita daca exista locuri libere si ocupa unul dintre ele pentru o anumita perioada de timp. (2 s)
Daca nu exista locuri libere, clientii intra intr-o coada de asteptare si ocupa locurile care se elibereaza in ordinea sosirii. 

Creati un program care simuleaza functionarea restaurantului, avand cate un thread pentru fiecare client.
Afisati mereu un mesaj cu locul pe care se asaza fiecare client, afisati cand un client soseste, respectiv cand un client asteapta la coada. 
-}

data Loc = L | O 
    deriving (Eq, Show)

type MRestaurant = MVar ([Loc], [Int]) -- locurile din restaurant, respecitv coada de asteptare 

liber :: Loc -> Bool 
liber loc = (loc == L)

ocupat :: Loc -> Bool 
ocupat loc = (loc == O)

full :: [Loc] -> Bool 
full restaurant = and (map ocupat restaurant)

ocupaloc i rest = let 
    (p1, p2) = splitAt i rest in p1 ++ (O : (tail p2))

elibloc i rest = let 
    (p1, p2) = splitAt i rest in p1 ++ (L : (tail p2))

takeprint stdo s = do 
    takeMVar stdo 
    putStrLn s 
    putMVar stdo () 

client restaurant stdo indexClient = do 
    threadDelay 1000000
    (currentRestaurant, cq) <- takeMVar restaurant 
    if full currentRestaurant then do 
        putMVar restaurant (currentRestaurant, cq ++ [indexClient])
        takeprint stdo $ "Clientul " ++ show indexClient ++ " asteapta la coada"
    else do 
        let indexLoc = fromJust $ elemIndex L currentRestaurant
        let newRestaurant = ocupaloc indexLoc currentRestaurant 
        putMVar restaurant (newRestaurant, cq)
        clientin restaurant stdo indexClient indexLoc 
    
clientin restaurant stdo indexClient indexLoc = do 
    takeprint stdo $ "Clientul " ++ show indexClient ++ " a ocupat locul " ++ show (indexLoc + 1)
    threadDelay 5000000
    takeprint stdo $ "Clientul " ++ show indexClient ++ " va pleca"
    (currentRestaurant, cq) <- takeMVar restaurant 
    if null cq then 
        putMVar restaurant (elibloc indexLoc currentRestaurant, cq)
    else do 
        putMVar restaurant (currentRestaurant, tail cq)
        clientin restaurant stdo (head cq) indexLoc

main = do 
    stdo <- newMVar ()
    restaurant <- newMVar ([L, L, L], [])
    let clients = 5 
    mapM_ (forkIO . client restaurant stdo) [1..clients]
    getLine  
