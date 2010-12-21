/**
 * 
 */
package at.ac.vut.pbwl.bootstrapping;

/**
 * @author stefan achleitner
 * swap rate class
 */
public class SwapRate {

	/**
	 * period of the swap rate
	 */
	private int period;
	
	/**
	 * value of the swap rate
	 */
	private double value;
	
	/**
	 * currency of the swap rate
	 */
	private String currency;
	
	
	/**
	 * default constructor
	 */
	public SwapRate() {
		
	}

	/**
	 * constructor
	 * @param swaprate value of the swaprate
	 * @param period
	 * @param currency
	 */
	public SwapRate(double swaprate, int period, String currency) {
		setCurrency(currency);
		setPeriod(period);
		setValue(swaprate);
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
	
	

}
