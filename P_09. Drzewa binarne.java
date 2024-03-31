//Dorian Rzasa- grupa 3
import java.util.Scanner;

class Node{  //klasa reprezentujaca wezel w drzewie
    int value;  //wartosc 
    Node left;  //lewe dziecko
    Node right;  //prawe dziecko

    Node(int value){  //konstruktor wezla przyjmujacy jako argument wartosc
        this.value=value;  //ustawiamy wartosc na dana
        this.left=null;  //ustawiamy dzieci na nulle
        this.right=null;
    }
}

class MyArrayList<T>{  //klasa myarraylist
    private Object[] elements;  //tablica objektow
    private int size;  //zmienna aktualny rozmiar
    private static final int DEFAULT_CAPACITY=10;

    public MyArrayList(){  //konstruktor domyslny
        elements=new Object[DEFAULT_CAPACITY];  //tworzymy tablice obiektow o rozmiarze 10
        size=0;  //ustawiamy aktualny rozmiar na 0
    }

    public void add(T element){  //metoda do dodawania elementu
        if(size==elements.length){  //jezeli nasz aktualny rozmiar rowna sie wielkosci
            expandCapacity();  //to zwiekszamy wielkosc
        }
        elements[size]=element;  //w innym przypadku na koniec tablicy dodajemy nasz element
        size++;  //zwiekszamy zmienna reprezentujaca aktualny rozmiar tablicy
    }

    public T get(int index){  //metoda zwracajaca element o danym indeksie
        if(index<0 || index>=size){  //jezeli zapytanie nie ma sensu
            throw new IndexOutOfBoundsException("Invalid index"); 
        }
        return (T) elements[index];  //zwracamy element na danym indeksie
    }

    public void remove(int index){  //metoda do usuwania elementu
        if(index < 0 || index>=size){  //jezeli zapytanie nie ma sensu
            throw new IndexOutOfBoundsException("Invalid index");
        }
        for(int i=index; i<size-1; i++){  //petla do usuwania elementu
            elements[i]=elements[i+1];  
        }
        elements[size-1]=null;  //ostatni element ustawiamy na nulla
        size--;  //zmniejszamy aktualny stan
    }

    public int size(){  //metoda zwracajaca rozmiar 
        return size;
    }

    public boolean isEmpty(){  //metoda sprawdzajaca czy lista jest pusta
        return size==0;
    }

    private void expandCapacity(){  //metoda do zwiekszania pojemnosci tablicy
        int newCapacity=elements.length*2;  //zwiekszamy zmienna pojemnosc 
        Object[] newElements=new Object[newCapacity];  //tworzymy nowa tablice o nowym rozmiarze
        System.arraycopy(elements, 0, newElements, 0, size);  //kopiujemy elementy ze starej tablicy do nowej
        elements=newElements;
    }
}

class BinaryTree{  //klasa drzewo binarne
    Node root;  //korzen drzewa

    BinaryTree(){  //konstruktor domysly
        this.root=null;  //ustawia korzen drzewa na nulla
    }

