// Dorian Rzasa
#include <iostream>

using namespace std;
void stablesortL(int tab[1024][1024], int M, int I, int K)
{
int tab1[1024];
int j=0;
for(int i=0;i<M;i++)
{  
   if(tab[I][i]!=0)
   {
    tab1[j]=tab[I][i];
    j++;
   }
}
for(int q=0;q<M;q++)
{
    if(q<j)
    {
        tab[I][q]=tab1[q];
    }
    else
    {
        tab[I][q]=0;
    }
}
}
void stablesortP(int tab[1024][1024], int M, int I, int K)
{
int tab1[1024];
int j=M-1;
for(int i=M-1;i>=0;i--)
{
    if(tab[I][i]!=0)
    {
        tab1[j]=tab[I][i];
        j--;
    }
}
for(int i=M-1;i>=0;i--)
{
if(i>j)
{
    tab[I][i]=tab1[i];
}
else
{
    tab[I][i]=0;
}
}
}
void stablesortG(int tab[1024][1024], int M, int I, int K)
{
int tab1[1024];
int j=0;
for(int i=0;i<M;i++)
{  
   if(tab[i][I]!=0)
   {
    tab1[j]=tab[i][I];
    j++;
   }
}
for(int q=0;q<M;q++)
{
    if(q<j)
    {
        tab[q][I]=tab1[q];
    }
    else
    {
        tab[q][I]=0;
    }
}
}
void stablesortD(int tab[1024][1024], int M, int I, int K)
{
int tab1[1024];
int j=M-1;
for(int i=M-1;i>=0;i--)
{
    if(tab[i][I]!=0)
    {
        tab1[j]=tab[i][I];
        j--;
    }
}
for(int i=M-1;i>=0;i--)
{
if(i>j)
{
    tab[i][I]=tab1[i];
}
else
{
    tab[i][I]=0;
}
}
}
void N(int tab[1024][1024] ,int x,int y,int w) 
{ 
    tab[x][y]=w;
}

int tab[1024][1024];
int main()
{
 ios_base::sync_with_stdio(0);
   cin.tie(0);
char Q;
int M;
unsigned long long SUMA=0;
cin>>M;
while( cin>>Q && Q!='K' )
{
if(Q=='S')
{
    cout<<SUMA<<endl;
}
if(Q=='N')
{
    int x,y,w;
    cin>>x>>y>>w;
    SUMA=SUMA+(unsigned long long)(w)-tab[x][y];
    N(tab, x, y, w);
}
if(Q=='W')
{
    for(int i=0;i<M;i++)
    {
        for(int j=0;j<M;j++)
        {
            cout<<tab[i][j]<<" ";
        }
        cout<<endl;
    }
}
if(Q=='C')
{   SUMA=0;
    cin>>M;
    for(int i=0;i<M;i++)
    {
        for(int j=0;j<M;j++)
        {
            cin>>tab[i][j];
            SUMA=SUMA+(unsigned long long)(tab[i][j]);
        }
    }
}
if(Q=='L') //LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
{
for(int I=0;I<M;I++)
{  
int g=0;
stablesortL(tab,M,I,0);
for(int q=0;q<M;q++)
{
    if(tab[I][q]!=0)
    {
        g=q;
    }
}
int i=0;
int j=1;
while(i+j<=g)
{
if(tab[I][i+j]!=0)
{
    if(tab[I][i]==tab[I][i+j])
    {
    tab[I][i]=tab[I][i]*2;
    tab[I][i+j]=0;
    j++;
    while(tab[I][i]==tab[I][i-1] && i>0)
    {
        tab[I][i-1]=2*tab[I][i-1];
        tab[I][i]=0;
        i--;
        j++;
    }
    }

    else
    {
    if(j>1)
    {
    tab[I][i+1]=tab[I][i+j];
    tab[I][i+j]=0;
    i++;
    }

    else
    {
    i++;
    }
    }
}

else
{
    j++;
}
}
}
}

if(Q=='P')  //PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP
{
for(int I=0;I<M;I++)
{  
int g=0;
stablesortP(tab,M,I,0);
for(int q=M-1;q>=0;q--)
{
    if(tab[I][q]!=0)
    {
        g=q;
    }
}
int i=M-1;
int j=1;
while(i-j>=g)
{
if(tab[I][i-j]!=0)
{
    if(tab[I][i]==tab[I][i-j])
    {
    tab[I][i]=tab[I][i]*2;
    tab[I][i-j]=0;
    j++;
    while(tab[I][i]==tab[I][i+1] && i<M-1)
    {
        tab[I][i+1]=2*tab[I][i+1];
        tab[I][i]=0;
        i++;
        j++;
    }
    }

    else
    {
    if(j>1)
    {
    tab[I][i-1]=tab[I][i-j];
    tab[I][i-j]=0;
    i--;
    }

    else
    {
    i--;
    }
    }
}

else
{
    j++;
}
}
}
}
if(Q=='G') //GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
{
for(int I=0;I<M;I++)
{  
int g=0;
stablesortG(tab,M,I,0);
for(int q=0;q<M;q++)
{
    if(tab[q][I]!=0)
    {
        g=q;
    }
}
int i=0;
int j=1;
while(i+j<=g)
{
if(tab[i+j][I]!=0)
{
    if(tab[i][I]==tab[i+j][I])
    {
    tab[i][I]=tab[i][I]*2;
    tab[i+j][I]=0;
    j++;
    while(tab[i][I]==tab[i-1][I] && i>0)
    {
        tab[i-1][I]=2*tab[i-1][I];
        tab[i][I]=0;
        i--;
        j++;
    }
    }

    else
    {
    if(j>1)
    {
    tab[i+1][I]=tab[i+j][I];
    tab[i+j][I]=0;
    i++;
    }

    else
    {
    i++;
    }
    }
}

else
{
    j++;
}
}
}
}
if(Q=='D') //DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD
{
for(int I=0;I<M;I++)
{  
int g=0;
stablesortD(tab,M,I,0);
for(int q=M-1;q>=0;q--)
{
    if(tab[q][I]!=0)
    {
        g=q;
    }
}
int i=M-1;
int j=1;
while(i-j>=g)
{
if(tab[i-j][I]!=0)
{
    if(tab[i][I]==tab[i-j][I])
    {
    tab[i][I]=tab[i][I]*2;
    tab[i-j][I]=0;
    j++;
    while(tab[i][I]==tab[i+1][I] && i<M-1)
    {
        tab[i+1][I]=2*tab[i+1][I];
        tab[i][I]=0;
        i++;
        j++;
    }
    }

    else
    {
    if(j>1)
    {
    tab[i-1][I]=tab[i-j][I];
    tab[i-j][I]=0;
    i--;
    }

    else
    {
    i--;
    }
    }
}

else
{
    j++;
}
}
}
}
}
cout<<SUMA<<endl;
 return 0;
}