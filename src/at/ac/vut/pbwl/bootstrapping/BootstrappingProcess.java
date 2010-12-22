/**
 *
 */
package at.ac.vut.pbwl.bootstrapping;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 0225648, Claus, Polanka, 534, Software- and Information Engineering
 *         bootstrapping process class
 */
public class BootstrappingProcess {

	private static final int TWO_DIGITS = 2;
	private static final int ONE_YEAR = 1;

	/**
	 * default constructor
	 */
	public BootstrappingProcess() {

	}

	/**
	 * transform current swap rates into interest rates
	 * 
	 * @param swapRates
	 *            current list of swap rates
	 * @return array list of current interest rates
	 */
	public ArrayList<InterestRate> calcCurrentInterestRates() {

		SwapRateRetriever swapRateRetriever = new SwapRateRetriever(
				"https://produkte.erstegroup.com/CorporateClients/de/MarketsAndTrends/Fixed_Income/Kapitalmarktderivate/index.phtml?elem585151_index=Table_SwapRates_Europe_Europe_EUR");

		// retrieve current swap rates
		List<SwapRate> swapRates = swapRateRetriever.RetrieveCurrentSwapRates(5);

		return calcInterestRates(swapRates);
	}

	/**
	 * transform swap rates into interest rates
	 * 
	 * @param swapRates
	 *            list of swap rates
	 * @return array list of interest rates
	 */
	public ArrayList<InterestRate> calcInterestRates(List<SwapRate> swapRates) {
		ArrayList<InterestRate> result = new ArrayList<InterestRate>();

		ArrayList<Double> discontFactors = new ArrayList<Double>();
		for (SwapRate each : swapRates) {
			if (each.getPeriod() == ONE_YEAR) {
				discontFactors.add(calculateDiscontFactorForOneYear(each));
				result.add(new InterestRate(each.getPeriod(), each.getValue()));
				continue;
			}
			double discontFactor = (1 - each.getValue() / 100 * sumOf(discontFactors)) / (each.getValue() / 100 + 1);

			discontFactors.add(discontFactor);

			double interestRate = Math.pow((1 / discontFactor), 1.0 / each.getPeriod()) - 1;

			result.add(new InterestRate(each.getPeriod(), roundTwoDecimalPlacesOf(interestRate)));
		}
		return result;
	}

	private double calculateDiscontFactorForOneYear(SwapRate rate) {
		return Math.pow((rate.getValue() / 100 + 1), -rate.getPeriod());
	}

	private double sumOf(ArrayList<Double> dfs) {
		double result = 0.0;
		for (Double each : dfs) {
			result += each;
		}
		return result;
	}

	private double roundTwoDecimalPlacesOf(double number) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(TWO_DIGITS);
		return Double.parseDouble(nf.format(number * 100).replace(',', '.'));
	}
}
