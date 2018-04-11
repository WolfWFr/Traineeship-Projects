USE Bank;

/****** Create tables here ******/
CREATE TABLE client(
client_nummer int NOT NULL,
voornaam varchar(255) NOT NULL,
tussenvoegsel varchar(255),
achternaam varchar(255) NOT NULL,
rekeningnr varchar(255) NOT NULL,
straatnaam varchar(255) NOT NULL,
huisnr int NOT NULL,
postcode varchar(255) NOT NULL,
plaatsnaam varchar(255) NOT NULL
);

CREATE TABLE rekening(
rekeningnr varchar(255) NOT NULL,
tegoed int NOT NULL,
rekeningType varchar(255) NOT NULL
);

CREATE TABLE transactie(
transID int NOT NULL,
rekeningnr varchar(255) NOT NULL,
transactieType varchar(255) NOT NULL,
bedrag int NOT NULL,
beschrijving varchar(255),
datum varchar(255) NOT NULL
);

CREATE TABLE [auto-transactie](
autoTransID int NOT NULL,
bedrag int NOT NULL,
dagVDMaand int NOT NULL,
rekeningnr varchar(255) NOT NULL,
beschrijving varchar(255),
datumVAanmaak varchar(255) NOT NULL
);

CREATE TABLE lening(
leningID int NOT NULL,
contractDatum varchar(255) NOT NULL,
vervalDatum varchar(255) NOT NULL,
bedrag int NOT NULL,
rekeningnr varchar(255) NOT NULL,
tegenRekening varchar(255) NOT NULL,
interval varchar(255) NOT NULL
);
