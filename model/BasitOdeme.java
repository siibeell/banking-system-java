package model;

public class BasitOdeme extends Odeme {

    private String tip;

    public BasitOdeme(String tip, double tutar){
        super(tutar);
        this.tip = tip;
    }

    @Override
    public String odemeTipi(){
        return tip;
    }
}
