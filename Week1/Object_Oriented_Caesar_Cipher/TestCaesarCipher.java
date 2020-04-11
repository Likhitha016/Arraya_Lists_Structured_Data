package Week1.Object_Oriented_Caesar_Cipher;

import edu.duke.FileResource;

public class TestCaesarCipher {
    //counts letters in the string
    public int[] countletters(String encrypted) {
        String alphabet="abcdefghijklmnopqrstuvwxyz";
        int count[]=new int[26];
        for(int i=0;i<encrypted.length();i++){
            char ch=Character.toLowerCase(encrypted.charAt(i));
            int index=alphabet.indexOf(ch);
            if(index!=-1) count[index]++;
        }
        return count;
    }
    //find the index at maximum count present
    public int IndexOfMax(int freq[]){
        int max=0;
        for(int i=0;i<freq.length;i++){
            if(freq[i]>freq[max])
                max=i;
        }
        return max;
    }
    public String breakCeasarCipher(String input){
        int count[]=countletters(input);
        int maxIndex=IndexOfMax(count);
        //finds the key used to decrypt
        int dkey=maxIndex-4;
        if(maxIndex<4)
            dkey=26-(4-maxIndex);
        CaesarCipher c=new CaesarCipher(dkey);
        return c.decrypt(input);
    }
    //testing method to test encryption and decryption
    public void simpleTests(){
        FileResource fr=new FileResource();
        CaesarCipher c=new CaesarCipher(18);
        String msg=fr.asString();
        String encrypted=c.encrypt(msg);
        System.out.println(encrypted+"\n"+c.decrypt(encrypted));
        String decrypted=breakCeasarCipher(encrypted);
        System.out.println(decrypted);
    }
    public static void main(String args[]){
        TestCaesarCipher tc=new TestCaesarCipher();
        tc.simpleTests();
    }
}
