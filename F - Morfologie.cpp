//Dorian Rzasa
#include <iostream>
#include <iomanip>
#include <vector>
#include <cmath>
#include <cstdio>
#include "Morfologia.h"

using namespace std;

class BitmapaExt : public Bitmapa{

public:
unsigned zakresX;
unsigned zakresY;
unsigned zakresZ;
bool ***array;

BitmapaExt(unsigned x, unsigned y, unsigned z){
    zakresX=x;
    zakresY=y;
    zakresZ=z;
    array = (bool***) calloc(x, sizeof(bool**));
    for (int i=0; i<x; i++){
        array[i] = (bool**) calloc(y, sizeof(bool*));
        for (int j = 0; j<y; j++){
            array[i][j] = (bool*) calloc(z, sizeof(bool));
        }
    }
}
    
 virtual unsigned int sx() const override{
    return zakresX;
 }
 
 virtual unsigned int sy() const override{
    return zakresY;
 }
 
 virtual unsigned int sz() const override{
    return zakresZ;
 }
 
 virtual bool operator()(unsigned int x, unsigned int y, unsigned int z)const override{
     return array[x][y][z];
}
virtual bool& operator()(unsigned int x, unsigned int y, unsigned int z) override {
    return array[x][y][z];
}

    BitmapaExt(const BitmapaExt& bitmapa1){
        zakresX=bitmapa1.zakresX;
        zakresY=bitmapa1.zakresY;
        zakresZ=bitmapa1.zakresZ;

        array=(bool***) calloc(zakresX, sizeof(bool**));
        for(int A=0; A<zakresX; A++)
        {
            array[A]=(bool**) calloc(zakresY, sizeof(bool*));
            for(int B=0; B<zakresY; B++)
            {
                array[A][B]=(bool*) calloc(zakresZ, sizeof(bool));
                for(int C=0; C<zakresZ; C++){
                    array[A][B][C]=bitmapa1.array[A][B][C];
                }
            }
        }
    }

    virtual ~BitmapaExt(){
        
        for(int i=0; i<zakresX; i++)
        {
            for(int j=0; j<zakresY; j++)
            {
                free(array[i][j]);
            }
        }

        for(int i=0; i<zakresX; i++)
        {
                free(array[i]);
        }
        free(array);
    }
    
    
};
   





class Erozja : public Przeksztalcenie{
    
    public:
    
    virtual void przeksztalc(Bitmapa& bitmapa)  {
        
        unsigned int x=bitmapa.sx();
        unsigned int y=bitmapa.sy();
        unsigned int z=bitmapa.sz();
        
        bool ***array1;
        array1 = (bool***) calloc(x, sizeof(bool**));
    for (int i=0; i<x; i++){
        array1[i] = (bool**) calloc(y, sizeof(bool*));
        for (int j=0; j<y; j++){
            array1[i][j] = (bool*) calloc(z, sizeof(bool));
        }
    }

      for(int A=0;A<x;A++)
    {
        for(int B=0;B<y;B++)
    {
       for(int C=0;C<z;C++)
    {
        
        if(bitmapa(A,B,C)==true)
        {
            
        if(A != 0)
        {
            if(bitmapa(A-1,B,C)==false)
            {
                array1[A][B][C]=true;
                goto Q;
            }
        }
        
        if(A != bitmapa.sx()-1)
        {
            if(bitmapa(A+1,B,C)==false)
            {
                array1[A][B][C]=true;
                goto Q;
            }
        }
        
        if(B != 0)
        {
            if(bitmapa(A,B-1,C)==false)
            {
                array1[A][B][C]=true;
                goto Q;
            }

        }
        
        if(B != bitmapa.sy()-1)
        {
            if(bitmapa(A,B+1,C)==false)
            {
                array1[A][B][C]=true;
                goto Q;
            }

        }
        
        if(C != 0)
        {
            if(bitmapa(A,B,C-1)==false)
            {
                array1[A][B][C]=true;
                goto Q;
            }
      
        }
        
        if(C != bitmapa.sz()-1)
        {
            if(bitmapa(A,B,C+1)==false)
            {
                array1[A][B][C]=true;
                goto Q;
            }

        }
        }
        
        Q:;

    } 
    }
    }
    
    for(int A=0;A<x;A++)
    {
        for(int B=0;B<y;B++)
    {
       for(int C=0;C<z;C++)
    {
       if(array1[A][B][C])
       {
           bitmapa(A,B,C)=0;
       }
    }
    }
    }
    for(int i=0;i<x;i++)
    {
        for (int j=0;j<y; j++) 
        {
            free(array1[i][j]);
        }
    }
    
    for(int j=0; j<x; j++)
    {
        free(array1[j]);
    }
        free(array1);
    
    }
    
};

