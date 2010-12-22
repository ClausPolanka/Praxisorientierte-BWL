/**
 *
 */
package at.ac.vut.pbwl.bootstrapping.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.ac.vut.pbwl.bootstrapping.BootstrappingProcess;
import at.ac.vut.pbwl.bootstrapping.InterestRate;
import at.ac.vut.pbwl.bootstrapping.SwapRate;

/**
 * @author stefan achleitner test class of the bootstrapping implementation
 */
public class BootstrappingProcessTest {

	/**
	 * test list of swap rates
	 */
	private List<SwapRate> swapRates;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		// add test swap rates
		swapRates = new ArrayList<SwapRate>();
		swapRates.add(new SwapRate(4.00, 1, "EUR"));
		swapRates.add(new SwapRate(4.50, 2, "EUR"));
		swapRates.add(new SwapRate(5.00, 3, "EUR"));
		swapRates.add(new SwapRate(5.50, 4, "EUR"));
		swapRates.add(new SwapRate(6.00, 5, "EUR"));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		swapRates = null;
	}

	/**
	 * Test method for
	 * {@link at.ac.vut.pbwl.bootstrapping.BootstrappingProcess#calcInterestRates(java.util.List)}
	 * .
	 */
	@Test
	public void testCalcInterestRates() {
		// create new BootstrappingProcess instance
		BootstrappingProcess bootstrappingProcess = new BootstrappingProcess();

		// create new InterestRate ArrayList
		ArrayList<InterestRate> interestRates = new ArrayList<InterestRate>();

		// calculate interest rates
		interestRates = bootstrappingProcess.calcInterestRates(swapRates);

		// compare results
		assertEquals(interestRates.get(0).getValue(), 4.00, 0.00);
		assertEquals(interestRates.get(1).getValue(), 4.51, 0.01);
		assertEquals(interestRates.get(2).getValue(), 5.03, 0.03);
		assertEquals(interestRates.get(3).getValue(), 5.57, 0.07);
		assertEquals(interestRates.get(4).getValue(), 6.13, 0.13);
	}

	/**
	 * This test just prints out the calculated interest rates based on the
	 * downloaded Swaprates. Assertions don't make sense because rates could
	 * change online this would cause the test to break.
	 */
	@Test
	public void testCalcInterestRatesWithDownloadedSwaprates() {
		ArrayList<InterestRate> interestRates = new BootstrappingProcess().calcCurrentInterestRates();
		for (InterestRate each : interestRates) {
			System.out.println(each.getValue());
		}
	}
}
