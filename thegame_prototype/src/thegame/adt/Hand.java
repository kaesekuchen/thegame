package thegame.adt;

import java.util.ArrayList;
import java.util.Arrays;

public class Hand {

	private static final int HANDCARDMAX = 6;
	
	private ArrayList<Card> cards;
	
	public Hand()
	{
		this.cards = new ArrayList<Card>();
	}
	
	
	@Override
	public String toString() {
		return Arrays.toString(cards.toArray());
	}

	public int getCardsLeft()
	{
		return this.cards.size();
	}
	
	public Card takeCard(int idx)
	{
		
		if (!this.cards.isEmpty() && idx >= 0 && idx < this.cards.size())
		{
			Card retrieveCard = this.cards.get(idx);
			this.cards.remove(idx);
			return retrieveCard;
		}
		
		return null;
		
	}
	
	public void putBackCard(Card c)
	{
		this.cards.add(c);
	}
	
	public void fillHand(CardDeck cardDeck)
	{
		int drawAmount = HANDCARDMAX - this.cards.size();
		
		if (drawAmount <= 0) return;
		
		try
		{
			ArrayList<Card> drawnCards = cardDeck.drawCards(drawAmount);
			this.cards.addAll(drawnCards);
		}
		catch (IllegalStateException e)
		{
			System.err.println(e);
		}
		
		
		
	}
	
}
