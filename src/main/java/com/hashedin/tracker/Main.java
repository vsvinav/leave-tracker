package com.hashedin.tracker;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");
        LeaveManager manager = new LeaveManager();
//        manager.leaveBalance(new Employee(1,"Someone","Developer",0, "female") , LocalDate.now())
        LocalDate date = LocalDate.now();
        System.out.println(date.getDayOfMonth());
    }
}