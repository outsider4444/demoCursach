package com.example.democursach.Classes;

public abstract class IsotopesOfChemical_Nb {
    public String Label;
    public double AtomicWeight;
    public Integer AtomicNumber;
    public String Name;

    public void NuclearReaction(int m, int z){
        this.AtomicNumber += z;
    }

    public abstract void ShowInfo();
}
