//Dorian Rzasa
#include <iostream>
#include <cstdlib>

using namespace std;

int main(){

    char Q;   // zmienna komend
    int w;  //ilosc wierszy
    cin>>w;
    int** tab = (int**)malloc(w*sizeof(int*));  //malloc na ilosc wierszy
    int* rozm = (int*)malloc(w*sizeof(int));  //malloc na tablice rozmiarow

for(int i=0;i<w;i++)  //petla do wpisywania rozmiarow wierszy, wartosci itd
{
    cin>>*(rozm+i);
    *(tab+i) = (int*)malloc(*(rozm+i)*sizeof(int));
    for(int j=0;j<*(rozm+i);j++)
    {
        cin>>*(*(tab+i)+j);
    }
}

while(cin>>Q && Q!='E')  //wpisywanie komend
{

    if(Q=='P') //wypisywanie stanu aktualnego
{
    for(int i=0;i<w;i++)
    {
        for(int j=0;j<*(rozm+i);j++)
    {
        cout<<*(*(tab+i)+j)<<" ";
    }
        cout<<endl;
    }
    cout<<endl;
}

    if(Q=='R') //usuniecie n-tego wiersza//
{
    int n;
    cin>>n;
    for(int i=n;i<w-1;i++) //glowna petla do usuwania
    {
        if(*(rozm+i)==*(rozm+i+1))  //jezeli rozmiary wierszy sa takie same to oszczedzamy czas nie robiac realloca
        {

        }
        else  //w innym wypadku reallocujemy wielkosc i+1 wiersza na i 
        {
    *(tab+i)=(int*)realloc(*(tab+i),*(rozm+i+1)*sizeof(int));  //reallocowanie wielosci i+1 na i
    *(rozm+i)=*(rozm+i+1);  //zmiana w tablicy rozmiarow
        }
    for(int j=0;j<*(rozm+i);j++)  //petla do przepisywania wiersza
    {
        *(*(tab+i)+j)=*(*(tab+i+1)+j);
    }
    }
    w--;  //zmieniamy zmienna odpowiedzialna za ilosc wierszy
    free(*(tab+w));  //zwalniamy ostatni wiersz
    tab=(int**)realloc(tab,w*sizeof(int*));  //reallocujemy tablice wierszy na rozmiar o 1 mniejszy od tego sprzed komendy
    rozm=(int*)realloc(rozm,w*sizeof(int));  //reallocujemy tablice rozmiarow na rozmiar o 1 mniejszy od tego sprzed komendy
}

    if(Q=='S') //zamiana miejscami wierszow n i m
{
    int n,m;
    cin>>n>>m;
    int* pomn = (int*)malloc(*(rozm+n)*sizeof(int));  //tworzymy zmienna tablice do ktorych bedziemy przepisywac odpowiednie wiersze co znacznie ulatwi komende
    int* pomm = (int*)malloc(*(rozm+m)*sizeof(int));
    for(int i=0;i<*(rozm+n);i++)  //przepisujemy zmienna do odpowiednich tablic pomocniczych
    {
    *(pomn+i)=*(*(tab+n)+i);
    }
    for(int i=0;i<*(rozm+m);i++)
    {
    *(pomm+i)=*(*(tab+m)+i);
    }
    *(tab+n)=(int*)realloc(*(tab+n),*(rozm+m)*sizeof(int));  //reallocujemy wielkosci odpowiednich tablic wierszy
    *(tab+m)=(int*)realloc(*(tab+m),*(rozm+n)*sizeof(int));

    for(int i=0;i<*(rozm+n);i++)  //przepisujemy wartosci odpowiednich wierszy do odpowiednich tablic wierszy
    {
    *(*(tab+m)+i)=*(pomn+i);
    }
    for(int i=0;i<*(rozm+m);i++)
    {
    *(*(tab+n)+i)=*(pomm+i);
    }
    free(pomn);  //zwalniamy tablice pomocnicze
    free(pomm);
    int x=*(rozm+n);  //zmienna do zmiany wartosci rozmiarow w tablicy rozmiarow
    *(rozm+n)=*(rozm+m);  //zmieniamy wartosci w tablicy rozmiarow na aktualne
    *(rozm+m)=x;

}

    if(Q=='D') //podwojenie wiersza n-tego
{
    int n;
    cin>>n;
    *(tab+n)=(int*)realloc(*(tab+n),2*(*(rozm+n))*sizeof(int));  //reallocujemy rozmiar n-tej tablicy wierszy aby zwiekszyl sie 2-krotnie
    for(int i=*(rozm+n);i<2*(*(rozm+n));i++)
    { 
        *(*(tab+n)+i)=*(*(tab+n)+i-*(rozm+n));  //przepisujemy podwojony juz wiersz
    }
    *(rozm+n)=2*(*(rozm+n));  //zmieniamy wartosc w tablicy rozmiarow na aktualna
}

 if(Q=='A') //dodanie do konca n- tego wiersza//
{
    int n;
    cin>>n;
    rozm=(int*)realloc(rozm,(w+1)*sizeof(int));  //reallocujemy tablice wierszy aby zrobic miejsce na koncu
    tab=(int**)realloc(tab,(w+1)*sizeof(int*));  //reallocujemy tablice rozmiarow aby zrobic miejsce na nowy rozmiar
    *(rozm+w)=*(rozm+n);  //przepisujemy ten rozmiar
    *(tab+w)=(int*)malloc((*(rozm+n))*sizeof(int));  //mallocujemy miejsce na nowy wiersz
    for(int i=0;i<*(rozm+n);i++)
    {
        *(*(tab+w)+i)=*(*(tab+n)+i);  //przepisujemy do ostatniego wiersza odpowiednie wartosci
    }
    w++;  //zwiekszamy zmienna odpowiedzialna za ilosc wierszy tak aby byla zgodna ze stanem aktualnym
}

    if(Q=='I') //wstawienie wiersza o nr. n do wiersza o nr. m na pozycji k
{
    int n,m,k;
    cin>>n>>m>>k;
    int q=*(rozm+n)+*(rozm+m);  //zmienna odpowiadajaca wielkosci "nowego" m-tego wiersza
    int* pom = (int*)malloc(q*sizeof(int));  //tworzymy tablice pomocnicza do ktorej przepiszemy wszystkie wartosci tak aby pod koniec tylko ja przepisac do tablicy wierszy
    int j=0;  //zmienna odpowiedzialna za pamietanie ostatniej przepisanej wartosci z wiersza m-tego (w zasadzie najmniejszej (liczac po indeksach) jeszcze nie przpisanej wartosci)
    for(int i=0;i<k;i++)
    {
    *(pom+i)=*(*(tab+m)+j);  //przepisywanie do tablicy pomocniczej czesci tablicy m-tej
    j++;
    }
    int p=0;  //zmienna do przepisywania n-tego wiersza
    for(int i=k;i<k+*(rozm+n);i++)
    {
    *(pom+i)=*(*(tab+n)+p);  //przepisywanie do tablicy pomocniczej tablicy n-tej
    p++;
    }
    for(int i=k+*(rozm+n);i<q;i++)
    {
    *(pom+i)=*(*(tab+m)+j);  //przepisywanie reszty tablicy m-tej do tablicy pomocniczej
    j++;
    }    
    *(tab+m)=(int*)realloc(*(tab+m),q*sizeof(int));  //reallocowanie wiersza m-tego aby pomiescil ekwiwalent sumy rozmiarow "starych" wierszy n-tego i m-tego
    for(int i=0;i<q;i++)
    {
        *(*(tab+m)+i)=*(pom+i);  //podstawienie wartosci z tablicy pomocniczej do tablicy wierszy
    }
    free(pom);  //zwolnienie tablicy pomocniczej
    *(rozm+m)=q;  //zmiana wartosci w tablicy rozmiarow na aktualny
    
for(int i=n;i<w-1;i++)  //komenda tozsama z usunieciem n-tego wiersza (poczatek)
    {
        if(*(rozm+i)==*(rozm+i+1))
        {

        }
        else
        {
    *(tab+i)=(int*)realloc(*(tab+i),*(rozm+i+1)*sizeof(int));
    *(rozm+i)=*(rozm+i+1);
        }
    for(int j=0;j<*(rozm+i);j++)
    {
        *(*(tab+i)+j)=*(*(tab+i+1)+j);
    }
    }
    w--;
    free(*(tab+w));
    tab=(int**)realloc(tab,w*sizeof(int*));
    rozm=(int*)realloc(rozm,w*sizeof(int));  //komenda tozsama z usunieciem n-tego wiersza (koniec)

}
}

for(int i=0;i<w;i++)  //zwalnianie kazdego wiersza z osobna
{
        free(*(tab+i));
}
free(tab);  //zwalnianie calej tablicy wierszy
free(rozm);  //zwalnianie tablicy rozmiarow

return 0;
}