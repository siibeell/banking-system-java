package model;

public abstract class Odeme {

    protected double tutar;

    public Odeme(double tutar){
        this.tutar = tutar;
    }

    public double getTutar(){
        return tutar;
    }

    public abstract String odemeTipi();
}
