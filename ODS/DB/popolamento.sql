use ODS;

insert into UTENTE(nome, cognome, email, password, amministratore, data_nascita)
values('Mario', 'Bianchi', 'uno@due.it', 'Password1', false, '1996/11/11'),
('Marco', 'Rossi', 'due@tre.it', 'Password1', true, '1989/08/13');

insert into CARTA_CREDITO(numero_carta, data_scadenza, cvv, nome_proprietario, cognome_proprietario, utente)
values('4003600592874153', '2020/10/20', '333', 'Mario', 'Bianchi', 1);

insert into INDIRIZZO(nome, cognome, cap, provincia, citta, via, cellulare, utente)
values('Mario', 'Bianchi', '12345', 'SA', 'Fisciano', 'via Roma, 5', '3456789012', 1);

insert into ORDINE(data, carta_credito, indirizzo, utente, totale)
values('2018/12/15', 1, 1, 1, 15), ('2018/01/21', 1, 1, 1, 18);

insert into PRODOTTO(codice_prodotto, descrizione, marca, modello, prezzo_compl, iva, in_vendita, categoria, foto)
values ('LE222F03O-K12', 'Composizione: 100% cotone, Materiale: Jeans, Vita: Normale, Chiusura: Bottone, Tasche: Tasche posteriori, Tasche laterali',
 'Levis', '501', 0, 0, false, 'jeans.uomo', 'img/levis_501.jpg'), ('ON321E17R-K11', 'Composizione: 97% poliestere, 3% elastan, Avvertenze: Non asciugare in asciugatrice, 
 Lavaggio a macchina a 30 gradi, Restringimento massimo del 5%, Scollo: Schiena scoperta, Fantasia: Floreale', 'Only', 'OnlNova Deep', 15, 22, true, 'camicie.donna', 'img/only_onldeep.jpeg');
 
 insert into TAGLIA values(1, 'S', 20), (1, 'M', 30), (1, 'L', 15), (2, 'S', 35), (2, 'M', 50), (2, 'L', 40);
 
 insert into PRODOTTO_IN_ORDINE(id_prodotto, prezzo_compl, iva, taglia, quantita) values(2, 15, 22, 'M', 1), (2, 18, 22, 'L', 1);
 
 insert into ORDINAZIONE values(1, 1), (2, 2);