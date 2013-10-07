/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmanimator;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Aaron Cohn
 */
public class HeapSortAlgorithm extends Algorithm {

    private final int arrSize = 10;
    private final Color BGD_PARENT = Color.GREEN;
    private final Color TXT_PARENT = Color.BLACK;
    private final Color BGD_CHILD = Color.YELLOW;
    private final Color TXT_CHILD = Color.BLACK;
    private final Color BGD_LARGEST = Color.ORANGE;
    private final Color TXT_LARGEST = Color.WHITE;
    
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
        float nodeWidth = AlgorithmAnimator.width/(float)arr.length;
        
        // calculate targets for each node
        int target[] = new int[arr.length];
        float step[] = new float[arr.length];
        boolean animate = false;
        for (Node n : arr) {
            int targetX = (int) (n.index * nodeWidth);
            if (targetX == n.x) {
                target[n.index] = -1;
            } else {
                target[n.index] = targetX;
                step[n.index] = (targetX - n.x)/10f;
                animate = true;
            }
        }
        
        // if nodes haven't moved, don't do any animating
        if (!animate) {
            repaintNodes();
            return;
        }
        
        // animate the movement of nodes
        for(int i = 0; i < 10; i++) {
            clearCanvas();
            for(Node n : arr) {
                int targetX = target[n.index];
                if (targetX > -1) {
                    n.x += step[n.index];
                }
                n.render(canvas);
            }
            commitCanvas();
            pause(80);
        }
        
        // make sure the nodes end up in their destinations
        clearCanvas();
        for(Node n : arr)
        {
            if (target[n.index] > -1) {
                n.x = target[n.index];
            }
            n.render(canvas);
        }
        commitCanvas();
        pause(PAUSE_TIME);
    }
    
    private void repaintNodes() {
        clearCanvas();
        for (Node n : arr) {
            n.render(canvas);
        }
        commitCanvas();
        pause(PAUSE_TIME);
    }
    
    private void repaintNodes(boolean reset) {
        
        if (reset) {
            clearCanvas();
            for (Node n : arr) {
                n.bgd = Color.BLUE;
                n.txt = Color.WHITE;
                n.render(canvas);
            }
            commitCanvas();
            pause(PAUSE_TIME);
        } else {
            repaintNodes();
        }
    }
    
    private void renderNode(Node n) {
        n.render(canvas);
        commitCanvas();
    }
    
    private void highlightParent(int i) {
        arr[i-1].bgd = BGD_PARENT;
        arr[i-1].txt = TXT_PARENT;
        renderNode(arr[i-1]);
        pause(PAUSE_TIME);
    }
    
    private void highlightChildren(int l, int r) {
        if (l <= arr.length) {
            arr[l-1].bgd = BGD_CHILD;
            arr[l-1].txt = TXT_CHILD;
            renderNode(arr[l-1]);
            if (r <= arr.length) {
                arr[r-1].bgd = BGD_CHILD;
                arr[r-1].txt = TXT_CHILD;
                renderNode(arr[r-1]);
            }
        }
        pause(PAUSE_TIME);
    }
    
    private void highlightLargest(int largest) {
        arr[largest-1].bgd = BGD_LARGEST;
        arr[largest-1].txt = TXT_LARGEST;
        renderNode(arr[largest-1]);
        pause(PAUSE_TIME);
    }
    
    private void unhighlight(int nodeIndex) {
        arr[nodeIndex-1].bgd = Color.BLUE;
        arr[nodeIndex-1].txt = Color.WHITE;
        renderNode(arr[nodeIndex-1]);
        pause(PAUSE_TIME);
    }
    
    private void unhighlight(int n1Index, int n2Index) {
        arr[n1Index-1].bgd = Color.BLUE;
        arr[n1Index-1].txt = Color.WHITE;
        renderNode(arr[n1Index-1]);
        arr[n2Index-1].bgd = Color.BLUE;
        arr[n2Index-1].txt = Color.WHITE;
        renderNode(arr[n2Index-1]);
        pause(PAUSE_TIME);
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
    private final int PAUSE_TIME = 500;
    public void maxHeapify(Node[] a, int i) {
        int l = left(i), r = right(i), largest;
        highlightParent(i);
        highlightChildren(l, r);
        
        if (l <= heapSize && a[l-1].value > a[i-1].value) {
            largest = l;
        } else {
            largest = i;
        }
        highlightLargest(largest);
        
        if (r <= heapSize && a[r-1].value > a[largest-1].value) {
            unhighlight(largest);
            largest = r;
            highlightLargest(largest);
        }
        if (largest != i) {
            // animated exchange of nodes
            exchange(a, i, largest); render();
            repaintNodes(true); // reset node colors
            maxHeapify(a, largest);
        }
        repaintNodes(true);
    }

    private void exchange(Node a[], int i, int j) {
        Node temp = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = temp;
        a[i-1].index = i-1;
        a[j-1].index = j-1;
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
            highlightChildren(1, i);
            exchange(arr, 1, i); render(); pause(PAUSE_TIME);
            unhighlight(1, i);
            heapSize--;
            maxHeapify(arr, 1);
        }
        printArr();
    }   
}
