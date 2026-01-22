package ui;

import javax.swing.*;

import model.Banka;
import model.Hesap;
import model.Musteri;
import model.VadeliHesap;
import model.VadesizHesap;
import model.YatirimHesabi;
import ui.MainFrame;

import java.awt.*;

public class DashboardPanel extends JPanel {

    private MainFrame mainFrame;
    private Banka banka;
    private Musteri aktifMusteri;

    // Bilgi etiketleri
    private JLabel lblAdSoyad;
    private JLabel lblHesapTur;
    private JLabel lblHesapNo;
    private JLabel lblBakiye;

    public DashboardPanel(MainFrame mainFrame, Banka banka){
        this.mainFrame = mainFrame;
        this.banka = banka;

        setLayout(new BorderLayout());

        // BAŞLIK
        JLabel lblBaslik = new JLabel("Hesap Paneli", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 22));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        // ORTA BİLGİ PANELİ
        JPanel bilgiPanel = new JPanel(new GridLayout(4,1,12,12));
        bilgiPanel.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));

        lblAdSoyad  = new JLabel();
        lblHesapTur = new JLabel();
        lblHesapNo  = new JLabel();
        lblBakiye   = new JLabel();

        lblAdSoyad.setFont(new Font("Arial", Font.BOLD, 16));
        lblHesapTur.setFont(new Font("Arial", Font.PLAIN, 14));
        lblHesapNo.setFont(new Font("Arial", Font.PLAIN, 14));
        lblBakiye.setFont(new Font("Arial", Font.BOLD, 15));

        bilgiPanel.add(lblAdSoyad);
        bilgiPanel.add(lblHesapTur);
        bilgiPanel.add(lblHesapNo);
        bilgiPanel.add(lblBakiye);

        add(bilgiPanel, BorderLayout.CENTER);

        // ALT MENÜ
        JPanel altPanel = new JPanel(new GridLayout(2,2,15,15));
        altPanel.setBorder(BorderFactory.createEmptyBorder(15,20,20,20));

        JButton btnHesaplarim   = new JButton("Hesaplarım");
        JButton btnIslemler     = new JButton("İşlemler");
        JButton btnBasvurular   = new JButton("Başvurular");
        JButton btnHesapGecmisi = new JButton("Hesap Geçmişi");
        JButton btnKartlarim = new JButton("Kartlarım");

        btnHesaplarim.addActionListener(e -> mainFrame.hesaplarimGoster());
        btnIslemler.addActionListener(e -> mainFrame.islemlerEkraniGoster());
        btnHesapGecmisi.addActionListener(e -> mainFrame.hesapGecmisiGoster());
        btnKartlarim.addActionListener(e -> mainFrame.kartlarimGoster());
        btnBasvurular.addActionListener(e -> mainFrame.basvurularGoster());

        altPanel.add(btnHesaplarim);
        altPanel.add(btnIslemler);
        altPanel.add(btnBasvurular);
        altPanel.add(btnKartlarim);
        altPanel.add(btnHesapGecmisi);

        add(altPanel, BorderLayout.SOUTH);
    }

    //GİRİŞTEN SONRA ÇAĞRIR
    public void setAktifMusteri(Musteri musteri){
        this.aktifMusteri = musteri;

        Hesap hesap = ilkHesabiGetir(musteri);
        if(hesap == null){
            int no = banka.getHesaplar().size() + 1001;
            hesap = new VadesizHesap(no, musteri, 5000);
            banka.hesapEkle(hesap);
        }

        ekraniGuncelle(hesap);
    }

    //İlk bulunan hesabı getir
    private Hesap ilkHesabiGetir(Musteri musteri){
        for(Hesap h : banka.getHesaplar()){
            if(h.getMusteri() == musteri){
                return h;
            }
        }
        return null;
    }

    //Bilgileri ekrana bas
    public void ekraniGuncelle(Hesap h){
        lblAdSoyad.setText("Kullanıcı: " +
                aktifMusteri.getAd() + " " + aktifMusteri.getSoyad());

        String tur;
        if(h instanceof VadeliHesap){
            tur = "Vadeli Hesap";
        }else if(h instanceof YatirimHesabi){
            tur = "Yatırım Hesabı";
        }else{
            tur = "Vadesiz TL Hesabı";
        }

        lblHesapTur.setText("Hesap Türü: " + tur);
        lblHesapNo.setText("Hesap No: " + h.getHesapNo());
        lblBakiye.setText("Bakiye: " + h.getBakiye() + " TL");
    }
    public void yenile(){
        if(aktifMusteri == null) return;

        Hesap h = ilkHesabiGetir(aktifMusteri);
        if(h != null){
            ekraniGuncelle(h);
        }
    }

}
