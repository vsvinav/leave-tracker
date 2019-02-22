package com.hashedin.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaveManagerController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/employee/{id}/leave/apply")
    public String leave(@PathVariable("id") int id) {
        LeaveManager manager = new LeaveManager();
        Employee e = employeeService.getEmployee(id);

        String s= manager.applyForLeave(new LeaveRequest(),e  ).getReason() + ".\nFor employee:" + e.getEmpName()
                + " till " + e.getLeaveEndDate();

        return s;

    }


}
