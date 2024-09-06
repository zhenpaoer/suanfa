package huawei.level2;

import jdk.nashorn.internal.ir.Block;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 有一个总空间为100字节的堆，现要从中新申请一块内存，
 * 内存分配原则为优先紧接着前一块已使用内存分配空间足够且最接近申请大小的空闲内存。
 *
 * 输入描述：
 * 第1行是1个整数，表示期望申请的内存字节数。
 * 第2到第N行是用空格分割的两个整数，表示当前已分配的内存的情况，每一行表示一块已分配的连续内存空间，每行的第1和第2个整教分别表示偏移地址和内存块大小，如:
 * 0 1
 *
 * 3 2
 * 表示0偏移地址开始的1个字节和3偏移地址开始的2个字节已被分配，其余内存空闲。
 * 输出描述：
 * 若申请成功，输出申请到内存的偏移
 * 若申请失败，输出-1。
 *
 * 备注：
 * 1.若输入信息不合法或无效，则申请失败
 *
 * 2.若没有足够的空间供分配，则申请失败
 *
 * 3.堆内存信息有区域重叠或有非法值等都是无效输入
 *
 * 示例1：
 *
 * 输入：
 *
 * 1
 * 0 1
 * 3 2
 *
 * 输出：
 *
 * 1
 *
 * 说明：
 * 堆中已使用的两块内存是偏移从0开始的1字节和偏移从3开始的2字节，空闲的两块内存是偏移从1开始2个字节和偏移从5开始95字节根据分配原则，新申请的内存应从1开始分配1个字节，所以输出偏移为1。
 *
 */
