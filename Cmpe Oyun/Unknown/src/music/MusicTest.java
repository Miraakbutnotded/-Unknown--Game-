package music;
import javax.sound.sampled.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class MusicTest {
		
	public static void startMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		int a = 0;
		File nFile = new File("Yusuf.wav"); //nFile kullan�lan dosya� temsil eden bir obje.
		AudioInputStream a1 = AudioSystem.getAudioInputStream(nFile); //getAudioInputStream belirtilen dosyay� kullanmam�z� sa�lar.
		Clip tClip =AudioSystem.getClip(); //Sesi oynatmak i�in.
		tClip.open(a1);
		tClip.start();
		long length = tClip.getMicrosecondLength();// ses dosyas�n�n uzunlu�u
		long length2  = tClip.getMicrosecondPosition();
		
		while (a < 32) {
			if ( length2 == length) {
				tClip.setMicrosecondPosition(0);
				tClip.start();
				try {
					Thread.sleep(32000);
					a++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println(length + " , " + length2);
	}
	
	
	
	
	
	

}
