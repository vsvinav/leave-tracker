package com.hashedin.tracker;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");
        LeaveManager manager = new LeaveManager();
        manager.leaveBalance(new Employee(1,"Someone","Developer",0, "female") , LocalDate.now());
    }

}