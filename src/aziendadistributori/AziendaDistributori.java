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
    
    public int getNumeroDistributoriCaldi(){
        int c = 0;
        for (int i = 0; i < getNumeroDistributori(); i++) {
            if(distributori.get(i) instanceof DistributoreCaldo){
                c++;
            }
        }
        return c;
    }
    
    public int getNumeroDistributoriFreddi(){
        int c = 0;
        for (int i = 0; i < getNumeroDistributori(); i++) {
            if(distributori.get(i) instanceof DistributoreFreddo){
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
                System.out.println("Puoi rifornire il distributore caldo con i seguenti prodotti: ");
                ProdottoCaldo.stampaProdottiCaldi();
                System.out.println("Inserisci indice prodotto da inserire: ");
                int c = Integer.parseInt(myObj.nextLine());
                if (c >= 0 && c <= 4) {
                    System.out.println("Inserisci quantità prodotto da inserire: ");
                    int rip = Integer.parseInt(myObj.nextLine());
                    switch (c) {
                        case 0:
                            for (int i = 0; i < rip; i++) {
                                distributori.get(posizione).aggiungiProdotto(ProdottoCaldo.TE);
                            }
                            break;
                        case 1:
                            for (int i = 0; i < rip; i++) {
                                distributori.get(posizione).aggiungiProdotto(ProdottoCaldo.LATTE_MACCHIATO);
                            }
                            break;
                        case 2:
                            for (int i = 0; i < rip; i++) {
                                distributori.get(posizione).aggiungiProdotto(ProdottoCaldo.CAPPUCCINO);
                            }
                            break;
                        case 3:
                            for (int i = 0; i < rip; i++) {
                                distributori.get(posizione).aggiungiProdotto(ProdottoCaldo.CIOCCOLATA);
                            }
                            break;
                        case 4:
                            for (int i = 0; i < rip; i++) {
                                distributori.get(posizione).aggiungiProdotto(ProdottoCaldo.CAFFE);
                            }
                            break;
                    }
                } else {
                    System.out.println("Inserimento errato, rieseguire la procedura.");
                }
            } else {
                System.out.println("Puoi rifornire il distributore caldo con i seguenti prodotti: ");
                ProdottoFreddo.stampaProdottiFreddi();
                System.out.println("Inserisci indice prodotto da inserire: ");
                int c = Integer.parseInt(myObj.nextLine());
                if (c >= 0 && c <= 5) {
                    System.out.println("Inserisci quantità prodotto da inserire: ");
                    int rip = Integer.parseInt(myObj.nextLine());
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
                    System.out.println("Inserimento errato, rieseguire la procedura.");
                }
            }

        } catch (Exception e) {
            System.out.println("|ERRORE!| RIPROVARE!");
            rifornisciDistributore(posizione);
        }
    }

    public void stampaInfo(boolean azienda) {
        if (getNumeroDistributori() == 0) {
            System.out.println("Non ci sono distributori installati");
        } else {
            System.out.println("Distributori installati: "+getNumeroDistributori()+"\n Di cui:\n-Distributori prodotti freddi: "+getNumeroDistributoriFreddi()+"\n-Distributori prodotti caldi: "+getNumeroDistributoriCaldi());
            for (int i = 0; i < getNumeroDistributori(); i++) {
                if (distributori.get(i) instanceof DistributoreCaldo) {
                    System.out.println(i + ") Distributore Caldo; Prodotti: " + distributori.get(i).prodottiDisponibili());
                } else {
                    System.out.println(i + ") Distributore Freddo; Prodotti: " + distributori.get(i).prodottiDisponibili());
                }
                if(azienda){
                    System.out.println("Profitto: "+distributori.get(i).getProfitto()+"€");
                }
            }
        }
    }
    
    public void stampaInfoTipo(boolean caldo){
        if(caldo){
            if(getNumeroDistributoriCaldi()>0){
                System.out.println("Distributori di prodotti caldi: "+getNumeroDistributoriCaldi());
                for (int i = 0; i < getNumeroDistributori(); i++) {
                    if(distributori.get(i) instanceof DistributoreCaldo){
                        System.out.println(i+") Prodotti disponibili: "+distributori.get(i).prodottiDisponibili()+" Profitto: "+distributori.get(i).getProfitto()+"€");
                    }
                }
            }else{
                System.out.println("Non c'è ancora alcun distributore di prodotti caldi installato;");
            }
        }else{
            if(getNumeroDistributoriFreddi()>0){
                System.out.println("Distributori di prodotti freddi: "+getNumeroDistributoriFreddi());
                for (int i = 0; i < getNumeroDistributori(); i++) {
                    if(distributori.get(i) instanceof DistributoreFreddo){
                        System.out.println(i+") Prodotti disponibili: "+distributori.get(i).prodottiDisponibili()+" Profitto: "+distributori.get(i).getProfitto()+"€");
                    }
                }
            }else{
                System.out.println("Non c'è ancora alcun distributore di prodotti freddi installato;");
            }
        }
    }
    
    public Distributore getDistributore(int posizione){
        return distributori.get(posizione);
    }
    
    public float getProfittiTotali(){
        float somma = 0;
        for (int i = 0; i < getNumeroDistributori(); i++) {
            somma += distributori.get(i).getProfitto();
        }
        return somma;
    }

}
