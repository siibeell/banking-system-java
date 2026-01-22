package ui;

import javax.swing.*;

import java.awt.*;

public class AltinIslemleriMenuPanel extends JPanel {

    private MainFrame mainFrame;

    public AltinIslemleriMenuPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JLabel lblBaslik = new JLabel("Altın İşlemleri", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(new GridLayout(2,1,15,15));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

        JButton btnAlis  = new JButton("Altın Alış");
        JButton btnSatis = new JButton("Altın Satış");

        btnAlis.addActionListener(e ->
                mainFrame.alisSatisEkraniGoster("ALTIN","ALIS")
        );

        btnSatis.addActionListener(e ->
                mainFrame.alisSatisEkraniGoster("ALTIN","SATIS")
        );

        menuPanel.add(btnAlis);
        menuPanel.add(btnSatis);
        add(menuPanel, BorderLayout.CENTER);

        JButton btnGeri = new JButton("Geri");
        btnGeri.addActionListener(e -> mainFrame.dovizAltinMenuGoster());

        JPanel altPanel = new JPanel();
        altPanel.add(btnGeri);
        add(altPanel, BorderLayout.SOUTH);
    }
}
