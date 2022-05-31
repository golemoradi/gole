
public class SeasonalFruit extends Fruit {

	public SeasonalFruit(String productName, double prodWeight, int prodPrice) {
		super(productName, prodWeight, prodPrice);
	}
	
	//takes 15% off seasonal fruits
	public int getCost() {
		int cost = super.getCost();
		cost = (int) (cost*0.85);
		return cost;
	}
}
