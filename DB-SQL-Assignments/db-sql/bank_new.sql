
/****** Create Bank Database ******/
CREATE DATABASE "Bank";
GO

USE [Bank]
GO

exec sp_configure 'contained database authentication', 1
go
reconfigure
go

alter database Bank
set containment = partial
go

CREATE TABLE "Address" (
	"ID" 			INT IDENTITY PRIMARY KEY,
	"Street"		varchar(150) NULL,
	"StreetNumber"		varchar(5) NOT NULL,
	"ZipCode"		varchar(7) NOT NULL,
	"City"			varchar(100) NULL
);

/****** Create Client table ******/
CREATE TABLE "Client" (
	"ID" 			INT IDENTITY PRIMARY KEY,
	"ClientNumber"		varchar(10) NOT NULL,
	"FirstName"		varchar(100) NOT NULL,
	"MiddleName"		varchar(100) NULL,
	"FamilyName"		varchar(100) NOT NULL,
	"AddressID"		int NOT NULL REFERENCES "Address"("ID")
);

CREATE TABLE "Account" (
	"ID" 			INT IDENTITY PRIMARY KEY,
	"AccountNumber"		varchar(10) NOT NULL,
	"Balance"		float NOT NULL,
	"Type"			char(1) NOT NULL,
	"DateOpened"		date NOT NULL,
	"ClientID"		int NOT NULL REFERENCES "Client"("ID")
);

/****** Create PaymentAuthorization table ******/
CREATE TABLE "PaymentAuthorization" (
	"ID" 			INT IDENTITY PRIMARY KEY,
	"Amount"		float NOT NULL,
	"ExecutionDayOfMonth"	int NOT NULL,
	"CreationDate"		date NOT NULL,
	"Description"		text NULL,
	"AccountID"		int NOT NULL REFERENCES "Account"("ID"),
	"PaymentAccountID"	int NOT NULL REFERENCES "Account"("ID")
);

/****** Create AccountTransaction table ******/
CREATE TABLE "AccountTransaction" (
	"ID" 			INT IDENTITY PRIMARY KEY,
	"Amount"		float NOT NULL,
	"Description"		text NULL,
	"Date"			date NOT NULL,
	"Type"			char(1) NOT NULL,
	"Code"			char(2) NOT NULL,
	"AccountID"		int NOT NULL REFERENCES "Account"("ID")
);

/****** Create Loan table ******/
CREATE TABLE "Loan" (
	"ID" 			INT IDENTITY PRIMARY KEY,
	"Amount"		float NOT NULL,
	"ContractDate"		date NOT NULL,
	"ExpirationDate"	date NOT NULL,
	"NumberOfPayment"	int NOT NULL,
	"LoanPercentage"	float NOT NULL,
	"LoanPenaltyPercentage"	float NOT NULL,
	"DateClosed"		date NULL,
	"ClientID"		int NOT NULL REFERENCES "Client"("ID"),
	"AccountID"		int NOT NULL REFERENCES "Account"("ID")
);

/****** Create Payment table ******/
CREATE TABLE "Payment" (
	"ID" 			INT IDENTITY PRIMARY KEY,
	"Amount"		float NOT NULL,
	"Description"		text NULL,
	"Date"			date NOT NULL,
	"LoanID"		int NOT NULL REFERENCES "Loan"("ID")
);

/****** Create New User bankuser ******/
CREATE USER bankuser WITH PASSWORD = 'P@ssword1';

GRANT ALL PRIVILEGES TO bankuser WITH GRANT OPTION;
GO

/****** Insert data in Address table ******/
INSERT INTO "Address" ("Street","StreetNumber","ZipCode","City")
     VALUES ('Rembrandtlaan','91','3529 DV','Scherpenzeel')
INSERT INTO "Address" ("Street","StreetNumber","ZipCode","City")
     VALUES ('Utrechtseweg','301','3721 GA','De Bilt')
INSERT INTO "Address" ("Street","StreetNumber","ZipCode","City")
     VALUES ('Woudenbergsestraat','9','4381 LJ','Woudenberg')
INSERT INTO "Address" ("Street","StreetNumber","ZipCode","City")
     VALUES ('Amersfoortselaan','53','6497 DE','Amersfoort')
