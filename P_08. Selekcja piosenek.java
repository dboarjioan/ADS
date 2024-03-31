import java.util.Scanner;

public class Source{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int z=scanner.nextInt();  //sciagamy ilosc testow

        for(int i=0; i<z; i++){  //przechodzimy przez ilosc testow
            int n=scanner.nextInt();  //sciagamy dlugosc tablicy
            int[] arr = new int[n];  //tworzymy tablice intow dlugosci n

            for(int j=0; j<n; j++){  //petla do wpisywania elementow tablicy
                arr[j]=scanner.nextInt();
            }

            int m=scanner.nextInt();  //sciagamy ilosc zapytan

            for(int k=0; k<m; k++){  //petla do obslugi zapytan
                int kj=scanner.nextInt();  //sciagamy szukany element

                if(kj<=n && kj>0){  //jezeli zapytanie ma sens, tzn indeks jest wiekszy od zera ale nie wiekszy od ilosci elementow w tablicy
                    int kthSmallest=findKthSmallest(arr, kj);  //wywolujemy funkcje znajdujaca dany element dla naszych danych
                    System.out.println(kj + " " + kthSmallest);  //wypisujemy wynik w odpowiednim formacie
                } 
                else{
                    System.out.println(kj + " brak");  //w innym wypadku wypisujemy brak odpowiedzi w odpowiednim formacie 
                }
            }
        }
    }


    /*
    
    Zlozonosc pamieciowa programu wynosi O(1) poniewaz nie tworzymy zadnych zmiennych zaleznych od ilosci danych, tablic itp.
    
    Zlozonosc czasowa programu wynosci O(n) poniewaz w kazdym kroku rekurencji dokonuje sie podzialu 
    tablicy na mniejsze czesci. W kazdej iteracji podzialu, liczba elementow do rozpatrzenia jest co
    najmniej o polowe mniejsza niz w poprzedniej iteracji. W rezultacie, wykonujemy podziaĹ dla coraz 
    mniejszych fragmentĂłw tablicy, a liczba iteracji wynosi co najwyĹźej proporcjonalnie do n.
    Dlatego algorytm dziaĹa w czasie O(n) w najgorszym przypadku.
    
    */
    private static int findKthSmallest(int[] arr, int k){  //funkcja znajdujaca k-ty element w danej tablicy
    return quickSelect(arr, 0, arr.length-1, k-1);  //wywoluje ona quickSelecta dla odpowiednich danych
    }

    private static int quickSelect(int[] arr, int left, int right, int k){  //rekurencyjny algorytm szukajacy k-tej liczby
    
    if(left==right){  //jesli lewy indeks rowna sie prawemu to go zwracamy
        return arr[left];
    }

    int pivotIndex=magicFivePartition(arr, left, right);  //ustawiamy pivot jako mediane median
    pivotIndex=partition(arr, left, right, pivotIndex);  //wywolujemy partitiona na tablicy uzywajac mediany median jako pivota

    if(k==pivotIndex){  //jesli nasz nowy pivot rowna sie k tzn ze tego szukalismy
        return arr[k];
    } 
    else if(k<pivotIndex){  //jesli pivot jest wiekszy tzn nasza kta liczba bedzie w lewej czesci 
        return quickSelect(arr, left, pivotIndex-1, k);
    } 
    else{  //w innym wypadku nasza liczba bedzie w prawej czesci
        return quickSelect(arr, pivotIndex+1, right, k);
    }
    }

    private static int magicFivePartition(int[] arr, int left, int right){
    int numGroups=(right-left+1)/5;  //ilosc podzialow na 5
    
    for(int i=0; i<numGroups; i++){
        int groupLeft=left+i*5;  //lewe ograniczenie na naszej grupie
        int groupRight=groupLeft+4;  //prawe ograniczenie na naszej grupie
        int medianIndex=findMedianIndex(arr, groupLeft, groupRight);  //wywolanie funkcji szukajacej mediany na naszej wydzielonej czesci
        swap(arr, medianIndex, left+i);  //swapujemy na naszej tablicy miejsca o medianie i lewemu indeksowi + 1
    }
   if(numGroups<=5){
        return findMedianIndex(arr, left, left + numGroups - 1);  //jeslii mamy mniej niz 5 grup, znajdujemy mediane calej tablicy
    } else {
        return magicFivePartition(arr, left, left + numGroups - 1);  //jesli mamy wiecej niż 5 grup, rekurencyjnie wykonujemy magicFivePartition na grupach median
    }
    }

    private static int findMedianIndex(int[] arr, int left, int right){  //funkcja do znajdowania mediany
    insertionSort(arr, left, right);  //wywolanie insertion sorta
    return left+(right-left)/2;  //zwracamy srodek czyli nasza mediane
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex){  //standardowa funkcja partition
    int pivot=arr[pivotIndex];  //natomiast pivot wybierany jest za pomoca algorytmu magicznych piatek
    swap(arr, pivotIndex, right);
    
    int i=left;
    for(int j=left; j<right; j++){
        if(arr[j]<pivot){
            swap(arr, i, j);
            i++;
        }
    }

    swap(arr, i, right);
    return i;
    }

private static void insertionSort(int[] arr, int left, int right){  //standardowy algorytm insertionSort
    for (int i=left+1; i<=right; i++){
        int key=arr[i];
        int j=i-1;

        while(j>=left && arr[j]>key){
            arr[j+1]=arr[j];
            j--;
        }
        
        arr[j+1]=key;
    }
}

private static void swap(int[] arr, int i, int j){  //funkcja swap 
    int temp=arr[i];
    arr[i]=arr[j];
    arr[j]=temp;
}

}

/*

Przykladowe wejscia i wyjscia: 


 wejscie 1:
3
5
1 1 1 1 1
3
1 2 3
5
9 8 6 1 2
5
2 5 1 3 4
10
1 2 1 2 1 2 1 2 1 2 
5
1 10 0 2 11

 wyjscie 1:
1 1
2 1
3 1
2 2
5 9
1 1
3 6
4 8
1 1
10 2
0 brak
2 1
11 brak


 wejscie 2:
3
2
1 100
3
1 2 9000
5
7 8 1 2 3
5
8 9 10000 0 7
10
1 1 5 5 2 2 3 3 1 9
5
1 90 0 2 13

 wyjscie 2:
1 1
2 100
9000 brak
8 brak
9 brak
10000 brak
0 brak
7 brak
1 1
90 brak
0 brak
2 1
13 brak

*/
