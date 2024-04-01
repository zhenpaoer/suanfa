package src.suanfa.sort;

/**
 * 堆排序
 */
public class HeapSort {


    private void proccess(int[] arr){
        if (arr == null || arr.length < 2)
            return;
        // O(N)
        for (int i = arr.length - 1; i >= 0 ; i--) {
            heapify(arr,i,arr.length);
        }
        // O(N) = ½N + ¼N + 1/8N
        // 2 O(N) = N + ½N + ¼N
        //2 O(N) - O(N) =


        int heapSize = arr.length;
        heapify(arr,0,--heapSize);
        // O(N*logN)
        while (heapSize > 0){// O(N)
            heapify(arr,0,heapSize);// O(logN)
            swap(arr,0,--heapSize);
        }
    }


    private void heapify(int[] arr,int index,int heapSize){
        int left = index * 2 + 1;
        while (left < heapSize){
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr,largest,index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr,int l,int r){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
