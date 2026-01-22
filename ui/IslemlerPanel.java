package ui;

import javax.swing.*;

import java.awt.*;

public class IslemlerPanel extends JPanel {

    private MainFrame mainFrame;

    public IslemlerPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());



        JLabel lblBaslik = new JLabel("İşlemler", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel(new GridLayout(3,1,15,15));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(40,60,40,60));

        JButton btnTransfer = new JButton("Para Transferleri");
        JButton btnOdeme    = new JButton("Ödemeler");
        JButton btnDoviz    = new JButton("Döviz & Altın");
        btnTransfer.addActionListener(e -> mainFrame.paraTransferEkraniGoster());
        btnOdeme.addActionListener(e -> mainFrame.odemelerEkraniGoster());
        btnDoviz.addActionListener(e -> mainFrame.dovizAltinMenuGoster());

        menuPanel.add(btnTransfer);
        menuPanel.add(btnOdeme);
        menuPanel.add(btnDoviz);

        add(menuPanel, BorderLayout.CENTER);

        JButton btnGeri = new JButton("Geri");
        btnGeri.addActionListener(e -> mainFrame.dashboardGoster());

        JPanel altPanel = new JPanel();
        altPanel.add(btnGeri);

        add(altPanel, BorderLayout.SOUTH);
    }
}
