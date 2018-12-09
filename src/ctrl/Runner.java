package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gui.MyFrame;
import objects.Car;
import objects.Road;

public class Runner {
	
	private double time = 0;
	private double[][] data = new double[1000][5];
	private double deltaT;

	public Runner(Road road, double deltaT) throws IOException {
		initData(road);
		this.deltaT=deltaT;
		int cpt=0;
		while(road.getCars().get(0).getPos()<=road.getLenght())	{
			for(int i=0;i<road.getCars().size();i++) {
				Car c = road.getCars().get(i);
				recupData(c,i);
				c.maj(road,deltaT);
				data[cpt][0]=i;
				data[cpt][1]=time;
				data[cpt][2]=c.getPos();
				cpt++;
			}
			time+=deltaT;
		}
		MyFrame frame =new MyFrame(data);
		
		
	}

	private void initData(Road road) throws IOException {
		PrintWriter writer = new PrintWriter("param.txt", "UTF-8");
		writer.println(""+road.getCars().size());
		writer.close();
		
		List<String> lignes = Arrays.asList("");
		Path fichier = Paths.get("data.txt");
		Files.write(fichier, lignes, Charset.forName("UTF-8"));
	}

	private void recupData(Car c, int i) throws IOException {
		List<String> lignes = Arrays.asList(""+i+":"+time+":"+c.getPos()+":"+c.getSpeed()+":"+c.getAccel());
		Path fichier = Paths.get("data.txt");
		Files.write(fichier, lignes, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
	}

}
