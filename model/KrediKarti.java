package model;

public class KrediKarti extends Kart {

    private double limit;
    private double borc;

    public KrediKarti(String kartNo, Musteri musteri, double limit){
        super(kartNo, musteri);
        this.limit = limit;
        this.borc = 0;
    }

    public double getLimit(){
        return limit;
    }

    public double getBorc(){
        return borc;
    }

    public void borcArtir(double miktar){
        if(miktar > 0){
            borc += miktar;
        }
    }

    public void borcOde(double miktar){
        if(miktar > 0 && miktar <= borc){
            borc -= miktar;
        }
    }

    @Override
    public String getKartTuru(){
        return "Kredi Kartı";
    }
}
