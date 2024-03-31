import java.util.Scanner;

public class Source
{
	public static void main(String[] args) {
	    
		Scanner scanner=new Scanner(System.in);
		
		String Trick=scanner.nextLine();  //szczytuje ilosc prob
		int ilosc=Integer.parseInt(Trick);  //zamieniam na inta
        
        for(int i=0;i<ilosc;i++)  
        {
            SingleLinkedList Lista_wszystkich_pociagow = new SingleLinkedList();  //tworze singlelinked liste do ktorej
            	                                                                  //bede dokladal nody jako nowe pociagi
            String Trickprim=scanner.nextLine();  //wpisuje ilosc testow
            int iloscprim=Integer.parseInt(Trickprim);  //zamieniam na inta
            
            for(int iprim=0;iprim<iloscprim;iprim++)
            {
                 String a=scanner.nextLine();  //skanuje polecenie
                 String cleanedExpression = a.replace("New ", "");  //nastepne ify odpowiadaja za rozpoznanie polecenia
                 
                 if(a.equals(cleanedExpression))//tzn ze nie New
                    {
                        cleanedExpression = a.replace("InsertFirst ", "");  
                        if(a.equals(cleanedExpression))  //tzn ze nie InsertFirst
                        {
                            cleanedExpression = a.replace("InsertLast ", "");  
                        if(a.equals(cleanedExpression))  //tzn ze nie InsertLast
                        {
                            cleanedExpression = a.replace("Reverse ", "");  
                        if(a.equals(cleanedExpression))  //tzn ze nie tzn ze nie Reverse
                        {
                            cleanedExpression = a.replace("Union ", "");  
                        if(a.equals(cleanedExpression))  //tzn ze nie Union
                        {
                            cleanedExpression = a.replace("DelFirst ", "");  
                        if(a.equals(cleanedExpression))  //tzn ze nie DelFirst
                        {
                            cleanedExpression = a.replace("DelLast ", "");  
                        if(a.equals(cleanedExpression))  //tzn ze nie DelLast
                        {
                            if(cleanedExpression.charAt(0)=='T')  //TrainList
                            {
                                System.out.print("Trains: ");
                             Lista_wszystkich_pociagow.displayList2();

                            }
                            else    //Display
                            {   cleanedExpression = a.replace("Display ", "");
                            String T="";  
                                 for(int Q=0;Q<cleanedExpression.length();Q++)  //petla do sciagniecia nazwy pociagu
                                 {
                                    if(cleanedExpression.charAt(Q)==' ')
                                    {
                                        break;
                                    }
                                    else
                                    {
                                        T=T+""+cleanedExpression.charAt(Q);
                                    }
                                 }
                                System.out.print(T+": ");
                                Lista_wszystkich_pociagow.findNodeByName(T).list.displayList();  //wypisanie konretnego pociagu
                            }
                        
                        }
                        else  //DelLast   
                        {     //analogicznie do DelFirst
                                                
                                                String T1 = cleanedExpression.substring(0, cleanedExpression.indexOf(" "));
                                                String T2 = cleanedExpression.substring(cleanedExpression.indexOf(" ") + 1);
                            
                                                 String w=Lista_wszystkich_pociagow.findNodeByName(T1).list.last.name;
                                                 Lista_wszystkich_pociagow.addNodeAtFront2(T2,w);
                                        		
	                                        	if(Lista_wszystkich_pociagow.findNodeByName(T1).list.IsOne()==true)
	                                        	{   
		                                            Lista_wszystkich_pociagow.removeNodeByName(T1);

	                                        	}
	                                        	else{

	                                            	Lista_wszystkich_pociagow.findNodeByName(T1).list.removeLastNode();
	                                        	}
                        }
                        
                        }
                        else  //DelFirst 
                        {
                                                String T1 = cleanedExpression.substring(0, cleanedExpression.indexOf(" "));  //sciagam nazwe pierwszego pociagu
                                                String T2 = cleanedExpression.substring(cleanedExpression.indexOf(" ") + 1);  //sciagam nazwe pociagu do stworzenia
                            
                                                 String w=Lista_wszystkich_pociagow.findNodeByName(T1).list.first.name;  //sciagam nazwe wagonu
                                                 Lista_wszystkich_pociagow.addNodeAtFront2(T2,w);  //tworze pociag o nazwie T2 i wagonie o nazwie w
                                                 
                                        
                                        		
	                                        	if(Lista_wszystkich_pociagow.findNodeByName(T1).list.IsOne()==true)  //jezeli pociag pierwszy ma tylko jeden wagon
	                                        	{                                                                    //to usuwamy go z listy pociagow
		                                            Lista_wszystkich_pociagow.removeNodeByName(T1);
		  
	                                        	}
	                                    else{
                                                Lista_wszystkich_pociagow.findNodeByName(T1).list.removeFirstNode();  //w innym przypadku usuwam jedynie pierwszy node w T1
	                                        }
                        }
                        }
                        else  //Union
                        {
                            String T1 = cleanedExpression.substring(0, cleanedExpression.indexOf(" "));  //sciagam nazwe pierwszego pociagu
                            String T2 = cleanedExpression.substring(cleanedExpression.indexOf(" ") + 1);  //sciagam nazwe drugiego pociagu
                            
                                Lista_wszystkich_pociagow.findNodeByName(T1).list.merge(Lista_wszystkich_pociagow.findNodeByName(T2).list);  //wykonuje operacje laczenia pociagow
                                Lista_wszystkich_pociagow.removeNodeByName(T2);  //usuwac drugi pociag z listy pociagow
                                       
                        }
                        
                        }
                        else  //Reverse
                        {
                            Lista_wszystkich_pociagow.findNodeByName(cleanedExpression).list.reverse();  //wykonuje operacje odwrocenia
                                     
                        }
                        
                        }
                        else  //InsertLast
                        {       
                                 String T = cleanedExpression.substring(0, cleanedExpression.indexOf(" "));  //scigam nazwe pociagu 
                                 String W = cleanedExpression.substring(cleanedExpression.indexOf(" ") + 1);  //sciagam nazwe wagonu
                            
                                Lista_wszystkich_pociagow.addNodeAtEnd3(T, W);  //wykonuje operacje dodania na koniec wagonu

                        }
                        
                        }
                        else  //InsertFirst
                        {
                            String T = cleanedExpression.substring(0, cleanedExpression.indexOf(" "));  //scigam nazwe pociagu 
                            String W = cleanedExpression.substring(cleanedExpression.indexOf(" ") + 1);  //sciagam nazwe wagonu
                            
                            Lista_wszystkich_pociagow.addNodeAtFront3(T,W);  //wykonuje operacje dodania na poczatek wagonu
                          
                        }
                    
                    
                    }
                    else  //New
                    {
                        
                    String T = cleanedExpression.substring(0, cleanedExpression.indexOf(" "));  //scigamy nazwe pociagu
                    String W = cleanedExpression.substring(cleanedExpression.indexOf(" ") + 1);  //sciagamy nazwe wagonu
                    
	            	Lista_wszystkich_pociagow.addNodeAtFront2(T,W);  //wykonujemy operacje dodania pociagu o danej nazwie z wagonem o danej nazwie

                    }

        }
	    
	}
    
}  

}

    class Node{  
    public String name;
    public Node prev;
    public Node next;
    
    public Node(String name){  //konstruktor wagonu
        this.name = name;
        this.prev = null;
        this.next = null;
    }
}


