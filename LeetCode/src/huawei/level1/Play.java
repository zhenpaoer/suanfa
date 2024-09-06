package huawei.level1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * XX市机场停放了多架飞机，每架飞机都有自己的航班号CA3385, CZ6678, SC6508
 *
 * 等，航班号的前2个大写字母（或数字）代表航空公司的缩写，后面4个数字代表航班信息。
 *
 * 但是XX市机场只有一条起飞用跑道，调度人员需要安排目前停留在机场的航班有序起
 *
 * 飞。为保障航班的有序起飞，调度员首先按照航空公司的缩写（航班号前2个字母）对所有航班
 *
 * 进行排序，同一航空公司的航班再按照航班号的后4个数字进行排序最终获得安排好的航班的
 *
 * 起飞顺序。
 *
 * 请编写一段代码根据输入的航班号信息帮助调度员输出航班的起飞顺序。航空公司缩写排
 *
 * 序按照从特殊符号$ & *,0~9,A~Z排序；
 *
 * 输入描述
 *
 * 第一行输入航班信息，多个航班号之间用逗号（“，"）分隔，输入的航班号不超过100个
 *
 * 例如：
 *
 * CA3385,CZ6678,SC6508,DU7523,HK4456,MK0987
 *
 * 备注:航班号为6位长度，后4位为纯数字，不考虑存在后4位重复的Q场号
 *
 * 输出描述
 *
 * CA3385,CZ6678/ DU7523,HK4456,MK0987,SC6508
 *
 * 示例1:
 *
 * 输入
 *
 * CA3385,CZ6678,SC6508,DU7523,HK4456,MK987
 *
 * 输出
 *
 * CA3385,CZ6678/ DU7523,HK4456,MK0987,SC6508
 *
 * 说明
 *
 * 输入目前停留在该机场的航班号，输出为按照调度排序后输出的有序的航班号
 *
 * 示例2：
 *
 * 输入
 *
 * MU1087,CA9908,3U0045,FM1703
 *
 * 输出
 *
 * 3U0045,CA9908,FM1703,MU1087
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Play {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        String[] flightInfo = sc.nextLine().split(",");
        List<FlightName> flightNameList = new ArrayList<>();
        for (int i = 0; i < flightInfo.length; i++) {
            FlightName flightName = new FlightName(flightInfo[i].substring(0, 2)
                    , flightInfo[i].substring(2));
            flightNameList.add(flightName);
        }

        Collections.sort(flightNameList);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < flightNameList.size(); i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(flightNameList.get(i).getFlightName());
        }
        System.out.println(String.valueOf(sb));


    }

    public static class FlightName implements Comparable<FlightName> {
        private String prefix;
        private String flightNum;


        public FlightName(String prefix, String flightNum) {
            this.prefix = prefix;
            this.flightNum = flightNum;
        }

        @Override
        public int compareTo(FlightName o) {
            if (o.prefix.equals(this.prefix)) {
                return o.flightNum.compareTo(this.flightNum);
            } else {
                return this.prefix.compareTo(o.prefix);
            }
        }

        public String getFlightName() {
            return this.prefix + this.flightNum;
        }
    }
}