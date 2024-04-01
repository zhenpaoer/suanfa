package src.suanfa.sort;

public class QuickSort {

    private void proccess(int[] arr,int L,int R){
        if (L >= R){
            return;
        }
        swap(arr,L+(int)Math.random()*(R-L+1),R);
        int[] sort = sort(arr,L,R);
        proccess(arr,L,sort[0]-1);
        proccess(arr,sort[1]+1,R);
    }

    private int[] sort(int[] arr,int L, int R){
        if (L > R){
            return new int[]{-1,-1};
        }
        if (L == R){
            return new int[]{L,R};
        }
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more){
            if (arr[index] == arr[R]){
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr,index++,++less);
            }else {
                swap(arr,index,--more);
            }
        }
        swap(arr,more,R);
        return new int[]{less+1,more};
    }

    private void swap(int[] arr,int l,int r){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