INSERT INTO "Address" ("Street","StreetNumber","ZipCode","City")
     VALUES ('Leusdersehei','112a','9823 EJ','Leusden')
;

/****** Insert data in Client table ******/
INSERT INTO "Client" ("ClientNumber","FirstName","MiddleName","FamilyName","AddressID")
     VALUES ('000001','Jan',null,'Jansen',1)
INSERT INTO "Client" ("ClientNumber","FirstName","MiddleName","FamilyName","AddressID")
     VALUES ('000002','Piet','van','Gelre',3)
INSERT INTO "Client" ("ClientNumber","FirstName","MiddleName","FamilyName","AddressID")
     VALUES ('000003','Klaas',null,'Pieterse',4)
INSERT INTO "Client" ("ClientNumber","FirstName","MiddleName","FamilyName","AddressID")
     VALUES ('000005','Edwin','van der','Laar',2)
INSERT INTO "Client" ("ClientNumber","FirstName","MiddleName","FamilyName","AddressID")
     VALUES ('000006','Bart','van','Driel',5)
INSERT INTO "Client" ("ClientNumber","FirstName","MiddleName","FamilyName","AddressID")
     VALUES ('000008','Tieneke',null,'Van-Brabandt',3)
INSERT INTO "Client" ("ClientNumber","FirstName","MiddleName","FamilyName","AddressID")
     VALUES ('000015','Truus',null,'Jansen',1)
;

/****** Insert data in Account table ******/
INSERT INTO "Account" ("AccountNumber","Balance","Type","DateOpened","ClientID")
     VALUES ('243791270','1000.00','C','2006-08-15',1)
INSERT INTO "Account" ("AccountNumber","Balance","Type","DateOpened","ClientID")
     VALUES ('123456789','2533.00','C','2005-01-01',2)
INSERT INTO "Account" ("AccountNumber","Balance","Type","DateOpened","ClientID")
     VALUES ('987654321','10056.23','C','2006-01-29',3)
INSERT INTO "Account" ("AccountNumber","Balance","Type","DateOpened","ClientID")
     VALUES ('497329874','26.50','C','2005-04-03',4)
INSERT INTO "Account" ("AccountNumber","Balance","Type","DateOpened","ClientID")
     VALUES ('213409758','-167.00','C','2006-02-28',5)
INSERT INTO "Account" ("AccountNumber","Balance","Type","DateOpened","ClientID")
     VALUES ('320985476','2594.86','C','2005-08-01',6)
INSERT INTO "Account" ("AccountNumber","Balance","Type","DateOpened","ClientID")
     VALUES ('878974509','-194.50','C','2006-05-08',7)
INSERT INTO "Account" ("AccountNumber","Balance","Type","DateOpened","ClientID")
     VALUES ('2987492387','2000.00','S','2006-08-15',1)
INSERT INTO "Account" ("AccountNumber","Balance","Type","DateOpened","ClientID")
     VALUES ('9832798723','3000.00','S','2006-01-29',3)
INSERT INTO "Account" ("AccountNumber","Balance","Type","DateOpened","ClientID")
     VALUES ('3209875724','4000.00','S','2006-02-28',5)
INSERT INTO "Account" ("AccountNumber","Balance","Type","DateOpened","ClientID")
     VALUES ('8098234723','3568.64','S','2005-08-01',6)
INSERT INTO "Account" ("AccountNumber","Balance","Type","DateOpened","ClientID")
     VALUES ('3094832098','25374.00','S','2006-08-01',7)
INSERT INTO "Account" ("AccountNumber","Balance","Type","DateOpened","ClientID")
     VALUES ('543513543','9879.00','B','2006-08-15',1)
INSERT INTO "Account" ("AccountNumber","Balance","Type","DateOpened","ClientID")
     VALUES ('768416453','3214.46','B','2005-05-01',4)
INSERT INTO "Account" ("AccountNumber","Balance","Type","DateOpened","ClientID")
     VALUES ('315645184','4320.00','B','2006-08-01',7)
;

/****** Insert data in PaymentAuthorization table ******/
INSERT INTO "PaymentAuthorization" ("Amount","ExecutionDayOfMonth","CreationDate","Description","AccountID", "PaymentAccountID")
     VALUES ('250.00',1,'2006-08-15','Standaard overboeking',1,7)
