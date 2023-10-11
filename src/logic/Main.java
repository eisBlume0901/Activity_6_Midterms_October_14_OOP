package logic;
import logic.reportGenerator.ReportGenerator;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("\033[3mText goes here\033[0m");
        EntryValidator uic = new EntryValidator();
        uic.validateEntries();

        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.displayReport(uic.saveDetailsToPersonObject());
    }
}
