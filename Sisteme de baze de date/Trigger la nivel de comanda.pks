
CREATE OR REPLACE TRIGGER salariu_inceput_de_an
    BEFORE UPDATE OF salariu  
    ON personal
DECLARE
    luna_an NUMBER;
BEGIN
    luna_an := EXTRACT(MONTH FROM sysdate);
    IF luna_an BETWEEN 1 AND 3 THEN
        raise_application_error(-20200,'Salariul unui angajat nu poate fi modificat in primul trimestru al anului');
    END IF;
END;


--Declansarea trigger-ului
DECLARE
exp_salariu exception;
PRAGMA EXCEPTION_INIT(exp_salariu,-20200);

BEGIN

UPDATE PERSONAL
SET SALARIU = SALARIU + 500;

EXCEPTION 
WHEN exp_salariu THEN
DBMS_OUTPUT.PUT_LINE(TO_CHAR(SQLERRM(-20200)));
END;
