package utilities;

import base.BasePage;

public class Common extends BasePage{
	
	public void navigatToUrl(String url) throws InterruptedException {
		driver.get(url);
	}
}
