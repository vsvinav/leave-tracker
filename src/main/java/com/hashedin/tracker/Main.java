package com.hashedin.tracker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args)  {
//        LeaveManager manager = new LeaveManager();
//        Employee e = new EmployeeMockData().getEmployeeDetails(2);
//
//        String s= manager.applyForLeave(new LeaveRequest(),e  ).reason + ".<br>For employee:" + e.getEmpName()
//                + " till " + e.getLeaveEndDate();
//        System.out.println(s);
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
