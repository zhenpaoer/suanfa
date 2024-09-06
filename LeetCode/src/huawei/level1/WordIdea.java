package huawei.level1;

import java.util.Collections;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.TreeSet;

/**
 * 主管期望你来实现英文输入法单词联想功能，需求如下：
 *
 * 依据用户输入的单词前缀，从已输入的英文语句中联想出用户想输入的单词。
 *
 * 按字典序输出联想到的单词序列，如果联想不到，请输出用户输入的单词前缀。 注意
 *
 * 英文单词联想时区分大小写
 *
 * 缩略形式如"don't" 判定为两个单词 "don"和 "t"
 *
 * 输出的单词序列不能有重复单词，且只能是英文单词，不能有标点符号
 *
 * 输入
 * 输入两行 首行输入一段由英文单词word和标点构成的语句str 接下来一行为一个英文单词前缀pre  0 < word.length() <= 20  0 < str.length() <= 10000  0 < pre.length() <= 20
 *
 * 输出
 * 输出符合要求的单词序列或单词前缀 存在多个时，单词之间以单个空格分割
 *
 * 示例一
 * 输入
 * I love you
 * 输出
 * He
 *
 * 示例二
 * 输入
 * The furthest distance in the world,Is not between life and death,But whenI stand in front of you,Yet you don't khow that I love you
 * f
 * 输出
 * front furthest
 */
public class WordIdea {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            String str = sc.nextLine();
            String pre = sc.nextLine();
            System.out.println(resultStr(str,pre));
        }
    }
    public static String resultStr(String str,String pre) {
        String[] tmp = str.split("[^a-zA-Z]");
        TreeSet<String> cache = new TreeSet<>();
        Collections.addAll(cache, tmp);
        //返回字符串
        StringJoiner sj = new StringJoiner(" ");
        cache.stream().filter(s-> s.startsWith(pre)).forEach(s-> sj.add(s));

        if(sj.length()>0) {
            return sj.toString();
        }else {
            return pre;
        }
    }
}