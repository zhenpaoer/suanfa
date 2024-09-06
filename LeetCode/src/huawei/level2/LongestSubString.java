package huawei.level2;

import java.util.LinkedList;
import java.util.Scanner;

/**
 给定一个字符串，只包含字母和数字，按要求找出字符串中的最长（连续)子串的长度，字符串本身是其最长的子串，子串要求:
 1、只包含1个字母(a-z，A~Z)，其余必须是数字;
 2、字母可以在子串中的任意位置;
 如果找不到满足要求的子串，如全是字母或全是数字，则返回-1。
 输入描述:
 字符串(只包含字母和数字)
 输出描述:
 子串的长度
 示例1:
 输入
 abC124ACb
 输出
 4
 *
 */
public class LongestSubString {

    public static void main(String[] args) {
        //解法1
//        try(Scanner sc = new Scanner(System.in)){
//            String source = sc.nextLine();
//            int count = -1;
//            for (int i = 0; i < source.length() - 1; i++) {
//                for (int j = i + 2; j < source.length() + 1; j++) {
//                     String substring = source.substring(i, j);
//                     if (substring.replaceAll("[0-9]", "").length() == 1) {
//                         count = substring.length() > count ? substring.length() : count;
//                     }
//                 }
//             }
//             System.out.println(count);
//        }
        //解法2,这个可以记录最长子串
        try(Scanner sc = new Scanner(System.in)){
            String str = sc.nextLine();
            System.out.println(lengthOut(str));
        }
    }

    public static Integer lengthOut(String str) {
        int maxLen = -1;
        boolean hasLetter = false;
        int l = 0 , r = 0;
        LinkedList<Integer > list = new LinkedList<>();
        while(r<str.length()) {
            char c = str.charAt(r);
            if(isLetter(c)) {
                hasLetter = true;
                list.add(r);
                //有一个以上字母,删除第一个，
                if(list.size()>1) {
                    l = list.removeFirst()+1;
                }
                //这里只是为了第一个字母或者全都是字母时，不进行计算maxLen
                if(r==l) {
                    r++;
                    continue;
                }
            }
            maxLen = Math.max(maxLen, r-l+1);
        }
        //没有字母，纯数字
        if(!hasLetter) {
            return -1;
        }
        return maxLen;
    }
    public static Integer lengthOut2(String str) {
        char[] arr = str.toCharArray();
        StringBuilder childStr = new StringBuilder();
        boolean hasLetter = false;
        int maxLen = 0;
        for(int i=0,len=arr.length;i<len;i++) {
            char c = arr[i];
            if(isLetter(c)) {
                if(maxLen<childStr.length()) {
                    maxLen=childStr.length();
                }
                childStr.delete(0, childStr.length());
                childStr.append(c);
                hasLetter=true;
            }else {
                childStr.append(c);
            }
        }
        if(!hasLetter) {
            return -1;
        }
        if(hasLetter&&maxLen<=1) {
            return -1;
        }
        return Math.max(maxLen, childStr.length());
    }


    public static boolean isLetter(char c) {
        return ((c>='a'&&c<='z')||(c>='A'&&c<='Z'));
    }

    public static Integer lengthOut1(String str) {
        char[] arr = str.toCharArray();
        StringBuilder childStr = new StringBuilder();
        boolean isChild = false;
        int cl =0;
        for(int i=0,len=arr.length;i<len;i++) {
            char c = arr[i];
            if(c>='0'&&c<='9') {
                childStr.append(c);
                cl = childStr.length();
            }else {
                if(isChild) {
                    if(cl<childStr.length()) {
                        cl = childStr.length();
                    }
                    childStr.delete(0, childStr.length()).append(c);
                }else {
                    isChild = true;
                    childStr.append(c);
                }
            }
        }
        if(childStr.length()==str.length()) {
            return -1;
        }
        if(cl==1&&isChild) {
            return -1;
        }
        return cl;
    }
}