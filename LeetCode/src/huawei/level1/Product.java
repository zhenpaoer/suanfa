package huawei.level1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 某个产品当前迭代周期内有N个特性({F1,F2,...,FN})需要进行覆盖测试，每个特性都被评估了对应的优先级，特性使用其ID作为下标进行标识。
 * 设计了M个测试用例({T1,T2,...,TM})，每个用例对应了一个覆盖特性的集合，测试用例使用其ID作为下标进行标识，测试用例的优先级定义为其覆盖的特性的优先级之和。
 * 在开展测试之前，需要制定测试用例的执行顺序，规则为:优先级大的用例先执行，如果存在优先级相同的用例，用例ID小的先执行。
 *
 * 输入描述:
 * 第一行输入为N和M，N表示特性的数量，M表示测试用例的数量，0<N<100.0<M<100.之后N行表示特性ID=1到特性ID=N的优先级。
 * 再接下来M行表示测试用例ID=1到测试用例ID=M关联的特性的ID的列表。
 * 输出描述:
 * 按照执行顺序(优先级从大到小)输出测试用例的ID，每行一个ID.
 *
 *
 * 备注:
 *
 * 测试用例覆盖的ID不重复。
 *
 * 示例：
 *
 * 输入
 *
 * 5 4
 * 1
 * 1
 * 2
 * 3
 * 5
 * 1 2 3
 * 1 4
 * 3 4 5
 * 2 3 4
 * 输出
 *
 * 3
 *
 * 4
 *
 * 1
 *
 * 2
 *
 * 说明
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class TestCase implements Comparable<TestCase> {
    int id;
    int priority;

    public TestCase(int id, int priority) {
        this.id = id;
        this.priority = priority;
    }

    // 实现Comparable接口，首先按照优先级降序排序，若优先级相同，则按照ID升序排序
    @Override
    public int compareTo(TestCase other) {
        if (this.priority != other.priority) {
            return other.priority - this.priority;
        } else {
            return this.id - other.id;
        }
    }
}

public class Product {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        scanner.nextLine(); // 读取并跳过行尾的换行符

        // 读取每个特性的优先级
        int[] featurePriorities = new int[N];
        for (int i = 0; i < N; i++) {
            featurePriorities[i] = scanner.nextInt();
        }

        List<TestCase> testCases = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            scanner.nextLine(); // 读取并跳过行尾的换行符
            String[] coveredFeatures = scanner.nextLine().split(" ");
            int prioritySum = 0;
            for (String featureIdStr : coveredFeatures) {
                int featureId = Integer.parseInt(featureIdStr) - 1; // 特性ID转换为数组下标
                prioritySum += featurePriorities[featureId];
            }
            testCases.add(new TestCase(i + 1, prioritySum)); // 测试用例ID是从1开始的
        }

        // 根据优先级和ID对测试用例进行排序
        Collections.sort(testCases);

        // 输出排序后的测试用例ID
        for (TestCase testCase : testCases) {
            System.out.println(testCase.id);
        }
    }
}