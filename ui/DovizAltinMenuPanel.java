package ui;

import javax.swing.*;

import java.awt.*;

public class DovizAltinMenuPanel extends JPanel {

    private MainFrame mainFrame;

    public DovizAltinMenuPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JLabel lblBaslik = new JLabel("Döviz & Altın", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(new GridLayout(2,1,15,15));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

        JButton btnDoviz = new JButton("Döviz İşlemleri");
        btnDoviz.addActionListener(e -> mainFrame.dovizIslemleriMenuGoster());
        JButton btnAltin = new JButton("Altın İşlemleri");
        btnAltin.addActionListener(e -> mainFrame.altinIslemleriMenuGoster());

        menuPanel.add(btnDoviz);
        menuPanel.add(btnAltin);

        add(menuPanel, BorderLayout.CENTER);

        JButton btnGeri = new JButton("Geri");
        btnGeri.addActionListener(e -> mainFrame.islemlerEkraniGoster());

        JPanel altPanel = new JPanel();
        altPanel.add(btnGeri);
        add(altPanel, BorderLayout.SOUTH);

        btnDoviz.addActionListener(e -> {});
        btnAltin.addActionListener(e -> {});
    }
}
