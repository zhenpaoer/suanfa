package huawei.level2;

import java.util.Scanner;

/**
 * 从前有个村庄，村民们喜欢在各种田地上插上小旗子，旗子上标识了各种不同的数字。某天集体村民决定将覆盖相同数字的最小矩阵形的土地的分配给为村里做出巨大贡献的村民，请问，此次分配土地，做出贡献的村民中最大会分配多大面积？
 *
 * 输入描述：
 *
 * 第一行输入m和n，m代表村子的土地的长，n代表土地的宽。第二行开始输入地图上的具体标识。
 *
 * 输出描述：
 *
 * 输出需要分配的土地面积，即包含相同数字旗子的最小矩阵中的最大面积。旗子上的数字为1-500，土地边长不超过500。未插旗子的土地用0标识。
 * 输入：
 * 3 3
 * 1 0 1
 * 0 0 0
 * 0 1 0
 * 输出
 * 9
 *
 */
public class FenPeiTuDi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] a = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    int x = i, y = j;
                    while (x < m && a[x][j] == 1) {
                        y = j;
                        while (y < n && a[x][y] == 1) {
                            y++;
                        }
                        x++;
                    }
                    ans = Math.max(ans, (x - i) * (y - j));
                }
            }
        }
        System.out.println(ans);
    }
}
