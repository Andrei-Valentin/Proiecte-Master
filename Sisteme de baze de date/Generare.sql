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
