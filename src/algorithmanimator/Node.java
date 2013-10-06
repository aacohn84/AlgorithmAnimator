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
public class Node 
{
    //Fields
    public float x, y;
    public int value;
    public int index;
    public Color bgd = Color.BLUE;
    public Color txt = Color.WHITE;
    
    public Node(int value, int index)
    {
        this.value = value;
        this.index = index;
        x = AlgorithmAnimator.width * index/15f;
    }
    
    public void render(Graphics g)
    {
        int size = 50;
        
        g.setColor(bgd);
        g.fillRect((int)x, (int)y, size, size);
        
        //Paint value
        g.setColor(txt);
        g.drawString(String.valueOf(value), (int)x + size/2, (int)y + size/2);
    }
}
