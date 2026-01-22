package ui;

import javax.swing.*;

import model.Banka;
import model.Hesap;
import model.YatirimHesabi;
import service.IslemService;

import java.awt.*;

public class AlisSatisPanel extends JPanel {

    private MainFrame mainFrame;   

    private String varlikTuru; // DOVIZ / ALTIN
    private String islemTuru;  // ALIS / SATIS

    private JTextField txtMiktar;
    private JLabel lblKur;
    private JLabel lblTlBakiye;
    private JLabel lblBaslik;

    public AlisSatisPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());

        lblBaslik = new JLabel("", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4,1,10,10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40,60,40,60));

        txtMiktar = new JTextField();

        lblKur = new JLabel();
        lblTlBakiye = new JLabel();

        formPanel.add(lblKur);
        formPanel.add(lblTlBakiye);
        formPanel.add(new JLabel("Miktar"));
        formPanel.add(txtMiktar);

        add(formPanel, BorderLayout.CENTER);

        JPanel altPanel = new JPanel(new GridLayout(1,2,10,10));
        altPanel.setBorder(BorderFactory.createEmptyBorder(10,60,20,60));

        JButton btnOnay = new JButton("Onayla");
        JButton btnGeri = new JButton("Geri");

        altPanel.add(btnOnay);
        altPanel.add(btnGeri);

        add(altPanel, BorderLayout.SOUTH);

        btnGeri.addActionListener(e -> geriDon());
        btnOnay.addActionListener(e -> islemiYap());
    }

    public void setIslem(String varlikTuru, String islemTuru){
        this.varlikTuru = varlikTuru;
        this.islemTuru = islemTuru;

        lblBaslik.setText(
                (varlikTuru.equals("DOVIZ") ? "Döviz" : "Altın") + " " +
                (islemTuru.equals("ALIS") ? "Alış" : "Satış")
        );

        double kur = varlikTuru.equals("DOVIZ") ? 30.0 : 2000.0;
        lblKur.setText("Kur: " + kur + " TL");

        Hesap tlHesap = mainFrame.getAktifHesap();
        lblTlBakiye.setText(
                "TL Bakiyesi: " + String.format("%.2f", tlHesap.getBakiye()) + " TL"
        );

        txtMiktar.setText("");
    }

    private void geriDon(){
        if(varlikTuru.equals("DOVIZ")){
            mainFrame.dovizIslemleriMenuGoster();
        }else{
            mainFrame.altinIslemleriMenuGoster();
        }
    }

    private void islemiYap(){
        try{
            double miktar = Double.parseDouble(txtMiktar.getText().trim());
            if(miktar <= 0) throw new Exception();

            Hesap tlHesap = mainFrame.getAktifHesap();
            if(tlHesap == null){
                JOptionPane.showMessageDialog(this,"TL hesabı bulunamadı!");
                return;
            }

            mainFrame.getIslemService().yatirimIslemiYap(
                    tlHesap,
                    varlikTuru,
                    islemTuru,
                    miktar,
                    mainFrame.getAktifMusteri()
            );

            lblTlBakiye.setText(
                    "TL Bakiyesi: " +
                    String.format("%.2f", tlHesap.getBakiye()) + " TL"
            );

            mainFrame.dashboardYenile();

            JOptionPane.showMessageDialog(this,"İşlem başarılı!");
            mainFrame.dashboardGoster();

        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Geçersiz miktar!");
        }
    }
}
