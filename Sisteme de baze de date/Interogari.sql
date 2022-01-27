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
