package thi.irobcon.saphira.behaviour;

import thi.irobcon.saphira.desire.DesTransVel;
import thi.irobcon.saphira.reactive.Behaviour;

public class BehLimFor extends Behaviour {

	protected static final double DEFAULT_WIDTH_RATIO = 2;
	
	protected int stopDist;
	protected int slowDist;
	protected int slowSpeed;
	protected double widthRatio;
	
public BehLimFor(String actionName, int stopDist, int slowDist, int slowSpeed) {
	super(actionName);
	this.stopDist = stopDist;
	this.slowDist = slowDist;
	this.slowSpeed = slowSpeed;
	widthRatio = DEFAULT_WIDTH_RATIO;
}

public BehLimFor(String actionName, int stopDist, int slowDist, int slowSpeed, double widthRation) {
	super(actionName);
	this.stopDist = stopDist;
	this.slowDist = slowDist;
	this.slowSpeed = slowSpeed;
	this.widthRatio = widthRation;
}

public void fire() {
	double dist;
	double checkDist;

	if (stopDist > slowDist) checkDist = stopDist;
	else checkDist = slowDist;

	dist = robot.checkBox(0, 
			-robot.getRadius() * widthRatio,
	  		checkDist + robot.getRadius(),
			robot.getRadius() * widthRatio, 
			null);
	 
    System.out.println("Distance to obstacle in box is " + dist);
	dist -= robot.getRadius();
	
	if (dist < stopDist) {
		if (robot.getTransVel() >= 0) {
			addDesire(new DesTransVel(0, 1.0));
		    System.out.println("Limitting velocity to 0");
		}
		else {
			addDesire(new DesTransVel(0, 0.8));			
		}
	}
	else if (dist > slowDist) return;
	else {			
		double vel = slowSpeed * ((dist - stopDist) / (slowDist - stopDist));
		addDesire(new DesTransVel(vel, 0.8));
	    // System.out.println("Limitting velocity to " + vel);
	}
}
}

