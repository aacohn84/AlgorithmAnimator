/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmanimator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Foxtrot <aeseligman@gmail.com>
 */
public abstract class Algorithm 
{
    //Fields
    protected Node[] arr;
    protected Graphics g;
    
    //Double buffer
    protected Image buffer = new BufferedImage(AlgorithmAnimator.width, AlgorithmAnimator.height, BufferedImage.TYPE_INT_ARGB);
    protected Graphics canvas = buffer.getGraphics();
    
    public Algorithm(Graphics g)
    {
        this.g = g;
    }
    
    protected void render()
    {
        animate();   
        
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void clearCanvas()
    {
        canvas.setColor(Color.GRAY);
        canvas.fillRect(0, 0, AlgorithmAnimator.width, AlgorithmAnimator.height);
    }
    
    protected void commitCanvas()
    {
        g.drawImage(buffer, 0, 0, null);
    }
    
    protected void pause(long millis)
    {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            Logger.getLogger(Algorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void scramble()
    {
        arr = new Node[15];
        for(int i = 0; i < arr.length; i++)
        {
            Node n = new Node((int)(Math.random()*100), i);
            arr[i] = n;
        }
    }
    
    public abstract void sort();
    protected abstract void animate();
}
