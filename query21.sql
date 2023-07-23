select employeeNumber, firstName, lastName, reportsTo from employees
where firstName ='mami'
or firstName = 'mary'
or firstName = 'diane'
order by employeeNumber desc