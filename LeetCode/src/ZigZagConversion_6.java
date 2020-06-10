/**
 * Created by zhangzhen on 2020/5/4
 */

/**
 * @Date 2020/5/4
 *将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 **/
public class ZigZagConversion_6 {
	public static String convert(String s, int numRows) {
		if ( s.length() ==0 || numRows <= 1) return s;
		StringBuilder[] array = new StringBuilder[numRows];
		int index = 0;
		int dir = 1;
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			array[i] = new StringBuilder();
		}
		for (char c:s.toCharArray()){
			array[index].append(c);
			index+=dir;
			if (index == 0 || index == numRows-1 ) dir = -dir;
		}
		for (int i = 0; i < array.length; i++) {
			res.append(array[i]);
		}

		return res.toString();
	}

	public static void main(String[] args) {
		String s = "LEETCODEISHIRING";
		int row = 3;
		System.out.println(convert(s, row));
	}
}
