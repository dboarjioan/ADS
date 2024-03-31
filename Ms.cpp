#include <iostream>
#include <string>

using namespace std;

class Pair{
public:
    int num;
    string str;

    Pair()
    {
    }

    Pair(int a, string b)
    {
        num=a;
        str=b;
    }
};

bool compare(Pair a, Pair b, int jak) {
    if(jak==0){
       return a.num <= b.num; 
    }
    return a.num>=b.num;
}


void merge(Pair tab[], int L, int mid, int R, Pair temp[], int jak) {
    for(int i=L; i<=R; i++){
        temp[i]=tab[i];
    }

    int i=L;
    int j=mid+1;
    int k=L;

    while(i<=mid && j<=R){
        if(compare(temp[i], temp[j], jak)){
            tab[k]=temp[i];
            i++;
        } 
        else{
            tab[k]=temp[j];
            j++;
        }
        k++;
    }

    while(i<=mid){
        tab[k]=temp[i];
        i++;
        k++;
    }

    while(j<=R){
        tab[k]=temp[j];
        j++;
        k++;
    }
}

void mergeSort(Pair tab[], int L, int R, Pair temp[], int jak){
    if(L<R){
        int mid=L+(R-L)/2;
        mergeSort(tab, L, mid, temp, jak);
        mergeSort(tab, mid+1, R, temp, jak);
        merge(tab, L, mid, R, temp, jak);
    }
}

int main(){
    int M;
    cin>>M;

    while(M--){
        char order;
        int N;
        cin>>order>>N;

        Pair* pairs=new Pair[N];
        Pair* temp=new Pair[N];

        for(int i=0; i<N; i++){
            int num;
            string str;
            cin>>num>>str;
            pairs[i]=Pair(num, str);
        }

        if(order=='<'){
            mergeSort(pairs, 0, N-1, temp, 0);
        } 
        else{
            mergeSort(pairs, 0, N-1, temp, 1);
        }
        for(int i=0; i<N; i++){
            cout<<pairs[i].num <<" "<<pairs[i].str<< " ";
        }
        cout<<endl;

        delete[] pairs;
        delete[] temp;
    }

    return 0;
}
