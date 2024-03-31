//Dorian Rzasa
#include <iostream>
#include <cstring>
#include <cmath>
#include <cstdio>

using namespace std;

void zmiana_na_onp(string a, string &b)
{
char stos[1009];
char kolejka[1009];
int k=0,s=0;
int i=0;
int j=0;
while(i<a.length())
{
if(a[i]>='a' && a[i]<='z')
{
    kolejka[k]=(char)(a[i]);
    k++;
    goto tutajonp;
}
if(a[i]=='(')
{
    stos[s]=(char)(a[i]);
    s++;
    j++;
    goto tutajonp;
}
if(a[i]=='>' || a[i]=='=' || a[i]=='&' || a[i]=='|')
{
while(stos[s-1]=='>' || stos[s-1]=='=' || stos[s-1]=='&' || stos[s-1]=='|' || stos[s-1]=='~')
{
kolejka[k]=stos[s-1];
k++;
s--;
}
stos[s]=(char)(a[i]);
s++;
goto tutajonp;
}
if(a[i]=='~')
{
stos[s]=(char)(a[i]);
s++;
goto tutajonp;
}
if(a[i]==')')
{
    j++;
while(stos[s-1]!='(')
{
kolejka[k]=stos[s-1];
s--;
k++;
}
s--;
}
tutajonp:
i++;
}
for(int i=s-1;i>=0;i--)
{
    kolejka[k]=stos[i];
    k++;
}
for(int i=0;i<a.length()-j;i++)
{
b=b+(char)(kolejka[i]);
}
}

void uproszczenie (string wyrazenie, string &wyrazeniepop)
{   
    for(int i=0; i<wyrazenie.length(); i++)
    {
        if(wyrazenie[i]=='~')
    {   int a=0;
        while(wyrazenie[i]=='~')
        {
        a++;
        i++;
        }
        if(a%2==1)
        {
        wyrazeniepop=wyrazeniepop+'~';
        }
        i--;
    }
        else
    {
        wyrazeniepop=wyrazeniepop+wyrazenie[i];
    }
    }
}

