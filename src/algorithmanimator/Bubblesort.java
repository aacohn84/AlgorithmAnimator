/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmanimator;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        canvas.setColor(Color.GRAY);
        canvas.fillRect(0, 0, AlgorithmAnimator.getInstance().getWidth(), AlgorithmAnimator.getInstance().getHeight());
        canvas.setColor(Color.yellow);
        canvas.fillRect(x, x++, 100, 100);
        
        //Commit buffer
        g.drawImage(buffer, 0, 0, null);
        
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(Bubblesort.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
