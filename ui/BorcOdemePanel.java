package ui;

import javax.swing.*;

import model.BasitOdeme;
import model.Hesap;
import model.Odeme;
import service.IslemService;

import java.awt.*;

public class BorcOdemePanel extends JPanel {

    private MainFrame mainFrame;
    private IslemService islemService;

    private JTextField txtTutar;

    public BorcOdemePanel(MainFrame mainFrame, IslemService islemService){
        this.mainFrame = mainFrame;
        this.islemService = islemService;
        setLayout(new BorderLayout());

        JLabel lblBaslik = new JLabel("Kredi Ödeme", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4,1,10,10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40,60,40,60));

        txtTutar = new JTextField();

        formPanel.add(new JLabel("Tutar"));
        formPanel.add(txtTutar);

        add(formPanel, BorderLayout.CENTER);

        JPanel altPanel = new JPanel(new GridLayout(1,2,10,10));
        altPanel.setBorder(BorderFactory.createEmptyBorder(10,60,20,60));

        JButton btnOde = new JButton("Öde");
        JButton btnGeri = new JButton("Geri");

        altPanel.add(btnOde);
        altPanel.add(btnGeri);
        add(altPanel, BorderLayout.SOUTH);

        btnGeri.addActionListener(e -> mainFrame.odemelerEkraniGoster());
        btnOde.addActionListener(e -> borcOde());
    }

    private void borcOde(){
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

            // 🔥 SERVİCE ÜZERİNDEN ÖDEME
            Odeme odeme = new BasitOdeme("Kredi Taksiti", tutar);
            islemService.odemeYap(hesap, odeme);

            JOptionPane.showMessageDialog(this, "Kredi taksiti ödendi.");
            txtTutar.setText("");
            mainFrame.dashboardYenile();

        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Geçersiz giriş!");
        }
    }
}
