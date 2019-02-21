package com.hashedin.tracker;


import java.time.LocalDate;

public class LeaveRequestMockData {
    public LeaveRequest getMockData() {
        LeaveRequest request = new LeaveRequest(LocalDate.now(),LocalDate.now().plusDays(1));
        return request;
    }
}
