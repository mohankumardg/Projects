package com.erevmax.selenium.webImpl;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface SeleniumInterface extends WebElement,SearchContext{
	public WebDriver driver=null;
	public RemoteWebDriver getRcDriver(RemoteWebDriver rcDriver);
	public WebDriver getDriver(WebDriver driver);
	public  WebDriver createBrowserInstance(String browserName);
	public void openUrl(String url);
	public void enterInput(By locator,String value);
	public void close();
	public void click(By locator);
	public WebElement findWebElement(By locator);
	public List<WebElement> findWebElements(By locator);
	public WebElement waitUntilelementDisplayed(By locator);
	public void elementWait(long milli);
	public void waitUntilElementsDisplayed(By locator);
	public void autoSelect(By lctr1, By lctr2, String input);
	public Set<String> getListOfWindows();
	public void switchToWindow(String windowId);
	public String switchToNewlyOpenedWindow();
	public void switchToOriginalWindow(String current);
	public boolean isPageLoad(String pageTitle);
	public String getCurrentUrl();
	public String getPageTitle();
	public void selectFromDropDownList(By lctr, String data);
	public int getListSize(By lctr);
	public String  checkTextAlertMessage();
	public void selectMultipleCheckBoxes(By lctr, int num);
	public void checkCheckBoxField(By lctr, int chekBoxNum);
	public void deSelectCheckbox(By lctr, int chekBoxNum);
	public void deSelectAllMultipleCheckBoxes(By lctr);
	public int sendHttpGetRequest(String url);
	public void sendHttpPostRequest(String url);
	public  boolean isURLLoaded(String resourceUrl);
	public String getText(By locator);
	public boolean isDisplayed(By locator);
	public String getAttributeValue(By lctr, String attributeName);
	

}
