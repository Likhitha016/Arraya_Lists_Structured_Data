package Week1;
import java.io.*;
import edu.duke.*;

public class CaesarCipher {
    //encrypts the string with givenn key
    public String encrypt(String input, int key){
        StringBuilder encrypted=new StringBuilder(input);
        String upperalphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String loweralphabet="abcdefghijklmnopqrstuvwxyz";
        String alphabet;
        int i;
        for(i=0;i<encrypted.length();i++){
            char current_char=encrypted.charAt(i);
            //if current character is lowercase take the lowercase alphabet
            if(Character.isLowerCase(current_char)){
                alphabet=loweralphabet;
            }
            else{
                alphabet=upperalphabet;
            }
            //shift the characters of the alphabet for key number of positions
            String shifted_alph=alphabet.substring(key)+alphabet.substring(0,key);
            //find the index of character in the shifted alphabet and set character in encrypted string
            int index=alphabet.indexOf(current_char);
            if(index!=-1){
                char new_char=shifted_alph.charAt(index);
                encrypted.setCharAt(i,new_char);
            }
        }
        return encrypted.toString();
    }


    void testCaesar(){
        FileResource fr=new FileResource();
        String message = fr.asString();
        int key=23;
        //test the encrypt method with the given key
        String encrypted = encrypt(message,key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    //encrypts the string..even characters with key1 and odd characters with key2
    String encryptTwoKeys(String input,int key1,int key2){
        int i;
        int evenIndex=0,oddIndex=0;
        String evenString="";
        String oddString="";
        String output="";
        //find the index of space in the sentence
        int space_Index=input.indexOf(' ');
        //store the characters at even position in one string and characters at odd position in one string
        for(i=0;i<input.length();i=i+2){
            if(input.charAt(i)==' '){
                continue;
            }
            evenString= evenString+input.charAt(i);
        }
        for(i=1;i<input.length();i=i+2){
            if(input.charAt(i)==' '){
                continue;
            }
            oddString=oddString+input.charAt(i);
        }
        //encrypt evenstring with key1 and oddstring with key2
        evenString=encrypt(evenString,key1);
        oddString=encrypt(oddString,key2);
        //append the encrypted strings to the output
        for(i=0;i<input.length();i++){
            if(i==space_Index){
                output=output+' ';
                i++;
            }
            if(i%2==0){
                output=output + evenString.charAt(evenIndex);
                evenIndex++;
            }
            else {
                output = output + oddString.charAt(oddIndex);
                oddIndex++;
            }
        }
        return output;
    }
    public static void main(String args[]){
        CaesarCipher c=new CaesarCipher();
        System.out.println(c.encrypt("FIRST LEGION ATTACK EAST FLANK!",23));
        System.out.println(c.encrypt("First Legion",23));
        System.out.println(c.encryptTwoKeys("First Legion",23,17));
        c.testCaesar();
    }
}
