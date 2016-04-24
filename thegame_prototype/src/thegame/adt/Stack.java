package thegame.adt;

import java.util.ArrayList;
import java.util.Arrays;

public class Stack {

	public enum Direction
	{
		UPWARDS,
		DOWNWARDS
	}
	
	private Direction direction;
	
	private ArrayList<Card> cards;
	
	public Stack(Direction direction)
	{
		this.direction = direction;
		this.cards = new ArrayList<Card>();
	}
	
	public Card peakTopCard()
	{
		if(this.cards.isEmpty()) return null;
		return this.cards.get(this.cards.size()-1);
	}
	
	public boolean putCard(Card c)
	{
		if (c == null) return false;
		
		if (cards.isEmpty())
		{
			cards.add(c);
			return true;
		}
		
		int topCardValue = cards.get(cards.size()-1).getValue(); 
		
		if (topCardValue > c.getValue()) 
		{
			//direction needs to be downwards
			if (direction == Direction.DOWNWARDS || (topCardValue - c.getValue() == 10))
			{
				cards.add(c);
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			//direction needs to be upwards
			if (direction == Direction.UPWARDS || (c.getValue() - topCardValue  == 10))
			{
				cards.add(c);
				return true;
			}
			else
			{
				return false;
			}
		}

	}
	
	@Override
	public String toString() {
		return Arrays.toString(cards.toArray());
	}

}
