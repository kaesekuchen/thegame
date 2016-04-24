package thegame.adt;

import java.util.ArrayList;

public class CardDeck {

	private ArrayList<Card> cards;
	
	private final static int CARDVALUEMIN = 1;
	private final static int CARDVALUEMAX = 99;
	private final static int CARDVALUESTEPS = 1;
	

	
	public CardDeck()
	{
		initialize();
	}
	
	
	public int getCardsLeft()
	{
		return this.cards.size();
	}
	
	public ArrayList<Card> drawCards(int amount) throws IllegalStateException
	{
		Card drawCard = null;
		ArrayList<Card> resultingCards = new ArrayList<Card>();
		
		while ( amount > 0 && (drawCard = drawCard())  != null)
		{
			amount --;
			resultingCards.add(drawCard);
		}
		
		if(amount > 0 ) throw new IllegalStateException("Deck is empty! Couldnt draw cards:"+amount);
		
		return resultingCards;
		
	}
	
	public Card drawCard()
	{
		
		int cardsLeft = getCardsLeft();
		if (cardsLeft > 0)
		{
			return this.cards.remove(cardsLeft-1);
		}
		return null;
	}
	
	private void initialize()
	{
		ArrayList<Integer> values = new ArrayList<Integer>();
		this.cards = new ArrayList<Card>();
		
		
		for ( int i = CARDVALUEMIN ; i <= CARDVALUEMAX ; i+=CARDVALUESTEPS)
		{
			values.add(new Integer(i));
		}
		int idx = 0;
		while(values.size() > 0)
		{
			idx = (int)(Math.random() *values.size());
			this.cards.add(new Card(values.remove(idx)));
		}
		Math.random();
				
	}
	
}
