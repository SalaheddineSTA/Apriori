/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import dm.Dm;

/**
 *
 * @author saleh
 */



public class Apriori {
    public ArrayList<ItemSet> itemsets = new ArrayList<ItemSet>();
    private ArrayList<ItemSet> itemsets2 = new ArrayList<ItemSet>();
    
    public static ArrayList<ItemRules> itemrules = new ArrayList<>();
   
    private int MinSup;
    private int MinConf;
    
   
    
    public int itemSup(ArrayList<Integer> list)
    {
        int val =0;
           for(int i=0;i<Dm.dat.get(list.get(0)).size();i++)
           {  
              int j;
              boolean v = true;
              for(j=1;j<list.size() && v;j++) if(!Dm.dat.get(list.get(j)).contains( Dm.dat.get(list.get(0)).get(i) ) ) v=false;
              if(v) val++;
           }
        return val;
    }
    
    public void Apriori (){

    }

    public int getMinSup() {
        return MinSup;
    }

    public void setMinSup(int MinSup) {
        this.MinSup = MinSup;
    }

    public int getMinConf() {
        return MinConf;
    }

    public void setMinConf(int MinConf) {
        this.MinConf = MinConf;
    }
    
   public void createOneItemsets()
    {
        for(int j=0;j<Dm.columns;j++)
        {             
           int val = Dm.dat.get(j).size();
            if(val>=this.MinSup)
            {
                ItemSet its;
                its = new ItemSet(val,"A"+j);
                its.taille=1;
                itemsets.add(its);
                
            }
        }
    }
    
    public void displayitemsets(){
        System.out.println(this.itemsets.toString());
    }
    public void createOtherItemsets()
      {
          
          int bornf=itemsets.size();
          //parcours de l'arraylist itemsets
           for(int i=0;i<bornf-1;i++)
              {
                  // salah fritha hna
                  for(int j=i+1;j<bornf;j++)
                  {  
                    ArrayList<Integer> array1 = convert(i);
                    ArrayList<Integer> array2 = convert(j);  
                      

                    int valeur=0;
                    int column = array1.get(0);
                    int column1= array2.get(0);
                    //calculer le support de chaque nouveau 2-itemsets
                    ArrayList<Integer> a = new ArrayList<Integer>();
                    a.add(column);
                    a.add(column1);
                    valeur=itemSup(a);
                    
                        if(valeur>=this.MinSup)
                        {   //ajouter le nouveau itemset a la list
                            //mchat :)
                            ItemSet its;
                            its = new ItemSet(valeur,"A"+column+"A"+column1);
                            its.taille=2;
                            itemsets.add(its);
                        }
                  }
               }
          int index = bornf;
          bornf=itemsets.size();
          boolean exist = true ;
          int added =0;
          
          while(exist)
          {
              int valeur=0;
              for(int i=index;i<bornf-1;i++)
              {
                  for(int j=i+1;j<bornf;j++)
                  {
                      valeur=0;
                      ArrayList<Integer> array1 = convert(i);
                      ArrayList<Integer> array2 = convert(j);
                      boolean can = true ;
                      
                      for (int k=0;k<array1.size()-1 && can ; k++)
                      {
                          //hqdi chofha omba3da
                        int column = array1.get(k);
                        int column1= array2.get(k);
                        if(column!=column1) can=false;
                      }
                      if(!can) 
                      {
                          continue;
                      }
                      ArrayList<Integer> array = new ArrayList<Integer>();
                      array.addAll(array1);
                      array.add(array2.get(array2.size()-1));
                         valeur=itemSup(array);  
                          if(valeur>=this.MinSup)
                            {   //ajouter le nouveau itemset a la liste
                                ItemSet its;
                                its = new ItemSet(valeur,itemsets.get(i).name+"A"+array2.get(array2.size()-1));
                                its.taille=array.size();
                                itemsets.add(its);
                                added++;
                            }
                  }
              }
               index = bornf;
               bornf=itemsets.size();
               if(added<=1) exist = false ;
               added =0;
          }
          
      }
      
         public ArrayList<Integer> convert(int i)
    {
        ArrayList<Integer> array = new ArrayList<Integer>();
        String item1[] = itemsets.get(i).name.split("A");
        for(int ll =1;ll<item1.length;ll++) array.add(Integer.parseInt(item1[ll]));
        return array;
    }
      
     public String ConvertToString(ArrayList<Integer> List)
      {
          String Chain="";
          for(int i=0;i<List.size();i++) Chain+="A"+List.get(i).toString();
          return Chain;
      }
      
        
    public void CreateItemR(ArrayList<Integer> RuleA,ArrayList<Integer> RuleB)
    {

        int val1=0;
        int val2=0;
        ArrayList<Integer> RuleAB = new ArrayList<>();
        RuleAB.addAll(RuleA);
        RuleAB.addAll(RuleB);

                              val1=itemSup(RuleAB);
                              val2=itemSup(RuleA);
                          float con=0 ;
                          if(val2!=0)        con = val1/val2;
                          
       // if(con>=MinConf/100) 
      //  {
            String conf = val1 + "/" + val2;
            ItemRules A = new ItemRules(ConvertToString(RuleA),ConvertToString(RuleB),conf);
            itemrules.add(A);
      //  }
    }
      
      
      public void generateRules()
      {
          int m=0;
          //sauter les ite,sets de longeur 1
          while (itemsets.get(m).taille<2) m++;
          //pqrcourir tous les itemsets
          for(int index=m;index<itemsets.size();index++)
          {
              ArrayList<Integer> array = convert(index);
              //parcourir la longeur de chaque itemsets
           for(int i = 1;i<array.size();i++)
         // int i =array.size()-1;  
          {
                 int temp[] = new int[i];
                 for(int k=0;(temp[k]=k)<i-1;k++);
                 ArrayList<Integer> ruleA = new ArrayList<>();
                 for(int l=0;l<temp.length;l++) {ruleA.add(array.get(temp[l])); }//System.out.print(temp[l]+ "  "); }  // convert
                //CreateItemR(temp,array,data);
                 ArrayList<Integer> ruleB = new ArrayList<>();
                 ruleB.addAll(array);
                 ruleB.removeAll(ruleA);
               CreateItemR(ruleA,ruleB);
                 int k;
                 
                 while(true)
                 {
                    for (k = i - 1; k >= 0 && temp[k] == array.size() - i + k; k--);
                        if (k < 0) break;
                        else 
                       {
                           temp[k]++;                  
                           for (++k; k < i; k++) temp[k] = temp[k - 1] + 1; 
                          ruleA.clear();
                          ruleB.clear();
                          for(int l=0;l<temp.length;l++) {ruleA.add(array.get(temp[l])); }//System.out.print(temp[l]+ "  "); }  // convert
                //CreateItemR(temp,array,data);
                
                          ruleB.addAll(array);
                          ruleB.removeAll(ruleA);
                      CreateItemR(ruleA,ruleB);
                       }  
                    }
            }
          }
      }
}
