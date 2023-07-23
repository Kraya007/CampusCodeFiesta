select sum((quantityOrdered*priceEach)) as total from orderdetails
where orderNumber = '10100'