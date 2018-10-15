package Renderer;

import javax.imageio.ImageIO;
import javax.swing.*;

import GameWorld.Game;
import GameWorld.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Render {


  public void renderGame(Graphics2D g2, int width, int height, Game game, int steps) {

    System.out.println("game position: " + game.getPlayer().getPosition().getx() + " , "+  game.getPlayer().getPosition().gety());
    System.out.println("Steps: " + game.tilesTilWall());
    System.out.println("direction: " + game.getPlayer().getDirection());
    System.out.println("wall front: " + game.wallForward());
    System.out.println("wall back: " + game.wallBehind());
    System.out.println("wall left: " + game.wallLeft());
    System.out.println("wall right: " + game.wallRight());
    int windowWidth = width;
    int windowHeight = height;
    int edgeWidth = 50;
    int tileHeight = 50;
    int bottom = windowWidth;
    int mid = bottom/2;
    int y = bottom;
    int stepsToWall = game.tilesTilWall() +1;
    int stepsToWallPolys = stepsToWall;
    boolean keyItem = false;
    
    
    Image img1 = loadImage("newKeyImage1.png");
    //BufferedImage key = scale((BufferedImage)img1, 60, 40);
    BufferedImage image = null;
    Image img2 = loadImage("scroll.png");
    //BufferedImage scroll = scale((BufferedImage)img2, 70, 40);
    
    //check if item is a key, if so it will get the key image. if not it gets the scroll image
    if(keyItem) {
      image = scale((BufferedImage)img1, 60, 40);
    }
    else {
      image = scale((BufferedImage)img2, 70, 40);
    }
    
    
    //to render object
    //check if object is in squares in front of player: boolean
    //if true, return how many steps away it is 
    
    
    ArrayList<Edge> horizontalEdges = new ArrayList<Edge>();
    for(int j = bottom; j > windowHeight/2; j--) {
      y = y/2;
      stepsToWall--;
      if(stepsToWall < 0) break;
      //if(y < 0.3) break;
      //System.out.println("y: " + y);
      Vertex lil = new Vertex(0, y, 0);
      Vertex uzi = new Vertex(windowWidth,  y, 0);
      Edge e = new Edge(lil, uzi);
      horizontalEdges.add(e);
      e.drawLine(g2);

    }

    for(Edge e : horizontalEdges) {
      e.v1.drawOval(g2);
      e.v2.drawOval(g2);
    }

    Vertex horizon = new Vertex(windowWidth/2, 0, 0);
    ArrayList<Edge> verticalEdges = new ArrayList<Edge>();
    /*
                    for(int i = 0; i < getWidth(); i++) {
                      if(i*edgeWidth > getWidth()) {
                        break;
                      }
                      Vertex v = new Vertex(0 + (i*edgeWidth), bottom, 0);
                      Edge ed = new Edge(horizon, v);
                      verticalEdges.add(ed);
                      ed.drawLine(g2);
                    }
     */
    Vertex bottomL = new Vertex(0,bottom,0);
    Vertex bottomR = new Vertex(windowWidth,bottom,0);
    Edge left = new Edge(bottomL, horizon);
    Edge right = new Edge(bottomR, horizon);
    left.drawLine(g2);
    right.drawLine(g2);
    verticalEdges.add(left);
    verticalEdges.add(right);






    ArrayList<Intersect> intersectionsL = new ArrayList<Intersect>();
    ArrayList<Intersect> intersectionsR = new ArrayList<Intersect>();
    //adding first two points on bottom left and right of screen to make first tile
    Intersect bottomLeft = new Intersect(0, windowHeight);
    intersectionsL.add(bottomLeft);
    Intersect bottomRight = new Intersect(windowWidth, windowHeight);
    intersectionsR.add(bottomRight);
    //adding all left hand side intersects 
    left.findGradient();
    left.findOffset();
    for(Edge ez : horizontalEdges) {
      ez.findGradient();
      ez.findOffset();
      Intersect intercept = ez.IntersectX(left);
      intersectionsL.add(intercept);
      g2.setColor(Color.RED);
      g2.fillOval((int)intercept.xInt, (int)intercept.yInt, 5, 5);
    }
    //adding all right hand side intersects 
    right.findGradient();
    right.findOffset();
    for(Edge ez : horizontalEdges) {
      ez.findGradient();
      ez.findOffset();
      Intersect intercept = ez.IntersectX(right);
      intersectionsR.add(intercept);
      g2.setColor(Color.RED);
      g2.fillOval((int)intercept.xInt, (int)intercept.yInt, 5, 5);
    }

    ArrayList<Tile> tiles = new ArrayList<Tile>();
    ArrayList<Polygon> polygons = new ArrayList<Polygon>();

    //creating polygon(tile) objects, adding to array then rendering.
    for(int i = 0; i < intersectionsL.size()-1; i++) {
      Tile tile = new Tile(intersectionsL.get(i), intersectionsL.get(i+1),intersectionsR.get(i), intersectionsR.get(i+1), Color.DARK_GRAY);
      tiles.add(tile);
      Polygon poly = new Polygon();
      poly.addPoint((int)intersectionsL.get(i).xInt, (int) intersectionsL.get(i).yInt);
      poly.addPoint((int)intersectionsR.get(i).xInt, (int) intersectionsR.get(i).yInt);
      poly.addPoint((int)intersectionsR.get(i+1).xInt, (int) intersectionsR.get(i+1).yInt);
      poly.addPoint((int)intersectionsL.get(i+1).xInt, (int) intersectionsL.get(i+1).yInt);
      polygons.add(poly);
      //System.out.println("Polygon created");
    }

    // drawing tile/polygon objects 
    for(Polygon p : polygons) {
      //System.out.println("Polygons");
      //System.out.println("Polygon: " + p.toString());
      g2.setColor(Color.ORANGE);
      
      g2.fillPolygon(p);
    }
    
    for(Tile t: tiles) {
      g2.setColor(Color.RED);
      g2.fillOval((int)t.v1.xInt+10, (int)t.v1.yInt - 10, 5, 5);
      System.out.println("drawn");
    }
    System.out.println("Polygons: " + polygons.size());
    System.out.println("Tiles: " + tiles.size());
    
    g2.setColor(Color.ORANGE);
    Polygon small = polygons.get(polygons.size()-1);
    g2.fillPolygon(small);
    Tile last = tiles.get(tiles.size()-1);
    int sideLength = ((int)last.v3.xInt - (int)last.v1.xInt);
    int xT = (int)last.v3.xInt - (sideLength/2) - 20;
    int yT = (int)last.v1.yInt - (int)last.v2.yInt;
    System.out.println("xt: " + xT);
    System.out.println("yt: " + yT);
    g2.drawImage(image, xT, yT, null);
    g2.setColor(Color.ORANGE);
    g2.drawOval(xT, yT, 8, 8);
    g2.drawImage(image, windowWidth-10, 0, null);
    //finding highest tile, where wall will be drawn
    Intersect lMax = null;
    Intersect rMax = null;
    //highest point on left
    for(int i = 0; i < intersectionsL.size()-1; i++) {
      if(intersectionsL.get(i).yInt < intersectionsL.get(i+1).yInt) {
        lMax = intersectionsL.get(i);
      }
      else {
        lMax = intersectionsL.get(i+1);
      }
    }
    //System.out.println("max is: " + lMax.yInt);
    //System.out.println("max should be: " + intersectionsL.get(stepsToWallPolys).yInt);

    //highest point on right
    for(int i = 0; i < intersectionsR.size()-1; i++) {
      if(intersectionsR.get(i).yInt < intersectionsR.get(i+1).yInt) {
        rMax = intersectionsR.get(i);
      }
      else {
        rMax = intersectionsR.get(i+1);
      }
    }
    //drawing back wall for passage with no turns
    Polygon backWall = new Polygon();

    backWall.addPoint((int)rMax.xInt, (int)rMax.yInt);
    backWall.addPoint((int)lMax.xInt, (int)lMax.yInt);

    Intersect topWL = new Intersect((int)lMax.xInt, 0);//top of wall left
    intersectionsL.add(topWL);
    backWall.addPoint((int)topWL.xInt, 0);
    Intersect topWR = new Intersect((int)rMax.xInt, 0);
    intersectionsR.add(topWR);
    backWall.addPoint((int)topWR.xInt, 0);//top of wall right
    g2.setColor(Color.GRAY);
    //g2.fillPolygon(backWall);

    for(int i = 0; i < intersectionsL.size(); i++) {
      Intersect i1 = intersectionsL.get(i);
      Intersect i2 = intersectionsR.get(i);
      g2.setColor(Color.BLACK);
      g2.drawLine((int)i1.xInt, (int)i1.yInt, (int)i2.xInt, (int)i2.yInt);
    }


    //creates polygon for back wall with turns
    Polygon backWallT = new Polygon();
    backWallT.addPoint(0, 0);
    backWallT.addPoint(0, (int)lMax.yInt);
    backWallT.addPoint(windowWidth, (int)lMax.yInt);
    backWallT.addPoint(windowWidth, 0);
    g2.setColor(Color.GREEN);
    //g2.fillPolygon(backWallT);//draws back wall all along display


    //draws left wall
    Polygon leftWall = new Polygon();
    leftWall.addPoint((int)topWL.xInt, (int)topWL.yInt); 
    leftWall.addPoint(0, 0);
    leftWall.addPoint(0, bottom);
    leftWall.addPoint((int)lMax.xInt, (int)lMax.yInt);
    g2.setColor(Color.GRAY);
    g2.fillPolygon(leftWall);
    left.drawLine(g2);
    right.drawLine(g2);

    Intersect rturn = intersectionsR.get(stepsToWallPolys-1);


    //g2.setColor(Color.GREEN);   
    //g2.drawLine((int)rturn.xInt, (int)rturn.yInt, getWidth(), (int)rturn.xInt);
    //g2.drawLine((int)rturn.xInt, (int)rturn.yInt, (int)rturn.xInt, 0);

    //draws floor for right turn
    Polygon floorT = new Polygon();//floor for turns
    floorT.addPoint(0, (int)rMax.yInt);
    floorT.addPoint(0, (int)rturn.yInt);
    floorT.addPoint(windowWidth, (int)rturn.yInt);
    floorT.addPoint(windowWidth, (int)rMax.yInt);
    g2.setColor(Color.GRAY);
    g2.fillPolygon(floorT);
    //draws line seperting floor from cieling along back wall
    g2.setColor(Color.BLACK);
    g2.drawLine(0, (int)rMax.yInt, windowWidth, (int)rMax.yInt);




    Intersect lturn = intersectionsL.get(stepsToWallPolys-1);
    g2.setColor(Color.GREEN); 
    //g2.drawLine((int)lturn.xInt, (int)lturn.yInt, (int)lturn.xInt, 0);



    //draws right wall w/ right turn
    Polygon rightTurn = new Polygon();
    rightTurn.addPoint((int)rturn.xInt, (int)rturn.yInt);
    rightTurn.addPoint(windowWidth, bottom);
    rightTurn.addPoint(windowWidth, 0);
    rightTurn.addPoint((int)rturn.xInt, 0);



    //draws left wall w/ left turn
    Polygon leftTurn = new Polygon();
    leftTurn.addPoint((int)lturn.xInt, (int)lturn.yInt);
    leftTurn.addPoint((int)lturn.xInt, 0);
    leftTurn.addPoint(0, 0);
    leftTurn.addPoint(0, bottom);




    //draws right wall
    Polygon rightWall = new Polygon();
    rightWall.addPoint(windowWidth, 0);
    rightWall.addPoint((int)topWR.xInt, (int)topWR.yInt);
    rightWall.addPoint((int)rMax.xInt, (int)rMax.yInt);
    rightWall.addPoint(windowWidth, bottom);



    g2.setColor(Color.GREEN);
    //g2.fillPolygon(rightWall);//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    left.drawLine(g2);
    right.drawLine(g2);

    if(game.hasRightCorner()) {
      System.out.println("right turn");
      g2.setColor(Color.RED);
      g2.drawLine((int)rMax.xInt, (int)rMax.yInt, windowWidth, (int)rMax.yInt);//draws line seperating back wall from floor
      g2.fillPolygon(rightTurn);//call to draw right turn//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      g2.fillPolygon(leftWall);
      g2.fillPolygon(backWallT);

      g2.setColor(Color.BLACK); 

      //line for wall turn
      g2.drawLine((int)rturn.xInt, (int)rturn.yInt, (int)rturn.xInt, 0);

      g2.setColor(Color.BLACK);
      //line for skiring seperating floor and walls
      g2.drawLine(0, bottom, (int)lMax.xInt, (int)lMax.yInt);
      g2.drawLine(windowWidth, bottom, (int)rMax.xInt, (int)rMax.yInt);

      drawWallLines(intersectionsL, intersectionsR, g2);

      //g2.drawImage(img3, 0, 0, null);//draws scroll image on tile
      //g2.drawImage(image, 0, 0, null);
      //g2.drawImage(image, xT, yT, null);
      drawItem(image, tiles, g2);
      return;
    }

    //if(door in front){
    //}

    //draws left corner turn
    if(game.hasLeftCorner()) {
      System.out.println("left turn");
      g2.setColor(Color.BLACK);
      g2.drawLine((int)lMax.xInt, (int)lMax.yInt, 0, (int)lMax.yInt);//draws line seperating back wall from floor
      g2.setColor(Color.GREEN);
      g2.fillPolygon(backWallT);
      g2.fillPolygon(leftTurn);//call to draw left turn
      g2.fillPolygon(rightWall);
      
     

      g2.setColor(Color.BLACK); 

      //line for wall turn
      g2.drawLine((int)rturn.xInt, (int)rturn.yInt, (int)rturn.xInt, 0);

      g2.setColor(Color.BLACK);
      //line for skiring seperating floor and walls
      g2.drawLine(0, bottom, (int)lMax.xInt, (int)lMax.yInt);
      g2.drawLine(windowWidth, bottom, (int)rMax.xInt, (int)rMax.yInt);
      
      

      drawWallLines(intersectionsL, intersectionsR, g2);

      //g2.drawImage(img3, 0, 0, null);//draws scroll image on tile
      //g2.drawImage(image, 0, 0, null);
      //g2.drawImage(image, xT, yT, null);
      drawItem(image, tiles, g2);
      return;
    }


    if(game.wallForward() && game.wallRight() && game.wallLeft()) {
      System.out.println("dead end corner");
      g2.fillPolygon(leftWall);
      g2.fillPolygon(rightWall);
      g2.fillPolygon(backWallT);
      //}

      //g2.drawLine(x1, y1, x2, y2);
      g2.setColor(Color.GRAY);  
      //draws back wall for right turn
      //g2.fillPolygon(backWallRT);//took out to fix render
      g2.setColor(Color.BLACK);
      //draws lines for backWallLT
      //g2.drawLine((int)lMax.xInt, (int)lMax.yInt, (int)lMax.xInt, 0);
      //g2.drawLine((int)rMax.xInt, (int)rMax.yInt, (int)rMax.xInt, 0);

      g2.setColor(Color.BLACK); 

      //line for wall turn
      g2.drawLine((int)rturn.xInt, (int)rturn.yInt, (int)rturn.xInt, 0);

      g2.setColor(Color.BLACK);
      //line for skiring seperating floor and walls
      g2.drawLine(0, bottom, (int)lMax.xInt, (int)lMax.yInt);
      g2.drawLine(windowWidth, bottom, (int)rMax.xInt, (int)rMax.yInt);

      drawWallLines(intersectionsL, intersectionsR, g2);
      //g2.drawImage(image, 0, 0, null);
      //g2.drawImage(image, (int)last.v2.xInt, (int)last.v2.yInt, null);
      drawItem(image, tiles, g2);
      
      return;
    }

    //end of wall is on the right
    if(game.wallForward() && game.wallRight()) {
      System.out.println("end wall is on right");
      g2.setColor(Color.BLACK);
      //line for skiring seperating floor and walls
      g2.drawLine(0, bottom, (int)lMax.xInt, (int)lMax.yInt);
      g2.drawLine(windowWidth, bottom, (int)rMax.xInt, (int)rMax.yInt);
      g2.setColor(Color.RED);
      g2.fillPolygon(backWallT);//draws back wall all along display
      g2.fillPolygon(rightWall);

      drawWallLines(intersectionsL, intersectionsR, g2);
      //g2.drawImage(image, 0, 0, null);
      drawItem(image, tiles, g2);
      return;
    }

    //end of wall is on the left
    if(game.wallForward() && game.wallLeft()) {//&& game.hasLeftCorner() == false
      System.out.println("end wall is on left");
      g2.setColor(Color.BLACK);
      //line for skiring seperating floor and walls
      g2.drawLine(0, bottom, (int)lMax.xInt, (int)lMax.yInt);
      g2.drawLine(windowWidth, bottom, (int)rMax.xInt, (int)rMax.yInt);
      g2.setColor(Color.RED);
      g2.fillPolygon(backWallT);//draws back wall all along display
      g2.fillPolygon(leftWall);

      drawWallLines(intersectionsL, intersectionsR, g2);
      //g2.drawImage(image, 0, 0, null);
      //g2.drawImage(image, xT, yT, null);
      drawItem(image, tiles, g2);
      return;
    }

    if(game.wallForward() && game.wallBehind()) {//will draw a hallway wall when your're facing it
      System.out.println("wall mid hall");
      g2.setColor(Color.BLACK);
      //line for skiring seperating floor and walls
      g2.drawLine(0, bottom, (int)lMax.xInt, (int)lMax.yInt);
      g2.drawLine(windowWidth, bottom, (int)rMax.xInt, (int)rMax.yInt);
      g2.setColor(Color.BLUE);
      g2.fillPolygon(backWallT);//draws back wall all along display

      drawWallLines(intersectionsL, intersectionsR, g2);
      //g2.drawImage(image, 0, 0, null);
      //g2.drawImage(image, xT, yT, null);
      //drawItem(image, tiles, g2);
      return;
    }

    //draws right corner turns

    //draws back wall last to cover horizon guide lines
    g2.setColor(Color.RED);  
    //g2.fillPolygon(backWall);

    if(game.hasLeftCorner() == false && game.hasRightCorner()){
      g2.setColor(Color.BLACK);
      g2.drawLine((int)lMax.xInt, (int)lMax.yInt, 0, (int)lMax.yInt);//draws line seperating back wall from floor
      g2.setColor(Color.GREEN);
      g2.fillPolygon(backWallT);
      g2.fillPolygon(leftWall);//call to draw left turn
      g2.fillPolygon(rightWall);


      g2.setColor(Color.BLACK); 

      //line for wall turn
      g2.drawLine((int)rturn.xInt, (int)rturn.yInt, (int)rturn.xInt, 0);

      g2.setColor(Color.BLACK);
      //line for skiring seperating floor and walls
      g2.drawLine(0, bottom, (int)lMax.xInt, (int)lMax.yInt);
      g2.drawLine(windowWidth, bottom, (int)rMax.xInt, (int)rMax.yInt);

      drawWallLines(intersectionsL, intersectionsR, g2);

      //g2.drawImage(image, 0, 0, null);//draws scroll image on tile
      //g2.drawImage(image, xT, yT, null);
      drawItem(image, tiles, g2);
      return;
    }
    
    if(game.hasLeftCorner() == false && game.hasRightCorner() == false) {
      System.out.println("skipped but caught");
    }

    System.out.println("skipped all if's");
    g2.fillPolygon(leftWall);
    g2.fillPolygon(rightWall);
    g2.fillPolygon(backWallT);
    //}

    //g2.drawLine(x1, y1, x2, y2);
    g2.setColor(Color.GRAY);  
    //draws back wall for right turn
    //g2.fillPolygon(backWallRT);//took out to fix render
    g2.setColor(Color.BLACK);
    //draws lines for backWallLT
    //g2.drawLine((int)lMax.xInt, (int)lMax.yInt, (int)lMax.xInt, 0);
    //g2.drawLine((int)rMax.xInt, (int)rMax.yInt, (int)rMax.xInt, 0);

    g2.setColor(Color.BLACK); 

    //line for wall turn
    g2.drawLine((int)rturn.xInt, (int)rturn.yInt, (int)rturn.xInt, 0);

    g2.setColor(Color.BLACK);
    //line for skiring seperating floor and walls
    g2.drawLine(0, bottom, (int)lMax.xInt, (int)lMax.yInt);
    g2.drawLine(windowWidth, bottom, (int)rMax.xInt, (int)rMax.yInt);

    for(Intersect i : intersectionsL) {
      g2.setColor(Color.BLACK);
      g2.drawLine((int)i.xInt, (int)i.yInt, (int)i.xInt, 0);
    }

    for(Intersect i : intersectionsR) {
      g2.setColor(Color.BLACK);
      g2.drawLine((int)i.xInt, (int)i.yInt, (int)i.xInt, 0);
    }

    //g2.drawImage(img, 0, 0, null);//draws key image on tile
    //g2.drawImage(img2, 0, 0, null);//draws scroll image on tile
    //g2.drawImage(bi3, 0, 0, null);//draws scroll image on tile
    //g2.drawImage(key, 0, 0, null);
  }

  public void drawWallLines(ArrayList<Intersect> r, ArrayList<Intersect> l, Graphics2D g) {
    g.setColor(Color.BLACK);
    for(Intersect i : l) {
      g.setColor(Color.BLACK);
      g.drawLine((int)i.xInt, (int)i.yInt, (int)i.xInt, 0);
    }

    for(Intersect i : r) {
      g.setColor(Color.BLACK);
      g.drawLine((int)i.xInt, (int)i.yInt, (int)i.xInt, 0);
    }
  }
  
  public void drawItem(Image image, ArrayList<Tile> tiles, Graphics2D g) {
    Tile targetTile = tiles.get(tiles.size()-1);
    int tileWidth = (int)targetTile.v3.xInt -  (int)targetTile.v1.xInt;
    int x = (int)targetTile.v1.xInt + (tileWidth/2) - 20;
    int tileHeight = (int)targetTile.v2.yInt - (int)targetTile.v1.yInt + 20;
    int y = (int)targetTile.v2.yInt - (tileHeight/2);
    g.drawImage(image, x, y, null);
  }

  public static Image loadImage(String fileName) {
    java.net.URL imageUrl = Render.class.getResource("images/" + fileName);
    System.out.println("imageURL: " + imageUrl.toString());
    Image img;
    try {
      img = ImageIO.read(imageUrl);
      return img;
    } catch (IOException e) {

      e.printStackTrace();
      throw new RuntimeException("Unable to load image" + fileName);
    }
  }

  public BufferedImage toBufferedImage(Image img){
    if (img instanceof BufferedImage)
    {
      return (BufferedImage) img;
    }

    // Create a buffered image with transparency
    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

    // Draw the image on to the buffered image
    Graphics2D bGr = bimage.createGraphics();
    bGr.drawImage(img, 0, 0, null);
    bGr.dispose();

    // Return the buffered image
    return bimage;
  }

  public BufferedImage scale(BufferedImage imageToScale, int dWidth, int dHeight) {
    BufferedImage scaledImage = null;
    if (imageToScale != null) {
      scaledImage = new BufferedImage(dWidth, dHeight, imageToScale.getType());
      Graphics2D graphics2D = scaledImage.createGraphics();
      graphics2D.drawImage(imageToScale, 0, 0, dWidth, dHeight, null);
      graphics2D.dispose();
    }
    return scaledImage;
  }

}

