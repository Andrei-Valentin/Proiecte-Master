import Data.Char 
import System.IO 


-- Recapitulare Haskell 

-- 1. Operatii aritmetice si logice
-- +, -, *, **, /, mod, div, &&, ||

-- 2. Definitii de functii
-- Sa se defineasca functia double care primeste un Int si returneaza numarul inmultit cu 2

-- 3. sa se scrie o functie maxim care primeste doua numere intregi si returneaza maximul lor 

-- 4. sa se scrie o functie maxim3 care calculeaza maximul a trei numere. Sa se foloseasca definitia anterioara.

-- 5. Liste 
-- Fiind data o lista de intregi, sa se returneze primul element din lista. Sa se returneze si un element de pe un index oarecare din lista.

-- 6. Sa se scrie o functie care sa returneze al n-lea termen al sirului lui Fibonacci.

-- 7. Sa se scrie o functie care calculeaza suma elementelor intr-o lista. Sa se foloseasca si o functie de agregare.

-- 8. Sa se scrie o functie care returneaza produsul elementelor pare dintr-o lista.

-- 9. Sa se scrie o functie care dubleaza toate elementele impare dintr-o lista. 

-- 10. Sa se scrie o functie care dubleaza toate elementele de pe pozitiile impare dintr-o lista, cu indexare de la 0.

-- 11. Sa se scrie o functie care, primind un numar natural, intoarce lista divizorilor naturali ai lui. Sa se scrie o functie care verifica daca numarul este prim. 

-- 12. Sa se scrie o functie multDigits care, primind un sir de caractere, intoarce produsul cifrelor din sirul primit. 
-- de exemplu, multDigits "The time is 4:25" este 40, iar multDigits "No digits here!" este 1

-- 13. Sa se scrie o functie ordonataNat care verifica daca elementele dintr-o lista sunt in ordine crescatoare. Sa se scrie si o functie pe o relatie de ordine oarecare.

-- 14. Fie urmatorul tip de date pentru reprezentarea formulelor in logica propozitionala.

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
    show = undefined 

-- 15. Se dau o formula si un environment in care se face evaluarea
-- se de determine valoarea de adevar a formulei

find :: Env -> Nume -> Bool 
find = undefined 

eval :: Prop -> Env -> Bool 
eval = undefined 

-- 16. Sa se scrie o functie care citeste o linie de la tastatura, si afiseaza la standard output literele mari. 

-- 17. Sa se scrie o functie care citeste o linie de la tastatura, si returneaza sirul de caractere format prin concatenarea cifrelor 


