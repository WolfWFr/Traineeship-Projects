DECLARE @DBName VARCHAR(50)
DECLARE @SqlQuery NVARCHAR(100)
SET @DBName = 'Bank'

USE master

IF EXISTS(SELECT * FROM sys.databases WHERE name = @DBName)
BEGIN
	SET @SqlQuery = 'DROP DATABASE ' + @DBName
	EXEC(@SqlQuery)
END

IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = @DBName)
BEGIN
    SET @SqlQuery = 'CREATE DATABASE ' + @DBName
    EXEC(@SqlQuery)
END
GO
