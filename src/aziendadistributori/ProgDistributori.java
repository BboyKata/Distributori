/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aziendadistributori;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Matteo
 */
public class ProgDistributori {

    private static AziendaDistributori azienda = new AziendaDistributori();
    private static Scanner myObj = new Scanner(System.in);
    private static boolean firstJoin = true;

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {
        }
    }

    public static int userMode() {
        return 1;
    }

    public static int aziendaMode() {
        if (firstJoin) {
            System.out.println("Benvenuto nel gestionale lato proprietario; Ecco lo stato attuale: ");
            azienda.stampaInfo(true);
            firstJoin = false;
        }
        System.out.println("Operazioni disponibili:\n0) Cambia modalità o esci;\n1) Installa distributore di prodotti caldi;\n2) Installa distributore di prodotti freddi;\n3) Rifornisci distributore;\n4) Visualizza informazioni generali;\n5) Visualizza informazioni distributore singolo;\n6) Visualizza profitto azienda attuale;\n7) Visualizza solo distributori di prodotti caldi;\n8) Visualizza solo distributori di prodotti freddi;\nScegli operazione: ");
        int c = Integer.parseInt(myObj.nextLine());
        Scanner myObj = new Scanner(System.in);
        switch (c) {
            case 0:
                firstJoin = true;
                break;
            case 1:
                DistributoreCaldo dc = new DistributoreCaldo();
                azienda.installaDistributore(dc);
                System.out.println("Distributore di prodotti caldi installato.\nDistributori installati: " + azienda.getNumeroDistributori());
                break;
            case 2:
                DistributoreFreddo df = new DistributoreFreddo();
                azienda.installaDistributore(df);
                System.out.println("Distributore di prodotti freddi installato.\nDistributori installati: " + azienda.getNumeroDistributori());
                break;
            case 3:
                System.out.println("Inserisci numero distributore da rifornire: ");
                int p = Integer.parseInt(myObj.nextLine());
                if (p >= 0 && p < azienda.getNumeroDistributori()) {
                    azienda.rifornisciDistributore(p);
                } else {
                    System.out.println("Errore di inserimento.");
                }
                break;
            case 4:
                azienda.stampaInfo(true);
                break;
            case 5:
                System.out.println("Inserisci numero distributore da visualizzare: ");
                p = Integer.parseInt(myObj.nextLine());
                if (p >= 0 && p < azienda.getNumeroDistributori()) {
                    Distributore d = azienda.getDistributore(p);
                    if (d instanceof DistributoreCaldo) {
                        System.out.println("Distributore di prodotti caldi. Prodotti disponibili: " + d.prodottiDisponibili() + " Profitto: " + d.getProfitto() + "€");
                        d.mostraProdotti();
                    } else {
                        System.out.println("Distributore di prodotti freddi. Prodotti disponibili: " + d.prodottiDisponibili() + " Profitto: " + d.getProfitto() + "€");
                        d.mostraProdotti();
                    }
                } else {
                    System.out.println("Errore di inserimento.");
                }
                break;
            case 6:
                azienda.getProfittiTotali();
                break;
            case 7:
                azienda.stampaInfoTipo(true);
                break;
            case 8:
                azienda.stampaInfoTipo(false);
                break;
            default:
                System.out.println("Errore di inserimento, operazione annullata.");
        }
        return c;
    }

    public static void main(String[] args) throws IOException {
        int m;
        while (true) {
            System.out.println("Benvenuto nel gestionale di distributori\nVuoi accedere al sistema come cliente o come proprietario?\n0) Esci;\n1) Cliente;\n2) Proprietario;\nInserisci una scelta: ");
            try {
                m = Integer.parseInt(myObj.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("|ATTENZIONE!| Errore di inserimento.");
            }
        }
        clearConsole();
        do {
            switch (m) {
                case 0:
                    System.out.println("Arrivederci.");
                    break;
                case 1:
                    int r = 0;
                    do {
                        r = userMode();
                    } while (r != 0);
                    break;
                case 2:
                    r = 0;
                    do {
                        r = aziendaMode();
                    } while (r != 0);
                    break;
                default:
                    System.out.println("Errore di inserimento, reinserire la scelta.");
                    break;
            }
            System.out.println("Vuoi accedere al sistema come cliente o come proprietario?\n0) Esci;\n1) Cliente;\n2) Proprietario;\nInserisci una scelta: ");
            m = Integer.parseInt(myObj.nextLine());
            clearConsole();
        } while (m != 0);
    }

}
