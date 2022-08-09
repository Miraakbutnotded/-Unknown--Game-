package forPlayer;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tools {
	public int worldX, worldY;
	public int speedOfCharacter;//Hata verebilir o yüzden geri dön ve baþka hýzlar dene. 
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public boolean collisionState  = false;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2; //Resimlerin depolanmasýnda kullanýlýr BufferedImage
	public String direction;
	public Rectangle solidArea;
	public int solidAreaDefaultX , solidAreaDefaultY;

}
