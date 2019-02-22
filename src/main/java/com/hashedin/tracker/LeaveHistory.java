package com.hashedin.tracker;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class LeaveHistory {
    @Id
    private int employeeID;
    private LocalDate startDate;
    private LocalDate endDate;
    LeaveHistory(int employeeID, LocalDate startDate, LocalDate endDate){
        this.employeeID = employeeID;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