public class Heap {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //初始化一个总空间为100字节的堆
        MemoryAllocator Memory = new MemoryAllocator(100);
        System.out.println(Memory);//打印初始化好的内存状态
        System.out.println("请输入已占用的内存块的数量：");
        int n = sc.nextInt();
        Queue<Block> OccupiedBlock = new LinkedList<>();
        System.out.println("请输入已占用内存块的偏移地址和大小：");
        int lastOffsetAddress =-1;
        boolean validInput = true;
        for(int i=0;i<n;i++){
            int OffsetAddress = sc.nextInt();
            int Size = sc.nextInt();
            boolean allocated = true;
            if (OffsetAddress<0||OffsetAddress>Memory.totalSize||Size<=0||Size>Memory.totalSize-OffsetAddress){
                System.out.println("输入不合法，请重新输入！");
                validInput = false;
                break;
            }
            //检查重叠
            if (i>0&&OffsetAddress<lastOffsetAddress+OccupiedBlock.peek().Size){
                System.out.println("内存块存在重叠，请重新输入！");
                validInput = false;
                break;
            }
            OccupiedBlock.add(new Block(OffsetAddress,Size,allocated));
            lastOffsetAddress = OffsetAddress;
        }
        if (validInput){
            //输入有效，将已占用块放入内存
            Memory.addOccupiedBlocks(OccupiedBlock);
            System.out.println("输入成功，内存状态如下：");
            System.out.println(Memory);
        }
        System.out.println("请输入请求的内存大小：");
        int requestSize = sc.nextInt();
        //找最合适的块
        Block BestFitBlock= Memory.findBestFitBlock(Memory,requestSize);
        Memory.addBlock(BestFitBlock,requestSize);
        System.out.println("申请成功，申请成功后的内存为：");
        System.out.println(Memory);
    }
    //Block类
    public static class Block{
        int OffsetAddress;
        int Size;
        boolean allocated;

        public Block(int offsetAddress, int size, boolean allocated){
            this.OffsetAddress = offsetAddress;
            this.Size = size;
            this.allocated = allocated;
        }
        //输出格式
        public String toString(){
            return "Block{"+
                    "偏移地址："+OffsetAddress+
                    ";块大小："+Size+
                    ";是否分配："+allocated+
                    "}\n";
        }
    }
    public static class MemoryAllocator {
        int totalSize;
        Queue<Block> Memory;

        public MemoryAllocator(int totalSize) {
            this.totalSize = totalSize;
            this.Memory = new LinkedList<>();
            Memory.add(new Block(0, totalSize, false));
        }
        public void addBlock(Block bestFitBlock,int requestSize) {
            Memory.remove(bestFitBlock);//注意移除
            if (bestFitBlock.Size>requestSize){
                Block slice = new Block(bestFitBlock.OffsetAddress+requestSize,bestFitBlock.Size-requestSize,false);
                Memory.add(slice);
                bestFitBlock.Size = requestSize;//将最佳块大小改为需求大小，再一次分割
                bestFitBlock.allocated = true;
                Memory.add(bestFitBlock);
            } else if (bestFitBlock.Size==requestSize) {
                bestFitBlock.allocated = true;
            }
            System.out.println("-===="+bestFitBlock);

        }
        public void addOccupiedBlocks(Queue<Block> occupiedBlock) {
            for (Block block : occupiedBlock) {
                Block FreeBlock = findFreeBlock(block.OffsetAddress, block.Size);
                System.out.println("当前可分配的空白块：" + FreeBlock);///
                //若空闲块大小大于等于占用块大小
                if (FreeBlock.Size >= block.Size) {
                    //若空闲块面积大于占用块，则划分新的空闲块
                    //①若占用块的偏移地址=空闲块的偏移地址,前相等
                    if (block.OffsetAddress == FreeBlock.OffsetAddress) {
                        if (FreeBlock.Size == block.Size) {//刚好放入,放入被占用后切割出来的空白块=0,无空白
                            Memory.remove(FreeBlock);//移除空闲块???太早了
                            Memory.add(block);//将占用的块放入
                            Memory.add(new Block(FreeBlock.OffsetAddress + block.Size, 0, false));
                        } else {//剩余一段空白：空白Block:[FreeBlock.OffsetAddress+block.Size,FreeBlock.Size-block.Size,false]
                            Memory.remove(FreeBlock);//移除空闲块???太早了
                            Memory.add(block);//将占用的块放入
                            Memory.add(new Block(FreeBlock.OffsetAddress + block.Size, FreeBlock.Size - block.Size, false));
                        }
                    }

                    //②若占用块的偏移地址大于空闲块的偏移地址且加上长度后不会越界，即在中间
                    if (block.OffsetAddress > FreeBlock.OffsetAddress && block.OffsetAddress + block.Size < FreeBlock.OffsetAddress + FreeBlock.Size) {
                        Memory.remove(FreeBlock);//移除空闲块???太早了
                        Memory.add(block);//将占用的块放入
                        //前空白
                        Memory.add(new Block(FreeBlock.OffsetAddress, block.OffsetAddress - FreeBlock.OffsetAddress, false));
                        //后空白
                        Memory.add(new Block(block.OffsetAddress + block.Size, FreeBlock.OffsetAddress + FreeBlock.Size - (block.OffsetAddress + block.Size), false));
                    }

                    //③若占用块和空闲地址后端对齐，后相等
                    if (block.OffsetAddress + block.Size == FreeBlock.OffsetAddress + FreeBlock.Size) {
                        if (FreeBlock.Size == block.Size) {//大小相等，无空白，与①一致
                            Memory.remove(FreeBlock);//移除空闲块???太早了
                            Memory.add(block);//将占用的块放入
                            Memory.add(new Block(FreeBlock.OffsetAddress + block.Size, 0, false));
                        } else {//会有前空白
                            Memory.remove(FreeBlock);//移除空闲块???太早了
                            Memory.add(block);//将占用的块放入
                            Memory.add(new Block(FreeBlock.OffsetAddress, FreeBlock.Size - block.Size, false));
                        }
                    }
                } else {
                    System.out.println("找不到合适的空闲块，无法分配！");
                }
            }
        }

        private Block findFreeBlock(int offsetAddress, int size) {
            //找的是空闲且可用的
            System.out.println("找空白块时的总内存状态：\n" + Memory);//
            for (Block block : Memory) {
                //块未分配/且块的偏移地址小于等于传来的偏移地址/而且块的结尾大于等于传来的传来的块的结尾
                if (!block.allocated && block.OffsetAddress <= offsetAddress && (block.OffsetAddress + block.Size) >= (offsetAddress + size)) {
                    return block;
                }
            }
            return null;
        }

        //我的思路：只找空白的块，不做筛查，筛查交给add做？
        private Queue<Block> findFreeBlocks(int size) {
            //找的是纯空闲
            System.out.println("找空白块时的总内存状态：\n" + Memory);//
            Queue<Block> FreeBlocks = new LinkedList<>();
            for (Block block : Memory) {
                //块未分配/且块的大小大于需要的大小
                if (!block.allocated && block.Size >= size) {
                    FreeBlocks.add(block);
                }
            }
            return FreeBlocks;
        }


        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("内存状态：\n");
            for (Block block : Memory) {
                sb.append(block.toString());//append("\n")
            }
            return sb.toString();
        }


        //接收requestSize寻找最合适的空白块，有就返回偏移地址，无就返回找不着
        private static Block findBestFitBlock(MemoryAllocator Memory, int requestSize) {

            System.out.println("找最合适块的内存传值检查：\n" + Memory);//√
            //Memory.findFreeBlocks(requestSize);
            Queue<Block> FreeBlocks = Memory.findFreeBlocks(requestSize);
            System.out.println("找到的空白块有：" + FreeBlocks);
            //找到了能放得下的空白块后，对最合适进行判断：接近需要的大小,因为已经选取的大于等于的大小，此处只需要选取最小的！
            Block BestFitBlock = null;
            for (Block block : FreeBlocks) {
                if (BestFitBlock == null || block.Size < BestFitBlock.Size) {
                    BestFitBlock = block;
                }
            }
            System.out.println("找到的最好的空闲块的偏移地址为："+BestFitBlock.OffsetAddress);
            return BestFitBlock;
        }
    }

}
