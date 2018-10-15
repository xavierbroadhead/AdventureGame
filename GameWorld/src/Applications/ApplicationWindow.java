/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Applications;

import GameWorld.Door;
import GameWorld.Game;
import GameWorld.Item;
import GameWorld.Key;
import GameWorld.Player;
import GameWorld.Player.Direction;
import GameWorld.Position;
import MapEditor.MapEditor;
import Renderer.Render;
import Parser.Parser;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileSystemView;

/**
 * APPLICATION WINDOW.
 *
 * @author harryrodger
 */
public class ApplicationWindow extends javax.swing.JFrame {

  protected Parser parser = new Parser();
  protected Player player = new Player(1, new Position(0, 0));
  protected Game game = new Game(player);
  protected Render render = new Render();
  // public static Image startImage = render.loadImage("front.png");
  // public static BufferedImage img = (BufferedImage) startImage;
  protected Image sovietUnion = loadImage("Screen Shot 2018-10-04 at 11.27.52 AM.png");
  protected BufferedImage soviet = (BufferedImage) sovietUnion;
  protected Image unitedState = loadImage("rsz_1280px-flag_of_the_united_statessvg.png");
  protected BufferedImage unitedStates = (BufferedImage) unitedState;
  protected int wallaway = 6;

  // Variables declaration - do not modify
  private javax.swing.JFrame discardPanel;
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
  private javax.swing.JFileChooser chooserLoad;
  private javax.swing.JFileChooser chooserSave;
  private javax.swing.JLabel inventory7;
  // End of variables declaration

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

