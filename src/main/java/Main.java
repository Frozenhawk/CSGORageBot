/**
 * Created by scott on 15/08/15.
 */
import com.sun.corba.se.spi.orbutil.fsm.Action;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String args[]) throws InterruptedException {
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffprofile = profile.getProfile("default");
        WebDriver driver = new FirefoxDriver(ffprofile);
        driver.get("http://csgorage.com/free-raffles/current");
        List<WebElement> raffleDivs = driver.findElements(By.cssSelector("" +
                ".raffle_box_lg"));
        for (int i = 0; i < raffleDivs.size(); i++) {
            raffleDivs.get(i).findElement(By.cssSelector("a")).click();
            int timer = 17;
            int currentTime = 0;
            JavascriptExecutor js = (JavascriptExecutor)driver;
            do{
                js.executeScript("window.scrollBy(0,250)", "");
                Thread.sleep(5000);
                currentTime++;
            }while(currentTime < timer);
            //TODO scrolling to bottom of the page
            itemEntry(driver);
            //driver.navigate().back();
            //raffleDivs = driver.findElements(By.cssSelector("" +
                   // ".raffle_box_lg"));
        }

    }
    public static void itemEntry(WebDriver d) {
        WebElement slot = d.findElement(By.xpath("//div[@class='slots']"));
        if(slot == null) return;
        System.out.println(slot.getTagName());
        slot.click();
        d.findElement(By.xpath("//button[@id='getslots']")).click();
        try {
            Alert alert = d.switchTo().alert();
            if (!alert.getText().equals("Success!")) {
                alert.accept();
                itemEntry(d);
            }
            alert.accept();
        } catch (NoAlertPresentException e) {
            return;
        }
    }
}
