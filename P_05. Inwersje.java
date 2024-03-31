//Dorian Rzasa grupa 3
import java.util.Scanner;

public class Source{
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();  //sciagamy ilosc testow
        
        for (int i=0; i<t; i++){  //przechodzimy przez wszystkie testy
            int n=scanner.nextInt();  //sciagamy dlugosc testu
            long[] arr=new long[n];  //tworzymy tablice dlugosci testu
            
            for (int j=0; j<n; j++){
                arr[j]=scanner.nextLong();  //wpisujemy test
            }
            
            long count=Inversion(arr, 0, arr.length-1);  //ilosc inwersji
            System.out.println(count);  //wypisujemy ilosc inwersji
        }
    }


    static long Inversion(long[] arr, int l, int r){
        if (l >= r){
            return 0;
        }
        int m=(l+r)/2;
        long count1=Inversion(arr, l, m);  //wywolujemy rekurencyjnie funkcje dla lewej czesci
        long count2=Inversion(arr, m + 1, r);  //wywolujemy rekurencyjnie funkcje dla prawej czesci
        
        
        long count=0; 
        int dl=r-m;  //dlugosc tablicy pomodniczej jako prawy indeks - srodkowy indeks
        long[] temp=new long[dl];  //tworzymy tablice pomocnicza nie wieksza niz dlugosc/2 +1

        int K=0;  //zmienna do ustawiania w tablicy pomocniczej
        for(int I=m+1; I<=r; I++){  //przepisujemy czesc orginalnej tablicy do pomocniczej
            temp[K]=arr[I];
            K++;
        }

        int i=m, j=dl-1, k=r;  //ustawiamy odpowiednie zmienne

        while(i>=l && j>=0){  //dopoki i jest nie mniejsze od lewego indeksu i j jest nie mniejsze od 0 
            
            if(arr[i]>temp[j]){  //jesli element w lewej czesci jest wiekszy niz element w prawej czesci
                arr[k--]=arr[i--];  //przenosimy element z lewej czesci do konca tablicy
                count+=j+1;  //zliczamy inwersje
            } 
            
            else{
                arr[k--]=temp[j--];  //przenosimy element z tablicy pomocniczej do konca tablicy
            }
        }

        while(j>=0){  //dopoki mozemy to przenosimy element z tablicy pomocniczej do konca tablicy
            arr[k--]=temp[j--];
        }



        return count1+count2+count;  //nasz wynik
    }  

}

/*
Przykladowe wejscia i wyjscia: 

 wejscie 1:
4
10
3421 132 12 3 1 5 123 543 1234 234
10
23487 4 123 64056 324 1 2 3 4 4
10
4231 1432234214 12 3 2 908 43 12 4 2
10
234423 4 3 1 765 43 423 9 0 777

 wyjscie 1:
20
27
32
24


wejscie 2:
4
10
21 36 1 124 54 12 43 2112 2 431
10
43212 123 435 786 867 354 32 8 0 9
10
8957 234 8760 231 231 4324 12 43 12 56
10
1342 132 6543 5 4 3 54 6 909 43

 wyjscie 2:
17
36
35
27

*/
