#pragma once
#include "Z3.h"
#include <iostream>
#include <sstream>
#include <string>
#include <cstdlib>
#include <cstring>

using namespace std;

class Polynomial
{
    public:
    Z3* tab = (Z3*)malloc(0*sizeof(Z3));
    unsigned int stopien;

    Polynomial();
    //~Polynomial();
    Polynomial(unsigned int rozmiar, Z3* tab1);
    Polynomial(unsigned int a);
    Z3 operator[](unsigned int p)const;
    unsigned int degree()const;
    Polynomial& operator+=(const Polynomial& a);
    Polynomial& operator-=(const Polynomial& a);
    Polynomial& operator*=(const Z3& a);
    Polynomial& operator*=(const Polynomial& a);
    Polynomial& operator/=(const Z3& a);
    std::string toString(std::string xVar) const;
};
Polynomial operator+(const Polynomial& a, const Polynomial& b);
Polynomial operator-(const Polynomial& a, const Polynomial& b);
Polynomial operator*(const Polynomial& a, const Z3& b);
Polynomial operator*(const Polynomial& a, const Polynomial& b);
Polynomial operator/(const Polynomial& a, const Z3& b);
ostream& operator<<(ostream& out, const Polynomial& a);
istream& operator>>(istream& is, Polynomial& poly);
