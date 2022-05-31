package assignments2018.a2template;
import java.math.BigInteger;

public class Polynomial {
	private SLinkedList<Term> polynomial;
	public int size()
	{	
		return polynomial.size();
	}
	private Polynomial(SLinkedList<Term> p)
	{
		polynomial = p;
	}
	
	public Polynomial()
	{
		polynomial = new SLinkedList<Term>();
	}
	
	// Returns a deep copy of the object.
	public Polynomial deepClone()
	{	
		return new Polynomial(polynomial.deepClone());
	}
	
	/* 
	 * TODO: Add new term to the polynomial. Also ensure the polynomial is
	 * in decreasing order of exponent.
	 */
	public void addTerm(Term t)
	{	
		/**** ADD CODE HERE ****/
		//checking if the term has proper exponent values/coefficient values
		if (t.getCoefficient().equals(BigInteger.ZERO) || t.getExponent()<0) {
			throw new IllegalArgumentException("Improper coefficient and/or exponent value");
		} else {
			//checking if the polynomial is empty 
			if (polynomial.size()==0) {
				polynomial.addFirst(t);
			} else {
				int index=0;
				for (Term currentTerm: polynomial) {
					//if there is a term with an equal exponent to t, need to add them 
					if (currentTerm.getExponent()==t.getExponent()) {
						BigInteger newCoeff = currentTerm.getCoefficient().add(t.getCoefficient());
						if (newCoeff.equals(BigInteger.ZERO)) {
							polynomial.remove(index);
							return;
						} else {
							Term newTerm = new Term(t.getExponent(), newCoeff);
							polynomial.remove(index);
							polynomial.add(index, newTerm);	
							return;
						}
					}
					
					//adding the term to polynomial at the beginning or end
					if (currentTerm.getExponent()<t.getExponent() && index==0) {
						polynomial.addFirst(t);
						return;
					}
					if (currentTerm.getExponent()>t.getExponent() && index==polynomial.size()-1) {
						polynomial.addLast(t);
						return;
					}
					index++;
				}
				//adding the term in the middle of the polynomial
				int i=0;
				for (Term currentTerm: polynomial) {
					if (i!=0 && i!=size()) {
						if (t.getExponent()>currentTerm.getExponent()) {
							polynomial.add(i, t);
							return;
						}
					}
					i++;
				}
			}
		}
		
		// Hint: Notice that the function SLinkedList.get(index) method is O(n), 
		// so if this method were to call the get(index) 
		// method n times then the method would be O(n^2).
		// Instead, use a Java enhanced for loop to iterate through 
		// the terms of an SLinkedList.
		/*
		for (Term currentTerm: polynomial)
		{
			// The for loop iterates over each term in the polynomial!!
			// Example: System.out.println(currentTerm.getExponent()) should print the exponents of each term in the polynomial when it is not empty.  
		}
		*/
	}
	
	public Term getTerm(int index)
	{
		return polynomial.get(index);
	}
	
	//TODO: Add two polynomial without modifying either
	public static Polynomial add(Polynomial p1, Polynomial p2)
	{
		/**** ADD CODE HERE ****/
		Polynomial p4 = p2.deepClone();
		for (Term currentTerm: p1.polynomial) {
			p4.addTerm(currentTerm);
		}
		return p4;
	}
	
	//TODO: multiply this polynomial by a given term.
	private void multiplyTerm(Term t)
	{	
		/**** ADD CODE HERE ****/
		SLinkedList<Term> mult = new SLinkedList<Term>();
		for (Term currentTerm: polynomial) {
			int exp = currentTerm.getExponent() + t.getExponent();
			BigInteger nCoeff = currentTerm.getCoefficient().multiply(t.getCoefficient());
			if (exp>0 && !nCoeff.equals(BigInteger.ZERO)) {
				Term temp = new Term(exp,nCoeff);
				mult.addLast(temp);
			}
			
		}
		this.polynomial =mult;	
	}
	
	//TODO: multiply two polynomials
	public static Polynomial multiply(Polynomial p1, Polynomial p2)
	{
		/**** ADD CODE HERE ****/
		Polynomial temp = new Polynomial();
		Polynomial copy = p2.deepClone();
		for (Term currentTerm: p1.polynomial) {
			copy.multiplyTerm(currentTerm);
			temp = add(temp,copy);
		}
		return temp;
	}
	
	//TODO: evaluate this polynomial.
	// Hint:  The time complexity of eval() must be order O(m), 
	// where m is the largest degree of the polynomial. Notice 
	// that the function SLinkedList.get(index) method is O(m), 
	// so if your eval() method were to call the get(index) 
	// method m times then your eval method would be O(m^2).
	// Instead, use a Java enhanced for loop to iterate through 
	// the terms of an SLinkedList.

	public BigInteger eval(BigInteger x)
	{
		/**** ADD CODE HERE ****/
		BigInteger a = BigInteger.ZERO;
		int index = 0;
		for (Term currentTerm: polynomial) {
			a = a.add(currentTerm.getCoefficient());
			int b = 0;
			if (index+1 == polynomial.size()) {
				b = currentTerm.getExponent();
			} else if (index+1 < polynomial.size()) {
				b = currentTerm.getExponent()-polynomial.get(index+1).getExponent();
			}
			a = a.multiply(x.pow(b));
			index++;
			
		}
		return a;
		
	}
	
	// Checks if this polynomial is same as the polynomial in the argument
	public boolean checkEqual(Polynomial p)
	{	
		if (polynomial == null || p.polynomial == null || size() != p.size())
			return false;
		
		int index = 0;
		for (Term term0 : polynomial)
		{
			Term term1 = p.getTerm(index);
			
			if (term0.getExponent() != term1.getExponent() ||
				term0.getCoefficient().compareTo(term1.getCoefficient()) != 0 || term1 == term0)
					return false;
			
			index++;
		}
		return true;
	}
	
	// This method blindly adds a term to the end of LinkedList polynomial. 
	// Avoid using this method in your implementation as it is only used for testing.
	public void addTermLast(Term t)
	{	
		polynomial.addLast(t);
	}
	
	// This is used for testing multiplyTerm
	public void multiplyTermTest(Term t)
	{
		multiplyTerm(t);
	}
	
	@Override
	public String toString()
	{	
		if (polynomial.size() == 0) return "0";
		return polynomial.toString();
	}
}
