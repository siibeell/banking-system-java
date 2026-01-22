package ui;

import model.Banka;

public class Main {

    public static void main(String[] args) {

        Banka banka = new Banka("Sibel BANK");
        new MainFrame(banka);
    }
}
