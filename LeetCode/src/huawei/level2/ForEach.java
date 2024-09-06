package huawei.level2;

import java.util.*;

/**
 * 有一棵二叉树，每个节点由一个大写字母标识(最多26个节点）。
 *
 * 现有两组字母，分别表示后序遍历（左孩子->右孩子->父节点）和中序遍历（左孩子->父节点->右孩子）的结果，请你输出层序遍历的结果。
 *
 * 输入
 * 每个输入文件一行，第一个字符串表示后序遍历结果，第二个字符串表示中序遍历结果。（每串只包含大写字母）
 *
 * 中间用单空格分隔。
 *
 * 输出
 * 输出仅一行，表示层序遍历的结果，结尾换行。
 *
 * 样例输入
 * CBEFDA CBAEDF
 * 样例输出
 * ABDCEF
 */

import java.util.*;

/**
 * 有一棵二叉树，每个节点由一个大写字母标识(最多26个节点）。
 * 现有两组字母，分别表示后序遍历（左孩子->右孩子->父节点）和中序遍历（左孩子->父节点->右孩子）的结果，请你输出层序遍历的结果。
 */
public class ForEach {
    private static String result = "";//设置全局结果的返回

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.next();//后序
        String s2 = scanner.next();//中序
        String[] split1 = s1.split("");//切割
        String[] split2 = s2.split("");
        Map<Integer, String> nomalSort = new HashMap<>();//用map集合来存储每个节点在数组中的位置以及节点值
        myfunction(split1, split2, nomalSort, 0);//从中序序列和后续序列得到完全二叉树的数组形式，key对应在数组中的位置，value对应该节点的值
        bianli(nomalSort, 0);//得到map集合存储的完全二叉树的数组形式，进行层次遍历，得到结果
        System.out.println(result);
    }

    /**
     * 层次遍历
     *
     * @param nomalSort
     * @param i         起始节点
     */
    private static void bianli(Map<Integer, String> nomalSort, int i) {
        Queue<Integer> stack = new LinkedList<>();//用队列来实现层次遍历
        stack.add(i);
        while (!stack.isEmpty()) {
            Integer pop = stack.poll();
            result += nomalSort.get(pop);
            if (nomalSort.containsKey(pop * 2 + 1)) {
                stack.add(pop * 2 + 1);
            }
            if (nomalSort.containsKey(pop * 2 + 2)) {
                stack.add(pop * 2 + 2);
            }
        }
    }

    /**
     * @param split1    后序
     * @param split2    中序
     * @param nomalSort 正常
     * @param index     正常顺序中的数组索引
     */
    private static void myfunction(String[] split1, String[] split2, Map<Integer, String> nomalSort, int index) {
        if (split1.length == 0 || split2.length == 0) {//如果中序或者后序数组的大小为0，说明该子树为空。
            return;
        }
        if (split1.length == 1) {//如果中序或者后序数组的大小为1，直接可以赋值，而无需做其他操作。
            nomalSort.put(index, split1[0]);
            return;
        }
        nomalSort.put(index, split1[split1.length - 1]);//将父节点加入map集合
        String parent = split1[split1.length - 1];//得到后序序列的最后一个结点，最后一个节点就是该部分的最顶层的节点
        for (int i = 0; i < split2.length; i++) {//循环遍历中序序列得到，根结点所在位置，然后即可找到左右子树
            if (split2[i].equals(parent)) {
                String[] inLeft = Arrays.copyOfRange(split2, 0, i);//中序遍历的左子树
                String[] inRight = Arrays.copyOfRange(split2, i + 1, split2.length);//中序遍历中的右子树

                String[] postLeft = Arrays.copyOfRange(split1, 0, i);//后序遍历中的左子树
                String[] postRight = Arrays.copyOfRange(split1, i, split1.length - 1);//后序遍历中的右子树

                myfunction(postLeft, inLeft, nomalSort, index * 2 + 1);//递归进入下一层（左孩子结点）
                myfunction(postRight, inRight, nomalSort, index * 2 + 2);//递归进入下一层（右孩子结点）

                break;
            }
        }
    }
}
