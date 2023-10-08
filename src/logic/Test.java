package logic;

public class Test implements InputValidator, Searcher
{
    public static void main(String[] args)
    {
        Test t =  new Test();
        System.out.println(t.isEnglishWord("Sophisticated"));
        System.out.println(t.searchRegion("Makati"));
    }
}
