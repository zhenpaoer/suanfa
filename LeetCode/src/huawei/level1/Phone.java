package huawei.level1;

import java.util.ArrayList;
import java.util.List;

/**
 * 智能手机方便了我们生活的同时，也侵占了我们不少的时间。“手机App防沉迷系统”能够让我们每天合理地规划手机App使用时间，在正确的时间做正确的事。
 *
 * 它的大概原理是这样的：
 *
 * 1. 在一天24小时内，可以注册每个App的允许使用时段;
 *
 * 2. 一个时间段只能使用一个App;
 * 3 .App有优先级，数值越高，优先级越高。注册使用时段时，如果高优先级的App时间和低优先级的时段有冲突，则系统会自动注销低优先级的时段，如果App的优先级相同，则后添加的App不能注册。
 *
 * 请编程实现，根据输入数据注册App，并根据输入的时间点，返回时间点使用的App名称，如果该时间点没有注册任何App，请返回字符串“NA”。
 *
 * 输入描述:
 * 第一行表示注册的App数量 N(N ≤ 100);
 * 第二部分包括 N 行，每行表示一条App注册数据;
 * 最后一行输入一个时间点，程序即返回该时间点使用的App;
 * 例如：
 * 2
 * App1 1 09:00 10:00
 * App2 2 11:00 11:30
 * 09:30
 *
 * 数据说明如下：
 * 1. N行注册数据以空格分隔，四项数依次表示：App名称、优先级、起始时间、结束时间
 * 2. 优先级1~5，数字越大，优先级越高
 * 3. 时间格式 HH:MM，小时和分钟都是两位，不足两位前面补0
 * 4. 起始时间需小于结束时间，否则注册不上
 * 5. 注册信息中的时间段包含起始时间点，不包含结束时间点
 *
 * 输出描述：
 * 输出一个字符串，表示App名称，或NA表示空闲时间
 *
 * 示例1：
 * 输入
 * 1
 * App1 1 09:00 10:00
 * 09:30
 *
 * 输出
 * App1
 *
 * 说明：App1注册在9点到10点间，9点半可用的应用名是App1
 *
 * 示例2：
 * 输入
 * 2
 * App1 1 09:00 10:00
 * App2 2 09:10 09:30
 * 09:20
 *
 * 输出
 * App2
 *
 * 说明：App1和App2的时间段有冲突，App2的优先级比App1高，注册App2后，系统将App1的注册信息自动注销后，09:20时刻可用应用名是App2.
 *
 * 示例3：
 * 输入
 * 2
 * App1 1 09:00 10:00
 * App2 2 09:10 09:30
 * 09:50
 *
 * 输出
 * NA
 *
 * 说明：App1被注销后，09:50时刻没有应用注册，因此输出NA
 */
import java.util.*;

class App {
    String appName;
    int priority;
    int startTime;
    int endTime;

    public App(String appName, int priority, int startTime, int endTime) {
        this.appName = appName;
        this.priority = priority;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

public class Phone {

    // 时间转换
    static int timeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    // 注册应用程序并处理冲突
    static List<App> registerApps(int N, Scanner scanner) {
        List<App> rg = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            String name = lineScanner.next();
            int priority = lineScanner.nextInt();
            String start = lineScanner.next();
            String end = lineScanner.next();

            int startTime = timeToMinutes(start);
            int endTime = timeToMinutes(end) - 1; // Adjust end time for inclusivity

            if (startTime > endTime) continue;

            App newApp = new App(name, priority, startTime, endTime);
            if (!hasConflict(newApp, rg)) {
                rg = removeConflictsAndAddNew(newApp, rg);
            }
        }
        return rg;
    }

    // 检查新应用是否与已注册应用冲突
    static boolean hasConflict(App newApp, List<App> rg) {
        for (App app : rg) {
            if (newApp.startTime <= app.endTime && newApp.endTime >= app.startTime) {
                if (newApp.priority <= app.priority) {
                    return true;
                }
            }
        }
        return false;
    }

    // 移除与新应用冲突的旧应用并添加新应用
    static List<App> removeConflictsAndAddNew(App newApp, List<App> currentApps) {
        List<App> res = new ArrayList<>();
        for (App x : currentApps) {
            if (!(newApp.startTime <= x.endTime && newApp.endTime >= x.startTime)) {
                res.add(x);
            }
        }
        res.add(newApp);
        return res;
    }

    // 查询特定时间点的App
    static void queryAppAtTime(List<App> rg, String queryTime) {
        int queryMinute = timeToMinutes(queryTime);
        for (App x : rg) {
            if (x.startTime <= queryMinute && x.endTime >= queryMinute) {
                System.out.println(x.appName);
                return;
            }
        }
        System.out.println("NA");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<App> rg = registerApps(N, scanner);

        String queryTime = scanner.nextLine();
        queryAppAtTime(rg, queryTime);

        scanner.close();
    }
}