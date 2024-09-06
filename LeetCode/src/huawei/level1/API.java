package huawei.level1;

/**
 * 某个产品的RESTful API集合部署在服务器集群的多个节点上，近期对客户端访问日志进行了采集，需要统计各个API的访问频次，根据热点信息在服务器节点之间做负载均衡，现在需要实现热点信息统计查询功能。
 * <p>
 * RESTful API是由多个层级构成，层级之间使用 / 连接，如 /A/B/C/D 这个地址，A属于第一级，B属于第二级，C属于第三级，D属于第四级。
 * <p>
 * 现在负载均衡模块需要知道给定层级上某个名字出现的频次，未出现过用0表示，实现这个功能。
 * <p>
 * 输入描述
 * 第一行为N，表示访问历史日志的条数，0 ＜ N ≤ 100。
 * <p>
 * 接下来N行，每一行为一个RESTful API的URL地址，约束地址中仅包含英文字母和连接符 / ，最大层级为10，每层级字符串最大长度为10。
 * <p>
 * 最后一行为层级L和要查询的关键字。
 * <p>
 * 输出描述
 * 输出给定层级上，关键字出现的频次，使用完全匹配方式（大小写敏感）。
 * <p>
 * 示例1
 * 输入： 5
 * <p>
 * /huawei/computing/no/one
 * <p>
 * /huawei/computing
 * <p>
 * /huawei
 * <p>
 * /huawei/cloud/no/one
 * <p>
 * /huawei/wireless/no/one
 * <p>
 * 2 computing
 * <p>
 * 输出： 2
 * <p>
 * 说明： 在第二层级上，computing出现了2次，因此输出2
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class API {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取日志条数
        int n = Integer.parseInt(scanner.nextLine().trim());

        // 存储日志
        List<String> logs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            logs.add(scanner.nextLine().trim());
        }

        // 读取查询参数
        String[] query = scanner.nextLine().trim().split(" ");
        int l = Integer.parseInt(query[0]);
        String k = query[1];

        int count = 0;

        // 遍历日志，统计符合条件的次数
        for (String log : logs) {
            String[] urlParts = log.split("/");
            if (urlParts.length > l && urlParts[l].equals(k)) {
                count++;
            }
        }

        // 输出结果
        System.out.println(count);

        // 关闭Scanner
        scanner.close();
    }
}