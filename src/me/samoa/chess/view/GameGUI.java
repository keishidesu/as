package me.samoa.chess.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.border.LineBorder;
import java.util.*;
import java.io.*;
import me.samoa.chess.controller.*;

public class GameGUI extends JFrame{
  private JButton[][] buttons = new JButton[8][7];
  private static HashMap<String, BufferedImage> chessImage = new HashMap<>();

  public GameGUI() {
    super("Webale Chess");

    setSize(500, 700);

    super.setJMenuBar(new Menu());

    JPanel mpanel = new JPanel(new GridLayout(8,7));

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 7; j++) {
        buttons[i][j] = new JButton();
        buttons[i][j].setName("("+j+","+i+")");
        // buttons[i][j].setText("("+j+","+i+")");
        buttons[i][j].setBackground(Color.WHITE);
        buttons[i][j].setBorder(new LineBorder(Color.BLUE));
        buttons[i][j].addMouseListener(new ButtonListener());
        mpanel.add(buttons[i][j]); 
      }
    }

    chessImg();

    chessWImage();
    super.add(mpanel);

    setVisible(true);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    // Displays a pop-up window to confirm whether or not the player wants to exit the game
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				JFrame frame = (JFrame)e.getSource();
		 
        if (JOptionPane.showConfirmDialog(mpanel, "Are you sure you want to exit the game? Don't forget to save!",
					  "Exit Game?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
		        setDefaultCloseOperation(EXIT_ON_CLOSE);
			}
		});
  }
  
  public void chessImg() {
    try{
      chessImage.put("Sun RED", ImageIO.read(new File("src/resources/Sun-red.png")));
      chessImage.put("Sun BLUE", ImageIO.read(new File("src/resources/Sun-blue.png")));
      chessImage.put("Sun BLUE", ImageIO.read(new File("src/resources/Sun-blue.png")));
      chessImage.put("Arrow RED", ImageIO.read(new File("src/resources/Arrow-red.png")));
      chessImage.put("Arrow BLUE", ImageIO.read(new File("src/resources/Arrow-blue.png")));
      chessImage.put("Chevron RED", ImageIO.read(new File("src/resources/Chevron-red.png")));
      chessImage.put("Chevron BLUE", ImageIO.read(new File("src/resources/Chevron-blue.png")));
      chessImage.put("Plus RED", ImageIO.read(new File("src/resources/Plus-red.png")));
      chessImage.put("Plus BLUE", ImageIO.read(new File("src/resources/Plus-blue.png")));
      chessImage.put("Triangle RED", ImageIO.read(new File("src/resources/Triangle-red.png")));
      chessImage.put("Triangle BLUE", ImageIO.read(new File("src/resources/Triangle-blue.png")));
    } catch(IOException err) {
      err.printStackTrace();
    }
  }

  public void chessWImage() {
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 7; j++){
        BoardSlotInfo slotInfo = new BoardSlotInfo(i, j);
        if(slotInfo.isOccupied()){
          if(chessImage.containsKey(slotInfo.getPieceName())){
            Image imgIcon = (Image)chessImage.get(slotInfo.getPieceName());
            buttons[i][j].setIcon(new ImageIcon(imgIcon));
          }
        } 
      }
    }
  }
}
