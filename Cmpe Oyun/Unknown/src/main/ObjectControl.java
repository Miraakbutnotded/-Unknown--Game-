package main;

import object.GuardianEye; //Guardian eye zaten generalObjectin child classý bu nedenle bunu import etmemiz yetti.
import object.Flames; //Flames eye zaten generalObjectin child classý bu nedenle bunu import etmemiz yetti.

public class ObjectControl {
//Bu class objelerin haritaya yerleþtirilmesi için.
	
	GamePanel gp;
	
	public ObjectControl(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.allObjs[0] = new Flames(23,32);
		gp.allObjs[1] = new Flames(15,19);
		gp.allObjs[2] = new Flames(4,38);
		gp.allObjs[3] = new GuardianEye(4,42);
		gp.allObjs[4] = new GuardianEye(11,19);
		gp.allObjs[5] = new GuardianEye(19,27);
		
		
	}
}
