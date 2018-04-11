USE Northwind

SELECT Customers.CustomerID, Customers.CompanyName
FROM Customers
WHERE 
Customers.city='London' AND
Customers.CustomerID IN (
	SELECT CustomerID
	FROM Orders
	GROUP BY CustomerID
	HAVING COUNT(CustomerID)<5
);