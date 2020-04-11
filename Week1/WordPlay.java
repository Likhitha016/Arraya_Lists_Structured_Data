package Week1;
public class WordPlay {
    //checks whether the entered character is vowel
    boolean isVowel(char ch){
        if(ch=='a' ||ch=='e'||ch=='i'||ch=='o'||ch=='u'||ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U')
            return true;
        else
            return false;
    }
    //tester method for checking a character is vowel or not
    void testVowels(){
        System.out.println(isVowel('F'));
        System.out.println(isVowel('a'));
    }
    //replaces the vowels in the string with given character
    String replaceVowels(String phrase , char ch){
        int i;
        StringBuilder temp_phrase=new StringBuilder(phrase);
        for(i=0;i<phrase.length();i++){
            char temp=phrase.charAt(i);
            //checks if the character is vowel or not
            if(isVowel(temp)){
                temp_phrase.setCharAt(i,ch);
            }
        }
        return temp_phrase.toString();
    }
    String emphasize(String phrase, char ch){
        StringBuilder temp_phrase=new StringBuilder(phrase);
        int i,temp=0;
        for(i=0;i<phrase.length();i++) {
            //finds index of character
            int index = phrase.indexOf(ch, temp);
            //if()
            if (index != -1) {
                if (index % 2 == 0)
                    temp_phrase.setCharAt(index, '*');
                else
                    temp_phrase.setCharAt(index, '+');
                //make temp to index next position to check for the character in remaining string
                temp = index + 1;
            }
            else break;
        }

        return temp_phrase.toString();
    }

    public static void main(String args[]){
        WordPlay w=new WordPlay();
        System.out.println(w.replaceVowels("Hello India",'*'));
        System.out.println(w.emphasize("Mary Bella Abracadabra",'a'));
    }
}
