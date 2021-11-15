package com.example.Sharbny.Model;

public class SetProgressBar {
    public double totalcups,intakenum;
    public SetProgressBar(double totalcupsm,double intakenum)
    {
        this.totalcups = totalcups;
        this.intakenum = intakenum;
    }
    public int CalcPercentage()
    {
        return (int) (totalcups/intakenum )*100;
    }
}
