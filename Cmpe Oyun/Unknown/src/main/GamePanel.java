package main;

import java.awt.Dimension; //Dimension bir obje i�ine uzunluk ve geni�lik girmemizi sa�lar.
import main.KeyControl.*;

import javax.swing.JPanel; // Bile�enler, ba�layabilece�imiz alan olu�turmada kullan�l�r.

import forPlayer.Player;
import object.GeneralObject;
import tile.TileControl;

import java.awt.Color; //Renkleri (darkGray'i) buradan al�yoruz.

import java.awt.Graphics; //Ekran �st�ne veya arkada �al��an olsun, �izimler graphics ile yap�l�r

import java.awt.Graphics2D;//Graphicsden daha komplike i�levleri var, geometri, koordinat, renk gibi i�lerden sorumlu.

public class GamePanel extends JPanel implements Runnable{//thread i�in runnable kulland�k. JPanel panel olu�umu i�indir.
	//Ekran Ayarlar�
	 final int ogTileSize = 16; //16*16 tile
	public final int tileSize = ogTileSize * 3; //48*48 �imdilik 
	public final int maxScreenTileCol = 16; //16 tile x ekseninde
	public final int maxScreenTileRow = 12; //12 tile y ekseninde
	public final int screenWidth = tileSize * maxScreenTileCol; // 768 pixel
	public final int screenHeight = tileSize * maxScreenTileRow; //576 pixel
	
	//D�nya ayarlar�
	public final int maxMapCol = 50; //HArita 50'ye 50 tilelardan olu�acak
	public final int maxMapRow = 50;
	public final int worldXLength = tileSize + maxMapCol;
	public final int worldYLength = tileSize + maxMapRow;
	
	
	int FPS = 60;
	TileControl tileObj = new TileControl(this);
	KeyControl keyObj = new KeyControl(); //*
	Thread gameThread; //Loop i�in gerekli. �al��t��nda run metodunu �a��r�r.
	public CollisionControl colChecker = new CollisionControl(this);
	public ObjectControl objCon = new ObjectControl(this);
	public Player player = new Player(this, keyObj);
	public GeneralObject allObjs[] = new GeneralObject[10]; //Ayn� anda 10 obje olabilir ekranda e�er bir obje al�n�rsa obje yok olur ve yeni bir obje ekleyebiliriz
	
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight)); //Verilen de�erde panel olu�umu
		this.setBackground(Color.darkGray); //Arka plan rengi
		this.setDoubleBuffered(true); //b�t�n �izimler ekranda olmayan  bir buffer taraf�ndan �izilecek
		this.addKeyListener(keyObj); 
		this.setFocusable(true); //Panel'in tu� bas�mlar�na odaklanmas�n� sa�lar.
		
		
	}
	
	public void setGame() {
		
		objCon.setObject(); 
	}
	
	public void startThread() {
		gameThread = new Thread(this); //Thread program�n izleyece�i yol gibidir, program�n �al��ma buna ba�l�.
		gameThread.start(); //excecution ba�lar
	
	}
	
	public void run() { //Loop 
		double drawInterval = 1000000000/FPS; // her �izim ka� saniye ara ile olacak 1 saniyeyi 60'a b�ld�k.
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		
		while (gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval; // �izim i�in artan zaman var
			
			lastTime = currentTime;
			
			if (delta >= 1) {
			updateScreen();
			repaint();
			delta--;
			}
			
			
		}
	}
	
	public void updateScreen() { //Bilgi update edilip ve draw ile �izilir.
		
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); //Paiting i�in gerekli kod burada, ve kendisi gerekli oldu�unda �a��r�l�r.
		
		Graphics2D graphic2 = (Graphics2D)g; // Graphics 2D graphics'den daha komplike i�lemleri yapmam�z� sa�lar. Geometri vs.
		
		//Tile �izimi
		tileObj.draw(graphic2);
		
		//Obje �izimi
		for(int i = 0; i < allObjs.length; i++) {
			if(allObjs[i] != null) {
				allObjs[i].draw(graphic2, this);
			}
		}
		
		//oyuncu �izimi
	    player.draw(graphic2);
		graphic2.dispose(); //kurtul
	}

}
