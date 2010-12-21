/**
 * 
 */
package at.ac.vut.pbwl.bootstrapping;

/**
 * @author stefan achleitner
 * interest rate class
 */
public class InterestRate {

	/**
	 * period of the interest rate
	 */
	private int period;
	
	/**
	 * discount factor of the current period and value
	 */
	private double discountFactor;
	
	/**
	 * value of the interest rate
	 */
	private double value;
	
	/**
	 * currency of the interest rate
	 */
	private String currency;

	/**
	 * default constructor
	 */
	public InterestRate() {
		
	}
	
	/**
	 * constructor
	 * @param period period of the interest rate
	 * @param value value of the interest rate
	 */
	public InterestRate(int period, double value){
		setPeriod(period);
		setValue(value);
	}
	
	/**
	 * @return the period
	 */
	public int getPeriod() {
		return period;
	}

	/**
	 * @param period the period to set
	 */
	public void setPeriod(int period) {
		this.period = period;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}
	
	/**
	 * @return the discountFactor
	 */
	public double getDiscountFactor() {
		return discountFactor;
	}

	/**
	 * @param discountFactor the discountFactor to set
	 */
	public void setDiscountFactor(double discountFactor) {
		this.discountFactor = discountFactor;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
