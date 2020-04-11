package Week2.Using_And_Improving_GradLibs;
import java.io.File;
import java.util.*;
import edu.duke.*;
public class WordsInFiles {
    private HashMap<String,ArrayList> fileMap;
    public WordsInFiles(){
        this.fileMap=new HashMap<>();
    }
    private void addWordsFromFile(File f){
        FileResource fr=new FileResource();
        for(String word:fr.words()){
            //for each word in the file add it to map along with the filename
            if(!fileMap.containsKey(word)){
                ArrayList<String> files=new ArrayList<String>();
                files.add(f.getName());
                fileMap.put(word,files);
            }
            else{
                ArrayList<String> files=fileMap.get(word);
                if(!files.contains(f.getName()))
                    files.add(f.getName());
            }
        }
    }
    //selects files using directory resource
    private void buildWordFileMap(){
        fileMap.clear();
        DirectoryResource dr=new DirectoryResource();
        for(File  f:dr.selectedFiles())
            addWordsFromFile(f);
    }
    public int maxNumber(){
        int max=0;
        for(String word:fileMap.keySet()){
            int size=fileMap.get(word).size();
            if(size>max)
                max=size;
        }
        return max;
    }
    // Returns an ArrayList of words that appear in exactly in given "num" files
    public ArrayList wordsInNumFiles(int num){
    ArrayList<String> words=new ArrayList<>();
    for(String word:fileMap.keySet()){
        int size=fileMap.get(word).size();
        if(size==num)
            words.add(word);
    }
    return words;
    }
    public void printFilesIn(String word){
        ArrayList<String> files=fileMap.get(word);
        for(String fname:files)
            System.out.println(fname);
    }
    //tester method to test all methods
    public void tester(){
        buildWordFileMap();
        int max=maxNumber();
        ArrayList<String> words=wordsInNumFiles(max);
        for(String word:words){
            System.out.println(word+" appears in files");
            printFilesIn(word);
            System.out.println();
        }
    }
    public static void main(String args[]){
        WordsInFiles wf=new WordsInFiles();
        wf.tester();
    }
}
