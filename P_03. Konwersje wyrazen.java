import java.util.Scanner;

public class Source
{
	public static void main(String[] args){
	
        Scanner scanner=new Scanner(System.in);
    
    String Trick=scanner.nextLine();  //sciagamy sobie linie tekstu aby nie bylo bledu
    int ilosc=Integer.parseInt(Trick);  //nastepnie ta linie zamieniamy na inta
    for(int i=0;i<ilosc;i++)            //poniewaz wiemy ze zawiera ona tylko liczbe naturalna
    {
        String a=scanner.nextLine();  //dla kazdego wyrazenia z testu sciagamy linie
        String cleanedExpression1 = a.replace("INF: ", "");  //zamieniamy w naszym wyrazeniu "INF: " na ""
        String cleanedExpression2 = a.replace("ONP: ","");  //zamieniamy w naszym wyrazeniu "ONP: " na ""
        if(a.equals(cleanedExpression1))  //jesli po usunieciu z wyrazenia "INF: " dalej mamy to samo wyrazenie to znaczy ze jest to onp
        {
        String b=PoprawnoscOnp(cleanedExpression2);  //korzystamy z funkcji usuwajacej zbedne symbole
	     if(Obliczalnosc(b))  //jezeli wyrazenie jest obliczalne to wypisujemy jego zamiane na inf
	     {
	         System.out.println("INF: "+postfixToInfix(b));
	     }
	     else  //w innym wypadku wypisujemy stosowana wiadomosc
	     {
	         System.out.println("INF: error");
	     }
        }
        else if(a.equals(cleanedExpression2))  //w innym wypadku usunelosmy "INF: " i zamienilismy na "", oznacza to ze jest to wyrazenie inf
        {
        String b=PoprawnoscInf(cleanedExpression1);  //korzystamy z funkcji usuwajacej zbedne symbole
	    if(Automat(b))  //jezeli wyrazenie przechodzi zadany automat to przechodzimy do zamiany go na onp
	     {
	         System.out.println("ONP: "+infixToPostfix(b));
	     }
        
        else  //w innym wypadku natychmiast wypisujemy stosowna wiadomosc
            {
               System.out.println("ONP: error");
            }
        }
        
    }
    
}

static String PoprawnoscInf(String wyrazenie){  //funkcja usuwajaca zbedne symbole w onp
    String wynik="";
    
    for (int i = 0; i < wyrazenie.length(); i++){
        char c = wyrazenie.charAt(i);
        if((c == '^' || c == '*' || c == '/' || c == '%' || c == '+' || c == '-' || c == '<' || c == '>' || c == '?' || c == '&' || c == '|' || c == '=' || c == '~' || c == '!' || c=='(' || c==')') || Character.isLetter(c)) 
        {
            wynik=wynik+c;
        }
    }
            return wynik;
    }
    
static String PoprawnoscOnp(String wyrazenie){  //funkcja usuwajaca zbedne symbole w inf
    String wynik="";
    
    for (int i = 0; i < wyrazenie.length(); i++){
        char c = wyrazenie.charAt(i);
        if((c == '^' || c == '*' || c == '/' || c == '%' || c == '+' || c == '-' || c == '<' || c == '>' || c == '?' || c == '&' || c == '|' || c == '=' || c == '~' || c == '!') || Character.isLetter(c)) 
        {
            wynik=wynik+c;
        }
    }
            return wynik;
    }

static boolean Automat(String wyrazenie){  //automat do sprawdzania popranosci wyrazenia inf
    int stan = 0;
    int ilosc_nawiasow = 0;
    int ilosc_operandow = 0;
    int ilosc_op2=0;
    
    for (int i = 0; i < wyrazenie.length(); i++){  //petla do przejscia przez cale wyrazenie
        char c = wyrazenie.charAt(i);  //sciagamy i'ty element stringa i zamieniamy go na chara
        
        if (c == '(') {  //jesli jest to nawias otwierajacy to podnosimy zmienna ilosc_nawiasow
           ilosc_nawiasow++;
        }

        if (c == ')') {  //analogicznie do wyzszego z ta roznica ze opuszczamy
            ilosc_nawiasow--;
            if (ilosc_nawiasow < 0) {  //jesli na tym etapie wystepuje wiecej nawiasow zamykajacych to oznacza to ze wyrazenie jest niepoprawne
            return false;
        } 
        }

        if (stan == 0) {  //jestesmy w stanie 0
            if (c == '(') {  //jesli nasz znak to '(' to zmieniamy stan na 0 i przechodzimy do kolejnego symbolu, wszystkie poprawne przejscia sa analogiczne
                stan = 0;
                continue;
            }

            if (c == '~' || c == '!') {
                stan = 2;
                continue;
            }

            if (Character.isLetter(c)){
                ilosc_operandow++;  //zwiekszamy ilosc operandow
                stan = 1;
                continue;
            }
            else  //jesli w stanie 0 pojawil sie inny znak to zanczy ze nasz automat nie akceptuje danego wyrazenia wiec zwracamy falsz, wszystkie tego typu przehscia sa analogiczne
            {
                return false;
            }

        } 
        else if (stan == 1) {  //jestesmy w stanie 1

            if (c == '^' || c == '*' || c == '/' || c == '%' || c == '+' || c == '-' || c == '<' || c == '>' || c == '?' || c == '&' || c == '|' || c == '=') {
               ilosc_op2++;  //zwiekszamy ilosc operatorow 2 argumentowych
                stan = 0;
                continue;
            }
            if (c == ')') {
                stan = 1;
                continue;
            }
            else
            {
                return false;
            }
        } 
        else if (stan == 2) {  //jestesmy w stanie 2
            if (c == '~' || c == '!') {
                stan = 2;
                continue;
            }

            if (Character.isLetter(c)) {
                ilosc_operandow++;
                stan = 1;
                continue;
            }
            if(c=='(')
            {
                stan=0;
                continue;
            }
            else
            {
                return false;
            }
        }

    }

    if (stan==1 && ilosc_nawiasow==0 && ilosc_operandow==ilosc_op2+1){ //jesli jestesmy w stanie akceptujacym oraz zgadza sie ilosc nawiasow
       //i ilosc operatorow 2 argumentowych jest o jeden mniejsza niz ilosc operandow to funckja zwraca prawde
            return true;
        } 
    else {  //w innym przypadku funckja zwraca falcz
        return false;
    }
}

static boolean Obliczalnosc(String wyrazenie){  //funkcja sprawdzajaca obliczalnosc wyrazenia onp
    //klasyczne obliczanie wyrazenia onp 
  Stack<String> stack=new Stack<String>();
    for (int i = 0; i < wyrazenie.length(); i++){  //przechodzimy przez cale wyrazenie 
        char c = wyrazenie.charAt(i);
        if(Character.isLetter(c))  //jezeli jest to litera, pushujemy na stack
        {
            stack.push(""+c);
        }
       else if(c == '^' || c == '*' || c == '/' || c == '%' || c == '+' || c == '-' || c == '<' || c == '>' || c == '?' || c == '&' || c == '|' || c == '=')
       {  //jezeli jest to operator dwuargumentowy
           if(stack.isEmpty())  //jezeli stos jest pusty, zwracamy flasz
           {
               return false;
           }
           String a=stack.pop();  //popujemy ze stosu 
           if(stack.isEmpty())  //jezeli stos jest pusty, zwracamy flasz
           {
               return false;  
           }
           String b=stack.pop();  //popujemy ze stosu
           String q=""+b+c+a;  //tworzymy stringa pierwsze wyrazenie, operator, drugie wyrazenie
           stack.push(q);  //odkladamy powstale wyrazenie na stos
       }
       else if(c == '!' || c == '~')  //jesli jest to operator jednoargumentowy
       {
           if(stack.isEmpty())  //jezeli stos jest punsty zwracamy falsz
           {
               return false;
           }
           String a=stack.pop();  //w innym wypadku popujemy stos
           String q=""+c+a;  //tworzymy wyrazenie operator, wyrazenie
           stack.push(q);  //odkladamy powstale wyrazenie na stos
       }
    }
    if(stack.size()==1)  //jezeli na stosie zostalo tylko jedno wyrazenie, oznacz to ze dane wyrazenie bylo poprawne, funckja zwraca wtedy prawde
    {
        return true;
    }
    else{  //w innym wypadku wyrazenie bylo bledne, funckja zwraca wtedy falsz
        return false;
    }
}

