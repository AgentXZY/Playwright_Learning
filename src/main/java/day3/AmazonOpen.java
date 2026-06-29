package day3;

import java.nio.file.Paths;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext; // Added import
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class AmazonOpen {
	
	public void open() {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().
				setHeadless(false).
				setExecutablePath(Paths.get("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe")).
				setSlowMo(100)
				);
		
		// 1. Create an isolated browser context (like an incognito window)
		BrowserContext context = browser.newContext();
		
		// 2. Open the page inside that specific context
		Page page = context.newPage();
		Page page2 = context.newPage();
		
		page.navigate("https://www.amazon.in/");
		page.waitForLoadState();
		
		Locator search = page.getByPlaceholder("Search Amazon.in");
		search.click();
		search.fill("Ps5");
		search.press("Enter");
	}
}