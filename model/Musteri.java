package model;

public class Musteri {
    // kapsülleme
    private String ad;
    private String soyad;
    private String tcNo;
    private String telNo;
    private String sifre;   // YENİ

    // kurucu metot
    public Musteri(String ad, String soyad, String tcNo, String telNo, String sifre){
        this.ad = ad;
        this.soyad = soyad;
        this.tcNo = tcNo;
        this.telNo = telNo;
        this.sifre = sifre;
    }

    // getter - setter
    public String getAd(){
        return ad;
    }

    public void setAd(String ad){
        this.ad = ad;
    }

    public String getSoyad(){
        return soyad;
    }

    public void setSoyad(String soyad){
        this.soyad = soyad;
    }

    public String getTcNo(){
        return tcNo;
    }

    // TC değişmez → setter yok (bilinçli)
    
    public String getTelNo(){
        return telNo;
    }

    public void setTelNo(String telNo){
        this.telNo = telNo;
    }

    public String getSifre(){
        return sifre;
    }

    public void setSifre(String sifre){
        this.sifre = sifre;
    }

    public void bilgileriGoster(){
        System.out.println("Ad: " + ad);
        System.out.println("Soyad: " + soyad);
        System.out.println("TC No: " + tcNo);
        System.out.println("Telefon: " + telNo);
    }
}
