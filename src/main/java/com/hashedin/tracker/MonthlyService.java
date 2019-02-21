//package com.hashedin.tracker;
//
//import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
//import java.time.temporal.TemporalAdjusters;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//public class MonthlyService {
////    final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
////
////    Runnable task = new Runnable() {
////        @Override
////        public void run() {
////            ZonedDateTime now = ZonedDateTime.now();
////            long delay = now.until(now.plusMonths(1), ChronoUnit.MILLIS);
////
////            try {
////                // ...
////            } finally {
////                executor.schedule(this, delay, TimeUnit.MILLISECONDS);
////            }
////        }
////    };
////    void perform() {
////        int dayOfMonth = 1;
////
////        ZonedDateTime dateTime = ZonedDateTime.now();
////        if (dateTime.getDayOfMonth() >= dayOfMonth) {
////            dateTime = dateTime.plusMonths(1);
////        }
////        dateTime = dateTime.withDayOfMonth(dayOfMonth);
////        executor.schedule(task,
////                ZonedDateTime.now().until(dateTime, ChronoUnit.MILLIS),
////                TimeUnit.MILLISECONDS);
////    }
//
//
//Runnable runnable = new Runnable() {
//            public void run() {
//                System.out.println("Run method invoked !!");
//            }
//        };
//        LocalDate currentDate = LocalDate.now();
//        LocalDate firstDayOfNextMonth = currentDate.with(TemporalAdjusters.firstDayOfNextMonth());
//        int noOfDaysBetween = (int)ChronoUnit.DAYS.between(currentDate, firstDayOfNextMonth);
//        ScheduledExecutorService service = Executors
//                .newSingleThreadScheduledExecutor();
//public MonthlyService(){
//    service.scheduleAtFixedRate(runnable, noOfDaysBetween, 30, TimeUnit.DAYS);
//}
//}
