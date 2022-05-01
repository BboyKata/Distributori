/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aziendadistributori;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Matteo
 */
public class AziendaDistributori {

    private List<Distributore> distributori;

    public AziendaDistributori() {
        distributori = new LinkedList<Distributore>();
    }

    public void installaDistributore(Distributore d) {
        distributori.add(d);
    }

    public int getNumeroDistributori() {
        return distributori.size();
    }

    public int getNumeroDistributoriCaldi() {
        int c = 0;
        for (int i = 0; i < getNumeroDistributori(); i++) {
            if (distributori.get(i) instanceof DistributoreCaldo) {
                c++;
            }
        }
        return c;
    }

    public int getNumeroDistributoriFreddi() {
        int c = 0;
        for (int i = 0; i < getNumeroDistributori(); i++) {
            if (distributori.get(i) instanceof DistributoreFreddo) {
                c++;
            }
        }
        return c;
    }

    public void rimuoviDistributore(int posizione) {
        distributori.remove(posizione);
    }

    public void rifornisciDistributore(int posizione) {
        Scanner myObj = new Scanner(System.in);
        try {
            if (distributori.get(posizione) instanceof DistributoreCaldo) {
                System.out.println(ConsoleColors.PURPLE_BOLD + "Puoi rifornire il distributore caldo con i seguenti prodotti: " + ConsoleColors.ANSI_RESET);
                ProdottoCaldo.stampaProdottiCaldi();
                System.out.println(ConsoleColors.PURPLE_BOLD + "Inserisci indice prodotto da abilitare: " + ConsoleColors.ANSI_RESET);
                int c = ProgDistributori.inputIntero();
                ProdottoCaldo pc = null;
                if (c >= 0 && c <= 4) {
                    switch (c) {
                        case 0:
                            pc = ProdottoCaldo.TE;
                            break;
                        case 1:
                            pc = ProdottoCaldo.LATTE_MACCHIATO;
                            break;
                        case 2:
                            pc = ProdottoCaldo.CAPPUCCINO;
                            break;
                        case 3:
                            pc = ProdottoCaldo.CIOCCOLATA;
                            break;
                        case 4:
                            pc = ProdottoCaldo.CAFFE;
                            break;
                    }
                    if (distributori.get(posizione).isProdottoInserito(pc)) {
                        System.out.println(ConsoleColors.ANSI_ORANGE + "Prodotto già abilitato" + ConsoleColors.ANSI_RESET);
                    } else {
                        distributori.get(posizione).aggiungiProdotto(pc);
                    }
                } else {
                    System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Errore di inserimento." + ConsoleColors.ANSI_RESET);
                }
            } else {
                System.out.println(ConsoleColors.PURPLE_BOLD + "Puoi rifornire il distributore freddo con i seguenti prodotti: " + ConsoleColors.ANSI_RESET);
                ProdottoFreddo.stampaProdottiFreddi();
                System.out.println(ConsoleColors.PURPLE_BOLD + "Inserisci indice prodotto da inserire: " + ConsoleColors.ANSI_RESET);
                int c = ProgDistributori.inputIntero();
                if (c >= 0 && c <= 5) {
                    System.out.println(ConsoleColors.PURPLE_BOLD + "Inserisci quantità prodotto da inserire: " + ConsoleColors.ANSI_RESET);
                    int rip = ProgDistributori.inputIntero();
                    switch (c) {
                        case 0:
                            for (int i = 0; i < rip; i++) {
                                distributori.get(posizione).aggiungiProdotto(ProdottoFreddo.PATATINE);
                            }
                            break;
                        case 1:
                            for (int i = 0; i < rip; i++) {
                                distributori.get(posizione).aggiungiProdotto(ProdottoFreddo.TRAMEZZINO);
                            }
                            break;
                        case 2:
                            for (int i = 0; i < rip; i++) {
                                distributori.get(posizione).aggiungiProdotto(ProdottoFreddo.TE);
                            }
                            break;
                        case 3:
                            for (int i = 0; i < rip; i++) {
                                distributori.get(posizione).aggiungiProdotto(ProdottoFreddo.ACQUA);
                            }
                            break;
                        case 4:
                            for (int i = 0; i < rip; i++) {
                                distributori.get(posizione).aggiungiProdotto(ProdottoFreddo.COCA_COLA);
                            }
                            break;
                        case 5:
                            for (int i = 0; i < rip; i++) {
                                distributori.get(posizione).aggiungiProdotto(ProdottoFreddo.ARANCIATA);
                            }
                            break;
                    }
                } else {
                    System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Errore di inserimento." + ConsoleColors.ANSI_RESET);
                }
            }

        } catch (Exception e) {
            System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Riprovare" + ConsoleColors.ANSI_RESET);
            rifornisciDistributore(posizione);
        }
    }

