SELECT * FROM Client
WHERE ID IN(
SELECT ClientID FROM Loan
WHERE Amount>2500
)
AND AddressID IN(
SELECT ID FROM Address
WHERE SUBSTRING(ZipCode,1,4) BETWEEN 3000 AND 4000
);