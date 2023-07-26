import java.util.TreeMap;

public class RomanToArabicConverter {
    private static final TreeMap<Integer, String> romanToArabicMap = new TreeMap<>();
    private static final TreeMap<String, Integer> arabicToRomanMap = new TreeMap<>();

    static {
        romanToArabicMap.put(1, "I");
        romanToArabicMap.put(4, "IV");
        romanToArabicMap.put(5, "V");
        romanToArabicMap.put(9, "IX");
        romanToArabicMap.put(10, "X");
        romanToArabicMap.put(40, "XL");
        romanToArabicMap.put(50, "L");
        romanToArabicMap.put(90, "XC");
        romanToArabicMap.put(100, "C");
        romanToArabicMap.put(400, "CD");
        romanToArabicMap.put(500, "D");
        romanToArabicMap.put(900, "CM");
        romanToArabicMap.put(1000, "M");

        arabicToRomanMap.put("I", 1);
        arabicToRomanMap.put("IV", 4);
        arabicToRomanMap.put("V", 5);
        arabicToRomanMap.put("IX", 9);
        arabicToRomanMap.put("X", 10);
        arabicToRomanMap.put("XL", 40);
        arabicToRomanMap.put("L", 50);
        arabicToRomanMap.put("XC", 90);
        arabicToRomanMap.put("C", 100);
        arabicToRomanMap.put("CD", 400);
        arabicToRomanMap.put("D", 500);
        arabicToRomanMap.put("CM", 900);
        arabicToRomanMap.put("M", 1000);
    }

    public static int convert(String roman) {
        int result = 0;
        int i = 0;

        while (i < roman.length()) {
            String twoChars = (i + 1 < roman.length()) ? roman.substring(i, i + 2) : null;

            if (twoChars != null && arabicToRomanMap.containsKey(twoChars)) {
                result += arabicToRomanMap.get(twoChars);
                i += 2;
            } else {
                String oneChar = roman.substring(i, i + 1);
                if (!arabicToRomanMap.containsKey(oneChar)) {
                    throw new IllegalArgumentException("Некорректное римское число: " + roman);
                }
                result += arabicToRomanMap.get(oneChar);
                i += 1;
            }
        }

        return result;
    }

    public static String convert(int arabic) {
        if (arabic <= 0) {
            throw new IllegalArgumentException("Римские числа могут быть только положительными");
        }

        StringBuilder roman = new StringBuilder();

        while (arabic > 0) {
            int highestValue = romanToArabicMap.floorKey(arabic);
            String romanNumeral = romanToArabicMap.get(highestValue);
            roman.append(romanNumeral);
            arabic -= highestValue;
        }

        return roman.toString();
    }
}