INSERT INTO "PaymentAuthorization" ("Amount","ExecutionDayOfMonth","CreationDate","Description","AccountID", "PaymentAccountID")
     VALUES ('25.00',1,'2006-08-15','Aflossing koop 2e hands auto',1,5)
INSERT INTO "PaymentAuthorization" ("Amount","ExecutionDayOfMonth","CreationDate","Description","AccountID", "PaymentAccountID")
     VALUES ('50.00',1,'2006-08-15','Overboeking spaarrekening',1,8)
INSERT INTO "PaymentAuthorization" ("Amount","ExecutionDayOfMonth","CreationDate","Description","AccountID", "PaymentAccountID")
     VALUES ('125.00',26,'2006-06-01','Overboeking spaarrekening',5,10)
INSERT INTO "PaymentAuthorization" ("Amount","ExecutionDayOfMonth","CreationDate","Description","AccountID", "PaymentAccountID")
     VALUES ('175.00','27','2005-11-13','Overboeking spaarrekening',6,11)
;

/****** Insert data in Loan table ******/
INSERT INTO "Loan" ("Amount","ContractDate","ExpirationDate","NumberOfPayment","LoanPercentage","LoanPenaltyPercentage","DateClosed","ClientID","AccountID")
     VALUES ('6000.00','2006-08-15','2009-08-15',12,6.2,8.1,null,1,1)
INSERT INTO "Loan" ("Amount","ContractDate","ExpirationDate","NumberOfPayment","LoanPercentage","LoanPenaltyPercentage","DateClosed","ClientID","AccountID")
     VALUES ('500.00','2005-03-01','2005-06-01',2,6.2,9.1,'2005-06-01',2,2)
INSERT INTO "Loan" ("Amount","ContractDate","ExpirationDate","NumberOfPayment","LoanPercentage","LoanPenaltyPercentage","DateClosed","ClientID","AccountID")
     VALUES ('300.00','2005-09-01','2005-12-01',3,8.5,12.0,'2005-07-01',2,2)
INSERT INTO "Loan" ("Amount","ContractDate","ExpirationDate","NumberOfPayment","LoanPercentage","LoanPenaltyPercentage","DateClosed","ClientID","AccountID")
     VALUES ('24000.00','2006-04-01','2014-04-01',48,5.8,8.1,null,2,2)
INSERT INTO "Loan" ("Amount","ContractDate","ExpirationDate","NumberOfPayment","LoanPercentage","LoanPenaltyPercentage","DateClosed","ClientID","AccountID")
     VALUES ('18000.00','2006-01-01','2021-01-01',180,8.5,11.7,null,6,6)
INSERT INTO "Loan" ("Amount","ContractDate","ExpirationDate","NumberOfPayment","LoanPercentage","LoanPenaltyPercentage","DateClosed","ClientID","AccountID")
     VALUES ('1200','2005-10-01','2006-04-01',6,7.5,9.9,'2006-03-31',4,4)
INSERT INTO "Loan" ("Amount","ContractDate","ExpirationDate","NumberOfPayment","LoanPercentage","LoanPenaltyPercentage","DateClosed","ClientID","AccountID")
     VALUES ('3000.00','2006-05-15','2009-10-15',5,7.1,9.6,null,4,4)
;

/****** Insert data in Payment table ******/
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('250.00','Standard loan payment','2005-04-15',2);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('250.00','Standard loan payment','2005-06-01',2);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('100.00','Standard loan payment','2005-10-01',3);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('100.00','Standard loan payment','2005-11-01',3);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('100.00','Standard loan payment','2005-12-01',3);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('500.00','Standard loan payment','2006-06-01',4);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('500.00','Standard loan payment','2006-08-01',4);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('100.00','Standard loan payment','2006-01-31',5);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('100.00','Standard loan payment','2006-02-28',5);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('100.00','Standard loan payment','2006-03-31',5);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('100.00','Standard loan payment','2006-04-30',5);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('100.00','Standard loan payment','2006-05-31',5);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('100.00','Standard loan payment','2006-06-30',5);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('100.00','Standard loan payment','2006-07-31',5);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('200.00','Standard loan payment','2005-10-31',6);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('200.00','Standard loan payment','2005-11-30',6);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('200.00','Standard loan payment','2005-12-31',6);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('200.00','Standard loan payment','2006-01-31',6);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('200.00','Standard loan payment','2006-02-28',6);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('200.00','Standard loan payment','2006-03-31',6);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('600.00','Standard loan payment','2006-06-15',7);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('600.00','Standard loan payment','2006-07-15',7);
INSERT INTO "Payment" ("Amount","Description","Date","LoanID")
     VALUES ('600.00','Standard loan payment','2006-08-15',7);
