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
    int x = 0;
    
    public Bubblesort(Graphics g)
    {
        super(g);
    }

    @Override
    public void sort()
    {
        //do logic
        //call animate
        while(true)
        {
            x++;
            animate();
        }
    }

    @Override
    protected void animate() 
    {
        //manipulates the list of nodes
        //and each node's x, y values
        canvas.setColor(Color.yellow);
        canvas.fillRect(x, x++, 100, 100);
    }

    
}
