/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditor;

import java.util.ArrayList;

import javax.swing.JButton;

import com.sun.xml.internal.ws.dump.LoggingDumpTube.Position;

import GameWorld.Game;
import GameWorld.Map;
import GameWorld.Player;

/**
 *
 * @author Christian Lee
 */
public class MapEditor extends javax.swing.JFrame {
  
  //MapEditor variables
  private Game originalGame;
  private ArrayList<JButton> buttonList;
  private ArrayList<String[][]> rooms;
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JButton jButton3;
  private javax.swing.JButton jButton4;
  private javax.swing.JButton jButton5;
  private javax.swing.JButton jButton6;
  private javax.swing.JButton jButton7;
  private javax.swing.JButton jButton8;
  private javax.swing.JButton jButton9;
  private javax.swing.JButton jButton10;
  private javax.swing.JButton jButton11;
  private javax.swing.JButton jButton12;
  private javax.swing.JButton jButton13;
  private javax.swing.JButton jButton14;
  private javax.swing.JButton jButton15;
  private javax.swing.JButton jButton16;
  private javax.swing.JButton jButton17;
  private javax.swing.JButton jButton18;
  private javax.swing.JButton jButton19;
  private javax.swing.JButton jButton20;
  private javax.swing.JButton jButton21;
  private javax.swing.JButton jButton22;
  private javax.swing.JButton jButton23;
  private javax.swing.JButton jButton24;
  private javax.swing.JButton jButton25;
  private javax.swing.JComboBox<String> jComboBox1;
  private javax.swing.JComboBox<String> jComboBox2;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JMenu jMenu1;
  private javax.swing.JMenuBar jMenuBar1;
  private javax.swing.JMenuItem jMenuItem1;
  private javax.swing.JMenuItem jMenuItem2;
  private javax.swing.JPanel jPanel1;
  
  
  /**
   * Creates new form MapEditor using a pre-existing game
   * @param game that is the game currently being played.
   */
  public MapEditor(Game game) {
    this.originalGame = game;
    initComponents();
    //convertGameToString();
  }
  
  
  /**
   * Saves the new modified game state into the existing game.
   * This converts the string array into a Game object which is the original game.
   * 
   * void method and takes no arguments.
   */
  public void saveGame() {
    //Convert rooms string back into a game and assign to this.originalGame
  }
  
  
  /**
   * Takes a Game object and converts it into a string array so that modifications can be made more 
   * easily as positions can be more specific due to game logic
   * .
   * void method and takes no arguments.
   */
  public void convertGameToString(){
    GameWorld.Position[][] map1= this.originalGame.getMaps().get(1).getMap();
    GameWorld.Position[][] map2= this.originalGame.getMaps().get(2).getMap();
    GameWorld.Position[][] map3= this.originalGame.getMaps().get(3).getMap();
    for(int i=0; i<5; i++) {
      for(int j=0; j<5; j++) {  
        if(map1[i][j] != null) {
          rooms.get(0)[i][j] = "Wall";
        }else {
          rooms.get(0)[i][j] = "Empty";
        }
        if(map2[i][j] != null) {
          rooms.get(1)[i][j] = "Wall";
        }else {
          rooms.get(1)[i][j] = "Empty";
        }
        if(map3[i][j] != null) {
          rooms.get(2)[i][j] = "Wall";
        }else {
          rooms.get(2)[i][j] = "Empty";
        }
      }
    }
    
    

  }
  
  
  
