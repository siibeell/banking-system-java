package ui;

import javax.swing.*;

import model.Banka;
import model.Hesap;
import model.Musteri;
import model.VadeliHesap;
import model.VadesizHesap;
import model.YatirimHesabi;
import service.AuthService;
import service.IslemService;
import service.KartService;
import ui.KrediKartiBasvuruPanel;

import java.awt.*;

public class MainFrame extends JFrame {

    private Banka banka;
    private Musteri aktifMusteri;


    // Ekran geçişleri için
    private CardLayout cardLayout;
    private JPanel anaPanel;

    // Paneller
    private AnaPanel anaPanelEkrani;
    private GirisPanel girisPanel;
    private KayitPanel kayitPanel;
    private DashboardPanel dashboardPanel;
    private IslemlerPanel islemlerPanel;
    private ParaTransferPanel paraTransferPanel;
    private OdemelerPanel odemelerPanel;    
    private FaturaMenuPanel faturaMenuPanel;
    private FaturaOdemePanel faturaOdemePanel;
    private BorcOdemePanel borcOdemePanel;
    private DovizAltinMenuPanel dovizAltinMenuPanel;   
    private DovizIslemleriMenuPanel dovizIslemleriMenuPanel; 
    private AltinIslemleriMenuPanel altinIslemleriMenuPanel;
    private AlisSatisPanel alisSatisPanel;
    private HesapGecmisiPanel hesapGecmisiPanel;
    private HesaplarimPanel hesaplarimPanel;
    private  BasvurularPanel basvurularPanel;
    private KartBasvuruMenuPanel kartBasvuruMenuPanel;
    private KrediBasvuruMenuPanel krediBasvuruMenuPanel;
    private KrediBasvuruFormPanel krediBasvuruFormPanel;
    private HesapBasvuruMenuPanel hesapBasvuruMenuPanel;
    private SigortaEmeklilikMenuPanel sigortaEmeklilikMenuPanel;
    private SigortaEmeklilikOdemePanel sigortaEmeklilikOdemePanel;
    private AuthService authService;
    private IslemService islemService;
    private KartService kartService;
    private KartlarimPanel kartlarimPanel;
    private KrediKartiBasvuruPanel krediKartiBasvuruPanel;

