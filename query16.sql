select officeCode, count(*) as numEmployees from employees
group by officeCode