
public abstract class MarketProduct {
	private String name;
	
	//constructor
	public MarketProduct(String productName) {
		this.name = productName;
	}
	
	// getName() method which retrieves the name of this market product
	public final String getName() {
		return this.name;
	}
	
	//abstract method getCost() declaration 
	public abstract int getCost();
	
	//abstract method equals() declaration
	public abstract boolean equals(Object obj);
}
