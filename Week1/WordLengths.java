package Week1;

import edu.duke.FileResource;

public class WordLengths {
    void countWordLengths(FileResource resource,int counts[]){
        for (String word: resource.words()) {
            //for each word in file find length
            int len=word.length();
            int count=0;
            //count the characters if those are alphabets
            for (int i=0;i<len;i++) {
                if (Character.isLetter(word.charAt(i)))
                    count++;
                else{
                    //increment the count if first character or last character is not a special character
                    if((word.charAt(i)=='\'' || word.charAt(i)=='-') && (i!=0 || i!=len-1))
                        count++;

                }
            }
            //store the word counts in counts array
            if (count<=counts.length & count!= 0)
                counts[count-1]+=1;
            else
                System.out.println("Exceeds length of array");
        }
        int i;
        //print the count of words
        for(i=0;i<counts.length;i++){
            if(counts[i]==0)
                continue;
            System.out.println(counts[i] + " words of length "+(i+1));
        }
    }
    //finds the index with maximum length of word
    int IndexOfMax(int values[]){
        int max=0;
        for(int i=0;i<values.length;i++){
            if(values[i]>values[max])
                max=i;
        }
        return max;
    }

    void testCaesar(){
        FileResource fr=new FileResource();
        int [] counts = new int[31];
        //stores the count of words
        countWordLengths(fr,counts);
        //finds position of element with maximum count
        int maxPosition=IndexOfMax(counts);
        System.out.println(maxPosition);
    }
    public static void main(String args[]){
        WordLengths w=new WordLengths();
        w.testCaesar();
    }
}
