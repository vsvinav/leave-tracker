package com.hashedin.tracker;

import org.springframework.web.bind.annotation.*;

@RestController
public class LeaveManagerController {

    @RequestMapping("/employees/{id}/applyleave")
    public String leave(@PathVariable("id") int id){
        LeaveManager manager = new LeaveManager();
        Employee e = new EmployeeMockData().getEmployeeDetails(id);

        String s= manager.applyForLeave(new LeaveRequest(),e  ).getReason() + ".<br>For employee:" + e.getEmpName()
                + " till " + e.getLeaveEndDate();

        return s;

    }

    @RequestMapping(value = "/employees/applyleave", method = RequestMethod.POST)
    public String leave(@RequestBody Employee e){
        LeaveManager manager = new LeaveManager();
        String s= manager.applyForLeave(new LeaveRequest(),e  ).getReason() + ".<br>For employee:" + e.getEmpName()
                + " till " + e.getLeaveEndDate();

        return s;

    }
}
