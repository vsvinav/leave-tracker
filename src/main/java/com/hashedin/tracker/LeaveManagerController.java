package com.hashedin.tracker;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaveManagerController {

    @RequestMapping("/employees/{id}/applyleave")
    public String leave(@PathVariable("id") int id){
        LeaveManager manager = new LeaveManager();
        Employee e = new EmployeeMockData().getEmployeeDetails(id);
        LeaveRequest mockData = new LeaveRequestMockData().getMockData();
        System.out.println(mockData.getStartDate());

        System.out.println(mockData.getEndDate());
        String s= manager.applyForLeave(mockData,e,2,LeaveType.maternityLeave  ).reason + ".<br>For employee:" + e.getEmpName()
                + " till " + mockData.getEndDate();
        System.out.println(mockData.getEndDate());

        return s;

    }
}
