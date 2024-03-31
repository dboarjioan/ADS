#include <iostream>

using namespace std;

int** multiply(int** a, int** b, int size, int mod)
{
    int** mul = new int*[size];
    for (int i = 0; i < size; ++i)
    {
        mul[i] = new int[size]();
        for (int j = 0; j < size; ++j)
        {
            for (int k = 0; k < size; ++k)
            {
                mul[i][j] = (mul[i][j] + ((unsigned long long)a[i][k] * b[k][j]) % mod) % mod;
            }
        }
    }
    return mul;
}

int** power(int** G, int size, unsigned long long n, int mod)
{
    int** res = new int*[size];
    for (int i = 0; i < size; ++i)
    {
        res[i] = new int[size]();
        res[i][i] = 1;
    }

    while (n > 0)
    {
        if (n % 2 == 1)
        {
            int** tempRes = multiply(res, G, size, mod);
            for (int i = 0; i < size; ++i)
            {
                for (int j = 0; j < size; ++j)
                {
                    res[i][j] = tempRes[i][j];
                }
            }
            for (int i = 0; i < size; ++i)
            {
                delete[] tempRes[i];
            }
            delete[] tempRes;
        }
        int** tempG = multiply(G, G, size, mod);
        for (int i = 0; i < size; ++i)
        {
            for (int j = 0; j < size; ++j)
            {
                G[i][j] = tempG[i][j];
            }
        }
        for (int i = 0; i < size; ++i)
        {
            delete[] tempG[i];
        }
        delete[] tempG;
        n /= 2;
    }

    return res;
}

int main()
{
    int Z;
    cin >> Z;

    while (Z--)
    {
        unsigned int Nk, Mk;
        unsigned long long Lk;
        cin >> Nk >> Lk >> Mk;

        int** G = new int*[Nk];
        for (unsigned int i = 0; i < Nk; ++i)
        {
            G[i] = new int[Nk]();
            unsigned int cm;
            cin >> cm;

            while (cm--)
            {
                unsigned int dest;
                cin >> dest;
                G[i][dest] = 1;
            }
        }

        int** result = power(G, Nk, Lk, Mk);

        cout << result[0][Nk - 1] << endl;

        for (unsigned int i = 0; i < Nk; ++i)
        {
            delete[] G[i];
            delete[] result[i];
        }
        delete[] G;
        delete[] result;
    }

    return 0;
}
