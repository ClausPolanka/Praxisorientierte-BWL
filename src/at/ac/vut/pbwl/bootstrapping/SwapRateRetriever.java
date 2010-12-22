/**
 *
 */
package at.ac.vut.pbwl.bootstrapping;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

/**
 * @author 0225648, Claus, Polanka, 534, Software- and Information Engineering
 *         swap rate retriever class
 */
public class SwapRateRetriever {

	private static final String NEW_LINE = "\n";
	private static final int PERIOD_LOWER_THAN_TEN = 2;
	private static final String END_OF_SWAP_RATES = "Markt Swapsätze";
	private static final String BEGINNING_OF_SWAP_RATES = "EUR 1Y IRS";
	private static final String ACCEPT_BUTTON = "elem581148_doAccept";
	private static final int SECOND_FORM = 2;
	/**
	 * URL to retrieve swap rates
	 */
	private String URL;

	/**
	 * default constructor
	 */
	public SwapRateRetriever() {

	}

	/**
	 * default constructor
	 */
	public SwapRateRetriever(String url) {
		this.URL = url;
	}

	/**
	 * retrieve current swap rates from specified URL
	 * 
	 * @param periods
	 *            number of periods to retrieve
	 * @return ArrayList of current swap rates
	 */
	public ArrayList<SwapRate> RetrieveCurrentSwapRates(int periods) {
		ArrayList<SwapRate> result = new ArrayList<SwapRate>();
		try {
			WebClient webClient = new WebClient();
			StringTokenizer tokenizer = getTokenizerForSwapRates(webClient);
			for (int i = 0; i < periods; i++) {
				result.add(createSwapRateOf(tokenizer.nextToken()));
			}
			webClient.closeAllWindows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private StringTokenizer getTokenizerForSwapRates(WebClient webClient) throws IOException, MalformedURLException {
		HtmlPage treasuryPage = getErsteBankTreasuryPage(webClient);
		String pageAsText = treasuryPage.asText();
		String swapRates = filterSwapRatesOf(pageAsText);
		StringTokenizer tokenizer = new StringTokenizer(swapRates, NEW_LINE);
		return tokenizer;
	}

	private HtmlPage getErsteBankTreasuryPage(WebClient webClient) throws IOException, MalformedURLException {
		HtmlPage page = webClient.getPage(URL);
		List<HtmlForm> forms = page.getForms();
		HtmlForm form = forms.get(SECOND_FORM);
		HtmlSubmitInput button = form.getInputByName(ACCEPT_BUTTON);
		return button.<HtmlPage> click();
	}

	private String filterSwapRatesOf(String pageAsText) {
		return pageAsText.substring(pageAsText.indexOf(BEGINNING_OF_SWAP_RATES), pageAsText.indexOf(END_OF_SWAP_RATES));
	}

	private SwapRate createSwapRateOf(String row) {
		StringTokenizer rowToken = new StringTokenizer(row);
		SwapRate swapRate = new SwapRate();
		swapRate.setCurrency(rowToken.nextToken());
		swapRate.setPeriod(extractPeriodOf(rowToken.nextToken()));
		rowToken.nextToken(); // Jump over element (INT RATE SWAP).
		swapRate.setValue(Double.parseDouble(rowToken.nextToken().replace(',', '.')));
		return swapRate;
	}

	private int extractPeriodOf(String token) {
		if (token.length() == PERIOD_LOWER_THAN_TEN) {
			return Integer.parseInt(token.substring(0, 1));
		}
		return Integer.parseInt(token.substring(0, 2));
	}

	/**
	 * @return the uRL
	 */
	public String getURL() {
		return URL;
	}

	/**
	 * @param url
	 *            the uRL to set
	 */
	public void setURL(String url) {
		URL = url;
	}
}
