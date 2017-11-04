
package dm;

public class ItemSet {
     int support;

     String name;
     int taille=0;
      
    public ItemSet(int s,String n){
        support=s;
        name=n;
    }
    
    public String toString()
    {
        return "Name :"+name+", Support :"+support+" .\n";
    }
}
