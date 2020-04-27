package linkedlist;

import java.io.FileInputStream;
import java.io.IOException;

public class LinkedList<T> {

    private Node<T> head;
    private Node<T> mainNode;

    void addDataFromFile(String path) {
    
        FileInputStream fis = null;

        try {
            int karakter;
            fis = new FileInputStream(path);

            while ((karakter = fis.read()) != -1) {

                // Karakterler okundugu gibi listeme ve ana dügümlere ekle
                // Buyuk harf ise kucult
                if (Character.isUpperCase((char) karakter)) {
                    addLast((T) Character.valueOf((char) Character.toLowerCase((char) karakter)));
                    addMainNode((T) Character.valueOf((char) Character.toLowerCase((char) karakter)));
                    // Bosluk ise eklesin
                } else if (Character.isSpaceChar((char) karakter)) {
                    addLast((T) Character.valueOf((char) karakter));
                    // Karakter degil ise atla
                } else if (!Character.isLetter((char) karakter)) {
                    continue;
                } else {
                    addLast((T) Character.valueOf((char) karakter));
                    addMainNode((T) Character.valueOf((char) karakter));
                }

            }

            fis.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    void addMainNode(T newData) {

        Node<T> newMainNode = new Node<T>(newData);
        // Listedeki anadügümüm bossa yeni bir tane olustur
        if (isMainNodeEmpty()) {
            mainNode = newMainNode;
        } else {
            // Eger bos degil ise onun nextine ekle
            Node<T> temp = mainNode;
            boolean isExist = false;
            while (temp.nextNode != null) {
                if (temp.data.equals(newData)) {
                    isExist = true;
                }
                temp = temp.nextNode;
            }
            if (!isExist) {
                addLastMainNode(newMainNode);
            }
        }
    }

    void addLastMainNode(Node<T> newMainNode) {

        // Anadügümleri sona eklemek icin 
        Node<T> temp = mainNode;

        while (temp.nextNode != null) {
            temp = temp.nextNode;
        }

        temp.nextNode = newMainNode;

    }

    void printMainNodes() {
        Node<T> temp = mainNode;
        System.out.println("--------MAIN NODES--------");
        // Bütün anadügümleri gez ve yazdir
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.nextNode;
        }
        System.out.println("");
    }

    void printListNodes() {

        System.out.println("--------LIST NODES--------");
        Node<T> temp = mainNode;

        // Once ana dügümleri gez 
        while (temp != null) {
            ListNode<T> tempList = temp.listNode;
            // Sonra liste dügümlerini gez ve null degilse yazdir
            while (tempList != null) {
                System.out.print(temp.data + " -> " + tempList.data + "," + tempList.count + " ");
                tempList = tempList.nextListNode;
            }

            System.out.println("");
            temp = temp.nextNode;
        }

    }

    boolean isMainNodeEmpty() {
        // Ana dügüm bos mu kontrolu
        return mainNode == null;
    }

    boolean isListNodeEmpty() {
        // Liste dügümü bos mu kontrolu
        return mainNode.listNode == null;
    }

    void addListNode() {

        Node<T> main = mainNode;

        // Anadügümleri gez 
        while (main != null) {
            Node<T> temp = head;
            // Dosyadaki verileri gez
            while (temp.nextNode != null) {
                
                // Ana dügümdeki veri ile dosyadaki veri aynı ise
                if (main.data.equals(temp.data)) {

                    // Ana dügümün listenode u yoksa, yeni bir tane olustur
                    if (main.listNode == null) {

                        // Listenode unu olusturmak icin bosluk olup olmadıgını kontrol et 
                        // Ve bosluk olmadıgı sürece listNode olustur
                        if (!Character.isSpaceChar((Character) temp.nextNode.data)) {
                            T data = temp.nextNode.data;
                            ListNode<T> newListNode = new ListNode<T>(data);
                            main.listNode = newListNode;
                            main.listNode.count++;
                        }

                    } else {
                        // Anadügümün listnodu varsa ve listnodenun datası, sonraki anadügümün datasına esit degil ise
                        // yeni bir listenodu olustur ve next listenoduna ata
                        if (main.listNode.data != temp.nextNode.data) {

                            if (!Character.isSpaceChar((Character) temp.nextNode.data)) {

                                T nextData = temp.nextNode.data;
                                ListNode<T> nextListNode = new ListNode<T>(nextData);
                                main.listNode.nextListNode = nextListNode;
                                main.listNode.nextListNode.count++;

                            }
                        // Eger sonraki anadügümün datasına esitse bu listenodu sayisini arttir
                        } else {
                            main.listNode.count++;
                        }
                    }

                }

                temp = temp.nextNode;

            }

            main = main.nextNode;
        }

    }

    void ardisikKarakterler(char k) {
        Node<T> temp = mainNode;
        System.out.println("------Ardisik Karakterler------");
        // Anadügümleri gez
        while (temp != null) {
            ListNode<T> tempList = temp.listNode;
            // Liste dügümlerini gez
            while (tempList != null) {
                // Aradigim karakter varsa kendisini ve sonrasındaki karakteri yazdir
                if (temp.data.equals(k)) {
                    System.out.println("Input : " + temp.data);
                    System.out.println("Ardisik : " + tempList.data + "," + tempList.count);
                }
                tempList = tempList.nextListNode;
            }

            temp = temp.nextNode;
        }

        System.out.println("");
    }

    void enCokArdisik() {
        System.out.println("--------En Cok Ardisik--------");
        Node<T> temp = mainNode;

        ListNode<T> maxList = temp.listNode;
        Node<T> mainMax = temp;
        
        // Ana dügümleri gez
        while (temp != null) {
            ListNode<T> tempList = temp.listNode;
            // Liste dügümlerini gez
            while (tempList != null) {
                
                // maxList'ten büyük anadügüm ve listedügümlerini degiskenlerde tut 
                if (tempList.count > maxList.count) {
                    maxList = tempList;
                    mainMax = temp;
                }

                tempList = tempList.nextListNode;
            }
            temp = temp.nextNode;
        }
        // En cok ardisik olan ikiliyiz yazdir
        System.out.println("En cok ardisik ikili : " + mainMax.data + " -> " + maxList.data + "," + maxList.count);
        System.out.println("");
    }

    void enCokArdisik(char k) {

        System.out.println("-----k'dan sonra en cok ardisik-----");
        Node<T> temp = mainNode;
        ListNode<T> maxList = temp.listNode;
        Node<T> maxMain = temp;
        
        // Anadügümleri gez
        while (temp != null) {
            // Aradigim karakter bu anadügümlerde varsa 
            if (temp.data.equals(k)) {
                ListNode<T> tempList = temp.listNode;
                // Liste nodelarına bak ve countı max olani 2 degiskende tut
                while (tempList != null) {
                    maxList = tempList;
                    if (tempList.count > maxList.count) {
                        maxList = tempList;
                        maxMain = temp;
                    }

                    tempList = tempList.nextListNode;
                }

            }
            temp = temp.nextNode;
        }
        System.out.println("Input : " + k);
        System.out.println("Ardisik : " + maxList.data + "," + maxList.count);
        System.out.println("");

    }

    void enAzArdisik() {
        System.out.println("--------En Az Ardisik--------");
        Node<T> temp = mainNode;

        ListNode<T> minList = temp.listNode;
        Node<T> minMain = temp;

        // Anadügümleri gez
        while (temp != null) {
            ListNode<T> tempList = temp.listNode;
            // Liste dügümlerini gez 
            while (tempList != null) {
                
                // minList'ten kücük anadügüm ve listedügümlerini degiskenlerde tut 
                if (tempList.count < minList.count) {
                    minList = tempList;
                    minMain = temp;

                }

                tempList = tempList.nextListNode;
            }
            temp = temp.nextNode;
        }
        System.out.println("En az ardisik ikili : " + minMain.data + " -> " + minList.data + "," + minList.count);
        System.out.println("");
    }

    void enAzArdisik(char k) {
        System.out.println("-----k'dan sonra en az ardisik-----");
        Node<T> temp = mainNode;
        ListNode<T> minList = temp.listNode;
        Node<T> minMain = temp;

        // Anadügümleri gez
        while (temp != null) {

            // Aradigim karakter bu anadügümlerde varsa
            if (temp.data.equals(k)) {
                ListNode<T> tempList = temp.listNode;
                // Liste dügümlerine bak ve count ı en kücük olanı 2 degiskende tut
                while (tempList != null) {
                    minList = tempList;
                    if (tempList.count < minList.count) {
                        minList = tempList;
                        minMain = temp;
                    }

                    tempList = tempList.nextListNode;
                }

            }
            temp = temp.nextNode;
        }
        System.out.println("Input : " + k);
        System.out.println("Ardisik : " + minList.data + "," + minList.count);
    }

    // --------------------------------------------------------------  
    void addLast(Node<T> newNode) {
        if (isEmpty()) {
            head = newNode;
        } else {
            Node<T> temp = head;

            while (temp.nextNode != null) {
                temp = temp.nextNode;
            }

            temp.nextNode = newNode;

        }
    }

    void addLast(T newData) {
        addLast(new Node<>(newData));
    }

    void print() {
        Node<T> temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.nextNode;
        }

        System.out.println("null");
    }

    boolean isEmpty() {
        return head == null;
    }

}
