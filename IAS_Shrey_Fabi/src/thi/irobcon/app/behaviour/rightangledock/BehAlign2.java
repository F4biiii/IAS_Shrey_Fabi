package thi.irobcon.app.behaviour.rightangledock;

import thi.irobcon.saphira.lps.Pose;
import thi.irobcon.saphira.reactive.Behaviour;

public class BehAlign2 extends Behaviour {

	protected int stopDistance;
	protected int rotVel;
	
public BehAlign2(String behName, int stopDistance, int rotVel) {
	super(behName);
	this.stopDistance = stopDistance;
	this.rotVel = rotVel;
}

public void fire() {
	int frontDist;
	Pose relObstaclePose = new Pose();
	
	// jetzt drehen 90 grad nach links, ka wie
	
	
	// dann wieder wie Align1, aber Box so, dass es egal ist, dass wir an der ersten Wand schon nah dran sind
	
	


	// das noch ändern, koords stimme nicht
	frontDist = (int)  (robot.checkBox(2500, 500, 100, 100, relObstaclePose) - robot.getRadius());

}
}

