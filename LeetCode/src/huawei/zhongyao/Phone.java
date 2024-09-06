package huawei.zhongyao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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