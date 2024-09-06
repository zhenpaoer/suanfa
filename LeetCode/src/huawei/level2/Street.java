package huawei.level2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 假定街道是棋盘型的，每格距离相等，车辆通过每格街道需要时间均为 timePerRoad；
 * 街道的街口（交叉点）有交通灯，灯的周期T(=lights[row][col])各不相同；
 * 车辆可直行、左转和右转，其中直行和左转需要等相应T时间的交通灯才可通行，右转无需等待。
 *
 * 现给出 n*m 个街口的交通灯周期，以及起止街口的坐标，计算车辆经过两个街口的最短时间。
 * 其中：
 * 1）起点和终点的交通灯不计入时间，且可以任意方向经过街口；
 * 2）不可超出 n*m 个街口，不可跳跃，但边线也是道路（即 lights[0][0] -> lights[0][1] 是有效路径）。
 *
 * 2.题目要求
 * 入口函数定义:
 * * lights : n*m 个街口每个交通灯的周期，值范围[0,120]，n和m的范围为[1,9]
 * * timePerRoad : 相邻两个街口之间街道的通过时间,范围为[0,600]
 * * rowStart : 起点的行号
 * * colStart : 起点的列号
 * * rowEnd : 终点的行号
 * * colEnd : 终点的列号
 * * return : lights[rowStart][colStart] 与 lights[rowEnd][colEnd] 两个街口之间的最短通行时间
 *
 * int calcTime(int[][] lights,int timePerRoad,int rowStart,int colStart,int rowEnd,int colEnd)
 *
 * 定义一个动点类：横纵坐标：row col ，用时：spendtime ，面向：facedirection ，转向：movedirection。
 * ①初始化起点，给它赋值四个面向（绝对位置），0上，1右，2下，3左（顺时针，与遍历顺序一致）；
 * ②动点当前值与移动方向叠加，对在边界内的动点进行计算；
 * ③将计算完的动点存入根据用时从小到大排列的优先队列，投入递归继续计算；
 * ④遍历到终点坐标返回当前时间（因为优先队列的特性，拿取的最顶端就是最小的）。
 *
 */
