USE Bank

IF EXISTS (SELECT * FROM sys.database_principals WHERE name = N'bankuser')
	DROP USER [bankuser]


IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = N'bankuser')
	CREATE USER [bankuser] FOR LOGIN [bankuser] WITH DEFAULT_SCHEMA=[dbo]

GRANT SELECT, INSERT, DELETE, UPDATE TO "bankuser"
GO
