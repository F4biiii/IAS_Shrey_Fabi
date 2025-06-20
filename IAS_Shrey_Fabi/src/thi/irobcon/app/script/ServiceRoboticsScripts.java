package thi.irobcon.app.script;

import thi.irobcon.ecar.ECarDefines;
import thi.irobcon.script.IRobConScript;

public class ServiceRoboticsScripts extends IRobConScript {

	public void script() {
//		autp();
//		patrol();
//	    slalom1();
//		slalom2();
//		clean();
		getFromStorage();
	}

	protected void autp() {
		
		//useRobot(ECarDefines.JSIM_DX1);
		useRobot(ECarDefines.DX4);
		turn(180);
	}

	protected void patrol() {
		
		useRobot(ECarDefines.JSIM_DX1);
//		useRobot(ECarDefines.DX4);
		for (int i=0; i < 10; i++) {
			move(1000);
			if (i%2==0) gripClose();
			else gripOpen();
			turn(180);
		}
	}

	protected void slalom1() {
		
		useRobot(ECarDefines.JSIM_DX1);
//		useRobot(ECarDefines.DX4);
		setRobPose(-2000,0,0);
		
		turn(-35);	// turn 14 deg clockwise
		move(1300);	// move straight for 2.06m
		turn(70);
		move(1500);
		turn(-70);
		move(1500);
		turn(70);
		move(1500);
		

	}
		
	protected void slalom2() {
		
//		useRobot(ECarDefines.JSIM_DX1);
		useRobot(ECarDefines.DX4);
		setRobPose(-2000,0,0);
		turn(-90);
		speed(200);
		rotate(16);
		wait(114);
		rotate(-16);
		wait(114);
		rotate(16);
		
	}
	
	protected void clean() {
		
//		useRobot(ECarDefines.JSIM_DX1);
		useRobot(ECarDefines.DX4);
		setRobPose(0,2000,90);
		liftUp();
		gripOpen();
		wait(5);
		move(50);
		wait(5);
		gripClose();
		wait(5);
		
		int count = 0;
		while(true) {
		speed(200);
			if(getSonarRange(3) <= 600 && getSonarRange(4) <= 600) {
				speed(0);
				if(count % 2 == 0) {
					turn(90);
					move(600);
					turn(90);
				} else {
					turn(-90);
					move(600);
					turn(-90);
				}
				count++;
			}
		}
	}
	
	protected void getFromStorage() {
		//useRobot(ECarDefines.JSIM_DX1);
		useRobot(ECarDefines.DX4);
		
		// Abstand zum Objekt
		int distObj = (getSonarRange(3) + getSonarRange(4)) / 2;
		panTilt(0,0);
		gripOpen();
		
		// Ausrichten zum Objekt (korrigieren)
		if(getBlobX(1) < 160) {
			rotate(10);
		} else {
			rotate(-10);
		}
		while (getBlobX(1) < 159 && getBlobX(1) > 161) {}
		rotate(0);
		wait(5);
		
		// Hinfahren zm Objekt
		liftDown();
		move((distObj-200));
	
		//Objekt nehmen
		wait(5);
		gripClose();
		wait(5);
		liftUp();
		
		// Zur�ck zum Anfang mit Objekt
		speed(-200);
		while(((getSonarRange(15) + getSonarRange(0)) / 2) < 1500) { }
		speed(0);
		wait(5);
		move(-80);
		
		// zum Ziel ausrichten, Kamera nach rechts
		turn(90);
		panTilt(-90,-10);
		
		// fahren bis Blob erkennen
		speed(200);
		while (getBlobX(3) < 160) { }
		speed(0);
		wait(5);
		
		// Zum Blob ausrichten
		turn(-90);
		panTilt(0, -10);
		wait(5);
		
		// Zum Blob ausrichten (korrigieren)
		if(getBlobX(3) < 160) {
			rotate(10);
		} else {
			rotate(-10);
		}
		while (getBlobX(3) < 159 && getBlobX(3) > 161) {}
		rotate(0);
		wait(5);
		
		// Zur Wand fahren
		distObj = (getSonarRange(2) + getSonarRange(5)) / 2;
		move(distObj-700);
		wait(5);
		
		// Objekt abstellen
		liftDown();
		wait(5);
		gripOpen();
		wait(5);
		
		// Zur�ckfahren
		move(-500);
	}
}
