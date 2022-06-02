use bazadedate;


create table if not exists adresa(
    id int NOT NULL auto_increment primary key,
    adresa_tara varchar(255) NOT NULL,
    adresa_oras varchar(255) NOT NULL,
    adresa_strada varchar(255) NOT NULL,
    adresa_numarStrada varchar(255) NOT NULL
    );

create table if not exists produse(
    id int NOT NULL auto_increment primary key,
    produs_categorie varchar(255) NOT NULL,
    produs_denumire varchar(255) NOT NULL,
    produs_pret float NOT NULL
    );

create table if not exists clienti(
    id int NOT NULL auto_increment primary key,
    client_nume varchar(255) NOT NULL,
    client_CNP bigint NOT NULL,
    client_varsta int NOT NULL,
    client_email varchar(255) NOT NULL
    );

create table if not exists magazine(
    id int NOT NULL auto_increment primary key,
    magazin_denumire varchar(255) NOT NULL,
    adresa_id int NOT NULL,
    foreign key (adresa_id) references adresa (id)
    );

create table if not exists stoc(
    id int NOT NULL auto_increment primary key,
    magazin_id int NOT NULL,
    produs_id int NOT NULL,
    cantitate_produs int NOT NULL,
    foreign key (magazin_id) references magazine (id),
    foreign key (produs_id) references produse (id)
    );