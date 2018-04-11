USE master

IF EXISTS (SELECT name FROM master.sys.server_principals WHERE name = 'bankuser')
	DROP LOGIN [bankuser]

IF NOT EXISTS (SELECT name FROM master.sys.server_principals WHERE name = 'bankuser')
	CREATE LOGIN [bankuser] WITH PASSWORD=N'P@ssword1', DEFAULT_DATABASE=[Bank]

ALTER LOGIN [bankuser] ENABLE
GO
