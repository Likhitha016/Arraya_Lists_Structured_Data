package Week4.UnknownLanguage_And_UnknownKeyLength;

import Week4.CaesarCracker;
import Week4.VigenereCipher;
import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String slicedstring="";
        for(int i=whichSlice;i<message.length();i+=totalSlices)
            slicedstring=slicedstring+message.charAt(i);
        return slicedstring;
    }
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> res=new HashSet<>();
        for(String s:fr.lines()){
            res.add(s.toLowerCase());
        }
        return res;
    }
    public int countWords(String message,HashSet<String> dictionary){
        String msgArray[]=message.split("\\W+");
        int count=0;
        for(String word:msgArray){
            if(dictionary.contains(word))
                count++;
        }
        return count;
    }
    public String breakForLanguage(String encrypted,HashSet<String> dictionary){
        int max=0;
        String decrypt="";
        int keylength=0;
        char mostCommon=mostCommonCharIn(dictionary);
        for(int i=1;i<100;i++){
            int key[]=tryKeyLength(encrypted,i,mostCommon);
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
            String s=sliceString(encrypted,i,klength);
            int k=cr.getKey(s);
            key[i]=k;
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr=new FileResource();
        String msg=fr.asString().toLowerCase();
        DirectoryResource dr=new DirectoryResource();
        HashMap<String,HashSet<String>> dictionary=new HashMap<>();
        for(File f:dr.selectedFiles()){
            FileResource resource=new FileResource();
            HashSet<String> dict=readDictionary(resource);
            dictionary.put(f.getName(),dict);
        }
        breakForAllLangs(msg,dictionary);

    }
    //finds the most often in the words in the language
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> hm=new HashMap<>();
        char commonchar='a';
        int max=0;
        //stores the character and its count of occurrences in the hashmap
        for(String s:dictionary){
            for(int i=0;i<s.length();i++){
                if(hm.containsKey(s.charAt(i)))
                    hm.put(s.charAt(i),hm.get(s.charAt(i)+1));
                else
                    hm.put(s.charAt(i),1);
            }
        }
        //iterate through the keyset of the hashmap and find character which occurs most often
        for(Character ch:hm.keySet()){
            if(hm.get(ch)>max){
                max=hm.get(ch);
                commonchar=ch;
            }
        }
        return commonchar;
    }

    public void breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages){
        //finds the language name and prints the decrypted message
        for(String languagename:languages.keySet()){
            System.out.println("Language is "+languagename);
            String decrypted=breakForLanguage(encrypted,languages.get(languagename));
            System.out.println(decrypted);
        }
    }
    public static void main(String args[]){
        VigenereBreaker vb=new VigenereBreaker();
        vb.breakVigenere();
    }
}
