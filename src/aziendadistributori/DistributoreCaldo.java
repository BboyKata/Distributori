/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aziendadistributori;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matteo
 */
public class DistributoreCaldo implements Distributore {

    private float credito;
    private float profitto;
    private List<ProdottoCaldo> prodotti;
    private static final StringBuilder sb = new StringBuilder();

    public DistributoreCaldo() {
        this.prodotti = new LinkedList<ProdottoCaldo>();
        this.profitto = 0;
        this.credito = 0;
    }

    public void aggiungiProdotto(Object o) {
        try {
            prodotti.add((ProdottoCaldo) o);
        } catch (Exception e) {
            System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Errore di inserimento. Il prodotto inserito non è un Prodotto Caldo" + ConsoleColors.ANSI_RESET);
        }
    }

    public boolean isProdottoInserito(Object o) {
        try {
            return prodotti.contains((ProdottoCaldo) o);
        } catch (Exception e) {
            System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Errore di inserimento. Il prodotto inserito non è un Prodotto Caldo" + ConsoleColors.ANSI_RESET);
            return false;
        }
    }

    public void rimuoviProdotto(int posizione) {
        //controlla che ti stai riferendo alla giusta posizione
        prodotti.remove(posizione);
    }

    public void erogaProdotto(int posizione) {
        ProgDistributori.clearConsole();
        CustomProgressBar pb1 = new CustomProgressBar("Erogazione prodotto", 100, 50, true, " %");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
//                        System.out.println("qq");
                    pb1.step();
//                        pb1.refresh();
                    try {
                        Thread.sleep(60);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                pb1.setExtraMessage("[PRONTO]");
                pb1.close();
                System.err.println("Prego, ritirare il prodotto");
                credito -= prodotti.get(posizione).getPrezzo();
                profitto += prodotti.get(posizione).getPrezzo();
                if (credito > 0) {
                    System.err.println(ConsoleColors.ANSI_GREEN + "Resto: " + credito + ConsoleColors.ANSI_RESET + "\n");
                    credito = 0;
                }
            }
        });

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(DistributoreCaldo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean isProdottoPagato(int posizione) {
        if (credito >= prodotti.get(posizione).getPrezzo()) {
            return true;
        }
        return false;
    }

    public void aggiungiMoneta(float moneta) {
        credito += moneta;
    }

    public void mostraProdotti() {
        for (int i = 0; i < prodotti.size(); i++) {
            System.out.println(ConsoleColors.ANSI_ORANGE + ((Integer) (i + 60)) + ") " + prodotti.get(i).getProdotto() + ConsoleColors.ANSI_RESET);
        }
    }

    public float getCredito() {
        return credito;
    }

    public int prodottiDisponibili() {
        return prodotti.size();
    }

    public float getProfitto() {
        return profitto;
    }
}