    void buildTreeFromPreIn(int[] preorder, int[] inorder){  //metoda do wywolania konstrukcji drzewa na podstawie porządku preorder i inorder
        root=buildTreeFromPreInHelper(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
    }

    Node buildTreeFromPreInHelper(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd){  //metoda do konstrukcji drzewa na podstawie porządku preorder i inorder
        if(preStart>preEnd || inStart>inEnd){  //jesli indeks jest nieprawidlowy to zwracamy nulla  
            return null;
        }

        int rootValue=preorder[preStart];  //roota ustawiamy na prestartowa wartosc z porzadku preorder
        Node root=new Node(rootValue);  //tworzymy nodea z ta wartoscia

        int rootIndex=0;
        for(int i=inStart; i<=inEnd; i++){  //wyszukiwanie indeksu korzenia w porzadku inorder
            if(inorder[i]==rootValue){
                rootIndex=i;
                break;
            }
        }

        int leftSubtreeSize=rootIndex-inStart;  //ustawiamy zakresy
        int rightSubtreeSize=inEnd-rootIndex;
        
        //rekurencyjne wywołanie dla lewego i prawego poddrzewa
        root.left=buildTreeFromPreInHelper(preorder, inorder, preStart + 1, preStart + leftSubtreeSize,
                inStart, rootIndex - 1);
        root.right=buildTreeFromPreInHelper(preorder, inorder, preStart + leftSubtreeSize + 1, preEnd,
                rootIndex+1, inEnd);

        return root;
    }

    void buildTreeFromPostIn(int[] postorder, int[] inorder){  //metoda do wywolania konstrukcji drzewa na podstawie porządku postorder i inorder
        root=buildTreeFromPostInHelper(postorder, inorder, postorder.length-1, 0, inorder.length-1);
    }

    Node buildTreeFromPostInHelper(int[] postorder, int[] inorder, int postEnd, int inStart, int inEnd){  //metoda do konstrukcji drzewa na podstawie porządku postorder i inorder
        if(postEnd<0 || inStart>inEnd){  //jesli indeks jest nieprawidlowy to zwracamy nulla  
            return null;
        }

        int rootValue=postorder[postEnd];  //ustawiamy roota na postEnd z postorderu
        Node root=new Node(rootValue);  //tworzymy nodea z ta wartoscia

        int rootIndex=-1;
        for(int i=inStart; i<=inEnd; i++){  //wyszukiwanie indeksu korzenia w porzadku inorder
            if(inorder[i]==rootValue){
                rootIndex=i;
                break;
            }
        }

        int rightSubtreeSize=inEnd-rootIndex;  //ustawiamy zakresy
        int leftSubtreeSize=postEnd-rightSubtreeSize-1;
        
        //rekurencyjne wywołanie dla lewego i prawego poddrzewa
        root.left=buildTreeFromPostInHelper(postorder, inorder, postEnd-rightSubtreeSize-1, inStart, rootIndex-1);
        root.right=buildTreeFromPostInHelper(postorder, inorder, postEnd-1, rootIndex+1, inEnd);

        return root;
    }
    
    void preorderTraversal(Node node, MyArrayList<Integer> result){  //metoda do przejscia po drzewie w porzadku preorder i zrzutowaniu tego do arraylisty
        if(node==null){  //jesli node jest nullem to konczymy
            return;
        }

        result.add(node.value);  //dodajemy wartosc nodea do listy 
        preorderTraversal(node.left, result);  //rekurencyjnie wywolujemy dla prawwego i lewego poddrzewa
        preorderTraversal(node.right, result);
    }

    void postorderTraversal(Node node, MyArrayList<Integer> result){  //metoda do przejscia po drzewie w porzadku postorder i zrzutowaniu tego do arraylisty
        if(node==null){  //jesli node jest nullem to konczymy
            return;
        }

        postorderTraversal(node.left, result);  //rekurencyjnie wywolujemy dla prawwego i lewego poddrzewa
        postorderTraversal(node.right, result);
        result.add(node.value);  //dodajemy wartosc nodea do listy 
    }

    void levelorderTraversal(Node node, MyArrayList<Integer> result){  //metoda do przejscia po drzewie w porzadku levelorder i zrzutowaniu tego do arraylisty
        if(node==null){  //jesli node jest nullem to konczymy
            return;
        }

        MyArrayList<Node> queue=new MyArrayList<>();  //tworzymy nowa arrayliste
        queue.add(node);  //dodajemy node

        while(!queue.isEmpty()){  //dopoki nasza lista nie jest pusta
            Node current=queue.get(0);  //bierzemy wartosc z 0 miejsca
            queue.remove(0);  //usuwamy je z naszej arraylisty
            result.add(current.value);  //dodajemy je do naszego wyniku

            if(current.left!=null){  //jesli nasz aktualny node ma lewe dziecko
                queue.add(current.left);  //dodajemy lewe dziecko
            }

            if(current.right!=null){  //jesli nasz aktualny node ma prawe dziecko
                queue.add(current.right);  //dodajemy prawe dziecko
            }
        }
    }
}

class Source{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int testCases=scanner.nextInt();  //sciagamy ilosc zestawow
        scanner.nextLine();  //sciagamy kolejna linie zeby sie nie psulo

