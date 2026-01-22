package ui;

import javax.swing.*;

import model.BasitOdeme;
import model.Hesap;
import model.Odeme;
import service.IslemService;

import java.awt.*;

public class FaturaOdemePanel extends JPanel {

    private MainFrame mainFrame;
    private IslemService islemService;
    private String faturaTuru;

    private JTextField txtTutar;
    private JLabel lblBaslik;

    public FaturaOdemePanel(MainFrame mainFrame, IslemService islemService){
        this.mainFrame = mainFrame;
        this.islemService = islemService;
        setLayout(new BorderLayout());

        lblBaslik = new JLabel("", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(2,1,10,10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40,60,40,60));

        txtTutar = new JTextField();

        formPanel.add(new JLabel("Fatura Tutarı"));
        formPanel.add(txtTutar);

        add(formPanel, BorderLayout.CENTER);

        JPanel altPanel = new JPanel(new GridLayout(1,2,10,10));
        altPanel.setBorder(BorderFactory.createEmptyBorder(10,60,20,60));

        JButton btnOde = new JButton("Öde");
        JButton btnGeri = new JButton("Geri");

        altPanel.add(btnOde);
        altPanel.add(btnGeri);

        add(altPanel, BorderLayout.SOUTH);

        btnGeri.addActionListener(e -> mainFrame.faturaMenuGoster());
        btnOde.addActionListener(e -> faturaOde());
    }

    public void setFaturaTuru(String tur){
        this.faturaTuru = tur;
        lblBaslik.setText(tur + " Faturası Ödeme");
        txtTutar.setText("");
    }

    private void faturaOde(){
        try{
            double tutar = Double.parseDouble(txtTutar.getText().trim());
            Hesap hesap = mainFrame.getAktifHesap();

            if(hesap == null){
                JOptionPane.showMessageDialog(this,"Hesap bulunamadı!");
                return;
            }

            if(tutar <= 0){
                JOptionPane.showMessageDialog(this,"Geçersiz tutar!");
                return;
            }

            // 🔥 SERVICE ÜZERİNDEN ÖDEME
            Odeme odeme = new BasitOdeme(faturaTuru, tutar);
            islemService.odemeYap(hesap, odeme);

            JOptionPane.showMessageDialog(this,"Fatura ödendi!");
            txtTutar.setText("");
            mainFrame.dashboardYenile();
            mainFrame.faturaMenuGoster();

        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Geçersiz tutar!");
        }
    }
}
