package huawei.level1;

/**
 * 机器人搬砖，一共有 N 堆砖存放在 N 个不同的仓库中，第i堆砖中有 bricks[ 块砖头，要求在8小时内搬完。机器人每小时能搬砖的数量取决于有多少能量格，机器人一个小时中只能在一个仓库中搬砖，机器人的能量格只在这一个小时有效，为使得机器人损耗最小化，应尽量减小每次补充的能量格数。为了保障在 8 小时内能完成搬砖任务，请计算每小时给机器人充能的最小能量格数。
 * 无需考虑机器人补充能力格的耗时;
 * 无需考虑机器人搬砖的耗时;
 * 机器人每小时补充能量格只在这一个小时中有效;
 * 输入描述
 * 第一行为一行数字，空格分隔
 * 输出描述
 * 机器人每小时最少需要充的能量格，若无法完成任务，输出-1
 * 用例
 * 输入    30 12 25 8 19
 * 输出    15
 * 说明    无
 * 输入    10 12 25 8 19 8 6 4 17 19 20 30
 * 输出    -1
 * 说明    砖的堆数为12堆存放在12个仓库中，机器人一个小时内只能在一个仓库搬砖，不可能完成任务。
 */
public class Rabbit {

    public static boolean canFinish(int[] bricks, double energyPerHour, int totalHours) {
        for (int brick : bricks) {
            int hoursNeeded = (int) Math.ceil(brick / energyPerHour);
            totalHours -= hoursNeeded;
            if (totalHours < 0) {
                return false;
            }
        }
        return true;
    }

    public static int minEnergy(int[] bricks) {
        if (bricks.length > 8) {
            return -1;
        }
        int maxBricks = 0;
        for (int brick : bricks) {
            if (brick > maxBricks) {
                maxBricks = brick;
            }
        }
        int low = 1, high = maxBricks;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canFinish(bricks, mid, 8)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] test1 = {30, 12, 25, 8, 19};
        int[] test2 = {10, 12, 25, 8, 19, 8, 6, 4, 17, 19, 20, 30};

        int output1 = minEnergy(test1);
        int output2 = minEnergy(test2);
        System.out.println(output1 + " " + output2);
    }
}