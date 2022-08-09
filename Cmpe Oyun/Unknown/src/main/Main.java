package main;

import java.io.IOException; //In ve out i�lemlerinde meydana gelbilecek hatalar.

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import music.MusicTest;

public class Main extends JFrame{ //JFrame pencere olu�turmam�z� sa�lar, ekran �zerindeki butonlar, ��kma tu�u gibi bile�enler buna aittir.
	//JFrame bir GUI olu�turmam�za yarar. (GUI kullan�c�n�n bilgisayarla yap�lan butonlar vb toollar ile ilti�ime ge�mesi)

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		JFrame window = new JFrame(); //pencere olu�turmak i�in i�erisinde haz�r methodlar olan jframe kullan�l�r.
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Unknown");
			
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel); // olu�turaln panel pencereye eklendi
		window.pack(); // pencerenin gamePanel ile uyu�mas� i�in boyutunu ayarlar, silinirse sadece pencere �ubu�u g�z�k�r
		
		window.setLocationRelativeTo(null); //Ekran�n ortas�
		window.setVisible(true);// pencerenin g�r�nt�lenmesi
		
		
		
		gamePanel.setGame();
		gamePanel.startThread();
		try {
			MusicTest.startMusic();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