class Dylatacja : public Przeksztalcenie{
    
    public:
    
    virtual void przeksztalc(Bitmapa& bitmapa) {
        
        unsigned int x=bitmapa.sx();
        unsigned int y=bitmapa.sy();
        unsigned int z=bitmapa.sz();
        
        bool ***array1;
        array1 = (bool***) calloc(x, sizeof(bool**));
    for (int i=0; i<x; i++){
        array1[i] = (bool**) calloc(y, sizeof(bool*));
        for (int j = 0; j<y; j++){
            array1[i][j] = (bool*) calloc(z, sizeof(bool));
        }
    }
    
      for(int A=0;A<x;A++)
    {
        for(int B=0;B<y;B++)
    {
       for(int C=0;C<z;C++)
    {
        
        if(bitmapa(A,B,C)==false)
        {
            
        if(A != 0)
        {
            if(bitmapa(A-1,B,C)==true)
            {
                array1[A][B][C]=true;
                goto Q;
            }

        }
        
        if(A != bitmapa.sx()-1)
        {
            if(bitmapa(A+1,B,C)==true)
            {
                array1[A][B][C]=true;
                goto Q;
            }
          
        }
        
        if(B != 0)
        {
            if(bitmapa(A,B-1,C)==true)
            {
                array1[A][B][C]=true;
                goto Q;
            }
   
        }
        
        if(B != bitmapa.sy()-1)
        {
            if(bitmapa(A,B+1,C)==true)
            {
                array1[A][B][C]=true;
                goto Q;
            }

        }
        
        if(C != 0)
        {
            if(bitmapa(A,B,C-1)==true)
            {
                array1[A][B][C]=true;
                goto Q;
            }

        }
        
        if(C != bitmapa.sz()-1)
        {
            if(bitmapa(A,B,C+1)==true)
            {
                array1[A][B][C]=true;
                goto Q;
            }
        }
        
        }
        
        Q:;
    } 
    }
    }
    
    for(int A=0;A<x;A++)
    {
        for(int B=0;B<y;B++)
    {
       for(int C=0;C<z;C++)
    {
       if(array1[A][B][C]==1)
       {
           bitmapa(A,B,C)=1;
       }

    }
    }
    }
    for(int i=0;i<x;i++)
    {
        for (int j=0;j<y; j++) 
        {
            free(array1[i][j]);
        }
    }
    
    for(int j=0; j<x; j++)
    {
        free(array1[j]);
    }
        free(array1);
    
    }
    
};




class Usrednianie : public Przeksztalcenie{
    
    public:
    
