package Week3.Counting_Website_Visits;
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
import java.util.HashMap;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {

         this.records=new ArrayList<>();
     }
     //counts unique ip addresses
     public int countUniqueIps(){
         ArrayList<String > uniqueIPs=new ArrayList<>();
         for(LogEntry le:records){
             String ip=le.getIpAddress();
             if(!uniqueIPs.contains(ip))
                 uniqueIPs.add(ip);
         }
         return uniqueIPs.size();
     }
     //prints entry logs with status code greater than the given number
     public void printAllHigherThanNum(int num){
         for(LogEntry le: records){
             int statusCode=le.getStatusCode();
             if(statusCode>num)
                 System.out.println(le);
         }
     }
     //prints ip addresses visited on the given day
     public ArrayList uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIPs = new ArrayList<>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             //for every record get access time
             String date = le.getAccessTime().toString().substring(4, 10);
             if (!uniqueIPs.contains(ip) && date.equals(someday)) {
                 uniqueIPs.add(ip);
             }
         }
         return uniqueIPs;
     }
     //counts the ip address in given range
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
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> countVisits = new HashMap<>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!countVisits.containsKey(ip)) {
                countVisits.put(ip, 1);
            }
            else {
                countVisits.put(ip, countVisits.get(ip) + 1);
            }
        }
        return countVisits;
    }
    public int mostNumberVisitsByIP(HashMap<String,Integer> ipaddr) {
        int max = 0;
        for (Integer i : ipaddr.values()) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ipaddr) {
        ArrayList<String> uniqueIPsInRange = new ArrayList<>();
        for (String ip : ipaddr.keySet()) {
            if (ipaddr.get(ip) == mostNumberVisitsByIP(ipaddr)) {
                uniqueIPsInRange.add(ip);
            }
        }
        return uniqueIPsInRange;
    }
    public HashMap<String,ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> res = new HashMap<>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString().substring(4, 10);
            if (!res.containsKey(date)) {
                ArrayList<String> iPs = new ArrayList<>();
                iPs.add(le.getIpAddress());
                res.put(date, iPs);
            }
            else {
                res.get(date).add(le.getIpAddress());
            }
        }
        return res;
    }
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsForDays) {
        int max = 0;
        String dayWithMostIPVisits = "";
        for (String date : iPsForDays.keySet()) {
            if (iPsForDays.get(date).size() > max) {
                max = iPsForDays.get(date).size();
                dayWithMostIPVisits = date;
            }
        }
        return dayWithMostIPVisits;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsForDays, String date) {
        ArrayList<String> iPsForDay = iPsForDays.get(date);
        HashMap<String, Integer> counts = new HashMap<>();
        for (String iP : iPsForDay) {
            if (!counts.containsKey(iP)) {
                counts.put(iP, 1);
            } else {
                counts.put(iP, counts.get(iP) + 1);
            }
        }
        return iPsMostVisits(counts);
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
