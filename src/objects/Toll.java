package objects;

public class Toll {

	private double speedLimitation;
	private double position;
	private String style;
	
	public Toll(double d, String style) {
		this.position=d;
		if(style.equalsIgnoreCase("toll")) {
			this.speedLimitation=0;
			this.style="toll";
		}
			
		else if(style.equalsIgnoreCase("teletoll")) {
			this.speedLimitation=0;
			this.style="teletoll";
		}
			
		else if(style.equalsIgnoreCase("fastteletoll")) {
			this.speedLimitation=8.333;
			this.style="fastteletoll";
		}
			
	}

	public Double getPos() {
		return position;
	}

	public double getSpeedLimit() {
		return speedLimitation;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}
