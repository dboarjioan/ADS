//Dorian Rzasa- grupa 3
import java.util.Scanner;

public class Source{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);  //scanner
        String lab = scanner.nextLine();  //sciagamy ilosc testow jako cala linijke zeby nie bylo bledow
        int z = Integer.parseInt(lab);  //zamieniamy cala linijke na inta
        for(int i=0; i<z; i++)  //przechodzimy przez wszystkie testy
        {
            String dane = scanner.nextLine();  //sciagamy piersza linijke testu zawierajaca dane
            String[] data = dane.split(",");  //splitujemy ja po przecinkach
            int wielkosc = Integer.parseInt(data[0]);  //pierwsza czesc to ilosc wierszy bez opisowego
            int poCzym = Integer.parseInt(data[1]);  //druga czesc to numer kolumny po ktorej bedziemy sortowac
            int jak = Integer.parseInt(data[2]);  //trzeci to sposob sortowania, zwykly lub reverse
            
            String w="";  //tworzymy stringa do sciagniecia danych
            
            for(int j=0; j<=wielkosc; j++)  //tablica do sciagniecia danych
            {
                w=w+scanner.nextLine()+"\n";  //sciagamy dane po linijce i dodajemy znak nastepnej linii
            }
            
            String[] lines = w.split("\n");  //splitujemy nasze dane po znakach nowej linii
            
            String[][] dataArray = new String[lines.length][];  //tworzymy glowna tablice
            
            for (int a=0; a<lines.length; a++){  //odpowiednio uzupelniamy nasza tablice
             String[] values = lines[a].split(",");
             dataArray[a] = values;
            }
            
   for(String[] row : dataArray){  //przesuniecie calej tablicy o jeden do tyl i wstawienie "posortujacej" kolumny na poczatek
        String temp = row[poCzym-1];  //robimy tempa z poCzym-1 czescia
        for (int a=poCzym-1; a>0; a--){  //przesuwamy reszte
            row[a] = row[a - 1];
        }
        row[0]=temp;  //wstawiamy tempa na 1 miejsce
    }
    
    if(jak==1){  //tryb normalnego sortowania
        quickSort(dataArray, 1, dataArray.length-1);  //wywolanie quick sorta 
    }
    else  //tryb reverse sortowania
    {
         reverseQuicksort(dataArray, 1, dataArray.length-1);  //wywolanie reverse quicksorta
    }

    for (String[] row : dataArray){  //wypisywanie calej tablicy w odpowiednim formacie
        for (int e=0; e<row.length-1; e++){
            System.out.print(row[e] + ",");
        }
        System.out.println(row[row.length - 1]);
    }
                System.out.println();
    }
    } 

    /*
    uzasadnienie sredniej zlozonosci algorytmu:
    zlozonosc pamieciowa: Wynosi O(1) poniewaz niezaleznie od wielkosci danych wykorzystujemy taka
    sama ilosc zmiennych, czyli oczywiscie nie tworzymy zadnych tablic zaleznych od wielkosci danych.
    
    srednia zlozonosc czasowa: 
    zlozonosc czasowa: Wynosi O(n*logn) poniewaz jest to symulacja wywolania stosu systemowego na 
    algorytmie quicksort ktory dziala w czasie O(n*logn)
    
    */
    
   public static void quickSort(String[][] arr, int low, int high){
    int l=low;  //ustawiamy zmienna l na poczatek 
    int r=high;  //ustawiamy zmienna r na koniec
    int q, i=0;  //zmienne porzadkujaco przeszukujace, pivot
    int tmpr=r;  //ala prawa granica
    while(true){
        i--;  //dekrementujemy zmienna i
        while(l<tmpr){  //dopoki nasza zmienna l jest mniejsza od tmpr
            
            if(tmpr-l+1<=5)  //jesli nasze dane sa nie wieksze niz 5 to wywolujemy selectionsorta
            {
                selectionSort(arr,l,tmpr);
            }
            
            q=partition(arr, l, tmpr);  //ustawiamy odpowiednio naszego pivota

            if(arr[tmpr][0].startsWith("-")){  //jezeli na tmpr miejscu bedzie znak "-" to go usuwamy
                arr[tmpr][0]=arr[tmpr][0].substring(1);  //wczesniej to byla granica na ktorej sortowalismy
            } 
            else{
                arr[tmpr][0]="-"+arr[tmpr][0];  //w innym przypadku go dodajemy (nie bylo tam partitiona)
            }

            tmpr=q-1;  //ustawiamy tmpr na pivot -1
            ++i;  //iterujemy zmienna i
        }
        if(i<0)  //jezeli i jest mniejsze od zera to wychodzimy z whilea
          { 
              break;
          }
        l++;  //iterujemy zmienna l
        tmpr=findNextR(arr, l, r);  //tmpr ustawiamy na nastepne wyrazenie z "-" lub na ostatnie wyrazenie
        
        if(arr[tmpr][0].startsWith("-")){  //jezeli jest przy wyrazeniu znak "-" to go usuwamy
            arr[tmpr][0]=arr[tmpr][0].substring(1);
        } 
        else{  //w innym wypadku go dodajemy
            arr[tmpr][0]="-"+arr[tmpr][0];
        }
    }
    
    for(int p=0; p<=high; p++){  //funkcja usuwajaca ewentualne znaki "-" w sortowanych danych
        if (arr[p][0].startsWith("-")) {
            arr[p][0] = arr[p][0].substring(1);
        }
    }
    
    }



