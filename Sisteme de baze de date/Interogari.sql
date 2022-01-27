-- Afisarea informatiilor complete ale spitalelor (Nume spital + adresa)
-- Ordonate descrescator in functie de tara si in cadrul fiecarei tari ordonate alfabetic

SELECT sp.nume "Nume Spital", ad.nume_strada "Nume Strada", ad.oras "Oras", NVL(ad.judet,'-') "Judet", ad.tara "Tara"
FROM spital sp JOIN adresa ad ON (sp.id_adresa = ad.id_adresa)
ORDER BY ad.tara DESC, ad.oras ASC;


--Afisarea personalului care a fost angajat in a 12-a zi a lunii
SELECT nume "Nume", prenume "Prenume", data_angajare "Data Angajare"
FROM personal
WHERE personal.data_angajare LIKE ('12%');
