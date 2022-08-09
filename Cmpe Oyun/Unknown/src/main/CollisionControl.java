package main;

import forPlayer.Tools;

public class CollisionControl {
	GamePanel gp;
	
	public CollisionControl(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkCollision (Tools entity) { //Tilelarýn collisionunu kontrol eder
		
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize; //Oyuncunun olacaðý
		int entityBottomRow = entityBottomWorldY/ gp.tileSize;
		
		int firstTile, secondTile;
		
		switch(entity.direction) {//Bakýlan yönde çarpýþmayý iki tile'da test ediyor
		case "up":
			entityTopRow = entityTopRow - (entity.speedOfCharacter  / gp.tileSize); //Karakterin olacaðý koordinatý tile size'a bölerek hangi tile'da olacaðýný
			// bulur.
			firstTile = gp.tileObj.mapTileNum[entityLeftCol][entityTopRow];
			secondTile = gp.tileObj.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileObj.tile[firstTile].collision == true || gp.tileObj.tile[secondTile].collision == true) {
				entity.collisionState = true;
			}
			break;
		case "down":
			entityBottomRow = entityBottomRow + (entity.speedOfCharacter  / gp.tileSize);
			firstTile = gp.tileObj.mapTileNum[entityLeftCol][entityBottomRow];
			secondTile = gp.tileObj.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileObj.tile[firstTile].collision == true || gp.tileObj.tile[secondTile].collision == true) {
				entity.collisionState = true; }
			break;
		case "left":
			entityLeftCol = entityLeftCol - (entity.speedOfCharacter  / gp.tileSize);
			firstTile = gp.tileObj.mapTileNum[entityLeftCol][entityTopRow];
			secondTile = gp.tileObj.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileObj.tile[firstTile].collision == true || gp.tileObj.tile[secondTile].collision == true) {
				entity.collisionState = true; }
			break;
		case "right":
			entityRightCol = entityRightCol + (entity.speedOfCharacter  / gp.tileSize);
			firstTile = gp.tileObj.mapTileNum[entityRightCol][entityTopRow];
			secondTile = gp.tileObj.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileObj.tile[firstTile].collision == true || gp.tileObj.tile[secondTile].collision == true) {
				entity.collisionState = true; }
			break;	
		}
	}
	//objelerin çarpmasý
	public int checkObject(Tools entity, boolean player) { //entity oyuncu olabilir
		
		int index = 999;
		
		for(int i = 0; i < gp.allObjs.length; i++) {
			if(gp.allObjs[i] != null) {
				//oyuncunun katý alaný koordinatý
				entity.solidArea.x= entity.worldX + entity.solidArea.x;
				entity.solidArea.y= entity.worldY + entity.solidArea.y;
				//objenin katý alan koordinatý
				
				gp.allObjs[i].areaOfCollusion.x = gp.allObjs[i].worldX + gp.allObjs[i].areaOfCollusion.x;
				gp.allObjs[i].areaOfCollusion.y = gp.allObjs[i].worldY + gp.allObjs[i].areaOfCollusion.y;
				
				switch(entity.direction) {
				case "up":
				entity.solidArea.y -= entity.speedOfCharacter;
				if(entity.solidArea.intersects(gp.allObjs[i].areaOfCollusion)) {
					if(gp.allObjs[i].collision == true) {
						entity.collisionState = true;
					}
					if(player == true) {
						index = i;
					}
					
					break;
				}
				
				case "down":
					entity.solidArea.y += entity.speedOfCharacter;
					if(entity.solidArea.intersects(gp.allObjs[i].areaOfCollusion)) {
						if(gp.allObjs[i].collision == true) {
							entity.collisionState = true;
						}
						if(player == true) {
							index = i;
						}
						
						  break;
					}
				  
				case "left":
					entity.solidArea.x -= entity.speedOfCharacter;  //karakter hareketinden sonra nerede olacak
					if(entity.solidArea.intersects(gp.allObjs[i].areaOfCollusion)) {
						if(gp.allObjs[i].collision == true) {
							entity.collisionState = true;
						}
						if(player == true) {
							index = i;
						}
						
						break;
					}
					
				case "right":
					entity.solidArea.x += entity.speedOfCharacter;
					if(entity.solidArea.intersects(gp.allObjs[i].areaOfCollusion)) {
						if(gp.allObjs[i].collision == true) {
							entity.collisionState = true;
						}
						if(player == true) {
							index = i;
						}
						
						break;
					}
					
				
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				
				gp.allObjs[i].areaOfCollusion.x = gp.allObjs[i].solidAreaDefaultX;
				gp.allObjs[i].areaOfCollusion.y = gp.allObjs[i].solidAreaDefaultY;
			}
				
			}
			
		return index;
	}

}