class DoublyLinkedList{  
    
    public Node first;
    public Node last;
    
    public DoublyLinkedList(){  //konstruktor listy wagonow
        this.first = null;
        this.last = null;
    }
    
     public boolean IsOne()  //czy pociag ma jeden wagon
     {
         if(first==last)
         {
             return true;
         }
         else
         {
             return false;
         }
     }
     
     
    public void addNodeAtFront(String name){  //dodanie wagonu na poczatek o danej nazwie
        Node newNode = new Node(name);
        if (first == null) {  //jezeli pierwszy jest nullem to wagon jest pusty
            first = newNode;
            last = newNode;
        } 
        
        else if(first.prev==null)  //nie obrocony pociag
        {
            newNode.next = first;  //ustawiamy dany node na pierwszy i odpowiednio go laczymy
            first.prev = newNode;
            first = newNode;
        }
        else  //obrocony pociag
        {
            newNode.prev = first;  //analogicznie ale dla obroconego pociagu
            first.next = newNode;
            first = newNode;
        }
    }
    
       public void merge(DoublyLinkedList list2){  //funkcja do laczenia 2 pociagow
           
    if(this.last.next==null && list2.first.prev==null)  //obydwa istotne wagonu pociagow nieobrocone
    {
        this.last.next=list2.first;
        list2.first.prev=this.last;
        this.last=list2.last;
    }
    else if(this.last.prev==null && list2.first.prev==null)  //istotny wagon pierwszego pociagu obrocony, drugiego pociagu nie
    {
        this.last.prev=list2.first;
        list2.first.prev=this.last;
        this.last=list2.last;
    }
    else if(this.last.next==null && list2.first.next==null)  //istotny wagon druguego pociagu obrocony, pierwszego nie
    {
        this.last.next=list2.first;
        list2.first.next=this.last;
        this.last=list2.last;
    }
    else if(this.last.prev==null && list2.first.next==null)  //obydwa istotne wagony pociagow obrocone
    {
        this.last.prev=list2.first;
        list2.first.next=this.last;
        this.last=list2.last;
    }
}
    
