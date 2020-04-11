package Week1;
import edu.duke.FileResource;

public class CaesarBreaker {
    public String decrypt(String encrypted){
       CaesarCipher c=new CaesarCipher();
       //find the count of each letter in the string
       int freq[]=countletters(encrypted);
       //find index of maximum occurring character
       int maxindex=IndexOfMax(freq);
       // here we are finding for 'e' character occurrences
        // find decryption key
       int dKey=maxindex-4;
       //if maximum index is less than 4 then decryption key is shifted
       if(maxindex<4)
           dKey=26-(4-maxindex);
       return c.encrypt(encrypted,26-dKey);
    }
    //finds count of letters in string
    public int[] countletters(String encrypted) {
        String alphabet="abcdefghijklmnopqrstuvwxyz";
        int count[]=new int[26];
        for(int i=0;i<encrypted.length();i++){
            char ch=Character.toLowerCase(encrypted.charAt(i));
            int index=alphabet.indexOf(ch);
            if(index!=-1)
                count[index]++;
        }
        return count;
    }

    public int IndexOfMax(int freq[]){
        int max=0;
        for(int i=0;i<freq.length;i++){
            if(freq[i]>freq[max])
                max=i;
        }
        return max;
    }
    //returns the string with characters at even position if start value is 0 or
    // string with characters at odd positions if start value is 1
    public String halfOfString(String message,int start){
        String evenpos="";
        String oddpos="";
        for(int i=0;i<message.length();i++){
            if(i%2==0)
                evenpos=evenpos+message.charAt(i);
            else
                oddpos=oddpos+message.charAt(i);
        }
        if(start==0)
            return evenpos;
        else
            return oddpos;
    }
    //returns the key used to decrypt the encrypted message
    public int getKey(String s){
        int count[]=new int[26];
        int key=0;
        count=countletters(s);
        int maxIndex=IndexOfMax(count);
        key=maxIndex-4;
        if(maxIndex<4)
            key=26-(4-maxIndex);
        return key;
    }
    //decrypts the string using two keys
    public String decryptTwoKeys(String encrypted){
        CaesarCipher c=new CaesarCipher();
        String evenstring=halfOfString(encrypted,0);
        String oddstring=halfOfString(encrypted,1);
        int evenIndex=0,oddIndex=0;
        //finds key for decrypting string formed from even positions
        int key1=getKey(evenstring);
        //finds key for decrypting string formed from odd positions
        int key2=getKey(oddstring);
        String encryptedeven=c.encrypt(evenstring,key1);
        String encryptedodd=c.encrypt(oddstring,key2);
        String encrypt="";
        //get the combined string to decrypt
        for(int i=0;i<evenstring.length()+oddstring.length()-1;i++){
            if(i%2==0){
                encrypt=encrypt + encryptedeven.charAt(evenIndex);
                evenIndex++;
            }
            else {
                encrypt = encrypt + encryptedodd.charAt(oddIndex);
                oddIndex++;
            }
        }
        return decrypt(encrypt);
    }
    public void testDecrypt(){
        String s="Yjhi p ithi higxcv lxiw adih du tttttttttttttttth";
        String d=decrypt(s);
        System.out.println(d);
    }
    public void testhalfOfStrings(){
        String s="Qbkm Zgis";
        String res=halfOfString(s,1);
        System.out.println(res);
    }
    public void testdecryptTwoKeys(){
        FileResource fr=new FileResource();
        String str=fr.asString();
        String res=decryptTwoKeys(str);
        System.out.println(res);
    }
    public static void main(String args[]){
        CaesarBreaker cb=new CaesarBreaker();
        cb.testDecrypt();
        cb.testhalfOfStrings();
        cb.testdecryptTwoKeys();
    }
}
