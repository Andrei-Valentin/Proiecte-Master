CREATE TABLE Adresa (
  id_adresa number(10) CONSTRAINT id_adresa_PK PRIMARY KEY,
  nume_strada varchar2(50 CHAR),
  oras varchar2(50 CHAR),
  judet varchar2(50 CHAR),
  tara varchar2(50 CHAR)
);


CREATE TABLE Spital (
  id_spital number(10) CONSTRAINT id_spital_PK PRIMARY KEY,
  id_adresa number(10),
  nume varchar2(50 CHAR) CONSTRAINT nume_spital_not_null NOT NULL,
  CONSTRAINT id_adresa_FK FOREIGN KEY(id_adresa) REFERENCES Adresa(id_adresa)
);


CREATE TABLE Pacienti (
  id_pacient number(10) CONSTRAINT id_pacient_PK PRIMARY KEY,
  nume varchar2(50 CHAR) CONSTRAINT nume_pacient_not_null NOT NULL,
  prenume varchar2(50 CHAR) CONSTRAINT prenume_pacient_not_null NOT NULL,
  data_nasterii date CONSTRAINT data_nasterii_not_null NOT NULL,
  telefon varchar2(15 CHAR),
  asigurat number(1) CONSTRAINT asigurat_not_null NOT NULL
);


CREATE TABLE Personal (
  id_angajat number(10) CONSTRAINT id_angajat_PK PRIMARY KEY,
  id_spital number(10),
  functie varchar2(50 CHAR) CONSTRAINT functie_angajat_not_null NOT NULL,
  nume varchar2(50 CHAR) CONSTRAINT nume_angajat_not_null NOT NULL,
  prenume varchar2(50 CHAR) CONSTRAINT prenume_angajat_not_null NOT NULL,
  data_angajare date CONSTRAINT data_angajare_not_null NOT NULL,
  salariu number(10) CONSTRAINT salariu_not_null NOT NULL,
  telefon number(10),
  CONSTRAINT id_spital_personal_FK FOREIGN KEY (id_spital) REFERENCES Spital(id_spital)
);

CREATE TABLE Medicamente(
  id_medicament number(10) CONSTRAINT id_medicament_PK PRIMARY KEY,
  denumire_medicament varchar2(50 CHAR) CONSTRAINT denumire_medicament_not_null NOT NULL,
  pret number(10) CONSTRAINT pret_medicament_not_null NOT NULL
);

CREATE TABLE Retete(
    id_reteta number(10) CONSTRAINT id_reteta_PK PRIMARY KEY,
    id_pacient number(10),
    data_emitere date CONSTRAINT data_emitere_not_null NOT NULL,
    diagnostic varchar2(50 CHAR),
    CONSTRAINT id_pacient_FK FOREIGN KEY (id_pacient) REFERENCES Pacienti(id_pacient)
);


CREATE TABLE ContinutRetete(
    id_reteta number(10),
    id_medicament number(10),
    cantitate number(3) CONSTRAINT cantitate_not_null NOT NULL,
    CONSTRAINT id_reteta_medicament_PK PRIMARY KEY (id_reteta,id_medicament),
    CONSTRAINT id_reteta_FK FOREIGN KEY (id_reteta) REFERENCES Retete(id_reteta),
    CONSTRAINT id_medicament_FK FOREIGN KEY (id_medicament) REFERENCES Medicamente(id_medicament)
);


INSERT INTO Adresa(id_adresa, nume_strada, oras, judet, tara)
VALUES (1,'Calea Floreasca 8', 'Bucuresti', 'Bucuresti', 'Romania');

INSERT INTO Adresa(id_adresa, nume_strada, oras, judet, tara)
VALUES (2,'Bulevardul Constantin Brancoveanu', 'Bucuresti', 'Bucuresti', 'Romania');

INSERT INTO Adresa(id_adresa, nume_strada, oras, judet, tara)
VALUES (3,'Bulevardul Ferdinand', 'Constanta', 'Constanta', 'Romania');

INSERT INTO Adresa(id_adresa, nume_strada, oras, judet, tara)
VALUES (4,'Calea București 258', 'Bucuresti', 'Bucuresti', 'Romania');

