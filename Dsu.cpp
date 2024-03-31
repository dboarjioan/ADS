//Dorian Rzasa
#include <iostream>
#include <vector>
#include <unordered_map>
#include <unordered_set>
#include <algorithm>

using namespace std;

struct Pixel {
    int x, y;

    Pixel() : x(0), y(0) {}

    Pixel(int _x, int _y) : x(_x), y(_y) {}

    bool operator<(const Pixel &other) const {
        return (y < other.y) || (y == other.y && x < other.x);
    }
    
    bool operator==(const Pixel &other) const {
        return x == other.x && y == other.y;
    }
};

struct DSU {
    unordered_map<int, int> parent;

    int findSet(int v) {
        if (parent.find(v) == parent.end()) {
            parent[v] = v;
            return v;
        }
        return parent[v] == v ? v : parent[v] = findSet(parent[v]);
    }

    void unionSets(int a, int b) {
        int A = findSet(a);
        int B = findSet(b);
        if (A != B) {
            parent[B] = A;
        }
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int Z;
    cin >> Z;

    for (int z = 0; z < Z; ++z) {
        int H, W, M;
        cin >> H >> W >> M;

        DSU dsu;
        vector<Pixel> badPixels;

        for (int m = 0; m < M; ++m) {
            int x, y;
            cin >> x >> y;
            badPixels.push_back(Pixel(x, y));
        }

        sort(badPixels.begin(), badPixels.end());

        for (const auto &q : badPixels) {
            int x = q.x;
            int y = q.y;
            int pixelIdx = y * W + x;
            dsu.findSet(pixelIdx);

            auto isBadPixel = [&](int dx, int dy) {
                return binary_search(badPixels.begin(), badPixels.end(), Pixel(x + dx, y + dy));
            };

            if (y > 0 && isBadPixel(0, -1))
                dsu.unionSets(pixelIdx, pixelIdx - W);
            if (y < H - 1 && isBadPixel(0, 1))
                dsu.unionSets(pixelIdx, pixelIdx + W);
            if (x > 0 && isBadPixel(-1, 0))
                dsu.unionSets(pixelIdx, pixelIdx - 1);
            if (x < W - 1 && isBadPixel(1, 0))
                dsu.unionSets(pixelIdx, pixelIdx + 1);
        }

        unordered_set<int> components;
        vector<Pixel> BajoJajo;

        for (const auto &q : badPixels) {
            int x = q.x;
            int y = q.y;
            int pixelIdx = y * W + x;

            if (components.find(dsu.findSet(pixelIdx)) == components.end()) {
                components.insert(dsu.findSet(pixelIdx));
                BajoJajo.push_back(q);
            }
        }

        cout << BajoJajo.size() << endl;
        for (const auto &pixel : BajoJajo) {
            cout << pixel.x << " " << pixel.y << endl;
        }
    }

    return 0;
}