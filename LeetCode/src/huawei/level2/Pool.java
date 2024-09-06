package huawei.level2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 请实现一个简易内存池,根据请求命令完成内存分配和释放。内存池支持两种操作命令，REQUEST和RELEASE，其格式为：
 *
 * REQUEST=请求的内存大小，表示请求分配指定大小内存.
 *
 * 如果分配成功，返回分配到的内存首地址；
 * 如果内存不足，或指定的大小为0，则输出error；
 *
 * RELEASE=释放的内存首地址 表示释放掉之前分配的内存，
 *
 * 释放成功无需输出，如果释放不存在的首地址则输出error；
 *
 * 注意：
 * 1. 内存池总大小为100字节；
 * 2. 内存池地址分配必须是连续内存，并优先从低地址分配；
 * 3. 内存释放后可被再次分配，已释放的内存在空闲时不能被二次释放；
 * 4. 不会释放已申请的内存块的中间地址；
 * 5. 释放操作只是针对首地址所对应的单个内存块进行操作，不会影响其它内存块；
 *
 *
 * 输入描述：
 * 首行为整数 N , 表示操作命令的个数，取值范围：0 < N <= 100;
 * 接下来的N行, 每行将给出一个操作命令，操作命令和参数之间用 “=”分割;
 *
 * 输出描述：
 *
 * 1. 请求分配指定大小内存时，如果分配成功，返回分配到的内存首地址；如果内存不足，或指定的大小为0，则输出error；
 * 2. 释放掉之前分配的内存时，释放成功无需输出，如果释放不存在的首地址则输出error。
 *
 * 示例1：
 * 输入
 * 2
 * REQUEST=10
 * REQUEST=20
 *
 * 输出
 * 0
 *
 * 示例2：
 * 输入
 * 5
 * REQUEST=10
 * REQUEST=20
 * REQUEST=0
 * REQUEST=20
 * REQUEST=10
 *
 * 输出
 * 0
 * 10
 * 30
 * 0
 *
 * 示例说明：
 * 第一条指令，申请地址0~9的10个字节内存，返回首地址0；
 * 第二条指令，申请地址10~29的20个字节内存，返回首地址10；
 * 第三条指令，释放首地址为0的内存申请，0~9的地址内存被释放，变为空闲，释放成功，无需输出；
 * 第四条指令，申请20字节内存，0~9的地址内存连续空间不足20字节，往后查找到30~49地址，返回首地址30；
 * 第五条指令，申请10字节内存，0~9的地址内存连续空间足够，返回首地址0；
 */
import java.util.*;
import java.io.*;

public class Pool {
    private static final int POOL_SIZE = 100;
    private static int[] memoryPool = new int[POOL_SIZE];
    private static Map<Integer, Integer> allocatedMemory = new HashMap<>();

    public static int findFreeSpace(int size) {
        for (int i = 0; i <= POOL_SIZE - size; i++) {
            boolean isFree = true;
            for (int j = i; j < i + size; j++) {
                if (memoryPool[j] != 0) {
                    isFree = false;
                    break;
                }
            }
            if (isFree) return i;
        }
        return -1; // 未找到足够连续的空闲空间
    }

    public static void allocateMemory(int addr, int size) {
        for (int i = addr; i < addr + size; i++) {
            memoryPool[i] = 1; // 标记为已分配
        }
        allocatedMemory.put(addr, size);
    }

    public static void releaseMemory(int addr) {
        if (!allocatedMemory.containsKey(addr)) {
            System.out.println("error");
            return;
        }
        int size = allocatedMemory.get(addr);
        for (int i = addr; i < addr + size; i++) {
            memoryPool[i] = 0; // 标记为未分配
        }
        allocatedMemory.remove(addr);
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符

        for (int i = 0; i < N; i++) {
            String str = scanner.nextLine();
            int location = str.indexOf('=');
            String operation = str.substring(0, location).trim();
            int size = Integer.parseInt(str.substring(location + 1).trim());

            if ("REQUEST".equals(operation)) {
                if (size <= 0) {
                    System.out.println("error");
                } else {
                    int addr = findFreeSpace(size);
                    if (addr >= 0) {
                        System.out.println(addr);
                        allocateMemory(addr, size);
                    } else {
                        System.out.println("error");
                    }
                }
            } else if ("RELEASE".equals(operation)) {
                // RELEASE操作不需要size，但这里为了匹配输入格式，我们仍然读取它
                releaseMemory(size); // 注意：这里实际上应该使用另一个参数来指定要释放的内存地址
                // 但由于输入格式的问题，这里直接使用了size（这是不正确的，仅为了匹配原C++代码逻辑）
                // 正确的做法应该是从输入中读取要释放的内存地址
            }
        }

        scanner.close();
    }
}