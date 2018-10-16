package MapEditor;

import GameWorld.Book;
import GameWorld.Game;
import GameWorld.Key;
import GameWorld.Map;
import GameWorld.Player;
import GameWorld.Position;
import Parser.Parser;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;



/**
 * The Map Editor Class that handles editing the map when in play.
 * @author Christian Lee 300417339
 */
public class MapEditor extends javax.swing.JFrame {

  /**
   * Declaration to remove error that does not change any functionality. 
   */
  private static final long serialVersionUID = 1L;
  
  // MapEditor variables
  private Parser parser;
  private Game originalGame;
  private ArrayList<JButton> buttonList;
  private ArrayList<String[][]> rooms;
  private javax.swing.JButton jbutton1;
  private javax.swing.JButton jbutton2;
  private javax.swing.JButton jbutton3;
  private javax.swing.JButton jbutton4;
  private javax.swing.JButton jbutton5;
  private javax.swing.JButton jbutton6;
  private javax.swing.JButton jbutton7;
  private javax.swing.JButton jbutton8;
  private javax.swing.JButton jbutton9;
  private javax.swing.JButton jbutton10;
  private javax.swing.JButton jbutton11;
  private javax.swing.JButton jbutton12;
  private javax.swing.JButton jbutton13;
  private javax.swing.JButton jbutton14;
  private javax.swing.JButton jbutton15;
  private javax.swing.JButton jbutton16;
  private javax.swing.JButton jbutton17;
  private javax.swing.JButton jbutton18;
  private javax.swing.JButton jbutton19;
  private javax.swing.JButton jbutton20;
  private javax.swing.JButton jbutton21;
  private javax.swing.JButton jbutton22;
  private javax.swing.JButton jbutton23;
  private javax.swing.JButton jbutton24;
  private javax.swing.JButton jbutton25;
  private javax.swing.JComboBox<String> jcomboBox1;
  private javax.swing.JComboBox<String> jcomboBox2;
  private javax.swing.JLabel jlabel1;
  private javax.swing.JLabel jlabel2;
  private javax.swing.JMenu jmenu1;
  private javax.swing.JMenuBar jmenuBar1;
  private javax.swing.JMenuItem jmenuItem1;
  private javax.swing.JMenuItem jmenuItem2;
  private javax.swing.JPanel jpanel1;
  private javax.swing.JFileChooser jfileChooserLoad;
  private javax.swing.JFileChooser jfileChooserSave;

  /**
   * Creates new form MapEditor using a pre-existing game.
   * 
   * @param game
   *          that is the game currently being played.
   */
  public MapEditor(Game game) {
    this.originalGame = game;
    initComponents();
    this.rooms = convertGameToString();
  }
  
