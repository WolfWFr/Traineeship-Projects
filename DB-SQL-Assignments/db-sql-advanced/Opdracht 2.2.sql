SELECT *
FROM Orders
WHERE OrderID IN (
	SELECT OrderID
	FROM [Order Details]
	WHERE ProductID IN (
		SELECT ProductID
		FROM Products
		WHERE Products.ProductName='pavlova')
	AND [Order Details].UnitPrice*[Order Details].Quantity>=800)