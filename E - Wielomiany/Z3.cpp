#include <iostream>
#include <sstream>
#include <string>
#include <cstdlib>
#include <cstring>
#include "Z3.h"

using namespace std;

Z3::Z3()
{
    this->x=0;
}

  Z3::Z3(short int a){
      if(a>=0)
      {
          this->x=a%3;
      }
      else{
    this->x=(3+ a%3)%3;
      }
  }

  Z3::operator short int()const{
        return (short int)(this->x);
  }

  Z3& Z3::operator+=(const Z3& a){
    this->x = (this->x+a.x)%3;
    return *this;
  }

  Z3& Z3::operator-=(const Z3& a){
    this->x = ((this->x-a.x+3)%3);
    return *this;
  }

  Z3& Z3::operator*=(const Z3& a){
    this->x = (this->x*a.x)%3;
    return *this;
  }

  Z3& Z3::operator/=(const Z3& a){
    if(a.x!=0){
    this->x = (this->x*a.x)%3;
    return *this;
    }
    else
    {
        cout<<"Dzielenie przez zero\n";
        return *this;
    }
  }

Z3 operator+(const Z3& a, const Z3& b){
  Z3 q;
  q.x=0;
  q.x=(a.x%3 +b.x%3 +3)%3;
  return q;
}

Z3 operator-(const Z3& a, const Z3& b){
  Z3 q;
  q.x=0;
  q.x=(a.x%3 -b.x%3 +3)%3;
  return q;
}

Z3 operator*(const Z3& a, const Z3& b){
  Z3 q;
  q.x=(a.x*b.x)%3;
  return q;
}



Z3 operator/(const Z3& a, const Z3& b){
  Z3 q;
  q.x=0;
  if(b.x!=0)
  {
  q.x=(a.x*b.x)%3;
  return q;
  }
  else
  {
      cout<<"Dzielenie przez zero\n";
      return a;
  }
}

ostream& operator<<(ostream& out, const Z3& a){
out<<a.x;
return out;
}


