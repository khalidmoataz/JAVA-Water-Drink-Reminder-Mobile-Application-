package com.example.Sharbny.Model;

public class WaterIntake extends Users implements CalcIntake{

    private int intake;
    public WaterIntake(String firstname, String username, int Age, double Weight) {
     super(firstname,username,Age,Weight);
     this.intake = 0;
    }

    @Override
    public double waterintakecalc(int Age,double Weight) {
        double d=0;
        double ounces = 0;
        double liters;
        if(Age < 30)
        {
             d = Weight * 40;
        }
        if(Age <= 55 || Age >= 30)
        {
             d = Weight * 35;
        }
        if(Age > 55)
        {
            d = Weight * 30;
        }

        ounces = d/28.3;
        liters = ounces *0.02957;

        return liters;

    }
}
