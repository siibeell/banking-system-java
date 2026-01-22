package model;

import java.util.ArrayList;

public class Banka {

    private String bankaAdi;
    private ArrayList<Musteri> musteriler = new ArrayList<>();
    private ArrayList<Hesap> hesaplar = new ArrayList<>();

    public Banka(String bankaAdi){
        this.bankaAdi = bankaAdi;
    }

    // BANKA BİLGİ
    public String getBankaAdi(){
        return bankaAdi;
    }

    // MÜŞTERİ
    public void musteriEkle(Musteri musteri){
        musteriler.add(musteri);
    }

    public ArrayList<Musteri> getMusteriler(){
        return musteriler;
    }

    // HESAP
    public void hesapEkle(Hesap hesap){
        hesaplar.add(hesap);
    }

    public ArrayList<Hesap> getHesaplar(){
        return hesaplar;
    }

    public Hesap hesapBul(int hesapNo){
        for(Hesap h : hesaplar){
            if(h.getHesapNo() == hesapNo){
                return h;
            }
        }
        return null;
    }

    public YatirimHesabi yatirimHesabiBul(Musteri musteri){
        for(Hesap h : hesaplar){
            if(h instanceof YatirimHesabi && h.getMusteri() == musteri){
                return (YatirimHesabi) h;
            }
        }
        return null;
    }
}
