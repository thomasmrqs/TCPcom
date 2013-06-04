package graphique;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import java.awt.Color;

public class DegradPanel extends JPanel
{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DegradPanel() 
	{
	        super();
	}

	    public void paint(Graphics graph) 
	    {
	        super.paintComponent(graph);
	        Graphics2D g = (Graphics2D) graph;

	        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        Rectangle bounds = getBounds();
	        Paint gradientPaint = new GradientPaint(0, bounds.y, Color.DARK_GRAY, 0, bounds.y + bounds.width, Color.WHITE);
	        g.setPaint(gradientPaint);
	        g.fillRect(0, 0, bounds.width, bounds.height);
	    }
}
