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
    
    public Node(int value, int index)
    {
        this.value = value;
        this.index = index;
    }
    
    public void render(Graphics g)
    {
        int size = 50;
        
        g.setColor(Color.BLUE);
        g.fillRect((int)x, (int)y, size, size);
        
        //Paint value
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(value), (int)x + size/2, (int)y + size/2);
    }
}
