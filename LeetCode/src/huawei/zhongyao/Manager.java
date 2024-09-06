package huawei.zhongyao;


import java.util.Scanner;
import java.util.Stack;

/**
 * 实现一个模拟目录管理功能的软件，输入一个命令序列，输出最后一条命令运行结果。<br>
 * 支持命令：<br>  1）
 * 创建目录命令：mkdir 目录名称，如mkdir abc为在当前目录创建abc目录，如果已存在同名目录则不执行任何操作。此命令无输出。<br>  2）
 * 进入目录命令：cd 目录名称, 如cd abc为进入abc目录，特别地，cd ..为返回上级目录，如果目录不存在则不执行任何操作。此命令无输出。<br> <div>   3）
 * 查看当前所在路径命令：pwd，输出当前路径字符串。 </div> <div>  <span>
 *     约束：
 * </span><br> <span> 1）目录名称仅支持小写字母；<span>mkdir和cd命令的参数</span>仅支持单个目录，
 * 如：mkdir abc和cd abc；不支持嵌套路径和绝对路径，如mkdir abc/efg, cd abc/efg, mkdir /abc/efg, cd /abc/efg是不支持的。</span><br> <span> 2）
 * 目录符号为/，根目录/作为初始目录。</span><br> <span> 3）
 * 任何不符合上述定义的无效命令不做任何处理并且无输出
 */
public class Manager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        Stack<String> pathStack = new Stack<>();
        pathStack.push("/");
        for (int i = 0; i < n; i++) {
            String command = sc.nextLine();
            String[] parts = command.split(" ");
            if (parts[0].equals("mkdir")) {
                String dirName = parts[1];
                if (!pathStack.contains(dirName)) {
                    pathStack.push(dirName);
                }
            } else if (parts[0].equals("cd")) {
                String dirName = parts[1];
                if (dirName.equals("..")) {
                    pathStack.pop();
                } else if (!pathStack.contains(dirName)) {
                    pathStack.push(dirName);
                }
            } else if (parts[0].equals("pwd")) {
                StringBuilder sb = new StringBuilder();
                for (String dir : pathStack) {
                    sb.append(dir).append("/");
                }
                System.out.println(sb.toString());
            }
        }
    }
}
