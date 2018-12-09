package gui;

import java.util.ArrayList;

import javax.swing.JFrame;

public class MyFrame extends JFrame{

	public MyFrame(double[][] data) {
		this.setTitle("Graphe");
		this.setSize(1200, 700);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(new MyPane(data));
		setVisible(true);
		
		
		
	}
}