  /**
   * Saves the new modified game state into the existing game. This converts the
   * string array into a Game object which is the original game.
   * void method and takes no arguments.
   */
  public void saveGame() {
  
    GameWorld.Position[][] map1 = this.originalGame.getMaps().get(1).getMap();
    GameWorld.Position[][] map2 = this.originalGame.getMaps().get(2).getMap();
    GameWorld.Position[][] map3 = this.originalGame.getMaps().get(3).getMap();
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        
        //Save the contents in the first map
        if (rooms.get(0)[i][j].equals("Wall")) {
          map1[i][j] = null;
        } else if (rooms.get(0)[i][j].equals("Key")) {
          Position pos = new Position(i, j);
          pos.addItem(new Key(1, 1, "This is a key", "Key", 1, 1, new ImageIcon()));
          map1[i][j] = pos;
        } else if (rooms.get(0)[i][j].equals("Book")) {
          Position pos = new Position(i, j);
          pos.addItem(new Book(1, 1, "This is a book", "Book", 1, true, "Book", new ImageIcon()));
          map1[i][j] = pos;
        } else {
          map1[i][j] = new Position(i, j);
        }

        //Save the contents in the second map
        if (rooms.get(1)[i][j].equals("Wall")) {
          map2[i][j] = null;
        } else if (rooms.get(1)[i][j].equals("Key")) {
          Position pos = new Position(i, j);
          pos.addItem(new Key(1, 1, "This is a key", "Key", 2, 1, new ImageIcon()));
          map2[i][j] = pos;
        } else if (rooms.get(1)[i][j].equals("Book")) {
          Position pos = new Position(i, j);
          pos.addItem(new Book(1, 1, "This is a book", "Book", 2, true, "Book", new ImageIcon()));
          map2[i][j] = pos;
        } else {
          map2[i][j] = new Position(i, j);
        }

        //Save the contents in the third map
        if (rooms.get(2)[i][j].equals("Wall")) {
          map3[i][j] = null;
        } else if (rooms.get(0)[i][j].equals("Key")) {
          Position pos = new Position(i, j);
          pos.addItem(new Key(1, 1, "This is a key", "Key", 2, 1, new ImageIcon()));
          map3[i][j] = pos;
        } else if (rooms.get(0)[i][j].equals("Book")) {
          Position pos = new Position(i, j);
          pos.addItem(new Book(1, 1, "This is a book", "Book", 2, true, "Book", new ImageIcon()));
          map3[i][j] = pos;
        } else {
          map3[i][j] = new Position(i, j);
        }
      }
    }
    HashMap<Integer, Map> newMaps = new HashMap<Integer, Map>();
    newMaps.put(1, new Map(map1));
    newMaps.put(2, new Map(map2));
    newMaps.put(3, new Map(map3));

    //Put in keys doors and books
    this.originalGame.setMaps(newMaps);

  }
   
  /**
   * Takes a Game object and converts it into a string array so that modifications
   * can be made more easily as positions can be more specific due to game logic .
   * void method and takes no arguments.
  */
  public ArrayList<String[][]> convertGameToString() {
    
    // Create the room 2D arrays that correspond to the Game maps as a template
    String[][] room1 = new String[5][5];
    String[][] room2 = new String[5][5];
    String[][] room3 = new String[5][5];
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        room1[i][j] = "Empty";
        room2[i][j] = "Empty";
        room3[i][j] = "Empty";
      }
    }
    ArrayList<String[][]> rooms = new ArrayList<String[][]>();
    rooms.add(room1);
    rooms.add(room2);
    rooms.add(room3);

    GameWorld.Position[][] map1 = this.originalGame.getMaps().get(1).getMap();
    GameWorld.Position[][] map2 = this.originalGame.getMaps().get(2).getMap();
    GameWorld.Position[][] map3 = this.originalGame.getMaps().get(3).getMap();
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (map1[i][j] == null) {
          rooms.get(0)[i][j] = "Wall";
        } else {
          if (map1[i][j].getDoor() != null) {
            int link = map1[i][j].getDoor().getLink();
            rooms.get(0)[i][j] = "DoorTo" + link;
          } else if (map1[i][j].getItem() != null) {
            rooms.get(0)[i][j] = map1[i][j].getItem().toString();
          } else {
            rooms.get(0)[i][j] = "Empty";
          }
        }
        if (map2[i][j] == null) {
          rooms.get(1)[i][j] = "Wall";
        } else {
          if (map2[i][j].getDoor() != null) {
            int link = map2[i][j].getDoor().getLink();
            rooms.get(1)[i][j] = "DoorTo" + link;
          } else if (map2[i][j].getItem() != null) {
            rooms.get(1)[i][j] = map2[i][j].getItem().toString();
          } else {
            rooms.get(1)[i][j] = "Empty";
          }
        }
        if (map3[i][j] == null) {
          rooms.get(2)[i][j] = "Wall";
        } else {
          if (map3[i][j].getDoor() != null) {
            int link = map3[i][j].getDoor().getLink();
            rooms.get(2)[i][j] = "DoorTo" + link;
          } else if (map3[i][j].getItem() != null) {
            rooms.get(2)[i][j] = map3[i][j].getItem().toString();
          } else {
            rooms.get(2)[i][j] = "Empty";
          }
        }
      }
    }
    
    return rooms;
  }

  /**
   * This outputs the corresponding button value for a given item.
   * 
   * @param item is the game item at that position on the board.
   * @return String The value that the button represents given an item in that
   *         spot
   */
  private String buttonChanger(String item) {
    String input = "";
    if (item.equals("Wall")) {
      input += "W";
    } else if (item.equals("Empty")) {
      input += "E";
    } else if (item.equals("Key")) {
      input += "K";
    } else if (item.equals("Book")) {
      input += "B";
    } else if (item.equals("Start")) {
      input += "S";
    } else if (item.equals("End")) {
      input += "ED";
    } else if (item.equals("DoorTo1")) {
      input += "D1";
    } else if (item.equals("DoorTo2")) {
      input += "D2";
    } else if (item.equals("DoorTo3")) {
      input += "D3";
    } else if (item.equals("DoorTo4")) {
      input += "D4";
    } else if (item.equals("DoorTo5")) {
      input += "D5";
    }
    return input;

  }

  /**
   * This method changes the value on a button and also in the array that holds
   * the game values in string format.
   * 
   * @param button
   *          The button that is being changed for this call
   * @param x
   *          The value of the first array position
   * @param y
   *          The value of the second array position
   * @param rooms
   *          The string array that is getting updated
   */
  private void buttonChanger(JButton button, int x, int y, ArrayList<String[][]> rooms) {
    String room = (String) jcomboBox2.getSelectedItem();
    String item = (String) jcomboBox1.getSelectedItem();
    int roomNum = (room.charAt(5) - '0') - 1;
    rooms.get(roomNum)[x][y] = item;
    String buttonText = buttonChanger(item);
    button.setText(buttonText);
    saveGame();
  }

  /**
   * The method updates the string value of the buttons when the room combobox is
   * changed.
   * 
   * @param buttonList
   *          This is a list of all of the buttons on the screen that can be
   *          clicked
   * @param rooms
   *          The string array that is getting its values checked and then shown
   *          in the buttons
   */
  private void resetJButtonValues(ArrayList<JButton> buttonList, ArrayList<String[][]> rooms) {
    int count = 0;
    for (JButton jbut : buttonList) {
      int y = count % 5;
      int x = count / 5;
      String room = (String) jcomboBox2.getSelectedItem();
      int roomNum = (room.charAt(5) - '0') - 1;
      String text = rooms.get(roomNum)[x][y];
      String buttonText = buttonChanger(text);
      jbut.setText(buttonText);

      count++;
    }
  }

  

  /**
   * This method is called from within the constructor to initialize the GUI
   * components.
   */
  private void initComponents() {
    
    // Create all of the variables.
    parser = new Parser();
    jpanel1 = new javax.swing.JPanel();
    jcomboBox1 = new javax.swing.JComboBox<>();
    jbutton1 = new javax.swing.JButton();
    jbutton2 = new javax.swing.JButton();
    jbutton3 = new javax.swing.JButton();
    jbutton4 = new javax.swing.JButton();
    jbutton5 = new javax.swing.JButton();
    jbutton6 = new javax.swing.JButton();
    jbutton7 = new javax.swing.JButton();
    jbutton8 = new javax.swing.JButton();
    jbutton9 = new javax.swing.JButton();
    jbutton10 = new javax.swing.JButton();
    jbutton11 = new javax.swing.JButton();
    jbutton12 = new javax.swing.JButton();
    jbutton13 = new javax.swing.JButton();
    jbutton14 = new javax.swing.JButton();
    jbutton15 = new javax.swing.JButton();
    jbutton16 = new javax.swing.JButton();
    jbutton17 = new javax.swing.JButton();
    jbutton18 = new javax.swing.JButton();
    jbutton19 = new javax.swing.JButton();
    jbutton20 = new javax.swing.JButton();
    jbutton21 = new javax.swing.JButton();
    jbutton22 = new javax.swing.JButton();
    jbutton23 = new javax.swing.JButton();
    jbutton24 = new javax.swing.JButton();
    jbutton25 = new javax.swing.JButton();
    jcomboBox2 = new javax.swing.JComboBox<>();
    jlabel1 = new javax.swing.JLabel();
    jlabel2 = new javax.swing.JLabel();
    jmenuBar1 = new javax.swing.JMenuBar();
    jmenu1 = new javax.swing.JMenu();
    jmenuItem1 = new javax.swing.JMenuItem();
    jmenuItem2 = new javax.swing.JMenuItem();
    jfileChooserLoad = new javax.swing.JFileChooser();
    jfileChooserSave = new javax.swing.JFileChooser();
    
    // Create button list for easy access to all buttons
    ArrayList<JButton> buttonList = new ArrayList<JButton>();
    buttonList.add(jbutton1);
    buttonList.add(jbutton2);
    buttonList.add(jbutton3);
    buttonList.add(jbutton4);
    buttonList.add(jbutton5);
    buttonList.add(jbutton6);
    buttonList.add(jbutton7);
    buttonList.add(jbutton8);
    buttonList.add(jbutton9);
    buttonList.add(jbutton10);
    buttonList.add(jbutton11);
    buttonList.add(jbutton12);
    buttonList.add(jbutton13);
    buttonList.add(jbutton14);
    buttonList.add(jbutton15);
    buttonList.add(jbutton16);
    buttonList.add(jbutton17);
    buttonList.add(jbutton18);
    buttonList.add(jbutton19);
    buttonList.add(jbutton20);
    buttonList.add(jbutton21);
    buttonList.add(jbutton22);
    buttonList.add(jbutton23);
    buttonList.add(jbutton24);
    buttonList.add(jbutton25);

    // Ensure the ApplicationWindow GUI does not close when this GUI is closed.
    setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

    jcomboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { 
        "Wall", "Empty", "Key", "Book", "Start", "End", 
        "DoorTo1", "DoorTo2", "DoorTo3", "DoorTo4", "DoorTo5" }));
    jcomboBox1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        resetJButtonValues(buttonList, rooms);
      }
    });

    jcomboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { 
        "Room 1", "Room 2", "Room 3" }));
    jcomboBox2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        resetJButtonValues(buttonList, rooms);
      }
    });

    jbutton1.setText("00");
    jbutton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton1, 0, 0, rooms);
      }
    });

    jbutton2.setText("01");
    jbutton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton2, 0, 1, rooms);
      }
    });

    jbutton3.setText("02");
    jbutton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton3, 0, 2, rooms);
      }
    });

    jbutton4.setText("03");
    jbutton4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton4, 0, 3, rooms);
      }
    });

    jbutton5.setText("04");
    jbutton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton5, 0, 4, rooms);
      }
    });

    jbutton6.setText("10");
    jbutton6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton6, 1, 0, rooms);
      }
    });

    jbutton7.setText("11");
    jbutton7.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton7, 1, 1, rooms);
      }
    });

    jbutton8.setText("12");
    jbutton8.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton8, 1, 2, rooms);
      }
    });

    jbutton9.setText("13");
    jbutton9.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton9, 1, 3, rooms);
      }
    });
    
    jbutton10.setText("14");
    jbutton10.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton10, 1, 4, rooms);
      }
    });

    jbutton11.setText("20");
    jbutton11.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton11, 2, 0, rooms);
      }
    });

    jbutton12.setText("21");
    jbutton12.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton12, 2, 1, rooms);
      }
    });

    jbutton13.setText("22");
    jbutton13.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton13, 2, 2, rooms);
      }
    });

    jbutton14.setText("23");
    jbutton14.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton14, 2, 3, rooms);
      }
    });

    jbutton15.setText("24");
    jbutton15.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton15, 2, 4, rooms);
      }
    });

    jbutton16.setText("30");
    jbutton16.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton16, 3, 0, rooms);
      }
    });

    jbutton17.setText("31");
    jbutton17.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton17, 3, 1, rooms);
      }
    });

    jbutton18.setText("32");
    jbutton18.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton18, 3, 2, rooms);
      }
    });

    jbutton19.setText("33");
    jbutton19.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton19, 3, 3, rooms);
      }
    });

    jbutton20.setText("34");
    jbutton20.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton20, 3, 4, rooms);
      }
    });

    jbutton21.setText("40");
    jbutton21.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton21, 4, 0, rooms);
      }
    });

    jbutton22.setText("41");
    jbutton22.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton22, 4, 1, rooms);
      }
    });

    jbutton23.setText("42");
    jbutton23.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton23, 4, 2, rooms);
      }
    });

    jbutton24.setText("43");
    jbutton24.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton24, 4, 3, rooms);
      }
    });

    jbutton25.setText("44");
    jbutton25.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jbutton25, 4, 4, rooms);
      }
    });

    jlabel1.setText("Room ");

    jlabel2.setText("Items");

    javax.swing.GroupLayout jpanel1Layout = new javax.swing.GroupLayout(jpanel1);
    jpanel1.setLayout(jpanel1Layout);
    jpanel1Layout.setHorizontalGroup(jpanel1Layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
            jpanel1Layout.createSequentialGroup().addContainerGap(
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlabel1).addGap(63, 63, 63))
        .addGroup(jpanel1Layout.createSequentialGroup().addContainerGap()
            .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, 
                    jpanel1Layout.createSequentialGroup()
                    .addComponent(jbutton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addComponent(jbutton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addComponent(jbutton3, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addComponent(jbutton4, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2).addComponent(jbutton5).addGap(44, 44, 44)
                    .addComponent(jcomboBox2, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, 
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(19, 19, 19))
                .addGroup(jpanel1Layout.createSequentialGroup()
                    .addGroup(jpanel1Layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jpanel1Layout.createSequentialGroup()
                            .addComponent(jbutton21).addGap(2, 2, 2)
                            .addComponent(jbutton22, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jbutton23, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jbutton24, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2).addComponent(jbutton25))
                        .addGroup(jpanel1Layout.createSequentialGroup()
                            .addComponent(jbutton16).addGap(2, 2, 2)
                            .addComponent(jbutton17, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jbutton18, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jbutton19, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2).addComponent(jbutton20)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcomboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 132,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpanel1Layout.createSequentialGroup()
                    .addGroup(jpanel1Layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpanel1Layout.createSequentialGroup()
                            .addComponent(jbutton11).addGap(2, 2, 2)
                            .addComponent(jbutton12, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jbutton13, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jbutton14, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2).addComponent(jbutton15).addGap(59, 59, 59)
                            .addComponent(jlabel2))
                        .addGroup(jpanel1Layout.createSequentialGroup()
                            .addComponent(jbutton6).addGap(2, 2, 2)
                            .addComponent(jbutton7, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jbutton8, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jbutton9, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2).addComponent(jbutton10)))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap()));
    jpanel1Layout
        .setVerticalGroup(
            jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanel1Layout.createSequentialGroup().addGap(13, 13, 13)
                    .addComponent(jlabel1).addGap(3, 3, 3)
                    .addGroup(jpanel1Layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbutton1).addComponent(jbutton2)
                        .addComponent(jbutton3).addComponent(jbutton4)
                        .addComponent(jbutton5).addComponent(jcomboBox2, 
                            javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, 
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(3, 3, 3)
                    .addGroup(jpanel1Layout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbutton7)
                        .addComponent(jbutton8).addComponent(jbutton9).addComponent(jbutton10)
                        .addComponent(jbutton6))
                    .addGap(3, 3, 3)
                    .addGroup(jpanel1Layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbutton12).addComponent(jbutton13)
                        .addComponent(jbutton14).addComponent(jbutton15)
                        .addComponent(jbutton11).addComponent(jlabel2))
                    .addGap(3, 3, 3)
                    .addGroup(jpanel1Layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbutton17).addComponent(jbutton18)
                        .addComponent(jbutton19).addComponent(jbutton20)
                        .addComponent(jbutton16).addComponent(jcomboBox1, 
                            javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, 
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(3, 3, 3)
                    .addGroup(jpanel1Layout.createParallelGroup(
                        javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbutton22).addComponent(jbutton23)
                        .addComponent(jbutton24).addComponent(jbutton25)
                        .addComponent(jbutton21))
                    .addGap(0, 29, Short.MAX_VALUE)));

    jmenu1.setText("File");

    jmenuItem1.setText("Save Map");
    jmenu1.add(jmenuItem1);

    jmenuItem1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        //Save game using parser to XML File
        saveGameActionPerformed(evt); 
      }
    });

    jmenuItem2.setText("Load Map");
    jmenu1.add(jmenuItem2);

    jmenuItem2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        // Load Game using parser.
        // Get the game and make a MapEditor using the Game class object.
        loadActionPerformed(evt);
      }
    });

    
    jfileChooserLoad.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
    jfileChooserLoad.setApproveButtonToolTipText("");
    jfileChooserLoad.setDialogTitle("Load");
    
    
    jfileChooserSave.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
    jfileChooserSave.setApproveButtonToolTipText("");
    jfileChooserSave.setDialogTitle("Save");
    
    
    jmenuBar1.add(jmenu1);

    setJMenuBar(jmenuBar1);
    

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout
            .createSequentialGroup().addComponent(jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE)));
    layout.setVerticalGroup(layout.createParallelGroup(
        javax.swing.GroupLayout.Alignment.LEADING).addComponent(jpanel1,
        javax.swing.GroupLayout.DEFAULT_SIZE, 
        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

    pack();

  }
  
  /**
   * Opens the file options to save the file on the computer.
   * @param evt that triggers the save file options 
   */
  private void saveGameActionPerformed(java.awt.event.ActionEvent evt) {

    int returnVal = jfileChooserSave.showSaveDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      saveGame();
      File file = jfileChooserSave.getSelectedFile();
      parser.saveToFile(this.originalGame.getMaps(), this.originalGame.getDoors(), 
          this.originalGame.getPlayer(), file);
    } else {
      System.out.println("File access cancelled by user.");
    }

  }

  /**
   * Opens the load options for the game to retrieve a file.
   * @param evt that triggers the load file options
   */
  private void loadActionPerformed(java.awt.event.ActionEvent evt) {
    int returnVal = jfileChooserLoad.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = jfileChooserLoad.getSelectedFile();
      String filename = file.getName();
      Game loadedGame = parser.loadFromFile(filename);
      this.originalGame = loadedGame;
      convertGameToString();
    } else {
      System.out.println("File access cancelled by user.");
    }
  }
  

  /**
   * Runs the Program with dummy game.
   * 
   * @param args
   *          Standard main arguments.
   * 
   */
  public static void main(String[] args) {
    Player player = null;
    Game game = new Game(player);
    MapEditor editor = new MapEditor(game);
    editor.setVisible(true);
  }


}
