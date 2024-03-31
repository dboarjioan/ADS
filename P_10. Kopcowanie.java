import java.util.Scanner;

public class Source{
    static int size=0;  //aktualna ilosc wszystkich elementow
    static int ile_roznych=0;  //aktualna ilosc roznych elementow
    static int capacityN;  //pojemnosc N
    static int capacityP;  //pojemnosc P

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  //scanner

        int testCases=scanner.nextInt();  //ilosc testow

        for(int t=0; t<testCases; t++){  //petla do przejscia poprzez wszystkie przypadki
            size=0;  //zerujemy size i ilosc roznych
            ile_roznych=0;

            capacityN=scanner.nextInt();  //sciagamy pojemnosci N i P
            capacityP=scanner.nextInt();
            Node[] heap=new Node[capacityN+1];  //tworzymy tablice odpowiedniej dlugosci

            while(true){  //dopoki prawda, czyli dopoki nie zostala wykonana operacja sortowania
                String operation=scanner.next();  //sciagamy operacje
                if(operation.equals("i")){  //jesli operacja jest rowna i
                    int k=scanner.nextInt();  //sciagamy ilosc elementow ktore bedziemy chcieli dodac
                    for(int i=0; i<k; i++){  //petla do dodawania elementow
                        int element=scanner.nextInt();  //sciagamy kolejne elementy
                        insert(element, heap);  //wywolujemy funkcje dodajaca element 
                    }
                } 
                else if(operation.equals("g")){  //jesli operacja jest rowna g
                    int k=scanner.nextInt();  //sciagamy ilosc o jaka chcielibysmy zmniejszyc wystapienia maksymalnego elementu
                    if(ile_roznych>0){  //jesli cokolwiek jest na kopcu
                        int maxIlosc=Math.min(k, heap[0].ilosc);  //ustawiamy zmienna maxIlosc na to ile faktycznie tego elementu mozemy usunac
                        int maxWartosc=heap[0].wartosc;  //sciagamy maxWartosc jako element na poczatku kopca
                        heap[0].ilosc-=maxIlosc;  //zmniejszamy ilosc wystapien tego elementu o tyle ile usuniemy
                        size-=maxIlosc;  //zmniejszamy ilosc naszego size o ilosc usunietych
                        System.out.println(maxWartosc+" "+maxIlosc);  //wypisujemy zadane dane w danym formacie
                        if(heap[0].ilosc==0){  //jesli okazalo sie ze wyzerowalismy ilosc tego elementu
                            swap(ile_roznych-1, 0, heap);  //swapujemy pierwszy element na koniec kopca
                            ile_roznych--;  //zmniejszamy ilosc roznych wystapien
                        }
                        downheap(0, ile_roznych, heap);  //przesiewamy w dol pierwszy element
                    } 
                    else{  //jezeli na kopcu nie bylo zadnego elementu
                        System.out.println("0 0");  //to zgodnie z poleceniem wypisujemy dwa zera
                    }
                } 
                else if(operation.equals("s")){  //jesli operacja jest rowna s
                    sort(ile_roznych, heap);  //wywolujemy funkcje sort
                    break;  //oraz konczymy cala petle while
                }
            }
        }
    }

    private static void insert(int element, Node heap[]){  //funkcja dodajaca dany element do kopca, o ile to mozliwe
        if (size<capacityP){  //jesli nasz size jest mniejszy od pojemnosci P
            int index=findIndex(element, heap);  //sprawdzamy czy nasz element wystepuje juz na kopcu
            if(index==-1 && ile_roznych<capacityN){  //jesli nie wystapil, sprawdzamy dodatkowo czy nie mamy juz maksymalnej ilsoci roznych elementow na kopcu
                Node newNode=new Node(element, 1);  //tworzymy krotke o danej wartosci i jednym wystapieniu
                heap[ile_roznych] = newNode;  //dodajemy krotke na koniec kopca
                upheap(ile_roznych, heap);  //przesiewamy ta krotke w gore kopca aby zachowac warunek max kopca
                ile_roznych++;  //iterujemy ilsoc roznych elementow na kopcu oraz ilosc wszystkich elementow
                size++;
            } else if(index!=-1){  //jesli element wystapil juz na kopcu
                heap[index].ilosc++;  //iterujemy ilosc wystapien na odpowiedniej krotce
                upheap(index, heap);  //przesiewamy w gore 
                size++;  //iterujemy size
            }
        }
    }

    private static int findIndex(int element, Node heap[]){  //funkcja sprawdzajaca czy dany element jest na kopcu
        for(int i=0; i<ile_roznych; i++){  //petla do przejscia przez kopiec
            if(heap[i].wartosc == element){  //jesli wartosc bedzie sie zgadzala z szukana
                return i;  //zwracamy indeks na ktorym sie znajduje
            }
        }
        return -1;  //jesli nie znalezlismy, tzn ze nie wystepuje jeszcze i zwracamy -1
    }

    static void upheap(int k, Node heap[]){  //przesiewanie w gore
        //standardowy upheap z wykladu
        int i=(k-1)/2;  //indeks poprzednika
        Node tmp=heap[k];  //zmienna pomocnicza

        while(k>0 && compare(tmp, heap[i])){  //dopoki nasz indeks k jest wiekszy od zera
            heap[k]=heap[i];                  //oraz nasz tmp jest wiekszy niz element na indeksie i
            k=i;          //przenosimy wezel w dol
            i=(i-1)/2;    //przechodzimy do poprzednika
        }
     
        heap[k]=tmp;  //teraz element heap[k] jest na swoim miejscu
    }

    static void downheap(int k, int n, Node heap[]){  //przesiewanie w dol
        //standardowy downheap z wykladu
        int j;
        Node tmp=heap[k];

        while(k<n/2){
            j=2*k +1;  //indeks lewego nastepnika heap[k]

            if(j<n-1 && compare(heap[j+1], heap[j])){
                j++;
            }  //wybieramy wiekszy z nastepnikow
               //heap[j] jest wiekszym nastepnikiem
               
            if(compare(tmp, heap[j])){  //warunek kopca ok
                break;
            }
            //przesuwamy aktualny element do gory
            heap[k]=heap[j];
            k=j;
        }  

        heap[k]=tmp;
    }

    private static boolean compare(Node a, Node b){  //funkcja do porownywania elementow
        if(a.ilosc != b.ilosc){  //jesli ilosc wystapien jest rozna
            return a.ilosc>b.ilosc;  //zwracamy prawde jesli a wystepuje wiecej razy niz b
        } 
        else{
            return a.wartosc>b.wartosc;  //w innym wypadku zwracamy prawde jesli a jest wieksze niz b
        }
    }

    private static void swap(int i, int j, Node heap[]){  //funkcja do zamieniania miejscami 2 elementow
        Node temp = heap[i];  //tworzymy pomocniczego nodea
        heap[i] = heap[j];  //na indeks i wstawiamy to co jest na j
        heap[j] = temp;  //na indeks j wstawiamy to co wzielismy wczesniej do tempa
    }

    static void sort(int n, Node heap[]){  //funkcja sortujaca nasz kopiec i wypisujaca elementy w danym formacie
       //standardowe sortowanie metoda kopca z wykladu
        for(int k=(n-1)/2; k>=0; k--)  //petla do przesiewania w dol
        {
            downheap(k, n, heap);  //przesiewanie w dol
        }
        while(n>0){  //faza usuwania z kopca
            swap(0, n-1, heap);  //swapujemy element o indeksie 0 z n-1
            n--;  //dekrementujemy n
            downheap(0, n, heap);  //przesiewamy w dol
        }
        
        for(int i=0; i<ile_roznych; i++){  //petla do wypisywania posortowanego kopca
            System.out.print(heap[i].wartosc + " " + heap[i].ilosc + " ");  //wypisujemy dane w formacie: wartosc elementu ilosc jego wystapien
        }
        System.out.println();  //drukujemy endl
    }

    static class Node{  //klasa reprezentujaca krotki
        int wartosc;  //wartosc elementu na krotce
        int ilosc;  //ilosc elementu na krotce

        Node(int wartosc, int ilosc){  //konstruktor krotki przyjmujacy wartosc krotki oraz ilosc wystapien
            this.wartosc=wartosc;
            this.ilosc=ilosc;
        }
    }
}

