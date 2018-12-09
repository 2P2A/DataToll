package objects;

import java.util.Map;
import java.util.TreeMap;

public class Road {

	private double lenght;
	
	private Map<Integer, Sign> signTab;
	private Map<Integer, Car> carTab;
	private Map<Integer, Toll> tollTab;
	
	public Road(Double lenght, Map<Integer, Sign> signs, Map<Integer, Car> carTab, Map<Integer, Toll> tolls) {
		this.lenght=lenght;
		this.signTab=signs;
		this.tollTab=tolls;
		this.carTab=carTab;
	}

	public Map<Integer, Car> getCars() {
		return carTab;
	}

	public double getLenght() {
		return lenght;
	}

	public Map<Integer, Sign> getSigns() {
		return signTab;
	}

	public Map<Integer, Toll> getToll() {
		return tollTab;
	}
	
	
}