    static int Prec(char ch)  //funkcja zwracajaca priorytety
    {
        switch (ch) {
        
        case '!':
        case '~':
            return 9;
            
        case '^':
            return 8;
            
        case '*':
        case '/':
        case '%':
        return 7;
        
        case '+':
        case '-':
        return 6;
        
        case '<':
        case '>':
        return 5;
        
        case '?':
        return 4;
        
        case '&':
        return 3;
        
        case '|':
        return 2;
        
        case '=':
        return 1;
        
        case '@':  //potrzebne do korzystania z oepratorow jednoargumentowych
        return 10;
        
        }
        return -1;
    }
  
   static String infixToPostfix(String exp)  //funkcja zamieniajaca wyrazenie infix do postfix(onp)
{
        String result = new String("");
  
        Stack<Character> stack=new Stack<Character>();
        
        int i=0;
        while(i<exp.length())  //przechodzimy przez cale wyrazenie 
        {
            char c = exp.charAt(i);

        
        if (Character.isLetter(c))  //jesli jest to litera pushujemy ja do kolejki
            {
                result = result+""+c;
            }

        else if (c == '(')  //jesli jest to nawias otwieraacy to pushujemy go na stos
            {
                stack.push(c);
            }
            
        else if (c == ')')  //jesli jest to nawias zamykajacy
            {
            while (!stack.isEmpty() && stack.peek() != '(')  //poki stos nie jest pusty oraz jego wyraz nie jest nawiasem otwierajacym
            {
                result = result+""+stack.pop();  //pushujemy pop ze stacka do kolejki
            }
            if (!stack.isEmpty() && stack.peek() == '(')  //jezeli stos nie jest pusty oraz jego wyraz to nawias otwierajacy
                {
                    stack.pop();                          //sciagamy go ze stosu
                }
            }
        
        else if(c == '~' || c == '!' || c == '^' || c == '*' || c == '/' || c == '%' || c == '+' || c == '-' || c == '<' || c == '>' || c == '?' || c == '&' || c == '|' || c == '=')  //jesli jest to jakis operator
            {
            if(c=='!' || c=='=' || c=='~' || c=='^')  //jesli jest to operator laczacy z prawej strony
            {
              while (!stack.isEmpty() && Prec(c) < Prec(stack.peek())) {  //dopoki stos nie jest pusty oraz priorytet wyrazenia c jest mniejsze niz priorytet kolejnego wyrazenia ze stosu
                result = result + "" + stack.pop();  //do kolejki dodajemy wyrazenie ze stosu
            }
            stack.push(c);  //pushujemy na stos wyrazenie c
            }
            else  //w innym wypadku jest to operator dwuargumentowy
            {
            while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())) {  //dopoki stos nie jest pusty oraz priorytet wyrazenia c jest niewiekszy niz priorytet kolejnego wyrazenia ze stosu
                result = result + "" + stack.pop();  //do kolejki dodajemy wyrazenie ze stosu
            }
            stack.push(c);  //pushujemy na stos wyrazenie c
            }
            }
        
