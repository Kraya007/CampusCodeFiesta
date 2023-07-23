#Give the phone numbers of customers whose salesRepEmployeeNumber is missing.
select phone from customers as phone
where salesRepEmployeeNumber is null