    public MainFrame(Banka banka){
        this.banka = banka;
        this.authService = new AuthService(banka);
        this.islemService = new IslemService(banka);

        setTitle("Mobil Bankacılık");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        anaPanel = new JPanel(cardLayout);

        // Panelleri oluştur
        anaPanelEkrani = new AnaPanel(this);
        girisPanel = new GirisPanel(this, authService);
        kayitPanel = new KayitPanel(this, banka);
        dashboardPanel = new DashboardPanel(this, banka);
        islemlerPanel = new IslemlerPanel(this);
        paraTransferPanel = new ParaTransferPanel(this, islemService);
        odemelerPanel = new OdemelerPanel(this);
        faturaMenuPanel = new FaturaMenuPanel(this);
        faturaOdemePanel = new FaturaOdemePanel(this, islemService);
        borcOdemePanel = new BorcOdemePanel(this, islemService);
        dovizAltinMenuPanel = new DovizAltinMenuPanel(this);
        dovizIslemleriMenuPanel = new DovizIslemleriMenuPanel(this);
        altinIslemleriMenuPanel = new AltinIslemleriMenuPanel(this);
        alisSatisPanel = new AlisSatisPanel(this);
        hesapGecmisiPanel = new HesapGecmisiPanel(this);
        hesaplarimPanel = new HesaplarimPanel(this);
        basvurularPanel = new BasvurularPanel(this);
        kartBasvuruMenuPanel = new KartBasvuruMenuPanel(this);
        krediBasvuruMenuPanel = new KrediBasvuruMenuPanel(this);
        krediBasvuruFormPanel = new KrediBasvuruFormPanel(this, islemService);
        hesapBasvuruMenuPanel = new HesapBasvuruMenuPanel(this);
        sigortaEmeklilikMenuPanel = new SigortaEmeklilikMenuPanel(this);
        sigortaEmeklilikOdemePanel = new SigortaEmeklilikOdemePanel(this, islemService);
        kartService = new KartService(banka);
        kartlarimPanel = new KartlarimPanel(this, kartService);
        krediKartiBasvuruPanel = new KrediKartiBasvuruPanel(this, kartService);


        // Panele ekle
        anaPanel.add(anaPanelEkrani, "ANA");
        anaPanel.add(girisPanel, "GIRIS");
        anaPanel.add(kayitPanel, "KAYIT");
        anaPanel.add(dashboardPanel, "DASHBOARD");
        anaPanel.add(islemlerPanel, "ISLEMLER");
        anaPanel.add(paraTransferPanel, "TRANSFER");
        anaPanel.add(odemelerPanel, "ODEMELER");    
        anaPanel.add(faturaMenuPanel, "FATURA_MENU");
        anaPanel.add(faturaOdemePanel, "FATURA_ODE");
        anaPanel.add(borcOdemePanel, "BORC_ODE");
        anaPanel.add(dovizAltinMenuPanel, "DOVIZ_ALTIN_MENU");
        anaPanel.add(dovizIslemleriMenuPanel, "DOVIZ_MENU");
        anaPanel.add(altinIslemleriMenuPanel, "ALTIN_MENU");
        anaPanel.add(alisSatisPanel, "ALIS_SATIS");
        anaPanel.add(hesapGecmisiPanel, "HESAP_GECMISI");
        anaPanel.add(hesaplarimPanel, "HESAPLARIM");
        anaPanel.add(basvurularPanel, "BASVURULAR");
        anaPanel.add(kartBasvuruMenuPanel, "KART_BASVURU_MENU");
        anaPanel.add(krediBasvuruMenuPanel, "KREDI_BASVURU_MENU");
        anaPanel.add(krediBasvuruFormPanel, "KREDI_FORM");
        anaPanel.add(hesapBasvuruMenuPanel, "HESAP_BASVURU_MENU");
        anaPanel.add(sigortaEmeklilikMenuPanel, "SIGORTA_MENU");
        anaPanel.add(sigortaEmeklilikOdemePanel, "SIGORTA_ODEME");
        anaPanel.add(kartlarimPanel, "KARTLARIM");
        anaPanel.add(krediKartiBasvuruPanel, "KREDI_KARTI_BASVURU");

        add(anaPanel);

        // İlk ekran
        cardLayout.show(anaPanel, "ANA");

        setVisible(true);
    }

    //EKRAN DEĞİŞTİRME METOTLARI

    public void girisEkraniGoster(){
        cardLayout.show(anaPanel, "GIRIS");
    }

    public void kayitEkraniGoster(){
        cardLayout.show(anaPanel, "KAYIT");
    }

    public void dashboardGoster(Musteri musteri){
        this.aktifMusteri = musteri;

        boolean tlHesapVar = false;
        for(Hesap h : banka.getHesaplar()){
            if(h.getMusteri() == musteri && !(h instanceof YatirimHesabi)){
                tlHesapVar = true;
                break;
            }
        }

        if(!tlHesapVar){
            int no = banka.getHesaplar().size() + 1001;
            banka.hesapEkle(new VadesizHesap(no, musteri, 50000));
        }

        dashboardPanel.setAktifMusteri(musteri);
        cardLayout.show(anaPanel, "DASHBOARD");
    }

    public void anaEkranaDon(){
        cardLayout.show(anaPanel, "ANA");
    }

    public void islemlerEkraniGoster(){
        cardLayout.show(anaPanel, "ISLEMLER");
    }

    public void dashboardGoster(){
            cardLayout.show(anaPanel, "DASHBOARD");
    }

    public void paraTransferEkraniGoster(){
        cardLayout.show(anaPanel, "TRANSFER");
    }

    public void odemelerEkraniGoster(){
        cardLayout.show(anaPanel, "ODEMELER");
    }

    public void faturaMenuGoster(){
        cardLayout.show(anaPanel, "FATURA_MENU");
    }

    public void faturaOdemeEkraniGoster(String tur){
        faturaOdemePanel.setFaturaTuru(tur);
        cardLayout.show(anaPanel, "FATURA_ODE");
    }

