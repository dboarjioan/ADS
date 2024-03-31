//Dorian Rzasa
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Edge {
    int u, v, w;
};

class DisjointSet {
public:
    DisjointSet(int n) : parent(n + 1), rank(n + 1, 1) {
        for (int i = 1; i <= n; ++i)
            parent[i] = i;
    }

    int findSet(int u) {
        if (u != parent[u])
            parent[u] = findSet(parent[u]);
        return parent[u];
    }

    void unionSets(int u, int v) {
        int rootU = findSet(u);
        int rootV = findSet(v);

        if (rootU != rootV) {
            if (rank[rootU] > rank[rootV])
                parent[rootV] = rootU;
            else if (rank[rootU] < rank[rootV])
                parent[rootU] = rootV;
            else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
        }
    }

private:
    vector<int> parent;
    vector<int> rank;
};

int kruskal(int n, vector<Edge>& edges) {
    sort(edges.begin(), edges.end(), [](const Edge& a, const Edge& b) {
        return a.w < b.w;
    });

    DisjointSet ds(n);
    int totalWeight = 0;

    for (const Edge& edge : edges) {
        int rootU = ds.findSet(edge.u);
        int rootV = ds.findSet(edge.v);

        if (rootU != rootV) {
            ds.unionSets(edge.u, edge.v);
            totalWeight += edge.w;
        }
    }

    return totalWeight;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int Z;
    cin >> Z;

    for (int z = 0; z < Z; ++z) {
        int Nk, Mk;
        cin >> Nk >> Mk;

        vector<Edge> edges(Mk);
        for (int i = 0; i < Mk; ++i) {
            cin >> edges[i].u >> edges[i].v >> edges[i].w;
        }

        int result = kruskal(Nk, edges);
        cout << result << endl;
    }

    return 0;
}
