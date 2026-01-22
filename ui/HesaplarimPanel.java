package ui;

import javax.swing.*;

import model.Hesap;
import model.Musteri;

import java.awt.*;

public class HesaplarimPanel extends JPanel {

    private MainFrame mainFrame;
    private JList<Hesap> liste;
    private DefaultListModel<Hesap> model;

    public HesaplarimPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JLabel lblBaslik = new JLabel("Hesaplarım", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        model = new DefaultListModel<>();
        liste = new JList<>(model);
        liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(liste);
        scroll.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        add(scroll, BorderLayout.CENTER);

        JButton btnGeri = new JButton("Geri");
        btnGeri.addActionListener(e -> mainFrame.dashboardGoster());

        JPanel altPanel = new JPanel();
        altPanel.add(btnGeri);
        add(altPanel, BorderLayout.SOUTH);
    }

    public void hesaplariGoster(){
        model.clear();

        Musteri aktif = mainFrame.getAktifMusteri();
        for(Hesap h : mainFrame.getBanka().getHesaplar()){
            if(h.getMusteri() == aktif){
                model.addElement(h);
            }
        }
    }
}
