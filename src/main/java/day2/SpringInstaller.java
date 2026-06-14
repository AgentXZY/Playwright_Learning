package day2;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class SpringInstaller {

	public void install() {
		
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false)
				.setSlowMo(100)
				.setChannel("msedge")
				);
		
		Page page = browser.newPage();
		page.navigate("https://start.spring.io/");
		page.waitForLoadState();
		
		page.getByText("3.5.15").click();
		page.locator("#input-group").clear();
		page.locator("#input-group").fill("com.automation");
		
		page.locator("#input-artifact").clear();
		page.locator("#input-artifact").fill("web");
		
		page.locator(".radio-content:has-text('26')").click();
		
		page.locator("#explore-dependencies").click();
		
		page.locator("#input-quicksearch").fill("web");
		page.locator("a.dependency").first().click();
		
		page.locator("#explore-dependencies").click();
		
//		page.locator("#input-quicksearch").clear();
		page.locator("#input-quicksearch").fill("lombok");
		page.locator("a.dependency").first().click();
		
		com.microsoft.playwright.Download download = page.waitForDownload(() -> {
			page.getByText("Generate").click();
		});
		
		// Saves the project zip file directly to your user downloads folder
		download.saveAs(java.nio.file.Paths.get(System.getProperty("user.home") + "/Downloads/demo.zip"));
		
		page.close();
		browser.close();
		playwright.close();
		
	}
	
}
