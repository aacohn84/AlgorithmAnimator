/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmanimator;

import java.awt.Graphics;

/**
 *
 * @author Aaron Cohn
 */
public class HeapSortAlgorithm extends Algorithm {

    private final int arrSize = 10;
    
    public HeapSortAlgorithm(Graphics g) {
        super(g);
        //printArr();
    }
    
    @Override
    public void sort() {
        render();
        heapSort();
    }

    @Override
    protected void animate() {
        float nodeSpacing = AlgorithmAnimator.width/(float)arr.length;
        
        float[] dists = new float[arr.length];
        
        System.out.println(AlgorithmAnimator.width + ":" + arr.length);
        for(int i = 0; i < 10; i++)
        {
            clearCanvas();
            for(Node n : arr)
            {
                int targetX = (int) (n.index*nodeSpacing);
                float step = (targetX - n.x)/10f;
                n.x += step;
                n.render(canvas);
            }
            commitCanvas();
            pause(50);
        }
        clearCanvas();
        for(Node n : arr)
        {
            int targetX = (int) (n.index*nodeSpacing);
            n.x = targetX;
            n.render(canvas);
        }
        commitCanvas();
    }
    
    private void printArr() {
        for (int i = 0; i < arrSize; i++) {
            System.out.print(arr[i].value + " ");
        }
        System.out.println();
    }
    
    // HEAP OPERATIONS
    public int parent(int i) {
        return i/2;
    }
    
    public int left(int i) {
        return 2*i;
    }
    
    public int right(int i) {
        return 2*i + 1;
    }
    
    private int heapSize = 0;
    public void maxHeapify(Node[] a, int i) {
        int l = left(i), r = right(i), largest;
        if (l <= heapSize && a[l-1].value > a[i-1].value) {
            largest = l;
        } else {
            largest = i;
        }
        if (r <= heapSize && a[r-1].value > a[largest-1].value) {
            largest = r;
        }
        if (largest != i) {
            exchange(a, i, largest);
            maxHeapify(a, largest);
        }
    }

    private void exchange(Node a[], int i, int j) {
        Node temp = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = temp;
        a[i-1].index = i-1;
        a[j-1].index = j-1;
        render();
    }
    
    public void buildHeap() {
        heapSize = arr.length;
        for (int i = arr.length; i >=1; i--) {
            maxHeapify(arr, i);
        }
    }
    
    public void heapSort() {
        buildHeap();
        for (int i = arr.length; i >= 2; i--) {
            exchange(arr, 1, i);
            heapSize--;
            maxHeapify(arr, 1);
        }
        printArr();
    }   
}
