package ui;

import javax.swing.*;
import java.awt.*;

public class BasvurularPanel extends JPanel {

    private MainFrame mainFrame;

    public BasvurularPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JLabel lblBaslik = new JLabel("Başvurular", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(new GridLayout(3,1,15,15));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(50,60,50,60));

        JButton btnKart = new JButton("Kart Başvurusu");
        JButton btnKredi = new JButton("Kredi Başvurusu");
        JButton btnHesap = new JButton("Hesap Açma");

        btnKart.addActionListener(e -> mainFrame.kartBasvuruMenuGoster());
        btnKredi.addActionListener(e -> mainFrame.krediBasvuruMenuGoster());
        btnHesap.addActionListener(e -> mainFrame.hesapBasvuruMenuGoster());

        menuPanel.add(btnKart);
        menuPanel.add(btnKredi);
        menuPanel.add(btnHesap);

        add(menuPanel, BorderLayout.CENTER);

        JButton btnGeri = new JButton("Geri");
        btnGeri.addActionListener(e -> mainFrame.dashboardGoster());

        JPanel alt = new JPanel();
        alt.add(btnGeri);
        add(alt, BorderLayout.SOUTH);
    }
}
