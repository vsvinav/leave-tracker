package com.hashedin.tracker;



import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class LeaveAccrualManager {

    public void creditLeaves(Employee e){
        if( e.getLeaveType() == LeaveType.paternityLeave && e.getSex().equals("male")) {
                e.setLeaveBalance((int)DAYS.between(LocalDate.now(), LocalDate.now().plusMonths(1)));
                return;
        }
         if(e.getLeaveType() == LeaveType.maternityLeave && e.getSex().equals("female")
                 && (int)DAYS.between(e.getJoiningDate(),LocalDate.now())>180)
        {
            e.setLeaveBalance((int)DAYS.between(LocalDate.now(), LocalDate.now().plusMonths(6)));
            return;
        }
        if(e.getJoiningDate().getMonth() == LocalDate.now().getMonth()){
            e.setLeaveBalance(0);
            return;
        }
         if(e.getLeavesTaken(LocalDate.now().getMonth())>=0 &&
                 e.getLeavesTaken(LocalDate.now().getMonth())<=e.getLeaveBalance()) {

            e.setLeaveBalance(e.getLeaveBalance()-e.getLeavesTaken(LocalDate.now().getMonth())+2);
        }


    }

}





