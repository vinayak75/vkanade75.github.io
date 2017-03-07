SELECT 
    CONCAT( d.Dname,  ':   ',
    COUNT(e.Ename)) AS 'DEPTPARTMENT NAME : NUMBER OF EMPLOYEE'
FROM   
	employee e
   INNER JOIN department d ON e.DeptNo = d.DeptNo
GROUP BY d.DeptNo;