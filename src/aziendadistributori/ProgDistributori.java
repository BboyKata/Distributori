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
        if (azienda.getNumeroDistributori() == 0) {
            System.out.println(ConsoleColors.RED_BACKGROUND + "Non ci sono distributori installati da poter utilizzare!");
            return 0;
        } else {
            if (firstJoin) {
                System.out.println(ConsoleColors.ANSI_CYAN_BACKGROUND + "Benvenuto nel gestionale lato cliente;\nEcco lo stato attuale:\n" + ConsoleColors.ANSI_GREEN_BACKGROUND);
                azienda.stampaInfo(false);
                System.out.println(ConsoleColors.ANSI_RESET);
                firstJoin = false;
            }
            System.out.println("\nOperazioni disponibili:\n0) Cambia modalità o esci;\n1) Visualizza distributori installati;\n2) Utilizza distributore;");
            int c = Integer.parseInt(myObj.nextLine());
            clearConsole();
            switch (c) {
                case 0:
                    firstJoin = true;
                    break;
                case 1:
                    azienda.stampaInfo(false);
                    break;
                case 2:
                    System.out.println("Inserisci numero distributore da utilizzare(0.." + azienda.getNumeroDistributori() + "): ");
                    int p = Integer.parseInt(myObj.nextLine());
                    if (p >= 0 && p < azienda.getNumeroDistributori()) {
                        Distributore d = azienda.getDistributore(p);
                        if (d instanceof DistributoreCaldo) {
                            System.out.println("Distributore di prodotti caldi. Prodotti disponibili: " + d.prodottiDisponibili());
                        } else {
                            System.out.println("Distributore di prodotti freddi. Prodotti disponibili: " + d.prodottiDisponibili());
                        }
                        d.mostraProdotti();

                    } else {
                        System.out.println("Errore di inserimento.");
                    }
                    break;
            }
            return c;
        }
    }

    public static int aziendaMode() {
        if (firstJoin) {
            System.out.println(ConsoleColors.ANSI_RED_BACKGROUND + "Benvenuto nel gestionale lato proprietario;\nEcco lo stato attuale:\n" + ConsoleColors.ANSI_GREEN_BACKGROUND);
            azienda.stampaInfo(true);
            System.out.println(ConsoleColors.ANSI_RESET);
            firstJoin = false;
        }
        System.out.println("\nOperazioni disponibili:\n0) Cambia modalità o esci;\n1) Installa distributore di prodotti caldi;\n2) Installa distributore di prodotti freddi;\n3) Rifornisci distributore;\n4) Visualizza informazioni generali;\n5) Visualizza informazioni distributore singolo;\n6) Visualizza profitto azienda attuale;\n7) Visualizza solo distributori di prodotti caldi;\n8) Visualizza solo distributori di prodotti freddi;\n9) Rimuovi prodotto/disponibilità prodotto;\nScegli operazione: ");
        int c = Integer.parseInt(myObj.nextLine());
        clearConsole();
        switch (c) {
            case 0:
                firstJoin = true;
                break;
            case 1:
                DistributoreCaldo dc = new DistributoreCaldo();
                azienda.installaDistributore(dc);
                System.out.println(ConsoleColors.RED_BOLD + "Distributore di prodotti caldi installato." + ConsoleColors.ANSI_RESET + ConsoleColors.GREEN_BACKGROUND + "\nDistributori installati: " + azienda.getNumeroDistributori() + ConsoleColors.ANSI_RESET);
                break;
            case 2:
                DistributoreFreddo df = new DistributoreFreddo();
                azienda.installaDistributore(df);
                System.out.println(ConsoleColors.CYAN_BOLD + "Distributore di prodotti freddi installato." + ConsoleColors.ANSI_RESET + ConsoleColors.GREEN_BACKGROUND + "\nDistributori installati: " + azienda.getNumeroDistributori() + ConsoleColors.ANSI_RESET);
                break;
            case 3:
                System.out.println("Inserisci numero distributore da rifornire(0.." + azienda.getNumeroDistributori() + "): ");
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
                System.out.println("Inserisci numero distributore da visualizzare(0.." + azienda.getNumeroDistributori() + "): ");
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
            case 9:
                System.out.println("Inserisci numero distributore su cui procedere (0.." + azienda.getNumeroDistributori() + "): ");
                p = Integer.parseInt(myObj.nextLine());
                if (p >= 0 && p < azienda.getNumeroDistributori()) {
                    Distributore d = azienda.getDistributore(p);
                    System.out.println("Prodotti disponibili: ");
                    d.mostraProdotti();
                    System.out.println("Inserisci indice prodotto da rimuovere/disabilitare: ");
                    int r = Integer.parseInt(myObj.nextLine());
                } else {
                    System.out.println("Errore di inserimento.");
                }
                break;

            default:
                System.out.println("Errore di inserimento, operazione annullata.");
        }
        return c;
    }

    public static void main(String[] args) throws IOException {
        clearConsole();
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
            if (m != 0) {
                System.out.println("Vuoi accedere al sistema come cliente o come proprietario?\n0) Esci;\n1) Cliente;\n2) Proprietario;\nInserisci una scelta: ");
                m = Integer.parseInt(myObj.nextLine());
                clearConsole();
            }

        } while (m != 0);
    }

}
