import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        //System.out.println("Hello world!");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://wine-shopper.ru/");
        driver.findElement(By.cssSelector(".popup-modal-dismiss")).click();
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (WebElement link: links)
        {
            String url = link.getAttribute("href");
            verifyLink(url);
        }
        driver.quit();
    }
    public static void verifyLink(String url)
    {
        try
        {
            URL link = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200)
            {
                System.out.println(url + " - " + httpURLConnection.getResponseCode() + " " + httpURLConnection.getResponseMessage());
            }
            else
            {
                System.out.println(url + " - " + httpURLConnection.getResponseCode() + " " + httpURLConnection.getResponseMessage() + " - " + "is a broken link");
            }
        }
        catch (Exception e)
        {
            System.out.println(url + " - " + "is a broken link");
        }
    }
}