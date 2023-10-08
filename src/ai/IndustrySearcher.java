package ai;

import java.io.*;
import java.nio.file.*;
import java.util.*;
public class IndustrySearcher
{
    private File file;
    private Map courseIndustryMap;

    public IndustrySearcher()
    {
        file =  new File("GeneralCoursesAndItsRespectiveIndustry");
        courseIndustryMap = new HashMap();
    }

    public void storeCoursesIndustriesToMap()
    {
        try {
            Files.lines(Paths.get(file.getAbsolutePath()))
                    .map(parts -> parts.split(","))
                    .forEach(parts -> courseIndustryMap.put(String.valueOf(parts[0]), String.valueOf(parts[1])));

            System.out.println("Successfully added!");
            System.out.println(courseIndustryMap.size());
        }
       catch (IOException ioe)
       {
           System.err.println("Error in storing file to Map");
           ioe.printStackTrace();
       }
    }

    public String searchIndustry(String input)
    {
        return courseIndustryMap.get(input).toString();
    }

    public static void main(String[] args)
    {
        IndustrySearcher is = new IndustrySearcher();
        is.storeCoursesIndustriesToMap();
        String result = is.searchIndustry("Computer Science");
        System.out.println(result);
    }
}
