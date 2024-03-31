import java.util.Scanner;

public class Source {

    private static void wyswietlWynik(int pojemnosc, Stack<Integer> elementy){  //funkcja do wyswietlnania wyniku
        System.out.print(pojemnosc + " =");
        while (!elementy.isEmpty()){  //dopoki jest co popowac to popujemy
            System.out.print(" " + elementy.pop());
        }
        System.out.println();
    }

    private static Stack<Integer> rec_pakuj(int[] wagi, int pojemnosc, int i){  //rekurencyjna funkcja plecakowa zwracajaca stos
        if(pojemnosc==0){  //jezeli pojemnosc jest rowna zero to zwracamy
            return new Stack<Integer>();
        }
        if(i==wagi.length){  //jezeli miejsce rozpatrywania jest rowne dlugosci tablicy wag
            return null;     //to zwracamy nulla
        }
        if(wagi[i]>pojemnosc){  //jezeli nie mozemy wziac danego elementu to go nie bierzemy
        return rec_pakuj(wagi, pojemnosc, i+1);  //i rekurencyjnie wywolujemy funkje dla nastepnego miejsca poczatkowego 
        }
        Stack<Integer> wynik1 = rec_pakuj(wagi, pojemnosc-wagi[i], i+1);  //tworzymy stos bedacy wynikiem rekurencyjnego wywolania dla tablicy wag ze zmniejszona pojemnoscia o element i na wagach
        if(wynik1!=null){                                                 //poniewaz jak doszlismy do tego momentu tzn ze mozemy wziac dany element, zaczynamy rozpatrywanie od i+1 elementu
            wynik1.push(wagi[i]);  //pushujemy na stos wage na itym indeksie 
            return wynik1;
        }
        Stack<Integer> wynik2 = rec_pakuj(wagi, pojemnosc, i + 1);  //tworzymy stos bedacy rekurencyjnym wywolaniem funkcji dla poczatku ustawionego na i+1
        return wynik2;  
    }

    private static Stack<Integer> iter_pakuj(int[] wagi, int pojemnosc){  //iteracyjna funkcja plecakowa zwracajaca stos
    Stack<Integer> stos = new Stack<Integer>();  //stos na indeksy
    Stack<Integer> wynik = new Stack<Integer>();  //stos wynikowy
    int i=0;  //zmienna do przeszukiwania
    while(i<wagi.length || !stos.isEmpty()){  //dopoki przeszukiwanie ma sens
        if(pojemnosc==0){  //jezeli pojemnosc jest zerowa
            for(int j=stos.size()-1; j>=0; j--)  //przepisujemy indeksy na stos wynikowy
            {
                wynik.push(wagi[stos.elementAt(j)]);
            }
            return wynik;  //a nastepnie go zwracamy
        }
        if(i==wagi.length){  //jezeli doszlismy do samego konca przeszukiwania
            if(stos.isEmpty()){  //jezeli stos do indeksow jest pusty, oznacza to ze nic nie 
                return null;     //wzielismy i zwracamy nulla
            }
            i=stos.pop()+1;  //w innym wypadku sciagamy ostatni indeks oraz zmienna do przeszukiwania ustawiamy na nastepne miejsce po tym co odlozylismy
            pojemnosc+=wagi[i-1];  //pojemnosc zwiekszamy o wartosc tego co odlozylismy
        }
        else if(wagi[i]>pojemnosc){  //jezeli nie mozemy wziac danego elementu to go nie bierzemy
            i++;                     //oraz przechodzimy ze zmienna do nastpnego miejsca
        } 
        else{  //w innym wypadku bierzemy ten element
            stos.push(i);  //pushujemy dany indeks na stos
            pojemnosc-=wagi[i];  //pojemnosc odpowiednio zmniejszamy
            i++;  //iterujemy zmienna do przeszukiwania
        }
    }
    return null;  //jesli tu doszlismy, oznacza to ze nie zapelnilismy w zadany sposob plecaka i zwracamy nulla
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int zestawyDanych = input.nextInt();  //ilosc danych
        for(int zestaw = 1; zestaw <= zestawyDanych; zestaw++){  //przechodzimy przez dane
            int pojemnosc = input.nextInt();  //wczytujemy pojemnosc
            int k = input.nextInt();  //wczytujemy dlugosc
            int[] wagi = new int[k];  //tworzymy tablice wag o danej dlugosci
            for (int i=0; i<k; i++){  //uzupelniamy tablice wag 
                wagi[i] = input.nextInt();
            }
            Stack<Integer> wynik1 = rec_pakuj(wagi, pojemnosc, 0);  //tworzymy dwa stosy i wywolujemy na nich odpowiednie funkcje
            Stack<Integer> wynik2 = iter_pakuj(wagi, pojemnosc);

            if(wynik1 != null) {  //wypisujemy odpowiedzi
                System.out.print("REC: ");
                wyswietlWynik(pojemnosc, wynik1);  //wywolujemy funckje do wypisania odpowiedzi
            } else {
                System.out.println("BRAK");
            }
            if(wynik2 != null) {
                System.out.print("ITER: ");
                wyswietlWynik(pojemnosc, wynik2);  //wywolujemy funkcje do wypisania odpowiedzi
            } 
        }
    }
    