    public void addNodeAtEnd(String name){  //funcja analogiczna do addNodeAtFront
        Node newNode = new Node(name);
        if (last == null) {
            first = newNode;
            last = newNode;
        }
        else if(last.next==null)
        {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
        else 
        {
            newNode.next = last;
            last.prev = newNode;
            last = newNode;
        }
    }
    
    public void removeFirstNode(){  //funcja do usuwania pierwszego wagonu w pociagu
        
        if(first == last) {  //pociag ma 1 wagon
            first = null;
            last = null;
        } 
        else if(this.first.prev==null)  //pierwszy wagon w pociagu jest nieobrocony
        {
            Node lastbeen=first;  //pomocniczy node
            first = first.next;  //zmieniamy firsta na nastepny wagon
            if(first.next==lastbeen)  //tzn ze nowy first wagon jest obrocony poniewaz poruszajac sie nextem natrafiamy na starego firsta
            {
                first.next=null;  //wiec musimy ustawic first.next na null
            }
            else  //analogicznie ale nie jest obrocony
            {
            first.prev = null;
            }
        }
        else  //pierwszy wagon w pociagu jest obrocony
        {
            Node lastbeen=first;  //pomocniczy node
            first = first.prev;  //zmieniamy firsta na nastepny wagon (poruszajac sie prev)
            if(first.prev==lastbeen)  //tzn ze nowy first jest nieobrocony
            {
                first.prev=null;
            }
            else  //nowy first jest obrocony
            {
            first.next=null;
            }
        }
    }
    
     public void removeLastNode(){  //funkcja analogiczna do removeFirstNode
       if (first == last) {
            first = null;
            last = null;
        } 
        else if (this.last.next==null)
        {
            Node lastbeen=last;
            last = last.prev;
            if(last.prev==lastbeen)
            {
                last.prev=null;
            }
            else{
            last.next=null;
            }
        }
        else
        {
            Node lastbeen=last;
            last = last.next;
            if(last.next==lastbeen)
            {
                last.next=null;
            }
            else{
            last.prev=null;
            }
        }
    }
    
    public void reverse(){  //funkcja do obracania pociagu
        Node current=last;  //first przestawiamy na lasta a lasta na wczesniejszego firsta
        last=first;
        first=current;
    }

    public void displayList() {  //funkcja do wypisywania pociagu
        Node current = first;  //current node ustawiamy na firsta
        Node lastbeen=null;  //ostatnio odwiedzony ustawiamy na nulla
        
        while (current != null){  //dopoki nasz aktualny nie bedzie nullem
        
            if(current.next!=lastbeen)  //jezeli bedac na currencie i idac nextem nie trafimy na ostatnio odwiedzony node
            {                           //tzn ze nie jestesmy na obroconym miejscu
            System.out.print(current.name+" ");  
            lastbeen=current;  //lastbeen zamieniamy na current
            current = current.next;  //current zamieniamy na nastepny przez next
            }
            
            else  //w tym przypadku idac nextem zapetlilibysmy sie wiec jestesmy na odwroconym miejscu
            {
            System.out.print(current.name+" ");
            lastbeen=current;  //zamieniamy lastbeen na current
            current = current.prev;  //current zamieniamy na nastepny przez prev
            }
            
        }
        System.out.println();
    }
    
}

class Node2{  
    public String name;
    public Node2 next;
    public DoublyLinkedList list;
    
