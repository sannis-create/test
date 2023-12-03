//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//public class WordCounter
//{
//    public static void main(String[] args)
//    {
//        String text = "This is a sample text. " +
//                "This text contains sample words for counting words frequency.";
//
//        Map<String, Integer> hmap = new HashMap<>();
//        Scanner obj = new Scanner(text);
//        obj.useDelimiter(" ");
//
//        while (obj.hasNext())
//        {
//            String word = obj.next().toLowerCase();
//            word = removeNonAlphabeticCharacters(word);
//            if (!word.isEmpty())
//            {
//                hmap.put(word, hmap.getOrDefault(word, 0) + 1);
//            }
//        }
//        obj.close();
//        for (Map.Entry<String, Integer> entry : hmap.entrySet())
//        {
//            System.out.println(entry.getKey() + ": Frequency: " + entry.getValue());
//        }
//    }
//
//    private static String removeNonAlphabeticCharacters(String input)
//    {
//        String result = "";
//
//        for (int i = 0; i < input.length(); i++)
//        {
//            char currentChar = input.charAt(i);
//            if ((currentChar >= 'A' && currentChar <= 'Z') || (currentChar >= 'a' && currentChar <= 'z')) {
//                // Append the alphabet letter to the result
//                result += currentChar;
//            }
//        }
//
//        return result;
//    }
//
//}
