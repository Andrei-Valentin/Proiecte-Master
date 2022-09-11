import Data.Char 
import System.IO 


-- Recapitulare Haskell 

-- 1. sa se scrie o functie maxim care primeste doua numere intregi si returneaza maximul lor 

maxim :: Int -> Int -> Int 
maxim x y = if x > y then x else y 

maxim' :: Int -> Int -> Int 
maxim' x y 
    | x > y = x 
    | otherwise = y 

-- 2. sa se scrie o functie maxim3 care calculeaza maximul a trei numere. Sa se foloseasca definitia anterioara.

maxim3 :: Int -> Int -> Int -> Int 
maxim3 x y z = maxim x (maxim y z)

maxim3' :: Int -> Int -> Int -> Int 
maxim3' x y z = maxim x $ maxim y z 

-- 3. Sa se scrie o functie care sa returneze al n-lea termen al sirului lui Fibonacci.

-- fibo 0 = 1 
-- fibo 1 = 1 

fibo :: Int -> Int 
fibo 0 = 1 
fibo 1 = 1 
fibo n = fibo (n - 1) + fibo (n - 2)

fibo' :: Int -> Int 
fibo' x 
    | x == 0 = 1
    | x == 1 = 1 
    | otherwise = fibo' (x - 1) + fibo' (x - 2)

fibo'' :: Int -> Int 
fibo'' x = 
    if x == 0 
        then 1 
        else 
            if x == 1 
                then 1 
                else fibo'' (x - 1) + fibo'' (x - 2)

-- 4. Sa se scrie o functie care calculeaza suma elementelor intr-o lista. Sa se foloseasca si o functie de agregare.

-- [] 
-- (x:xs) - inseamna ca x este head, xs este tail 

sumList :: [Int] -> Int 
sumList [] = 0
sumList (x:xs) = x + sumList xs 

sumList' :: [Int] -> Int 
sumList' = sum

sumList'' :: [Int] -> Int 
sumList'' = foldr (+) 0 

-- 5. Sa se scrie o functie care returneaza produsul elementelor pare dintr-o lista.

prodList :: [Int] -> Int 
prodList [] = 1 
prodList (x:xs)
    | even x = x * prodList xs 
    | otherwise = prodList xs 

prodList' :: [Int] -> Int 
prodList' ls = product $ filter even ls 

-- | x `mod` 2 == 0 = x * prodList xs 

-- 6. Sa se scrie o functie care dubleaza toate elementele impare dintr-o lista. 

doubleElements :: [Int] -> [Int] 
doubleElements [] = [] 
doubleElements (x:xs) 
    | odd x = (2 * x) : doubleElements xs 
    | otherwise = x : doubleElements xs  

doubleElements' :: [Int] -> [Int]
doubleElements' ls = map (\x -> if odd x then 2 * x else x) ls 

-- 7. Sa se scrie o functie care dubleaza toate elementele de pe pozitiile impare dintr-o lista, cu indexare de la 0.

doubleOddIndex :: [Int] -> [Int]
doubleOddIndex ls = map (\(x, p) -> if odd p then 2 * x else x) $ zip ls [0..]

-- 8. Sa se scrie o functie care, primind un numar natural, intoarce lista divizorilor naturali ai lui. Sa se scrie o functie care verifica daca numarul este prim. 

listDiv :: Int -> [Int]
listDiv n = [ d | d <- [1..n], n `mod` d == 0 ]

prim :: Int -> Bool 
prim x = (length $ listDiv x) == 2 

-- { d | n `mod` d == 0 }

-- 9. Sa se scrie o functie multDigits care, primind un sir de caractere, intoarce produsul cifrelor din sirul primit. 
-- de exemplu, multDigits "The time is 4:25" este 40, iar multDigits "No digits here!" este 1
-- String = [Char]

multDigits :: String -> Int 
multDigits [] = 1 
multDigits (x:xs)
    | isDigit x = digitToInt x * multDigits xs 
    | otherwise = multDigits xs

