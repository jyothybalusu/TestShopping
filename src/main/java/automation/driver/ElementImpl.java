package automation.driver;

import org.openqa.selenium.*;

import java.util.List;

/**
 * Created by Aram on 31/01/2018.
 */


public class ElementImpl implements Element {

    private final WebElement element;

    public ElementImpl(final WebElement element) {
        this.element = element;
    }

    /**
     * Overrides all the methods of WebElement
     */
 @Override
    public void click() {
    element.click();
    }

    public void sendKeys(CharSequence... key) {
        element.sendKeys(key);
    }

    public void submit() {
        element.submit();
    }

    public void clear() {
        element.clear();
    }

    public String getTagName() {
        return element.getTagName();
    }

    public String getAttribute(String s) {
        element.getAttribute(s);
        return s;
    }

    public boolean isSelected() {
        return element.isSelected();
    }

    public boolean isEnabled() {
        return element.isEnabled();
    }

    public String getText() {
        return element.getText();
    }

    public List<WebElement> findElements(By var1) {
        return element.findElements(var1);
    }

    public WebElement findElement(By var1) {
        return element.findElement(var1);
    }

    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    public Point getLocation() {
        return element.getLocation();
    }

    public Dimension getSize() {
        return element.getSize();
    }

    public Rectangle getRect() {
        return element.getRect();
    }

    public String getCssValue(String s) {
        return element.getCssValue(s);
    }

    public <X> X getScreenshotAs(OutputType<X> var1) {
        return element.getScreenshotAs(var1);
    }
}
