#include <iostream>
#include "Uczestnik.h"

using namespace std;

 class Wodzirej{
public:
int kto_ma_chusteczke=0;
int trwanie_zabawy=0;
int w=0;
int licznik_identyfikatorow=0;
int licznik=0;

Uczestnik* okragla_tablica = (Uczestnik*)malloc(1*sizeof(Uczestnik));  //malloc na tablice rozmiarow
unsigned int* okragla_tablica_identyfikatorow = (unsigned int*)malloc(1*sizeof(unsigned int));  //malloc na tablice rozmiarow

Wodzirej()
{
Uczestnik wodzirej (Uczestnik::W);
*(okragla_tablica+0)=wodzirej;
*(okragla_tablica_identyfikatorow+0)=0;
licznik++;
licznik_identyfikatorow=0;
}


unsigned int dolacz(Uczestnik* osoba)
{
  if(osoba==nullptr)
{
  return 0;
}
  if(osoba->plec==2)
{
  return 0;
}
for(int i=0;i<licznik;i++)
{
if(osoba==&(*(okragla_tablica+i)))
{
return 0;
}
}
licznik++;
licznik_identyfikatorow++;
Uczestnik* okragla_tablica_pomocnicza = (Uczestnik*)malloc(licznik*sizeof(Uczestnik));  //malloc na tablice 
unsigned int* okragla_tablica_identyfikatorow_pomocnicza = (unsigned int*)malloc(licznik*sizeof(unsigned int));  //malloc na tablice 
*(okragla_tablica_pomocnicza+0)=*(okragla_tablica+0);
*(okragla_tablica_identyfikatorow_pomocnicza+0)=*(okragla_tablica_identyfikatorow+0);
*(okragla_tablica_pomocnicza+1)=*osoba;
*(okragla_tablica_identyfikatorow_pomocnicza+1)=licznik_identyfikatorow;
int j=1;
for(int i=2;i<licznik;i++)
{
*(okragla_tablica_pomocnicza+i)=*(okragla_tablica+j);
*(okragla_tablica_identyfikatorow_pomocnicza+i)=*(okragla_tablica_identyfikatorow+j);
j++;
}
okragla_tablica=(Uczestnik*)realloc(okragla_tablica,licznik*sizeof(Uczestnik));
okragla_tablica_identyfikatorow=(unsigned int*)realloc(okragla_tablica_identyfikatorow,licznik*sizeof(unsigned int));
for(int i=0;i<licznik;i++)
{
*(okragla_tablica+i)=*(okragla_tablica_pomocnicza+i);
*(okragla_tablica_identyfikatorow+i)=*(okragla_tablica_identyfikatorow_pomocnicza+i);
}
free(okragla_tablica_identyfikatorow_pomocnicza); 
free(okragla_tablica_pomocnicza); 
return licznik_identyfikatorow;
}

unsigned int dolacz(Uczestnik* osoba, unsigned int pozycja)
{
  if(osoba==nullptr)
{
  return 0;
}
  if(osoba->plec==2)
{
  return 0;
}
if(pozycja>=licznik)
{
    return 0;
}
for(int i=0;i<licznik;i++)
{
if(osoba==&(*(okragla_tablica+i)))
{
return 0;
}
}
licznik++;
licznik_identyfikatorow++;
Uczestnik* okragla_tablica_pomocnicza = (Uczestnik*)malloc(licznik*sizeof(Uczestnik));  //malloc na tablice 
unsigned int* okragla_tablica_identyfikatorow_pomocnicza = (unsigned int*)malloc(licznik*sizeof(unsigned int));  //malloc na tablice 
for(int i=0;i<(licznik-pozycja-1);i++)
{
*(okragla_tablica_pomocnicza+i)=*(okragla_tablica+i);
*(okragla_tablica_identyfikatorow_pomocnicza+i)=*(okragla_tablica_identyfikatorow+i);
}
*(okragla_tablica_pomocnicza+licznik-pozycja-1)=*osoba;
*(okragla_tablica_identyfikatorow_pomocnicza+licznik-pozycja-1)=licznik_identyfikatorow;
for(int i=licznik-pozycja;i<licznik;i++)
{
*(okragla_tablica_pomocnicza+i)=*(okragla_tablica+i-1);
*(okragla_tablica_identyfikatorow_pomocnicza+i)=*(okragla_tablica_identyfikatorow+i-1);
}
okragla_tablica=(Uczestnik*)realloc(okragla_tablica,licznik*sizeof(Uczestnik));
okragla_tablica_identyfikatorow=(unsigned int*)realloc(okragla_tablica_identyfikatorow,licznik*sizeof(unsigned int));
for(int i=0;i<licznik;i++)
{
*(okragla_tablica+i)=*(okragla_tablica_pomocnicza+i);
*(okragla_tablica_identyfikatorow+i)=*(okragla_tablica_identyfikatorow_pomocnicza+i);
}
free(okragla_tablica_identyfikatorow_pomocnicza); 
free(okragla_tablica_pomocnicza);
return licznik_identyfikatorow;
}


bool rozpocznij()
{
if(trwanie_zabawy==1)
{
    return false;
}
if(licznik==1)
{
    return false;
}
int licznik_samcow=0;
int licznik_samic=0;
for(int i=1;i<licznik;i++)
{
    if(((okragla_tablica+i)->plec)==0)
    {
        licznik_samic++;
    }
    if(((okragla_tablica+i)->plec)==1)
    {
        licznik_samcow++;
    }
    if(licznik_samcow!=0 && licznik_samic!=0)
    {
       kto_ma_chusteczke=*(okragla_tablica_identyfikatorow+1);
       trwanie_zabawy=1;
       return true; 
    }
}
return false;
}


bool zakoncz()
{
if(trwanie_zabawy==0)
{
    return false;
}
if(trwanie_zabawy==1)
{
    trwanie_zabawy=0;
    kto_ma_chusteczke=0;
    return true;
}
}


//bool zrezygnuj(Uczestnik* osoba)
//{
    /*
  if(osoba==nullptr)
{
  return 0;
}
  if(osoba->plec==2)
{
  return 0;
}
int osobnik;
int zmienna=0;
for(int i=0;i<licznik;i++)
{
if(osoba!=&(*(okragla_tablica+i)))
{
zmienna++;
}
else
{
osobnik=i;    
}
}
if(zmienna==licznik)
{
   //return false; 
}

if(*(okragla_tablica_identyfikatorow+osobnik)==kto_ma_chusteczke)
{
    return false;
}
licznik--;
osobnik =1;
Uczestnik* okragla_tablica_pomocnicza = (Uczestnik*)malloc(licznik*sizeof(Uczestnik));  //malloc na tablice 
unsigned int* okragla_tablica_identyfikatorow_pomocnicza = (unsigned int*)malloc(licznik*sizeof(unsigned int));  //malloc na tablice 
for(int i=0;i<osobnik;i++)
{
    *(okragla_tablica_pomocnicza+i)=*(okragla_tablica+i);
    *(okragla_tablica_identyfikatorow_pomocnicza+i)=*(okragla_tablica_identyfikatorow+i);
}
for(int i=osobnik;i<licznik;i++)
{
    *(okragla_tablica_pomocnicza+i)=*(okragla_tablica+i+1);
    *(okragla_tablica_identyfikatorow_pomocnicza+i)=*(okragla_tablica_identyfikatorow+i+1);
}
okragla_tablica=(Uczestnik*)realloc(okragla_tablica,licznik*sizeof(Uczestnik));
okragla_tablica_identyfikatorow=(unsigned int*)realloc(okragla_tablica_identyfikatorow,licznik*sizeof(unsigned int));
for(int i=0;i<licznik;i++)
{
    *(okragla_tablica+i)=*(okragla_tablica_pomocnicza+i);
    *(okragla_tablica_identyfikatorow+i)=*(okragla_tablica_identyfikatorow_pomocnicza+i);
}
free(okragla_tablica_identyfikatorow_pomocnicza); 
free(okragla_tablica_pomocnicza); 
return true;
// dopisac spradzenie z chusteczka i reszte przypadkow


*/
//return false;
//}

bool zrezygnuj(unsigned int x)
{
if(x==0)
{
    return false;
}
if(kto_ma_chusteczke==x)
{
    return false;
}
int zmienna=0;
int osobnik=0;
for(int i=0;i<licznik;i++)
{
    if(*(okragla_tablica_identyfikatorow+i)!=x)
    {
      zmienna++;  
    }
    else
    {
    osobnik=i;
    }
}
if(zmienna==licznik)
{
    return false;
}
licznik--;
Uczestnik* okragla_tablica_pomocnicza = (Uczestnik*)malloc(licznik*sizeof(Uczestnik));  //malloc na tablice 
unsigned int* okragla_tablica_identyfikatorow_pomocnicza = (unsigned int*)malloc(licznik*sizeof(unsigned int));  //malloc na tablice 
for(int i=0;i<osobnik;i++)
{
    *(okragla_tablica_pomocnicza+i)=*(okragla_tablica+i);
    *(okragla_tablica_identyfikatorow_pomocnicza+i)=*(okragla_tablica_identyfikatorow+i);
}
for(int i=osobnik;i<licznik;i++)
{
    *(okragla_tablica_pomocnicza+i)=*(okragla_tablica+i+1);
    *(okragla_tablica_identyfikatorow_pomocnicza+i)=*(okragla_tablica_identyfikatorow+i+1);
}
okragla_tablica=(Uczestnik*)realloc(okragla_tablica,licznik*sizeof(Uczestnik));
okragla_tablica_identyfikatorow=(unsigned int*)realloc(okragla_tablica_identyfikatorow,licznik*sizeof(unsigned int));
for(int i=0;i<licznik;i++)
{
    *(okragla_tablica+i)=*(okragla_tablica_pomocnicza+i);
    *(okragla_tablica_identyfikatorow+i)=*(okragla_tablica_identyfikatorow_pomocnicza+i);
}
free(okragla_tablica_identyfikatorow_pomocnicza); 
free(okragla_tablica_pomocnicza); 
return true;
}

//bool przekaz(Uczestnik* osoba)
//{
    /*
if(trwanie_zabawy==0)
{
  return false;
}
int zmienna=0;
for(int i=0;i<licznik;i++)
{
if(osoba!=&(*(okragla_tablica+i)))
{
zmienna++;
}
}
if(zmienna==licznik)
{
   return false; 
}
int i=0;
while(*(okragla_tablica_identyfikatorow+i)!=kto_ma_chusteczke)
{
    i++;
}
if(((okragla_tablica+i)->plec)==osoba->plec || osoba->plec==2)
{
    return false;
}
kto_ma_chusteczke=*(okragla_tablica_identyfikatorow+i);
return true;
*/
//return true;
//}

bool przekaz(unsigned int x)
{
if(trwanie_zabawy==0)
{
  return false;
}
int zmienna=0;
int numerek=0;
for(int i=0;i<licznik;i++)
{
if(x!=*(okragla_tablica_identyfikatorow+i))
{
zmienna++;
}
else
{
numerek=i;    
}
}
if(zmienna==licznik)
{
   return false; 
}
int i=0;
while(*(okragla_tablica_identyfikatorow+i)!=kto_ma_chusteczke)
{
    i++;
}
if(((okragla_tablica+i)->plec)==((okragla_tablica+zmienna)->plec) || ((okragla_tablica+zmienna+0)->plec==2))
{
    return false;
}
kto_ma_chusteczke=x;
return true;
}

void uczestnicy() const 
{
cout<<"plec: "<<((okragla_tablica+0)->plec)<<", nr: "<<*(okragla_tablica_identyfikatorow+0)<<endl;
for(int i=licznik-1;i>=1;i--)
{
    cout<<"plec: "<<((okragla_tablica+i)->plec)<<", nr: "<<*(okragla_tablica_identyfikatorow+i)<<endl;
}
}

void uczestnicy(Uczestnik::Plec p)const 
{
    
for(int i=0;i<licznik;i++)
{   if(((okragla_tablica+i)->plec)==p)
    {
        cout<<"nr: "<<*(okragla_tablica_identyfikatorow+i)<<endl;
    }
}

}

unsigned int liczba()const 
{
return licznik;
}

Uczestnik* wodzirej() const
{
return &(*(okragla_tablica+0));
}

};
