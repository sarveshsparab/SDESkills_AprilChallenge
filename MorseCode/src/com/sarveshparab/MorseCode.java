package com.sarveshparab;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: MorseCode I & II
 *   -- https://beta.sdeskills.com/30day-challenge/day3
 *   -- https://beta.sdeskills.com/30day-challenge/day5
 *
 * Author Details:
 *   -- Sarvesh Parab
 *   -- sarveshsparab@gmail.com
 *   -- http://www.sarveshparab.com/
 *   -- https://www.linkedin.com/in/sarveshsparab/
 */
public class MorseCode {

    private static Map<Character, String> encodeMap;
    private static Map<String, Character> decodeMap;

    static {
        encodeMap = new HashMap<>();
        decodeMap = new HashMap<>();

        encodeMap.put('a', ".-");     decodeMap.put(".-", 'a');
        encodeMap.put('b', "-...");   decodeMap.put("-...", 'b');
        encodeMap.put('c', "-.-.");   decodeMap.put("-.-.", 'c');
        encodeMap.put('d', "-..");    decodeMap.put("-..", 'd');
        encodeMap.put('e', ".");      decodeMap.put(".", 'e');
        encodeMap.put('f', "..-.");   decodeMap.put("..-.", 'f');
        encodeMap.put('g', "--.");    decodeMap.put("--.", 'g');
        encodeMap.put('h', "....");   decodeMap.put("....", 'h');
        encodeMap.put('i', "..");     decodeMap.put("..", 'i');
        encodeMap.put('j', ".---");   decodeMap.put(".---", 'j');
        encodeMap.put('k', "-.-");    decodeMap.put("-.-", 'k');
        encodeMap.put('l', ".-..");   decodeMap.put(".-..", 'l');
        encodeMap.put('m', "--");     decodeMap.put("--", 'm');
        encodeMap.put('n', "-.");     decodeMap.put("-.", 'n');
        encodeMap.put('o', "---");    decodeMap.put("---", 'o');
        encodeMap.put('p', ".--.");   decodeMap.put(".--.", 'p');
        encodeMap.put('q', "--.-");   decodeMap.put("--.-", 'q');
        encodeMap.put('r', ".-.");    decodeMap.put(".-.", 'r');
        encodeMap.put('s', "...");    decodeMap.put("...", 's');
        encodeMap.put('t', "-");      decodeMap.put("-", 't');
        encodeMap.put('u', "..-");    decodeMap.put("..-", 'u');
        encodeMap.put('v', "...-");   decodeMap.put("...-", 'v');
        encodeMap.put('w', ".--");    decodeMap.put(".--", 'w');
        encodeMap.put('x', "-..-");   decodeMap.put("-..-", 'x');
        encodeMap.put('y', "-.--");   decodeMap.put("-.--", 'y');
        encodeMap.put('z', "--..");   decodeMap.put("--..", 'z');
        encodeMap.put('0', "-----");  decodeMap.put("-----", '0');
        encodeMap.put('1', ".----");  decodeMap.put(".----", '1');
        encodeMap.put('2', "..---");  decodeMap.put("..---", '2');
        encodeMap.put('3', "...--");  decodeMap.put("...--", '3');
        encodeMap.put('4', "....-");  decodeMap.put("....-", '4');
        encodeMap.put('5', ".....");  decodeMap.put(".....", '5');
        encodeMap.put('6', "-....");  decodeMap.put("-....", '6');
        encodeMap.put('7', "--...");  decodeMap.put("--...", '7');
        encodeMap.put('8', "---..");  decodeMap.put("---..", '8');
        encodeMap.put('9', "---..");  decodeMap.put("---..", '9');
    }

    public static String encode(String str) {
        StringBuilder sb = new StringBuilder();

        char[] cArr = str.toLowerCase().toCharArray();
        for (int c = 0; c < cArr.length; c++){
            sb.append(encodeMap.get(cArr[c]));
            sb.append(" ");
        }

        return sb.toString().trim();
    }

    public static String decode(String str) {
        StringBuilder sb = new StringBuilder();

        String[] sArr = str.split(" ");
        for (int c = 0; c < sArr.length; c++){
            sb.append(decodeMap.get(sArr[c]));
        }

        return sb.toString().toUpperCase();
    }
}

