package com.hashedin.tracker;

public class LeaveResponse {
    private LeaveStatus status;
    private String reason;

    public LeaveResponse(LeaveStatus status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
