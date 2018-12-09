package gui;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.sql.DatabaseMetaData;

import javax.swing.JPanel;

public class MyPane extends JPanel{

	private double[][] data;

	public MyPane(double[][] data2) {
		this.data=data2;
	}
	
	public void paint(Graphics g) {
		for(int i=0;i<data.length;i++) {
			g.drawRect((int)data[i][1]*10, (int)data[i][2]/2
					, 1, 1);
			System.out.println(i+"-"+data[i][1]+"-"+data[i][2]);
			//Point2D.Double point = new Point2D.Double(d[1], d[2]);
		}
		
		
			
			
	}
}
