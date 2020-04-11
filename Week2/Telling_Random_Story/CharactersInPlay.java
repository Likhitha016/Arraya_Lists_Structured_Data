package Week2.Telling_Random_Story;
import edu.duke.*;
import java.util.ArrayList;
public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> count;

    public CharactersInPlay() {
        this.characters = new ArrayList<String>();
        this.count = new ArrayList<Integer>();
    }
    //if the string is person adds to characters array list and add it's count
    public void update(String person){
        if(!characters.contains(person)){
            characters.add(person);
            count.add(1);
        }
    }
    public void findAllCharacters(){
        characters.clear();
        count.clear();
        FileResource fr=new FileResource();
        for(String s:fr.lines()){
            int indexofperiod=s.indexOf(".");
            String speakingperson="";
            if(indexofperiod!=-1){
                //gets the speaking person string
                speakingperson=s.substring(0,indexofperiod);
                int indexOfPerson=characters.indexOf(speakingperson);
                //if person is not added in the characters list add by calling update function
                if(indexOfPerson==-1)
                    update(speakingperson);
                //if person is already present in list increment the count of person
                else{
                    int freq=count.get(indexOfPerson);
                    count.set(indexOfPerson,freq+1);
                }
            }
        }

    }
    public void tester(){
        findAllCharacters();
        int indexOfPerson=0;
        for(int i=0;i<characters.size();i++){
            //gets the index of person speaking more times
            if(count.get(i)>count.get(indexOfPerson))
                indexOfPerson=i;
        }
        System.out.println("Speaking parts of main Character is: "+characters.get(indexOfPerson)+
                " "+count.get(indexOfPerson));
        charactersWithNumParts(10,15);
    }
    //prints the persons having count greater than or equals to num1 and less than or equal to num2
    void charactersWithNumParts(int num1,int num2){
        for(int i=0;i<count.size();i++){
            if(count.get(i)>=num1 && count.get(i)<=num2)
                System.out.println(count.get(i)+" "+characters.get(i));
        }
    }
    public static void main(String args[]){
        CharactersInPlay cp=new CharactersInPlay();
        cp.tester();

    }
}