private static int findNextR(String[][] arr, int low, int high){  //funkcja szukajaca znaku "-" w sortowanych danych
    for(int i=low; i<=high; ++i){
        if (isNegatedArray(arr[i]))
            return i;
    }
    return high;
}

private static boolean isNegatedArray(String[] arr){  //funkcja sprawdzajaca czy ustawilismy minus
    return arr[0].startsWith("-");
}
    
    public static void selectionSort(String[][] arr, int low, int high){  //standardowy algorytm selectionsort
    for (int i=low; i<high; i++){  //przechodzimy przez tablice
        int minIndex=i;  //indeks najmniejszej wartosci
        for(int j=i+1; j <=high; j++){  //szukamy najmniejszej wartosci w petli
            if(compare(arr[j][0], arr[minIndex][0])<0){  //jesli jest mniejsze cos to aktualizujemy minIndex
                minIndex = j;  
            }
        }
        swap(arr, i, minIndex);  //swapujemy
    }
    }

    private static int partition(String[][] arr, int low, int high){  //partition do quicksorta
    String pivot=arr[high][0];  //ustawiamy pivota
    int i=low-1;  //ustawiamy zmienna do porzadkowania
    for(int j=low; j<high; j++){  //przechodzimy przez tablice
        if(compare(arr[j][0], pivot)<=0){  //jezeli cos bedzie mniejsze od pivota to 
            i++;                           //iterujemy zmienna do porzadkowania
            swap(arr, i, j);               //oraz wykonujemy swapa
        }
    }
    swap(arr, i+1, high);  //swapujemy
    return i+1;  //zwracamy i+1
    }

    public static void reverseSelectionSort(String[][] arr, int low, int high){  //odwrocony algorytm selectionsorta  
    for(int i=low; i<high; i++){  //analogicznie wszystko ale w druga strone
        int maxIndex = i;
        for(int j=i+1; j<=high; j++){
            if(compare(arr[j][0], arr[maxIndex][0])>0){
                maxIndex = j;
            }
        }
        swap(arr, i, maxIndex);
    }
    }
    
   public static void reverseQuicksort(String[][] arr, int low, int high){  //funkcja analogiczna do quicksorta z ta roznica ze wywolywany jest reverse partition co odpowiada za odwrotna kolejnosc sortowania
    int l=low;  //ustawiamy zmienna l na poczatek 
    int r=high;  //ustawiamy zmienna r na koniec
    int q, i=0;  //zmienne porzadkujaco przeszukujace, pivot
    int tmpr=r; 
    while(true){
        i--;  //dekrementujemy zmienna i
        while(l<tmpr){  //dopoki nasza zmienna l jest mniejsza od tmpr
            
            if(tmpr-l+1<=5)  //jesli nasze dane sa nie wieksze niz 5 to wywolujemy selectionsorta
            {
                reverseSelectionSort(arr,l,tmpr);
            }
            
            q=reversePartition(arr, l, tmpr);  //ustawiamy odpowiednio naszego pivota

            if(arr[tmpr][0].startsWith("-")){  //jezeli na tmpr miejscu bedzie znak "-" to go usuwamy
                arr[tmpr][0]=arr[tmpr][0].substring(1);
            } 
            else{
                arr[tmpr][0]="-"+arr[tmpr][0];  //w innym przypadku go dodajemy
            }

            tmpr=q-1;  //ustawiamy tmpr na pivot -1
            ++i;  //iterujemy zmienna i
        }
        if(i<0)  //jezeli i jest mniejsze od zera to wychodzimy z whilea
          { 
              break;
          }
        l++;  //iterujemy zmienna l
        tmpr=findNextR(arr, l, r);  //tmpr ustawiamy na nastepne wyrazenie z "-" lub na ostatnie wyrazenie
        
        if(arr[tmpr][0].startsWith("-")){  //jezeli jest przy wyrazeniu znak "-" to go usuwamy
            arr[tmpr][0]=arr[tmpr][0].substring(1);
        } 
        else{  //w innym wypadku go dodajemy
            arr[tmpr][0]="-"+arr[tmpr][0];
        }
    }
    
    for(int p=0; p<=high; p++){  //funkcja usuwajaca ewentualne znaki "-" w sortowanych danych
        if (arr[p][0].startsWith("-")) {
            arr[p][0] = arr[p][0].substring(1);
        }
    }
    
    }

