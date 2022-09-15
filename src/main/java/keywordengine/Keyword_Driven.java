package keywordengine;

import base.Baseclass;
import io.qameta.allure.internal.shadowed.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Keyword_Driven {
    public static WebDriver driver;
    public static Properties prop;
    public static Workbook workbook;
    public static Sheet sheet;
    public WebElement element;
    public final String PATH = "C:\\Users\\HP\\IdeaProjects\\Facebook_KeywordDrivenFramework\\src\\main\\java\\path\\FB_DataAdded.xlsx";

    public void startExecution(String sheetName) {
        String locatorName = null;
        String locatorValue = null;
        FileInputStream file = null;
        try {
            file = new FileInputStream(PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            workbook = WorkbookFactory.create(file);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            e.printStackTrace();
        }

        sheet = workbook.getSheet(sheetName);
        int k = 0;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            try {
                String locatorColValue = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
                if (!locatorColValue.equalsIgnoreCase("NA")) {
                    locatorName = locatorColValue.split("=")[0].trim(); // id
                    locatorValue = locatorColValue.split("=")[1].trim(); // email
                }

                String action = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
                String value = sheet.getRow(i + 1).getCell(k + 3).toString().trim();

                switch (action) {
                    case "open browser":
                        Baseclass base = new Baseclass();
                        base.initProperties();
                        if (value.equals("NA")) {
                            driver = (WebDriver) base.initProperties();
                        } else {
                            driver = base.setProperties();
                        }
                        break;

                    case "enter url":
                        if (value.equals("NA")) {
                            driver.get(prop.getProperty("url"));
                        } else {
                            driver.get(value);
                        }
                        break;
                    case "quit":
                        driver.quit();
                        break;
                    default:
                        break;
                }

                switch (locatorName) {
                    case "id":
                        element = driver.findElement(By.id(locatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            element.clear();
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        }
                        break;

                    case "name":
                        element = driver.findElement(By.name(locatorValue));
                        if (action.equalsIgnoreCase("click")) {
                            element.clear();
                            element.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            element.click();
                        }
                        break;

                    case "linkTest":
                        element = driver.findElement(By.linkText(locatorValue));
                        element.click();
                        locatorName = null;
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
            }
        }
    }
}
