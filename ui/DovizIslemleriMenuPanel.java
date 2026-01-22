package ui;

import javax.swing.*;

import java.awt.*;

public class DovizIslemleriMenuPanel extends JPanel {

    private MainFrame mainFrame;


    public DovizIslemleriMenuPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JLabel lblBaslik = new JLabel("Döviz İşlemleri", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(new GridLayout(2,1,15,15));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

        JButton btnAlis  = new JButton("Döviz Alış");
        btnAlis.addActionListener(e -> mainFrame.alisSatisEkraniGoster("DOVIZ","ALIS"));
        JButton btnSatis = new JButton("Döviz Satış");
        btnSatis.addActionListener(e -> mainFrame.alisSatisEkraniGoster("DOVIZ","SATIS"));


        menuPanel.add(btnAlis);
        menuPanel.add(btnSatis);

        add(menuPanel, BorderLayout.CENTER);

        JButton btnGeri = new JButton("Geri");
        btnGeri.addActionListener(e -> mainFrame.dovizAltinMenuGoster());

        JPanel altPanel = new JPanel();
        altPanel.add(btnGeri);
        add(altPanel, BorderLayout.SOUTH);

        btnAlis.addActionListener(e ->
            mainFrame.alisSatisEkraniGoster("DOVIZ","ALIS")
        );

        btnSatis.addActionListener(e ->
                mainFrame.alisSatisEkraniGoster("DOVIZ","SATIS")
        );
    }
}
