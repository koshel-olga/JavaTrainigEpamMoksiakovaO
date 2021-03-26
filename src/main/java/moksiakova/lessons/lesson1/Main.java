package main.java.moksiakova.lessons.lesson1;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello, world!");

        WorkWithString work = new WorkWithString();
        work.checkLength("bla-bla-bla");
        work.equalsString("example","Example");
        char[] charArrayFromString = work.getCharArrayFromString("stringToArray");
        byte[] byteArrayFromString = work.getByteArrayFromString("stringToArray");
        work.stringToUpperCase("stringToUpperCase");
        int firstIndex = work.findFirstPositionInString("I like apple!","a");
        int lastIndex = work.findLastPositionInString("I like apple and banana!","a");

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
