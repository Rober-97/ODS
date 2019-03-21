DROP DATABASE IF EXISTS ODS;
CREATE DATABASE ODS;
USE ODS;

CREATE TABLE UTENTE(
id_utente		int		primary key auto_increment,
nome			varchar(30),
cognome			varchar(30),
email			varchar(30)	not null,
password		varchar(200)	not null,
amministratore		boolean,
data_nascita		date
);

CREATE TABLE CARTA_CREDITO(
numero_carta		varchar(16)	primary key,
data_scadenza		date		not null,
cvv			char(3)		not null,
nome_proprietario	varchar(30)	not null,
cognome_proprietario	varchar(30)	not null,
utente			int
	references UTENTE(id_utente)
		on delete no action	
		on update cascade
);

CREATE TABLE INDIRIZZO(
id_indirizzo		int		primary key auto_increment,
nome			varchar(30),
cognome			varchar(30),
cap			char(5),
provincia		char(2),
citta			varchar(30),
via			varchar(50),
cellulare		varchar(13),
utente			int
	references UTENTE(id_utente)
		on delete no action
		on update cascade
);

CREATE TABLE ORDINE(
id_ordine		int		primary key auto_increment,
data			date,
carta_credito		varchar(16)
	references CARTA_CREDITO(numero_carta)
		on delete no action
		on update cascade,

indirizzo		int
	references INDIRIZZO(id_indirizzo)
		on delete no action
		on update cascade,

utente			int
	references UTENTE(id_utente)
		on delete no action
		on update cascade,

totale			float
);

/*
Prodotto ha prezzo e iva;
Taglia aggiunge taglie e quantità per ogni taglia;
Prodotto_in_ordine ha prezzo, iva, quantità e taglia riferite al prodotto acquistato al momento dell'acquisto.
*/

CREATE TABLE PRODOTTO(
id_prodotto		int		primary key auto_increment,
codice_prodotto		varchar(20),
descrizione		varchar(200),
marca			varchar(20),
modello			varchar(20),
prezzo_compl		float,
iva			int,
in_vendita		boolean,
categoria		varchar(20),	/*categoria.maxicategoria	es:camicia.uomo
					maxicategorie tra:	uomo, donna, accessori
					categorie tra:	giacca, jeans, camicia, intimo, maglia e felpa, cappotto, pantalone*/
foto			varchar(30)	/* url foto */
);

CREATE TABLE TAGLIA(	/*entita debole*/
id_prodotto		int,
taglia			varchar(5),
quantita 		int,

	foreign key (id_prodotto)
	references PRODOTTO(id_prodotto)
		on delete no action
		on update cascade
);

CREATE TABLE PRODOTTO_IN_ORDINE(
id_prodotto_ordine	int primary key auto_increment,
id_prodotto		int	
	references PRODOTTO(id_prodotto)
		on delete no action
		on update cascade,

prezzo_compl		float,
iva			int,
taglia			varchar(5),
quantita 		int
);

CREATE TABLE ORDINAZIONE(
ordine			int,
prodotto		int,

	primary key(ordine, prodotto),

	foreign key (ordine)
	references ORDINE(id_ordine)
		on delete no action
		on update no action,

	foreign key (prodotto)
	references PRODOTTO_IN_ORDINE(id_prodotto_ordine)
		on delete no action
		on update no action
);