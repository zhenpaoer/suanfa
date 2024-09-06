package huawei.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 现有若干个会议，所有会议共享一个会议室，用数组表示每个会议的开始时间和结束时间，格式：[[会议 1 开始时间，会议 1 结束时间], [会议 2 开始时间，会议 2 结束时间]]。请计算会议室占用时间段。
 *
 * 输入描述：
 * [[会议 1 开始时间，会议 1 结束时间], [会议 2 开始时间，会议 2 结束时间]]
 *
 * 补充说明：
 * 1. 会议室个数范围：[1, 100]；
 * 2. 会议室时间段：[1, 24]；
 *
 * 输出描述：
 * [[会议开始时间，会议结束时间], [会议开始时间，会议结束时间]]，输出格式与输入一致
 *
 * 示例1：
 * 输入
 * [[1,4],[2,5],[7,9],[14,18]]
 *
 * 输出：
 * [[1,5],[7,9],[14,18]]
 *
 * 说明：时间段 [1,4] 和 [2,5] 重叠, 合并为 [1,5]
 *
 * 示例2：
 * 输入
 * [[1,4],[4,5]]
 *
 * 输出
 * [[1,5]]
 */
import java.util.*;
import java.util.stream.Collectors;

public class ScheduleMeetingRoom {

    static class Meeting implements Comparable<Meeting> {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting other) {
            return this.start - other.start;
        }
    }

    static List<Meeting> meetingTime(List<Meeting> meetings) {
        if (meetings.isEmpty()) return Collections.emptyList();

        Collections.sort(meetings);

        List<Meeting> mergedMeetings = new ArrayList<>();
        for (Meeting meeting : meetings) {
            if (mergedMeetings.isEmpty() || mergedMeetings.get(mergedMeetings.size() - 1).end < meeting.start) {
                mergedMeetings.add(meeting);
            } else {
                mergedMeetings.get(mergedMeetings.size() - 1).end = Math.max(mergedMeetings.get(mergedMeetings.size() - 1).end, meeting.end);
            }
        }

        return mergedMeetings;
    }

    static List<Meeting> parseInput(String input) {
        List<Meeting> meetings = new ArrayList<>();
        String[] parts = input.split(",");
        for (int i = 0; i < parts.length; i += 2) {
            String[] time = parts[i].substring(1, parts[i].length() - 1).split("\\s+");
            int start = Integer.parseInt(time[0]);
            int end = Integer.parseInt(time[1].substring(0, time[1].length() - 1));
            meetings.add(new Meeting(start, end));
        }
        return meetings;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        List<Meeting> meetings = parseInput(input);

        List<Meeting> result = meetingTime(meetings);

        System.out.print("[");
        String resultString = result.stream()
                .map(meeting -> "[" + meeting.start + ", " + meeting.end + "]")
                .collect(Collectors.joining(", "));
        System.out.println(resultString + "]");

        // Java中没有直接的"pause"命令，这里只是模拟程序结束前的等待
        try {
            Thread.sleep(5000); // 等待5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
