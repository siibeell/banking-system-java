package model;

public class BankaKarti extends Kart {

    public BankaKarti(String kartNo, Musteri musteri){
        super(kartNo, musteri);
    }

    @Override
    public String getKartTuru(){
        return "Banka Kartı";
    }
}
