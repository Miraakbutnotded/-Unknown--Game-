package main;

import java.io.IOException; //In ve out iþlemlerinde meydana gelbilecek hatalar.

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import music.MusicTest;

public class Main extends JFrame{ //JFrame pencere oluþturmamýzý saðlar, ekran üzerindeki butonlar, çýkma tuþu gibi bileþenler buna aittir.
	//JFrame bir GUI oluþturmamýza yarar. (GUI kullanýcýnýn bilgisayarla yapýlan butonlar vb toollar ile iltiþime geçmesi)

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		JFrame window = new JFrame(); //pencere oluþturmak için içerisinde hazýr methodlar olan jframe kullanýlýr.
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Unknown");
			
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel); // oluþturaln panel pencereye eklendi
		window.pack(); // pencerenin gamePanel ile uyuþmasý için boyutunu ayarlar, silinirse sadece pencere çubuðu gözükür
		
		window.setLocationRelativeTo(null); //Ekranýn ortasý
		window.setVisible(true);// pencerenin görüntülenmesi
		
		
		
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
