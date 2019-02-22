API list for the code : 
-------------------------------------------------------------
/employee : would show the list of all employees.

/employee/{id} : would show the details of a specific employee with id = {id}.

/employee/create : would require you to add JSON code such as below:
Example JSON to create a new employee:
{
    "empId": 2,
    "empName": "Angela",
    "empDesignation": "Developer",
    "leaveBalance": 2,
    "sex": "female",
    "optionalLeaveBalance": 1,
    "leaveType": "general",
    "leaveStartDate": "2019-02-21",
    "leaveEndDate": "2019-02-22",
    "joiningDate": "2019-01-01",
    "optionalLeavesTaken": 0,
    "compOffBalance": 0
}

/employee/{id} (as PUT) : would update the details of the employee with id = {id}. Uses same JSON format as in create.

/employee/{id}/delete :  would delete the employee with id = {id}.

/employee/{id}/leavebalance : would show the leaveBalance for the specific employee with id = {id}.

/employee/{id}/compoffbalance : would show the compoffbalance for the specific employee with id = {id}.

/employee/{id}/leave/apply : Returns a string, would display a message about the leave approval rejection, based on the business logic behind.


