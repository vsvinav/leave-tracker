package com.hashedin.tracker;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello");
        LocalDate date = LocalDate.now();
        System.out.println(date.getDayOfMonth());
        System.out.println(date.getMonth());
        System.out.println(date.getYear());

    }
//    Runnable runnable = new Runnable() {
//        public void run() {
//            System.out.println("Run method invoked !!");
//        }
//    };
//    LocalDate currentDate = LocalDate.now();
//    LocalDate firstDayOfNextMonth = currentDate.with(TemporalAdjusters.firstDayOfNextMonth());
//    int noOfDaysBetween = (int)ChronoUnit.DAYS.between(currentDate, firstDayOfNextMonth);
//    ScheduledExecutorService service = Executors
//            .newSingleThreadScheduledExecutor();
//    public void MonthlyService(){
//        service.scheduleAtFixedRate(runnable, noOfDaysBetween, 30, TimeUnit.DAYS);
//    }
}