        i++;  //iterujemy zmienna do przeszukiwania
        }
        
        while (!stack.isEmpty()) {  //dopoki stos nie jest posty  
        result = result + "" +stack.pop();  //dodajemy do kolejki wyrazenie ze stosu
    }
     
        String re=result;
        String wy="";  
    for(int q=0;q<re.length();q++)  //petla do dodawania spacji miedzy znakami
    {
        char f = re.charAt(q);
        wy=""+wy+f+" ";
    }
    return wy;
        
    }


    static String postfixToInfix(String exp){  //funkcja zamieniajaca wyrazenie postfix do infix tak aby zawieralo minimalna ilosc nawiasow
                                               //do tego aby kolejnosc wykonywania operacji zostala zachowana
        String result = new String("");
  
        Stack<String> stack=new Stack<String>();
        Stack<Character> last_operator= new Stack<Character>();  //stack ostatnich operatorow
        int i=0;
        while(i<exp.length())  //przechodzimy przez cale wyrazenie
        {
        char c = exp.charAt(i);
        
        if (Character.isLetter(c))  //jezeli jest to litera
            {
                stack.push(""+c);  //na stos pushujemy ta litere
                last_operator.push('@');  //na stos ostatnich operatorow pushujemy @ poniewaz ma ono najwyzszy operator i sprawia ze gdy wyrazeniu jest przypisany ten znak, traktowany jest on jako jednosc
            }

        else if (c == '*' || c == '/' || c == '%' || c == '+' || c == '-' || c == '<' || c == '>' || c == '?' || c == '&' || c == '|' || c == '=' || c == '^')  //jesli jest to operator dwuargumentowy
        {
            String x,y;  
            if(!stack.isEmpty())  //jesli stos nie jest pusty
          {
              x=stack.pop();  //popujemy wyrazenie ze stosu
          }
          else
          {
              return "error";  //w innym wypadku zwracamy "error" jako odpowiedz
          }
          
          if(!stack.isEmpty())  //jesli stos nie jest pusty
          {
              y=stack.pop();  //popujemy wyrazenie ze stosu
          }
          else
          {
              return "error";  //w innym wypadku zwracamy "error" jako odpowiedz
          }
          
          char wartosc_x1=last_operator.pop();  //popujemy ze stacku ostatnich operatorow
          char wartosc_y1=last_operator.pop();  //popujemy ze stacku ostatnich operatorow
          
          int wartosc_x=Prec(wartosc_x1);  //nadajemy zmiennym wartosc_x/y wartosc priorytetow tych znakow
          int wartosc_y=Prec(wartosc_y1);
          
          if(wartosc_x<=Prec(c))  //jezeli wartosc ostatniego operatora drugiego wyrazenia jest nie wieksza niz naszego operatora c
          {                       //to wstawiamy nawiasy
            x="("+x+")";
          }
          
          if(wartosc_y<Prec(c))  //jezeli wartosc ostatniego operatora pierwszego wyrazenia jest mniejsza niz naszego operatora c
          {                      //to wstawiamy nawiasy
            y="("+y+")";
          } 
          
        stack.push(y+c+x);  //na stos pushujemy y,c,x
        last_operator.push(c);  //na stos ostatnich operatorow pushujemy operator c jako ostatni operator laczacy 
          
        }
        
        else if( c== '!' || c=='~')  //jezeli jest to operator jednoargumentowy
        {
            String x;
            if(!stack.isEmpty())  //jesli stos nie jest pusty
          {
              x=stack.pop();  //popujemy wyrazenie ze stosu
          }
          else
          {
              return "error";  //w innym wypadku zwracamy "error" jako odpowiedz
          }
          
          char wartosc_x1=last_operator.pop();  //popujemy ze stacku ostatnich operatorow
          int wartosc_x=Prec(wartosc_x1);  //nadajemy wartosc zmiennej wartosc_x wartosc operatora
          
          if(wartosc_x!=10)  //jezeli wartosc danego operatora jest rozna od 10 to wstawiamy nawiasy
          {                  //10 pojawia sie wtedy gdy wyrazenie jest postaci "~~!~!~!~~x", "x" lub tym podobne
            x="("+x+")";     //wtedy mozna bez obaw pominac nawiasy
          }
          
        stack.push(c+x);  //na stos pushujemy c,x
        last_operator.push('@');  //na stos operatorow pushujemy @ aby traktowac to wyrazenie jako calosc
        }
        i++;
        }

    String re=stack.pop();  
        String wy="";
    for(int q=0;q<re.length();q++)  //dodajemy do wyrazenia nawiasy
    {
        char f = re.charAt(q);
        wy=""+wy+f+" ";
    }
    return wy;
        
    }
    
    static class Stack<T> {  //stack
    private Object[] array;  
    private int size;
    private int capacity;

    public Stack() {  // konstruktor stosu, tworzy stos danego obiektu o wielkosci maksymalnej 10, ustawia rozmiar na 0
        capacity = 10;
        array = new Object[capacity];
        size = 0;
    }

    public void push(T data) {  //funckja push, jezeli rozmiar jest rowny maksymalnemu rozmiarowi, to powiekszamy stos
        if (size == capacity) {
            resize();
        }
        array[size++] = data;  //dodajemy do stosu dany argument
    }

    public T pop() {  //funkcja sciagajaca ostatnia wartosc ze stosu, jesli stos jest pusty, zwracany jest null
        if (isEmpty()) {
            return null;
        }
        T data = (T) array[--size];
        array[size] = null;
        return data;
    }

    public T peek() {  //funkcja zwracajajaca null jesli stos jest pusty a w innym wypadku ostatni element ze stacku jednoczesnie go nie sciagajac
        if (isEmpty()) {
            return null;
        }
        return (T) array[size - 1];
    }

    public boolean isEmpty() {  //funkcja sprawdzajaca czy stos jest pusty
        return size == 0;
    }

    public int size() {  //funckaj zwracajaca wielkosc stacku
        return size;
    }

    private void resize() {  //funkcja zwiekszajaca maksymalna wielkosc stacku 
        capacity *= 2;  //zwiekszamy dwukrotnie wielkosc maksymalna stosu
        Object[] newArray = new Object[capacity];  //tworzymy nowy stos
        System.arraycopy(array, 0, newArray, 0, size);  //"przepisujemy" do nowego stosu stary
        array = newArray;  //podmieniamy 
    }
    }
}

