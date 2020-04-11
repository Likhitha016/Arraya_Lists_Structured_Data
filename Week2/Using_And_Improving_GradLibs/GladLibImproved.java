package Week2.Using_And_Improving_GradLibs;


import edu.duke.*;
import java.util.*;

public class GladLibImproved {

    private HashMap<String,ArrayList> myMap;
    private ArrayList<String> Words;
    private ArrayList<String> Categories;
    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibImproved(){
        this.myMap=new HashMap<>();
        this.Categories=new ArrayList<>();
        this.Words=new ArrayList<>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLibImproved(String source){
        this.myMap=new HashMap<>();
        this.Categories=new ArrayList<>();
        this.Words=new ArrayList<>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    //adds verb and fruit text files map
    private void initializeFromSource(String source) {
        myMap.put("adjective",readIt(source+"/adjective.txt"));
        myMap.put("noun",readIt(source+"/noun.txt"));
        myMap.put("color",readIt(source+"/color.txt"));
        myMap.put("country", readIt(source+"/country.txt"));
        myMap.put("name",readIt(source+"/name.txt"));
        myMap.put("animal",readIt(source+"/animal.txt"));
        myMap.put("time",readIt(source+"/timeframe.txt"));
        myMap.put("fruit",readIt(source+"/fruit.txt"));
        myMap.put("verb",readIt(source+"/verb.txt"));
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        String res=randomFrom(myMap.get(label));
        return res;
    }
    private void addCategory(String label){
        if(Categories.indexOf(label)==-1)
            Categories.add(label);
    }
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    private void totalWordsInMap(){
        int res=0;
        for(String s:myMap.keySet() ){
            ArrayList<String> words = new ArrayList<>();
            words.add(s);
            System.out.println("Category: "+s+" Total words in this category : "+words.size());
            res+=words.size();
        }
        System.out.println("Total number of words in all Lists are : "+res);
    }
    private int totalWordsConsidered(){
        int total=0;
        ArrayList<String> categorywords=new ArrayList<>();
        for(int i=0;i<Categories.size();i++){
            String cat=Categories.get(i);
            categorywords=myMap.get(cat);
            System.out.println("Category: "+cat+"  Words in category"+categorywords.size());
            total+=categorywords.size();
        }
        return total;
    }
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("/home/likhita/Downloads/GladLib/data/madtemplate2.txt");
        printOut(story, 60);
        totalWordsInMap();
        totalWordsConsidered();
    }
    public static void main(String args[]){
    GladLibImproved g=new GladLibImproved();
    g.makeStory();
    }

}