class Vertex {
  int x;
  int y;
  int z;

  Vertex(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  public void drawOval(Graphics2D g) {
    g.setColor(Color.BLUE);
    g.fillOval(this.x, this.y, 2, 2);
  }
}

class Tile {
  Intersect v1;
  Intersect v2;
  Intersect v3;
  Intersect v4;
  Color color;
  Tile( Intersect v1,  Intersect v2,  Intersect v3,  Intersect v4, Color color) {
    this.v1 = v1;
    this.v2 = v2;
    this.v3 = v3;
    this.v4 = v4;
    this.color = Color.GRAY;
  } 
}

class Wall{
  Intersect v1;
  Intersect v2;
  Intersect v3;
  Intersect v4;
  Color color;
  Wall( Intersect v1,  Intersect v2,  Intersect v3,  Intersect v4, Color color) {
    this.v1 = v1;
    this.v2 = v2;
    this.v3 = v3;
    this.v4 = v4;
    this.color = Color.DARK_GRAY;
  } 
}

class Edge {

  Vertex v1;
  Vertex v2;
  double slope;
  double offset;

  Edge(Vertex v1, Vertex v2){
    this.v1 = v1;
    this.v2 = v2;
  }

  public void drawLine(Graphics2D g) {
    g.setColor(Color.BLUE);
    g.drawLine(v1.x, v1.y, v2.x, v2.y);
  }

  public double findGradient() {
    if((v2.x - v1.x) == 0) {
      return 0;
    }
    double x = (v2.x - v1.x);
    double y = (v2.y - v1.y);
    double gradient = y/x;
    return this.slope = gradient;
  }

  public double findOffset() {
    double offset = v1.y - (v1.x * this.slope);
    return this.offset = offset;
  }

  public Intersect IntersectX(Edge other) {
    double xInt = (other.offset - this.offset) /(this.slope-other.slope);
    double yInt = this.slope * xInt + this.offset;
    Intersect intersect = new Intersect(xInt, yInt);

    return intersect;
  }

}

class Intersect{
  double xInt;
  double yInt;

  Intersect(double xInt, double yInt){
    this.xInt = xInt;
    this.yInt = yInt;
  }
}