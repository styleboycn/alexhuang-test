/* 
 * Copyright (c) 2016, S.F. Express Inc. All rights reserved.
 */
package com.alexhuang.idgenerator;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

class IDCoder {

    private static final char[] ENCODE_TABLE = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '=', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '_', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z' };

    private static Map<Character, Integer> DECODE_TABLE;

    static {
        DECODE_TABLE = new HashMap<Character, Integer>();
        for (int i = 0; i < ENCODE_TABLE.length; i++) {
            DECODE_TABLE.put(ENCODE_TABLE[i], i);
        }
    }

    public static String encode(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            appendEncodeBinaryString(sb, b);
        }

        StringBuilder encodeBuf = new StringBuilder();
        int index = 0;
        int len = sb.length();
        while (index < len) {
            int end = index + 6;
            if (end > len) {
                end = len;
            }
            String s = sb.substring(index, end);
            int ch = Integer.parseInt(s, 2);
            encodeBuf.append(ENCODE_TABLE[ch]);
            index = end;
        }
        return encodeBuf.toString();
    }

    public static byte[] decode(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, n = s.length(); i < n; i++) {
            char ch = s.charAt(i);
            int value = DECODE_TABLE.get(ch);
            appendDecodeBinaryString(sb, value, i == (n - 1));
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int index = 0;
        int len = sb.length();
        while (index < len) {
            int end = index + 8;
            if (end > len) {
                end = len;
            }
            String s1 = sb.substring(index, end);
            int ch = Integer.parseInt(s1, 2);
            os.write(ch);
            index = end;
        }
        return os.toByteArray();
    }

    private static void appendEncodeBinaryString(StringBuilder sb, int value) {
        String s = Integer.toBinaryString(value);
        int len = s.length();
        int size = 8;
        if (len == size) {
            sb.append(s);
        } else if (len > size) {
            sb.append(s, len - size, len);
        } else if (len < size) {
            for (int i = 0, n = size - len; i < n; i++) {
                sb.append('0');
            }
            sb.append(s);
        }
    }

    private static void appendDecodeBinaryString(StringBuilder sb, int value, boolean last) {
        String s = Integer.toBinaryString(value);
        int len = s.length();
        int size = 6;
        if (len == size) {
            sb.append(s);
        } else if (len > size) {
            sb.append(s, len - size, len);
        } else if (len < size) {
            if (last) {
                int total = sb.length() + size;
                int count = total % 8;
                if (count > 0) {
                    size -= count;
                }
            }
            for (int i = 0, n = size - len; i < n; i++) {
                sb.append('0');
            }
            sb.append(s);
        }
    }
}
