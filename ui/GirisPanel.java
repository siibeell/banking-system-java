package ui;

import javax.swing.*;

import model.Banka;
import model.Musteri;
import service.AuthService;

import java.awt.*;

public class GirisPanel extends JPanel {

    private MainFrame mainFrame;
    private Banka banka;

    private JTextField txtTc;
    private JPasswordField txtSifre;
    private JLabel lblHata;
    private AuthService authService;

    public GirisPanel(MainFrame mainFrame, AuthService authService){
        this.mainFrame = mainFrame;
        this.authService = authService;

        setLayout(new BorderLayout());

        // Başlık
        JLabel lblBaslik = new JLabel("Giriş Yap", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        add(lblBaslik, BorderLayout.NORTH);

        // Form alanı
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        txtTc = new JTextField();
        txtSifre = new JPasswordField();

        formPanel.add(new JLabel("TC Kimlik No"));
        formPanel.add(txtTc);
        formPanel.add(new JLabel("Şifre"));
        formPanel.add(txtSifre);

        add(formPanel, BorderLayout.CENTER);

        // Alt alan (butonlar + hata)
        JPanel altPanel = new JPanel(new BorderLayout());

        lblHata = new JLabel("", SwingConstants.CENTER);
        lblHata.setForeground(Color.RED);

        JPanel butonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        butonPanel.setBorder(BorderFactory.createEmptyBorder(10, 60, 20, 60));

        JButton btnGiris = new JButton("Giriş");
        JButton btnGeri = new JButton("Geri");

        butonPanel.add(btnGiris);
        butonPanel.add(btnGeri);

        altPanel.add(lblHata, BorderLayout.NORTH);
        altPanel.add(butonPanel, BorderLayout.CENTER);

        add(altPanel, BorderLayout.SOUTH);

        //Giriş butonu
        btnGiris.addActionListener(e -> girisYap());

        //Geri butonu
        btnGeri.addActionListener(e -> mainFrame.anaEkranaDon());
    }

    private void girisYap(){
        String tc = txtTc.getText().trim();
        String sifre = new String(txtSifre.getPassword());

        if(tc.isEmpty() || sifre.isEmpty()){
            lblHata.setText("TC ve şifre boş olamaz!");
            return;
        }

        Musteri m = authService.girisYap(tc, sifre);

        if(m == null){
            lblHata.setText("TC veya şifre hatalı!");
        }else{
            // alanları temizle
            txtTc.setText("");
            txtSifre.setText("");
            lblHata.setText("");

            // Dashboard'a geç
            mainFrame.dashboardGoster(m);
        }
    }
}
