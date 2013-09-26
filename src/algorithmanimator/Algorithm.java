/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmanimator;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

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
    
    public abstract void sort();
    protected abstract void animate();
}