    virtual void przeksztalc(Bitmapa& bitmapa) {
      
        int flaga=0;
        unsigned int x=bitmapa.sx();
        unsigned int y=bitmapa.sy();
        unsigned int z=bitmapa.sz();
        
        bool ***array1;
        array1 = (bool***) calloc(x, sizeof(bool**));
    for (int i=0; i<x; i++){
        array1[i] = (bool**) calloc(y, sizeof(bool*));
        for (int j = 0; j<y; j++){
            array1[i][j] = (bool*) calloc(z, sizeof(bool));
        }
    }
    
      for(int A=0;A<x;A++)
    {
        for(int B=0;B<y;B++)
    {
       for(int C=0;C<z;C++)
    {
        
        
        if(bitmapa(A,B,C)==false)
        {
            
        if(A != 0)
        {
            if(bitmapa(A-1,B,C)==true)
            {
                flaga++;
         
            }
        }
        
        if(A != bitmapa.sx()-1)
        {
            if(bitmapa(A+1,B,C)==true)
            {
                flaga++;
           
            }
        }
        
        if(B != 0)
        {
            if(bitmapa(A,B-1,C)==true)
            {
               flaga++;
       
            }
        }
        
        if(B != bitmapa.sy()-1)
        {
            if(bitmapa(A,B+1,C)==true)
            {
               flaga++;
             
            }
        }
        
        if(C != 0)
        {
            if(bitmapa(A,B,C-1)==true)
            {
               flaga++;
            
            }
        }
        
        if(C != bitmapa.sz()-1)
        {
            if(bitmapa(A,B,C+1)==true)
            {
                flaga++;
                
            }
        }
        }
        
        else
        {
            
        if(A != 0)
        {
            if(bitmapa(A-1,B,C)==false)
            {
                flaga++;
         
            }
        }
        
        if(A != bitmapa.sx()-1)
        {
            if(bitmapa(A+1,B,C)==false)
            {
                flaga++;
           
            }
        }
        
        if(B != 0)
        {
            if(bitmapa(A,B-1,C)==false)
            {
               flaga++;
       
            }
        }
        
        if(B != bitmapa.sy()-1)
        {
            if(bitmapa(A,B+1,C)==false)
            {
               flaga++;
             
            }
        }
        
        if(C != 0)
        {
            if(bitmapa(A,B,C-1)==false)
            {
               flaga++;
            
            }
        }
        
        if(C != bitmapa.sz()-1)
        {
            if(bitmapa(A,B,C+1)==false)
            {
                flaga++;
                
            }
        }
        
        }
        if(flaga>3)
        {
            array1[A][B][C]=!bitmapa(A,B,C);
        }
        else if(flaga<=3)
        {
            array1[A][B][C]=bitmapa(A,B,C);
        }
        
        flaga=0;
    } 
    }
    }
    
    for(int A=0;A<x;A++)
    {
        for(int B=0;B<y;B++)
    {
       for(int C=0;C<z;C++)
    {
           bitmapa(A,B,C)=array1[A][B][C];
    }
    }
    }
    for(int i=0;i<x;i++)
    {
        for (int j=0;j<y; j++) 
        {
            free(array1[i][j]);
        }
    }
    
    for(int j=0; j<x; j++)
    {
        free(array1[j]);
    }
        free(array1);
    
    }
    
};


class Inwersja : public Przeksztalcenie{
    
    public:
    
    virtual void przeksztalc(Bitmapa& bitmapa) override {
      for(int A=0;A<bitmapa.sx();A++)
    {
        for(int B=0;B<bitmapa.sy();B++)
    {
       for(int C=0;C<bitmapa.sz();C++)
    {
            bitmapa(A,B,C)=!bitmapa(A,B,C);
        
    } 
    }
    }
    
    }
    
};

   inline ostream& operator<<(ostream& out, const Bitmapa& bitmapa){
    out<<"{";
    out<<endl;
    out<<" ";
    out<<"{";
    out<<endl;
    out<<"  ";
    out<<"{";
    for(int A=0;A<bitmapa.sx();A++)
    {
        if(A!=0)
        {
            out<<" {";
            out<<endl;
        }
        for(int B=0;B<bitmapa.sy();B++)
        { 
            if(A==0 && B==0)
            {
                
            }
            else
            {
                out<<"  {";
            }
            
           for(int C=0;C<bitmapa.sz()-1;C++)
           {
            out<<bitmapa(A, B, C);
            out<<",";
            
           } 
           out<<bitmapa(A, B, bitmapa.sz()-1);
           if(B!=bitmapa.sy()-1)
           {
           out<<"},";
           out<<endl;
           }
           else if(A!=bitmapa.sx()-1)
           {
           out<<"}";
           out<<endl;
           out<<" },";
           out<<endl;
           }
           else
           {
               out<<"}";
           out<<endl;
           out<<" }";
           out<<endl;
           out<<"}";
           }
        }
    }
    
    
    return out;
    }
    

class Zerowanie : public Przeksztalcenie{
    
    public:
    
    virtual void przeksztalc(Bitmapa &bitmapa){
      for(int A=0;A<bitmapa.sx();A++)
    {
        for(int B=0;B<bitmapa.sy();B++)
    {
       for(int C=0;C<bitmapa.sz();C++)
    {
        bitmapa(A,B,C)=0;
    } 
    }
    }
    
    }
    
};



         class ZlozeniePrzeksztalcen : public Przeksztalcenie {
        public:
        vector<Przeksztalcenie *> przeksztalcenia;
            ZlozeniePrzeksztalcen() {}

            void dodajPrzeksztalcenie(Przeksztalcenie *p) {
                przeksztalcenia.push_back(p);
            }

            void przeksztalc(Bitmapa &bmp) override {
                for (auto i=przeksztalcenia.begin(); i<przeksztalcenia.end();i++) {
                    auto p=*i;
                    p->przeksztalc(bmp);
                }
            }
        };