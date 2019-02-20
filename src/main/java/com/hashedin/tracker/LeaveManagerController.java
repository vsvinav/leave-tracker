package com.hashedin.tracker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaveManagerController {

    @RequestMapping("/applyleave")
    public String leave(){
        LeaveManager manager = new LeaveManager();
        Employee e = new EmployeeMockData().getEmployeeDetails(2);
        LeaveRequest mockData = new LeaveRequestMockData().getMockData();
        System.out.println(mockData.getStartDate());

        System.out.println(mockData.getEndDate());
        String s= manager.applyForLeave(mockData,e,2,LeaveType.maternityLeave  ).reason;
        System.out.println(mockData.getEndDate());

        return s;

    }
}