    static class Stack<T> {  //stack
    private Object[] array;  
    private int size;
    private int capacity;

    public Stack() {  // konstruktor stosu, tworzy stos danego obiektu o wielkosci maksymalnej 10, ustawia rozmiar na 0
        capacity = 10;
        array = new Object[capacity];
        size = 0;
    }

    public void push(T data) {  //funckja push, jezeli rozmiar jest rowny maksymalnemu rozmiarowi, to powiekszamy stos
        if (size == capacity) {
            resize();
        }
        array[size++] = data;  //dodajemy do stosu dany argument
    }

    public T pop() {  //funkcja sciagajaca ostatnia wartosc ze stosu, jesli stos jest pusty, zwracany jest null
        if (isEmpty()) {
            return null;
        }
        T data = (T) array[--size];
        array[size] = null;
        return data;
    }

    public T peek() {  //funkcja zwracajajaca null jesli stos jest pusty a w innym wypadku ostatni element ze stacku jednoczesnie go nie sciagajac
        if (isEmpty()) {
            return null;
        }
        return (T) array[size - 1];
    }
    
    public T elementAt(int j){  //funkcja zwracajaca wartosc stosu dla j-tego elementu na stosie
        if (isEmpty()) {
            return null;
        }
        else
        {
            return (T) array[j];
        }
    }

    public boolean isEmpty() {  //funkcja sprawdzajaca czy stos jest pusty
        return size == 0;
    }

    public int size() {  //funckaj zwracajaca wielkosc stacku
        return size;
    }

    private void resize() {  //funkcja zwiekszajaca maksymalna wielkosc stacku 
        capacity *= 2;  //zwiekszamy dwukrotnie wielkosc maksymalna stosu
        Object[] newArray = new Object[capacity];  //tworzymy nowy stos
        System.arraycopy(array, 0, newArray, 0, size);  //"przepisujemy" do nowego stosu stary
        array = newArray;  //podmieniamy 
    }
    }
    }
    
/*

Przykladowe wejscia i wyjscia: 

 wejscie 1:
2
21
6
21 10 11 5 6 100
70
2
35 35 10 

 wyjscie 1:
REC: 21 = 21
ITER: 21 = 21
REC: 70 = 35 35
ITER: 70 = 35 35


 wejscie 2:
3
1
6
1 1 1 1 1 1
2
7
1 3 1 3 1 3 2
100
10
1 2 3 4 5 6 7 8 9 10

 wyjscie 2:
REC: 1 = 1
ITER: 1 = 1
REC: 2 = 1 1
ITER: 2 = 1 1
BRAK

*/
