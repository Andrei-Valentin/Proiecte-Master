-- Afisarea informatiilor complete ale spitalelor (Nume spital + adresa)
-- Ordonate descrescator in functie de tara si in cadrul fiecarei tari ordonate alfabetic
SELECT sp.nume "Nume Spital", ad.nume_strada "Nume Strada", ad.oras "Oras", NVL(ad.judet,'-') "Judet", ad.tara "Tara"
FROM spital sp JOIN adresa ad ON (sp.id_adresa = ad.id_adresa)
ORDER BY ad.tara DESC, ad.oras ASC;



--Afisarea personalului care a fost angajat in a 12-a zi a lunii
SELECT nume "Nume", prenume "Prenume", data_angajare "Data Angajare"
FROM personal
WHERE personal.data_angajare LIKE ('12%');



--Afisarea pacientilor in mod descrescator in functie de numarul de retete pe care l-au avut
SELECT pc.nume "Nume Pacient", rt.id_pacient "Id pacient", COUNT(rt.id_pacient) AS "Numar retete"
FROM retete rt JOIN pacienti pc ON (pc.id_pacient=rt.id_pacient)
GROUP BY pc.nume, rt.id_pacient
ORDER BY "Numar retete" DESC;



--Spitalele cu cea mai mare medie a salariilor, ordonate descrescator
--Media salariilor este afisata cu 2 zecimale
SELECT spital.nume "Nume Spital", ROUND(AVG(personal.salariu),2) AS "Salariu Mediu"
FROM personal
JOIN spital
ON (spital.id_spital=personal.id_spital)
GROUP BY spital.nume
ORDER BY "Salariu Mediu" DESC;



--Pacientii ordonati descrescator in functie de numarul total de medicamente care le-au fost prescrise
SELECT pc.nume "Nume Pacient", pc.prenume "Prenume Pacient", cm.Cantitate "Cantitate medicamente"
FROM pacienti pc
JOIN (SELECT rt.id_pacient, crt.id_reteta, SUM(crt.cantitate) Cantitate FROM continutretete crt JOIN retete rt
        ON (crt.id_reteta=rt.id_reteta)
        GROUP BY rt.id_pacient, crt.id_reteta) cm
ON (pc.id_pacient=cm.id_pacient)
GROUP BY pc.nume, pc.prenume, cm.Cantitate
ORDER BY cm.Cantitate DESC;



--Pacientii care au avut cel putin doua retete, ordonati descrescator
SELECT pc.nume, nrt."Numar" "Numar Retete"
FROM pacienti pc
JOIN(SELECT id_pacient, COUNT(id_reteta) "Numar"
    FROM retete
    GROUP BY id_pacient
    ORDER BY "Numar"
    ) nrt
ON (pc.id_pacient = nrt.id_pacient)
HAVING nrt."Numar" > 1
GROUP BY pc.nume, nrt."Numar"
ORDER BY "Numar Retete" DESC




