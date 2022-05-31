
public class Customer {
	private String customerName;
	private int balance;
	private Basket b; 
	
	//constructor
	public Customer(String name, int initialB) {
		this.customerName=name;
		this.balance=initialB;
		b = new Basket(); 
	}
	
	//get methods
	public String getName() {
		return this.customerName;
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public Basket getBasket() {
		return b;
	}
	
	//adding funds to the balance
	public int addFunds(int added) {
		if (added<0) {
			throw new IllegalArgumentException("Illegal amount added to balance");
		}
		else {
			balance = balance + added;
			return balance;
		}
	}
	
	//adding a new object to this basket
	public void addToBasket(MarketProduct prod) {
		b.add(prod);
	}
	
	//removing an item from the basket
	public boolean removeFromBasket(MarketProduct prod) {
		MarketProduct[] cBasket = b.getProducts();
		//checking if the product is in the basket 
		for (int i=0; i<cBasket.length; i++ ) {
			if (cBasket[i].equals(prod)) {
				//removing the product
				b.remove(prod);
				return true;
			}
		}
		return false;
	}
	
	//clearing the customer basket and returning the receipt
	public String checkOut() {
		int total = b.getTotalCost();
		if (balance<total) {
			throw new IllegalStateException();
		} else {
			balance = balance-total;
			String receipt = b.toString();
			b.clear();
			return receipt;
		}
	}
	
	
}
