package forPlayer;

import java.awt.Rectangle; //dikdörtgen oluþturmada kullanýlýr.
import java.awt.Color;
import java.io.IOException;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyControl;
import music.MusicTest;

public class Player extends Tools{
	int p = 3;
	GamePanel gp;
	KeyControl keyH;
	
	int flames = 0;
	public final int screenY;
	public int k;
	public final int screenX;
	
	
	int counter2 = 0;
	
	public Player(GamePanel gp, KeyControl keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = (gp.screenWidth - gp.tileSize) / 2; //karakteri ekranýn ortasýna yerleþtirdik.
		screenY = (gp.screenHeight - gp.tileSize) / 2;
		
		solidArea = new Rectangle(8, 16, 32, 32); //çakýþma için karakter içindeki alan
		solidArea.x = 8; //keyfi
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		setDefaultValues();
		getPlayerImage();
		direction = "down";
	}
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 26;  //karakterin oyuna baþlayacaðý yer
		worldY = gp.tileSize * 16;
		speedOfCharacter = 4;
		
	}
	
	public void getPlayerImage() {
		
			
			
			try { 
				up1 = ImageIO.read(getClass().getResourceAsStream("/player/Ýrem_pArt_up1.png"));
				up2 = ImageIO.read(getClass().getResourceAsStream("/player/Ýrem_pArt_up2.png"));
				down1 = ImageIO.read(getClass().getResourceAsStream("/player/Ýrem_pArt_down1.png"));
				down2 = ImageIO.read(getClass().getResourceAsStream("/player/Ýrem_pArt_down2.png"));
				left1 = ImageIO.read(getClass().getResourceAsStream("/player/Ýrem_pArt_left1.png"));
				left2 = ImageIO.read(getClass().getResourceAsStream("/player/Ýrem_pArt_left2.png"));
				right1 = ImageIO.read(getClass().getResourceAsStream("/player/Ýrem_pArt_right1.png"));
				right2 = ImageIO.read(getClass().getResourceAsStream("/player/Ýrem_pArt_right2.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}

	public void update() {
		
		if (keyH.downP == true || keyH.upP == true || keyH.leftP == true || keyH.rightP == true) { 
			
			if(keyH.upP == true) {
				direction = "up";
				
			}
			else if (keyH.downP == true) {
				direction = "down";
				
			}
			else if (keyH.leftP == true) {
				direction = "left";
				
			}
			else if (keyH.rightP == true) {
				direction = "right";
				
			}
			
			//çakýþmayý kontrol eder (tile'larýnkini)
			collisionState = false;
			gp.colChecker.checkCollision(this); //this player classý
			
			//objelerin çakýþmasýný kontrol eder
			int objIndex = gp.colChecker.checkObject(this, true);
			pickUpObject(objIndex);	//anahtar ve kapý için method çaðrýsý
			
			
			if (collisionState == false) { //collusion off olduðunda harekete izin verir
				switch (direction) {
				case "up":
					worldY -= speedOfCharacter;
					break;
				case "down":
					worldY += speedOfCharacter;
					break;
				case "left":
					worldX -= speedOfCharacter;
					break;
				case "right":
					worldX += speedOfCharacter;
					break;
			
				}
			}
			
			
			
			spriteCounter++; //bu method update methodunda saniyede 60 kez çaðýrýlýr bu nedenle pozisyon resimleri arasýda her 8 framede bir geçiþ olur
			if (spriteCounter > 8) {
				if (spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	
	public void pickUpObject(int i) {
		if (i != 999) {
			
			String objectName = gp.allObjs[i].name;
			
			switch (objectName) {
			case "Flames" :
				flames++;
				gp.allObjs[i] = null; //Alýnan objenin yeri null olur kendisi aslýnda yok olur ve yerine baþka bir obje element olarak geçebilir.
				if (k < 1) {
					System.out.println("Burn the eyes for passage!");
					
					k++;
				}
				System.out.println("Flames: " + flames);
				break;
			case "GuardianEye":
				if (flames > 0) {
					gp.allObjs[i] = null;
					if (gp.allObjs[i] == null) {
						p--;
						if (p == 0) {
							System.out.println("You discovered all of the house! You won!");
						}
					}
					flames--;
					System.out.println("Flames: " + flames);
					System.out.println("Guardian eye: " + p);
					
				}
				break;
			}
		}
	}
	public void draw(Graphics2D g2) {
		
		
		
	BufferedImage image = null;
		
		switch (direction) { //sýrayla resimlerin oynatýlýþý (yönlere baðlý olarak).
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2; 
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
		    break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}

}
