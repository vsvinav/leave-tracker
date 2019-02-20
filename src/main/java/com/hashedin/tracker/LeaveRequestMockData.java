package com.hashedin.tracker;


import java.time.LocalDate;

public class LeaveRequestMockData {
    public LeaveRequest getMockData() {
        LeaveRequest request = new LeaveRequest(LocalDate.now(),LocalDate.of(2019,02,21));
        return request;
    }
}
