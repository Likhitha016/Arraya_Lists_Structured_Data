package Week2.Using_And_Improving_GradLibs;
import java.util.*;
import edu.duke.*;
public class CodonCounts {
    private HashMap<String, Integer> codonsMap;

    public CodonCounts() {
        this.codonsMap = new HashMap<>();
    }

    public void buildCodonMap(int start, String dna) {
        codonsMap.clear();
        //stores all the codons  along with their counts
        for (int i = 0; i < dna.length() - 3; i = i + 3) {
            String codon = dna.substring(i, i + 3);
            if (!codonsMap.containsKey(codon))
                codonsMap.put(codon, 1);
            else
                codonsMap.put(codon, codonsMap.get(codon) + 1);
        }
    }
    //finds the most common codon i.e which occurs more number of times
    public String getMostCommonCodon() {
        int count = 0;
        String position = "";
        for (String s : codonsMap.keySet()) {
            int c = codonsMap.get(s);
            if (c > count) {
                count = c;
                position = s;
            }
        }
        return position;
    }
    //prints codons in the given range
    public void printCodonCounts(int start, int end) {
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
        for (String s : codonsMap.keySet()) {
            int count = codonsMap.get(s);
            if (count >= start && count <= end)
                System.out.println(s + " " + codonsMap.get(s));
        }
        System.out.println("\n\n\n");
    }
    //tester method to test all the methods
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        for (int i = 0; i < 3; i++) {
            System.out.println("Reading frame starting with " + i + " results in 2 unique codons");
            buildCodonMap(i, dna);
            String common = getMostCommonCodon();
            System.out.println("and most common codon is " + common + " with count " + codonsMap.get(common));
            printCodonCounts(1, 5);
        }
    }

    public static void main(String args[]) {
        CodonCounts cc = new CodonCounts();
        cc.tester();
    }
}
