package Week1.Object_Oriented_Caesar_Cipher;

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key){
        this.alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.shiftedAlphabet=alphabet.substring(key)+alphabet.substring(0,key);
        this.mainKey=key;
    }
    //encrypts the given string with shifted alphabet using key given while initializing using constructor
    public String encrypt(String input){
        StringBuilder encrypted=new StringBuilder(input);
        int i;
        for(i=0;i<encrypted.length();i++){
            char current_char=encrypted.charAt(i);
            int index=alphabet.indexOf(current_char);
            if(index!=-1){
                char new_char=shiftedAlphabet.charAt(index);
                encrypted.setCharAt(i,new_char);
            }
        }
        return encrypted.toString();
    }
    //decrypts the string using key given while initializing
    public String decrypt(String input){
        CaesarCipher c=new CaesarCipher(26-mainKey);
        return c.encrypt(input);
    }
    public static void main(String args[]){
        CaesarCipher c=new CaesarCipher(23);
        System.out.println(c.encrypt("FIRST LEGION ATTACK EAST FLANK!"));
    }
}
