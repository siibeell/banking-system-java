package model;

public class VadeliHesap extends Hesap implements FaizHesaplama{
    private double faizOrani;

    public VadeliHesap(int hesapNo, Musteri musteri, double bakiye, double faizOrani){
        super(hesapNo, musteri, bakiye);
        this.faizOrani = faizOrani;
    }

    public double getFaizOrani(){
        return faizOrani;
    }

    public void setFaizOrani(double faizOrani){
        this.faizOrani = faizOrani;
    }

    //faiz hesaplama
    public double faizHesapla(){
        return getBakiye() * faizOrani;
    }

    @Override
    public String getHesapTuru() {
        return "Vadeli Hesap";
    }
}
