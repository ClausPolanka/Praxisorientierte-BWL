/**
 * 
 */
package at.ac.vut.pbwl.bootstrapping;

import java.util.ArrayList;

/**
 * @author MATRIKELNUMMER, VORNAME, NACHNAME, STUDIENKENNZAHL, STUDIUM
 * swap rate retriever class
 */
public class SwapRateRetriever {

	/**
	 * URL to retrieve swap rates
	 */
	private String URL;
	
	/**
	 * default constructor
	 */
	public SwapRateRetriever(){
		
	}
	
	/**
	 * default constructor
	 */
	public SwapRateRetriever(String url){
		this.URL = url;
	}

	/**
	 * retrieve current swap rates from specified URL
	 * @param periods number of periods to retrieve
	 * @return ArrayList of current swap rates
	 */
	public ArrayList<SwapRate> RetrieveCurrentSwapRates(int periods){
		//TODO
		return null;
	}
	
	
	/**
	 * @return the uRL
	 */
	public String getURL() {
		return URL;
	}

	/**
	 * @param url the uRL to set
	 */
	public void setURL(String url) {
		URL = url;
	}
}
