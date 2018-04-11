SELECT ClientNumber, ClientID, FirstName,MiddleName,FamilyName,AccountID,Balance,Amount FROM Account
FULL OUTER JOIN Client ON Account.ClientID=Client.ID
FULL OUTER JOIN AccountTransaction ON Account.ID=AccountTransaction.AccountID
WHERE AccountTransaction.Code='LP'
ORDER BY ClientID