private static void swap(String[][] arr, int i, int j){  //funkcja swap do zamieniania odpowiednich czesci w tablicy 2d
    String[] temp=arr[i];  //tworzymy tempa
    arr[i]=arr[j];  //dokonujemy zamiany
    arr[j]=temp;
}

private static int compare(String a, String b){  //funkcja do porownywania stringow
    if (isNumeric(a) && isNumeric(b)){  //jesli sa to liczby
        int numA=Integer.parseInt(a);  //parsujemy obydwie czesci
        int numB=Integer.parseInt(b);
        return Integer.compare(numA, numB);  //zwracamy porownanie standardowe
    } 
    else 
    {
        return a.compareTo(b);  //w innym wypadku compare leksykograficzne z ascii
    }
}

private static boolean isNumeric(String str){  //funckja sprawdzajaca czy string "jest liczba"
    if((str.charAt(0)=='-' && (str.charAt(1)=='0' || str.charAt(1)=='1' || str.charAt(1)=='2' || str.charAt(1)=='3' || str.charAt(1)=='4' || str.charAt(1)=='5' || str.charAt(1)=='6' ||str.charAt(1)=='7' ||str.charAt(1)=='8' || str.charAt(1)=='9')) || (str.charAt(0)=='0' || str.charAt(0)=='1' || str.charAt(0)=='2' || str.charAt(0)=='3' || str.charAt(0)=='4' || str.charAt(0)=='5' || str.charAt(0)=='6' ||str.charAt(0)=='7' ||str.charAt(0)=='8' || str.charAt(0)=='9'))
{
    return true;
}
return false;
}

    private static int reversePartition(String[][] arr, int low, int high){  //partition do quicksorta
    String pivot=arr[high][0];  //ustawiamy pivota
    int i=low-1;  //ustawiamy zmienna do porzadkowania
    for(int j=low; j<high; j++){  //przechodzimy przez tablice
        if(compare(arr[j][0], pivot)>=0){  //jezeli cos bedzie mniejsze od pivota to 
            i++;                           //iterujemy zmienna do porzadkowania
            swap(arr, i, j);               //oraz wykonujemy swapa
        }
    }
    swap(arr, i+1, high);  //swapujemy
    return i+1;  //zwracamy i+1
    }
}

/*

Przykladowe wejscia i wyjscia: 


 wejscie 1:
3
3,1,1
marka,rok,moc,cena
apple,2020,21,12000
samsung,2021,17,10000
lenovo,2022,20,9800
3,2,-1
marka,rok,moc,cena
apple,2020,21,12000
samsung,2021,17,10000
lenovo,2022,20,9800
3,3,1
marka,rok,moc,cena
apple,2020,21,12000
samsung,2021,17,10000
lenovo,2022,20,9800

 wyjscie 1:
marka,rok,moc,cena
apple,2020,21,12000
lenovo,2022,20,9800
samsung,2021,17,10000

rok,marka,moc,cena
2022,lenovo,20,9800
2021,samsung,17,10000
2020,apple,21,12000

moc,marka,rok,cena
17,samsung,2021,10000
20,lenovo,2022,9800
21,apple,2020,12000


 wejscie 2:
4
3,1,1
kraj,stolica,mieszkancylacznie,mieszkancywstolicy
polska,warszawa,38000000,2000000
turcja,ankara,85000000,5500000
islandia,rejkiawik,400000,135000
3,2,-1
kraj,stolica,mieszkancylacznie,mieszkancywstolicy
polska,warszawa,38000000,2000000
turcja,ankara,85000000,5500000
islandia,rejkiawik,400000,135000
3,3,1
kraj,stolica,mieszkancylacznie,mieszkancywstolicy
polska,warszawa,38000000,2000000
turcja,ankara,85000000,5500000
islandia,rejkiawik,400000,135000
3,4,-1
kraj,stolica,mieszkancylacznie,mieszkancywstolicy
polska,warszawa,38000000,2000000
turcja,ankara,85000000,5500000
islandia,rejkiawik,400000,135000

 wyjscie 2:
kraj,stolica,mieszkancylacznie,mieszkancywstolicy
islandia,rejkiawik,400000,135000
polska,warszawa,38000000,2000000
turcja,ankara,85000000,5500000

stolica,kraj,mieszkancylacznie,mieszkancywstolicy
warszawa,polska,38000000,2000000
rejkiawik,islandia,400000,135000
ankara,turcja,85000000,5500000

mieszkancylacznie,kraj,stolica,mieszkancywstolicy
400000,islandia,rejkiawik,135000
38000000,polska,warszawa,2000000
85000000,turcja,ankara,5500000

mieszkancywstolicy,kraj,stolica,mieszkancylacznie
5500000,turcja,ankara,85000000
2000000,polska,warszawa,38000000
135000,islandia,rejkiawik,400000


*/