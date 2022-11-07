package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.hepsiburada_page;
import utilities.ConfigReader;
import utilities.Driver;
import java.util.Set;

public class test1 {
    static WebDriver driver=Driver.getDriver();
    final static Logger logger = Logger.getLogger(test1.class);

    hepsiburada_page hepsiburada_page = new hepsiburada_page();

    @BeforeClass
    public static void setup () {
        Driver.getDriver().get(ConfigReader.getProperty("hepsiburadaUrl"));
        logger.info("Hepsiburada sayfasına gidildi.");
    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }

    @Test (priority = 1)
    public void get_page() throws InterruptedException {
        Assert.assertTrue(driver.getCurrentUrl().contains(ConfigReader.getProperty("assert_mainpage")));
        logger.info("Ana sayfada olduğu doğrulandı.");

        hepsiburada_page.cookies.click();
        logger.info("Çerezler kabul edildi.");

        hepsiburada_page.searchbox.sendKeys(ConfigReader.getProperty("searching_product"));
        logger.info("Arama çubuğuna 'Futbol Topu' kelimesi yazdırıldı.");

        hepsiburada_page.searching_button.click();
        logger.info("Arama tuşuna basıldı");

        Thread.sleep(5000);

    }

    @Test (priority = 2, dependsOnMethods = "get_page")
    public void product_choose() throws InterruptedException {
        Assert.assertTrue(driver.getCurrentUrl().contains(ConfigReader.getProperty("assert_searchingpage")));
        logger.info("Arama sonucu sayfasında olduğu doğrulandı.");

        hepsiburada_page.first_product.click();
        logger.info("İlk ürüne tıklandı.");

        Set<String> windowAllWindows = driver.getWindowHandles();
        for (String window:windowAllWindows) {driver.switchTo().window(window);}
        logger.info("Driver'ın yeni sekmeye geçişi yapıldı.");

        hepsiburada_page.add_to_cart.click();
        logger.info("Ürün sepete eklendi.");

        Thread.sleep(5000);

        Assert.assertTrue(hepsiburada_page.text_of_added_product.getText().contains(hepsiburada_page.text_of_click_product.getText()));
        logger.info("Tıklanan ürün ile sepete eklenen ürünün aynısı olduğu doğrulandı.");
    }

    @Test (priority = 3, dependsOnMethods = "product_choose")
    public void add_other_product() throws InterruptedException {
        Thread.sleep(5000);

        hepsiburada_page.keep_shopping.click();
        logger.info("'Alışverişe Devam Et' butonuna basıldı.");

        Thread.sleep(5000);

        hepsiburada_page.same_product_keep_shopping.click();
        logger.info("Sonuçlardan ilk çıkan ürüne tıklandı.");

        Thread.sleep(5000);

        Set<String>windowAllWindows = driver.getWindowHandles();

        for (String window:windowAllWindows) {driver.switchTo().window(window);}
        logger.info("Açılan 3. sekmeye driver geçişi yapıldı.");

        hepsiburada_page.other_product.click();
        logger.info("Aynı ürün farklı bir satıcıdan sepete eklendi.");

        Thread.sleep(5000);

        Assert.assertTrue(hepsiburada_page.text_of_added_other_product.getText().contains(hepsiburada_page.text_of_added_product.getText()));
        logger.info("Tıklanan ürün ile sepete eklenen ürünün aynısı olduğu doğrulandı.");

        Thread.sleep(5000);
        //hepsiburada_page.addingtoCart.click();
        //logger.info("'Sepete Git' elementine tıklandı.");
    }

    @Test (priority = 4, dependsOnMethods = "add_other_product")
    public void last_product_adding() throws InterruptedException {
        hepsiburada_page.keep_shopping.click();
        logger.info("'Alışverişe Devam Et' butonuna basıldı.");

        Thread.sleep(5000);

        hepsiburada_page.last_product.click();
        logger.info("Sonuçlardan ilk çıkan ürüne tıklandı.");

        Set<String>windowAllWindows = driver.getWindowHandles();

        for (String window:windowAllWindows) {driver.switchTo().window(window);}
        logger.info("Açılan sekmeye driver geçişi yapıldı.");

        Thread.sleep(5000);

        hepsiburada_page.last_product_adding.click();
        logger.info("Aynı ürün farklı bir satıcıdan sepete eklendi.");
        Thread.sleep(5000);

        Assert.assertTrue(hepsiburada_page.text_of_added_other_product.getText().contains(hepsiburada_page.last_product_text.getText()));
        logger.info("Tıklanan ürün ile sepete eklenen ürünün aynısı olduğu doğrulandı.");

        hepsiburada_page.addingtoCart.click();
        logger.info("'Sepete Git' elementine tıklandı.");
    }
}
