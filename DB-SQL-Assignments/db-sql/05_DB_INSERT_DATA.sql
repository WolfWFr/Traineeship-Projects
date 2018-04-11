USE Bank;

/****** Insert data in tables here ******/
INSERT INTO Bank.dbo.client(client_nummer, voornaam,achternaam,straatnaam,huisnr,postcode,plaatsnaam,rekeningnr)
VALUES ('1','Wolf','Freije','Hooft Graaflandstraat','1','3525VM','Utrecht','4763978');

INSERT INTO Bank.dbo.client(client_nummer, voornaam,tussenvoegsel,achternaam,straatnaam,huisnr,postcode,plaatsnaam,rekeningnr)
VALUES ('2','Robbert','van', 'Vugt', 'Nepstraat', '28','1122AB','Utrecht','123456789');