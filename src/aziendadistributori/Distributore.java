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

    public void aggiungiProdotto(Object o);
    
    public boolean isProdottoInserito(Object o);
    
    public void rimuoviProdotto(int posizione);

    public void erogaProdotto(int posizione);

    public boolean isProdottoPagato(int posizione);

    public void aggiungiMoneta(float moneta);
    
    public void mostraProdotti();
    
    public int prodottiDisponibili();
    
}
