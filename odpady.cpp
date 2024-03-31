#include <iostream>
#include <list>
#include <fstream>
#include <set>

using namespace std;

int num_edges;
list<int>* adj_list;

int* colors;

void add_edge(int u, int v){
    adj_list[u].push_back(v);
    adj_list[v].push_back(u);
}

bool canUseColor(int k, int col){  //funckja odpowiadajaca temu co dzieje sie
    for(const auto& i : adj_list[k]){  //w 5 lini funkcji z wykladu
        if(colors[i]==col && i<k){  //za pomoca petli sprawdzamy czy mozemy pokolorowac
            return false;  //dany wierzcholek za pomoca koloru col
        }
    }
    return true;
}

bool colorGraph(int k, int no_of_cols){  //funckja z wykladu
    if(k==num_edges+1){
        return true;
    }

    for(int col=1; col<=no_of_cols; col++){
        if(canUseColor(k, col)){
            colors[k]=col;

            if(colorGraph(k+1, no_of_cols)){
                return true;
            }
        }
    }
    return false;
}

bool isColorable(int no_of_cols){  //funkcja z wykladu
    colors[1]=1;
    for(int i=2; i<=num_edges; i++){
        colors[i]=0;
    }
    return colorGraph(2, no_of_cols);
}

bool areNeighbors(int v, const set<int>& clique){  //funckja sprawdzajaca czy wierzcholek v jest sasiadem wszystkich elementow z kliki
    for(int u : clique){
        auto it=find(adj_list[v].begin(), adj_list[v].end(), u);  //szuka u w danym zakresie
        if(it==adj_list[v].end()){
            return false;
        }
    }
    return true;
}

void findCliques(int k, set<int>& candidates, set<int>& current_clique, list<set<int>>& cliques){  //algorytm znajdujacy wszystkie kliki w grafie
    if(candidates.empty()){
        cliques.push_back(current_clique);
        return;
    }

    set<int> candidates_copy=candidates;
    for(int v : candidates_copy){
        if(areNeighbors(v, current_clique)){
            current_clique.insert(v);
            set<int> new_candidates;
            for(int u : candidates){
                auto it = find(adj_list[v].begin(), adj_list[v].end(), u);
                if(it!=adj_list[v].end()){
                    new_candidates.insert(u);
                }
            }
            findCliques(k+1, new_candidates, current_clique, cliques);
            current_clique.erase(v);
            candidates.erase(v);
        }
    }
}

set<int> findLargestClique(){  //funkcja bioraca najwieksza klike ze wszystkich klik w naszym grafie
    set<int> candidates;
    for(int i=1; i<=num_edges; i++){
        candidates.insert(i);
    }

    set<int> current_clique;
    list<set<int>> cliques;

    findCliques(1, candidates, current_clique, cliques);

    set<int> largest_clique;
    for (const auto& clique : cliques){
        if (clique.size() > largest_clique.size()) {
            largest_clique = clique;
        }
    }

    return largest_clique;
}

int main(){
    ifstream file("/home/dorian/CLionProjects/untitled/zwiazki.txt");  //wczytujemy plik tekstowy

    file>>num_edges;

    adj_list = new list<int>[num_edges+1];  //tworzymy liste sasiedztwa oraz tablice kolorow
    colors = new int[num_edges+1];

    int u, v;
    while(file>>u>>v){  //dodajemy krawedzie
        add_edge(u, v);
    }

    file.close();

    int no_of_cols=1;  //zaczynamy od 1 koloru
    while(true){
        if(isColorable(no_of_cols)){  //jezeli mozemy pokolorowac graf tyloma kolorami
            for(int i=1; i<=no_of_cols; i++){  //to wypisujemy wszystkie "samochody" czyli wierzcholki tych samych
                cout<<"Samochód "<<i<<": ";  //kolorow
                for(int j=1; j<=num_edges; j++){
                    if(colors[j]==i){
                        cout<<j<< " ";
                    }
                }
                cout<<endl;
            }
            break;
        }
        no_of_cols++;
    }

    set<int> largest_clique = findLargestClique();  //znajdujemy najbardziej niebezpieczne polaczenie, czyli najwieksza podklike w naszym grafie
    cout<<"Maksymalnie niebezpieczny układ ma "<<largest_clique.size()<<" elementy."<<endl;
    cout<<"Przykładowy taki układ: ";
    for(int v : largest_clique){
        cout<<v<<" ";
    }
    cout<<endl;

    delete[] adj_list;  //zwalniamy pamiec
    delete[] colors;

    return 0;
}
/*model matematyczny do zadania:
 * nasze "niebezpieczne polaczenia substancji" to krawedzie w naszym grafie ktorego wierzcholkami sa
 * wszystkie dostepne nam substancje, natomiast samochody do optymalnego wyjazdu to zbiory wierzcholkow
 * ktore zostaly pomalowane tym samym kolorem, maksymalnie niebezpieczne polaczenie to najwieksza
 * podklika w naszym grafie*/