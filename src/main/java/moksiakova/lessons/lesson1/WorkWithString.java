package main.java.moksiakova.lessons.lesson1;

/**
 * Class for working with string. */
public class WorkWithString {

    /**
     * Method get length of string.
     * @param checkString for calculate length.
     * */
    public void checkLength(String checkString) {
        System.out.printf("1. Length of string '%s' is %d chars.\n",
                checkString, checkString.length());
    }

    /**
     * Method check equals of strings.
     * @param firstString first string for equals.
     * @param secondString second string for equals.
     * */
    public void equalsString(String firstString, String secondString) {
        String resultString = "2. '%s' and '%s' is ";
        if (firstString.equalsIgnoreCase(secondString)) { resultString += "equals ignore case."; }
        else { resultString += "not equals ignore case.";}
        System.out.printf(resultString+"\n", firstString, secondString);
    }

    /**
     * Method for create with constructor and add string to linters.
     * @param string string for create with constructor and add to linters.
     * @return string.
     * */
    public String createStringWithConstructor(String string) {
        return new String(string).intern();
    }

    /**
     * Method get from string char array.
     * @param stringToArray string to array.
     * @return char array from stringToArray. */
    public char[] getCharArrayFromString(String stringToArray) {
        return stringToArray.toCharArray();
    }

    /**
     * Method get from string byte array.
     * @param stringToByte string to array of byte.
     * @return byte array from stringToByte. */
    public byte[] getByteArrayFromString(String stringToByte) {
        return stringToByte.getBytes();
    }

    /**
     * Method set string to upper case.
     * @param  stringToUpperCase source string.
     */
    public void stringToUpperCase(String stringToUpperCase) {
        System.out.printf("6. \"%s\" in Upper Case \"%s\" \n",
                stringToUpperCase,
                stringToUpperCase.toUpperCase()
        );
    }

    /**
     * Method find first position string in source string.
     * @param stringForSearch source string.
     * @param searchable the string we are looking for.
     * @return first position. */
    public int findFirstPositionInString(String stringForSearch, String searchable) {
        int index = stringForSearch.indexOf(searchable) + 1;
        System.out.printf("7. Char \"a\" have first position in %d char in sentence \"%s\"\n",
                index,
                stringForSearch);
        return index;
    }

    /**
     * Method find last position string in source string.
     * @param stringForSearch source string.
     * @param searchable the string we are looking for.
     * @return last position. */
    public int findLastPositionInString(String stringForSearch, String searchable) {
        int index = stringForSearch.lastIndexOf(searchable)+1;
        System.out.printf("8. Char \"a\" have last position in %d char in sentence \"%s\"\n",
                index,
                stringForSearch);
        return index;
    }

    /**
     * Method check that one string contains other string.
     * @param stringForSearch source string.
     * @param searchable the string we are looking for. */
    public void checkContainsString(String stringForSearch, String searchable) {
        String result;
        if (stringForSearch.contains(searchable)) { result = "contains"; }
        else { result = "not contains"; }
        System.out.printf("9. '%s' %s '%s'.\n", stringForSearch, result, searchable);
    }

    /**
     * Method check that source string ends with other string.
     * @param original source string.
     * @param endWith the string we are looking for. */
    public void checkEndWith(String original, String endWith) {
        String result;
        if (original.contains(endWith)) { result = "end with"; }
        else { result = "not end with"; }
        System.out.printf("10. '%s' %s '%s'.\n", original, result, endWith);
    }

    /**
     * Method check that source string starts with other string.
     * @param original source string.
     * @param startWith the string we are looking for. */
    public void checkStartWith(String original, String startWith) {
        String result;
        if (original.contains(startWith)) { result = "start with"; }
        else { result = "not start with"; }
        System.out.printf("11. '%s' %s '%s'.\n", original, result, startWith);
    }

    /**
     * Method replace strings in source string.
     * @param source string.
     * @param  from string for replace.
     * @param to string to replace. */
    public void replaceInString(String source, String from, String to) {
        System.out.printf("12. When Change '%s' to '%s' in '%s' then return '%s'\n",
                from, to, source, source.replace(from,to));
    }

    /**
     * Method search substring in source string from to index.
     * @param source string.
     * @param  from index from get substring.
     * @param to index to get substring. */
    public void substringFromTo(String source, int from, int to) {
        System.out.printf("13. Substring from %d to %d char for \n\n\t '%s' \n\n is \n\n\t'%s'.\n\n",
                from, to, source, source.substring(from,to));
    }

    /**
     * Method split string of splitter.
     * @param source string.
     * @param  splitter for string.
     * @return array of string. */
    public String[] stringSplit(String source, String splitter) {
        return source.split(splitter);
    }

    /**
     * Method reverse string.
     * @param source string.
     * @return reversed string.*/
    public String reverseString(String source) {
        String result = new StringBuilder(source).reverse().toString();
        System.out.printf("15. '%s' to back read is '%s'. \n", source, result);
        return result;
    }


}