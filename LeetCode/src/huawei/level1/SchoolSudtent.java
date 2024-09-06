package huawei.level1;

import java.util.ArrayList;
import java.util.List;

/**
 * 某学校举行运动会，学生们按编号(1、2、3...n)进行标识，现需要按照身高由低到高排列，对身高相同的人，按体重由轻到重排列;对于身高体重都相同的人，维持原有的编号顺序关系。请输出排列后的学生编号。
 *
 * 输入描述:
 *
 * 两个序列，每个序列由n个正整数组成(0<n<=100)。第一个序列中的数值代表身高，第二个序列中的数值代表体重。
 *
 * 输出描述:
 *
 * 排列结果，每个数值都是原始序列中的学生编号，编号从1开始
 * 输入
 * 4
 *
 * 100 100 120 130
 *
 * 40 30 60 50
 * 输出
 * 2 1 3 4
 */

import java.util.*;

public class SchoolSudtent {
    private static class Student {
        private int height;
        private int weight;
        private int id;

        public Student(int height, int weight, int id) {
            this.height = height;
            this.weight = weight;
            this.id = id;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        int n = scanner.nextInt();
        int[] height = new int[n];
        int[] weight = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            weight[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            students.add(new Student(height[i], weight[i], i + 1));
        }
        // 根据身高升序，身高相同则按体重升序，再相同则保持原编号顺序
        students.sort((o1, o2) -> {
            if (o1.height != o2.height) {
                return o1.height - o2.height;
            } else if (o1.weight != o2.weight) {
                return o1.weight - o2.weight;
            } else {
                return o1.id - o2.id;
            }
        });
        for (Student student : students) {
            System.out.println(student.id);
        }

    }
}
