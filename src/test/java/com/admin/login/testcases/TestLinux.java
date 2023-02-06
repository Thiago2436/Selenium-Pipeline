package com.admin.login.testcases;

import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class TestLinux {

  public static void main(String[] args) throws MalformedURLException {
    // TODO Auto-generated method stub

    /*
     * String hubURL = "http://172.26.191.15:4444/wd/hub"; DesiredCapabilities caps
     * = new DesiredCapabilities(); caps.setBrowserName("chrome");
     * caps.setPlatform(Platform.LINUX);
     * 
     * 
     * 
     * ChromeOptions options = new ChromeOptions(); options.merge(caps);
     * 
     * WebDriver driver = new RemoteWebDriver(new URL(hubURL), caps);
     * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     * driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
     * driver.get("http://www.google.com/ncr"); WebElement element =
     * driver.findElement(By.name("q"));
     * 
     * element.sendKeys("TestingBot"); element.submit();
     * 
     * System.out.println(driver.getTitle()); //driver.quit();
     */

    /*
     * String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new
     * java.util.Date()); System.out.println("TimeStamp>>>>"+timeStamp);
     */

    String tempString = "V Supplier {{timestamp}}";

    System.out.println(tempString.contains("{{timestamp}}"));
    String finalString = "";
    if (tempString.contains("{{timestamp}}")) {
      System.out.println("coming");
      System.out.println("String after Removing 'a' = " + tempString.replace("{{timestamp}}", ""));
      String ts1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.now());
      System.out.println("ts1>>>>" + ts1);
      String str = ts1.toString();
      System.out.println("New Timespan : " + str);
      finalString = tempString.replace("{{timestamp}}", "") + str;
      }
    System.out.println("Final String >>>>>" + finalString);

    String userName = "" + (int) (Math.random() * Integer.MAX_VALUE);
    String emailID = "User" + userName + "@example.com";
    System.out.println("EmailId>>>>" + emailID);

    generateRandomEmail(10);

    String myEmailAddress = "thisismyaddress+" + System.nanoTime() + "@Gmail.com";
    System.out.println("Time Email Id >>>>" + myEmailAddress);
    System.out.println("<<<Random Method >>>>");
    generateRandomEmail(10);
    generateRandomAlphanumeric(3);
    generateRandomString(4);
    }

  public String appendTimestamp(String text) {
    return text += System.nanoTime();
    }

  public static String generateRandomEmail(int length) {
    String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "_-.";
    String email = "";
    String temp = RandomStringUtils.random(length, allowedChars);
    email = temp.substring(0, temp.length() - 9) + "@testdata.com";
    System.out.println("New Email >>>>>" + email);
    return email;
    }

  public static String generateRandomAlphanumeric(int length) {
    String randomAlphanumeric = generateRandomCharSequence(length, "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");
    System.out.println("RandomAlphaNumberic >>>" + randomAlphanumeric);
    return randomAlphanumeric;
    }

  public static String generateRandomString(final int length) {
    String randomString;

    randomString = generateRandomCharSequence(length, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    System.out.println("RandomString>>>" + randomString);
    return randomString;
    }

  public String generateRandomNumber(final int length) {
    String randomNumber = generateRandomCharSequence(length, "1234567890");
    System.out.println("randomNumber>>>" + randomNumber);
    return randomNumber;
    }

  private static String generateRandomCharSequence(final int length, final String possibleChars) {
    StringBuilder builder = new StringBuilder();
    Random rnd = new Random();
    while (builder.length() < length) {
      int index = (int) (rnd.nextFloat() * possibleChars.length());
      builder.append(possibleChars.charAt(index));
      }

    return builder.toString();
    }

  }
