package model;

public class FaturaOdeme extends Odeme {

    private String tur;

    public FaturaOdeme(double tutar, String tur){
        super(tutar);
        this.tur = tur;
    }

    @Override
    public String odemeTipi(){
        return "Fatura (" + tur + ")";
    }
}
