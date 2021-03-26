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
        work.checkContainsString(stringWithSun, "sun");
        work.checkContainsString(stringWithoutSun, "sun");
        String stringWithOracle = "database Oracle";
        String stringWithoutOracle = "database Mysql";
        work.checkEndWith(stringWithOracle, "Oracle");
        work.checkEndWith(stringWithoutOracle, "Oracle");
        String stringWithJava = "Java is so cool";
        String stringWithoutJava = "Home sweet home";
        work.checkStartWith(stringWithJava, "Java");
        work.checkStartWith(stringWithoutJava, "Java");
        String stringWithA = "ha-ha-ha";
        work.replaceInString(stringWithA, "a","o");
        String  bigString = "Blackbird singing in the dead of night\n" +
                "Take these broken wings and learn to fly\n" +
                "All your life\n" +
                "You were only waiting for this moment to arise";
        work.substringFromTo(bigString, 44, 99);
        String stringToSplit = "I want to split this string.";
        String[] arrayOfSplitString = work.stringSplit(stringToSplit, " ");
        String stringReverse = work.reverseString("word");
    }
}
