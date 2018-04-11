SELECT *
FROM Orders
WHERE OrderID IN (
	SELECT OrderID
	FROM [Order Details]
	WHERE ProductID IN (
		SELECT ProductID
		FROM Products
		Where ProductName='Tofu'))
AND Freight>25
AND Freight<50