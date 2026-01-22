package ui;

import javax.swing.*;

import model.BasitOdeme;
import model.Hesap;
import model.Odeme;
import service.IslemService;

import java.awt.*;

public class SigortaEmeklilikOdemePanel extends JPanel {

    private MainFrame mainFrame;
    private IslemService islemService;
    private String tur;

    private JTextField txtTc;
    private JTextField txtTutar;

    public SigortaEmeklilikOdemePanel(MainFrame mainFrame, IslemService islemService){
        this.mainFrame = mainFrame;
        this.islemService = islemService;
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel("", SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 18));
        lbl.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lbl, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(4,1,10,10));
        form.setBorder(BorderFactory.createEmptyBorder(40,60,40,60));

        txtTc = new JTextField();
        txtTutar = new JTextField();

        form.add(new JLabel("TC Kimlik No"));
        form.add(txtTc);
        form.add(new JLabel("Tutar (TL)"));
        form.add(txtTutar);

        add(form, BorderLayout.CENTER);

        JPanel alt = new JPanel(new GridLayout(1,2,10,10));
        alt.setBorder(BorderFactory.createEmptyBorder(10,60,20,60));

        JButton btnOde = new JButton("Öde");
        JButton btnGeri = new JButton("Geri");

        alt.add(btnOde);
        alt.add(btnGeri);
        add(alt, BorderLayout.SOUTH);

        btnOde.addActionListener(e -> odemeYap());
        btnGeri.addActionListener(e -> mainFrame.sigortaEmeklilikMenuGoster());
    }

    public void setTur(String tur){
        this.tur = tur;
        txtTc.setText("");
        txtTutar.setText("");
    }

    private void odemeYap(){
        try{
            String tc = txtTc.getText().trim();
            double tutar = Double.parseDouble(txtTutar.getText().trim());

            if(tc.length() != 11 || tutar <= 0)
                throw new Exception();

            Hesap tlHesap = mainFrame.getAktifHesap();

            if(tlHesap == null){
                JOptionPane.showMessageDialog(this,"Hesap bulunamadı!");
                return;
            }

            String odemeTipi =
                    tur.equals("SIGORTA")
                    ? "Sigorta Ödemesi"
                    : "Emeklilik (BES) Ödemesi";

            // 🔥 SERVICE ÜZERİNDEN ÖDEME
            Odeme odeme = new BasitOdeme(odemeTipi + " | TC: " + tc, tutar);
            islemService.odemeYap(tlHesap, odeme);

            JOptionPane.showMessageDialog(this,"Ödeme başarılı!");
            mainFrame.dashboardYenile();
            mainFrame.dashboardGoster();

        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Geçersiz bilgi!");
        }
    }
}
