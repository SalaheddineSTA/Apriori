/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm;

import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;







/**
 *
 * @author kayisse
 */
public class Dm {
     
    private static final String FILENAME = "C:\\Users\\saleh\\Desktop\\dm\\lotfi.txt";
   public static int  rows = 10;
   public static int  columns = 5; 
   public static ArrayList<ArrayList<Integer>> dat = new ArrayList<ArrayList<Integer>>();
    
     public static void reader()
    {
        BufferedReader br = null;
		FileReader fr = null;
		try
                {
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			String sCurrentLine;
			br = new BufferedReader(new FileReader(FILENAME));
			while ((sCurrentLine = br.readLine()) != null) 
                        {
                            String[] cc= sCurrentLine.split("\t");
                            ArrayList<Integer> a = new ArrayList<Integer>();
                            
                            for(int i=0;i<cc.length;i++)
                            {
                                if(Integer.parseInt(cc[i])==1)
                                a.add(i);
                            }
                            dat.add(new ArrayList<Integer>(a));
                            System.out.println(new ArrayList<Integer>(a));
                            
                        }

		} catch (IOException e) { e.printStackTrace();} finally
                {
			try {
				if (br != null) br.close();
				if (fr != null) fr.close();
                            } 
                        catch (IOException ex) {ex.printStackTrace();}
		}
    }
    
     public static void writer()
     {
         donee = new Object[rows][columns];
                BufferedWriter bw = null;
		FileWriter fw = null;
		try 
                {

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
                        for(int i=0;i<columns;i++)
                        {
                            String line="";
                            Random ran= new Random();
                            int a= ran.nextInt(2);
                            line += a+"";
                            donee[0][i]=a;
                            for(int j=1;j<rows;j++)
                            {
                                 a= ran.nextInt(2);
                                 line += "\t"+a; 
                                 donee[j][i]=a;
                            }
                            bw.write(line);
                            bw.newLine();
                            
                        }
                        for(int i=0;i<Dm.rows;i++)
                        {
                            
                                for(int j=0;j<Dm.columns;j++)
                                {
                                    System.out.print((int)donee[i][j]+"  ");
                                }
                                System.out.println("");
                        }
                                    
		} catch (IOException e) {e.printStackTrace();} 
                finally 
                {
			try 
                        {
				if (bw != null)	bw.close();
				if (fw != null)	fw.close();
			} catch (IOException ex) {ex.printStackTrace();}
		}
     }
    
       public int minsup;
       public int minConf=3;
       public static Object[][] donee;
   
       public String entetes ="" ;
       boolean yes =false;
       public Object[][] fre;
      

    
       
    public Object[][] getFre() {
        return fre;
    }

    public void setFre(Object[][] fre) {
        this.fre = fre;
    }
       

    public String getEntetes() {
        for(int i=0;i<columns;i++)
            entetes+="A"+i+",";
        return entetes;
    }

    public void setDonee(Object[][] donee) {
        this.donee = donee;
    }


    public Object[][] getDonee() {
        return donee;
    }
       

    public void setMinConf(int minConf) {
        this.minConf = minConf;
    }

    public int getMinConf() {
        return minConf;
    }
      
    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setMinsup(int minsup) {
        this.minsup = minsup;
    }

    public void setR(int R) {
        this.rows = R;
    }

    public void setC(int C) {
        this.columns = C;
    }

    public void setYes(boolean yes) {
        this.yes = yes;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getMinsup() {
        return minsup;
    }



    public int getR() {
        return rows;
    }

    public int getC() {
        return columns;
    }

    public boolean isYes() {
        return yes;
    }
    public  void main() {
           
    }
    
    
    public void Execute(){
        
        Apriori ap= new Apriori();
        ap.setMinSup(minsup);
        ap.setMinConf(minConf);
        writer();
        reader();
        ap.createOneItemsets();
        //ap.displayitemsets();
        ap.createOtherItemsets();
        //ap.displayitemsets();
        fre= new Object[ap.itemsets.size()][2];
        for(int i =0;i<ap.itemsets.size();i++){
            fre[i][0]=ap.itemsets.get(i).name;
            fre[i][1]=ap.itemsets.get(i).support+"/"+rows;
        }
        
        ap.generateRules();
        
        
        }
        
    }
    

