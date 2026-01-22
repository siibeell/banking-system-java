package ui;

import javax.swing.*;
import java.awt.*;

public class HesapBasvuruMenuPanel extends JPanel {

    private MainFrame mainFrame;

    public HesapBasvuruMenuPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JLabel lblBaslik = new JLabel("Hesap Açma", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        JPanel menu = new JPanel(new GridLayout(3,1,15,15));
        menu.setBorder(BorderFactory.createEmptyBorder(50,60,50,60));

        JButton btnVadesiz = new JButton("Vadesiz Hesap");
        JButton btnVadeli  = new JButton("Vadeli Hesap");
        JButton btnYatirim = new JButton("Yatırım Hesabı");

        menu.add(btnVadesiz);
        menu.add(btnVadeli);
        menu.add(btnYatirim);

        add(menu, BorderLayout.CENTER);

        // 🔽 ALT PANEL + GERİ BUTONU
        JPanel alt = new JPanel();
        JButton btnGeri = new JButton("Geri");
        alt.add(btnGeri);
        add(alt, BorderLayout.SOUTH);

        btnVadesiz.addActionListener(e -> {
            mainFrame.vadesizHesapAc();
            mainFrame.dashboardYenile();
            JOptionPane.showMessageDialog(this, "Vadesiz hesap açıldı.");
        });

        btnVadeli.addActionListener(e -> {
            mainFrame.vadeliHesapAc();
            mainFrame.dashboardYenile();
            JOptionPane.showMessageDialog(this, "Vadeli hesap açıldı.");
        });

        btnYatirim.addActionListener(e -> {
            mainFrame.yatirimHesabiAc();
            mainFrame.dashboardYenile();
            JOptionPane.showMessageDialog(this, "Yatırım hesabı açıldı.");
        });

        // 🔁 GERİ → BAŞVURULAR
        btnGeri.addActionListener(e ->
                mainFrame.basvurularGoster()
        );
    }
}
