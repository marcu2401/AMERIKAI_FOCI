package amerikai_foci;


import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AMERIKAI_FOCI {

    public static ArrayList<Jatekos> Jatekosok= new ArrayList<>();
    public static void beolvasas(){
        try{
            RandomAccessFile file = new RandomAccessFile("C:/Adatok/txt_filok/NFL_iranyitok.txt","r");
            
            while(file.getFilePointer()<file.length()){
            Jatekosok.add(new Jatekos(file.readLine())); 
        }
            file.close();
            System.out.println("\nA statisztikában "+Jatekosok.size()+" irányító szerepel!");
            
           
        }
        catch(IOException e){
            System.out.println("e");
        }
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        
        beolvasas();
        System.out.println("A lejobb irányítók:");
        for(Jatekos i : Jatekosok){
              if (i.iranyitomutato>=100 && i.yardMeterben(i.yardok)>=4000){
                    System.out.println("\t"+i.FormazottNev(i.nev));
                }
            }
        System.out.println("Eladott labdák száma: ");
        String eladott=sc.nextLine();

        Integer.parseInt(eladott);
        try {
            FileWriter myWriter = new FileWriter("C:/Adatok/txt_filok/legtobbeladott.txt");
            for(Jatekos i : Jatekosok){
                if(i.eladott>10){
                ArrayList<String> jatekosnev= new ArrayList<>();
                jatekosnev.add(i.nev);
                Collections.sort(jatekosnev);
                
                for(String j : jatekosnev){
                    myWriter.write(j+"\n");
                }
               
                }
            }
            myWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(AMERIKAI_FOCI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
class Jatekos{
    public String nev;
    public int yardok;
    public int kiserletek;
    public int sikeres;
    public int tdPasszok;
    public int eladott;
    public double iranyitomutato;
    public String egyetem;
    
    public Jatekos(String sor){
        String [] adatok=sor.split(";");
        System.out.println(sor);
        nev=adatok[0];
        yardok=Integer.parseInt(adatok[1]);
        kiserletek=Integer.parseInt(adatok[2]);
        sikeres=Integer.parseInt(adatok[3]);
        tdPasszok=Integer.parseInt(adatok[4]);
        eladott=Integer.parseInt(adatok[5]);
        iranyitomutato= Konvertal(adatok[6]);
        egyetem=adatok[7];
    }
    
    
    
    private  double Konvertal(String iranyitoMutato){
        return  Double.parseDouble(iranyitoMutato.replace(",", "."));
    }
	
    public int yardMeterben(int yard){
        double yardmeterben=yard*0.9144;
        String asd=String.valueOf(Math.round(yardmeterben));
        return Integer.parseInt(asd);
    }
    
    
	public String FormazottNev(String nev)
	{
		String [] n = nev.split(" ");
		n[n.length - 1] = n[n.length - 1].toUpperCase();
		return n[0]+" "+n[n.length - 1];
	}

}