package day2;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class LaunchBrowser {

	public static void main(String[] args) {
		
		Properties config = new Properties();
		try {
			// Load the properties file
			config.load(new FileInputStream("config.properties"));
		} catch (IOException e) {
			System.out.println("Could not find config.properties file!");
			return;
		}
		
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(
				new LaunchOptions().setHeadless(false)
				.setChannel("msedge")
//				.setExecutablePath(Paths.get("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"))
				);
		Page page = browser.newPage();
		page.navigate("https://www.amazon.in/");
		page.waitForLoadState();
		
		Locator myAccount = page.locator("#nav-link-accountList-nav-line-1");
		myAccount.click();
//		page.locator(".nav-action-signin-button").click();
		
		assertThat(page).hasTitle("Amazon Sign-In");
		page.locator("#ap_email_login").fill(config.getProperty("amazon.email"));
		page.locator("input[type='submit']").click();
		page.locator("#ap_password").fill(config.getProperty("amazon.password"));
		page.locator("#auth-signin-button").click();
		page.locator("input[type='submit']").click();
		
	}
	
}