/*

Przykladowe wejscia i wyjscia: 


 wejscie 1:
3
12 129
g 10 g 1 g 2 i 11 8 10 10 4 12 9 5 10 12 4 8 i 6 5 11 7 4 10 7 g 10 g 10 i 3 9 1 3 g 3 i 9 5 11 12 2 9 10 12 7 6 g 5 i 7 12 5 6 4 3 3 12 g 5 i 9 12 6 3 11 7 7 4 2 4 i 2 11 1 i 1 2 i 4 6 2 9 8 g 9 g 11 g 2 i 11 12 9 11 4 12 12 4 7 6 9 1 g 4 i 5 2 9 1 2 2 g 8 g 11 g 9 i 8 12 11 5 9 3 8 11 8 i 9 8 3 8 8 5 5 10 10 8 i 9 12 11 4 10 12 8 8 8 3 g 8 g 9 g 4 g 7 g 3 i 11 3 8 3 4 5 1 8 7 9 1 10 i 9 3 8 12 1 3 6 10 8 9 i 10 4 9 3 1 9 12 10 12 9 11 g 5 i 1 9 g 1 i 4 8 3 11 8 s 
12 178
i 4 9 3 9 6 i 7 7 1 5 2 11 2 10 g 12 i 1 8 i 10 2 11 2 2 11 3 2 8 6 2 g 7 g 9 g 5 i 4 5 5 11 3 g 9 i 10 9 1 3 3 1 5 2 5 9 1 i 1 8 i 2 4 11 i 10 5 2 12 6 12 1 6 1 11 6 g 2 g 9 g 10 i 10 5 10 1 6 3 9 8 12 7 8 i 10 9 11 10 9 7 6 10 7 2 10 g 5 i 6 9 1 2 10 1 6 i 2 8 11 g 5 i 8 10 1 5 11 5 8 1 2 g 3 g 5 g 11 i 11 9 5 7 12 1 6 10 2 6 6 2 i 3 8 3 8 g 12 i 10 5 12 11 1 10 3 4 11 12 2 g 11 g 6 g 10 i 5 9 7 3 5 9 i 3 10 11 10 i 3 2 5 10 i 6 9 3 2 12 11 1 g 3 g 11 g 12 i 8 10 10 12 4 3 10 10 11 g 7 i 4 5 9 3 8 g 6 g 1 g 9 g 5 g 9 i 12 7 10 11 12 8 2 11 2 3 9 2 4 i 2 7 2 i 10 12 2 12 11 1 3 6 3 9 10 g 4 i 9 6 7 9 5 10 9 5 5 2 g 10 i 2 5 11 i 5 1 8 4 8 4 s 
14 189
g 6 i 1 9 g 11 i 11 1 12 6 13 1 5 7 14 6 1 5 g 3 g 1 g 12 g 6 g 11 i 10 8 12 9 10 11 1 6 3 2 8 i 14 10 13 2 2 3 1 10 12 2 11 4 14 11 11 i 6 13 3 3 14 3 3 g 12 g 4 i 4 7 2 10 2 g 14 i 7 5 2 13 10 2 4 5 i 4 13 10 1 10 i 3 11 10 9 g 4 i 6 3 13 6 13 4 5 g 8 i 7 9 4 2 14 12 4 4 g 5 i 6 6 6 2 8 2 6 i 1 12 i 6 11 1 6 14 4 14 g 1 i 6 7 10 12 7 10 5 g 12 g 5 i 12 10 7 14 11 2 2 2 11 5 8 6 5 i 6 12 11 1 12 3 6 i 14 7 11 13 6 8 13 14 1 1 2 10 9 3 12 i 6 10 8 9 9 6 2 g 5 i 2 7 9 i 11 13 10 5 3 4 4 12 14 2 4 6 g 8 g 4 g 12 i 6 1 12 3 14 10 14 i 10 12 9 5 14 5 9 9 12 4 8 g 12 i 8 4 10 3 1 10 8 13 5 i 14 4 9 5 8 1 5 13 2 4 6 6 1 5 9 g 11 g 12 i 1 10 g 8 i 3 2 8 11 i 1 1 g 14 g 6 i 3 5 5 6 s 

 wyjscie 1:
0 0
0 0
0 0
10 4
4 3
12 2
9 3
12 4
7 5
11 4
6 2
4 4
2 7
12 4
9 4
8 8
5 7
3 4
11 4
10 3
8 5
3 1
5 1 7 2 11 2 4 4 6 4 10 4 8 5 12 6 9 7 1 8 3 8 
9 2
2 7
11 3
8 2
5 3
1 2
6 5
3 5
10 5
1 5
11 3
9 5
5 6
8 7
2 8
12 6
6 6
11 3
10 7
1 7
7 6
3 6
9 1
11 5
9 5
5 5
2 4
10 7
1 2 6 2 7 3 2 4 3 4 5 4 8 4 9 4 11 4 12 5 4 6 
0 0
9 1
1 3
6 1
5 2
14 1
13 1
3 6
11 4
2 6
10 4
13 6
4 5
6 1
12 6
10 5
6 5
2 8
14 4
9 7
5 9
1 11
8 9
10 8
4 9
6 6
1 1 8 1 10 1 6 3 2 5 9 5 13 5 3 6 5 6 7 7 11 7 12 7 14 7 


 wejscie 2:
4
13 166
g 6 i 6 6 10 5 3 5 11 i 10 6 3 8 2 4 11 6 11 3 4 i 13 9 2 5 3 7 7 8 5 1 1 9 12 7 g 7 g 1 i 6 1 9 13 11 4 1 i 6 7 9 5 9 3 7 g 9 i 2 1 1 i 2 13 1 g 11 i 10 1 7 1 4 9 9 9 9 11 6 g 10 g 13 i 2 1 8 g 7 g 5 i 2 8 4 g 13 g 4 g 1 i 3 5 11 9 i 3 12 4 9 i 5 1 10 11 7 7 i 3 13 4 1 i 2 11 5 i 8 5 7 9 8 7 12 7 9 i 11 4 3 1 7 13 9 2 3 2 1 11 i 13 3 3 5 5 10 13 12 3 7 2 4 10 11 i 9 1 12 9 11 4 7 12 2 9 g 12 i 12 7 6 11 11 11 13 2 13 8 11 12 12 g 12 g 12 g 8 i 5 11 9 5 13 6 i 12 8 4 3 11 1 3 2 9 11 12 11 11 g 13 i 11 9 5 12 5 13 1 3 6 10 11 6 i 8 6 11 2 5 11 3 11 11 g 5 i 2 2 10 s 
19 160
g 13 i 15 1 10 8 18 5 7 2 13 17 19 8 6 17 14 6 i 5 12 12 17 9 7 g 15 g 4 g 18 i 19 13 12 15 4 8 8 4 19 11 4 19 6 12 11 5 7 10 3 2 g 5 g 13 g 12 g 14 g 14 g 15 i 13 17 5 14 15 1 12 19 1 7 10 5 11 18 g 13 g 14 i 5 5 9 18 8 15 g 5 i 16 7 15 6 16 1 1 4 6 14 10 3 9 12 2 9 2 g 10 g 17 g 11 i 6 5 4 6 2 1 7 g 16 g 9 i 14 10 14 15 11 10 14 1 10 10 19 10 13 15 7 g 17 i 9 11 9 18 5 12 11 7 1 16 g 4 g 4 i 4 16 3 1 9 i 6 10 1 8 3 2 4 g 6 g 9 i 12 19 8 5 3 3 7 1 4 13 10 14 8 g 12 i 14 8 12 6 14 3 13 10 6 19 13 8 10 16 12 g 4 g 10 g 7 g 10 i 17 5 15 8 2 4 1 7 11 14 4 7 18 1 2 10 18 15 g 8 i 2 11 6 g 4 i 3 7 2 8 s 
14 184
g 12 g 11 g 6 g 7 i 7 1 2 7 5 2 4 11 g 8 i 12 9 7 3 12 1 4 5 4 9 12 13 2 i 11 6 6 7 8 11 8 11 7 2 14 7 g 8 g 1 g 11 i 5 13 14 5 6 10 i 4 2 6 10 1 g 13 g 3 g 2 i 12 9 4 8 1 1 6 9 5 6 8 5 11 i 7 14 5 12 3 10 11 14 i 12 4 6 13 12 3 13 6 4 10 13 14 9 g 9 i 13 9 9 10 3 11 1 6 10 10 10 6 4 2 g 10 g 13 i 4 14 11 6 3 g 5 g 5 i 2 5 12 i 13 5 12 12 14 14 6 14 6 13 3 3 4 5 i 6 5 5 7 5 4 14 i 3 3 8 7 i 10 4 5 14 3 11 14 3 2 10 10 g 4 g 10 i 14 6 1 11 4 9 2 11 11 14 5 13 12 2 1 g 13 g 14 i 3 10 10 12 g 2 i 6 11 9 8 6 3 8 i 3 10 5 7 i 12 6 11 7 8 3 3 5 4 13 3 4 1 g 14 i 9 8 9 14 11 10 8 5 11 10 g 5 i 8 6 9 9 10 14 7 1 9 i 6 10 8 8 5 12 1 g 5 i 2 1 1 s 
16 175
g 14 i 10 13 2 1 1 15 7 15 7 9 10 i 16 10 13 2 14 7 5 7 6 12 7 13 13 16 3 1 12 i 12 1 8 15 1 1 13 14 9 10 4 7 1 i 1 11 g 11 i 1 13 g 11 i 14 13 12 16 12 3 5 16 1 10 3 10 2 2 11 i 7 12 10 15 11 12 9 16 i 3 9 15 10 g 1 i 13 6 4 4 1 12 13 14 9 12 5 5 12 11 i 3 9 8 12 i 1 5 g 1 g 3 g 12 i 9 14 14 14 12 12 6 7 11 7 g 5 i 11 15 8 13 13 9 2 13 7 1 9 7 i 8 9 12 9 8 8 3 11 10 g 3 g 14 i 10 6 3 12 4 7 6 2 15 5 1 i 9 3 2 16 6 11 11 16 13 2 i 5 13 5 10 10 8 i 16 6 1 1 13 12 4 15 3 3 6 1 14 3 11 13 3 i 3 13 7 9 i 16 9 4 8 9 1 15 5 12 14 8 1 2 12 9 12 1 i 7 12 3 2 12 9 3 4 s


 wyjscie 2:
0 0
5 4
3 1
9 5
1 7
7 6
11 5
9 4
6 4
4 5
8 4
3 1
7 8
11 10
12 8
3 8
9 9
11 5
7 1 9 1 12 2 8 3 3 4 6 5 11 5 4 6 10 6 13 9 1 10 2 10 5 10 
0 0
17 3
12 2
8 2
19 3
7 3
6 3
4 3
13 2
12 2
5 4
11 3
18 3
1 5
15 4
10 4
2 5
9 4
14 5
10 4
7 4
1 5
8 4
3 6
12 4
10 5
6 5
19 4
4 6
11 4
3 1 6 1 10 1 11 1 12 1 17 1 9 2 1 3 14 3 18 3 2 4 13 4 15 4 16 4 5 5 7 5 8 6 
0 0
0 0
0 0
0 0
2 2
7 5
11 1
4 3
6 4
5 3
2 2
14 5
10 8
9 7
6 5
11 5
5 4
3 10
14 8
12 8
4 2
5 9
8 5
1 5
5 2 12 2 14 2 3 4 2 5 7 5 9 6 8 7 1 8 4 8 6 8 13 8 10 9 11 9 
0 0
1 7
13 6
10 1
12 1
12 3
12 6
7 5
9 3
10 7
10 2 16 6 4 7 7 7 5 8 6 8 8 8 14 8 11 9 15 9 1 10 2 10 12 10 13 10 3 12 9 12 


*/
