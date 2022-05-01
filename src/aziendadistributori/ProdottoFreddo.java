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
public enum ProdottoFreddo {
    PATATINE("Patatine",0.8f),
    TRAMEZZINO("Tramezzino",2.0f),
    TE("Tè freddo",0.2f),
    ACQUA("Acqua",1.0f),
    COCA_COLA("Coca Cola",1.5f),
    ARANCIATA("Aranciata",3.0f);
    
    private ProdottoFreddo(String nome, float prezzo){
        this.nome = nome;
        this.prezzo = prezzo;
    }
    
    private String nome;
    private float prezzo;
    
    public String getProdotto(){
        return nome+": "+prezzo+" euro";
    }
    
    public float getPrezzo(){
        return prezzo;
    }
    
    public static void stampaProdottiFreddi(){
        int i = 0;
        List<ProdottoFreddo> asList = Arrays.asList(ProdottoFreddo.values());
        for (ProdottoFreddo prodottoFreddo : asList) {
            System.out.println(i+") "+prodottoFreddo.getProdotto());
            i++;
        }
    }
}