  /**
   * This outputs the corresponding button value for a given item.
   * @param item
   * @return String The value that the button represents given an item in that spot
   */
  public String buttonChanger(String item) {
    String input = "";
    if (item.equals("Wall")) {
      input += "W";
    } else if (item.equals("Empty")) {
      input += "E";
    } else if (item.equals("Key")) {
      input += "K";
    } else if (item.equals("Start")) {
      input += "S";
    } else if (item.equals("End")) {
      input += "ED";
    } else if (item.equals("RoomEnterPos")) {
      input += "EP";
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
   * @param button The button that is being changed for this call
   * @param x The value of the first array position
   * @param y The value of the second array position
   * @param rooms The string array that is getting updated
   */
  public void buttonChanger(JButton button, int x, int y, ArrayList<String[][]> rooms) {
    String room = (String) jComboBox2.getSelectedItem();
    String item = (String) jComboBox1.getSelectedItem();
    int roomNum = room.charAt(5) - '0';
    rooms.get(roomNum)[x][y] = item;
    String buttonText = buttonChanger(item);
    button.setText(buttonText);
  }

  
  /**
   * The method updates the string value of the buttons when the room combobox is changed
   * @param buttonList This is a list of all of the buttons on the screen that can be clicked
   * @param rooms The string array that is getting its values checked and then shown in the buttons
   */
  private void resetJButtonValues(ArrayList<JButton> buttonList, ArrayList<String[][]> rooms) {
    int count = 0;
    for (JButton jBut : buttonList) {
      int y = count % 5;
      int x = count / 5;
      String room = (String) jComboBox2.getSelectedItem();
      int roomNum = (room.charAt(5) - '0') -1;
      String Text = rooms.get(roomNum)[x][y];
      String buttonText = buttonChanger(Text);
      jBut.setText(buttonText);

      count++;
    }

  }

  /**
   * This method is called from within the constructor to initialize the GUI components.
   */
  private void initComponents() {
    
    //Create all of the variables. 
    jPanel1 = new javax.swing.JPanel();
    jComboBox1 = new javax.swing.JComboBox<>();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();
    jButton4 = new javax.swing.JButton();
    jButton5 = new javax.swing.JButton();
    jButton6 = new javax.swing.JButton();
    jButton7 = new javax.swing.JButton();
    jButton8 = new javax.swing.JButton();
    jButton9 = new javax.swing.JButton();
    jButton10 = new javax.swing.JButton();
    jButton11 = new javax.swing.JButton();
    jButton12 = new javax.swing.JButton();
    jButton13 = new javax.swing.JButton();
    jButton14 = new javax.swing.JButton();
    jButton15 = new javax.swing.JButton();
    jButton16 = new javax.swing.JButton();
    jButton17 = new javax.swing.JButton();
    jButton18 = new javax.swing.JButton();
    jButton19 = new javax.swing.JButton();
    jButton20 = new javax.swing.JButton();
    jButton21 = new javax.swing.JButton();
    jButton22 = new javax.swing.JButton();
    jButton23 = new javax.swing.JButton();
    jButton24 = new javax.swing.JButton();
    jButton25 = new javax.swing.JButton();
    jComboBox2 = new javax.swing.JComboBox<>();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jMenuBar1 = new javax.swing.JMenuBar();
    jMenu1 = new javax.swing.JMenu();
    jMenuItem1 = new javax.swing.JMenuItem();
    jMenuItem2 = new javax.swing.JMenuItem();

    // Create the room 2D arrays that correspond to the Game maps
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

    //Create button list for easy access to all buttons
    ArrayList<JButton> buttonList = new ArrayList<JButton>();
    buttonList.add(jButton1);
    buttonList.add(jButton2);
    buttonList.add(jButton3);
    buttonList.add(jButton4);
    buttonList.add(jButton5);
    buttonList.add(jButton6);
    buttonList.add(jButton7);
    buttonList.add(jButton8);
    buttonList.add(jButton9);
    buttonList.add(jButton10);
    buttonList.add(jButton11);
    buttonList.add(jButton12);
    buttonList.add(jButton13);
    buttonList.add(jButton14);
    buttonList.add(jButton15);
    buttonList.add(jButton16);
    buttonList.add(jButton17);
    buttonList.add(jButton18);
    buttonList.add(jButton19);
    buttonList.add(jButton20);
    buttonList.add(jButton21);
    buttonList.add(jButton22);
    buttonList.add(jButton23);
    buttonList.add(jButton24);
    buttonList.add(jButton25);

    //Ensure the ApplicationWindow GUI does not close when this GUI is closed.
    setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

    
    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Wall", "Empty", "Key", "Start", "End",
        "RoomEnterPos", "DoorTo1", "DoorTo2", "DoorTo3", "DoorTo4", "DoorTo5" }));
    jComboBox1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {

      }
    });
    
    jComboBox2.setModel(
        new javax.swing.DefaultComboBoxModel<>(new String[] { "Room 1", "Room 2", "Room 3"}));
    jComboBox2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        resetJButtonValues(buttonList, rooms);
      }
    });
    
    jButton1.setText("00");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton1, 0, 0, rooms);
      }
    });
    
    jButton2.setText("01");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton2, 0, 1, rooms);
      }
    });

    jButton3.setText("02");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton3, 0, 2, rooms);
      }
    });
    
    jButton4.setText("03");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton4, 0, 3, rooms);
      }
    });

    jButton5.setText("04");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton5, 0, 4, rooms);
      }
    });
    
    jButton6.setText("10");
    jButton6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton6, 1, 0, rooms);
      }
    });

    jButton7.setText("11");
    jButton7.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton7, 1, 1, rooms);
      }
    });

    jButton8.setText("12");
    jButton8.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton8, 1, 2, rooms);
      }
    });

    jButton9.setText("13");
    jButton9.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton9, 1, 3, rooms);
      }
    });

    jButton10.setText("14");
    jButton10.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton10, 1, 4, rooms);
      }
    });

    jButton11.setText("20");
    jButton11.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton11, 2, 0, rooms);
      }
    });

    jButton12.setText("21");
    jButton12.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton12, 2, 1, rooms);
      }
    });

    jButton13.setText("22");
    jButton13.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton13, 2, 2, rooms);
      }
    });

    jButton14.setText("23");
    jButton14.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton14, 2, 3, rooms);
      }
    });

    jButton15.setText("24");
    jButton15.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton15, 2, 4, rooms);
      }
    });

    jButton16.setText("30");
    jButton16.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton16, 3, 0, rooms);
      }
    });

    jButton17.setText("31");
    jButton17.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton17, 3, 1, rooms);
      }
    });

    jButton18.setText("32");
    jButton18.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton18, 3, 2, rooms);
      }
    });

    jButton19.setText("33");
    jButton19.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton19, 3, 3, rooms);
      }
    });

    jButton20.setText("34");
    jButton20.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton20, 3, 4, rooms);
      }
    });

    jButton21.setText("40");
    jButton21.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton21, 4, 0, rooms);
      }
    });

    jButton22.setText("41");
    jButton22.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton22, 4, 1, rooms);
      }
    });

    jButton23.setText("42");
    jButton23.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton23, 4, 2, rooms);
      }
    });

    jButton24.setText("43");
    jButton24.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton24, 4, 3, rooms);
      }
    });

    jButton25.setText("44");
    jButton25.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChanger(jButton25, 4, 4, rooms);
      }
    });

    jLabel1.setText("Room ");

    jLabel2.setText("Items");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
            jPanel1Layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1).addGap(63, 63, 63))
        .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2).addComponent(jButton5).addGap(44, 44, 44)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(19, 19, 19))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup().addComponent(jButton21).addGap(2, 2, 2)
                            .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2).addComponent(jButton25))
                        .addGroup(jPanel1Layout.createSequentialGroup().addComponent(jButton16).addGap(2, 2, 2)
                            .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2).addComponent(jButton20)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 132,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup().addComponent(jButton11).addGap(2, 2, 2)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2).addComponent(jButton15).addGap(59, 59, 59).addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createSequentialGroup().addComponent(jButton6).addGap(2, 2, 2)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2).addComponent(jButton10)))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap()));
    jPanel1Layout
        .setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup().addGap(13, 13, 13).addComponent(jLabel1).addGap(3, 3, 3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1).addComponent(jButton2).addComponent(jButton3).addComponent(jButton4)
                        .addComponent(jButton5).addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(3, 3, 3)
                    .addGroup(jPanel1Layout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButton7)
                        .addComponent(jButton8).addComponent(jButton9).addComponent(jButton10).addComponent(jButton6))
                    .addGap(3, 3, 3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton12).addComponent(jButton13).addComponent(jButton14).addComponent(jButton15)
                        .addComponent(jButton11).addComponent(jLabel2))
                    .addGap(3, 3, 3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton17).addComponent(jButton18).addComponent(jButton19).addComponent(jButton20)
                        .addComponent(jButton16).addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(3, 3, 3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton22).addComponent(jButton23).addComponent(jButton24).addComponent(jButton25)
                        .addComponent(jButton21))
                    .addGap(0, 29, Short.MAX_VALUE)));

    jMenu1.setText("File");

    jMenuItem1.setText("Save Map");
    jMenu1.add(jMenuItem1);

    jMenuItem2.setText("Load Map");
    jMenu1.add(jMenuItem2);

    jMenuBar1.add(jMenu1);

    setJMenuBar(jMenuBar1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout
            .createSequentialGroup().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE)));
    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1,
        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

    pack();

  }

  /**
   * Runs the Program with dummy game.
   * @param args Standard main arguments.
   *          
   */
  public static void main(String args[]) {
    Player player = null;
    Game game = new Game(player);
    MapEditor editor = new MapEditor(game);
    editor.setVisible(true);
  }
  

}
