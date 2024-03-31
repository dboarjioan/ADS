/*
przykladowe wejscie:

6
1 : 1 6
0 0 -1 0  -1 4
2 : 2 5
 -1 1 -1 -1 0
 1 -1 -1 -1 0
3 : 2 5
 0 -1 -1 3 3
 4 -2 -2 3 2
4 : 2 5
 0 -1 -1 4 0
 4 -2 -2 0 100
5 : 2 5
 -1 -2 -3 -1 -2
 -1 -1 -1 -1 -5
6 : 2 5
 9 9 9 -100 101 
 -10 9 9 -100 200
 
oczekiwane wyjscie:

1: n = 1 m = 6, ms = 4, mst = a[0..0][5..5]
2: n = 2 m = 5, ms = 1, mst = a[0..0][1..1]
3: n = 2 m = 5, ms = 11, mst = a[0..1][3..4]
4: n = 2 m = 5, ms = 104, mst = a[0..1][3..4]
5: n = 2 m = 5, ms = 0, mst is empty 
6: n = 2 m = 5, ms = 301, mst = a[0..1][4..4]

*/

import java.util.Scanner;

public class Source{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ilosc = scanner.nextInt();
        for(int j=0;j<ilosc;j++)
        {
            int T=j+1;  //zmienna porzadkowa do wypisywania numerow rozwizan
            int WE=scanner.nextInt();  //zmienna do sciagania niepotrzebnych informacji
            WE=0;
            int WF=0;
            scanner.next();
            int n = scanner.nextInt();  //n i m to wymiary tablicy
            int m = scanner.nextInt();
            int pierwsze_zerox=-1;  //w przypadku gdy tablica bedzie wypelniona w zlosliwy sposob
            int pierwsze_zeroy=-1;  //zapamietujemy wystapenie pierwszej sensownej liczby
            int[][] a = new int[n][m+5];  //tworzymy tablice lekko wieksza aby mogla przechowywac 
            for(int i=0;i<n;i++)          //inetresujace nas dane dotyczace odpowiedzi
            {
                for(int w=0;w<m;w++)
                {
                    int q=scanner.nextInt();  //wpisujemy elementy tablicy
                    a[i][w]=q;
                    if(q<0)  //jesli tablica ma elementy ujemne to je zliczamy aby pozniej
                    {        //natychmiast dac odpoweidz ze maksymalna podtablica jest pusta
                        WE++;
                    }
                    if(q<=0)  //jesli tablica zawiera jedynie elementy niedodatnie to taki 
                    {
                        WF++;
                    }
                    if(q==0 && pierwsze_zerox==-1)
                    {
                        pierwsze_zerox=i;
                        pierwsze_zeroy=w;
                    }
                }
            }
                if(WE==m*n)  //jesli tablica jest wypelniona jedynie liczbami niedodatnimi
                {            //to zwracamy jako wynik pierwsze napotkane zero
                System.out.println(T+": n = "+n+" m = "+m+", ms = "+a[0][m]+", mst is empty " );
                continue;
                }
                if(WF==m*n && WE!=0)  //jesli tablica jest wypelniona jedynie liczbami niedodatnimi
                {                     //to zwracamy jako wynik pierwsze napotkane zero
                System.out.println(T+": n = "+n+" m = "+m+", ms = "+a[0][m]+", mst = a["+pierwsze_zerox+".."+pierwsze_zerox+"]["+pierwsze_zeroy+".."+pierwsze_zeroy+"]");
                continue;
                }
            kadane2d(a,n,m);
            System.out.println(T+": n = "+n+" m = "+m+", ms = "+a[0][m]+", mst = a["+a[0][m+1]+".."+a[0][m+2]+"]["+a[0][m+3]+".."+a[0][m+4]+"]" );
        }
    }
    public static int[] kadane1d(int[] a, int n) {  //standardowy algorytm kadane
        int b1=0, e1=0, max1=0;
        int current=0, b2=0;
        for (int i=0; i<n; i++){
            current=current+a[i];
            if(current<=0){
                current=0;
                b2=i+1;
            }
            if(current>max1){
                max1=current;
                b1=b2;
                e1=i;
            }
            if(max1==current && ((i-b2)<(e1-b1))){  //jesli "aktualna" podtablica ma taki sam 
                b1=b2;  //rozmiar, to sprawdzamy czy moze nie ma mniejszego rozmiaru aby spelnic
                e1=i;   //warunek zwracanie najwiekszej tablicy o najmniejszych wymiarach
            }
        }
        a[n]=max1;
        a[n+1]=b1;
        a[n+2]=e1;
        return a;
    }
    public static int[][] kadane2d(int[][] a, int n, int m){  //algorytm kadane 2d
            int[] A = new int[m+3];  //tworzymy pomocnicza tablice
            int wp=0, kp=0, wk=0, kk=0;  //tworzymy zmienne do odpoweidnich wspolrzednych
            int maxi=0, suma=0, kol1=0, kol2=0;  //tworzymy zmienne do lokalnego i globalnego 
            for (int i=0; i<n; i++)  //maksimum oraz zmienne do kadane1d
            {
                for (int k=0; k<m; k++) {
                    A[k]=0;  //zerujemy tablice pomocnicza
                }

                for (int ik=i; ik<n; ik++)  //szukamy najwiekszej podtablicy dla konkretnych
                {                           //ograniczen kolumnowych
                    for (int k=0; k<m; k++){
                        A[k]=A[k]+a[ik][k];  //zliczamy do tablicy pomocniczej sumy "po wierszach"
                    }
                    kadane1d(A,m);
                    suma=A[m];
                    kol1=A[m+1];
                    kol2=A[m+2];
                    if (suma>maxi){  //jezeli lokalna suma jest wieksza od globalnej to sie
                        maxi=suma;   //je zamienia wraz z innymi parametrami
                        wp=i;
                        kp=kol1;
                        wk=ik;
                        kk=kol2;
                    }
                    if(suma==maxi && ik-i<wk-wp){  //jezeli lokalna suma jest taka sama
                       wp=i;              //ale ma lepsze wymiary to ja uistawiamy na globalna
                       wk=ik;
                    }
                }
            }
        a[0][m]=maxi;  //na odpowiednie indeksy tablicy wkladamy dodatkowe dane 
        a[0][m+1]=wp;  //bedace suma elementow maksymalnej podtablicy i punkty jej "zaczepienia"
        a[0][m+2]=wk;
        a[0][m+3]=kp;
        a[0][m+4]=kk;
        return a;
    }
}
