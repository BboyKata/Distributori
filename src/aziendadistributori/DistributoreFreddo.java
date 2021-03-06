/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aziendadistributori;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Matteo
 */
public class DistributoreFreddo implements Distributore {

    private float credito;
    private float profitto;
    private List<ProdottoFreddo> prodotti;

    public DistributoreFreddo() {
        this.prodotti = new LinkedList<ProdottoFreddo>();
        this.credito = 0;
        this.profitto = 0;
    }

    public void aggiungiProdotto(Object o) {
        try {
            prodotti.add((ProdottoFreddo) o);
        } catch (Exception e) {
            System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Errore di inserimento. Il prodotto inserito non è un Prodotto Freddo" + ConsoleColors.ANSI_RESET);
        }
    }

    public float getCredito() {
        return credito;
    }

    public boolean isProdottoInserito(Object o) {
        try {
            return prodotti.contains((ProdottoFreddo) o);
        } catch (Exception e) {
            System.out.println(ConsoleColors.ANSI_RED + "|ATTENZIONE!| Errore di inserimento. Il prodotto inserito non è un Prodotto Freddo" + ConsoleColors.ANSI_RESET);
            return false;
        }
    }

    public void rimuoviProdotto(int posizione) {
        //controlla che ti stai riferendo alla giusta posizione
        prodotti.remove(posizione);
    }

    public void erogaProdotto(int posizione) {
        System.out.println(ConsoleColors.ANSI_ORANGE + "Erogazione " + prodotti.get(posizione).getProdotto() + ConsoleColors.ANSI_RESET + "\n");
        credito -= prodotti.get(posizione).getPrezzo();
        if (credito > 0) {
            System.out.println(ConsoleColors.ANSI_GREEN + "Resto: " + credito + ConsoleColors.ANSI_RESET + "\n");
            credito = 0;
        }
        rimuoviProdotto(posizione);
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
            System.out.println(ConsoleColors.ANSI_CYAN + ((Integer) (i + 60)) + ") " + prodotti.get(i).getProdotto() + ConsoleColors.ANSI_RESET);
        }
    }

    public int prodottiDisponibili() {
        return prodotti.size();
    }

    public float getProfitto() {
        return profitto;
    }

}