void uproszczenie (string &wyrazenie)
{   
    for(int i=0; i<wyrazenie.length(); i++)
    {
        while(wyrazenie[i]=='~' && wyrazenie[i+1]=='~')
    {   
        wyrazenie.erase(i,2);
    }
    }
}
int main()
{
    ios_base::sync_with_stdio(0);
   cin.tie(0);
   char Q;
   while(cin>>Q && Q!='W')
   {

if(Q=='T') //TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT
{
int q=1;
string a,c;
cin>>a;
    uproszczenie(a);
    zmiana_na_onp(a,c);
    int tab[30]={0};
   for(int i=0;i<c.length();i++)
    {
        if(c[i]>='a' && c[i]<='z')
        {
            tab[(int)((c[i]))-(int)('a')]=1;
        }
    }
    int ilosc=0;
    for(int i=0;i<26;i++)
    {
        if(tab[i]==1)
        {   
            ilosc++;
        }
    }
    char *tablica_zmiennych = new char [ilosc];
    int *tablica_wartoscowan = new int [ilosc];
    int zmienna_do_kolejnsoci=0;
    for(int i=0;i<26;i++)
    {
        if(tab[i]==1)
        {
            tablica_zmiennych[zmienna_do_kolejnsoci]=(char)(i+'a');
            zmienna_do_kolejnsoci++;
        }
    }
     int ilosc_prawd=pow(2,ilosc);
     int P=c.length();
     for(int i=0;i<ilosc_prawd;i++)
    {
        for(int L=0;L<ilosc;L++)
       {
        int r=pow(2,L+1);
        int R=pow(2,L);
        if((i%r)<R)
        {
        tablica_wartoscowan[L]=1;
        }
        else
        {
        tablica_wartoscowan[L]=0;
        }
       }
       string wynik=c;
       for(int i=0;i<P;i++)
        {
            for(int I=0;I<ilosc;I++)
            {
                if(c[i]==tablica_zmiennych[I])
                {
                wynik[i]=(char)(tablica_wartoscowan[I]+48);
                break;
                }
            }
    }
    int I=0;
    char stos[1009];
    int s=0;
    while(I<wynik.length())
    {
     if(wynik[I]=='1' || wynik[I]=='0')
     {
        stos[s]=wynik[I];
        s++;
     }
     if(wynik[I]=='>' || wynik[I]=='=' || wynik[I]=='&' || wynik[I]=='|')
{
    
    if(wynik[I]=='|')
    {
    if(stos[s-2]=='0' && stos[s-1]=='0')
    {
        s=s-2;
        stos[s]='0';
        s++;
    }
    else
    {
        s=s-2;
        stos[s]='1';
        s++;
    }
    goto tutajT;
    }
    
        if(wynik[I]=='&')
    {
        if(stos[s-2]=='1' && stos[s-1]=='1')
    {
        s=s-2;
        stos[s]='1';
        s++;
    }
    
    else
    {
        s=s-2;
        stos[s]='0';
        s++;
    }
        goto tutajT;

    }
    
        if(wynik[I]=='>')
    {
        if(stos[s-2]=='1' && stos[s-1]=='0')
    {
        s=s-2;
        stos[s]='0';
        s++;
    }
    
    else
    {
        s=s-2;
        stos[s]='1';
        s++;
    }
        goto tutajT;

    }
    
        if(wynik[I]=='=')
    {
        if((stos[s-2]=='1' && stos[s-1]=='1') || (stos[s-2]=='0' && stos[s-1]=='0'))
        {
            s=s-2;
            stos[s]='1';
            s++;
        }
        else
        {
            s=s-2;
            stos[s]='0';
            s++;
        }
        goto tutajT;
    }

}

    if(wynik[I]=='~')
    {
    string w="";
    w=w+stos[s-1];
    s--;
    if(w[0]=='1')
    {
        stos[s]='0';
        s++;
    }
    if(w[0]=='0')
    {
        stos[s]='1';
        s++;
    }
    }
    tutajT:
    I++;
    }
    if(stos[0]=='0')
    {
        cout<<"NIE"<<endl;
        q=0;
        i=ilosc_prawd;
    }
    }
    if(q==1)
    {
        cout<<"TAK"<<endl;
    }
}
    
if(Q=='P') //PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
{
       int q=0;
string a,c;
cin>>a;
    uproszczenie(a);
    zmiana_na_onp(a,c);
    int tab[30]={0};
   for(int i=0;i<c.length();i++)
    {
        if(c[i]>='a' && c[i]<='z')
        {
            tab[(int)((c[i]))-(int)('a')]=1;
        }
    }
    int ilosc=0;
    for(int i=0;i<26;i++)
    {
        if(tab[i]==1)
        {   
            ilosc++;
        }
    }
    char *tablica_zmiennych = new char [ilosc];
    int *tablica_wartoscowan = new int [ilosc];
    int zmienna_do_kolejnsoci=0;
    for(int i=0;i<26;i++)
    {
        if(tab[i]==1)
        {
            tablica_zmiennych[zmienna_do_kolejnsoci]=(char)(i+'a');
            zmienna_do_kolejnsoci++;
        }
    }
     int ilosc_prawd=pow(2,ilosc);
     int P=c.length();
     for(int i=0;i<ilosc_prawd;i++)
    {
        for(int L=0;L<ilosc;L++)
       {
        int r=pow(2,L+1);
        int R=pow(2,L);
        if((i%r)<R)
        {
        tablica_wartoscowan[L]=1;
        }
        else
        {
        tablica_wartoscowan[L]=0;
        }
       }
       string wynik=c;
       for(int i=0;i<P;i++)
        {
            for(int I=0;I<ilosc;I++)
            {
                if(c[i]==tablica_zmiennych[I])
                {
                wynik[i]=(char)(tablica_wartoscowan[I]+48);
                break;
                }
            }
    }
    int I=0;
    char stos[1009];
    int s=0;
    while(I<wynik.length())
    {
     if(wynik[I]=='1' || wynik[I]=='0')
     {
        stos[s]=wynik[I];
        s++;
     }
     if(wynik[I]=='>' || wynik[I]=='=' || wynik[I]=='&' || wynik[I]=='|')
{
    
    if(wynik[I]=='|')
    {
    if(stos[s-2]=='0' && stos[s-1]=='0')
    {
        s=s-2;
        stos[s]='0';
        s++;
    }
    else
    {
        s=s-2;
        stos[s]='1';
        s++;
    }
    goto tutajP;
    }
    
        if(wynik[I]=='&')
    {
        if(stos[s-2]=='1' && stos[s-1]=='1')
    {
        s=s-2;
        stos[s]='1';
        s++;
    }
    
    else
    {
        s=s-2;
        stos[s]='0';
        s++;
    }
        goto tutajP;

    }
    
        if(wynik[I]=='>')
    {
        if(stos[s-2]=='1' && stos[s-1]=='0')
    {
        s=s-2;
        stos[s]='0';
        s++;
    }
    
    else
    {
        s=s-2;
        stos[s]='1';
        s++;
    }
        goto tutajP;

    }
    
        if(wynik[I]=='=')
    {
        if((stos[s-2]=='1' && stos[s-1]=='1') || (stos[s-2]=='0' && stos[s-1]=='0'))
        {
            s=s-2;
            stos[s]='1';
            s++;
        }
        else
        {
            s=s-2;
            stos[s]='0';
            s++;
        }
        goto tutajP;
    }

}

    if(wynik[I]=='~')
    {
    string w="";
    w=w+stos[s-1];
    s--;
    if(w[0]=='1')
    {
        stos[s]='0';
        s++;
    }
    if(w[0]=='0')
    {
        stos[s]='1';
        s++;
    }
    }
    tutajP:
    I++;
    }
    if(stos[0]=='1')
    {
        q++;
    }
    }
    cout<<q<<endl;
}

if(Q=='F') //FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF
{
         int q=0;
string a,c;
cin>>a;
    uproszczenie(a);
    zmiana_na_onp(a,c);
    int tab[30]={0};
   for(int i=0;i<c.length();i++)
    {
        if(c[i]>='a' && c[i]<='z')
        {
            tab[(int)((c[i]))-(int)('a')]=1;
        }
    }
    int ilosc=0;
    for(int i=0;i<26;i++)
    {
        if(tab[i]==1)
        {   
            ilosc++;
        }
    }
    char *tablica_zmiennych = new char [ilosc];
    int *tablica_wartoscowan = new int [ilosc];
    int zmienna_do_kolejnsoci=0;
    for(int i=0;i<26;i++)
    {
        if(tab[i]==1)
        {
            tablica_zmiennych[zmienna_do_kolejnsoci]=(char)(i+'a');
            zmienna_do_kolejnsoci++;
        }
    }
     int ilosc_prawd=pow(2,ilosc);
     int P=c.length();
     for(int i=0;i<ilosc_prawd;i++)
    {
        for(int L=0;L<ilosc;L++)
       {
        int r=pow(2,L+1);
        int R=pow(2,L);
        if((i%r)<R)
        {
        tablica_wartoscowan[L]=1;
        }
        else
        {
        tablica_wartoscowan[L]=0;
        }
       }
       string wynik=c;
       for(int i=0;i<P;i++)
        {
            for(int I=0;I<ilosc;I++)
            {
                if(c[i]==tablica_zmiennych[I])
                {
                wynik[i]=(char)(tablica_wartoscowan[I]+48);
                break;
                }
            }
    }
    int I=0;
    char stos[1009];
    int s=0;
    while(I<wynik.length())
    {
     if(wynik[I]=='1' || wynik[I]=='0')
     {
        stos[s]=wynik[I];
        s++;
     }
     if(wynik[I]=='>' || wynik[I]=='=' || wynik[I]=='&' || wynik[I]=='|')
{
    
    if(wynik[I]=='|')
    {
    if(stos[s-2]=='0' && stos[s-1]=='0')
    {
        s=s-2;
        stos[s]='0';
        s++;
    }
    else
    {
        s=s-2;
        stos[s]='1';
        s++;
    }
    goto tutajF;
    }
    
        if(wynik[I]=='&')
    {
        if(stos[s-2]=='1' && stos[s-1]=='1')
    {
        s=s-2;
        stos[s]='1';
        s++;
    }
    
    else
    {
        s=s-2;
        stos[s]='0';
        s++;
    }
        goto tutajF;

    }
    
        if(wynik[I]=='>')
    {
        if(stos[s-2]=='1' && stos[s-1]=='0')
    {
        s=s-2;
        stos[s]='0';
        s++;
    }
    
    else
    {
        s=s-2;
        stos[s]='1';
        s++;
    }
        goto tutajF;

    }
    
        if(wynik[I]=='=')
    {
        if((stos[s-2]=='1' && stos[s-1]=='1') || (stos[s-2]=='0' && stos[s-1]=='0'))
        {
            s=s-2;
            stos[s]='1';
            s++;
        }
        else
        {
            s=s-2;
            stos[s]='0';
            s++;
        }
        goto tutajF;
    }

}

    if(wynik[I]=='~')
    {
    string w="";
    w=w+stos[s-1];
    s--;
    if(w[0]=='1')
    {
        stos[s]='0';
        s++;
    }
    if(w[0]=='0')
    {
        stos[s]='1';
        s++;
    }
    }
    tutajF:
    I++;
    }
    if(stos[0]=='0')
    {
        q++;
    }
    }
    cout<<q<<endl;
}

if(Q=='1') //111111111111111111111111111111111111111111111111111111
{
char A;
cin>>A;
int q=1;
string a,c;
cin>>a;
    uproszczenie(a);
    zmiana_na_onp(a,c);
    int tab[30]={0};
    for(int i=0;i<c.length();i++)
    {
        if(c[i]==A)
        {
            c[i]='1';
        }
    }
   for(int i=0;i<c.length();i++)
    {
        if(c[i]>='a' && c[i]<='z')
        {
            tab[(int)((c[i]))-(int)('a')]=1;
        }
    }
    int ilosc=0;
    for(int i=0;i<26;i++)
    {
        if(tab[i]==1)
        {   
            ilosc++;
        }
    }
    char *tablica_zmiennych = new char [ilosc];
    int *tablica_wartoscowan = new int [ilosc];
    int zmienna_do_kolejnsoci=0;
    for(int i=0;i<26;i++)
    {
        if(tab[i]==1)
        {
            tablica_zmiennych[zmienna_do_kolejnsoci]=(char)(i+'a');
            zmienna_do_kolejnsoci++;
        }
    }
     int ilosc_prawd=pow(2,ilosc);
     int P=c.length();
     for(int i=0;i<ilosc_prawd;i++)
    {
        for(int L=0;L<ilosc;L++)
       {
        int r=pow(2,L+1);
        int R=pow(2,L);
        if((i%r)<R)
        {
        tablica_wartoscowan[L]=1;
        }
        else
        {
        tablica_wartoscowan[L]=0;
        }
       }
       string wynik=c;
       for(int i=0;i<P;i++)
        {
            for(int I=0;I<ilosc;I++)
            {
                if(c[i]==tablica_zmiennych[I])
                {
                wynik[i]=(char)(tablica_wartoscowan[I]+48);
                break;
                }
            }
    }
    int I=0;
    char stos[1009];
    int s=0;
    while(I<wynik.length())
    {
     if(wynik[I]=='1' || wynik[I]=='0')
     {
        stos[s]=wynik[I];
        s++;
     }
     if(wynik[I]=='>' || wynik[I]=='=' || wynik[I]=='&' || wynik[I]=='|')
{
    
    if(wynik[I]=='|')
    {
    if(stos[s-2]=='0' && stos[s-1]=='0')
    {
        s=s-2;
        stos[s]='0';
        s++;
    }
    else
    {
        s=s-2;
        stos[s]='1';
        s++;
    }
    goto tutaj1;
    }
    
        if(wynik[I]=='&')
    {
        if(stos[s-2]=='1' && stos[s-1]=='1')
    {
        s=s-2;
        stos[s]='1';
        s++;
    }
    
    else
    {
        s=s-2;
        stos[s]='0';
        s++;
    }
        goto tutaj1;

    }
    
        if(wynik[I]=='>')
    {
        if(stos[s-2]=='1' && stos[s-1]=='0')
    {
        s=s-2;
        stos[s]='0';
        s++;
    }
    
    else
    {
        s=s-2;
        stos[s]='1';
        s++;
    }
        goto tutaj1;

    }
    
        if(wynik[I]=='=')
    {
        if((stos[s-2]=='1' && stos[s-1]=='1') || (stos[s-2]=='0' && stos[s-1]=='0'))
        {
            s=s-2;
            stos[s]='1';
            s++;
        }
        else
        {
            s=s-2;
            stos[s]='0';
            s++;
        }
        goto tutaj1;
    }

}

    if(wynik[I]=='~')
    {
    string w="";
    w=w+stos[s-1];
    s--;
    if(w[0]=='1')
    {
        stos[s]='0';
        s++;
    }
    if(w[0]=='0')
    {
        stos[s]='1';
        s++;
    }
    }
    tutaj1:
    I++;
    }
    if(stos[0]=='0')
    {
        cout<<"NIE"<<endl;
        q=0;
        i=ilosc_prawd;
    }
    }
    if(q==1)
    {
        cout<<"TAK"<<endl;
    }
}
    

if(Q=='0') //0000000000000000000000000000000000000000000000000000
{
  char A;
cin>>A;
int q=1;
string a,c;
cin>>a;
    uproszczenie(a);
    zmiana_na_onp(a,c);
    int tab[30]={0};
    for(int i=0;i<c.length();i++)
    {
        if(c[i]==A)
        {
            c[i]='0';
        }
    }
   for(int i=0;i<c.length();i++)
    {
        if(c[i]>='a' && c[i]<='z')
        {
            tab[(int)((c[i]))-(int)('a')]=1;
        }
    }
    int ilosc=0;
    for(int i=0;i<26;i++)
    {
        if(tab[i]==1)
        {   
            ilosc++;
        }
    }
    char *tablica_zmiennych = new char [ilosc];
    int *tablica_wartoscowan = new int [ilosc];
    int zmienna_do_kolejnsoci=0;
    for(int i=0;i<26;i++)
    {
        if(tab[i]==1)
        {
            tablica_zmiennych[zmienna_do_kolejnsoci]=(char)(i+'a');
            zmienna_do_kolejnsoci++;
        }
    }
     int ilosc_prawd=pow(2,ilosc);
     int P=c.length();
     for(int i=0;i<ilosc_prawd;i++)
    {
        for(int L=0;L<ilosc;L++)
       {
        int r=pow(2,L+1);
        int R=pow(2,L);
        if((i%r)<R)
        {
        tablica_wartoscowan[L]=1;
        }
        else
        {
        tablica_wartoscowan[L]=0;
        }
       }
       string wynik=c;
       for(int i=0;i<P;i++)
        {
            for(int I=0;I<ilosc;I++)
            {
                if(c[i]==tablica_zmiennych[I])
                {
                wynik[i]=(char)(tablica_wartoscowan[I]+48);
                break;
                }
            }
    }
    int I=0;
    char stos[1009];
    int s=0;
    while(I<wynik.length())
    {
     if(wynik[I]=='1' || wynik[I]=='0')
     {
        stos[s]=wynik[I];
        s++;
     }
     if(wynik[I]=='>' || wynik[I]=='=' || wynik[I]=='&' || wynik[I]=='|')
{
    
    if(wynik[I]=='|')
    {
    if(stos[s-2]=='0' && stos[s-1]=='0')
    {
        s=s-2;
        stos[s]='0';
        s++;
    }
    else
    {
        s=s-2;
        stos[s]='1';
        s++;
    }
    goto tutaj0;
    }
    
        if(wynik[I]=='&')
    {
        if(stos[s-2]=='1' && stos[s-1]=='1')
    {
        s=s-2;
        stos[s]='1';
        s++;
    }
    
    else
    {
        s=s-2;
        stos[s]='0';
        s++;
    }
        goto tutaj0;

    }
    
        if(wynik[I]=='>')
    {
        if(stos[s-2]=='1' && stos[s-1]=='0')
    {
        s=s-2;
        stos[s]='0';
        s++;
    }
    
    else
    {
        s=s-2;
        stos[s]='1';
        s++;
    }
        goto tutaj0;

    }
    
        if(wynik[I]=='=')
    {
        if((stos[s-2]=='1' && stos[s-1]=='1') || (stos[s-2]=='0' && stos[s-1]=='0'))
        {
            s=s-2;
            stos[s]='1';
            s++;
        }
        else
        {
            s=s-2;
            stos[s]='0';
            s++;
        }
        goto tutaj0;
    }

}

    if(wynik[I]=='~')
    {
    string w="";
    w=w+stos[s-1];
    s--;
    if(w[0]=='1')
    {
        stos[s]='0';
        s++;
    }
    if(w[0]=='0')
    {
        stos[s]='1';
        s++;
    }
    }
    tutaj0:
    I++;
    }
    if(stos[0]=='0')
    {
        cout<<"NIE"<<endl;
        q=0;
        i=ilosc_prawd;
    }
    }
    if(q==1)
    {
        cout<<"TAK"<<endl;
    }
}
}
return 0;
}
