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

    public static int userMode() {
        
    }

    public static int aziendaMode() {
        System.out.println("Benvenuto nel gestionale lato proprietario; Ecco lo stato attuale: ");
        azienda.stampaInfo(true);
        System.out.println("Operazioni disponibili:\n0) Cambia modalità o esci;\n1) Installa Distributore;\n2) Rifornisci distributore;\n3) Visualizza informazioni generali;\n4) Visualizza informazioni distributore singolo;\n5) Visualizza profitto azienda attuale;\nScegli operazione: ");
        int c = Integer.parseInt(myObj.nextLine())
    }

    public static void main(String[] args) throws IOException {
        int m;
        while (true) {
            System.out.println("Benvenuto nel gestionale di distributori\nVuoi accedere al sistema come cliente o come proprietario?\n0) Esci;\n1) Cliente;\n2) Proprietario;\nInserisci una scelta: ");
            try{
                m = Integer.parseInt(myObj.nextLine());
                break;
            }catch(Exception e){
                System.out.println("|ATTENZIONE!| Errore di inserimento.");
            }
        }
        Runtime.getRuntime().exec("cls");  
        do {
            switch(m){
                case 0:
                    System.out.println("Arrivederci.");
                    break;
                case 1:
                    int r = 0;
                    do{
                        r = userMode();
                    }while(r != 0);
                    break;
                case 2:
                    r = 0;
                    do{
                        r = aziendaMode();
                    }while(r != 0);
                    break;
                default:
                    System.out.println("Errore di inserimento, reinserire la scelta.");
                    break;
            }
            System.out.println("Vuoi accedere al sistema come cliente o come proprietario?\n0) Esci;\n1) Cliente;\n2) Proprietario;\nInserisci una scelta: ");
            m = Integer.parseInt(myObj.nextLine());
            Runtime.getRuntime().exec("cls");              
        } while (m != 0);
    }

}
