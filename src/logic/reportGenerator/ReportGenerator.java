package logic.reportGenerator;

import java.time.MonthDay;
import java.util.Scanner;

public class ReportGenerator
{
    private StringProcessor stringProcessor;
    private UserInfoAnalyzer userInfoAnalyzer;


    public ReportGenerator()
    {
        stringProcessor = new StringProcessor();
        userInfoAnalyzer = new UserInfoAnalyzer();
    }
    public void displayReport()
    {
        System.out.println("Displaying report");
    }
}
