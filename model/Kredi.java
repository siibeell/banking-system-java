package model;

public abstract class Kredi {

    protected double tutar;
    protected int vade; // ay
    protected double faizOrani;

    public Kredi(double tutar, int vade, double faizOrani){
        this.tutar = tutar;
        this.vade = vade;
        this.faizOrani = faizOrani;
    }

    public double getTutar() {
        return tutar;
    }

    public abstract double aylikTaksit();

    public double toplamGeriOdeme(){
        return aylikTaksit() * vade;
    }
}
