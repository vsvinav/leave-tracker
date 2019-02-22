package com.hashedin.tracker;


import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.DAYS;

public class CompoffManager {
    private LocalDateTime compOffDateTime;
    public CompoffManager() {}
    public CompoffManager(LocalDateTime workedDateStartTime, int employeeId, LocalDateTime workedDateEndTime) {
        this.workedDateStartTime = workedDateStartTime;
        this.employeeId = employeeId;
        this.workedDateEndTime = workedDateEndTime;
    }

    public List<LocalDateTime> getCompOffLog() {
        return compOffLog;
    }

    public void setCompOffLog(List<LocalDateTime> compOffLog) {
        this.compOffLog = compOffLog;
    }

    private List<LocalDateTime> compOffLog;
    private LocalDateTime workedDateStartTime;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    private int employeeId;
    public LocalDateTime getWorkedDateEndTime() {
        return workedDateEndTime;
    }

    public void setWorkedDateEndTime(LocalDateTime workedDateEndTime) {
        this.workedDateEndTime = workedDateEndTime;
    }

    private LocalDateTime workedDateEndTime;
    private int duration;

    public LocalDateTime getCompOffDateTime() {
        return compOffDateTime;
    }

    public void setCompOffDateTime(LocalDateTime compOffDateTime) {
        this.compOffDateTime = compOffDateTime;
    }

    public LocalDateTime getWorkedDateStartTime() {
        return workedDateStartTime;
    }

    public void setWorkedDateStartTime(LocalDateTime workedDateStartTime) {
        this.workedDateStartTime = workedDateStartTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    public ArrayList<Integer> findWeekend(Month m) {
        int year    = 2019;
        Month month = m;
        ArrayList<Integer> weekendList = new ArrayList<>();
        IntStream.rangeClosed(1, YearMonth.of(year, month).lengthOfMonth())
                .mapToObj(day -> LocalDate.of(year, month, day))
                .filter(date -> date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                        date.getDayOfWeek() == DayOfWeek.SUNDAY)
                .forEach(date -> weekendList.add(date.getDayOfMonth()));
        return weekendList;
    }
    int numberOfWeekendContained(LocalDate startDate, LocalDate endDate) {
        LocalDate start = startDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate end = endDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        return (int)DAYS.between(start, end)/ 7;
    }

}
