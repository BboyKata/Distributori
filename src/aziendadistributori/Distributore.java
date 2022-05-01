/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aziendadistributori;

/**
 *
 * @author Matteo
 */
public interface Distributore {

    /**
     * Aggiunge prodotto caldo o freddo ad un distributore.
     *
     * @param o Prodotto Caldo o Freddo da aggiungere/rendere disponibile in un
     * distributore.
     */
    public void aggiungiProdotto(Object o);

    /**
     * Verifica la presenza di un prodotto nel distributore.
     *
     * @param o Prodotto Caldo o Freddo
     * @return true se il prodotto è presente nel distributore, altrimenti
     * false.
     */
    public boolean isProdottoInserito(Object o);

    /**
     * Rimuove il prodotto presente all'indice passato da parametro.
     *
     * @param posizione indice del prodotto nel distributore.
     */
    public void rimuoviProdotto(int posizione);

    /**
     * Eroga il prodotto presente all'indice passato da parametro a parametro.
     * Per il distributore freddo rimuove anche il prodotto, per quello caldo
     * no.
     *
     * @param posizione indice del prodotto nel distributore.
     */
    public void erogaProdotto(int posizione);

    /**
     * Verifica se il prodotto è stato pagato completamente.
     *
     * @param posizione indice del prodotto nel distributore da voler
     * acquistare.
     * @return true se il credito >= del prezzo del prodotto scelto.
     */
    public boolean isProdottoPagato(int posizione);

    /**
     * Aumenta il credito del distributore.
     *
     * @param moneta valore della moneta: 0.10; 0.20; 0.50; 1.0; 2.0;
     */
    public void aggiungiMoneta(float moneta);

    /**
     * Mostra i prodotti disponibili nel distributore
     */
    public void mostraProdotti();

    /**
     * Restituisce il numero dei prodotti presenti/disponibili nel distributore
     *
     * @return numero prodotti presenti/disponibili nel distributore
     */
    public int prodottiDisponibili();

    /**
     * Il profitto è la somma di tutte le transazioni eseguite.
     *
     * @return profitto attuale del distributore.
     */
    public float getProfitto();

    /**
     * Restituisce il credito attuale nel distributore.
     *
     * @return il credito attuale nel distributore.
     */
    public float getCredito();

}
