package ai;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class RegionCitySearcher
{
    private File file;
    private Map regionCityMap;

    public RegionCitySearcher()
    {
        file = new File("RegionAndCitiesInPhilippines");
        regionCityMap = new HashMap();
    }

    public void storeRegionAndCitiesToMap()
    {
        try
        {
            Files.lines(Paths.get(file.getAbsolutePath()))
                    .map(parts -> parts.split(","))
                    .forEach(parts -> regionCityMap.put(String.valueOf(parts[0]), String.valueOf(parts[1])));

            System.out.println("Successfully added!");
            System.out.println(regionCityMap.size());
        }
        catch (IOException ioe)
        {
            System.err.println("Error in storing file to Map");
            ioe.printStackTrace();
        }
    }
    public String searchRegion(String input)
    {
        return regionCityMap.get(input).toString();
    }

    public static void main(String[] args)
    {
        RegionCitySearcher rcs = new RegionCitySearcher();
        rcs.storeRegionAndCitiesToMap();
        System.out.println(rcs.searchRegion("Makati"));
    }
}
