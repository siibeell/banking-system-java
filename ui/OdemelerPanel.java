package ui;

import javax.swing.*;

import java.awt.*;

public class OdemelerPanel extends JPanel {

    private MainFrame mainFrame;

    public OdemelerPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JLabel lblBaslik = new JLabel("Ödemeler", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(new GridLayout(3,1,15,15));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(40,60,40,60));

        JButton btnFatura = new JButton("Fatura Ödeme");
        JButton btnBorc   = new JButton("Borç / Kredi Ödeme");
        JButton btnSigorta = new JButton("Sigorta & Emeklilik");

        menuPanel.add(btnFatura);
        menuPanel.add(btnBorc);
        menuPanel.add(btnSigorta);

        add(menuPanel, BorderLayout.CENTER);

        JButton btnGeri = new JButton("Geri");
        btnGeri.addActionListener(e -> mainFrame.islemlerEkraniGoster());
        btnFatura.addActionListener(e -> mainFrame.faturaMenuGoster());
        btnBorc.addActionListener(e -> mainFrame.borcOdemeEkraniGoster());
        btnSigorta.addActionListener(e ->mainFrame.sigortaEmeklilikMenuGoster());

        JPanel altPanel = new JPanel();
        altPanel.add(btnGeri);

        add(altPanel, BorderLayout.SOUTH);
    }
}
