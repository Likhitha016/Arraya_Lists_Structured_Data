package Week3.Counting_Website_Visits;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import Week3.Reading_Log_Files.LogEntry;
import Week3.Finding_Unique_IPAdresses.LogAnalyzer;

import java.util.Date;

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
        System.out.println(analyzer.uniqueIPVisitsOnDay("Apr 24").size());
        System.out.println(analyzer.countUniqueIPsInRange(300,399));

    }
    public static void main(String args[]){
        Tester t=new Tester();
        t.testLogAnalyzer();
        t.testLogEntry();
    }
}
