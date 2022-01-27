--Trigger de tip LMD la nivel de linie
--Nu ne permite sa introducem mai mult de 5 medicamente pe aceeasi reteta


CREATE OR REPLACE TRIGGER numar_medicamente
BEFORE INSERT OR UPDATE ON CONTINUTRETETE
FOR EACH ROW
DECLARE
v_nr number;
BEGIN
select count(id_medicament) into v_nr from continutretete where id_reteta = :new.id_reteta;
IF (v_nr > 4) THEN
RAISE_APPLICATION_ERROR(-20300,'Nu se pot prescrie mai mult de 5 medicamente pe aceeasi reteta.');
END IF;
END;



--Declansarea trigger-ului
DECLARE
exp_medicamente exception;
PRAGMA EXCEPTION_INIT(exp_medicamente,-20300);

BEGIN
INSERT INTO ContinutRetete(id_reteta, id_medicament, cantitate) VALUES (1, 3, 2);
INSERT INTO ContinutRetete(id_reteta, id_medicament, cantitate) VALUES (1, 4, 1);
INSERT INTO ContinutRetete(id_reteta, id_medicament, cantitate) VALUES (1, 5, 1);
--Daca incercam sa introducem alt medicament pe aceeasi reteta se activeaza trigger-ul si nu ne lasa
INSERT INTO ContinutRetete(id_reteta, id_medicament, cantitate) VALUES (1, 6, 1);

EXCEPTION 
WHEN exp_medicamente THEN
DBMS_OUTPUT.PUT_LINE(TO_CHAR(SQLERRM(-20300)));
END;
