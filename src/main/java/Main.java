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

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://csgorage.com/raffles/current");
        List<WebElement> raffleDivs = driver.findElements(By.cssSelector("" +
                ".raffle_box_lg"));
        for (int i = 0; i < raffleDivs.size(); i++) {
            raffleDivs.get(i).findElement(By.cssSelector("a")).click();
            //itemEntry(driver);
            driver.navigate().back();
            raffleDivs = driver.findElements(By.cssSelector("" +
                    ".raffle_box_lg"));
        }

    }
    public static void itemEntry(WebDriver d) {
        WebElement slot = d.findElement(By.className("slots"));
        //slot.click();
        try {
            Alert alert = d.switchTo().alert();
            if (!alert.getText().equals("x")) {
                alert.accept();
                itemEntry(d);
            }
            alert.accept();
        } catch (NoAlertPresentException e) {
            return;
        }
    }
}
