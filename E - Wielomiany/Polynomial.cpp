//Dorian Rzasa
#include "Polynomial.h"
#include <iostream>
#include <sstream>
#include <string>
#include <cstdlib>
#include <cstring>

using namespace std;

Polynomial::Polynomial(){
    this->tab=(Z3*)realloc(this->tab,1*sizeof(Z3));
     this->tab[0].x=0;
     this->stopien=0;
 }

 //Polynomial::~Polynomial(){
  // free(this->tab);
 //}

 Polynomial::Polynomial(unsigned int rozmiar, Z3* tab1){
     int w=0;
     for(int i=rozmiar;i>=0;i--)
     {
         if(tab1[i].x!=0 && w==0)
         {
             w=i;
         }
     }
    this->tab=(Z3*)realloc(this->tab,(w+1)*sizeof(Z3));
     for(int i=0;i<=w;i++)
     {
         this->tab[i].x=tab1[i].x%3;
     }
     this->stopien=w;
 }

Polynomial& Polynomial::operator*=(const Z3& a){
return *this;
}
Polynomial& Polynomial::operator/=(const Z3& a){
return *this;
}

  Polynomial& Polynomial::operator+=(const Polynomial& a){
    Z3* tab1 = (Z3*)malloc(0*sizeof(Z3));
    if(stopien>=a.stopien)
       {
        tab1=(Z3*)realloc(tab1,(stopien+1)*sizeof(Z3));
       for(int i=0;i<=stopien;i++)
       {
         if(i<=a.stopien)
         {
             tab1[i].x=(tab[i].x+a.tab[i].x)%3;
         }
         else
         {
             tab1[i].x=tab[i].x;
         }
       }
     int w=0;
     for(int i=stopien;i>=0;i--)
     {
         if(tab1[i].x!=0 && w==0)
         {
             w=i;
         }
     }
    tab=(Z3*)realloc(tab,(w+1)*sizeof(Z3));
     for(int i=0;i<=w;i++)
     {
         tab[i].x=tab1[i].x%3;
     }
     stopien=w;
       }

       else
       {
        tab1=(Z3*)realloc(tab1,(a.stopien+1)*sizeof(Z3));
        for(int i=0;i<=a.stopien;i++)
       {
         if(i<=stopien)
         {
             tab1[i].x=(tab[i].x+a.tab[i].x)%3;
         }
         else
         {
             tab1[i].x=(a.tab[i].x)%3;
         }
       }
        int w=0;
     for(int i=a.stopien;i>=0;i--)
     {
         if(tab1[i].x!=0 && w==0)
         {
             w=i;
         }
     }
    tab=(Z3*)realloc(tab,(w+1)*sizeof(Z3));
     for(int i=0;i<=w;i++)
     {
         tab[i].x=tab1[i].x%3;
     }
     stopien=w;
       }

    return *this;
  }

Polynomial operator*(const Polynomial& a, const Z3& b){
    return a;
}

Polynomial operator/(const Polynomial& a, const Z3& b){
    return a;
}

  Polynomial operator+(const Polynomial& a, const Polynomial& b){
  //Polynomial q;
        int W=0;
         Z3* tab1 = (Z3*)malloc(0*sizeof(Z3));
  if(b.stopien>=a.stopien)
       {
        tab1=(Z3*)realloc(tab1,(b.stopien+1)*sizeof(Z3));
        //q.tab=(Z3*)realloc(q.tab,(b.stopien+1)*sizeof(Z3));
        //q.stopien=b.stopien;
       for(int i=0;i<=b.stopien;i++)
       {
         if(i<=a.stopien)
         {
             tab1[i].x=(b.tab[i].x+a.tab[i].x)%3;
             //q.tab[i]=(b.tab[i]+a.tab[i])%3;
         }
         else
         {
             //q.tab[i]=b.tab[i];
             tab1[i].x=(b.tab[i].x)%3;
         }
       }

       int w=0;
     for(int i=b.stopien;i>=0;i--)
     {
         if(tab1[i].x!=0 && w==0)
         {
             w=i;
         }
     }
    tab1=(Z3*)realloc(tab1,(w+1)*sizeof(Z3));
    W=w;
       }

       else if(b.stopien<a.stopien)
       {
        tab1=(Z3*)realloc(tab1,(a.stopien+1)*sizeof(Z3));
        //q.tab=(Z3*)realloc(q.tab,(a.stopien+1)*sizeof(Z3));
        //q.stopien=a.stopien;
       for(int i=0;i<=a.stopien;i++)
       {
         if(i<=b.stopien)
         {
             tab1[i].x=(b.tab[i].x+a.tab[i].x)%3;
         }
         else
         {
             tab1[i].x=a.tab[i].x;
         }
       }
       int w=0;
     for(int i=a.stopien;i>=0;i--)
     {
         if(tab1[i].x!=0 && w==0)
         {
             w=i;
         }
     }
    tab1=(Z3*)realloc(tab1,(w+1)*sizeof(Z3));
    W=w;
       }

       Polynomial q(W,tab1);
       return q;

}

    Z3 Polynomial::operator[](unsigned int p)const{
    if(p>stopien)
    {
        cout<<"Niepoprawny indeks wielomianu\n";
        return tab[0];
    }
    else
    {
        //this->tab[p]=(tab[p])%3;
        return tab[p];
    }
    }

    unsigned int Polynomial::degree()const{

    return this->stopien;

    }


    std::string Polynomial::toString(std::string xVar) const {
        string result;
    if(this->stopien==0)
    {
        return "0";
    }
    else {
        for (int i = 0; i <=this->stopien; i++){
              if(i==0){
                if(tab[i].x==1)
                {
                    result=result+"1";
                }
                if(tab[i].x==2)
                {
                    result=result+"2";
                }
              }
              else
              {
                 if(tab[i].x==1)
                {
                    result=result+"+1*"+xVar+"^"+to_string(i);

                }
                if(tab[i].x==2)
                {
                    result=result+"+2*"+xVar+"^"+to_string(i);
                }
              }
            }
        }
        return result;
    }



    ostream& operator<<(ostream& out, const Polynomial& a){
    out<<"{";
    for(int i=0;i<=a.stopien;i++)
    {
        out<<a.tab[i];
        if(i<a.stopien){
        out<<",";}
    }
    out<<"}";
    return out;
    }

    istream& operator>>(istream& is, Polynomial& poly){
    int capacity = 10; // pocz�tkowy rozmiar tablicy
    int size = 0; // liczba dotychczas wczytanych wsp�czynnik�w
    Z3* coeffs = (Z3*)malloc(capacity*sizeof(Z3));
    char c;
    while (is >> c && c != '{') {} // szukamy pocz�tku tablicy
    while (is >> c) {
        if (c == '}') break; // koniec tablicy
        if (size == capacity) {
            capacity *= 2;
            Z3* newCoeffs = (Z3*)malloc(capacity*sizeof(Z3));
            for (int i = 0; i < size; i++) {
                newCoeffs[i] = coeffs[i];
            }
            free(coeffs);
            coeffs = newCoeffs;
        }
        if (c >= '0' && c <= '2'){ // sprawdzamy, czy wczytany znak jest cyfr� od 0 do 2
            int coef = c - '0';
            Z3 q;
            q.x=coef;
            coeffs[size] = q;
            size++;
        }
    }
    coeffs=(Z3*)realloc(coeffs,size*sizeof(Z3));
    poly = Polynomial(size-1, coeffs);
    free(coeffs);
    return is;
}

