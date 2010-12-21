/**
 * 
 */
package at.ac.vut.pbwl.bootstrapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MATRIKELNUMMER, VORNAME, NACHNAME, STUDIENKENNZAHL, STUDIUM
 * bootstrapping process class
 */
public class BootstrappingProcess {

	/**
	 * default constructor
	 */
	public BootstrappingProcess(){
		
	}
	
	/**
	 * transform current swap rates into interest rates
	 * @param swapRates current list of swap rates
	 * @return array list of current interest rates
	 */
	public ArrayList<InterestRate> calcCurrentInterestRates (){

		//specify and create new swap rate retriever instance
		SwapRateRetriever swapRateRetriever = new SwapRateRetriever("https://produkte.erstegroup.com/CorporateClients/de/MarketsAndTrends/Fixed_Income/Kapitalmarktderivate/index.phtml?elem585151_index=Table_SwapRates_Europe_Europe_EUR");
		
		//retrieve current swap rates
		List<SwapRate> swapRates = swapRateRetriever.RetrieveCurrentSwapRates(5);
		
		return calcInterestRates(swapRates);
	}
	
	
	/**
	 * transform swap rates into interest rates
	 * @param swapRates list of swap rates
	 * @return array list of interest rates
	 */
	public ArrayList<InterestRate> calcInterestRates (List<SwapRate> swapRates){
		//TODO
		return null;
	}
}
