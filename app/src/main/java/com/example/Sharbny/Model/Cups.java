package com.example.Sharbny.Model;

public class Cups {
    private String type;
    final static String bottle="Bottle";
    final static String cups="Cups";

    private int Capacity;
    public String gettype()
    {
        return this.type;
    }
    public void setType(String type)
    {
        if(type==bottle || type==cups)
        {
            this.type=type;
        }

    }
    public int getCapacity()
    {
        return this.Capacity;
    }
    public void setCapacity(int Capacity)
    {
        this.Capacity=Capacity;

    }
    Cups()
    {
        type=bottle;
        Capacity=100;

    }
    Cups(String type,int Capacity)
    {
        this.type=type;
        this.Capacity=Capacity;
    }
    @Override
    public String toString()
    {
        return type+Capacity;
    }
}
