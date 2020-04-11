package Week4.KnownLanguage_And_KeyLength;

import Week4.CaesarCracker;
import Week4.VigenereCipher;
import edu.duke.*;

public class VigenereBreaker {
    //returns a String consisting of every totalSlices-th character from message,
    // starting at the whichSlice-th character
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String slicedstring="";
        for(int i=whichSlice;i<message.length();i+=totalSlices)
            slicedstring=slicedstring+message.charAt(i);
        return slicedstring;
    }
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cr=new CaesarCracker(mostCommon);
        for(int i=0;i<klength;i++){
            String s=sliceString(encrypted,i,klength);
            // finds the shift for each index in the key
            int k=cr.getKey(s);
            key[i]=k;
        }
        return key;
    }
    //
    public void breakVigenere () {
        FileResource fr=new FileResource();
        String msg=fr.asString();
        VigenereBreaker vb=new VigenereBreaker();
        int key[]=vb.tryKeyLength(msg,5,'e');
        //using vigenereCipher's decrypt method to decrypt the message
        VigenereCipher vc=new VigenereCipher(key);
        for(int i:key)
            System.out.println(i);
        System.out.println(vc.decrypt(msg));
    }
    public static void main(String args[]){
        VigenereBreaker vb=new VigenereBreaker();
        vb.breakVigenere();
    }

}
