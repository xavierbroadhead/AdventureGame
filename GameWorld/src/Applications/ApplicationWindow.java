/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Applications;

import java.awt.Image;
import java.awt.image.BufferedImage;

import GameWorld.Game;
import GameWorld.Player;
import GameWorld.Player.Direction;
import GameWorld.Position;
import MapEditor.MapEditor;
import Renderer.Render;

/**
 * APPLICATION WINDOW.
 *
 * @author harryrodger
 */
public class ApplicationWindow extends javax.swing.JFrame {

  public static Player player = new Player(1, new Position(0, 0));
  public static Game game = new Game(player);
  public static Render render = new Render();
  public static Image startImage = render.loadImage("front.png");
  public static BufferedImage img = (BufferedImage) startImage;
  public static Image sovietUnion = render.loadImage("Screen Shot 2018-10-04 at 11.27.52 AM.png");
  public static BufferedImage soviet = (BufferedImage) sovietUnion;
  public static Image unitedState = render.loadImage("rsz_1280px-flag_of_the_united_statessvg.png");
  public static BufferedImage unitedStates = (BufferedImage) unitedState;

  /**
   * CREATION OF GUI. Creates new form AppWindows
   */
  public ApplicationWindow() {

    initComponents();

  }

  /**
   * Initialise all components of the GUI.
   * 
   */
  @SuppressWarnings("unchecked")

  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    renderer = new javax.swing.JPanel();
    jLabel3 = new javax.swing.JLabel();
    bottomPanel = new javax.swing.JPanel();
    up = new javax.swing.JButton();
    backwards = new javax.swing.JButton();
    right = new javax.swing.JButton();
    left = new javax.swing.JButton();
    rightSidePanel = new javax.swing.JPanel();
    unlock = new javax.swing.JButton();
    discard = new javax.swing.JButton();
    pickUp = new javax.swing.JButton();
    open = new javax.swing.JButton();
    leftSidePanel = new javax.swing.JPanel();
    inventory = new javax.swing.JLabel();
    inventory1 = new javax.swing.JLabel();
    inventory2 = new javax.swing.JLabel();
    inventory3 = new javax.swing.JLabel();
    inventory4 = new javax.swing.JLabel();
    inventory5 = new javax.swing.JLabel();
    inventory6 = new javax.swing.JLabel();
    jPanel2 = new javax.swing.JPanel();
    jLabel10 = new javax.swing.JLabel();
    jPanel4 = new javax.swing.JPanel();
    jLabel4 = new javax.swing.JLabel();
    messageBoard = new java.awt.TextArea();
    jMenuBar1 = new javax.swing.JMenuBar();
    file = new javax.swing.JMenu();
    newGame = new javax.swing.JMenuItem();
    saveGame = new javax.swing.JMenuItem();
    load = new javax.swing.JMenuItem();
    restart = new javax.swing.JMenuItem();
    mapEditor = new javax.swing.JMenu();
    openMapEditor = new javax.swing.JMenuItem();

    jLabel1.setText("jLabel1");

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setBackground(new java.awt.Color(255, 51, 51));
    setResizable(false);

    renderer.setPreferredSize(new java.awt.Dimension(50, 512));

    jLabel3.setIcon(new javax.swing.ImageIcon(this.img)); // NOI18N

