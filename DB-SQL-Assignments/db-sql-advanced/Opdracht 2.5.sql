SELECT DISTINCT City
FROM Employees
WHERE City IN (
	SELECT City
	FROM Customers)