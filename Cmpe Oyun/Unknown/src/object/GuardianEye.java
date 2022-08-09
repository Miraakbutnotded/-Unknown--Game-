package object;

import java.io.IOException;


import javax.imageio.ImageIO;

public class GuardianEye extends GeneralObject{
	
	public GuardianEye(int x, int y) {
		name = "GuardianEye";
		this.worldX =  x * 48;
		this.worldY =  y * 48;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Alp_pArt_Eye3.png"));
			
		}catch (IOException e ){
			e.printStackTrace();
		}
		collision = true;
	}
	

}
