
public class Egg extends MarketProduct {
	private int eggNumber;
	private int priceDozen;
	
	//constructor
	public Egg(String prodName, int n, int p) {
		super(prodName);
		this.eggNumber = n;
		this.priceDozen = p; 
	}
	
	// get cost method
	@Override
	public int getCost() {
		int cost = (eggNumber*priceDozen)/12;
		return cost;
	}
	
	//equals method that checks for the same type, name, number of eggs and price per dozen
	@Override
	public boolean equals(Object obj) {
		String type = obj.getClass().getName();
		String name = ((MarketProduct) obj).getName();
		if (type.equals("Egg") && name.equals(getName())) {
			if (obj instanceof Egg) {
				Egg test = (Egg) obj;
				return eggNumber==test.eggNumber && 
						priceDozen==test.priceDozen;
			}
		} else {
			return false;
		}
		return false;
	}
}
