package main;
import java.awt.event.KeyEvent; //Meydana gelen tuþ basýmý.
import java.awt.event.KeyListener;

public class KeyControl implements KeyListener{
	public boolean upP, downP, leftP, rightP;
	@Override
	public void keyTyped(KeyEvent e) { 
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
	//her tuþu kontrol ediyoruz, iki tuþu ayný anda koymayý denemek için buraya tekrar gel.
		
		if (code == KeyEvent.VK_A) {
			upP = false;
			rightP = false;
			downP = false;
			leftP = true;
		}
		if (code == KeyEvent.VK_D) {
			upP = false;
			leftP = false;
			downP = false;
			rightP = true;
		}
		if (code == KeyEvent.VK_W) {
			leftP = false;
			rightP = false;
			downP = false;
			upP = true;
		}
		if (code == KeyEvent.VK_S) {
			upP = false;
			leftP = false;
			rightP = false;
			downP = true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
	
		if (code == KeyEvent.VK_S) {
			downP = false;
		}
		if (code == KeyEvent.VK_W) {
			upP = false;
		}
		
		if (code == KeyEvent.VK_D) {
			rightP = false;
		}
		if (code == KeyEvent.VK_A) {
			leftP = false;
		}
	}
	
	
}
