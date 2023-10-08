package logic;

import static java.lang.System.*;

public class Test implements InputValidator, Searcher
{
    public static void main(String[] args)
    {
        Test t =  new Test();
//        out.println(t.isStringInputValid("kate"));
//        out.println(t.isEnglishWord("Kate"));
//        out.println();
//        out.println(t.isStringInputValid("chloe"));
//        out.println(t.isEnglishWord("Chloe"));
//        out.println();
//        out.println(t.isStringInputValid("aaron"));
//        out.println(t.isEnglishWord("aaron"));
//        out.println();
//        out.println(t.isStringInputValid("blueberry"));
//        out.println(t.isEnglishWord("blueberry"));
//        out.println();
//        out.println(t.isStringInputValid("fkajkafjakfjakf"));
//        out.println(t.isEnglishWord("fkajkafjakfjakf"));
//        out.println();
//        out.println(t.isEnglishWord("anecito"));
//        out.println(t.isEnglishWord("Sophisticated"));
//        out.println(t.searchRegion("Makati"));
//        out.println(t.searchIndustry("Information Technology"));
        out.println(t.isSentenceValid("The Mischievous"));
        out.println(t.isEnglishWord("you"));
        out.println(t.isSentenceValid("Mango sandwich"));

    }
}
