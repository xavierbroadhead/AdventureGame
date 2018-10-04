package Renderer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GameWorld.Player;
import GameWorld.Player.Direction;
import GameWorld.Position;

public class Render{
	
	//access to image of wall/direction
	public int wallZoomScale;
	public int objectZoomScale;
	//keep track of direction being rendered using player object, 
	//also allows you to know position - also tell us what object is there
	public Player player; 
	
	public File background;
	public File item;
	
	
	public File getItemFile(String itemName) {
		if(itemName.equals("key")) {
			//set item file to key png
			return item;
		}
		return null;
		
	}
	
	//THIS RENDERER SHOULD PRINT THE IMAGE ONTO A GRAPHIS OBJECT WHICH IS GIVEN TO THE GUI CLASS WHEN CALLED
	//IN MAP ARRAYS STORE IMAGES FOR EACH POSOTION (4 FOR EACH) - SIMPLY GET IMAGE CORRELATING TO DIRECTION OF PLAYER
	public void getBackgroundFile(Player player) {
		//based on player position there should be same 4 photos used on corridor, variance is zoomScale
		//get position on board
		//get file of view based on which direction player is looking from map array(each position of map array should have 4 files)
		//return background
		Position playerPos = player.getPosition();
		Direction direction = player.getDirection();
		if(direction == Direction.NORTH) {
			//image = mapArray[playerPos.x][playerPos.y]
			//or image = position.view.north
			
			//frontDemo file
		}
		if(direction == Direction.EAST) {
			//rightDemofile
		}
		if(direction == Direction.SOUTH) {
			//backDemo file
		}
		if(direction == Direction.WEST) {
			//leftDemo file
		}
		
		renderBackground(background);
		//return background;
	}

	
	public BufferedImage manipulateImage(Player player, BufferedImage img, int imageWidth, int imageHeight) {
		int zoomFactor = zoomFactor(player);
		int newImageHeight = imageHeight * zoomFactor;
		int newImageWidth = imageWidth * zoomFactor;
		BufferedImage resizedImage = new BufferedImage( newImageWidth, newImageHeight, BufferedImage.TYPE_INT_ARGB);
		
		return resizedImage;
	}
	
	public int zoomFactor(Player player) {
		return 0;
		//return player.stepsFromWallInFront
	}
	
	//always keep track of a 3x3 grid around you
	//check grid for accessable tiles to understand which file you want to return
	//just check how far away walls are from current pos
	
	/*
	 * Get file of background depending on position and which position is being face
	 * Manipulate image depending on position (zoomScale)
	 * return the file to be rendered on JPanel
	 */
	public Graphics2D renderBackground(File imageFile) {
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(new File(imageFile.getName()));//need to get correct name of image file
		}
		catch (IOException e) {
		}
		
		int imageWidth = 0;
		int imageHeight = 0;
		BufferedImage scaledImage = manipulateImage(player, img, imageWidth, imageHeight);
		
		Graphics2D g = (Graphics2D) scaledImage.getGraphics();
		
		return g;
	}
	
}
