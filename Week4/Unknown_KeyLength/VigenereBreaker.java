package Week4.Unknown_KeyLength;

import Week4.CaesarCracker;
import Week4.VigenereCipher;
import edu.duke.FileResource;

import java.util.HashSet;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String slicedstring="";
        for(int i=whichSlice;i<message.length();i+=totalSlices)
            slicedstring=slicedstring+message.charAt(i);
        return slicedstring;
    }
    //adds the words in the dictionary file to the hast set
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> res=new HashSet<>();
        for(String s:fr.lines()){
            res.add(s.toLowerCase());
        }
        return res;
    }
    //splits the message and check how many words are present in the dictionary
    public int countWords(String message,HashSet<String> dictionary){
        String msgArray[]=message.split("\\W+");
        int count=0;
        for(String word:msgArray){
            if(dictionary.contains(word))
                count++;
        }
        return count;
    }
    //decrypts the string with key best suits for decryption in the range of
    public String breakForLanguage(String encrypted,HashSet<String> dictionary){
        int max=0;
        String decrypt="";
        int keylength=0;
        for(int i=1;i<100;i++){
            int key[]=tryKeyLength(encrypted,i,'e');
            VigenereCipher vc=new VigenereCipher(key);
            String decrypted=vc.decrypt(encrypted);
            //finds which key gives maximum count of real words in dictionary which is used for decryption
            if(countWords(decrypted,dictionary)>max){
                max=countWords(decrypted,dictionary);
                decrypt=decrypted;
                keylength=i;
            }
        }
        System.out.println("KeyLength used to encrypt: "+keylength);
        return decrypt;
    }
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cr=new CaesarCracker(mostCommon);
        for(int i=0;i<klength;i++){
            // finds the shift for each index in the key
            String s=sliceString(encrypted,i,klength);
            int k=cr.getKey(s);
            key[i]=k;
        }
        return key;
    }
    public void breakVigenere () {
        FileResource fr=new FileResource();
        String msg=fr.asString().toLowerCase();
        FileResource fr2=new FileResource();
        HashSet<String> dictionary=readDictionary(fr2);
        //finds valid words in dictionary
        String decrypt=breakForLanguage(msg,dictionary);
        System.out.println("Valid words in decrypted string : "+countWords(decrypt,dictionary));
        System.out.println(decrypt);
        //find the key for the message you read in
        String foundkey=new VigenereCipher(tryKeyLength(msg,38,'e')).decrypt(msg);
        System.out.println("Valid words in decrypted string : "+countWords(foundkey,dictionary));
    }
    public static void main(String args[]){
        VigenereBreaker vb=new VigenereBreaker();
        vb.breakVigenere();
    }
}
