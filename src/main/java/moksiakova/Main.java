package main.java.moksiakova;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello, world!");

        String checkLength = "checkLength";
        System.out.printf("1. length \"%s\" - %d\n\n",
                checkLength,
                checkLength.length());

        String string1 = "string1";
        String string2 = "String1";
        String string3 = "string3";
        System.out.printf("2.1. '%s' and '%s' is equals? %s.\n\n",
                string1, string2, string1.equalsIgnoreCase(string2));
        System.out.printf("2.2. '%s' and '%s' is equals? %s. \n\n",
                string1, string3, string1.equalsIgnoreCase(string3));

        String newString = new String("StringToLiteral").intern();

        String stringToArray = "stringToArray";
        char[] charArrayFromString = stringToArray.toCharArray();

        String stringToByte = "stringToArray";
        byte[] byteArrayFromString = stringToByte.getBytes();

        String stringToUpperCase = "stringToUpperCase";
        System.out.printf("6. \"%s\" in Upper Case \"%s\" \n",
                stringToUpperCase,
                stringToUpperCase.toUpperCase()
        );

        String findA = "I like apple!";
        System.out.printf("7. Char \"a\" have first position in %d char in sentence \"%s\"\n",
                findA.indexOf("a")+1,
                findA);

        String findALast = "I like apple and banana!";
        System.out.printf("8. Char \"a\" have last position in %d char in sentence \"%s\"\n",
                findALast.lastIndexOf("a")+1,
                findALast);

        String stringWithSun = "sun shine";
        String stringWithoutSun = "sweet";
        System.out.printf("9. '%s' contains 'sun'? %s. \n '%s' contains 'sun'? %s.\n",
                stringWithSun,
                stringWithSun.contains("sun"),
                stringWithoutSun,
                stringWithoutSun.contains("sun"));

        String stringWithOracle = "database Oracle";
        String stringWithoutOracle = "database Mysql";
        System.out.printf("10. '%s' end with 'Oracle'? %s. \n '%s' end with 'Oracle'? %s.\n",
                stringWithOracle,
                stringWithOracle.endsWith("Oracle"),
                stringWithoutOracle,
                stringWithoutOracle.endsWith("Oracle"));

        String stringWithJava = "Java is so cool";
        String stringWithoutJava = "Home sweet home";
        System.out.printf("11. '%s' start with 'Java'? %s. \n '%s' start with 'Java'? %s.\n",
                stringWithJava,
                stringWithJava.startsWith("Java"),
                stringWithoutJava,
                stringWithoutJava.startsWith("Java"));

        String stringWithA = "ha-ha-ha";
        System.out.printf("12. When Change char 'a' to 'o' in '%s' then return '%s'\n",
                stringWithA, stringWithA.replace("a","o"));

        String  bigString = "Blackbird singing in the dead of night\n" +
                "Take these broken wings and learn to fly\n" +
                "All your life\n" +
                "You were only waiting for this moment to arise";
        System.out.printf("13. Substring from 44 to 90 char for \n\n\t '%s' \n\n is \n\n\t'%s'\n\n",
                bigString,
                bigString.substring(44,99));

        String stringToSplit = "I want to split this string.";
        String[] arrayOfSplitString = stringToSplit.split(" ");

        String stringToMirror = "word";
        System.out.printf("15. '%s' to back read is '%s'. \n",
                stringToMirror,
                new StringBuilder(stringToMirror).reverse().toString());
    }
}
