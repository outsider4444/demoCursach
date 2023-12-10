package com.example.democursach.Classes;

public class StableIsotope extends IsotopesOfChemical_Nb{
    public double NaturalContent;
    public long N;
    public int A;

    public StableIsotope(String label, double atomic_weight, Integer atomic_number, String name, int a, double content){
        this.Label = label;
        this.AtomicWeight = atomic_weight;
        this.AtomicNumber = atomic_number;
        this.Name = name;
        this.A = a;
        this.NaturalContent = content;
    }

    @Override
    public void ShowInfo() {

    }
}
