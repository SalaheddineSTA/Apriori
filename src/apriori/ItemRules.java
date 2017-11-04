
package dm;

public class ItemRules {
   String nameA;
   String nameB;
   
    String confiance;
   
    public ItemRules(String nameA, String nameB, String confiance) {
        this.nameA = nameA;
        this.nameB = nameB;
        this.confiance = confiance;
    }   
    public String getName()
    {
        return nameA+" --> "+nameB;
    }
       public int getConf()
    {
        String ab[] = confiance.split("/");
        int x1 = Integer.parseInt(ab[0]);
        int x2 = Integer.parseInt(ab[1]);
        float a = ((float) x1/(float)x2)*100;
        return (int)(a);
    }
      public String toString()
    {
        return "Rule :"+nameA+" ----> "+nameB+" | Confiance : "+confiance+" .\n";
    }
}
