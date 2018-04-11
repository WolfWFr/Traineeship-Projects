Use Bank

SELECT Client.ID AS ClientID, Client.FirstName, Client.FamilyName, Loan.AccountID AS AccountID, SUM(Payment.Amount) AS [Sum of Payments]
FROM Loan
LEFT JOIN Payment ON Loan.ID=Payment.LoanID
INNER JOIN Client ON Loan.ClientID=Client.ID
WHERE Loan.DateClosed IS NULL
GROUP BY Client.ID, Loan.AccountID, Client.FamilyName, Client.FirstName

SELECT Client.ID AS ClientID, Client.FirstName, Client.MiddleName, Client.FamilyName, SUM(Payment.Amount) as [Sum of Payments] 
FROM Client, Payment
WHERE Payment.LoanID IN (
	SELECT Loan.ID FROM Loan 
	WHERE Loan.DateClosed is null AND Loan.ClientID = Client.ID)
GROUP BY Client.ID, Client.FirstName, Client.MiddleName, Client.FamilyName;