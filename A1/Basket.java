
public class Basket {
	//an array of marketproducts
	private MarketProduct[] basket;
	
	//constructor that initializes basket with empty array of market products
	public Basket() {
		basket = new MarketProduct[0];
	}
	
	//returns shallow copy of the basket array 
	public MarketProduct[] getProducts() {
		MarketProduct[] shallowCopy = basket.clone();
		return shallowCopy;
	}
	
	//adds market product object to the array of market product (basket)
	public void add(MarketProduct prod) {
		if (basket.length==0) {
			basket = new MarketProduct[]{prod};
		} else {
			MarketProduct[] temp = basket;
			int n = temp.length;
			basket = new MarketProduct[n+1];
		
			//populating the array with the previous items in the basket 
			for (int i=0; i<basket.length-1; i++) {
				basket[i] = temp[i];
			}
			//adding the new product to the basket 
			basket[basket.length-1] = prod; 
		}
	}
	
	//removing a product from the basket
	public boolean remove(MarketProduct prod) {
		int n = basket.length;
		MarketProduct[] temp = new MarketProduct[n-1];
		for (int i=0; i<n; i++) {
			if (basket[i].equals(prod)) {
				//repopulating the array with the other basket items
				for (int j=0; j<i; j++) {
					temp[j] = basket[j];
				}
				for (int k=i; k<n-1; k++) {
					temp[k] = basket[k+1];
				}
				basket = temp; 
				return true;
			}
		}
		return false;
	}
	
	//basket array is emptied
	public void clear() {
		basket = new MarketProduct[0];
	}
	
	//gets the number of products in the basket
	public int getNumOfProducts() {
		int n = basket.length;
		return n;
	}
	
	//returns the cost of all the items -tax
	public int getSubTotal() {
		int cost = 0;
		for (int i=0; i<basket.length; i++) {
			cost = cost +basket[i].getCost();
		}
		return cost;
	}
	
	//gets the tax for items that are not eggs or fruit 
	public int getTotalTax() {
		int tax = 0;
		String eggs = "Egg";
		String fruits = "Fruit";
		for (int i=0; i<basket.length; i++) {
			String classType = basket[i].getClass().getName();
			if (!classType.equals(eggs) && !classType.equals(fruits)) {
				tax = (int) (tax + basket[i].getCost()*0.15);
			}
		}
		return tax;
	}
	
	//returns subtotal + tax
	public int getTotalCost() { 
		int total = getSubTotal() + getTotalTax();
		return total;
	}
	
	//printing out the receipt
	public String toString() {
		String receipt = "";
		for (int i=0; i<basket.length; i++) {
			receipt = (receipt + basket[i].getName()+"\t"+ toDollars(basket[i].getCost())+"\n");
		}
		receipt = (receipt +"\nSubtotal\t"+ toDollars(getSubTotal())+"\nTotal Tax\t"+ toDollars(getTotalTax()));
		receipt = (receipt +"\n\nTotal Cost\t" + toDollars(getTotalCost())+ "\n");
		return receipt;
	}
	
	//helper method for conversion of int cents to dollars with 2 decimal places
	private String toDollars(int num) {
		float number = num/100;
		if (num<=0) {
			return "-";
		}
		String dollars = String.format("%.2f", number);
		return dollars;
	}	
}
