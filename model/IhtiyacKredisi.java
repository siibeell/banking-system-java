package model;

public class IhtiyacKredisi extends Kredi {

    public IhtiyacKredisi(double tutar, int vade){
        super(tutar, vade, 0.03);
    }

    @Override
    public double aylikTaksit(){
        double toplam = tutar + (tutar * faizOrani);
        return toplam / vade;
    }
}
