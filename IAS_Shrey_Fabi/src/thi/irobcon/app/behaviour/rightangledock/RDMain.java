package thi.irobcon.app.behaviour.rightangledock;

import thi.irobcon.ecar.ECarDefines;
import thi.irobcon.saphira.SaphiraRobot;
import thi.irobcon.saphira.behaviour.BehConstTransVel;
import thi.irobcon.saphira.behaviour.BehLimFor;
import thi.irobcon.saphira.lps.Pose;
import thi.irobcon.saphira.reactive.BehGroup;

public class RDMain {

	public static void main(java.lang.String[] args) {

		SaphiraRobot robot = new SaphiraRobot(ECarDefines.DX4);
		// SaphiraRobot robot = new SaphiraRobot(ECarDefines.JSIM_DX3);

//		robot.addLaser();
		robot.setRobPose(new Pose(-2000,-2000,20));
		
		BehGroup dock = new BehGroup("Dock");
		BehConstTransVel cv = new BehConstTransVel("ConstTransVel", 200);
		BehLimFor lf = new BehLimFor("LimFor", 400, 2000, 100); 
  	    BehAlign al = new BehAlign("Align", 30, 5);
  	    BehStop st = new BehStop("Stop", 800);
  	    BehAlign2 al2 = new BehAlign2("Align2", 1000, 5);
  	    BehWait wa = new BehWait("Wait", 1500);
  	    
		dock.add(lf, 80);
		dock.add(cv, 50);
		dock.add(al, 75);
		dock.add(st, 80);
		//dock.add(al2, 76);
		dock.add(wa, 90);
		robot.add(dock);
		
		robot.add(new RDStrategy(dock));
		
		robot.run();		  
	}
}
