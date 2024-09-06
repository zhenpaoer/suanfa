package huawei.level1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 小明今年升学到小学一年级，来到新班级后发现其他小朋友们身高参差不齐，然后就想基于各小朋友和自己的身高差对他们进行排序，请帮他实现排序。
 * 输入：
 * 100 10
 * 95 96 97 98 99 101 102 103 104 105
 * 输出：
 * 99 101 98 102 97 103 96 104 95 105
 */
public class sortStrundent {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int mingHeight = scanner.nextInt();
        int studentsNum = scanner.nextInt();

        List<Integer> studentsHeight = new ArrayList<>();
        for (int i = 0; i < studentsNum; i++) {
            studentsHeight.add(scanner.nextInt());
        }

        studentsHeight.sort((a,b) ->{
            if (Math.abs(mingHeight - a) > Math.abs(mingHeight - b)) {
                return 1;
            } else if (Math.abs(mingHeight - a) < Math.abs(mingHeight - b)) {
                return -1;
            } else { // 身高一样时，按照本身身高升序排序
                return a < b ? -1 : 1;
            }
        });

        for (Integer student : studentsHeight) {
            System.out.print(student + " ");
        }
    }

}
