select O.orderNumber, R.status, (O.quantityOrdered) as quantityOrdered, P.productName
from orderdetails O, products P, orders R
where O.orderNumber = R.orderNumber
and O.productCode = P.productCode
and  P.productVendor ='Exoto Designs' 
and R.status = 'cancelled'