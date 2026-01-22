package ui;

import javax.swing.*;
import java.awt.*;

public class KrediBasvuruMenuPanel extends JPanel {

    private MainFrame mainFrame;

    public KrediBasvuruMenuPanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JLabel lblBaslik = new JLabel("Kredi Başvurusu", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        JPanel menu = new JPanel(new GridLayout(2,1,15,15));
        menu.setBorder(BorderFactory.createEmptyBorder(60,60,60,60));

        JButton btnKonut = new JButton("Konut Kredisi");
        JButton btnIhtiyac = new JButton("İhtiyaç Kredisi");

        menu.add(btnKonut);
        menu.add(btnIhtiyac);

        add(menu, BorderLayout.CENTER);

        // 🔽 ALT PANEL + GERİ
        JPanel alt = new JPanel();
        JButton btnGeri = new JButton("Geri");
        alt.add(btnGeri);
        add(alt, BorderLayout.SOUTH);

        btnKonut.addActionListener(e ->
                mainFrame.krediFormGoster("KONUT")
        );

        btnIhtiyac.addActionListener(e ->
                mainFrame.krediFormGoster("IHTIYAC")
        );

        btnGeri.addActionListener(e ->
                mainFrame.basvurularGoster()
        );
    }
}
