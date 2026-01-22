package ui;

import javax.swing.*;

import model.Hesap;
import service.IslemService;

import java.awt.*;

public class ParaTransferPanel extends JPanel {

    private MainFrame mainFrame;
    private IslemService islemService;

    private JTextField txtAliciHesap;
    private JTextField txtTutar;

    public ParaTransferPanel(MainFrame mainFrame, IslemService islemService){
        this.mainFrame = mainFrame;
        this.islemService = islemService;

        setLayout(new BorderLayout());

        JLabel lblBaslik = new JLabel("Para Transferleri", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 20));
        lblBaslik.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        add(lblBaslik, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(4,1,10,10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20,60,20,60));

        txtAliciHesap = new JTextField();
        txtTutar = new JTextField();

        formPanel.add(new JLabel("Alıcı Hesap No"));
        formPanel.add(txtAliciHesap);
        formPanel.add(new JLabel("Tutar"));
        formPanel.add(txtTutar);

        add(formPanel, BorderLayout.CENTER);

        JPanel altPanel = new JPanel(new GridLayout(1,2,10,10));
        altPanel.setBorder(BorderFactory.createEmptyBorder(10,60,20,60));

        JButton btnGonder = new JButton("Gönder");
        JButton btnGeri = new JButton("Geri");

        altPanel.add(btnGonder);
        altPanel.add(btnGeri);

        add(altPanel, BorderLayout.SOUTH);

        // OLAYLAR
        btnGeri.addActionListener(e -> mainFrame.islemlerEkraniGoster());
        btnGonder.addActionListener(e -> havaleYap());
    }

    private void havaleYap(){
        try{
            int aliciNo = Integer.parseInt(txtAliciHesap.getText().trim());
            double tutar = Double.parseDouble(txtTutar.getText().trim());

            Hesap gonderen = mainFrame.getAktifHesap();
            if(gonderen == null){
                JOptionPane.showMessageDialog(this,"Gönderen hesap bulunamadı!");
                return;
            }

            Hesap alici = mainFrame.getBanka().hesapBul(aliciNo);
            if(alici == null){
                JOptionPane.showMessageDialog(this,"Alıcı hesap bulunamadı!");
                return;
            }

            islemService.havaleYap(gonderen, alici, tutar, true);
            mainFrame.dashboardYenile();

            JOptionPane.showMessageDialog(this,"Havale başarılı!");
            txtAliciHesap.setText("");
            txtTutar.setText("");

        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Geçersiz giriş!");
        }
    }
}
