package thegame.adt;

public class Card {

	private int value;
	
	public int getValue() {
		return value;
	}

	public Card(int value)
	{
		this.value = value;
		//test
	}

	@Override
	public String toString() {
		return "Card Value:" + this.value;
	}
}
