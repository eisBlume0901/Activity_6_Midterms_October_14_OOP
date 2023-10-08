package ai;

import java.util.HashMap;
import java.util.Scanner;

public class Test
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Pasig", "NCR");
        hashMap.put("Makati", "NCR");
        System.out.println(hashMap);

        System.out.println(hashMap.get("Pasig"));

    }
}