    public void borcOdemeEkraniGoster(){
        cardLayout.show(anaPanel, "BORC_ODE");
    }

    public void dovizAltinMenuGoster(){
        cardLayout.show(anaPanel, "DOVIZ_ALTIN_MENU");
    }

    public void dovizIslemleriMenuGoster(){
        cardLayout.show(anaPanel, "DOVIZ_MENU");
    }

    public void altinIslemleriMenuGoster(){
        cardLayout.show(anaPanel, "ALTIN_MENU");
    }

    public Musteri getAktifMusteri(){
        return aktifMusteri;
    }

    public void alisSatisEkraniGoster(String varlikTuru, String islemTuru){
        alisSatisPanel.setIslem(varlikTuru, islemTuru);
        cardLayout.show(anaPanel, "ALIS_SATIS");
    }

    public void hesapGecmisiGoster(){
        hesapGecmisiPanel.yukle();
        cardLayout.show(anaPanel, "HESAP_GECMISI");
    }

    public void hesaplarimGoster(){
        hesaplarimPanel.hesaplariGoster();
        cardLayout.show(anaPanel, "HESAPLARIM");
    }

    public Banka getBanka(){
        return banka;
    }

    public void kartBasvuruMenuGoster(){
        cardLayout.show(anaPanel, "KART_BASVURU_MENU");
    }

    public void krediBasvuruMenuGoster(){
        cardLayout.show(anaPanel, "KREDI_BASVURU_MENU");
    }

    public void hesapBasvuruMenuGoster(){
        cardLayout.show(anaPanel, "HESAP_BASVURU_MENU");
    }

    public void krediFormGoster(String tur){
        krediBasvuruFormPanel.setKrediTuru(tur);
        cardLayout.show(anaPanel, "KREDI_FORM");
    }

    public void vadesizHesapAc(){
        int no = banka.getHesaplar().size() + 1001;
        banka.hesapEkle(new VadesizHesap(no, aktifMusteri, 0));
        JOptionPane.showMessageDialog(this, "Vadesiz hesap açıldı.");
    }

    public void vadeliHesapAc(){
        int no = banka.getHesaplar().size() + 1001;
        banka.hesapEkle(new VadeliHesap(no, aktifMusteri, 0, 0.02));
        JOptionPane.showMessageDialog(this, "Vadeli hesap açıldı.");
    }

    public void yatirimHesabiAc(){
        int no = banka.getHesaplar().size() + 1001;
        banka.hesapEkle(new YatirimHesabi(no, aktifMusteri, 0, 0.03, 0.01));
        JOptionPane.showMessageDialog(this, "Yatırım hesabı açıldı.");
    }

    public Hesap getAktifHesap(){
        for(Hesap h : banka.getHesaplar()){
            if(h.getMusteri() == aktifMusteri && !(h instanceof YatirimHesabi)){
                return h;
            }
        }
        return null;
    }

    public void dashboardYenile(){
        Hesap h = getAktifHesap();
        if(h != null){
            dashboardPanel.ekraniGuncelle(h);
        }
    }

    public void sigortaEmeklilikMenuGoster(){
        cardLayout.show(anaPanel, "SIGORTA_MENU");
    }

    public void sigortaEmeklilikOdemeGoster(String tur){
        sigortaEmeklilikOdemePanel.setTur(tur);
        cardLayout.show(anaPanel, "SIGORTA_ODEME");
    }

    public IslemService getIslemService(){
        return islemService;
    }

    public void kartlarimGoster(){
        kartService.bankaKartiOlustur(aktifMusteri);
        kartlarimPanel.yukle();
        cardLayout.show(anaPanel, "KARTLARIM");
    }

    public void krediKartiBasvuruGoster(){
        cardLayout.show(anaPanel, "KREDI_KARTI_BASVURU");
    }

    public void basvurularGoster(){
        cardLayout.show(anaPanel, "BASVURULAR");
    }

    public void bankaKartiBasvuruYap(){
        kartService.bankaKartiOlustur(getAktifMusteri());
        JOptionPane.showMessageDialog(this, "Banka kartı oluşturuldu.");
        kartlarimGoster();
    }

}
