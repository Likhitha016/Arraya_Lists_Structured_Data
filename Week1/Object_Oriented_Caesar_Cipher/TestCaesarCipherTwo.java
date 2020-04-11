package Week1.Object_Oriented_Caesar_Cipher;

import Week1.CaesarBreaker;
import Week1.CaesarCipher;
import edu.duke.FileResource;

public class TestCaesarCipherTwo {
    CaesarBreaker cb=new CaesarBreaker();
    void simpleTest(){
        FileResource fr=new FileResource();
        String str=fr.asString();
        //creates object of CaesarCipherTwo class and intialize with given keys
        CaesarCipherTwo caesarCipherTwo=new CaesarCipherTwo(17,3);
        String encrypted=caesarCipherTwo.encrypt(str);
        System.out.println("Encrypted String: "+encrypted);
        String decrypted=caesarCipherTwo.decrypt(encrypted);
        System.out.println("Decrypted String: "+decrypted);
        String decrypt=breakCaesarCipher(encrypted);
        System.out.println("Decrypted String with automatically finding keys: "+decrypt);
    }
    public String breakCaesarCipher(String input){
        Week1.CaesarCipher c=new CaesarCipher();
        String evenString=cb.halfOfString(input,0);
        String oddString=cb.halfOfString(input,1);
        int evenIndex=0,oddIndex=0;
        //finds keys used to encrypt
        int key1=cb.getKey(evenString);
        int key2=cb.getKey(oddString);
        String encryptedeven=c.encrypt(evenString,key1);
        String encryptedodd=c.encrypt(oddString,key2);
        String encrypt="";
        //get the combined string to decrypt
        for(int i=0;i<evenString.length()+oddString.length()-1;i++){
            if(i%2==0){
                encrypt=encrypt + encryptedeven.charAt(evenIndex);
                evenIndex++;
            }
            else {
                encrypt = encrypt + encryptedodd.charAt(oddIndex);
                oddIndex++;
            }
        }
        CaesarCipherTwo obj=new CaesarCipherTwo(key1,key2);
        return obj.decrypt(encrypt);
    }
    public static void main(String args[]){
        TestCaesarCipherTwo testCaesarCipherTwo=new TestCaesarCipherTwo();
        testCaesarCipherTwo.simpleTest();
    }
}
