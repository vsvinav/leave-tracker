package com.hashedin.tracker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        LeaveManager manager = new LeaveManager();
//        System.out.println(manager.applyForLeave(new LeaveRequestMockData().getMockData(),new Employee(),2,LeaveType.general).reason);

        SpringApplication.run(Main.class,args);
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