        for(int t=1; t<=testCases; t++){  //przechodzimy przez wszystkie zestawy
            int n=scanner.nextInt();  //sciagamy ilosc elementow w drzewie
            scanner.nextLine();  //sciagamy kolejna linie zeby sie nie psulo

            String traversalType = scanner.nextLine().trim();  //sciagamy sposob reprezentacji
            int[] preorder = new int[n];
            String[] preorderInput = scanner.nextLine().split(" ");
            for(int i=0; i<n; i++){
                preorder[i]=Integer.parseInt(preorderInput[i]);
            }

            String inorderType=scanner.nextLine().trim();
            int[] inorder=new int[n];
            String[] inorderInput=scanner.nextLine().split(" ");
            for(int i=0; i<n; i++){
                inorder[i]=Integer.parseInt(inorderInput[i]);
            }

            BinaryTree binaryTree=new BinaryTree();  //tworzymy drzewo

            if(traversalType.equals("PREORDER")){  //jesli dostalismy preorder
                binaryTree.buildTreeFromPreIn(preorder, inorder);  //tworzymy drzewo z preorderu i inorderu
            } 
            else{  //w innym wypadku dostalismy postorder
                binaryTree.buildTreeFromPostIn(preorder, inorder);  //tworzymy drzewo z postorderu i inorderu
            }

            MyArrayList<Integer> preorderResult = new MyArrayList<>();  //tworzymy nowa arrayliste na ktorej wywolujemy funkcje budujaca preorder 
            binaryTree.preorderTraversal(binaryTree.root, preorderResult);

            MyArrayList<Integer> postorderResult = new MyArrayList<>();  //tworzymy nowa arrayliste na ktorej wywolujemy funkcje budujaca postorder 
            binaryTree.postorderTraversal(binaryTree.root, postorderResult);

            MyArrayList<Integer> levelorderResult = new MyArrayList<>();  //tworzymy nowa arrayliste na ktorej wywolujemy funkcje budujaca levelorder
            binaryTree.levelorderTraversal(binaryTree.root, levelorderResult);

            System.out.println("ZESTAW " + t);  //wypisanie numeracji danych
            if(traversalType.equals("PREORDER")){  //jesli w danych dostalismy preorder
                System.out.println("POSTORDER");  //to wypsiujemy w odpowiednim formacie wypisac reprezentacje drzewa w postorderze
                for(int i=0; i<postorderResult.size(); i++){  //petla do wypisania wyniku
                    System.out.print(postorderResult.get(i)+" ");
                }
                System.out.println();  //znak nowej lini
            } 
            else{  //w innym wypadku otrzymalismy postorder 
                System.out.println("PREORDER");  //wypisujemy wiec reprezentacje w preorderze
                for(int i=0; i<preorderResult.size(); i++){  //petla do wypisania wyniku
                    System.out.print(preorderResult.get(i)+" ");
                }
                System.out.println();  //znak nowej lini
            }

            System.out.println("LEVELORDER");  //wypisanie danych w levelorderze
            for(int i=0; i<levelorderResult.size(); i++){  //petla do wypisania danych
                System.out.print(levelorderResult.get(i)+" ");
            }
            System.out.println();  //znak nowej lini
        }

    }
}


/*

Przykladowe wejscia i wyjscia: 


 wejscie 1:
2
11
PREORDER
1 2 3 4 5 6 7 8 9 10 11
INORDER
11 10 9 8 7 6 5 4 3 2 1 
9
POSTORDER
1 2 3 4 5 6 7 8 9
INORDER
9 8 7 6 5 4 3 2 1

 wyjscie 1:
ZESTAW 1
POSTORDER
11 10 9 8 7 6 5 4 3 2 1 
LEVELORDER
1 2 3 4 5 6 7 8 9 10 11 
ZESTAW 2
PREORDER
9 8 7 6 5 4 3 2 1 
LEVELORDER
9 8 7 6 5 4 3 2 1 


 wejscie 2:
2
11
POSTORDER
8 4 9 5 2 6 10 11 7 3 1
INORDER
8 4 2 5 9 1 6 3 10 7 11
9
POSTORDER
4 1 2 7 5 8 6 3 9
INORDER
4 2 1 5 7 3 6 8 9


 wyjscie 2:
ZESTAW 1
PREORDER
1 2 4 8 5 9 3 6 7 10 11 
LEVELORDER
1 2 3 4 5 6 7 8 9 10 11 
ZESTAW 2
PREORDER
9 3 5 2 4 1 7 6 8 
LEVELORDER
9 3 5 6 2 7 8 4 1 


*/