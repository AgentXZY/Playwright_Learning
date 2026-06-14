package day2;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FlightSearch {
	
	public void search() {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(
				new LaunchOptions()
				.setHeadless(false)
				.setChannel("msedge")
				.setSlowMo(100)
				);
		
		Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
				.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36 Edg/124.0.0.0");
		
		BrowserContext context = browser.newContext(contextOptions);
		
		Page page = context.newPage();
		page.navigate("https://www.goindigo.in/");
		page.waitForLoadState();
		
		// 1. Click first to open the dynamic container input
				page.locator("input[placeholder='Start typing..']").first().click();
				
				// 2. Clear and fill the input string
				page.locator("input[placeholder='Start typing..']").first().fill("Chandigarh");
				
				// 3. Select the dropdown element matching Chandigarh
				page.locator(".city-selection__list-item:has-text('Chandigarh')").first().click();
		
	}
	
}
