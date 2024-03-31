//Dorian Rzasa grupa 3
/*
przykladowe wejscie:
5
12
1 2 3 3 4 8 10 12 12 12 13 200
12
 1 200
 2 2
 3 6
 2 1
 -1 10
 -100 100000
 4 6
 4 3
 -1 9
 1 1
 4 4
 0 900
10
0 0 0 0 0 0 0 0 0 0
7
1 2
0 1
1 1
0 0
2 2
3 1
-1 -1
1 
0
5
0 1
-1 0
-1 1
0 0 
-100 -10
5 
-1 -1 -1 0 0
3
0 0
-1 -1
-1 -5
2
-100 100
3
-100 100
0 0 
100 -100

wyjscie dla przykladowego wejscia:
12
1
3
0
7
12
1
0
6
1
1
12
9
0
10
0
10
0
0
0
1
1
1
1
1
0
1
2
3
0
2
2
0
0
2
*/

import java.util.Scanner;
public class Source {

public static int []a;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int ilosc = scanner.nextInt();  //wczytujemy ilosc danych do sprawdzenia
        for(int i=0;i<ilosc;i++)
        {
            int o=1;  //ilsoc roznych wartosci w danym zestawie danych
            int c=0;  //zmienna do sprwadzania czy wartosci w danych sie powtarzaja
            int dlugosc = scanner.nextInt();  //dlugosc tablicy zmiennych
            a = new int[dlugosc];  //tablica zmiennych
            for(int j=0;j<dlugosc;j++)
            {     
                a[j]=scanner.nextInt();  //wczytujemy po kolei elementy tablicy
            }
            int ilosc_testow = scanner.nextInt ();
            for(int p=0;p<ilosc_testow;p++)  //przechodzimy przez ilosc testow
            {
                 int poczatek = scanner.nextInt();  
                 int koniec = scanner.nextInt();
                 if(koniec>=poczatek){  //jezeli koniec jest niemniejszy niz poczatek to zwracamy
//roznice miedzy osatnim nie wiekszym elementem od "koniec" i pierwszym nie mniejszym niz "poczatek"
                  System.out.println(SearchBinFirst(koniec+1)-SearchBinFirst(poczatek));
                 }
                 else  //jezeli poczatek jest wiekszy od konca to zbior tablic o takich wartosciach
                 {     //musi byc pusty
                  System.out.println(0);
                 }
            }
     System.out.println(Powtorzenia());  //wypisujemy ilosc roznych wartosci w danych
        }
        }
        
   public static int SearchBinFirst(int x)  //funkcja szukajaca indeksu pierwszej nie mniejszej od zadanej wartosci
   {
       int mid;
       int low=0;
       int high=a.length;

       while(low<high){  //dopoki jest co przeszukiwac
       
           mid=(high+low)/2;  //"srodkowe miejsce" ustawiamy jako low + delta ograniczen

           if(x<=a[mid]){  //jezeli nasza szukana wartosc jest nie wieksza niz tablica z indeksem mid
               high=mid;   //to nowym ogrniczeniem gornym bedzie wartosc mid
           }

           else{  //w innym wypadku ograniczenie dolne zwiekszamy na mid+1
               low=mid+1;
           }
       }

       return low;
   }
   
    public static int Powtorzenia()
    {       int o=1;  //ilsoc roznych wartosci w danym zestawie danych
            int c=0;  //zmienna do sprwadzania czy wartosci w danych sie powtarzaja
            
         for(int j=0;j<a.length;j++)
            {     
                 
                if(a[j]!=c && j>0)  //jesli element tablicy jest rozny od ostatniej "nowej" wartosci i j>0
                {                   //to iterujemy ilosc roznych wartosci i zmieniamy ostatnia "nowa" wartosc
                    o++;            //na ta ktora jest w tablicy o j-tym indeksie
                    c=a[j];
                }
                else if(j==0)  //pierwszy element zawsze wpisujemy
                {
                    c=a[0];
                }
            }
            return o;
    }
    }