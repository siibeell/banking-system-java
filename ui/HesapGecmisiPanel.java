package ui;

import javax.swing.*;

import model.Hesap;

import java.awt.*;

public class HesapGecmisiPanel extends JPanel {

    private MainFrame mainFrame;
    private JTextArea txtGecmis;

    public HesapGecmisiPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JLabel lblBaslik = new JLabel("Hesap Geçmişi", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        txtGecmis = new JTextArea();
        txtGecmis.setEditable(false);

        JScrollPane scroll = new JScrollPane(txtGecmis);
        scroll.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        add(scroll, BorderLayout.CENTER);

        JButton btnGeri = new JButton("Geri");
        btnGeri.addActionListener(e -> mainFrame.dashboardGoster());

        JPanel altPanel = new JPanel();
        altPanel.add(btnGeri);
        add(altPanel, BorderLayout.SOUTH);
    }

    public void gecmisiGoster(Hesap hesap){
        txtGecmis.setText("");

        if(hesap == null){
            txtGecmis.setText("Hesap seçilmedi.");
            return;
        }

        if(hesap.getIslemGecmisi().isEmpty()){
            txtGecmis.setText("Bu hesaba ait işlem yok.");
            return;
        }

        for(String islem : hesap.getIslemGecmisi()){
            txtGecmis.append(islem + "\n");
        }
    }
    public void yukle(){
        txtGecmis.setText("");

        Hesap h = mainFrame.getAktifHesap();
        if(h == null) return;

        for(String s : h.getIslemGecmisi()){
            txtGecmis.append(s + "\n");
        }
    }
}
