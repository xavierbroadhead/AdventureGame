package Renderer;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GameWorld.Player;
import GameWorld.Player.Direction;
import GameWorld.Position;

public class Render{
	
	
	public int wallZoomScale;
	public int objectZoomScale;
	public Player player; 
	public File background;
	public Image image;
	public File item;
	
	/**
	 * Returns image specified in input string.
	 * 
	 * @param itemName - String of item name
	 * @return item - File of item image
	 */
	public File getItemFile(String itemName) {
		if(itemName.equals("key")) {
			//set item file to key png
			return item;
		}
		return null;
		
	}
	
	/**
	 * Gets the image player is viewing based on their position and direction in a map.
	 * Calls a render method to draw and scale image.
	 * 
	 * @param player
	 */
	public BufferedImage getBackgroundFile(Player player) {
		Direction direction = player.getDirection();
		if(direction == Direction.NORTH) {
			//frontDemo file
			image = loadImage("front.png");


			System.out.println("NORTH");
			


			System.out.println("NORTH");
			

		}
		if(direction == Direction.EAST) {
			//rightDemofile
			
			image = loadImage("right.png");
			System.out.println("EAST");
			System.out.println("image: " + image);



			
		}
		if(direction == Direction.SOUTH) {
			//backDemo file
			image = loadImage("back.png");
			System.out.println("SOUTH");
			System.out.println("image: " + image);

		}
		if(direction == Direction.WEST) {
			//leftDemo file
			image = loadImage("left.png");
			System.out.println("WEST");
			System.out.println("image: " + image);

		}
		
		return renderBackground(image);
	}

	/**
	 * Returns a scaled bufferedImage based on zooming in on an original bufferedImage. 
	 * This is based on how far away a player is from a wall.
	 * 
	 * @param player - Game player
	 * @param img - Original bufferedImage of the image stored in Camera object
	 * @param imageWidth - width in pixels of bufferedImage
	 * @param imageHeight - height in pixels of bufferedImage
	 * @return - A  scaled bufferedImage.
	 */
	public BufferedImage manipulateImage(Player player, BufferedImage img, int imageWidth, int imageHeight) {
		int zoomFactor = zoomFactor(player);
		int newImageHeight = imageHeight * zoomFactor;
		int newImageWidth = imageWidth * zoomFactor;
		BufferedImage resizedImage = new BufferedImage( newImageWidth, newImageHeight, BufferedImage.TYPE_INT_ARGB);
		
		return resizedImage;
	}
	
	public int zoomFactor(Player player) {
		return 0;
		
	}
	
	
	/**
	 * Converts Image in Camera object to bufferedImage, scales image based on distance 
	 * from wall and returns bufferedImage to the GUI.
	 * 
	 * @param image - Image from
	 * @return img - Scaled bufferedObject for GUI to display on canvas.
	 */
	public BufferedImage renderBackground(Image image) {
		BufferedImage img = (BufferedImage) image;
		/**
		try {
			img = ImageIO.read(new File(imageFile.getName()));//need to get correct name of image file
		}
		catch (IOException e) {
		}
		*/
		
		int imageWidth = 0;
		int imageHeight = 0;
		//BufferedImage scaledImage = manipulateImage(player, img, imageWidth, imageHeight);
		
		
		return img;
	}
	
	/**
	 * Retrieves image from images package based on fileName specified in getBackground method.
	 * 
	 * @param fileName - Name of image file
	 * @return img - Image file stored in images package.
	 */
	public Image loadImage(String fileName) {
		java.net.URL imageURL = Render.class.getResource("images/" + fileName);
		System.out.println("imageURL: " + imageURL.toString());
		Image img;
		try {
			img = ImageIO.read(imageURL);
			return img;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Unable to load image" + fileName);
		}
		
	}
	
	
	
}
