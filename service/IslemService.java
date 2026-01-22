package service;

import model.Banka;
import model.Hesap;
import model.Kredi;
import model.Musteri;
import model.Odeme;
import model.YatirimHesabi;

public class IslemService {

    private Banka banka;

    public IslemService(Banka banka){
        this.banka = banka;
    }

    // HAVALE
    public void havaleYap(Hesap gonderen, Hesap alici, double miktar, boolean ayniBanka){

        if(miktar <= 0) return;

        double kesinti = ayniBanka ? miktar * 0.01 : miktar * 0.03;
        double toplam = miktar + kesinti;

        if(gonderen.getBakiye() < toplam) return;

        gonderen.bakiyeAzalt(toplam);
        alici.bakiyeArtir(miktar);

        gonderen.islemKaydet("Havale gönderildi: -" + miktar + " TL");
        alici.islemKaydet("Havale alındı: +" + miktar + " TL");
    }

    // ÖDEME
    public void odemeYap(Hesap hesap, Odeme odeme){

        if(odeme.getTutar() <= 0) return;
        if(hesap.getBakiye() < odeme.getTutar()) return;

        hesap.bakiyeAzalt(odeme.getTutar());
        hesap.islemKaydet(
            odeme.odemeTipi() + " ödendi: -" + odeme.getTutar() + " TL"
        );
    }

    // KREDİ
    public void krediCek(Hesap hesap, Kredi kredi){

        hesap.bakiyeArtir(kredi.getTutar());

        hesap.islemKaydet(
            "Kredi çekildi: +" + kredi.getTutar() +
            " TL | Aylık Taksit: " +
            String.format("%.2f", kredi.aylikTaksit()) + " TL"
        );
    }

    // YATIRIM ALIŞ / SATIŞ
    public void yatirimIslemiYap(
            Hesap tlHesap,
            String varlikTuru,
            String islemTuru,
            double miktar,
            Musteri musteri
    ){
        double fiyat = varlikTuru.equals("DOVIZ") ? 30 : 2000;
        double tutar = miktar * fiyat;

        YatirimHesabi yatirimHesabi = banka.yatirimHesabiBul(musteri);

        if(islemTuru.equals("ALIS")){
            if(tlHesap.getBakiye() < tutar) return;

            tlHesap.bakiyeAzalt(tutar);

            if(yatirimHesabi == null){
                int no = banka.getHesaplar().size() + 1001;
                yatirimHesabi = new YatirimHesabi(
                        no, musteri, 0, 0.03, 0.01
                );
                banka.hesapEkle(yatirimHesabi);
            }

            yatirimHesabi.bakiyeArtir(tutar);

            tlHesap.islemKaydet(
                    (varlikTuru.equals("DOVIZ") ? "Döviz" : "Altın") +
                    " Alış: " + miktar + " birim (" + tutar + " TL)"
            );

        } else { // SATIS
            if(yatirimHesabi == null || yatirimHesabi.getBakiye() < tutar) return;

            yatirimHesabi.bakiyeAzalt(tutar);
            tlHesap.bakiyeArtir(tutar);

            tlHesap.islemKaydet(
                    (varlikTuru.equals("DOVIZ") ? "Döviz" : "Altın") +
                    " Satış: " + miktar + " birim (+" + tutar + " TL)"
            );
        }
    }
}
