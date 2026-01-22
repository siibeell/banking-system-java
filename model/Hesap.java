package model;

import java.util.LinkedList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Hesap {

    private int hesapNo;
    private double bakiye;
    private Musteri musteri;

    private LinkedList<String> islemGecmisi = new LinkedList<>();

    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public Hesap(int hesapNo, Musteri musteri, double bakiye){
        this.hesapNo = hesapNo;
        this.musteri = musteri;
        this.bakiye = bakiye;
    }

    public int getHesapNo(){ return hesapNo; }
    public double getBakiye(){ return bakiye; }
    public Musteri getMusteri(){ return musteri; }

    protected void setBakiye(double bakiye){
        this.bakiye = bakiye;
    }

    protected void islemEkle(String islem){
        String zaman = LocalDateTime.now().format(FORMAT);
        islemGecmisi.add(zaman + " - " + islem);
    }

    public LinkedList<String> getIslemGecmisi(){
        return islemGecmisi;
    }

    public void paraYatir(double miktar){
        if(miktar > 0){
            bakiye += miktar;
            islemEkle("Para yatırıldı: +" + miktar + " TL");
        }
    }

    public void paraCek(double miktar){
        if(miktar > 0 && miktar <= bakiye){
            bakiye -= miktar;
            islemEkle("Para çekildi: -" + miktar + " TL");
        }
    }

    public abstract String getHesapTuru();

    @Override
    public String toString(){
        return getHesapTuru() +
            " | Hesap No: " + hesapNo +
            " | Bakiye: " + bakiye + " TL";
    }

    public abstract double faizHesapla();

    public void bakiyeAzalt(double miktar){
        if(miktar > 0 && miktar <= bakiye){
            bakiye -= miktar;
        }
    }

    public void bakiyeArtir(double miktar){
        if(miktar > 0){
            bakiye += miktar;
        }
    }

    public void islemKaydet(String mesaj){
        islemEkle(mesaj);
    }
}