INSERT INTO Adresa(id_adresa, nume_strada, oras, judet, tara)
VALUES (5,'Bulevardul Mamaia 477', 'Constanta', 'Constanta', 'Romania');

INSERT INTO Adresa(id_adresa, nume_strada, oras, judet, tara)
VALUES (6,'Str. Clinicilor 3-5', 'Cluj-Napoca', 'Cluj', 'Romania');

INSERT INTO Adresa(id_adresa, nume_strada, oras, judet, tara)
VALUES (7,'Strümpellstraße 41', 'Leipzig', '', 'Germania');


INSERT INTO Spital(id_spital, id_adresa, nume)
VALUES (1, 1, 'Spitalul Clinic de Urgenta Bucuresti');

INSERT INTO Spital(id_spital, id_adresa, nume)
VALUES (2, 4, 'Institutul Clinic Fundeni');

INSERT INTO Spital(id_spital, id_adresa, nume)
VALUES (3, 2, 'Spitalul Clinic de Copii Marie Skłodowska Curie');

INSERT INTO Spital(id_spital, id_adresa, nume)
VALUES (4, 3, 'Spitalul Clinic de Boli Infecțioase');

INSERT INTO Spital(id_spital, id_adresa, nume)
VALUES (5, 7, 'Helios Park-Klinikum Leipzig');

INSERT INTO Spital(id_spital, id_adresa, nume)
VALUES (6, 6, 'Spitalul Clinic Județean de Urgență Cluj');



INSERT INTO Pacienti(id_pacient, nume, prenume, data_nasterii,telefon, asigurat)
VALUES (1, 'Popa', 'Alexandru', to_date('13-12-1970','dd-mm-yyyy'), to_char('0726594753'), 1);

INSERT INTO Pacienti(id_pacient, nume, prenume, data_nasterii,telefon, asigurat)
VALUES (2, 'Gheorghescu', 'Florin', to_date('13-10-1999','dd-mm-yyyy'), to_char('0726337547'), 0);

INSERT INTO Pacienti(id_pacient, nume, prenume, data_nasterii,telefon, asigurat)
VALUES (3, 'Mateiescu', 'Cornel', to_date('11-04-1990','dd-mm-yyyy'), to_char('0726291554'), 1);

INSERT INTO Pacienti(id_pacient, nume, prenume, data_nasterii,telefon, asigurat)
VALUES (4, 'Vasiliu', 'Cristian', to_date('05-03-1980','dd-mm-yyyy'), to_char('0726676739'), 1);

INSERT INTO Pacienti(id_pacient, nume, prenume, data_nasterii,telefon, asigurat)
VALUES (5, 'Ayaz', 'Eren', to_date('07-03-1965','dd-mm-yyyy'), to_char('0721316239'), 0);



INSERT INTO Personal(id_angajat, id_spital, functie, nume, prenume, data_angajare, salariu, telefon)
VALUES (1, 2, 'ASISTENT MEDICAL', 'Dragan', 'Ionut', to_date('05-12-2019','dd-mm-yyyy'), 3200, to_char('0721493553'));

INSERT INTO Personal(id_angajat, id_spital, functie, nume, prenume, data_angajare, salariu, telefon)
VALUES (2, 1, 'MEDIC PRIMAR', 'Codreanu', 'Matei', to_date('07-12-2003','dd-mm-yyyy'), 9200, to_char('0721524153'));

INSERT INTO Personal(id_angajat, id_spital, functie, nume, prenume, data_angajare, salariu, telefon)
VALUES (3, 2, 'ASISTENT DE RADIOLOGIE', 'Bradeanu', 'Petru', to_date('03-11-2018','dd-mm-yyyy'), 6200, to_char('0726534523'));

INSERT INTO Personal(id_angajat, id_spital, functie, nume, prenume, data_angajare, salariu, telefon)
VALUES (4, 2, 'ASISTENT MEDICAL', 'Serban', 'Carmen', to_date('01-12-2021','dd-mm-yyyy'), 4200, to_char('0726196723'));

INSERT INTO Personal(id_angajat, id_spital, functie, nume, prenume, data_angajare, salariu, telefon)
VALUES (5, 5, 'MEDIC PRIMAR', 'Jürgen', 'Kiwit', to_date('03-12-1980','dd-mm-yyyy'), 25000, to_char('2407194653'));
