/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aziendadistributori;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Matteo
 */
public enum ProdottoCaldo {
    TE("Tè caldo", 0.2f),
    LATTE_MACCHIATO("Latte Macchiato", 1.5f),
    CAPPUCCINO("Cappuccino", 1.5f),
    CIOCCOLATA("Cioccolata Calda", 2.5f),
    CAFFE("Caffè", 1.0f);

    private ProdottoCaldo(String nome, float prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
    }

    private String nome;
    private float prezzo;

    /**
     *
     * @return "NomeProdotto: Prezzo in euro".
     */
    public String getProdotto() {
        return nome + ": " + prezzo + " euro";
    }

    /**
     * 
     * @return prezzo del prodotto.
     */
    public float getPrezzo() {
        return prezzo;
    }

    /**
     * Stampa con indice tutti i prodotti caldi da poter inserire nel distributore di prodotti caldi.
     */
    public static void stampaProdottiCaldi() {
        int i = 0;
        List<ProdottoCaldo> asList = Arrays.asList(ProdottoCaldo.values());
        for (ProdottoCaldo prodottoCaldo : asList) {
            System.out.println(i + ") " + prodottoCaldo.getProdotto());
            i++;
        }
    }
}