    public Node2(String name, DoublyLinkedList lista){  //konstruktor pociagu o danej nazwie z linked lista
        this.name = name;
        this.next = null;
        this.list = lista;
    }
}

class SingleLinkedList{

    public Node2 first;
    
    public SingleLinkedList(){  //konstruktor listy pociagow
        this.first = null;
    }
    
    public Node2 findNodeByName(String name){  //funkcja do znajdowania danego pociagu po nazwie
        Node2 current = first;  //current do przeszukiwania ustawiamy na pierwszy pociag
       
        while (current != null){  //dopoki current nie bedzie nullem
            if (current.name.equals(name))  //jezeli imie current pociagu jest rowne imieniu szukanego pociagu
            {                               //to zwracamy ten pociag
                return current;
            }
            else
            {
            current = current.next;  //w innym wypadku przechodzimy do nastepnego pociagu
            }
        }
        return null;
    }

    public void addNodeAtFront2(String name, String lista){  //funkcja do stworzenia pociagu o danej nazwie posiadajacego liste wagonow
        DoublyLinkedList ciag_wagonow = new DoublyLinkedList();  //tworzymy liste wagonow
        ciag_wagonow.addNodeAtFront(lista);  //dodajemy do tej listy wagon o danej nazwie
        Node2 newNode2 = new Node2(name, ciag_wagonow);  //tworzymy pociag o danym imieniu zawierajacy dana liste wagonow
        if (first == null){  //jezeli first jest nullem tzn ze lista wagonow jest pusta i ustawiamy nasz na pierwsze miejsce 
            first = newNode2;
        } 
        else  //tzn ze lista pociagow nie jest pusta i nasz ustawiamy na pierwsze miejsce
        {
            newNode2.next = first;
            first = newNode2;
        }
    }
    
    public void addNodeAtFront3(String name, String lista){  //funkcja dodajaca do pociagu o danym imieniu wagon  o danym imieniu na poczatek
        findNodeByName(name).list.addNodeAtFront(lista);
    }
    
    public void addNodeAtEnd3(String name, String lista){  //funkcja analogiczna do addNodeAtFront 3
        findNodeByName(name).list.addNodeAtEnd(lista);
    }
    
    public void displayList2(){  //funkcja do wypisania listy pociagow
        Node2 current = first;  //current ustawiamy na firsta
        while (current != null){  //dopoki current nie jest nullem
            System.out.print(current.name+" ");  //wypisujemy
            current = current.next;  //ustawiamy current na nexta
            }
            System.out.println();  //endl
        }
        
