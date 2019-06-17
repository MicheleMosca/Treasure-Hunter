use gamecloud;

create table Livelli(
	id int primary key
)

go

create table Utenti(
	username varchar(30) primary key,
	password varchar(30) not null
)

go

create table Partite(
	username varchar(30) references Utenti,
	livello int references Livelli(id),
	coins int not null,
	time time not null,
	primary key (username, livello)
)

go

-- Insrimento di un giocatore di prova

insert into Utenti values ('michele', 1234)
insert into Utenti values ('pluto', 1234)
insert into Utenti values ('pippo', 1234)
insert into Livelli values (1)
insert into Livelli values (2)
insert into Partite values ('michele', 1, 1, '0:30:15')
insert into Partite values ('pluto', 1, 3, '0:36:15')
insert into Partite values ('pippo', 1, 3, '0:32:15')

-- Visualizzazione delle tabelle

select *
from Utenti

select *
from Partite

select *
from Livelli

-- Query per la classifica

select username, coins, time
from Partite
where livello = 1
Order by coins desc, time asc

-- Query per il check del login

select *
from Utenti
where username = 'michele' and password = '1234'

-- Query per il check dello username per il sign up

select *
from Utenti
where username = 'michele'

-- Query per l'inserimento dei migliori record su partite

select coins, time
from Partite
where username = 'michele' and livello = 1

update Partite
set coins = 1, time = '0:30:0'
where username = 'michele' and livello = 1

-- Query per ricevere l'ultimo livello che l'utente ha finito

select livello
from Partite P
where username = 'michele' and livello >=ALL(
	select livello
	from Partite P1
	where P1.username = P.username)