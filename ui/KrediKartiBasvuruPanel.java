package ui;

import javax.swing.*;
import java.awt.*;

import model.KrediKarti;
import service.KartService;

public class KrediKartiBasvuruPanel extends JPanel {

    private MainFrame mainFrame;
    private KartService kartService;

    private JTextField txtLimit;

    public KrediKartiBasvuruPanel(MainFrame mainFrame, KartService kartService){
        this.mainFrame = mainFrame;
        this.kartService = kartService;

        setLayout(new BorderLayout());

        JLabel lblBaslik = new JLabel("Kredi Kartı Başvurusu", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(2,2,10,10));
        form.setBorder(BorderFactory.createEmptyBorder(40,60,40,60));

        form.add(new JLabel("Kart Limiti (TL):"));
        txtLimit = new JTextField();
        form.add(txtLimit);

        add(form, BorderLayout.CENTER);

        JPanel alt = new JPanel(new GridLayout(1,2,10,10));
        alt.setBorder(BorderFactory.createEmptyBorder(10,60,20,60));

        JButton btnBasvur = new JButton("Başvur");
        JButton btnGeri = new JButton("Geri");

        alt.add(btnBasvur);
        alt.add(btnGeri);
        add(alt, BorderLayout.SOUTH);

        btnGeri.addActionListener(e -> mainFrame.kartlarimGoster());
        btnBasvur.addActionListener(e -> basvur());
    }

    private void basvur(){
        try{
            double limit = Double.parseDouble(txtLimit.getText().trim());
            if(limit <= 0) throw new Exception();

            KrediKarti kk =
                kartService.krediKartiBasvur(
                    mainFrame.getAktifMusteri(),
                    limit
                );

            if(kk == null){
                JOptionPane.showMessageDialog(
                        this,
                        "Zaten kredi kartınız bulunmaktadır!"
                );
                return;
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Kredi kartı onaylandı!\nLimit: " + limit + " TL"
            );

            txtLimit.setText("");
            mainFrame.kartlarimGoster();

        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Geçerli bir limit giriniz.");
        }
    }
}
