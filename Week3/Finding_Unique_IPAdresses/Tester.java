package Week3.Finding_Unique_IPAdresses;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import Week3.Reading_Log_Files.LogEntry;
import Week3.Counting_Website_Visits.LogAnalyzer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer analyzer=new LogAnalyzer();
        analyzer.readFile("short-test_log");
        analyzer.printAll();
        System.out.println(analyzer.countUniqueIps());
        analyzer.printAllHigherThanNum(500);
        System.out.println(analyzer.uniqueIPVisitsOnDay("Apr 22").size());
        System.out.println(analyzer.countUniqueIPsInRange(300,399));
        HashMap<String,Integer> res=new HashMap<>();
        res=analyzer.countVisitsPerIP();
        for(String s:res.keySet()){
            System.out.println(s+"  "+res.get(s));
        }
        HashMap<String,ArrayList<String>> res2=new HashMap<>();
        res2=analyzer.iPsForDays();
        System.out.println(analyzer.dayWithMostIPVisits(res2));
        ArrayList<String> result=new ArrayList<>();
        result=analyzer.iPsWithMostVisitsOnDay(res2,"Sep 30");
        for(String s:result){
            System.out.println(s);
        }
    }
    public static void main(String args[]){
        Tester t=new Tester();
        t.testLogAnalyzer();
        t.testLogEntry();
    }
}