multDigits' :: String -> Int 
multDigits' str = product $ map digitToInt $ filter isDigit str  

-- 10. Sa se scrie o functie ordonataNat care verifica daca elementele dintr-o lista sunt in ordine crescatoare. Sa se scrie si o functie pe o relatie de ordine oarecare.

ordonataNat :: [Int] -> Bool 
ordonataNat [] = True 
ordonataNat [_] = True 
ordonataNat (x:y:xs)
    | x <= y = ordonataNat (y:xs) 
    | otherwise = False 
-- [1, 4, 9, 12, 24]

ordonata :: [a] -> (a -> a -> Bool) -> Bool 
ordonata [] _ = True 
ordonata [_] _ = True 
ordonata (x:y:xs) rel 
    | rel x y = ordonata (y:xs) rel 
    | otherwise = False 

-- 11. Fie urmatorul tip de date pentru reprezentarea formulelor in logica propozitionala.

type Nume = String 
data Prop 
    = Var Nume 
    | F 
    | T 
    | Not Prop 
    | Prop :|: Prop 
    | Prop :&: Prop 
    | Prop :->: Prop 
    | Prop :<->: Prop
    deriving Eq
infixr 2 :|:
infixr 3 :&:

type Env = [(Nume, Bool)]

-- p1 = (P \/ Q) /\ (P /\ Q)
p1 :: Prop 
p1 = (Var "P" :|: Var "Q") :&: (Var "P" :&: Var "Q")

-- p2 = (P \/ Q) /\ (~P /\ ~Q)
p2 :: Prop
p2 = (Var "P" :|: Var "Q") :&: (Not (Var "P") :&: Not (Var "Q"))

instance Show Prop where 
    show F = "F"
    show T = "T"
    show (Var p) = show p 
    show (Not prop) = "(~" ++ show prop ++ ")"
    show (prop1 :&: prop2) = "(" ++ show prop1 ++ " & " ++ show prop2 ++ ")"
    show (prop1 :|: prop2) = "(" ++ show prop1 ++ " | " ++ show prop2 ++ ")"
    show (prop1 :->: prop2) = "(" ++ show prop1 ++ " -> " ++ show prop2 ++ ")"
    show (prop1 :<->: prop2) = "(" ++ show prop1 ++ " <-> " ++ show prop2 ++ ")"
    

-- 12. Se dau o formula si un environment in care se face evaluarea
-- se de determine valoarea de adevar a formulei

-- Env = [("P", False), ("Q", True)]
-- p = Var "P" :|: Var "Q" 

find :: Env -> Nume -> Bool 
find env p = head $ map (\(nv, val) -> val) $ filter (\(nv, val) -> nv == p) env 

eval :: Prop -> Env -> Bool 
eval T _ = True 
eval F _ = False 
eval (Var p) env = find env p 
eval (Not p) env = not (eval p env)
eval (p1 :&: p2) env = (eval p1 env) && (eval p2 env)
eval (p1 :|: p2) env = (eval p1 env) || (eval p2 env)
eval (p1 :->: p2) env = not (eval p1 env) || (eval p2 env)
eval (p1 :<->: p2) env = (eval (p1 :->: p2) env) && (eval (p2 :->: p1) env)

-- 13. Sa se scrie o functie care citeste o linie de la tastatura, si afiseaza la standard output literele mari. 

readLineFromStdIn :: IO ()
readLineFromStdIn = do 
    line <- getLine 
    let upperLetters = filter isUpper line 
    putStrLn upperLetters 

getString :: String 
getString = "string"

getStringFromStdin :: IO String 
getStringFromStdin = do 
    line <- getLine 
    return line 


-- 15. Sa se scrie o functie care citeste continutul unui fisier si il afiseaza la stdout cu modificari

readFromFile :: FilePath -> IO ()
readFromFile path = do 
    content <- readFile path 
    let modifiedContent = map toUpper content 
    putStrLn modifiedContent
