package main.java.moksiakova.lessons.lesson1;

public class WorkWithString {

    public void checkLength(String checkString) {
        System.out.printf("1. Length of string '%s' is %d chars. \n",
                checkString, checkString.length());
    }

    public void equalsString(String firstString, String secondString) {
        String resultString = "2. '%s' and '%s' is ";
        if (firstString.equalsIgnoreCase(secondString)) { resultString += "equals ignore case."; }
        else { resultString += "not equals ignore case.";}
        System.out.printf(resultString+"\n", firstString, secondString);
    }

    public String createStringWithConstructor(String string) {
        return new String(string).intern();
    }

    public char[] getCharArrayFromString(String stringToArray) {
        return stringToArray.toCharArray();
    }

    public byte[] getByteArrayFromString(String stringToByte) {
        return stringToByte.getBytes();
    }

    public void stringToUpperCase(String stringToUpperCase) {
        System.out.printf("6. \"%s\" in Upper Case \"%s\" \n",
                stringToUpperCase,
                stringToUpperCase.toUpperCase()
        );
    }

    public int findFirstPositionInString(String stringForSearch, String searchable) {
        int index = stringForSearch.indexOf(searchable) + 1;
        System.out.printf("7. Char \"a\" have first position in %d char in sentence \"%s\"\n",
                index,
                stringForSearch);
        return index;
    }

    public int findLastPositionInString(String stringForSearch, String searchable) {
        int index = stringForSearch.lastIndexOf(searchable)+1;
        System.out.printf("8. Char \"a\" have last position in %d char in sentence \"%s\"\n",
                index,
                stringForSearch);
        return index;
    }

}