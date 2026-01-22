package service;

import model.Banka;
import model.Musteri;

public class AuthService {

    private Banka banka;

    public AuthService(Banka banka){
        this.banka = banka;
    }

    public Musteri girisYap(String tc, String sifre){
        for(Musteri m : banka.getMusteriler()){
            if(m.getTcNo().equals(tc) && m.getSifre().equals(sifre)){
                return m;
            }
        }
        return null;
    }
}
