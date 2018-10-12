package Renderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Render {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Renderer");
        
        // panel to display render results
        
        
        JPanel renderPanel = new JPanel() {
          
                public void paintComponent(Graphics g) {
                  super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(Color.BLACK);
                    g2.fillRect(0, 0, getWidth(), getHeight());
                  
                    
                    int edgeWidth = 50;
                    int tileHeight = 50;
                    int bottom = getHeight();
                    int mid = bottom/2;
                    int y = bottom;
                    int stepsToWall = 0;
                    int stepsToWallPolys = stepsToWall;
                    ArrayList<Edge> horizontalEdges = new ArrayList<Edge>();
                    for(int j = bottom; j > getHeight()/2; j--) {
                      y = y/2;
                      stepsToWall--;
                      if(stepsToWall < 0) break;
                      //if(y < 0.3) break;
                      System.out.println("y: " + y);
                      Vertex lil = new Vertex(0, y, 0);
                      Vertex uzi = new Vertex(getWidth(),  y, 0);
                      Edge e = new Edge(lil, uzi);
                      horizontalEdges.add(e);
                      e.drawLine(g2);
                      
                    }
                    
                    for(Edge e : horizontalEdges) {
                      e.v1.drawOval(g2);
                      e.v2.drawOval(g2);
                    }
                    
                    Vertex horizon = new Vertex(getWidth()/2, 0, 0);
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
                    Vertex bottomR = new Vertex(getWidth(),bottom,0);
                    Edge left = new Edge(bottomL, horizon);
                    Edge right = new Edge(bottomR, horizon);
                    left.drawLine(g2);
                    right.drawLine(g2);
                    verticalEdges.add(left);
                    verticalEdges.add(right);
                    
                  
            
                  
                  
                  
                    ArrayList<Intersect> intersectionsL = new ArrayList<Intersect>();
                    ArrayList<Intersect> intersectionsR = new ArrayList<Intersect>();
                    //adding first two points on bottom left and right of screen to make first tile
                    Intersect bottomLeft = new Intersect(0, getHeight());
                    intersectionsL.add(bottomLeft);
                    Intersect bottomRight = new Intersect(getWidth(), getHeight());
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
                    System.out.println("Polygon created");
                  }
                  
                  // drawing tile/polygon objects 
                  for(Polygon p : polygons) {
                    System.out.println("Polygons");
                    System.out.println("Polygon: " + p.toString());
                    g2.setColor(Color.GRAY);
                    g2.fillPolygon(p);
                  }
                  
                  
                  
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
                  System.out.println("max is: " + lMax.yInt);
                  System.out.println("max should be: " + intersectionsL.get(stepsToWallPolys).yInt);
                  
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
                  //Vertex topWallvertexL = new Vertex((int)lMax.xInt, 0, 0);
                  Intersect topWL = new Intersect((int)lMax.xInt, 0);
                  intersectionsL.add(topWL);
                  backWall.addPoint((int)topWL.xInt, 0);
                  Intersect topWR = new Intersect((int)rMax.xInt, 0);
                  intersectionsR.add(topWR);
                  backWall.addPoint((int)topWR.xInt, 0);
                  g2.setColor(Color.GRAY);
                  //g2.fillPolygon(backWall);
                  
                  for(int i = 0; i < intersectionsL.size(); i++) {
                    Intersect i1 = intersectionsL.get(i);
                    Intersect i2 = intersectionsR.get(i);
                    g2.setColor(Color.BLACK);
                    g2.drawLine((int)i1.xInt, (int)i1.yInt, (int)i2.xInt, (int)i2.yInt);
                  }
                  
                  //creates polygon for back wall with right turn and florr
                  Polygon backWallRT = new Polygon();
                  backWallRT.addPoint((int)lMax.xInt, (int)lMax.yInt);
                  backWallRT.addPoint(getWidth(), (int)lMax.yInt);
                  backWallRT.addPoint(getWidth(), 0);
                  backWallRT.addPoint((int)topWL.xInt, 0);
                  //g2.setColor(Color.GREEN);
                  //g2.fillPolygon(backWallLT);
                  
                  
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
                  
                  g2.setColor(Color.GREEN);   
                  //g2.drawLine((int)rturn.xInt, (int)rturn.yInt, getWidth(), (int)rturn.xInt);
                  g2.drawLine((int)rturn.xInt, (int)rturn.yInt, (int)rturn.xInt, 0);
                  //draws floor for right turn
                  Polygon floorRT = new Polygon();
                  floorRT.addPoint((int)rMax.xInt, (int)rMax.yInt);
                  floorRT.addPoint((int)rturn.xInt, (int)rturn.yInt);
                  floorRT.addPoint(getWidth(), (int)rturn.yInt);
                  floorRT.addPoint(getWidth(), (int)rMax.yInt);
                  g2.setColor(Color.GRAY);
                  g2.fillPolygon(floorRT);
                  //draws line seperting floor from cieling along back wall
                  g2.setColor(Color.BLACK);
                  g2.drawLine((int)rMax.xInt, (int)rMax.yInt, getWidth(), (int)rMax.yInt);
                  
                  Intersect lturn = intersectionsL.get(stepsToWallPolys-1);
                  g2.setColor(Color.GREEN); 
                  g2.drawLine((int)lturn.xInt, (int)lturn.yInt, (int)lturn.xInt, 0);

                  
                  
                  //draws right wall w/ right turn
                  Polygon leftTurn = new Polygon();
                  
                  leftTurn.addPoint((int)rturn.xInt, (int)rturn.yInt);
                  leftTurn.addPoint(getWidth(), bottom);
                  leftTurn.addPoint(getWidth(), 0);
                  leftTurn.addPoint((int)rturn.xInt, 0);
                  g2.setColor(Color.GRAY);
                  g2.fillPolygon(leftTurn);
                  
                  /**
                  //draws right wall
                  Polygon rightWall = new Polygon();
                  rightWall.addPoint(getWidth(), 0);
                  rightWall.addPoint((int)topWR.xInt, (int)topWR.yInt);
                  rightWall.addPoint((int)rMax.xInt, (int)rMax.yInt);
                  rightWall.addPoint(getWidth(), bottom);
                  
                  
                  
                  g2.setColor(Color.GRAY);
                  g2.fillPolygon(rightWall);
                  left.drawLine(g2);
                  right.drawLine(g2);
                  */
                  
                  
                  //draws back wall last to cover horizon guide lines
                  g2.fillPolygon(backWall);
                  //g2.drawLine(x1, y1, x2, y2);
                  g2.setColor(Color.GRAY);  
                  //draws back wall for right turn
                  g2.fillPolygon(backWallRT);
                  g2.setColor(Color.BLACK);
                  //draws lines for backWallLT
                  g2.drawLine((int)lMax.xInt, (int)lMax.yInt, (int)lMax.xInt, 0);
                  //g2.drawLine((int)rMax.xInt, (int)rMax.yInt, (int)rMax.xInt, 0);
                  
                  g2.setColor(Color.BLACK); 
                  
                  //line for wall turn
                  g2.drawLine((int)rturn.xInt, (int)rturn.yInt, (int)rturn.xInt, 0);
                  
                  g2.setColor(Color.BLACK);
                  //line for skiring seperating floor and walls
                  g2.drawLine(0, bottom, (int)lMax.xInt, (int)lMax.yInt);
                  g2.drawLine(getWidth(), bottom, (int)rMax.xInt, (int)rMax.yInt);
                  
                  for(Intersect i : intersectionsL) {
                    g2.setColor(Color.BLACK);
                    g2.drawLine((int)i.xInt, (int)i.yInt, (int)i.xInt, 0);
                  }
                  
                  for(Intersect i : intersectionsR) {
                    g2.setColor(Color.BLACK);
                    g2.drawLine((int)i.xInt, (int)i.yInt, (int)i.xInt, 0);
                  }
         
                }
                
            };
            
        frame.add(renderPanel);

        frame.setSize(400, 400);
        frame.setVisible(true);
        
        
        
        
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
    System.out.println("co-ords v1: " + v1.x + " : " + v1.y);
    System.out.println("co-ords v2: " + v2.x + " : " + v2.y);
    System.out.println("y calcs: " + (v2.y - v1.y));
    System.out.println("x calcs: " + (v2.x - v1.x));
    System.out.println("gradient of edge: " + gradient);
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