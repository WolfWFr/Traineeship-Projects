Use Bank

SELECT Balance, (
	SELECT SUM(AccountTransaction.Amount) FROM AccountTransaction WHERE Type='C' AND AccountID IN (
		SELECT ID FROM Account WHERE Account.Type='C' AND ClientID IN(
			SELECT ID FROM Client
			WHERE Client.FirstName='Tieneke'
			AND Client.FamilyName='Van-Brabandt')))-(
	SELECT SUM(AccountTransaction.Amount) FROM AccountTransaction WHERE Type='D' AND AccountID IN (
		SELECT ID FROM Account WHERE Account.Type='C' AND ClientID IN(
			SELECT ID FROM Client
			WHERE Client.FirstName='Tieneke'
			AND Client.FamilyName='Van-Brabandt'))) AS [Net Balance]
FROM Account
WHERE Account.Type='C' AND Account.ClientID IN
	(
	SELECT ID FROM Client
	WHERE Client.FirstName='Tieneke'
	AND Client.FamilyName='Van-Brabandt'
	);