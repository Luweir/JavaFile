package Test;


import java.util.*;

public class Solution {
    public String validIPAddress(String queryIP) {
        if (queryIP == null || queryIP.length() == 0)
            return "Neither";
        if (queryIP.charAt(queryIP.length() - 1) == '.' || queryIP.charAt(queryIP.length() - 1) == ':')
            return "Neither";
        String[] strs = queryIP.split("\\.");
        if (strs.length == 4) {
            for (int i = 0; i < 4; i++) {
                String cur = strs[i];
                if (cur.length() == 0 || (cur.length() > 1 && cur.charAt(0) == '0'))
                    return "Neither";

                for (int j = 0; j < cur.length(); j++) {
                    if (cur.charAt(j) < '0' || cur.charAt(j) > '9')
                        return "Neither";
                }

                int num = Integer.parseInt(cur);
                if (num > 255)
                    return "Neither";
            }
            return "IPv4";
        } else {
            strs = queryIP.split(":");
            if (strs.length != 8)
                return "Neither";
            for (int i = 0; i < 8; i++) {
                String cur = strs[i];
                cur = cur.toLowerCase();
                for (int j = 0; j < cur.length(); j++) {
                    if (cur.length() == 0 || cur.length() > 4)
                        return "Neither";
                    if ((cur.charAt(j) <= 'f' && cur.charAt(j) >= 'a') || (cur.charAt(j) <= '9' && cur.charAt(j) >= '0'))
                        continue;
                    else {
                        return "Neither";
                    }
                }
            }
            return "IPv6";
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().validIPAddress("2001:db8:85a3:0::8a2E:0370:7334"));

    }
}

