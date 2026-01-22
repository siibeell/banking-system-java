package ui;

import javax.swing.*;

import java.awt.*;

public class SigortaEmeklilikMenuPanel extends JPanel {

    private MainFrame mainFrame;

    public SigortaEmeklilikMenuPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel("Sigorta / Emeklilik Ödeme", SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 18));
        lbl.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lbl, BorderLayout.NORTH);

        JPanel orta = new JPanel(new GridLayout(2,1,15,15));
        orta.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

        JButton btnSigorta = new JButton("Sigorta Ödeme");
        JButton btnEmeklilik = new JButton("Emeklilik (BES) Ödeme");

        btnSigorta.addActionListener(e ->
                mainFrame.sigortaEmeklilikOdemeGoster("SIGORTA")
        );

        btnEmeklilik.addActionListener(e ->
                mainFrame.sigortaEmeklilikOdemeGoster("EMEKLILIK")
        );

        orta.add(btnSigorta);
        orta.add(btnEmeklilik);

        add(orta, BorderLayout.CENTER);

        JButton btnGeri = new JButton("Geri");
        btnGeri.addActionListener(e -> mainFrame.odemelerEkraniGoster());
        add(btnGeri, BorderLayout.SOUTH);
    }
}
