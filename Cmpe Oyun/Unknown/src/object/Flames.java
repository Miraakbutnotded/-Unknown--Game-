package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Flames extends GeneralObject{
	
	public Flames(int x, int y) {
		name = "Flames";
		this.worldX =  x * 48;
		this.worldY =  y * 48;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Alp_pArt_Flames.png"));
			
		}catch (IOException e ){
			e.printStackTrace();
		}
	}

}
