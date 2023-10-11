package logic.validator;

import logic.EntryValidator;

public class Test implements InputValidator
{
    public static void main(String[] args)
    {
        Test t = new Test();
        boolean result = t.isPhraseValid("abcdefg");
        System.out.println(result);

        EntryValidator ev = new EntryValidator();
        ev.validateEntries();
    }
}
