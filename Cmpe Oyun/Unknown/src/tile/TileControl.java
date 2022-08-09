package tile;

import java.io.BufferedReader; //Girilen bir stream'den karakterleri okur.
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader; //Inout straem byte olarak al�r bu ise byte'� characterlere �evirir.
import java.awt.Graphics2D;

import javax.imageio.ImageIO; //Resimlerin in ve outu i�in

import main.GamePanel;

public class TileControl {
	GamePanel gp;
	public TileSettings[] tile;
	public int mapTileNum[][];
	private int num;
	
	public TileControl(GamePanel gp) {
		this.gp = gp;
		
		tile = new TileSettings[10];
		mapTileNum = new int[gp.maxMapCol][gp.maxMapCol];
		
		getImageofTile();
		startMap("/maps/Graveyard.txt");
	}
	
	public void getImageofTile() {
		tile[0] = this.createTile("/tiles/Alp_pArt_Grass2.png", false);
		tile[1] = this.createTile("/tiles/Alp_pArt_Wall2.png", true);
		tile[2] = this.createTile("/tiles/Dirt2_Tile.png", false);
		tile[3] = this.createTile("/tiles/Dirt_Tile.png", false);
		tile[4] = this.createTile("/tiles/Alp_pArt_WoodenGround.png", false);
		tile[5] = this.createTile("/tiles/Dirt_Tile.png", false);
		tile[6] = this.createTile("/tiles/Alp_pArt_Wall.png", true);
		tile[7] = this.createTile("/tiles/Alp_pArt_GraveStoneUp.png", true);
		tile[8] = this.createTile("/tiles/Alp_pArt_GraveStoneLow.png", true);
	}

	private TileSettings createTile(String resourcePath, Boolean collision) { 
		TileSettings t1 = new TileSettings();
		t1.collision = collision;
		try {
			t1.image = ImageIO.read(getClass().getResourceAsStream(resourcePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t1;
	}
	
	public void startMap(String filePath) {//loads map
		try {
			InputStream s1 = getClass().getResourceAsStream(filePath);	//importlamak i�in
			
			BufferedReader alp = new BufferedReader(new InputStreamReader(s1));// okumak i�in	
			
			int col = 0;
			int row = 0;
			
			while (col < gp.maxMapCol && row < gp.maxMapCol) {
				String partOfLine = alp.readLine();
				while (col < gp.maxMapCol) {
					String numbers[] = partOfLine.split(" ");
					
					int num = Integer.parseInt(numbers[col]); //num arryaindeki data string olarak okunuyor burada da int oluyor
					
					mapTileNum[col][row] = num; //Hartian�n koordinat�ndaki say� tile arrayindeki elemtlerin yerine ge�mek i�i num oluyor.
					col++;
				}
				if (col == gp.maxMapCol) {
					col = 0;
					row++;
				}
			}
			alp.close();
			
		}catch (Exception e) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int worldXasTile = 0;
		int worldYasTile = 0;
		
		
		while (worldXasTile < gp.maxMapCol && worldYasTile < gp.maxMapRow ) {
			
			int focusedTile = mapTileNum[worldXasTile][worldYasTile]; //iki array var bunlardan birisi haritadaki kkordinat� al�rken tile[tileNum] 
			//oraya girilmi� say� de�erini yani image'i al�yor.
			
			int worldX = worldXasTile * gp.tileSize;
			int worldY = worldYasTile * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX; //world x'ten player.world ��kar�l�nca tile'�n karaktere g�re map'te nereye �izilmesi
			// gerekti�ini buluruz, player.player.screen eklenice karakterin ekran�n ortas�nda �izilmesi devam ediyor.
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			// if k�sm� program�n b�t�n haritay� �izmek yerine sadece bizim g�r�� alan�m�zda olan k�s�mlar� �izmesini sa�l�yor
			// + gp.tileSize 48 piksel daha eklyerek g�r�nt�de daha �izilmeyen karanl�k k�s�mlar� tamaml�yor, onlar� silip g�rebilirsin
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
					worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
					worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
					worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			g2.drawImage(tile[focusedTile].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			worldXasTile++;
			
			
			if (worldXasTile == gp.maxMapCol) { //x ekseni s�n�ra vurunca resetlenmesini sa�lar.
				worldXasTile = 0;
				
				worldYasTile++;
				
			}
		}
	}

}