;

/****** Insert data in Transaction table ******/

/** client 1 **/
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('13000.00','Initiele storting','2006-08-15','C','MD',1);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('10000.00','Overboeking belegrekening','2006-08-15','D','MT',1);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('10000.00','Overboeking belegrekening','2006-08-15','C','MT',13);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('2000.00','Overboeking spaarrekening','2006-08-18','D','MT',1);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('2000.00','Overboeking spaarrekening','2006-08-18','C','MT',8);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('121.00','Standard money transfer','2006-08-19','D','MT',13);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('121.00','Standard money transfer','2006-08-19','C','MT',1);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('121.00','Standard withdraw','2006-08-19','D','MW',1);

/** client 2 **/
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('5000.00','Initiele storting','2005-01-01','C','MD',2);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('250','Standard loan payment','2005-04-15','D','LP',2);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('525.00','Standard withdraw','2005-05-19','D','MW',2);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('250','Standard loan payment','2005-06-01','D','LP',2);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('100','Standard loan payment','2005-10-01','D','LP',2);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('142.00','Standard withdraw','2005-10-23','D','MW',2);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('100','Standard loan payment','2005-11-01','D','LP',2);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('100','Standard loan payment','2005-12-01','D','LP',2);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('500','Standard loan payment','2006-06-01','D','LP',2);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('500','Standard loan payment','2006-08-01','D','LP',2);

/** client 3 **/
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('10000.00','Initiele storting','2006-02-16','C','MD',3);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('1943.77','Standard withdraw','2006-04-06','D','MW',3);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('5000.00','Standard deposit','2006-05-19','C','MD',3);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('3000.00','Overboeking spaarrekening','2006-07-05','D','MT',3);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('3000.00','Overboeking spaarrekening','2006-07-05','C','MT',9);

/** client 4 **/
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('4500.00','Initiele storting','2005-04-15','C','MD',4);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('4000.00','Overboeking belegrekening','2005-05-10','D','MT',4);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('4000.00','Overboeking belegrekening','2005-05-10','C','MT',14);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('1500.00','Standard deposit','2005-06-01','C','MD',4);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('200.00','Standard loan payment','2005-10-31','D','LP',4);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('200.00','Standard loan payment','2005-11-30','D','LP',4);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('200.00','Standard loan payment','2005-12-31','D','LP',4);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('200.00','Standard loan payment','2006-01-31','D','LP',4);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('200.00','Standard loan payment','2006-02-28','D','LP',4);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('785.54','Standard money transfer','2006-03-05','D','MT',14);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('785.54','Standard money transfer','2006-03-05','C','MT',4);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('200.00','Standard loan payment','2006-03-31','D','LP',4);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('600.00','Standard loan payment','2006-06-15','D','LP',4);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('250.00','Standard deposit','2006-06-28','C','MD',4);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('600.00','Standard loan payment','2006-07-15','D','LP',4);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('9.04','Standard withdraw','2006-07-30','D','MW',4);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('600.00','Standard loan payment','2006-08-15','D','LP',4);

/** client 5 **/
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('3000.00','Initiele storting','2006-03-01','C','MD',5);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('3000.00','Overboeking spaarrekening','2006-05-15','D','MT',5);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('3000.00','Overboeking spaarrekening','2006-05-15','C','MT',10);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('1500.00','Standard deposit','2006-06-10','C','MD',5);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('125.00','Overboeking spaarrekening','2006-06-26','D','PA',5);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('125.00','Overboeking spaarrekening','2006-06-26','C','PA',10);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('325.00','Standard withdraw','2006-06-28','D','MW',5);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('750.00','Standard money transfer','2006-07-01','D','MT',5);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('750.00','Standard money transfer','2006-07-01','C','MT',10);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('125.00','Overboeking spaarrekening','2006-07-26','D','PA',5);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('125.00','Overboeking spaarrekening','2006-07-26','C','PA',10);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('342.00','Standard withdraw','2006-08-03','D','MW',5);

