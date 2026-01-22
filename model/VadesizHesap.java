package model;

public class VadesizHesap extends Hesap implements FaizHesaplama {
    //Kurucu Metot(hesap sınıfından miras alır)
    public VadesizHesap(int hesapNo, Musteri musteri, double bakiye){
        super(hesapNo, musteri, bakiye);        //üst sınıfların contructorını çağırır
    }

    public double faizHesapla(){            // vadesiz olduğu için faiz hesaplamaz
        return 0.0;
    }

    @Override
    public String getHesapTuru() {
        return "Vadesiz TL Hesabı";
    }
}
