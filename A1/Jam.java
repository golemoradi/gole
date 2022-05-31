
public class Jam extends MarketProduct{
	private int numberJars;
	private int priceJar;
	
	//constructor
	public Jam(String prodName, int n, int p) {
		super(prodName);
		this.numberJars = n;
		this.priceJar = p;
	}
	
	//get method
	@Override
	public int getCost() {
		int cost = numberJars*priceJar;
		return cost;
	}
	
	//equals method that checks for the same type, name and cost 
	@Override
	public boolean equals(Object obj) {
		String type = obj.getClass().getName();
		String name = ((MarketProduct) obj).getName();
		if (type.equals("Jam") && name.equals(getName())) {
			if (obj instanceof Jam) {
				Jam test = (Jam) obj;
				return numberJars==test.numberJars && 
						priceJar==test.priceJar;
			}
		} else {
			return false;
		}
		return false;
	}
	
}
