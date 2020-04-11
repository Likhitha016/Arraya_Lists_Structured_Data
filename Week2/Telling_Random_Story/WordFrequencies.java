package Week2.Telling_Random_Story;
import edu.duke.FileResource;
import java.util.ArrayList;
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(){
        this.myWords=new ArrayList<String>();
        this.myFreqs=new ArrayList<Integer>();
    }
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr=new FileResource();
        for(String s:fr.words()){
            int index=myWords.indexOf(s);
            //if word is not present in myWords list adds it to list
            if(index==-1){
                myWords.add(s);
                myFreqs.add(1);
            }
            //if word is already present increments it's frequency
            else{
                int freq=myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
        }
    }
    //finds the word which is occurring more number of times
    public int indexOfMax(){
        int max=myFreqs.get(0);
        int maxIndex=0;
        for(int i=0;i<myFreqs.size();i++){
            if (myFreqs.get(i) > max) {
                max=myFreqs.get(i);
                maxIndex=i;
            }
        }
        return maxIndex;
    }
    public void tester(){
        findUnique();
        System.out.println("Number of unique words: "+myWords.size());
        int index=indexOfMax();
        for(int i=0;i<myFreqs.size();i++){
            System.out.println(myFreqs.get(i)+" "+myWords.get(i));
        }
        System.out.println("The word that occurs most often and its count are: "+myWords.get(index)+" "+myFreqs.get(index));
    }
    public static void main(String args[]){
        WordFrequencies wf=new WordFrequencies();
        wf.tester();
    }
}
