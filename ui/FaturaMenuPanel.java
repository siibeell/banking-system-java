package ui;

import javax.swing.*;

import java.awt.*;

public class FaturaMenuPanel extends JPanel {

    private MainFrame mainFrame;

    public FaturaMenuPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JLabel lblBaslik = new JLabel("Fatura Ödeme", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(new GridLayout(4,1,15,15));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(40,60,40,60));

        JButton btnTelefon   = new JButton("Telefon");
        JButton btnSu        = new JButton("Su");
        JButton btnElektrik  = new JButton("Elektrik");
        JButton btnDogalgaz  = new JButton("Doğalgaz");

        menuPanel.add(btnTelefon);
        menuPanel.add(btnSu);
        menuPanel.add(btnElektrik);
        menuPanel.add(btnDogalgaz);

        add(menuPanel, BorderLayout.CENTER);

        JButton btnGeri = new JButton("Geri");
        btnGeri.addActionListener(e -> mainFrame.odemelerEkraniGoster());

        JPanel altPanel = new JPanel();
        altPanel.add(btnGeri);

        add(altPanel, BorderLayout.SOUTH);
        
        btnTelefon.addActionListener(e -> {});
        btnSu.addActionListener(e -> {});
        btnElektrik.addActionListener(e -> {});
        btnDogalgaz.addActionListener(e -> {});

        btnTelefon.addActionListener(e -> mainFrame.faturaOdemeEkraniGoster("Telefon"));
        btnSu.addActionListener(e -> mainFrame.faturaOdemeEkraniGoster("Su"));
        btnElektrik.addActionListener(e -> mainFrame.faturaOdemeEkraniGoster("Elektrik"));
        btnDogalgaz.addActionListener(e -> mainFrame.faturaOdemeEkraniGoster("Doğalgaz"));
    }
}
