package ui;

import javax.swing.*;

import model.Hesap;
import model.IhtiyacKredisi;
import model.KonutKredisi;
import model.Kredi;
import service.IslemService;

import java.awt.*;

public class KrediBasvuruFormPanel extends JPanel {

    private MainFrame mainFrame;
    private String krediTuru;
    private IslemService islemService;
    private JTextField txtTutar;
    private JTextField txtVade;

    public KrediBasvuruFormPanel(MainFrame mainFrame, IslemService islemService){
        this.mainFrame = mainFrame;
        this.islemService = islemService;
        setLayout(new BorderLayout());

        JLabel lblBaslik = new JLabel("Kredi Başvurusu", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(2,2,10,10));
        form.setBorder(BorderFactory.createEmptyBorder(40,60,40,60));

        form.add(new JLabel("Tutar (TL):"));
        txtTutar = new JTextField();
        form.add(txtTutar);

        form.add(new JLabel("Vade (Ay):"));
        txtVade = new JTextField();
        form.add(txtVade);

        add(form, BorderLayout.CENTER);

        JButton btnBasvur = new JButton("Başvur");
        JButton btnGeri = new JButton("Geri");

        JPanel alt = new JPanel();
        alt.add(btnBasvur);
        alt.add(btnGeri);
        add(alt, BorderLayout.SOUTH);

        btnGeri.addActionListener(e -> mainFrame.krediBasvuruMenuGoster());

        btnBasvur.addActionListener(e -> basvur());
    }

    public void setKrediTuru(String krediTuru){
        this.krediTuru = krediTuru;
    }

    private void basvur(){
        try{
            double tutar = Double.parseDouble(txtTutar.getText());
            int vade = Integer.parseInt(txtVade.getText());

            Kredi kredi;

            if(krediTuru.equals("KONUT")){
                kredi = new KonutKredisi(tutar, vade);
            } else {
                kredi = new IhtiyacKredisi(tutar, vade);
            }

            Hesap hesap = mainFrame.getAktifHesap();
            if(hesap == null){
                JOptionPane.showMessageDialog(this, "TL hesabı bulunamadı!");
                return;
            }

            // 🔥 SERVICE ÜZERİNDEN KREDİ
            islemService.krediCek(hesap, kredi);

            mainFrame.dashboardYenile();

            JOptionPane.showMessageDialog(
                    this,
                    krediTuru + " kredisi onaylandı.\n"
                    + "Tutar: " + tutar + " TL\n"
                    + "Vade: " + vade + " Ay\n"
                    + "Aylık Taksit: " + String.format("%.2f", kredi.aylikTaksit()) + " TL"
            );

            txtTutar.setText("");
            txtVade.setText("");

            mainFrame.dashboardGoster(mainFrame.getAktifMusteri());

        } catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Lütfen geçerli tutar ve vade giriniz.");
        }
    }
}
