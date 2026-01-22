package ui;

import javax.swing.*;

import java.awt.*;

public class AnaPanel extends JPanel {

    private MainFrame mainFrame;

    public AnaPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());

        // Üst başlık
        JLabel lblBaslik = new JLabel("Mobil Bankacılık", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 22));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(40, 10, 40, 10));

        add(lblBaslik, BorderLayout.NORTH);

        // Orta butonlar
        JPanel butonPanel = new JPanel();
        butonPanel.setLayout(new GridLayout(2, 1, 15, 15));
        butonPanel.setBorder(BorderFactory.createEmptyBorder(50, 60, 50, 60));

        JButton btnGiris = new JButton("Giriş Yap");
        JButton btnKayit = new JButton("Kayıt Ol");

        btnGiris.setFont(new Font("Arial", Font.PLAIN, 16));
        btnKayit.setFont(new Font("Arial", Font.PLAIN, 16));

        butonPanel.add(btnGiris);
        butonPanel.add(btnKayit);

        add(butonPanel, BorderLayout.CENTER);

        // Buton olayları
        btnGiris.addActionListener(e -> mainFrame.girisEkraniGoster());
        btnKayit.addActionListener(e -> mainFrame.kayitEkraniGoster());
    }
}
