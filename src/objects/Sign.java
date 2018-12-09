package objects;

public class Sign {

	private double speedLimit;
	private Double position;
	
	public Sign(double speedLimit, double d) {
		this.speedLimit=speedLimit;
		this.position=d;
	}

	public double getPos() {
		return position;
	}

	public double getSpeedLimit() {
		return speedLimit;
	}
	
}
