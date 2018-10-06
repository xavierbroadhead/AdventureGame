package GameWorld;

public class Map {

  private Position[][] map;

  public Map(Position[][] map) {
    this.map = map;
  }

  public Position[][] getMap() {
    return map;
  }

  public void setMap(Position[][] map) {
    this.map = map;
  }
}
