package huawei.level1;

import java.util.ArrayList;
import java.util.List;

public class MinestSum {

    public static void main(String[] args) {
        String str1 = "bb1234aa";
        String strf = "bb12-34aa";
        String strg = "bb12-34aa34f-24";
        System.out.println(sum(strg));
    }

    public static String sum(String sf) {
        Long sum = 0L;
        if (!sf.contains("-")) {
            for (int i = 0; i < sf.length(); i++) {
                char c = sf.charAt(i);
                if (isdig(c)) {
                    String digo = String.valueOf(c);
                    sum = sum + Long.parseLong(digo);
                }
            }
            return sum + "";
        } else {
            int start = 0;
            int end = 0;
            for (int i = 0; i < sf.length(); i++) {
                char c = sf.charAt(i);
                if (start == 0 && end == 0) {
                    if (isdig(c)) {
                        String digo = String.valueOf(c);
                        sum = sum + Long.parseLong(digo);
                    }
                }
                if (c == '-') {
                    start = i + 1;
                    StringBuffer digl = new StringBuffer();
                    digl.append("-");
                    for (int j = i + 1; j < sf.length(); j++) {
                        if (isdig(sf.charAt(j))) {
                            digl.append(sf.charAt(j));
                        } else {
                            end = j - 1;
                            break;
                        }
                    }
                    sum = sum + Long.parseLong(digl.toString());
                }
                if (end == i) {
                    start = 0;
                    end = 0;
                }
            }
        }
        return sum + "";
    }

    public static boolean isdig(char c) {
        if (Character.isDigit(c)) {
            return true;
        }
        return false;
    }
}
