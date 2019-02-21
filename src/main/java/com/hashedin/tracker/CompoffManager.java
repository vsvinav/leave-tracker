package com.hashedin.tracker;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.DAYS;

public class CompoffManager {
    private LocalDate compOffDate;
    private LocalDate workedDate;
    private int duration;

    public LocalDate getCompOffDate() {
        return compOffDate;
    }

    public void setCompOffDate(LocalDate compOffDate) {
        this.compOffDate = compOffDate;
    }

    public LocalDate getWorkedDate() {
        return workedDate;
    }

    public void setWorkedDate(LocalDate workedDate) {
        this.workedDate = workedDate;
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
