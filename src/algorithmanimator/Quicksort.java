/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmanimator;

import java.awt.Graphics;

/**
 *
 * @author Foxtrot <aeseligman@gmail.com>
 */
public class Quicksort extends Algorithm
{
    public Quicksort(Graphics g)
    {
        super(g);
    }

    @Override
    public void sort()
    {
        render();
        print();
        quicksort(0, arr.length-1);
    }
    
    private void quicksort(int low, int high)
    {
        if(low >= high)
            return;
        
        int pivot = (low + high)/2;
        int pivotValue = arr[pivot].value;
        
        System.out.println(low + ":" + pivot + ":" + high);
        
        swap(pivot, high);  pivot = high;
        int cursor = low;
        
        for(int i = low; i < pivot; i++)
        {
            if(arr[i].value < pivotValue)
            {
                swap(i, cursor);
                cursor++;
            }
        }
        
        swap(pivot, cursor);
        
        render();
        print();
        
        quicksort(low, cursor-1);
        quicksort(cursor+1, high);
    }
    
    private void print()
    {
        for(Node n : arr)
            System.out.println(n.value);
        System.out.println();
    }
    
    private void swap(int i1, int i2)
    {
        Node temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
        
        System.out.println(arr[i1].value + " was at index " + arr[i1].index);
        System.out.println(arr[i2].value + " was at index " + arr[i2].index);
        arr[i1].index = i1;
        arr[i2].index = i2;
        System.out.println("Now " + arr[i1].value + " is at index " + arr[i1].index);
        System.out.println("Now " + arr[i2].value + " is at index " + arr[i2].index);
        System.out.println("Swapped " + i1 + " and " + i2);
    }

    @Override
    protected void animate() 
    {
        float nodeSpacing = AlgorithmAnimator.width/(float)arr.length;
        
        float[] dists = new float[arr.length];
        for(int i = 0; i < arr.length; i++)
        {
            Node n = arr[i];
            int targetX = (int) (n.index*nodeSpacing);
//            dists[i] 
            //TODO: store all dists so we know how far to step each node each time
            //actually just store the step distances
        }
        
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

    
}
