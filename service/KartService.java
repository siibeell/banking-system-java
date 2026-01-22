package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Banka;
import model.BankaKarti;
import model.Kart;
import model.KrediKarti;
import model.Musteri;

public class KartService {

    private Banka banka;
    private List<Kart> kartlar = new ArrayList<>();
    private Random random = new Random();

    public KartService(Banka banka){
        this.banka = banka;
    }

    // 1️⃣ Banka kartı otomatik oluştur (1 kez)
    public void bankaKartiOlustur(Musteri musteri){
        // Zaten varsa tekrar oluşturma
        for(Kart k : kartlar){
            if(k.getMusteri() == musteri && k instanceof BankaKarti){
                return;
            }
        }

        String kartNo = kartNoUret();
        BankaKarti bankaKarti = new BankaKarti(kartNo, musteri);
        kartlar.add(bankaKarti);
    }

    // 2️⃣ Kredi kartı başvurusu
    public KrediKarti krediKartiBasvur(Musteri musteri, double limit){
        // Zaten kredi kartı varsa verme
        for(Kart k : kartlar){
            if(k.getMusteri() == musteri && k instanceof KrediKarti){
                return null;
            }
        }

        String kartNo = kartNoUret();
        KrediKarti krediKarti = new KrediKarti(kartNo, musteri, limit);
        kartlar.add(krediKarti);
        return krediKarti;
    }

    // 3️⃣ Kullanıcının kartlarını getir
    public List<Kart> kartlariGetir(Musteri musteri){
        List<Kart> sonuc = new ArrayList<>();

        for(Kart k : kartlar){
            if(k.getMusteri() == musteri){
                sonuc.add(k);
            }
        }
        return sonuc;
    }

    // 🔧 Kart numarası üretici
    private String kartNoUret(){
        return "5400 " +
               (1000 + random.nextInt(9000)) + " " +
               (1000 + random.nextInt(9000)) + " " +
               (1000 + random.nextInt(9000));
    }
}
