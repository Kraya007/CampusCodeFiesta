select productCode , (buyPrice +(buyPrice*14.99999999999999/100)) as totalPrice from products
where quantityInStock < 100
