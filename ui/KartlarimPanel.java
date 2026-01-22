package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import model.Kart;
import model.BankaKarti;
import model.KrediKarti;
import service.KartService;

public class KartlarimPanel extends JPanel {

    private MainFrame mainFrame;
    private KartService kartService;

    private JPanel kartListePanel;

    public KartlarimPanel(MainFrame mainFrame, KartService kartService){
        this.mainFrame = mainFrame;
        this.kartService = kartService;

        setLayout(new BorderLayout());

        JLabel lblBaslik = new JLabel("Kartlarım", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        kartListePanel = new JPanel();
        kartListePanel.setLayout(new BoxLayout(kartListePanel, BoxLayout.Y_AXIS));
        kartListePanel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

        add(kartListePanel, BorderLayout.CENTER);

        JButton btnGeri = new JButton("Geri");
        btnGeri.addActionListener(e -> mainFrame.dashboardGoster());
        add(btnGeri, BorderLayout.SOUTH);
    }

    public void yukle(){
        kartListePanel.removeAll();

        List<Kart> kartlar = kartService.kartlariGetir(
                mainFrame.getAktifMusteri()
        );

        for(Kart k : kartlar){
            JPanel kartPanel = new JPanel(new GridLayout(0,1));
            kartPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY),
                    BorderFactory.createEmptyBorder(10,10,10,10)
            ));

            kartPanel.add(new JLabel("Kart Türü: " + k.getKartTuru()));
            kartPanel.add(new JLabel("Kart No: " + k.getKartNo()));

            if(k instanceof KrediKarti){
                KrediKarti kk = (KrediKarti) k;
                kartPanel.add(new JLabel("Limit: " + kk.getLimit() + " TL"));
                kartPanel.add(new JLabel("Borç: " + kk.getBorc() + " TL"));
            }

            kartListePanel.add(kartPanel);
            kartListePanel.add(Box.createVerticalStrut(15));
        }

        kartListePanel.revalidate();
        kartListePanel.repaint();
    }
}