    public void stampaInfo(boolean azienda) {
        if (getNumeroDistributori() == 0) {
            System.out.println("Non ci sono distributori installati" + ConsoleColors.ANSI_RESET);
        } else {
            System.out.println(ConsoleColors.PURPLE_BOLD + "Distributori installati: " + getNumeroDistributori() + "\n Di cui:\n" + ConsoleColors.ANSI_RESET + ConsoleColors.ANSI_CYAN + "\t-Distributori prodotti freddi: " + getNumeroDistributoriFreddi() + ConsoleColors.ANSI_ORANGE + "\t-Distributori prodotti caldi: " + getNumeroDistributoriCaldi() + "\n" + ConsoleColors.ANSI_RESET);
            for (int i = 0; i < getNumeroDistributori(); i++) {
                if (distributori.get(i) instanceof DistributoreCaldo) {
                    System.out.println(ConsoleColors.ANSI_ORANGE + i + ") Distributore Caldo; Prodotti: " + distributori.get(i).prodottiDisponibili() + ConsoleColors.ANSI_RESET);
                } else {
                    System.out.println(ConsoleColors.ANSI_CYAN + i + ") Distributore Freddo; Prodotti: " + distributori.get(i).prodottiDisponibili() + ConsoleColors.ANSI_RESET);
                }
                if (azienda) {
                    System.out.println(ConsoleColors.ANSI_GREEN + "Profitto: " + distributori.get(i).getProfitto() + " euro");
                }
                System.out.println("");
            }
        }
    }

    public void stampaInfoTipo(boolean caldo) {
        if (caldo) {
            if (getNumeroDistributoriCaldi() > 0) {
                System.out.println(ConsoleColors.PURPLE_BOLD + "Distributori di prodotti caldi: " + getNumeroDistributoriCaldi() + ConsoleColors.ANSI_RESET + "\n");
                for (int i = 0; i < getNumeroDistributori(); i++) {
                    if (distributori.get(i) instanceof DistributoreCaldo) {
                        System.out.println(ConsoleColors.ANSI_ORANGE + i + ") Prodotti disponibili: " + distributori.get(i).prodottiDisponibili() + " Profitto: " + distributori.get(i).getProfitto() + " euro" + ConsoleColors.ANSI_RESET + "\n");
                    }
                }
            } else {
                System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Non c'è ancora alcun distributore di prodotti caldi installato!" + ConsoleColors.ANSI_RESET);
            }
        } else {
            if (getNumeroDistributoriFreddi() > 0) {
                System.out.println(ConsoleColors.PURPLE_BOLD + "Distributori di prodotti freddi: " + getNumeroDistributoriFreddi() + ConsoleColors.ANSI_RESET + "\n");
                for (int i = 0; i < getNumeroDistributori(); i++) {
                    if (distributori.get(i) instanceof DistributoreFreddo) {
                        System.out.println(ConsoleColors.ANSI_CYAN + i + ") Prodotti disponibili: " + distributori.get(i).prodottiDisponibili() + " Profitto: " + distributori.get(i).getProfitto() + " euro" + ConsoleColors.ANSI_RESET + "\n");
                    }
                }
            } else {
                System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Non c'è ancora alcun distributore di prodotti freddi installato!" + ConsoleColors.ANSI_RESET);
            }
        }
    }

    public Distributore getDistributore(int posizione) {
        return distributori.get(posizione);
    }

    public float getProfittiTotali() {
        float somma = 0;
        for (int i = 0; i < getNumeroDistributori(); i++) {
            somma += distributori.get(i).getProfitto();
        }
        return somma;
    }

}
