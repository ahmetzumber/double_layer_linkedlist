package linkedlist;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // /Users/mac/NetBeansProjects/Veri_Yapilari_Odev1/src/Odev/veri.txt
        String path = ""; 
        System.out.print("Bir dosya yolu giriniz : ");
        Scanner s = new Scanner(System.in);
        path = s.next();
        
        LinkedList list = new LinkedList();
        list.addDataFromFile(path);
        //list.print();
        list.printMainNodes();
        list.addListNode();
        list.printListNodes();
        list.ardisikKarakterler('e');
        list.enCokArdisik();
        list.enCokArdisik('d');
        list.enAzArdisik();
        list.enAzArdisik('v');
        
    }

}
