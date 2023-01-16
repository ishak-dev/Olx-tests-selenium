package olx_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;



import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

class OlxTests {
	private static String url;
	private static WebDriver webDriver;
	
	@BeforeAll
	static void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Isko\\Downloads\\chromedriver.exe");
		webDriver = new ChromeDriver();
		url = "https://olx.ba";
		
		webDriver.get(url);
		webDriver.manage().window().maximize();
	}

	@AfterAll
	static void tearDown() throws Exception {
		webDriver.quit();
	}

	
	void registerTest() throws InterruptedException {
		
		WebElement loginBtn = webDriver.findElement(By.linkText("PRIJAVA"));
		loginBtn.click();
		Thread.sleep(1000);
		WebElement registerBtn = webDriver.findElement(By.linkText("REGISTRUJTE SE"));
		registerBtn.click();
		Thread.sleep(8000);
		WebElement registerProfile = webDriver.findElement(By.xpath("//*[@id=\"reg-desno\"]/a"));
		registerProfile.click();
		Thread.sleep(3000);
		
		WebElement emailInput = webDriver.findElement(By.className("reg-email"));
		WebElement passwordInput = webDriver.findElement(By.className("reg-password"));
		WebElement olxNameInput = webDriver.findElement(By.className("reg-username"));
		WebElement genderBtn = webDriver.findElement(By.id("spol3"));
		Select chooseLocationDropdown = new Select(webDriver.findElement(By.id("kanton")));
		WebElement termsOfServiceBox = webDriver.findElement(By.id("tos"));
		WebElement finishRegistrationBtn = webDriver.findElement(By.xpath("//*[@id=\"reg-forma\"]/div[9]/button"));
		
		emailInput.sendKeys("isooniisooni@gmail.com");
		passwordInput.sendKeys("selenium123");
		olxNameInput.sendKeys("selenium1");
		genderBtn.click();
		chooseLocationDropdown.selectByValue("9");
		Thread.sleep(1000);
		
		Select choosePlaceDropdown = new Select(webDriver.findElement(By.id("mjesto")));
		choosePlaceDropdown.selectByValue("3879");
		Thread.sleep(2000);
		
		termsOfServiceBox.click();
		Thread.sleep(2000);
		
		finishRegistrationBtn.click();
		Thread.sleep(5000);
		
	}
	
	@Test
	void loginTest() throws InterruptedException {
		
		
		WebElement loginBtn = webDriver.findElement(By.linkText("PRIJAVA"));
		WebElement emailInput = webDriver.findElement(By.id("username")); 
		WebElement passwordInput = webDriver.findElement(By.id("password"));
		WebElement loginBtn2 = webDriver.findElement(By.id("btnlogin1"));
		
		loginBtn.click();
		emailInput.sendKeys("isooniisooni@gmail.com");
		passwordInput.sendKeys("selenium123");
		
		loginBtn2.click();
		
		Thread.sleep(1000);
	}
	
	
	void testArticle() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(webDriver,Duration.ofSeconds(5));
		
		webDriver.navigate().back();
		Thread.sleep(1000);
		
		List<WebElement> articleList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("artikal")));
		System.out.println(articleList.size());
		
		articleList.get(10).click();
		
		
		//webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		//Thread.sleep(3000);
		
		
		WebElement articleWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"modal\"]")));
		WebElement openArticleBtn = webDriver.findElement(By.className("otvori-artikal"));
		
		openArticleBtn.click();
		Thread.sleep(2000);
		
		String handle1 = webDriver.getWindowHandle();
		for(String handle :  webDriver.getWindowHandles()) {
			if(!handle.equals(handle1)) {
				webDriver.switchTo().window(handle);
				break;
				
			}
		}
		
		//gallery
		WebElement openGalleryBtn = webDriver.findElement(By.xpath("/html/body/div[4]/div[3]/div[1]/div[1]/div[1]/div/div[1]/ul/li[3]"));
		openGalleryBtn.click();
		Thread.sleep(1000);
		
		//list next
		for(int i = 0; i < 5; i++) {
			WebElement listNextBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[9]/div/div/div[2]/a[2]")));
			listNextBtn.click();
			Thread.sleep(1000);
		}
		
		//close gallery
		WebElement closeGallery = webDriver.findElement(By.className("close"));
		closeGallery.click();
		
		//test article tabs
		WebElement detailsBtn = webDriver.findElement(By.xpath("//*[@id=\"desno\"]/div[1]/div/div[1]/ul/li[2]"));
		WebElement questionsBtn = webDriver.findElement(By.xpath("//*[@id=\"desno\"]/div[1]/div/div[1]/ul/li[4]"));
		questionsBtn.click();
		detailsBtn.click();
		
		webDriver.navigate().back();
		Thread.sleep(1000);
		
		webDriver.switchTo().window(handle1);
		
		Thread.sleep(3000);
	}
	
	
	
	void searchTest() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(webDriver,Duration.ofSeconds(5));
		
		
		WebElement searchInput = webDriver.findElement(By.name("trazilica"));
		WebElement searchBtn = webDriver.findElement(By.className("btn-mainsearch"));
		
		searchInput.sendKeys("mercedes");
		searchBtn.click();
		
		String result1 = webDriver.findElement(By.xpath("//*[@id=\"pretraga-json\"]/div[2]/span")).getText();
		
		WebElement fromInput = webDriver.findElement(By.id("od"));
		fromInput.sendKeys("15000");
		
		WebElement refreshBtn = webDriver.findElement(By.className("dugmezaosvjezi"));
		refreshBtn.click();
		
		Thread.sleep(1000);
		
		String result2 = webDriver.findElement(By.xpath("//*[@id=\"pretraga-json\"]/div[2]/span")).getText();
		
		Thread.sleep(2000);
		
		System.out.println(result1 + " " + result2);
		
		webDriver.navigate().back();
		
		assertNotEquals(result1, result2);
		
	}
	
	
	void testNavigation() {
		
		ArrayList<String> navList = new ArrayList<String>();
		navList.add("Moj profil");
		navList.add("Moj OLX");
		navList.add("Postavke");
		navList.add("Spašeni artikli");
		navList.add("Spašeni korisnici");
		
		ArrayList<String> urlList = new ArrayList<String>();
		urlList.add("https://www.olx.ba/profil/selenium1");
		urlList.add("https://www.olx.ba/mojpik");
		urlList.add("https://www.olx.ba/postavke");
		urlList.add("https://www.olx.ba/mojpik/spaseno/artikli");
		urlList.add("https://www.olx.ba/mojpik/spaseno/korisnici");
		
		
		for(int i=0;i<navList.size();i++) {
			WebElement profileNavigationBtn = webDriver.findElement(By.xpath("//*[@id=\"u_h\"]"));
			profileNavigationBtn.click();
			WebElement navItemBtn = webDriver.findElement(By.linkText(navList.get(i)));
			navItemBtn.click();
			assertEquals(urlList.get(i),webDriver.getCurrentUrl());
		}
		
	}
	
	
	
	void chatTest() throws InterruptedException {
		String message = "Test 1234";
		WebDriverWait wait = new WebDriverWait(webDriver,Duration.ofSeconds(5));
 		
		webDriver.navigate().to("https://www.olx.ba/profil/Masina_");
		Thread.sleep(2000);
		
		WebElement openMessBtn = webDriver.findElement(By.linkText("POŠALJI PORUKU"));
		openMessBtn.click();
		Thread.sleep(1000);
		
		WebElement textarea = webDriver.findElement(By.xpath("//*[@id=\"tekst_poruke\"]"));
		textarea.sendKeys(message);
		WebElement sendMessBtn = webDriver.findElement(By.id("posaljiporuku-final"));
		sendMessBtn.click();
		
		
		WebElement messModal = webDriver.findElement(By.id("modal"));
		wait.until(ExpectedConditions.invisibilityOf(messModal));
		
		webDriver.navigate().to("https://www.olx.ba/poruke/outbox");
		List<WebElement> chats = webDriver.findElements(By.xpath("//*[@id=\"zascrollanje\"]/ul"));
		chats.get(0).click();
		
		Thread.sleep(2000);
		
		List<WebElement> messages = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("tekst_poruke")));
		String lastMess = messages.get(messages.size()-1).getText();
		
		assertEquals(lastMess, message);
		
	}
	
	
	void filterTest() throws InterruptedException {
		webDriver.navigate().to(url);
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
		WebElement searchInput = webDriver.findElement(By.className("searchinput"));
		WebElement searchBtn = webDriver.findElement(By.className("btn-mainsearch"));
		
		searchInput.sendKeys("mercedes");
		searchBtn.click();
		
		String resNum1 = webDriver.findElement(By.xpath("//*[@id=\"pretraga-json\"]/div[2]/span")).getText();
		
		
		Thread.sleep(1000);
		WebElement onlyCarsBtn = webDriver.findElement(By.xpath("//*[@id=\"filter-kategorije-div\"]/ul/ul/li[1]/a"));
		onlyCarsBtn.click();
		
		String resNum2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"pretraga-json\"]/div[2]/span"))).getText();
		
		
		System.out.println(resNum1+resNum2);
		assertNotEquals(resNum1, resNum2);
		
		resNum1 = resNum2;
		
		WebElement fromInput = webDriver.findElement(By.id("od"));
		WebElement toInput = webDriver.findElement(By.id("do"));
		fromInput.sendKeys("15000");
		toInput.sendKeys("150000");
		WebElement refreshBtn = webDriver.findElement(By.xpath("//*[@id=\"filter-cijena-div\"]/span"));
		refreshBtn.click();
		
		Thread.sleep(1000);
		resNum2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"pretraga-json\"]/div[2]/span"))).getText();
	
		System.out.println(resNum1+resNum2);
		assertNotEquals(resNum1, resNum2);
		System.out.println("proso");
		//data of manufacture
		Select dom = new Select(webDriver.findElement(By.className("minmax")));
		dom.selectByValue("2010");
		webDriver.findElement(By.xpath("//*[@id=\"filter-godiste-div\"]/span")).click();

		resNum1 = resNum2;
		resNum2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"pretraga-json\"]/div[2]/span"))).getText();
		System.out.println(resNum1+resNum2);
		assertNotEquals(resNum1, resNum2);
	}
	
	
	
	void postArticle() throws InterruptedException {
		webDriver.navigate().to(url);
		WebElement postBtn = webDriver.findElement(By.linkText("OBJAVITE"));
		postBtn.click();
		
		Thread.sleep(2000);
		WebElement postArticleBtn = webDriver.findElement(By.xpath("//*[@id=\"vrstaobjave\"]/div/div[1]/a"));
		postArticleBtn.click();
		
		WebElement articleHeaderInput = webDriver.findElement(By.xpath("//*[@id=\"kategorija_sug\"]"));
		articleHeaderInput.sendKeys("slike za ukras");
		
		webDriver.findElement(By.id("pronadjikat")).click();
		Thread.sleep(2000);
		webDriver.findElement(By.xpath("//*[@id=\"prijedlog_kat_331\"]")).click();
		Thread.sleep(2000);
		webDriver.findElement(By.xpath("//*[@id=\"korak1btn\"]")).click();
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		webDriver.findElement(By.id("podnaslovartikla")).sendKeys("test za prodaju slika");
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		webDriver.findElement(By.xpath("//*[@id=\"polje2412\"]")).sendKeys("30");
		webDriver.findElement(By.xpath("//*[@id=\"polje2413\"]")).sendKeys("30");
		
		WebElement selectMotiveInput = webDriver.findElement(By.xpath("//*[@id=\"polje1732\"]"));
		Select selectMotive = new Select(selectMotiveInput);
		selectMotive.selectByIndex(9);
		
		WebElement selectTechniqueInput = webDriver.findElement(By.xpath("//*[@id=\"polje1111\"]"));
		Select selectTechnique = new Select(selectTechniqueInput);
		selectTechnique.selectByIndex(1);
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
JavascriptExecutor js = (JavascriptExecutor) webDriver;
		
		js.executeScript("window.scrollBy(0,900)");
		Thread.sleep(2000);
		WebElement nextBtn3 = webDriver.findElement(By.id("korak3btn"));
		nextBtn3.click();
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		webDriver.findElement(By.id("cijena")).sendKeys("250");
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		js.executeScript("window.scrollBy(0,900)");
		
		Thread.sleep(2000);
	
		WebElement nextBtn4 = webDriver.findElement(By.id("korak4btn"));
		nextBtn4.click();
js.executeScript("window.scrollBy(0,900)");
		
		Thread.sleep(2000);
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		webDriver.findElement(By.xpath("//*[@id=\"textareaKoristeno\"]")).sendKeys("ovo je test verzija, slika je dobra");
js.executeScript("window.scrollBy(0,900)");
		
		Thread.sleep(7000);
		//2 btns with same id exist
		webDriver.findElement(By.className("objava_artikla_zavrsi")).click();
js.executeScript("window.scrollBy(0,900)");
		
		Thread.sleep(2000);
		webDriver.findElement(By.xpath("//*[@id=\"dugmeizdvoji\"]")).click();
		
		
	}
	
	
	void testSavingArticle() throws InterruptedException {
		webDriver.navigate().to(url);
		
		List<WebElement> articleList =	webDriver.findElements(By.className("artikal"));
		WebElement article = articleList.get(4);
		System.out.println(article);
		article.click();
		
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		webDriver.findElement(By.linkText("Otvori oglas")).click();
		
		String handle = webDriver.getWindowHandle();
		
		for(String handle1 : webDriver.getWindowHandles()) {
			if(!handle.equals(handle1)) {
				webDriver.switchTo().window(handle1);
				break;
			}
		}
		String urlOriginal = webDriver.getCurrentUrl();
		
		webDriver.findElement(By.xpath("//*[@id=\"prvidio\"]/a[2]")).click();
		
		webDriver.findElement(By.xpath("//*[@id=\"mojpikgore\"]")).click();
		
		webDriver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div/ul[2]/li[1]/a")).click();
		
		List<WebElement> savedArticleList = webDriver.findElements(By.className("na"));
		WebElement firstArticle = savedArticleList.get(0);
		System.out.println(firstArticle);
		
		Thread.sleep(2000);
		firstArticle.click();
		
		
		Thread.sleep(2000);
		String url2 = webDriver.getCurrentUrl();
		
		assertEquals(urlOriginal, url2);
		
	} 
	
	
	void updateProfil() throws InterruptedException {
		webDriver.findElement(By.xpath("//*[@id=\"u_h\"]")).click();
		webDriver.findElement(By.linkText("Postavke")).click();
		
		WebElement nameInput = webDriver.findElement(By.id("ime"));
		WebElement surnameInput = webDriver.findElement(By.id("prezime"));
		WebElement zipInput = webDriver.findElement(By.id("zip"));
		WebElement addressInput = webDriver.findElement(By.id("adresa"));
		WebElement dayList =  webDriver.findElement(By.id("g_dan"));
		Select day = new Select(dayList);
		WebElement monthList = webDriver.findElement(By.id("g_mjesec"));
		Select month = new Select(monthList);
		WebElement yearList = webDriver.findElement(By.id("g_godina"));
		Select year = new Select(yearList);
		
		
		nameInput.sendKeys("testIme");
		surnameInput.sendKeys("testPrezime");
		zipInput.sendKeys("71000");
		addressInput.sendKeys("Test 71");
		day.selectByIndex(6);
		month.selectByIndex(7);
		year.selectByIndex(30);
		
		
		webDriver.findElement(By.xpath("//*[@id=\"forma_postavke\"]/form/input[2]")).click();
		Thread.sleep(4000);
	}
	

	void elementHover() throws InterruptedException {
		Actions action = new Actions(webDriver);
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		
		webDriver.navigate().to(url);
		
		js.executeScript("window.scrollBy(0,300)");
		
		WebElement vozila = webDriver.findElement(By.linkText("Vozila"));
		WebElement nekretnine = webDriver.findElement(By.linkText("Nekretnine"));
		WebElement poslovi = webDriver.findElement(By.linkText("Poslovi"));
		WebElement usluge = webDriver.findElement(By.linkText("Usluge"));
		WebElement kompjuteri = webDriver.findElement(By.linkText("Kompjuteri"));
		WebElement dom = webDriver.findElement(By.linkText("Moj dom"));
		WebElement biznis = webDriver.findElement(By.linkText("Biznis i industrija"));
		
		action.moveToElement(vozila).perform();
		Thread.sleep(2000);
		action.moveToElement(nekretnine).perform();
		Thread.sleep(2000);
		action.moveToElement(poslovi).perform();
		Thread.sleep(2000);
		action.moveToElement(usluge).perform();
		Thread.sleep(2000);
		action.moveToElement(kompjuteri).perform();
		Thread.sleep(2000);
		action.moveToElement(dom).perform();
		Thread.sleep(2000);
		action.moveToElement(biznis).perform();
		Thread.sleep(2000);
	}
	
	@Test
	void deleteArticle() throws InterruptedException {
		List<WebElement> myArticlesList = webDriver.findElements(By.className("na"));
		int articleSize = myArticlesList.size();
		myArticlesList.get(0).click();
		webDriver.findElement(By.linkText("ZAVRŠI PRODAJU")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.linkText("BRIŠI ARTIKAL")).click();
		Thread.sleep(1000);
		webDriver.findElement(By.linkText("BRIŠI OGLAS")).click();
		webDriver.navigate().to(url+"/mojpik");
		List<WebElement> myArticleList2 = webDriver.findElements(By.className("na"));
		int articleSize2 = myArticleList2.size();
		
		assertNotEquals(articleSize2, articleSize);
				
				
		
	}
}