public class Street {
    public static int shorttime =  Integer.MAX_VALUE;
    public static int shortesPassTime = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入时间周期数组
        System.out.println("请输入车辆通过每格街道需要的时间timePerRoad[0,600]：");
        int timePerRoad = sc.nextInt();
        if (timePerRoad>=0&&timePerRoad<=600){
            System.out.println("时间timePerRoad："+timePerRoad+"，设置成功!");
        }else {
            System.out.println("非法输入!");
            return;
        }
        //输入棋盘长宽
        System.out.println("请输入街道的长n和宽m[1,9]：");
        int n = sc.nextInt();
        int m = sc.nextInt();
        if (n>=1 && n<=9 && m>=1 && m<=9){
            System.out.println("街道长n："+n+"，宽m："+m+"，设置成功!");
        }else {
            System.out.println("非法输入!");
            return;
        }
        //输入交通灯周期数组
        System.out.println("请输入n*m 个街口的交通灯周期T(=lights[row][col])[0,120]：");
        int[][] lights = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                lights[i][j] = sc.nextInt();
                if (lights[i][j]>=0&&lights[i][j]<=120){
                    System.out.println("位置["+i+"]["+j+"]处的交通灯时间T："+lights[i][j]+"，设置成功！");
                }else {
                    System.out.println("非法输入!");
                    return;
                }
            }
        }
        System.out.println("交通灯周期设置成功：");
        for (int[] row : lights) {
            System.out.println(Arrays.toString(row));
        }
        //输入起止街口的坐标
        System.out.println("请输入起点的行号rowStart：");
        int rowStart = sc.nextInt();
        System.out.println("请输入起点的列号colStart：");
        int colStart = sc.nextInt();
        System.out.println("请输入终点的行号rowEnd：");
        int rowEnd = sc.nextInt();
        System.out.println("请输入终点的列号colEnd：");
        int colEnd = sc.nextInt();

        //int time = calcTime(lights,timePerRoad,rowStart,colStart,rowEnd,colEnd);
        System.out.println("车辆经过两个街口的最短时间为："+calcTime(lights,timePerRoad,rowStart,colStart,rowEnd,colEnd));//从起点到终点的最短时间
    }
    public static int[][] DIRECTION = {
            {0,1},//上up0
            {1,0},//右right1
            {0,-1},//下down2
            {-1,0}//左left3
    };
    private static int calcTime(int[][] lights, int timePerRoad, int rowStart, int colStart, int rowEnd, int colEnd) {

        //----------------------------------
        int n = lights.length;
        int m = lights[0].length;
        //
        PriorityQueue<MoveSpot> pqMoveSpot = new PriorityQueue<>(
                Comparator.comparingInt(MoveSpot::getTimeSpend));
        MoveSpot start = new MoveSpot(rowStart,colStart,0,-1, MoveSpot.Move.NoMoveDirection);
        pqMoveSpot.add(start);

        //当优先队列不为空时，当前只存了start点
        MoveSpot startMoveSpot = pqMoveSpot.poll();
        //赋值
        int startx = startMoveSpot.position[0];//row
        int starty = startMoveSpot.position[1];//col
        int startTime = startMoveSpot.timeSpend;//时间
        int startfaceDir = startMoveSpot.FaceDirection;//面向
        MoveSpot.Move startmoveDir = startMoveSpot.direction;//转向
        //起点初始化，无面向无转向，面向可为可上下左右
        if (startfaceDir==-1&&startmoveDir== MoveSpot.Move.NoMoveDirection){
            for (int i=0;i<4;i++){
                //上
                if (i==0){
                    startfaceDir = 0;//将当前的面向改为上
                    //刚出发不考虑转向，随便设置，转向是后期根据面向改变进行判断的，可不设置
                    startmoveDir = MoveSpot.Move.GoStraight;
                    pqMoveSpot.offer(new MoveSpot(rowStart,colStart,0,startfaceDir, startmoveDir));
                }
                //右
                if (i==1){
                    startfaceDir = 1;//将当前的面向改为上
                    //刚出发不考虑转向，随便设置，转向是后期根据面向改变进行判断的，可不设置
                    startmoveDir = MoveSpot.Move.GoStraight;
                    pqMoveSpot.offer(new MoveSpot(rowStart,colStart,0,startfaceDir, startmoveDir));
                }
                //下
                if (i==2){
                    startfaceDir = 2;//将当前的面向改为上
                    //刚出发不考虑转向，随便设置，转向是后期根据面向改变进行判断的，可不设置
                    startmoveDir = MoveSpot.Move.GoStraight;
                    pqMoveSpot.offer(new MoveSpot(rowStart,colStart,0,startfaceDir, startmoveDir));
                }
                //左
                if (i==3){
                    startfaceDir = 3;//将当前的面向改为上
                    //刚出发不考虑转向，随便设置，转向是后期根据面向改变进行判断的，可不设置
                    startmoveDir = MoveSpot.Move.GoStraight;
                    pqMoveSpot.offer(new MoveSpot(rowStart,colStart,0,startfaceDir, startmoveDir));
                }
            }
        }
        /*
            for (int i=0;i<4;i++){
                System.out.println("看看初始化好没"+pqMoveSpot.poll());
                }
         */

        shortesPassTime=findMoveRoad(pqMoveSpot,rowEnd,colEnd,lights,timePerRoad);//=======================调用
       /* if (!pqMoveSpot.isEmpty()){
        ///    shortesPassTime=pqMoveSpot.peek().getTimeSpend();
        }*/
        //时间=路线经过格子数量*tPR+经过格子的灯周期累计（右转=0/直左=T） 算路线？根据累积量来？
        //起点终点时间不计算
        return shortesPassTime;
    }
    private static int findMoveRoad(PriorityQueue<MoveSpot> pqMoveSpot,int rowEnd,int colEnd,int[][] lights,int timePerRoad) {
        int n = lights.length;
        int m = lights[0].length;
        PriorityQueue<MoveSpot> newpqMoveSpot = new PriorityQueue<>(
                Comparator.comparingInt(MoveSpot::getTimeSpend));
        while (!pqMoveSpot.isEmpty()) {
            //从队列中拿出放入当前动点
            MoveSpot currentMoveSpot = pqMoveSpot.poll();
            //赋值
            int x = currentMoveSpot.position[0];//row
            int y = currentMoveSpot.position[1];//col
            int currentTime = currentMoveSpot.timeSpend;//时间
            int currentfaceDir = currentMoveSpot.FaceDirection;//面向
            MoveSpot.Move currentmoveDir = currentMoveSpot.direction;//转向

            //当到了终点就返回时间
            if (x == rowEnd && y == colEnd) {
                return currentTime;
            }

            //遍历四个方向
            for (int i = 0; i < 4; i++) {
                x = currentMoveSpot.position[0];//row
                y = currentMoveSpot.position[1];//col
                currentTime = currentMoveSpot.timeSpend;//时间
                currentfaceDir = currentMoveSpot.FaceDirection;//面向
                currentmoveDir = currentMoveSpot.direction;//转向
                //eg:i=0,取DIRECTION[0][0]与DIRECTION[0][1]即向上（x+0，y+1)
                int newX = x + DIRECTION[i][0];
                int newY = y + DIRECTION[i][1];
                //在边界内
                if (newX >= 0 && newX < n && newY >= 0 && newY < m) {
                    MoveSpot.Move movedirection = currentmoveDir;
                    if (currentfaceDir == i) {//面向一致
                        currentTime = currentMoveSpot.timeSpend;
                        movedirection = MoveSpot.Move.GoStraight;
                        currentTime = currentTime + lights[newX][newY] + timePerRoad;//直行时间为交通灯等待时间+行走时间
                        currentfaceDir = i;
                    } else {//面向不一致
                        if (Math.abs(currentfaceDir - i) == 2) {//若需要转两次
                            continue;
                        } else {
                            //01 12 23 30右转
                            if (i - currentfaceDir == 1 || (currentfaceDir == 3 && i == 0)) {
                                currentTime = currentMoveSpot.timeSpend;
                                movedirection = MoveSpot.Move.TurnRight;
                                currentTime = currentTime + timePerRoad;//右转时间为行走时间
                                currentfaceDir = i;
                            }
                            //03 10 21 32左转
                            else if (currentfaceDir - i == 1 || (currentfaceDir == 0 && i == 3)) {
                                currentTime = currentMoveSpot.timeSpend;
                                movedirection = MoveSpot.Move.TurnLeft;
                                currentTime = currentTime + lights[newX][newY] + timePerRoad;//左转时间为交通灯等待时间+行走时间
                                currentfaceDir = i;
                            }
                        }
                    }
                    //加入队列
                    MoveSpot newSpot = new MoveSpot(newX, newY, currentTime, currentfaceDir, movedirection);
                    newSpot.position[0] = newX;
                    newSpot.position[1] = newY;
                    newSpot.timeSpend = currentTime;
                    newSpot.direction = movedirection;
                    newSpot.FaceDirection = currentfaceDir;
                    newpqMoveSpot.offer(newSpot);
                }//边界内判断
            }//四方向循环
        }
        /*
        for (int i = 0; i < 10; i++) {
            System.out.println("newpq检查："+newpqMoveSpot.poll());
        }
         */
        shorttime = findMoveRoad(newpqMoveSpot, rowEnd, colEnd, lights, timePerRoad);
        return shorttime;
    }
    //定义一个动点类
    public static class MoveSpot {
        private int[] position = new int[2];//位置 {row，col}
        private int timeSpend;//用时
        private int FaceDirection;//面向
        private Move direction;//转向

        public class FaceDirection{
            public static final int NULL = -1;
            public static final int UP = 0;
            public static final int DOWN = 1;
            public static final int LEFT = 2;
            public static final int RIGHT = 3;
        }
        //转向枚举
        public enum Move {
            NoMoveDirection,GoStraight,TurnLeft,TurnRight;
        }

        //构造函数
        public MoveSpot(int row , int col , int timeSpend, int faceDirection,Move direction) {
            this.position[0] = row;//位置行列
            this.position[1] = col;
            this.timeSpend = timeSpend;
            this.FaceDirection = faceDirection;
            this.direction = direction;
        }

        //方法
        //位置
        public int[] getPosition() {
            return position;
        }
        public void setPosition(int row, int col) {
            this.position[0] = row;
            this.position[1] = col;
        }
        //时间
        public int getTimeSpend() {
            return timeSpend;
        }
        public void setTimeSpend(int timeSpend) {
            this.timeSpend = timeSpend;
        }
        //面向
        public int getFaceDirection() {
            return FaceDirection;
        }
        public void setFaceDirection(int faceDirection) {
            FaceDirection = faceDirection;
        }
        //转向
        public Move getDirection() {
            return direction;
        }
        public void setDirection(Move direction) {
            this.direction = direction;
        }
        //输出
        public String toString(){
            return "汽车"+ "到达["+position[0]+","
                    +position[1]+"]处"
                    +",用时为"+timeSpend
                    +",当前面向为："+FaceDirection
                    +",当前行动状态为"+direction+"。";
        }

    }
}
