#include <iostream>
#include <vector>

using namespace std;

void printMaxSubSquare(const vector<vector<bool> >& screen) {
    int H = screen.size();
    int W = screen[0].size();

    vector<int> S(W, 0);

    int max_of_s = 0;
    int max_i = 0;
    int max_j = 0;

    for (int i = 0; i < H; i++) {
        int prev = 0;
        for (int j = 0; j < W; j++) {
            int temp = S[j];
            if (i == 0 || j == 0) {
                S[j] = screen[i][j] ? 1 : 0;
            } else if (screen[i][j]) {
                int min_val = min(prev, min(S[j - 1], S[j]));
                S[j] = min_val + 1;
            } else {
                S[j] = 0;
            }

            prev = temp;

            if (S[j] > max_of_s) {
                max_of_s = S[j];
                max_i = i;
                max_j = j;
            }
        }
    }

    cout << max_of_s << "\n";
}

int main() {
    int Z;
    cin >> Z;

    for (int z = 0; z < Z; z++) {
        int H, W;
        cin >> H >> W;

        vector<vector<bool> > screen(H, vector<bool>(W, false));
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                char pixel;
                cin >> pixel;
                screen[i][j] = (pixel == 'o');
            }
        }

        printMaxSubSquare(screen);
    }

    return 0;
}