    inventory7 = new javax.swing.JLabel();
    chooserLoad = new javax.swing.JFileChooser();
    chooserSave = new javax.swing.JFileChooser();
    jLabel1 = new javax.swing.JLabel();
    renderer = new javax.swing.JPanel() {
      public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        render.renderGame(g2, getWidth(), getHeight(), game, wallaway);
      }
    };
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

    chooserLoad.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
    chooserLoad.setApproveButtonToolTipText("");
    chooserLoad.setDialogTitle("Load");

    chooserSave.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
    chooserSave.setApproveButtonToolTipText("");
    chooserSave.setDialogTitle("Save");

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setBackground(new java.awt.Color(255, 51, 51));
    setResizable(false);

    // Renderer Panel
    renderer.setPreferredSize(new java.awt.Dimension(50, 512));

    // jLabel3.setIcon(new javax.swing.ImageIcon(this.img));

    javax.swing.GroupLayout rendererLayout = new javax.swing.GroupLayout(renderer);
    renderer.setLayout(rendererLayout);
    rendererLayout.setHorizontalGroup(rendererLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE));
    rendererLayout.setVerticalGroup(rendererLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE));

    // Bottom Panel
    up.setText("FORWARD");
    up.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        upActionPerformed(evt);
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

    // Right side panel

    unlock.setText("Unlock"); // set button text
    unlock.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        unlockActionPerformed(evt);
      }
    });

    discard.setText("Discard"); // set button text
    discard.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        discardActionPerformed(evt);
      }
    });

    pickUp.setText("Pick up"); // set button text
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

    javax.swing.GroupLayout rightSidePanelLayout = new javax.swing.GroupLayout(rightSidePanel); // initialise right side
                                                                                                // panel layout
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

    // Create inventory aspects of the GUI and Left side panel

    inventory.setText("INVENTORY");

    inventory1.setText("");
    inventory1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3)); // set Borders as

    inventory1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

    inventory2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

    inventory3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/health.gif")));
    inventory3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

    inventory4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/key.gif")));
    inventory4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

    inventory5.setText("");
    inventory5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

    inventory6.setText("");
    inventory6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

    // URL url ApplicationWindow.class.getResource("\"ooga.gif\"")
    // ImageIcon ooga = new
    // ImageIcon(getClass().getResource("/Resources/ooga.gif"));
    inventory7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/ooga.gif")));

    javax.swing.GroupLayout leftSidePanelLayout = new javax.swing.GroupLayout(leftSidePanel);
    leftSidePanel.setLayout(leftSidePanelLayout);
    leftSidePanelLayout.setHorizontalGroup(leftSidePanelLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(leftSidePanelLayout.createSequentialGroup()
            .addGroup(leftSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(leftSidePanelLayout.createSequentialGroup().addGap(71, 71, 71).addComponent(inventory))
                .addGroup(leftSidePanelLayout.createSequentialGroup().addGap(28, 28, 28)
                    .addGroup(leftSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(inventory1, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inventory3, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                        .addComponent(inventory6, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(40, 40, 40)
                    .addGroup(leftSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(inventory4, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                        .addComponent(inventory5, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inventory2, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                    leftSidePanelLayout.createSequentialGroup().addContainerGap()
                        .addComponent(inventory7, javax.swing.GroupLayout.PREFERRED_SIZE, 131,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    leftSidePanelLayout
        .setVerticalGroup(leftSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
            leftSidePanelLayout.createSequentialGroup().addContainerGap().addComponent(inventory).addGap(38, 38, 38)
                .addGroup(leftSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inventory1, javax.swing.GroupLayout.PREFERRED_SIZE, 61,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inventory2, javax.swing.GroupLayout.PREFERRED_SIZE, 61,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(leftSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inventory3, javax.swing.GroupLayout.PREFERRED_SIZE, 61,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inventory5, javax.swing.GroupLayout.PREFERRED_SIZE, 61,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(leftSidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inventory6, javax.swing.GroupLayout.PREFERRED_SIZE, 61,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inventory4, javax.swing.GroupLayout.PREFERRED_SIZE, 61,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(inventory7, javax.swing.GroupLayout.PREFERRED_SIZE, 122,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap()));

    jLabel10.setIcon(new javax.swing.ImageIcon(this.soviet)); // creates the soviet icon from the Label

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2); // initialises bottom right jPanel
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
            jPanel2Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(jLabel10,
                javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)));
    jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE));

    jLabel4.setIcon(new javax.swing.ImageIcon(this.unitedStates)); // creates the USA icon from the label

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel4,
            javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE));
    jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

    file.setText("File");

    // ActionListeners for file menubar Item

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

    // ActionListener for MapEditor menubar item

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
    layout // sorts out the layout of the contents pane
        .setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) // sorts horizontal
                                                                                                  // layout
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
    layout // sorts vertical layout
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
   * 
   *          When new game called, call relevant methods to initialise game
   */
  private void newGameActionPerformed(java.awt.event.ActionEvent evt) {
    javax.swing.JFrame newGame = new javax.swing.JFrame();

    javax.swing.JDialog warning = new javax.swing.JDialog();
    javax.swing.JLabel message = new javax.swing.JLabel(
        "Are you sure you would like begin start a new game? All Progress will be LOST");

    JButton yes = new JButton("yes");
    yes.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent ev) { // need to sort out
        dispose();
        newGame.dispose();
        new ApplicationWindow().main(null);
      }
    });

    JButton no = new JButton("no");
    no.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent ev) {
        newGame.dispose();
      }
    });

    javax.swing.JFrame buttons = new javax.swing.JFrame();
    buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
    buttons.add(yes);
    buttons.add(no);

    newGame.add(message);
    newGame.add(yes);
    newGame.add(no);

    newGame.setSize(550, 80);
    newGame.setLayout(new FlowLayout());
    newGame.setResizable(false);
    newGame.setVisible(true);

    messageBoard.append("You have restarted the game \n");

    messageBoard.setText("You have started a New Game. \n Welcome :) \n");

  }

  /**
   * Pick up invocation.
   * 
   * @param evt
   * 
   *          When pick up pressed calls this method which will invoke certain
   *          actions
   */
  private void pickUpActionPerformed(java.awt.event.ActionEvent evt) {
    // if item is key add new imageIcon to one of the invetory spaces same goes for
    // the other things
    List<Item> inventory = this.player.getInventory();

    if (inventory.size() == 6) {
      messageBoard.append("Your inventory is full \n please discard something");
    }

    if (this.player.getPosition().containsItem()) {
      
      GameWorld.Item item = this.player.getPosition().getItem();
      

      if (item instanceof GameWorld.Key) {
        this.player.pickup();
        this.inventory6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/key.gif")));
        messageBoard.append("You have picked up a Key! \n");
        // invetory1 .add icon ....
      } else if (item instanceof GameWorld.Book) {
        this.player.pickup();
        messageBoard.append("You have picked up a Book! \n");
        // inventory 1 .add icon
      }

      // if there is an item you are on top of

      // else there is no item to pick up
    }

  }

  /**
   * Open MapEditor Action.
   * 
   * @param evt
   * 
   *          When this item is selected from the menu bar the Map editor GUI will
   *          be made visible so that you may do what you wish from there.
   */
  private void openMapEditorActionPerformed(java.awt.event.ActionEvent evt) {
    MapEditor editor = new MapEditor(game);
    editor.setVisible(true);
    game = editor.getGame();
    renderer.repaint();
  }

  /**
   * Open Action.
   * 
   * @param evt
   * 
   *          If the door is unlocked then you will be able to see the method call
   *          the relevant methods to open the door.
   */
  private void openActionPerformed(java.awt.event.ActionEvent evt) {
    Door door = player.getPosition().getDoor();
    if (door != null) {
      if (door.getDirection() == player.getDirection()) {
        if (door.isLocked()==false) {
          messageBoard.append("You have opened the door, sir \n");
        } else {
          messageBoard.append("Door is still locked, you \n you need to find the right key");
        }
      } else {
        messageBoard.append("You are not facing the right \n way. the door is facing" + door.getDirection() + "");
      }
    } else {
      messageBoard.append("You are not near a door!");
    }

  }

  /**
   * Unlock Action.
   * 
   * @param evt
   * 
   *          When this method is called it will check whether you have the
   *          relevant key for the relevant room. If so then you will be able to
   *          unlock the door.
   */
  private void unlockActionPerformed(java.awt.event.ActionEvent evt) {

    Door door = player.getPosition().getDoor();
    if (door != null) {
      if (door.getDirection() == player.getDirection()) {
        if (door.hasKey(player)) {
          messageBoard.append("KaCHINK... the door is now \n open... \n");
          door.setLock(false);
        } else {
          messageBoard.append("You do not have the KEY on \n " + "you! Go find one somewhere around the faculty");
        }
      } else {
        messageBoard.append("You are not facing the right \n way. the door is facing" + door.getDirection() + "");
      }
    } else {
      messageBoard.append("You are not near a door!");
    }

   

   

  }

  /**
   * Discard Action.
   * 
   * @param evt
   * 
   *          When the player invokes this operation it will discard a Key from
   *          your inventory and place it on the floor below you.
   */
  private void discardActionPerformed(java.awt.event.ActionEvent evt) {

    List<Item> items = this.player.getInventory();

    if (items.isEmpty()) {
      System.out.println("inside");
      messageBoard.append("You have no items to discard");
      return;
    }

    // discard Panel
    discardPanel = new javax.swing.JFrame();

    JRadioButton key = new JRadioButton("Key");
    JRadioButton scroll = new JRadioButton("Scroll");
    JRadioButton healthPack = new JRadioButton("Health Pack");

    JButton discard = new JButton("Discard");
    discard.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {

        if (key.isSelected() == true) {
          messageBoard.append("You have discarded a Key");
          for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof GameWorld.Key) {

              items.remove(i);
              // put on ground
            }
          }
        } else if (scroll.isSelected() == true) {
          messageBoard.append("You have discarded a Book");
          for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof GameWorld.Book) {

              items.remove(i);
              // put on ground
            }
          }
        } else if (healthPack.isSelected() == true) {
          for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof GameWorld.Key) {
              items.remove(i);
              // put on ground
            }
          }

        } else {
          messageBoard.append("Select a item to discard");
        }
        discardPanel.dispose();
      }
    });

    ButtonGroup buttons = new ButtonGroup();
    buttons.add(key);
    buttons.add(scroll);
    buttons.add(healthPack);
    buttons.add(discard);
    discardPanel.add(key);
    discardPanel.add(scroll);
    discardPanel.add(healthPack);
    discardPanel.add(discard);
    discardPanel.setSize(100, 150);
    discardPanel.setLayout(new FlowLayout());
    discardPanel.setVisible(true);

  }

  /**
   * Forward Movement Action.
   * 
   * @param evt
   * 
   *          When the forward button is pressed on the GUI this method will be
   *          invoked. It will get the players current location check if the
   *          movement is valid then move the player forward by using the relevant
   *          methods from the gameWorld and the Renderer.
   */
  private void upActionPerformed(java.awt.event.ActionEvent evt) {
    // player.setDirection(player.getBehind());
    player.movePlayer(player.getDirection(), this.game);

    wallaway--;

    renderer.repaint();

  }

  /**
   * Backwards movement Action.
   * 
   * @param evt
   * 
   *          If the player is in a location that allows for them to move
   *          backwards then the player will be placed in a position they have
   *          come from
   */
  private void backwardsActionPerformed(java.awt.event.ActionEvent evt) {
    player.movePlayer(player.getDirection(), this.game);
    //player.setDirection(player.getBehind());
    wallaway++;

    renderer.repaint();

  }

  /**
   * Right Movement Action.
   * 
   * @param evt
   * 
   *          when user click clockwise on the GUI, the user will then find its
   *          orientation and rotate to the right.
   */
  private void rightActionPerformed(java.awt.event.ActionEvent evt) {

    player.setDirection(player.getRight());
    renderer.repaint();

  }

  /**
   * Left Movement Action.
   * 
   * @param evt
   * 
   *          When user clicks antiClockwise on the GUI the user will then find
   *          its orientation and rotate to the left.
   */
  private void leftActionPerformed(java.awt.event.ActionEvent evt) {
    player.setDirection(player.getLeft());
    renderer.repaint();

  }

  /**
   * Save Game Action.
   * 
   * @param evt
   * 
   *          When user clicks the save option in the menu bar a file chooser with
   *          the save dialog will appear which will then give you option as what
   *          to do next.
   * 
   */
  private void saveGameActionPerformed(java.awt.event.ActionEvent evt) {

    int returnVal = chooserSave.showSaveDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = chooserSave.getSelectedFile();
      //parser.saveToFile(this.game.getMaps(), this.game.getDoors(), this.player, file);
    } else {
      System.out.println("File access cancelled by user.");
    }

    messageBoard.append("You have saved the game \n");
  }

  /**
   * Load Game Action.
   * 
   * @param evt
   * 
   *          When the load option is selected in the menu bar a j file chooser
   *          will appear which will allow the user to select which file they
   *          would like to intergrate into the interface.
   */
  private void loadActionPerformed(java.awt.event.ActionEvent evt) {
    int returnVal = chooserLoad.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = chooserLoad.getSelectedFile();
      String filename = file.getName();
      Game loadedGame = parser.loadFromFile(filename);
      this.game = loadedGame;
      renderer.repaint(); // refreshes the players position etc

    } else {
      System.out.println("File access cancelled by user.");
    }

    messageBoard.append("You have loaded a game \n");
    // loadFromFile("hello");
  }

  /**
   * Restart Action.
   * 
   * @param evt
   * 
   *          When the restart action is called a game will essentially begin a
   *          new game. So everything called in new game will be called.
   */
  private void restartActionPerformed(java.awt.event.ActionEvent evt) {
    javax.swing.JFrame restart = new javax.swing.JFrame();

    javax.swing.JDialog warning = new javax.swing.JDialog();
    javax.swing.JLabel message = new javax.swing.JLabel(
        "Are you sure you would like to restart? All Progress will be LOST");

    JButton yes = new JButton("yes");
    yes.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent ev) { // need to sort out
        dispose();
        restart.dispose();
        new ApplicationWindow().main(null);
      }
    });

    JButton no = new JButton("no");
    no.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent ev) {
        restart.dispose();
      }
    });

    javax.swing.JFrame buttons = new javax.swing.JFrame();
    buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
    buttons.add(yes);
    buttons.add(no);

    restart.add(message);
    restart.add(yes);
    restart.add(no);

    restart.setSize(410, 80);
    restart.setLayout(new FlowLayout());
    restart.setResizable(false);
    restart.setVisible(true);

    messageBoard.append("You have restarted the game \n");

  }

  /**
   * Load Image.
   * 
   * @param fileName
   * 
   *          This method will load a image from the render resources folder.
   */

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

  /**
   * Main method.
   * 
   * @param args
   * 
   *          This main will start the experience of our game for the user. Once
   *          the GUI is operational all aspects from the other packages are able
   *          to be seen.
   */
  public static void main(String[] args) {

    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new ApplicationWindow().setVisible(true);
      }
    });
  }

}
