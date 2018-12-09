package ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import objects.Car;
import objects.Road;
import objects.Sign;
import objects.Toll;

public class Controller {

	private double deltaT;

	public Controller(double deltaT) throws IOException {
		this.deltaT=deltaT;
	    String distance = JOptionPane.showInputDialog("Entrez une distance", "entre 1000 et 10000");
	    String[] tollsTypes = {"toll", "teleToll", "fastTeleToll"};
	    String tollType = (String)JOptionPane.showInputDialog(null, 
	      "Choisissez le type de péage",
	      "What kind of",
	      JOptionPane.QUESTION_MESSAGE,
	      null,
	      tollsTypes,
	      tollsTypes[0]);
	    String nbreToll = JOptionPane.showInputDialog("Entrez un nombre de péage", "1");
	    String nbreCar = JOptionPane.showInputDialog("Entrez un nombre de voitures", "1");
	    String[] kindOfDrivers = {"cool", "normal", "fredericJallah"};
	    String typeConducteur = (String)JOptionPane.showInputDialog(null, 
	  	      "Choisissez le type de conducteur",
		      "What kind of",
		      JOptionPane.QUESTION_MESSAGE,
		      null,
		      kindOfDrivers,
		      kindOfDrivers[1]);
	    Map<Integer, Car> cars = carGenerator(Integer.parseInt(nbreCar), typeConducteur);
	    Map<Integer, Toll> tolls = tollGenerator(Integer.parseInt(nbreToll), tollType, Double.parseDouble(distance));
	    Map<Integer, Sign> signs = signGenerator(tolls);
	    Road road = new Road(Double.parseDouble(distance), signs, cars, tolls);
	    Runner runner = new Runner(road, deltaT);
	}
	
	public Map<Integer, Car> carGenerator(int nbreCar, String style) {
		Map<Integer, Car> cars = new HashMap<>();
		for(int i=0;i<nbreCar;i++)
			cars.put(i, new Car(style, deltaT));
		System.out.println(""+cars);
		return cars;
	}
	
	public Map<Integer, Toll> tollGenerator(int nbreToll, String style, Double lenght) {
		Map<Integer, Toll> tolls = new TreeMap<>();
		for(int i=1;i<=nbreToll;i++)
			tolls.put(i-1, new Toll((lenght/(i+1))*i,style));
		System.out.println(""+tolls.get(0).getSpeedLimit()+" - "+tolls.get(0).getPos());
		return tolls;
	}
	
	public TreeMap<Integer, Sign> signGenerator(Map<Integer, Toll> tolls) {
		int signsPosDesc[] = {-500, -400, -300, -200, -100};
		int signsPosAccl[] = {100,200,300};
		TreeMap<Integer, Sign> signs = new TreeMap<>();
		int j=0;
		for(int i=0;i<tolls.size();i++) {
			double limit = 36.111;
			for(int dist:signsPosDesc) {
				signs.put(j, new Sign(limit, tolls.get(i).getPos()+dist));
				limit-=5.556;
				j++;
			}
			limit = 25;
			for(int dist:signsPosAccl) {
				signs.put(j, new Sign(limit, tolls.get(i).getPos()+dist));
				limit+=5.556;
				j++;
			}
		}
		/*for(int i = 0; i<8;i++)
			System.out.println(""+signs.get(i)+" - "+signs.get(i).getPos()+" - "+signs.get(i).getSpeedLimit());
		*/
		return signs;
	}
}
