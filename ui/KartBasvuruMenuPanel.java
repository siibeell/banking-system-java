package ui;

import javax.swing.*;
import java.awt.*;

public class KartBasvuruMenuPanel extends JPanel {

    private MainFrame mainFrame;

    public KartBasvuruMenuPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JLabel lblBaslik = new JLabel("Kart Başvurusu", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        JPanel menu = new JPanel(new GridLayout(2,1,15,15));
        menu.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

        JButton btnBankaKarti = new JButton("Banka Kartı");
        JButton btnKrediKarti = new JButton("Kredi Kartı");

        menu.add(btnBankaKarti);
        menu.add(btnKrediKarti);

        add(menu, BorderLayout.CENTER);

        JPanel alt = new JPanel(new GridLayout(1,1));
        JButton btnGeri = new JButton("Geri");
        alt.add(btnGeri);
        add(alt, BorderLayout.SOUTH);

        // 🔁 AKIŞLAR
        btnBankaKarti.addActionListener(e ->
                mainFrame.bankaKartiBasvuruYap()
        );

        btnKrediKarti.addActionListener(e ->
                mainFrame.krediKartiBasvuruGoster()
        );

        btnGeri.addActionListener(e ->
                mainFrame.basvurularGoster()
        );
    }
}
