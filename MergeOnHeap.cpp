#include <iostream>
#include <vector>
#include <cstring>

using namespace std;

class Node {
public:
    char name[32];
    int rzad;
    int prestiz;
    int ilosc;

    Node() : rzad(0), prestiz(0), ilosc(0) {
        name[0] = '\0';
    }

    Node(const char* name1, int rzad1, int prestiz1, int ilosc1)
        : rzad(rzad1), prestiz(prestiz1), ilosc(ilosc1) {
        strncpy(name, name1, sizeof(name));
        name[sizeof(name) - 1] = '\0';
    }
};

class Heap {
public:
    vector<Node> heap;
    int size;
    int capacity;

    Heap(int max_capacity) {
        capacity = max_capacity;
        heap.resize(max_capacity);
        size = 0;
    }

    void downheap(int k, int n) {
        int j;
        Node tmp = heap[k];
        while (k < n >> 1) {
            j = 2 * k + 1;

            if (j < n - 1 && compare(heap[j + 1], heap[j])) {
                j++;
            }

            if (compare(tmp, heap[j])) {
                break;
            }

            heap[k] = heap[j];
            k = j;
        }
        heap[k] = tmp;
    }

    void upheap(int k) {
        int i = (k - 1) >> 1;
        Node tmp = heap[k];

        while (k > 0 && compare(tmp, heap[i])) {
            heap[k] = heap[i];
            k = i;
            i = (i - 1) >> 1;
        }
        heap[k] = tmp;
    }

    void insert(Node element) {
        heap[size] = element;
        upheap(size);
        size++;
    }

    bool compare(const Node& a, const Node& b) {
        if (a.prestiz != b.prestiz) {
            return a.prestiz < b.prestiz;
        }
        if (a.ilosc != b.ilosc) {
            return a.ilosc > b.ilosc;
        }
        return a.rzad < b.rzad;
    }

    Node deleteMax() {
        Node maxElement = heap[0];
        heap[0] = heap[size - 1];
        size--;
        downheap(0, size);
        return maxElement;
    }
};

void mergeList(int k, vector<vector<Node>>& heaps) {
    Heap heap(k);

    for (int i = 0; i < k; i++){
            heap.insert(heaps[i].back());
            heaps[i].pop_back();

            if (!heaps[i].empty()) {
                heaps[i].back().ilosc--;
            }
    }

    while (heap.size != 0) {
        Node curr = heap.deleteMax();
        puts(curr.name);

        if (!heaps[curr.rzad].empty()) {
            int ilop = heaps[curr.rzad].back().ilosc;
            heap.insert(heaps[curr.rzad].back());
            heaps[curr.rzad].pop_back();
            if (!heaps[curr.rzad].empty()) {
                heaps[curr.rzad].back().ilosc = ilop - 1;
            }
        }
    }
}

int main() {
    int M;
    char name3[32];
    scanf("%d", &M);
    vector<vector<Node>> heaps;
    heaps.reserve(M);

    for (int i = 0; i < M; i++) {
        int rzad3 = i;
        int ilosc3;
        scanf("%d", &ilosc3);

        vector<Node> a(ilosc3);

        for (int j = 0; j < ilosc3; j++) {
            int prestiz3;
            scanf("%d %s", &prestiz3, name3);
            Node node(name3, rzad3, prestiz3, ilosc3);
            a[ilosc3 - j - 1] = node;
        }
        heaps.push_back(a);
    }

    mergeList(M, heaps);

    return 0;
}
