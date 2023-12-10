package com.example.democursach.Classes;

public class Radionuclide extends IsotopesOfChemical_Nb{
    public double HalfLife;
    public double Lambda;
    public int N;
    public int A;

    public Radionuclide(String label, double atomic_weight, Integer atomic_number, String name, int a, double lambda){
        this.Label = label;
        this.AtomicWeight = atomic_weight;
        this.AtomicNumber = atomic_number;
        this.Name = name;
        this.A = a;
        this.Lambda = lambda;
    }

    public void NuclearReaction(int m, int z){
        super.NuclearReaction(m, z);
        A+=m;
        N = A - AtomicNumber;
    }

    @Override
    public void ShowInfo() {
        System.out.println("Изотоп " + this.Label + "-" + A + " с периодом полураспада = " + this.HalfLife + "[сек.], атомный номер = " + this.AtomicNumber);
    }
}
