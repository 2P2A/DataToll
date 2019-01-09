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

import objects.Car;
import objects.Road;

public class Runner {
	
	private double time = 0;
	private double deltaT;

	public Runner(Road road, double deltaT) throws IOException {
		initData(road);
		this.deltaT=deltaT;
		int nbre_voit=road.getCars().size();
		while(road.getCars().get(nbre_voit-1).getPos()<=road.getLenght())	{
			for(int i=0;i<road.getCars().size();i++) {
				Car c = road.getCars().get(i);
				if(c.getPos()<road.getLenght()) {
					recupData(c,i);
					c.maj(road,deltaT);
				}
			}
			time+=deltaT;
		}
		
	}

	private void initData(Road road) throws IOException {
		PrintWriter writer = new PrintWriter("param.txt", "UTF-8");
		writer.println(""+road.getCars().size());
		writer.close();
		
		PrintWriter writers[] = new PrintWriter[10];
		for(int i=0;i<road.getCars().size();i++) {
			writers[0] = new PrintWriter("data"+i+".txt", "UTF-8");
			writer.println("");
			writer.close();
		}
	}

	private void recupData(Car c, int i) throws IOException {
		List<String> lignes = Arrays.asList("v"+i+":"+time+":"+c.getPos()+":"+c.getSpeed()+":"+c.getAccel());
		Path fichier = Paths.get("data"+i+".txt");
		Files.write(fichier, lignes, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
	}

}