    public void removeNodeByName(String name){  //funkcja do usuniecia pociagu o danym imieniu z listy 
        if (first == null) {  //lista jest pusta
            return;
        }
        if(first.name.equals(name)){  //szukany pociag byl pierwszy
            first = first.next;
            return;
        }
        Node2 current = first.next;  //przepinamy na nastepny pociag
        Node2 previous = first;  //node o pamietania tego wczesniejszego
        while (current != null){  //dopoki current nie jest nullem
            if (current.name.equals(name))  //jezeli imie currenta jest rowne temu szukanemu 
            {
                previous.next = current.next;  //przepinamy odpowiednie polaczenia
                return;
            }
            previous = current;  //wczesniejszy rowna sie aktualnemu
            current = current.next;  //z aktualnym przechodzimy do nastepnego
        }
    }
}

/*
Przykladowe wejscie nr 1: 

2
25
New T1 W1
InsertFirst T1 W2
Display T1
InsertLast T1 W0
Display T1
DelFirst T1 T2
Display T1
Display T2
TrainsList
DelLast T1 T3
Display T1
Display T3
TrainsList
New T4 Z1
InsertFirst T4 Z2
Reverse T4
Display T4
Union T3 T4
Display T3
TrainsList
Union T3 T2
Display T3
Reverse T3
Display T3
TrainsList
24
New T1 W1
InsertFirst T1 W2
Display T1
InsertFirst T1 W0
Display T1
DelFirst T1 T2
Display T1
Display T2
DelLast T1 T3
Display T1
Display T3
TrainsList
New T4 Z1
InsertFirst T4 Z2
Reverse T4
Display T4
Union T3 T4
Display T3
Reverse T3
TrainsList
Union T3 T2
Display T3
Reverse T3
Display T3 

Wyjscie dla danych przykladowych nr 1:

T1: W2 W1 
T1: W2 W1 W0 
T1: W1 W0 
T2: W2 
Trains: T2 T1 
T1: W1 
T3: W0 
Trains: T3 T2 T1 
T4: Z1 Z2 
T3: W0 Z1 Z2 
Trains: T3 T2 T1 
T3: W0 Z1 Z2 W2 
T3: W2 Z2 Z1 W0 
Trains: T3 T1 
T1: W2 W1 
T1: W0 W2 W1 
T1: W2 W1 
T2: W0 
T1: W2 
T3: W1 
Trains: T3 T2 T1 
T4: Z1 Z2 
T3: W1 Z1 Z2 
Trains: T3 T2 T1 
T3: Z2 Z1 W1 W0 
T3: W0 W1 Z1 Z2

Przykladowe wejscie nr2:

1
33
New T1 W1
InsertFirst T1 W2
InsertLast T1 W3
InsertLast T1 W4
InsertLast T1 W5
InsertLast T1 W6
InsertLast T1 W7
Reverse T1
Reverse T1
Reverse T1
Reverse T1
Reverse T1
Display T1
New T2 Z1
InsertLast T2 Z2
InsertFirst T2 Z3
InsertFirst T2 Z4
InsertFirst T2 Z5
InsertFirst T2 Z6
InsertFirst T2 Z7
TrainsList
Union T1 T2
TrainsList
Display T1
Reverse T1
Reverse T1
Display T1
Reverse T1
Reverse T1
Display T1
Reverse T1
Display T1
TrainsList

Wyjscie dla danych przykladowych nr 2:

T1: W7 W6 W5 W4 W3 W1 W2 
Trains: T2 T1 
Trains: T1 
T1: W7 W6 W5 W4 W3 W1 W2 Z7 Z6 Z5 Z4 Z3 Z1 Z2 
T1: W7 W6 W5 W4 W3 W1 W2 Z7 Z6 Z5 Z4 Z3 Z1 Z2 
T1: W7 W6 W5 W4 W3 W1 W2 Z7 Z6 Z5 Z4 Z3 Z1 Z2 
T1: Z2 Z1 Z3 Z4 Z5 Z6 Z7 W2 W1 W3 W4 W5 W6 W7 
Trains: T1

*/
