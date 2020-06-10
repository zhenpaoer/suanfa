/**
 * Created by zhangzhen on 2020/5/4
 */

/**
 * @ClassName QiuMO
 * @Description: TODO
 * @Author zhangzhen
 * @Date 2020/5/4 
 * @Version V1.0
 **/
public class QiuMO {
	public static void main(String[] args) {
		int a1 = 12138;
		int one = a1 % 10;
		int ten = a1 % 100 / 10;
		int han = a1 % 1000 / 100;
		int tho = a1 % 10000 / 1000;
		int wan = a1 / 10000;
		System.out.println("个位数是： "+one);
		System.out.println("十位数是： "+ten);
		System.out.println("百位数是： "+han);
		System.out.println("千位数是： "+tho);
		System.out.println("万位数是： "+wan);
	}
}
