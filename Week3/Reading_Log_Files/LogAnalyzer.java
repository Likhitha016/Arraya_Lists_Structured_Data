package Week3.Reading_Log_Files;
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
        this.records=new ArrayList<>();
     }
        
     public void readFile(String filename) {
         FileResource fr=new FileResource();
         for(String line:fr.lines()){
             LogEntry entry= WebLogParser.parseEntry(line);
             records.add(entry);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
