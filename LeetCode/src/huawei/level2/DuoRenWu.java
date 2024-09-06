package huawei.level2;

import java.util.*;

/**
 * 一个应用启动时，会有多个初始化任务需要执行，并且任务之间有依赖关系，例如A任务依赖B任务，那么必须在B任务执行完成之后，才能开始执行A任务。
 *
 * 现在给出多条任务依赖关系的规则，请输入任务的顺序执行序列，规则采用贪婪策略，即一个任务如果没有依赖的任务，则立刻开始执行，如果同时有多个任务要执行，则根据任务名称字母顺序排序。
 *
 * 例如: B任务依赖A任务，C任务依赖A任务，D任务依赖B任务和C任务，同时，D任务还依赖E任务。那么执行任务的顺序由先到后是:A任务，E任务，B任务，C任务，D任务。这里A和E任务都是没有依赖的，立即执行
 * 那么输入为：&quot;B->A C->A D->B D->C D->E&quot;   输出结果为“A E B C D”
 * 输入描述
 * 输入参数每个元素都表示任意两个任务之间的依赖关系，输入参数中符号”->”表示依赖方向。
 *
 * 例如A->B表示A依赖B，多个依赖之间用单个空格分割
 *
 * 输出描述
 * 输出为排序后的启动任务列表，多个任务之间用单个空格分割。
 *
 */
import java.util.ArrayList;
import java.util.List;


import java.util.*;

public class DuoRenWu {

    public static String sortTasks(String dependencies) {
        // 使用Map来存储每个任务的依赖列表和入度
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();

        // 解析输入并构建图及入度表
        String[] relations = dependencies.split(" ");
        for (String relation : relations) {
            String[] parts = relation.split("->");
            String dependee = parts[1];
            String dependency = parts[0];

            // 添加依赖关系到图
            graph.computeIfAbsent(dependee, k -> new ArrayList<>()).add(dependency);

            // 更新入度表
            inDegree.put(dependency, inDegree.getOrDefault(dependency, 0) + 1);

            // 如果dependee之前没有在图中，则默认入度为0
            inDegree.putIfAbsent(dependee, 0);
        }

        // 创建一个优先队列来存储所有入度为0的节点，按字母顺序排序
        PriorityQueue<String> zeroInDegreeQueue = new PriorityQueue<>(Comparator.naturalOrder());
        for (Map.Entry<String, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                zeroInDegreeQueue.offer(entry.getKey());
            }
        }

        // 拓扑排序
        List<String> result = new ArrayList<>();
        while (!zeroInDegreeQueue.isEmpty()) {
            String current = zeroInDegreeQueue.poll();
            result.add(current);

            // 移除当前节点的所有出边，并更新相关节点的入度
            for (String neighbor : graph.getOrDefault(current, Collections.emptyList())) {
                int newInDegree = inDegree.get(neighbor) - 1;
                inDegree.put(neighbor, newInDegree);
                if (newInDegree == 0) {
                    zeroInDegreeQueue.offer(neighbor);
                }
            }
        }

        // 检查是否所有任务都被排序（即图中是否还有剩余节点）
//        if (result.size() != graph.size()) {
//            throw new RuntimeException("存在环，任务无法完全排序");
//        }

        // 返回排序后的任务列表
        return String.join(" ", result);
    }

    public static void main(String[] args) {
        String dependencies = "B->A C->A D->B D->C D->E";
        System.out.println(sortTasks(dependencies)); // 应输出 A E B C D

        dependencies = "A->B C->B";
        System.out.println(sortTasks(dependencies)); // 应输出 B A C

        dependencies = "C->A B->A";
        System.out.println(sortTasks(dependencies)); // 应输出 A B C
    }
}