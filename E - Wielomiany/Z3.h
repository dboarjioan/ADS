#pragma once
#include <iostream>
#include <sstream>
#include <string>
#include <cstdlib>
#include <cstring>

using namespace std;

class Z3
{
    public:

  short int x;

  Z3();
  Z3(short int a);
  Z3& operator+=(const Z3& a);
  Z3& operator-=(const Z3& a);
  Z3& operator*=(const Z3& a);
  Z3& operator/=(const Z3& a);
  operator short int()const;

};

Z3 operator+(const Z3& a, const Z3&b);
Z3 operator-(const Z3& a, const Z3&b);
Z3 operator*(const Z3& a, const Z3&b);
Z3 operator/(const Z3& a, const Z3&b);
ostream& operator<<(ostream& out, const Z3& a);

