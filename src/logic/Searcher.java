package logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;

public interface Searcher
{
    private Map storeRegionAndCitiesToMap()
    {
        Map<String, String> regionCityMap = new HashMap<>();
        File regionCityFile = new File("RegionAndCitiesInPhilippines");
        try
        {
            Files.lines(Paths.get(regionCityFile.getAbsolutePath()))
                    .map(parts -> parts.split(","))
                    .forEach(parts -> regionCityMap.put(String.valueOf(parts[0]), String.valueOf(parts[1])));

        }
        catch (IOException ioe)
        {
            System.err.println("Error in storing file to Map");
            ioe.printStackTrace();
        }
        return regionCityMap;
    }

    default String searchRegion(String input)
    {
        Map<String, String> regionCityMap = storeRegionAndCitiesToMap();
        return regionCityMap.get(input).toString();
    }
}
