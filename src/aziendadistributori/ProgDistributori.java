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

    public static int inputIntero() {
        while (true) {
            try {
                return Integer.parseInt(myObj.nextLine());
            } catch (Exception e) {
                System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Errore di inserimento." + ConsoleColors.ANSI_RESET);
            }
        }
    }

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
            System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Non ci sono distributori installati da poter utilizzare!." + ConsoleColors.ANSI_RESET);
            return 0;
        } else {
            if (firstJoin) {
                System.out.println(ConsoleColors.ANSI_CYAN_BACKGROUND + "Benvenuto nel gestionale lato cliente;\nEcco lo stato attuale:\n" + ConsoleColors.ANSI_GREEN_BACKGROUND);
                azienda.stampaInfo(false);
                System.out.println(ConsoleColors.ANSI_RESET);
                firstJoin = false;
            }
            System.out.println(ConsoleColors.PURPLE_BOLD + "\nOperazioni disponibili:\n0) Cambia modalità o esci;\n1) Visualizza distributori installati;\n2) Utilizza distributore;\nInserisci scelta: " + ConsoleColors.ANSI_RESET);
            int c = inputIntero();
            clearConsole();
            switch (c) {
                case 0:
                    firstJoin = true;
                    break;
                case 1:
                    azienda.stampaInfo(false);
                    break;
                case 2:
                    int max = azienda.getNumeroDistributori() - 1;
                    System.out.println(ConsoleColors.PURPLE_BOLD + "Inserisci numero distributore da utilizzare(0.." + max + "): " + ConsoleColors.ANSI_RESET);
                    int p = inputIntero();
                    if (p >= 0 && p < azienda.getNumeroDistributori()) {
                        Distributore d = azienda.getDistributore(p);
                        if (d.prodottiDisponibili() > 0) {
                            if (d instanceof DistributoreCaldo) {
                                System.out.println(ConsoleColors.ANSI_ORANGE + "Distributore di prodotti caldi. Prodotti disponibili: " + d.prodottiDisponibili() + ConsoleColors.ANSI_RESET);
                            } else {
                                System.out.println(ConsoleColors.CYAN_BOLD + "Distributore di prodotti freddi. Prodotti disponibili: " + d.prodottiDisponibili() + ConsoleColors.ANSI_RESET);
                            }
                            d.mostraProdotti();
                            System.out.println(ConsoleColors.PURPLE_BOLD + "Inserisci indice prodotto da acquistare: " + ConsoleColors.ANSI_RESET);
                            int r = inputIntero() - 60;
                            if (r >= 0 && r < d.prodottiDisponibili()) {
                                while (!d.isProdottoPagato(r)) {
                                    clearConsole();
                                    System.out.println(ConsoleColors.ANSI_GREEN + "Credito: " + d.getCredito() + ConsoleColors.ANSI_RESET);
                                    System.out.println(ConsoleColors.PURPLE_BOLD + "Inserisci moneta da:\n0) 0.10 euro;\n1) 0.20 euro;\n2) 0.50 euro;\n3) 1.0 euro;\n4) 2.0 euro;\n" + ConsoleColors.ANSI_RESET);
                                    int m = inputIntero();
                                    switch (m) {
                                        case 0:
                                            d.aggiungiMoneta(0.10f);
                                            break;
                                        case 1:
                                            d.aggiungiMoneta(0.2f);
                                            break;
                                        case 2:
                                            d.aggiungiMoneta(0.5f);
                                            break;
                                        case 3:
                                            d.aggiungiMoneta(1);
                                            break;
                                        case 4:
                                            d.aggiungiMoneta(2);
                                            break;
                                    }
                                }
                                d.erogaProdotto(r);
                            } else {
                                System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Errore di inserimento." + ConsoleColors.ANSI_RESET);
                            }
                        } else {
                            System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Errore di inserimento." + ConsoleColors.ANSI_RESET);
                        }
                    } else {
                        System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Errore di inserimento." + ConsoleColors.ANSI_RESET);
                    }
                    break;
                default:
                    System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Errore di inserimento." + ConsoleColors.ANSI_RESET);
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
        System.out.println(ConsoleColors.PURPLE_BOLD + "\nOperazioni disponibili:\n0) Cambia modalità o esci;\n1) Installa distributore di prodotti caldi;\n2) Installa distributore di prodotti freddi;\n3) Rifornisci distributore;\n4) Visualizza informazioni generali;\n5) Visualizza informazioni distributore singolo;\n6) Visualizza profitto azienda attuale;\n7) Visualizza solo distributori di prodotti caldi;\n8) Visualizza solo distributori di prodotti freddi;\n9) Rimuovi prodotto/disponibilità prodotto;\nScegli operazione: " + ConsoleColors.ANSI_RESET);
        int c = inputIntero();
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
                int max = azienda.getNumeroDistributori() - 1;
                System.out.println("Inserisci numero distributore da rifornire(0.." + max + "): ");
                int p = inputIntero();
                if (p >= 0 && p < azienda.getNumeroDistributori()) {
                    azienda.rifornisciDistributore(p);
                } else {
                    System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Errore di inserimento." + ConsoleColors.ANSI_RESET);
                }
                break;
            case 4:
                azienda.stampaInfo(true);
                break;
            case 5:
                max = azienda.getNumeroDistributori() - 1;
                System.out.println(ConsoleColors.PURPLE_BOLD + "Inserisci numero distributore da visualizzare(0.." + max + "): " + ConsoleColors.ANSI_RESET);
                p = inputIntero();
                if (p >= 0 && p < azienda.getNumeroDistributori()) {
                    Distributore d = azienda.getDistributore(p);
                    if (d instanceof DistributoreCaldo) {
                        System.out.println(ConsoleColors.ANSI_ORANGE + "Distributore di prodotti caldi. Prodotti disponibili: " + d.prodottiDisponibili() + " Profitto: " + d.getProfitto() + " euro" + ConsoleColors.ANSI_RESET);
                        d.mostraProdotti();
                    } else {
                        System.out.println(ConsoleColors.ANSI_CYAN + "Distributore di prodotti freddi. Prodotti disponibili: " + d.prodottiDisponibili() + " Profitto: " + d.getProfitto() + " euro" + ConsoleColors.ANSI_RESET);
                        d.mostraProdotti();
                    }
                } else {
                    System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Errore di inserimento." + ConsoleColors.ANSI_RESET);
                }
                break;
            case 6:
                System.out.println(ConsoleColors.PURPLE_BOLD + "Profitto: " + azienda.getProfittiTotali() + ConsoleColors.ANSI_RESET + "\n");
                break;
            case 7:
                azienda.stampaInfoTipo(true);
                break;
            case 8:
                azienda.stampaInfoTipo(false);
                break;
            case 9:
                max = azienda.getNumeroDistributori() - 1;
                System.out.println(ConsoleColors.PURPLE_BOLD + "Inserisci numero distributore su cui procedere (0.." + max + "): " + ConsoleColors.ANSI_RESET);
                p = inputIntero();
                if (p >= 0 && p < azienda.getNumeroDistributori()) {
                    Distributore d = azienda.getDistributore(p);
                    System.out.println(ConsoleColors.ANSI_GREEN + "Prodotti disponibili: " + ConsoleColors.ANSI_RESET);
                    d.mostraProdotti();
                    System.out.println(ConsoleColors.PURPLE_BOLD + "Inserisci indice prodotto da rimuovere/disabilitare: " + ConsoleColors.ANSI_RESET);
                    int r = inputIntero() - 60; 
                    if (r >= 0 && r < d.prodottiDisponibili()) {
                        d.rimuoviProdotto(r);
                        System.out.println(ConsoleColors.PURPLE_BOLD + "Prodotto rimosso" + ConsoleColors.ANSI_RESET);
                    } else {
                        System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Errore di inserimento." + ConsoleColors.ANSI_RESET);
                    }
                } else {
                    System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Errore di inserimento." + ConsoleColors.ANSI_RESET);
                }
                break;

            default:
                System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Errore di inserimento." + ConsoleColors.ANSI_RESET);
        }
        return c;
    }

    public static void main(String[] args) throws IOException {
        clearConsole();
        System.out.println(ConsoleColors.PURPLE_BACKGROUND + "Benvenuto nel gestionale di distributori\nVuoi accedere al sistema come cliente o come proprietario?" + ConsoleColors.ANSI_ORANGE + "\n0) Esci;" + ConsoleColors.ANSI_RESET + ConsoleColors.CYAN_BACKGROUND + "\n1) Cliente;" + ConsoleColors.RED_BACKGROUND + "\n2) Proprietario;" + ConsoleColors.PURPLE_BACKGROUND + "\nInserisci una scelta: " + ConsoleColors.ANSI_RESET);
        int m = inputIntero();
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
                    System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Errore di inserimento." + ConsoleColors.ANSI_RESET);
                    break;
            }
            if (m != 0) {
                System.out.println(ConsoleColors.PURPLE_BACKGROUND + "Vuoi accedere al sistema come cliente o come proprietario?" + ConsoleColors.ANSI_ORANGE + "\n0) Esci;" + ConsoleColors.ANSI_RESET + ConsoleColors.CYAN_BACKGROUND + "\n1) Cliente;" + ConsoleColors.RED_BACKGROUND + "\n2) Proprietario;" + ConsoleColors.PURPLE_BACKGROUND + "\nInserisci una scelta: " + ConsoleColors.ANSI_RESET);
                m = inputIntero();
                clearConsole();
            }

        } while (m != 0);
    }

}
