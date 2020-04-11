package Week1.Object_Oriented_Caesar_Cipher;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private  int mainKey1;
    private int mainKey2;
    public CaesarCipherTwo(int key1,int key2){
        this.alphabet="abcdefghijklmnopqrstuvwxyz";
        this.shiftedAlphabet1=alphabet.substring(key1)+alphabet.substring(0,key1);
        this.shiftedAlphabet2=alphabet.substring(key2)+alphabet.substring(0,key2);
        this.mainKey1=key1;
        this.mainKey2=key2;
    }
    //encrypts the string with two keys
    public String encrypt(String input){
        StringBuilder encrypted=new StringBuilder(input);
        int i;
        for(i=0;i<encrypted.length();i++){
            char current_char=encrypted.charAt(i);
            //character at even positions use shifted alphabet1 with key1 to encrypt
            if(i%2==0) {
                int index = alphabet.indexOf(current_char);
                if (index != -1) {
                    char new_char = shiftedAlphabet1.charAt(index);
                    encrypted.setCharAt(i, new_char);
                }
            }
            //character at odd positions use shifted alphabet2 with key2 to encrypt
            else{
                int index = alphabet.indexOf(current_char);
                if (index != -1) {
                    char new_char = shiftedAlphabet1.charAt(index);
                    encrypted.setCharAt(i, new_char);
                }
            }
        }
        return encrypted.toString();
    }
    public String decrypt(String input){
        CaesarCipherTwo ct=new CaesarCipherTwo(26-mainKey1,26-mainKey2);
        return ct.encrypt(input);
    }
}