    javax.swing.GroupLayout rendererLayout = new javax.swing.GroupLayout(renderer);
    renderer.setLayout(rendererLayout);
    rendererLayout.setHorizontalGroup(rendererLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE));
    rendererLayout.setVerticalGroup(rendererLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE));

    up.setText("FORWARD");
    up.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        UPActionPerformed(evt);
      }
    });

    backwards.setText("BACKWARDS");
    backwards.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        backwardsActionPerformed(evt);
      }
    });

    right.setText("Clockwise");
    right.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        rightActionPerformed(evt);
      }
    });

    left.setText("Anti-Clockwise");
    left.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        leftActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
    bottomPanel.setLayout(bottomPanelLayout);
    bottomPanelLayout.setHorizontalGroup(bottomPanelLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(up, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(bottomPanelLayout.createSequentialGroup()
                    .addComponent(left, javax.swing.GroupLayout.PREFERRED_SIZE, 117,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(backwards)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(right, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(52, 52, 52)));
    bottomPanelLayout.setVerticalGroup(bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(bottomPanelLayout.createSequentialGroup().addContainerGap()
            .addComponent(up, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
            .addGroup(bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(backwards, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(right, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(left, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(46, 46, 46)));

    unlock.setLabel("Unlock");
    unlock.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        unlockActionPerformed(evt);
      }
    });

    discard.setLabel("Discard");
    discard.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        discardActionPerformed(evt);
      }
    });

    pickUp.setLabel("Pick up");
    pickUp.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        pickUpActionPerformed(evt);
      }
    });

    open.setText("Open");
    open.setSize(new java.awt.Dimension(105, 29));
    open.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        openActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout rightSidePanelLayout = new javax.swing.GroupLayout(rightSidePanel);
    rightSidePanel.setLayout(rightSidePanelLayout);
    rightSidePanelLayout
        .setHorizontalGroup(rightSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightSidePanelLayout.createSequentialGroup()
                .addGroup(rightSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rightSidePanelLayout.createSequentialGroup()
                        .addComponent(pickUp, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(open,
                            javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rightSidePanelLayout.createSequentialGroup()
                        .addComponent(discard, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(unlock,
                            javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    rightSidePanelLayout
        .setVerticalGroup(
            rightSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(rightSidePanelLayout.createSequentialGroup().addGap(123, 123, 123)
                    .addGroup(rightSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(discard, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(unlock))
                    .addGap(49, 49, 49)
                    .addGroup(rightSidePanelLayout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(pickUp,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(open))));

    inventory.setText("INVENTORY");

    inventory1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

    inventory2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

    inventory3.setText("           ");
    inventory3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

    inventory4.setText("           ");
    inventory4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

    inventory5.setText("           ");
    inventory5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

    inventory6.setText("           ");
    inventory6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

    javax.swing.GroupLayout leftSidePanelLayout = new javax.swing.GroupLayout(leftSidePanel);
    leftSidePanel.setLayout(leftSidePanelLayout);
    leftSidePanelLayout.setHorizontalGroup(leftSidePanelLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(leftSidePanelLayout.createSequentialGroup().addGroup(leftSidePanelLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftSidePanelLayout.createSequentialGroup().addGap(28, 28, 28)
                .addGroup(leftSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(leftSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(inventory3, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inventory1, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(inventory6))
                .addGap(51, 51, 51)
                .addGroup(leftSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inventory2, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inventory4, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inventory5, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(leftSidePanelLayout.createSequentialGroup().addGap(71, 71, 71).addComponent(inventory)))
            .addContainerGap(50, Short.MAX_VALUE)));
    leftSidePanelLayout
        .setVerticalGroup(leftSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
            leftSidePanelLayout.createSequentialGroup().addContainerGap().addComponent(inventory).addGap(38, 38, 38)
                .addGroup(leftSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inventory1, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inventory2, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(leftSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inventory3, javax.swing.GroupLayout.PREFERRED_SIZE, 47,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inventory5, javax.swing.GroupLayout.PREFERRED_SIZE, 47,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(leftSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inventory4, javax.swing.GroupLayout.PREFERRED_SIZE, 47,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inventory6, javax.swing.GroupLayout.PREFERRED_SIZE, 47,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE)));

    jLabel10.setIcon(new javax.swing.ImageIcon(this.soviet)); // NOI18N

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
            jPanel2Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(jLabel10,
                javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)));
    jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE));

    jLabel4.setIcon(new javax.swing.ImageIcon(this.unitedStates)); // NOI18N

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel4,
            javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE));
    jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

    file.setText("File");

    newGame.setText("New Game");
    newGame.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        newGameActionPerformed(evt);
      }
    });
    file.add(newGame);

    saveGame.setText("Save");
    saveGame.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        saveGameActionPerformed(evt);
      }
    });
    file.add(saveGame);

    load.setText("Load");
    load.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        loadActionPerformed(evt);
      }
    });
    file.add(load);

    restart.setText("Restart");
    restart.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        restartActionPerformed(evt);
      }
    });
    file.add(restart);

    jMenuBar1.add(file);

    mapEditor.setText("MapEditor");

    openMapEditor.setText("Open");
    openMapEditor.setToolTipText("");
    openMapEditor.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {

        openMapEditorActionPerformed(evt);
      }
    });
    mapEditor.add(openMapEditor);

    jMenuBar1.add(mapEditor);

    setJMenuBar(jMenuBar1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout
        .setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(leftSidePanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(renderer, javax.swing.GroupLayout.PREFERRED_SIZE, 484,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                        layout.createSequentialGroup()
                            .addComponent(bottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(22, 22, 22)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rightSidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 204,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                        layout.createSequentialGroup().addGap(5, 5, 5).addComponent(jPanel2,
                            javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup().addGap(10, 10, 10).addComponent(messageBoard,
                        javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))));
    layout
        .setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rightSidePanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(messageBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 150,
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup().addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(leftSidePanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(renderer, javax.swing.GroupLayout.PREFERRED_SIZE, 381,
                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(jPanel4,
                                    javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                    javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                        layout.createSequentialGroup()
                            .addComponent(bottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))));

    pack();
  }

  /**
   * New game invocation.
   * 
   * @param evt
   *          - when new game called, call relevant methods to initialise game
   */
  private void newGameActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
    messageBoard.setText("You have started a New Game. Welcome :) \n");
  }

  /**
   * Pick up invocation.
   * 
   * @param evt
   *          - when pick up pressed calls this method which will invoke certain
   *          actions
   */
  private void pickUpActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
    messageBoard.append("You have picked up an Item! \n");

  }

  /**
   * 
   * @param evt
   */
  private void openMapEditorActionPerformed(java.awt.event.ActionEvent evt) {
    MapEditor editor = new MapEditor(game);
    editor.setVisible(true);
    // TODO add your handling code here:
  }

  /**
   * 
   * @param evt
   */
  private void openActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  /**
   * 
   * @param evt
   */
  private void unlockActionPerformed(java.awt.event.ActionEvent evt) {
    messageBoard.append("KaCHINK... the door is now \n open... \n");
    // TODO add your handling code here:
  }

  /**
   * 
   * @param evt
   */
  private void discardActionPerformed(java.awt.event.ActionEvent evt) {
    messageBoard.append("you have discarded an item \n");
    // TODO add your handling code here:
  }

  /**
   * 
   * @param evt
   */
  private void UPActionPerformed(java.awt.event.ActionEvent evt) {
    player.movePlayer(Direction.NORTH);

  }

  /**
   * 
   * @param evt
   */
  private void backwardsActionPerformed(java.awt.event.ActionEvent evt) {

    player.setDirection(player.getBehind());
    player.movePlayer(player.getDirection());
  }

  /**
   * 
   * @param evt
   */
  private void rightActionPerformed(java.awt.event.ActionEvent evt) {

    player.setDirection(player.getRight());
    this.img = render.getBackgroundFile(player);
    jLabel3.setIcon(new javax.swing.ImageIcon(this.img));

  }

  /**
   * 
   * @param evt
   */
  private void leftActionPerformed(java.awt.event.ActionEvent evt) {
    player.setDirection(player.getLeft());
    this.img = render.getBackgroundFile(player);
    jLabel3.setIcon(new javax.swing.ImageIcon(this.img));

  }

  /**
   * 
   * @param evt
   */
  private void saveGameActionPerformed(java.awt.event.ActionEvent evt) {
    messageBoard.append("You have saved the game \n");
  }

  /**
   * 
   * @param evt
   */
  private void loadActionPerformed(java.awt.event.ActionEvent evt) {
    messageBoard.append("You have loaded a game \n");
    // loadFromFile("hello");
  }

  /**
   * 
   * @param evt
   */
  private void restartActionPerformed(java.awt.event.ActionEvent evt) {
    messageBoard.append("You have restarted the game \n");
  }

  /**
   * 
   * @param args
   */
  public static void main(String[] args) {

    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new ApplicationWindow().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify
  private javax.swing.JPanel renderer;
  private javax.swing.JButton up;
  private javax.swing.JButton backwards;
  private javax.swing.JPanel bottomPanel;
  private javax.swing.JButton discard;
  private javax.swing.JMenu file;
  private javax.swing.JLabel inventory;
  private javax.swing.JLabel inventory1;
  private javax.swing.JLabel inventory2;
  private javax.swing.JLabel inventory3;
  private javax.swing.JLabel inventory4;
  private javax.swing.JLabel inventory5;
  private javax.swing.JLabel inventory6;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel10;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JMenuBar jMenuBar1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel4;
  private javax.swing.JButton left;
  private javax.swing.JPanel leftSidePanel;
  private javax.swing.JMenuItem load;
  private javax.swing.JMenu mapEditor;
  private java.awt.TextArea messageBoard;
  private javax.swing.JMenuItem newGame;
  private javax.swing.JButton open;
  private javax.swing.JMenuItem openMapEditor;
  private javax.swing.JButton pickUp;
  private javax.swing.JMenuItem restart;
  private javax.swing.JButton right;
  private javax.swing.JPanel rightSidePanel;
  private javax.swing.JMenuItem saveGame;
  private javax.swing.JButton unlock;
  // End of variables declaration
}
