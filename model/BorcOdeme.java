package model;

public class BorcOdeme extends Odeme {

    private String tur;

    public BorcOdeme(double tutar, String tur){
        super(tutar);
        this.tur = tur;
    }

    @Override
    public String odemeTipi(){
        return "Borç (" + tur + ")";
    }
}
