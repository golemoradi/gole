
public class Fruit extends MarketProduct{
	private double weight;
	private int priceKg;
	
	//constructor
	public Fruit(String productName, double prodWeight, int prodPrice) {
		super(productName);
		this.weight = prodWeight;
		this.priceKg = prodPrice;
	}
	
	//get cost of fruit
	@Override
	public int getCost() {
		int cost = (int) (weight*priceKg);
		return cost;
	}
	
	//overriding equals method
	@Override
	public boolean equals(Object obj) {
		String type = obj.getClass().getName();
		String name = ((MarketProduct) obj).getName();
		//checking if the type and name is the same 
		if (type.equals("Fruit") && name.equals(getName())) {
			//checking if the weight and price per kg is the same too
			if (obj instanceof Fruit) {
				Fruit test = (Fruit) obj;
				return weight==test.weight && 
						priceKg==test.priceKg;
			}
		} else {
			return false;
		}
		return false;
	}
	
}
