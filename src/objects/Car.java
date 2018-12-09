package objects;

public class Car {

	private double position;
	private double speed;
	private double accel;
	
	private double maxSpeed;
	private double maxAccel;
	private int driveStyle;
	private int visibility;
	private double timeToPay;
	
	public Car(double pos, double speed, double accel, String driveStyle, double deltaT) {
		this.position=pos;
		this.speed=speed;
		this.accel=accel;
		if(driveStyle.equals("cool")) {
			this.maxAccel=10;
			this.maxSpeed=33.333;
			this.visibility=150;
			this.driveStyle=-1;
			this.timeToPay=12/deltaT;
		}
		else if(driveStyle.equals("fred")) {
			this.maxAccel=100;
			this.maxSpeed=55.556;
			this.visibility=50;
			this.driveStyle=1;
			this.timeToPay=3/deltaT;
		}
		else {
			this.maxAccel=20;
			this.maxSpeed=36.111;
			this.visibility=100;
			this.driveStyle=0;
			this.timeToPay=6/deltaT;
		}
	}

	public Car(String style, double deltaT) {
		this(0.0, 36.111, 0.0, style, deltaT);
	}

	public double getPos() {
		return position;
	}

	public void maj(Road road, double deltaT) {
		
		for(int i=0; i<road.getSigns().size();i++) {
			if(road.getSigns().get(i).getPos()>this.getPos() && road.getSigns().get(i).getPos()<this.getPos()+this.visibility) {
				adaptAccel(road.getSigns().get(i));
			}
		}
		for(int i=0; i<road.getToll().size();i++) {
			if(road.getToll().get(i).getPos()>this.getPos() && road.getToll().get(i).getPos()<this.getPos()+this.visibility) {
				adaptAccel(road.getToll().get(i));
			}
		}
		for(int i=0; i<road.getCars().size();i++) {
			if(road.getCars().get(i).getPos()>this.getPos() && road.getCars().get(i).getPos()<this.getPos()+50.0) {
				adaptAccel(road.getCars().get(i));
			}
		}
		
		// ancienne pos modif speed ici
		
		
		if(this.speed>this.maxSpeed) {
			this.speed=this.maxSpeed;
			this.accel=0;
		}
		if(this.accel>this.maxAccel)
			this.accel=maxAccel;
		
		if(this.speed<0)
			this.speed=0;
		
		if(this.speed<=0.1) {
			System.out.println("test");
			for(int i=0; i<road.getToll().size();i++) {
				if((Math.abs(road.getToll().get(i).getPos()-this.getPos())<5)
						&& road.getToll().get(i).getStyle().equalsIgnoreCase("toll")
						&& timeToPay>0) {
					System.out.println("test if");
					this.timeToPay--;
					this.accel=0;
					this.speed=0;
					System.out.println("ttp:"+timeToPay);
				}
				else {
					System.out.println("test else");
					if(this.driveStyle==-1) 
						this.timeToPay=12;
					else if(this.driveStyle==0)
						this.timeToPay=5;
					else
						this.timeToPay=2;
				}
			}
		}
		
		this.position += (deltaT*(this.speed)); // ICI DEPLACER LA MODIF DE SPEED
		this.speed += this.accel*deltaT;
	}

	private void adaptAccel(Car car) {
		this.accel=((( car.getSpeedLimit()*car.getSpeedLimit()) - (this.speed*this.speed))
					/(2*(car.getPos()-this.position) )) ;
	}

	private double getSpeedLimit() {
		return 0;
	}

	private void adaptAccel(Toll toll) {
		this.accel=((( toll.getSpeedLimit()*toll.getSpeedLimit()) - (this.speed*this.speed))
					/(2*(toll.getPos()-this.position) ) );
	}

	private void adaptAccel(Sign sign) {
		this.accel=(((sign.getSpeedLimit()*sign.getSpeedLimit()) - (this.speed*this.speed))
							/ (2*(sign.getPos()-this.position) ) );// /1000 si milisec, ici /1 pour 1sec
		System.out.println("sign:"+sign.getSpeedLimit()+" // speed:"+this.speed+" // posSign:"+sign.getPos()+" // ma pos:"+this.getPos()+" // accel"+this.accel);
	}

	public double getSpeed() {
		return speed;
	}
	public double getAccel() {
		return accel;
	}

}
