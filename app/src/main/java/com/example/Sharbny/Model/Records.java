package com.example.Sharbny.Model;

public class Records {
    public int totaltimes;
    public Thirsty cups_drinked;
    public double total_liters_drinked;

    public double WeeklyAverageDrinks()
    {
        return totaltimes/7;
    }
    public double MonthlyAverageDrinks()
    {
        return totaltimes/30;
    }
    public double AnnualAverageDrinks()
    {
        return totaltimes/365;
    }
}