/*
Przykladowe wejscie:
89
INF: !!!~g
ONP: xj*!~!
INF: /&ibg*ec<
ONP: bii+/b!%~/-e*f-!fe
INF: %*=(/|de|-&
ONP: -(ic-!||f%
INF: !/=h~<*g-!*c(/~d
ONP: %g
INF: c!|<%*+<>=~-ed
ONP: <bf%!+=|ha~hc)*)!ba^
INF: *f&gacg*
ONP: ky>j!<su%hf|>-za~a!^**
ONP: c~~h!~=sy=ig=&tr%zu=^^|
ONP: vc~!h^|
INF: i
INF: !q+(!~o-u)
ONP: bt+oj>-h~m!>*!
ONP: xd!>r~t~*~&
ONP: mi<~df-!&!
INF: !d=!k*v
ONP: l~~pe|dr>*~%
INF: ((u/t&j+o)>!w>v)/((w<u)+(m=f)&((k=q)|!f))
ONP: u~vb>>~~
INF: v
ONP: k!
ONP: tk>yo==m~sa*+%!
INF: ~(q-j)%(t>f)%j
ONP: q~!~uq^bh<=bx&h~^>+
ONP: sp-n~-~y~~x&=
INF: d
ONP: da~~-oa%s!+ut*ei/>--
INF: !j/f>!d<j>(!a+!z&(m%j)^!l)
INF: ~~!k<~!y
INF: ~r|i=j<a/(w-(s%b=~c))
INF: !!f%j&~s-z
INF: ~a-s-(o&z)=~n*k
INF: q
INF: ~~l%(a<h)=((d|n)%(h+i))^(n*e+(s=q))
INF: !!~!w
ONP: qx/ak|<t!xg<<=so+~o~zx|=/=
ONP: cw+do&%!~
INF: ~~c|f<~m%a+~v
INF: ~~p<m-(!n&!o)
ONP: wl^we%*y~o!*-m+
ONP: iz%!msx^%|h~cz>*xp+rd+%|<
INF: l=!~~w
INF: !~i<j/~b^u
INF: (!g>o>(g&q)^~g)^!i
INF: !s^(y-l)+~r<t>!(p|q)/!i
ONP: i~a=fz%q~&/gdj~-++
ONP: co+!~!
ONP: qa|!!hn-sf>=ae^sw|&%>
ONP: pg+q~|!!
INF: !!w^(!t<f%h)
ONP: f
ONP: ch<ur<^c!rt=%/c|
INF: |/&~hbh%b
ONP: -!i^&|+=h&~>gb*+<
INF: (ae**<-h=)+
ONP: <-c</h|a=dahee<
INF: h<<-c
ONP: <h(gddg<*&=h^
INF: ->(&/(=&<=!ch
ONP: /d~*(<g=!*
ONP: vsy%%~!
ONP: gf%j+j~=g%
INF: ~k|l-(!i-!l)+(!b=o-(v|x=x=q))
ONP: wd/nu<&tk%tq<</!
INF: !!p|(p&v)-(k=n)
ONP: u!wo*=jb|!/~
INF: m<~!r-(t+j)
INF: ~l%t^!x=a&(!o|~j)*!!q
ONP: i~l!u~oa%%-%
ONP: d!da=&wv=~*l!u~&^
INF: !(r<p)/(v&r)>(f=~l)
INF: !d*~l%s
ONP: v
INF: )<^d<c(dgi*^
ONP: ~/%~+cf*!-<e=<=
INF: ^/(%(-!+|<!geg*%>&*c
INF: /%&&*><%/bd(^/>%(-!
ONP: gg<
INF: eg)~<e!^|g<edi
ONP: )i(>
INF: ec/=/be<edd
ONP: ag/cfc+b*gb>h
INF: e!b
ONP: h)
INF: c=>bb-*-

Wyjscie:
ONP: g ~ ! ! ! 
INF: ! ~ ! ( x * j ) 
ONP: error
INF: error
ONP: error
INF: error
ONP: error
INF: error
ONP: error
INF: error
ONP: error
INF: ( ( k > y < ! j ) - ( s % u > ( h | f ) ) ) * ( z * ~ a ^ ! a ) 
INF: ( ~ ~ c = ~ ! h ) | ( ( s = y ) & ( i = g ) ) ^ ( ( t % r ) ^ ( z = u ) ) 
INF: v | ! ~ c ^ h 
ONP: i 
ONP: q ! o ~ ! u - + 
INF: ! ( ( b + t - ( o > j ) ) * ( ~ h > ! m ) ) 
INF: x > ! d & ~ ( ~ r * ~ t ) 
INF: ! ( ~ ( m < i ) & ! ( d - f ) ) 
ONP: d ! k ! v * = 
INF: ~ ~ l % ~ ( ( p | e ) * ( d > r ) ) 
ONP: u t / j o + & w ! > v > w u < m f = + k q = f ! | & / 
INF: ~ ~ ( ~ u > ( v > b ) ) 
ONP: v 
INF: ! k 
INF: ! ( ( t > k = ( y = o ) ) % ( ~ m + s * a ) ) 
ONP: q j - ~ t f > % j % 
INF: ~ ! ~ q + ( ( u ^ q = b < h ) > ( b & x ) ^ ~ h ) 
INF: ~ ( s - p - ~ n ) = ~ ~ y & x 
ONP: d 
INF: d - ~ ~ a - ( o % a + ! s - ( u * t > e / i ) ) 
ONP: j ! f / d ! > j < a ! z ! + m j % l ! ^ & > 
ONP: k ! ~ ~ y ! ~ < 
ONP: r ~ i | j a w s b % c ~ = - / < = 
ONP: f ! ! j % s ~ z - & 
ONP: a ~ s - o z & - n ~ k * = 
ONP: q 
ONP: l ~ ~ a h < % d n | h i + % n e * s q = + ^ = 
ONP: w ! ~ ! ! 
INF: q / x < ( a | k ) = ! t < ( x < g ) = ~ ( s + o ) / ( ~ o = z | x ) 
INF: ~ ! ( ( c + w ) % ( d & o ) ) 
ONP: c ~ ~ f m ~ a % v ~ + < | 
ONP: p ~ ~ m n ! o ! & - < 
INF: w ^ l * ( w % e ) - ~ y * ! o + m 
INF: ( ! ( i % z ) | m % s ^ x ) < ( ~ h * ( c > z ) | ( x + p ) % ( r + d ) ) 
ONP: l w ~ ~ ! = 
ONP: i ~ ! j b ~ u ^ / < 
ONP: g ! o > g q & g ~ ^ > i ! ^ 
ONP: s ! y l - ^ r ~ + t < p q | ! i ! / > 
INF: ( ~ i = a ) / ( f % z & ~ q ) + ( g + ( d - ~ j ) ) 
INF: ! ~ ! ( c + o ) 
INF: ! ! ( q | a ) > ( h - n = s > f ) % ( a ^ e & ( s | w ) ) 
INF: ! ! ( p + g | ~ q ) 
ONP: w ! ! t ! f h % < ^ 
INF: f 
INF: ( c < h ) ^ ( u < r ) / ( ! c % ( r = t ) ) | c 
ONP: error
INF: error
ONP: error
INF: error
ONP: error
INF: error
ONP: error
INF: error
INF: ! ~ ( v % ( s % y ) ) 
INF: ( g % f + j = ~ j ) % g 
ONP: k ~ l i ! l ! - - b ! o v x | x q = = - = + | 
INF: ! ( ( w / d & n < u ) / ( t % k < ( t < q ) ) ) 
ONP: p ! ! p v & k n = - | 
INF: ~ ( ( ! u = w * o ) / ! ( j | b ) ) 
ONP: m r ! ~ t j + - < 
ONP: l ~ t x ! ^ % a o ! j ~ | q ! ! * & = 
INF: ~ i % ( ! l - ~ u % ( o % a ) ) 
INF: ( ( ! d & ( d = a ) ) * ~ ( w = v ) ) ^ ( ! l & ~ u ) 
ONP: r p < ! v r & / f l ~ = > 
ONP: d ! l ~ * s % 
INF: v 
ONP: error
INF: error
ONP: error
ONP: error
INF: g < g 
ONP: error
INF: error
ONP: error
INF: error
ONP: error
INF: h 
ONP: error
*/
