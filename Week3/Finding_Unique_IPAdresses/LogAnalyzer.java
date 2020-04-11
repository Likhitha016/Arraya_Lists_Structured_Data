package Week3.Finding_Unique_IPAdresses;
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import Week3.Reading_Log_Files.LogEntry;
import Week3.Reading_Log_Files.WebLogParser;
import edu.duke.FileResource;

import java.util.ArrayList;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {

         this.records=new ArrayList<>();
     }
     public int countUniqueIps(){
         ArrayList<String > uniqueIPs=new ArrayList<>();
         for(LogEntry le:records){
             String ip=le.getIpAddress();
             if(!uniqueIPs.contains(ip))
                 uniqueIPs.add(ip);
         }
         return uniqueIPs.size();
     }
     public void printAllHigherThanNum(int num){
         for(LogEntry le: records){
             int statusCode=le.getStatusCode();
             if(statusCode>num)
                 System.out.println(le);
         }
     }
     public ArrayList uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIPs = new ArrayList<>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             String date = le.getAccessTime().toString().substring(4, 10);
             if (!uniqueIPs.contains(ip) && date.equals(someday)) {
                 uniqueIPs.add(ip);
             }
         }
         return uniqueIPs;
     }
    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<>();
        int count = 0;
        for (LogEntry le : records) {
            String ip= le.getIpAddress();
            int status = le.getStatusCode();
            if (!uniqueIPs.contains(ip) && (status >=low && status <= high)) {
                count++;
                uniqueIPs.add(ip);
            }
        }
        return count;
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
