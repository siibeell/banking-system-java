package model;

public abstract class Kart {

    protected String kartNo;
    protected Musteri musteri;

    public Kart(String kartNo, Musteri musteri){
        this.kartNo = kartNo;
        this.musteri = musteri;
    }

    public String getKartNo(){
        return kartNo;
    }

    public Musteri getMusteri(){
        return musteri;
    }

    public abstract String getKartTuru();
}
