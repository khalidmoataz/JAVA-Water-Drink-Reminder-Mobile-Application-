package com.example.Sharbny.Model;

public class Goal extends Users implements GoalCalculation{

    public void DailyGoalcalc (double extra)
    {
        double extra2 =   (super.liters_recomended + extra);
             if(super.The_Records.total_liters_drinked >= extra2)
             {
                 System.out.println("You have reached your daily goal");
             }
             else
                 {
                     System.out.println("Still more cups to go");
                 }
    }


}
