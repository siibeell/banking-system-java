package model;

public class YatirimHesabi extends Hesap implements FaizHesaplama {

    private double getiriOrani;
    private double cekimKomisyonu;

    public YatirimHesabi(
            int hesapNo,
            Musteri musteri,
            double bakiye,
            double getiriOrani,
            double cekimKomisyonu
    ) {
        super(hesapNo, musteri, bakiye);
        this.getiriOrani = getiriOrani;
        this.cekimKomisyonu = cekimKomisyonu;
    }

    @Override
    public double faizHesapla() {
        return getBakiye() * getiriOrani;
    }

    @Override
    public void paraCek(double miktar) {
        double komisyon = miktar * cekimKomisyonu;
        double toplam = miktar + komisyon;

        if (toplam <= getBakiye()) {
            setBakiye(getBakiye() - toplam);
        }
    }

    @Override
    public String getHesapTuru() {
        return "Yatırım Hesabı";
    }

}
