package main;

import java.awt.Dimension; //Dimension bir obje içine uzunluk ve geniþlik girmemizi saðlar.
import main.KeyControl.*;

import javax.swing.JPanel; // Bileþenler, baðlayabileceðimiz alan oluþturmada kullanýlýr.

import forPlayer.Player;
import object.GeneralObject;
import tile.TileControl;

import java.awt.Color; //Renkleri (darkGray'i) buradan alýyoruz.

import java.awt.Graphics; //Ekran üstüne veya arkada çalýþan olsun, çizimler graphics ile yapýlýr

import java.awt.Graphics2D;//Graphicsden daha komplike iþlevleri var, geometri, koordinat, renk gibi iþlerden sorumlu.

public class GamePanel extends JPanel implements Runnable{//thread için runnable kullandýk. JPanel panel oluþumu içindir.
	//Ekran Ayarlarý
	 final int ogTileSize = 16; //16*16 tile
	public final int tileSize = ogTileSize * 3; //48*48 þimdilik 
	public final int maxScreenTileCol = 16; //16 tile x ekseninde
	public final int maxScreenTileRow = 12; //12 tile y ekseninde
	public final int screenWidth = tileSize * maxScreenTileCol; // 768 pixel
	public final int screenHeight = tileSize * maxScreenTileRow; //576 pixel
	
	//Dünya ayarlarý
	public final int maxMapCol = 50; //HArita 50'ye 50 tilelardan oluþacak
	public final int maxMapRow = 50;
	public final int worldXLength = tileSize + maxMapCol;
	public final int worldYLength = tileSize + maxMapRow;
	
	
	int FPS = 60;
	TileControl tileObj = new TileControl(this);
	KeyControl keyObj = new KeyControl(); //*
	Thread gameThread; //Loop için gerekli. Çalýþtýðnda run metodunu çaðýrýr.
	public CollisionControl colChecker = new CollisionControl(this);
	public ObjectControl objCon = new ObjectControl(this);
	public Player player = new Player(this, keyObj);
	public GeneralObject allObjs[] = new GeneralObject[10]; //Ayný anda 10 obje olabilir ekranda eðer bir obje alýnýrsa obje yok olur ve yeni bir obje ekleyebiliriz
	
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight)); //Verilen deðerde panel oluþumu
		this.setBackground(Color.darkGray); //Arka plan rengi
		this.setDoubleBuffered(true); //bütün çizimler ekranda olmayan  bir buffer tarafýndan çizilecek
		this.addKeyListener(keyObj); 
		this.setFocusable(true); //Panel'in tuþ basýmlarýna odaklanmasýný saðlar.
		
		
	}
	
	public void setGame() {
		
		objCon.setObject(); 
	}
	
	public void startThread() {
		gameThread = new Thread(this); //Thread programýn izleyeceði yol gibidir, programýn çalýþma buna baðlý.
		gameThread.start(); //excecution baþlar
	
	}
	
	public void run() { //Loop 
		double drawInterval = 1000000000/FPS; // her çizim kaç saniye ara ile olacak 1 saniyeyi 60'a böldük.
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		
		while (gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval; // çizim için artan zaman var
			
			lastTime = currentTime;
			
			if (delta >= 1) {
			updateScreen();
			repaint();
			delta--;
			}
			
			
		}
	}
	
	public void updateScreen() { //Bilgi update edilip ve draw ile çizilir.
		
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); //Paiting için gerekli kod burada, ve kendisi gerekli olduðunda çaðýrýlýr.
		
		Graphics2D graphic2 = (Graphics2D)g; // Graphics 2D graphics'den daha komplike iþlemleri yapmamýzý saðlar. Geometri vs.
		
		//Tile çizimi
		tileObj.draw(graphic2);
		
		//Obje çizimi
		for(int i = 0; i < allObjs.length; i++) {
			if(allObjs[i] != null) {
				allObjs[i].draw(graphic2, this);
			}
		}
		
		//oyuncu çizimi
	    player.draw(graphic2);
		graphic2.dispose(); //kurtul
	}

}
