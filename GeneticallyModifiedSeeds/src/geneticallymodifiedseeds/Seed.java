/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticallymodifiedseeds;

/**
 *
 * @author robby
 */
public class Seed {
    private final String seed;
    private final String binary;
    private final long decimal;
    
    public Seed getWithSkulls(boolean[] skulls){
        String decSeed = Long.toString(decimal);
        if(decSeed.length() >= 10) decSeed = decSeed.substring(decSeed.length()-10, decSeed.length());
        else{
            while(decSeed.length() < 10){
                decSeed = "0" + decSeed;
            }
        }
        int skullData = 0;
        for(int i = 0; i < skulls.length; i++){
            if(skulls[i] == true) skullData += Math.pow(2,i);
        }
        decSeed = Integer.toString(skullData) + decSeed;
        long newVal = Long.parseLong(decSeed);
        String newSeed = "";
        while(newVal > 0){
            char next;
            next = (char) (newVal % 36);
            if(next < 10){
                next += '0';
            } else {
                next += 'a' - 10;
            }
            newSeed = next + newSeed;
            newVal /= 36;
        }
        return new Seed(newSeed);
    }
    
    public String getAsBinary(){
        return binary;
    }
    
    public String getSeed(){
        return seed;
    }
    
    public long getAsDecimal(){
        return decimal;
    }
    
    public void printSkulls(){
        boolean skullPrinted = false;
        long num = decimal / 100000;
        num /= 100000;
        System.out.print("Skulls: ");
        for(int i = 0; i < GeneticallyModifiedSeeds.SKULLS.length; i++){
            if(num % 2 == 1){
                if(skullPrinted) System.out.print(", ");
                System.out.print(GeneticallyModifiedSeeds.SKULLS[i]);
                skullPrinted = true;
            }
            num /= 2;
        }
        if(!skullPrinted) System.out.print("None!");
        System.out.println();
    }
    
    public boolean[] getSkulls(){
        boolean[] skulls = new boolean[17];
        long num = decimal / 100000;
        num /= 100000;
        for(int i = 0; i < GeneticallyModifiedSeeds.SKULLS.length; i++){
            if(num % 2 == 1) skulls[i] = true;
            num /= 2;
        }
        return skulls;
    }
    
    public Seed(String seed){
        this.seed = seed.toLowerCase();
        seed = seed.toLowerCase();
        long val = 0;
        for(int i = 0; i < seed.length(); i++){
            int current = seed.length() - 1 - i;
            if(seed.charAt(current) >= 48 && seed.charAt(current) <= 57){
                current = seed.charAt(current) - '0';
            } else {
                current = seed.charAt(current) - 'a' + 10;
            }
            val += current * Math.pow(36, i);
        }
        decimal = val;
        String str = "";
        while(val > 0){
            if(val % 2 == 1) str = "1" + str;
            else str = "0" + str;
            val /= 2;
        }
        binary = str;
    }
}
