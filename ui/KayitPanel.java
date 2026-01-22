package ui;

import javax.swing.*;

import model.Banka;
import model.Musteri;

import java.awt.*;

public class KayitPanel extends JPanel {

    private MainFrame mainFrame;
    private Banka banka;

    private JTextField txtAd;
    private JTextField txtSoyad;
    private JTextField txtTc;
    private JTextField txtTel;
    private JPasswordField txtSifre;
    private JLabel lblHata;

    public KayitPanel(MainFrame mainFrame, Banka banka){
        this.mainFrame = mainFrame;
        this.banka = banka;

        setLayout(new BorderLayout());

        // Başlık
        JLabel lblBaslik = new JLabel("Kayıt Ol", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
        add(lblBaslik, BorderLayout.NORTH);

        // Form alanı
        JPanel formPanel = new JPanel(new GridLayout(10, 1, 8, 8));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));

        txtAd = new JTextField();
        txtSoyad = new JTextField();
        txtTc = new JTextField();
        txtTel = new JTextField();
        txtSifre = new JPasswordField();

        formPanel.add(new JLabel("Ad"));
        formPanel.add(txtAd);

        formPanel.add(new JLabel("Soyad"));
        formPanel.add(txtSoyad);

        formPanel.add(new JLabel("TC Kimlik No"));
        formPanel.add(txtTc);

        formPanel.add(new JLabel("Telefon"));
        formPanel.add(txtTel);

        formPanel.add(new JLabel("Şifre"));
        formPanel.add(txtSifre);

        add(formPanel, BorderLayout.CENTER);

        // Alt alan
        JPanel altPanel = new JPanel(new BorderLayout());

        lblHata = new JLabel("", SwingConstants.CENTER);
        lblHata.setForeground(Color.RED);

        JPanel butonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        butonPanel.setBorder(BorderFactory.createEmptyBorder(10, 60, 20, 60));

        JButton btnKaydet = new JButton("Kaydet");
        JButton btnGeri = new JButton("Geri");

        butonPanel.add(btnKaydet);
        butonPanel.add(btnGeri);

        altPanel.add(lblHata, BorderLayout.NORTH);
        altPanel.add(butonPanel, BorderLayout.CENTER);

        add(altPanel, BorderLayout.SOUTH);

        btnKaydet.addActionListener(e -> kayitOl());
        btnGeri.addActionListener(e -> mainFrame.anaEkranaDon());
    }

    private void kayitOl(){
        String ad = txtAd.getText().trim();
        String soyad = txtSoyad.getText().trim();
        String tc = txtTc.getText().trim();
        String tel = txtTel.getText().trim();
        String sifre = new String(txtSifre.getPassword());

        if(ad.isEmpty() || soyad.isEmpty() || tc.isEmpty() || tel.isEmpty() || sifre.isEmpty()){
            lblHata.setText("Tüm alanları doldurunuz!");
            return;
        }

        // Yeni müşteri oluştur
        Musteri musteri = new Musteri(ad, soyad, tc, tel, sifre);
        banka.musteriEkle(musteri);

        // Alanları temizle
        txtAd.setText("");
        txtSoyad.setText("");
        txtTc.setText("");
        txtTel.setText("");
        txtSifre.setText("");
        lblHata.setText("");

        JOptionPane.showMessageDialog(this, "Kayıt başarılı!");

        // Ana ekrana dön
        mainFrame.anaEkranaDon();
    }
}
