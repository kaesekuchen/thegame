package thegame.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import thegame.adt.Card;
import thegame.adt.CardDeck;
import thegame.adt.Hand;
import thegame.adt.Stack;
import thegame.adt.Stack.Direction;

public class main {
	public static Image i = null ;
	public static BufferedImage iAll = null ;
	
	public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
	    BufferedImage dbi = null;
	    if(sbi != null) {
	        dbi = new BufferedImage(dWidth, dHeight, imageType);
	        Graphics2D g = dbi.createGraphics();
	        AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
	        g.drawRenderedImage(sbi, at);
	    }
	    return dbi;
	}
	
	public static void main (String[] args)
	{
		CardDeck cd = new CardDeck();
		
		ArrayList<Card> draw = cd.drawCards(1);
		
		Hand hand = new Hand();
		hand.fillHand(cd);
		
		System.out.println(draw.get(0).getValue());
		System.out.println(hand);
		
		
		Stack up1 = new Stack(Direction.UPWARDS);
		Stack up2 = new Stack(Direction.UPWARDS);
		Stack down1 = new Stack(Direction.DOWNWARDS);
		Stack down2 = new Stack(Direction.DOWNWARDS);
		
		Card c;
		
		
		StringBuilder sb;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		javax.swing.JFrame frame = new JFrame("THE game.");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(800, 600);
		File fMatte = new File("projektwoche_thegame.png");
		File f= new File("the-game.jpg");
		
		try {
			 i = ImageIO.read(f);
			 iAll = ImageIO.read(fMatte);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		JPanel panel = new JPanel()
		{

			@Override
			protected void paintComponent(Graphics g) 
			{
				// TODO Auto-generated method stub
				super.paintComponent(g);
					
				Font font = new Font("Consolas", Font.PLAIN, 180);
				
				
				
			g.setColor(Color.white);
			g.setFont(font);
			
			int width = 350;
			int height = 600;
			
			g.drawImage(scale(iAll, BufferedImage.TYPE_INT_RGB, width, height, (double)width/iAll.getWidth(),  (double)height/iAll.getHeight()), 25,25,null);
			
			
			
//				for ( int idx = 0 ; idx < 4 ; idx ++)
//				{
//					g.drawImage(i, 20 + (idx*i.getWidth(null)),100,null);
//					g.drawString("00", 20 + (idx*i.getWidth(null))
//							, 200 + i.getHeight(null)/2);
//				}
				
				
			}
	
		};
		
		JComponent component = new JComponent() {
			@Override
			public void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);

				g.drawString("hello", 200, 200);
			
			}
		};
		component.setVisible(true);
			panel.add(component);
		
		frame.add(panel);
		frame.setVisible(true);
		
		int input = -1;
		while (cd.getCardsLeft() > 0)
		{
			input = -1;
			sb = new StringBuilder();
			
			if(up1.peakTopCard() != null) sb.append(up1.peakTopCard().getValue());
			sb.append(",");
			if(up2.peakTopCard() != null) sb.append(up2.peakTopCard().getValue());
			sb.append(",");
			if(down1.peakTopCard() != null) sb.append(down1.peakTopCard().getValue());
			sb.append(",");
			if(down2.peakTopCard() != null) sb.append(down2.peakTopCard().getValue());
			sb.append(",");
			
			System.out.println(sb);
			System.out.println(hand);
			
			do
			{
				try
				{
					input = Integer.parseInt(br.readLine());	
				}
				catch( NumberFormatException | IOException e)
				{
					System.err.println(e);
				}
			}while(input < 1 || input > hand.getCardsLeft());
			
			c = hand.takeCard(input-1);
			
			System.out.println("you took: "+c);
			System.out.println("up1 (1), up2(2), down1(3), down2(4)");
			do
			{
				try
				{
					input = Integer.parseInt(br.readLine());	
				}
				catch( NumberFormatException | IOException e)
				{
					System.err.println(e);
				}
			}while(input < 1 || input > 4);
		
			boolean success = false;
			switch (input)
			{
				case 1 : success= up1.putCard(c);
				break;
				case 2 : success= up2.putCard(c);
				break;
				case 3 : success= down1.putCard(c);
				break;
				case 4 : success= down2.putCard(c);
				break;
			}
			
			if(!success)
			{
				hand.putBackCard(c);
				System.out.println("invalid move");
			}

			
			
			
			hand.fillHand(cd);;
			
			System.out.println(hand);
			System.out.println(cd.getCardsLeft());
		}
		
	}
	
}
