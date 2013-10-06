/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmanimator;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Foxtrot <aeseligman@gmail.com>
 */
public class Bubblesort extends Algorithm
{    
    private int comparator;
    private int action;
    
    public Bubblesort(Graphics g)
    {
        super(g);
    }

    @Override
    public void sort()
    {
        for (int i = arr.length; i > 0; i--){
            for (int j = 0; j < i-1; j++){
                comparator = j;
                if (arr[j].value > arr[j+1].value){
                    swap(j, j+1);
                    action = 1;
                }
                else if (arr[j].value < arr[j+1].value){
                    action = -1;
                }
                else
                {
                    action = 0;
                }
                render();
            }
        }
    }
    
    private void swap(int a, int b){
        Node n = arr[a];
        arr[a] = arr[b];
        arr[b] = n;
        
        arr[a].index = a;
        arr[b].index = b;
    }
    
    private void highlight(){
        arr[comparator].txt = Color.BLACK;
        arr[comparator].bgd = Color.YELLOW;
        arr[comparator+1].txt = Color.BLACK;
        arr[comparator+1].bgd = Color.YELLOW;
    }
    
    private void showDiff(int a, int b){
        arr[a].bgd = Color.GREEN;
        arr[a].txt = Color.BLACK;
        arr[b].bgd = Color.RED;
        arr[b].txt = Color.WHITE;
    }
    
    private void showSame(){
        arr[comparator].txt = Color.WHITE;
        arr[comparator].bgd = Color.ORANGE;
        arr[comparator+1].txt = Color.WHITE;
        arr[comparator+1].bgd = Color.ORANGE;
    }
    
    private void reset(){
        arr[comparator].txt = Color.WHITE;
        arr[comparator].bgd = Color.BLUE;
        arr[comparator+1].txt = Color.WHITE;
        arr[comparator+1].bgd = Color.BLUE;
    }

    @Override
    protected void animate() 
    {
        float nodeSpacing = AlgorithmAnimator.width/(float)arr.length;
        
        highlight();        
        clearCanvas();
        for (Node n : arr){
            n.render(canvas);
        }
        commitCanvas();
        
        pause(800);
        
        if (action != 0){
            showDiff(comparator + 1, comparator);
        }
        else
        {
            showSame();
        }
        
        for (int i = 0; i < 10; i++){
            clearCanvas();
            for (Node n : arr){
                float targetX = n.index * nodeSpacing;
                float step = nodeSpacing / 10;
                if (targetX < n.x){
                    step *= -1;
                }
                else if (targetX == n.x){
                    step = 0;
                }
                n.x += step;
                n.render(canvas);
            }
            commitCanvas();
            pause(50);
        }
        
        reset();
        
        clearCanvas();
        
        for (Node n : arr){
            int targetX = (int) (n.index*nodeSpacing);
            n.x = targetX;
            n.render(canvas);
        }
        commitCanvas();
    }   
}
