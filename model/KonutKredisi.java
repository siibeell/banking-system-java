package model;

public class KonutKredisi extends Kredi {

    public KonutKredisi(double tutar, int vade){
        super(tutar, vade, 0.015);
    }

    @Override
    public double aylikTaksit(){
        double toplam = tutar + (tutar * faizOrani);
        return toplam / vade;
    }
}