/** client 6 **/
/** client 6 **/
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('5000.00','Initiele storting','2005-09-01','C','MD',6);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('175.00','Overboeking spaarrekening','2005-11-27','D','PA',6);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('175.00','Overboeking spaarrekening','2005-11-27','C','PA',11);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('175.00','Overboeking spaarrekening','2005-12-27','D','PA',6);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('175.00','Overboeking spaarrekening','2005-12-27','C','PA',11);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('2000.00','Standard money transfer','2006-01-10','D','MT',6);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('2000.00','Standard money transfer','2006-01-10','C','MT',11);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('175.00','Overboeking spaarrekening','2006-01-27','D','PA',6);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('175.00','Overboeking spaarrekening','2006-01-27','C','PA',11);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('100.00','Standard loan payment','2006-01-31','D','LP',6);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('175.00','Overboeking spaarrekening','2006-02-27','D','PA',6);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('175.00','Overboeking spaarrekening','2006-02-27','C','PA',11);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('100.00','Standard loan payment','2006-02-28','D','LP',6);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('175.00','Overboeking spaarrekening','2006-03-27','D','PA',6);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('175.00','Overboeking spaarrekening','2006-03-27','C','PA',11);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('6.36','Standard money transfer','2006-04-03','D','MT',11);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('6.36','Standard money transfer','2006-04-03','C','MT',6);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('100.00','Standard loan payment','2006-03-31','D','LP',6);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('175.00','Overboeking spaarrekening','2006-04-27','D','PA',6);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('175.00','Overboeking spaarrekening','2006-04-27','C','PA',11);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('100.00','Standard loan payment','2006-04-30','D','LP',6);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('175.00','Overboeking spaarrekening','2006-05-27','D','PA',6);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('175.00','Overboeking spaarrekening','2006-05-27','C','PA',11);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('100.00','Standard loan payment','2006-05-31','D','LP',6);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('175.00','Overboeking spaarrekening','2006-06-27','D','PA',6);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('175.00','Overboeking spaarrekening','2006-06-27','C','PA',11);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('2000.00','Standard deposit','2006-06-28','C','MD',6); 
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('100.00','Standard loan payment','2006-06-30','D','LP',6);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('175.00','Overboeking spaarrekening','2006-07-27','D','PA',6);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('175.00','Overboeking spaarrekening','2006-07-27','C','PA',11);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('136.50','Standard withdraw','2006-07-29','D','MW',6);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('100.00','Standard loan payment','2006-07-31','D','LP',6);

/** client 7 **/
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('20000.00','Initiele storting','2006-05-15','C','MD',7);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('15000.00','Overboeking spaarrekening','2006-05-15','D','MT',7);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('15000.00','Overboeking spaarrekening','2006-05-15','C','MT',12);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('7500.00','Standard deposit','2006-07-01','C','MD',7);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('10000.00','Standard money transfer','2006-07-01','D','MT',7);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('10000.00','Standard money transfer','2006-07-01','C','MT',12);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('2000.00','Standard withdraw','2006-07-17','D','MW',7);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('6500.00','Standard deposit','2006-08-01','C','MD',7);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('4320.00','Standard money transfer','2006-08-01','D','MT',7);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('4320.00','Standard money transfer','2006-08-01','C','MT',15);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('2000.00','Standard money transfer','2006-08-11','D','MT',7);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('2000.00','Standard money transfer','2006-08-11','C','MT',12);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('1626.00','Standard money transfer','2006-08-15','D','MT',12);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('1626.00','Standard money transfer','2006-08-15','C','MT',7);
INSERT INTO "AccountTransaction" ("Amount","Description","Date","Type","Code","AccountID")
     VALUES ('2500.50','Standard withdraw','2006-08-19','D','MW',7);
