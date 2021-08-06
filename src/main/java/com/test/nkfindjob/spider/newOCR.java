package com.test.nkfindjob.spider;

import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.util.List;

import static java.lang.Thread.sleep;

public class newOCR {
    public static void main(String[] args) throws TesseractException, InterruptedException, NoSuchElementException, IOException {
        System.setProperty("webdriver.chrome.driver", "C://dev//chromedriver.exe");
        ////////////////////////////////////////////////// 驱动地址↑ 注意修改
        new spiderthread1().start();
//        new spiderthread2().start();
//        new spiderthread3().start();

    }
}
class spiderthread1 extends Thread{
    public void run()
    {
        ChromeOptions options = new ChromeOptions();
        String url_str="https://www.zhipin.com/c100010000-p100301/?page=";// xxxx
        //              https://www.zhipin.com/c100010000-p100101/?page=3&ka=page-3
        int count=1;
        for(;count<=8;count++) {
            File file=new File("测试工程师.csv");
            try {
                WebDriver driver=new ChromeDriver(options);
                driver.get(url_str+count+"&ka=page-"+count);
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<WebElement> positions = driver.findElements(By.cssSelector("div.info-primary > div.primary-wrapper > div.primary-box > div.job-title>span.job-name>a"));
                List<WebElement> firms = driver.findElements(By.cssSelector("div.info-primary > div.info-company > div.company-text > h3.name > a"));
                List<WebElement> profession = driver.findElements(By.cssSelector("div.info-primary > div.info-company > div.company-text > p > a"));
                List<WebElement> capitals_scales = driver.findElements(By.cssSelector("div.info-primary > div.info-company > div.company-text > p"));
                List<WebElement> salaries = driver.findElements(By.cssSelector(" div.info-primary > div.primary-wrapper > div > div.job-limit.clearfix > span.red"));
                List<WebElement> locations = driver.findElements(By.cssSelector("div.info-primary > div.primary-wrapper > div.primary-box > div.job-title>span.job-area-wrapper>span.job-area"));
                List<WebElement> techs = driver.findElements(By.cssSelector("div.tags"));
                List<WebElement> requirements = driver.findElements(By.cssSelector("div.info-primary > div.primary-wrapper > div.primary-box > div.job-limit.clearfix > p"));
                List<WebElement> benefits = driver.findElements(By.cssSelector("div.info-desc"));
                System.out.println("data: " + benefits.size());
                if(benefits.size()==0)
                {
                    driver.get(url_str+count+"&ka=page-"+count);

                    positions = driver.findElements(By.cssSelector("div.info-primary > div.primary-wrapper > div.primary-box > div.job-title>span.job-name>a"));
                    firms = driver.findElements(By.cssSelector("div.info-primary > div.info-company > div.company-text > h3.name > a"));
                    profession = driver.findElements(By.cssSelector("div.info-primary > div.info-company > div.company-text > p > a"));
                    capitals_scales = driver.findElements(By.cssSelector("div.info-primary > div.info-company > div.company-text > p"));
                    salaries = driver.findElements(By.cssSelector(" div.info-primary > div.primary-wrapper > div > div.job-limit.clearfix > span.red"));
                    locations = driver.findElements(By.cssSelector("div.info-primary > div.primary-wrapper > div.primary-box > div.job-title>span.job-area-wrapper>span.job-area"));
                    techs = driver.findElements(By.cssSelector("div.tags"));
                    requirements = driver.findElements(By.cssSelector("div.info-primary > div.primary-wrapper > div.primary-box > div.job-limit.clearfix > p"));
                    benefits = driver.findElements(By.cssSelector("div.info-desc"));
                    System.out.println("data2: " + benefits.size());
                }
                for (int i = 0; i < positions.size(); i++) {
                    PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true),"GB18030")));
                    writer.write("\n");
//                    BufferedWriter writer =new BufferedWriter(new FileWriter(file,true));
//                    String detail_str=positions.get(i).getAttribute("href");
//                    WebDriver next=new ChromeDriver(options);
//                    WebElement detail=null;
//                    try{
//                        next.get(detail_str);
//                        detail = next.findElement(By.cssSelector("div.text"));
//                    }
//                    catch(Exception e) {
//                        System.out.println("re-get");
//                        try {
//                            next.get(detail_str);
//                            detail = next.findElement(By.cssSelector("div.text"));
//                        } catch (Exception ee) {
//                            System.out.println("error2");
//                        }
//                    }
//                    String detaill="";if(detail!=null)detaill=detail.getText();
//                    writer.newLine();
                    List<WebElement> tags=techs.get(i).findElements(By.cssSelector("span.tag-item"));
                    String temp="";
                    for(WebElement element:tags)temp+=element.getText();
                    String capital_scale=capitals_scales.get(i).getText().replace(profession.get(i).getText(),"");
                    ///////////////////////////////
                    int flag=0;boolean bre=true;
                    while(bre)
                    {if(capital_scale.charAt(flag)>='0'&&capital_scale.charAt(flag)<='9'){bre=false;} flag++;}
                    String capital=capital_scale.substring(0,flag-1);
                    String scale=capital_scale.substring(flag-1,capital_scale.length());
//                    if(detaill.equals("")) {
//                        try {
//                            sleep(10000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
                    writer.write("测试工程师" + "," +firms.get(i).getText()+","+profession.get(i).getText()+","+scale+","+capital+"," + salaries.get(i).getText() + ","+locations.get(i).getText().substring(0,2)+","+requirements.get(i).getText()+"," + benefits.get(i).getText().replace("，","") + ","+temp);
                    System.out.println(positions.get(i).getText() + "," +firms.get(i).getText()+","+profession.get(i).getText()+","+scale+","+capital+"," + salaries.get(i).getText() + ","+locations.get(i).getText().substring(0,2)+","+requirements.get(i).getText()+"," + benefits.get(i).getText().replace("，","") + ","+temp);
//                    next.close();
                    writer.close();
                }

                driver.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class spiderthread2 extends Thread{
    public void run()
    {
        ChromeOptions options = new ChromeOptions();
        String url_str="https://www.zhipin.com/c100010000-p100101/?page=";
        //              https://www.zhipin.com/c100010000-p100101/?page=3&ka=page-3
        int count=11;
        for(;count<=20;count++) {
            File file=new File("java.csv");
            try {
                WebDriver driver=new ChromeDriver(options);
                driver.get(url_str+count+"&ka=page-"+count);
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<WebElement> positions = driver.findElements(By.cssSelector("div.info-primary > div.primary-wrapper > div.primary-box > div.job-title>span.job-name>a"));
                List<WebElement> firms = driver.findElements(By.cssSelector("div.info-primary > div.info-company > div.company-text > h3.name > a"));
                List<WebElement> profession = driver.findElements(By.cssSelector("div.info-primary > div.info-company > div.company-text > p > a"));
                List<WebElement> capitals_scales = driver.findElements(By.cssSelector("div.info-primary > div.info-company > div.company-text > p"));
                List<WebElement> salaries = driver.findElements(By.cssSelector(" div.info-primary > div.primary-wrapper > div > div.job-limit.clearfix > span.red"));
                List<WebElement> locations = driver.findElements(By.cssSelector("div.info-primary > div.primary-wrapper > div.primary-box > div.job-title>span.job-area-wrapper>span.job-area"));
                List<WebElement> techs = driver.findElements(By.cssSelector("div.tags"));
                List<WebElement> requirements = driver.findElements(By.cssSelector("div.info-primary > div.primary-wrapper > div.primary-box > div.job-limit.clearfix > p"));
                List<WebElement> benefits = driver.findElements(By.cssSelector("div.info-desc"));
                System.out.println("data: " + benefits.size());
                if(benefits.size()==0)
                {
                    driver.get(url_str+count+"&ka=page-"+count);

                    positions = driver.findElements(By.cssSelector("div.info-primary > div.primary-wrapper > div.primary-box > div.job-title>span.job-name>a"));
                    firms = driver.findElements(By.cssSelector("div.info-primary > div.info-company > div.company-text > h3.name > a"));
                    profession = driver.findElements(By.cssSelector("div.info-primary > div.info-company > div.company-text > p > a"));
                    capitals_scales = driver.findElements(By.cssSelector("div.info-primary > div.info-company > div.company-text > p"));
                    salaries = driver.findElements(By.cssSelector(" div.info-primary > div.primary-wrapper > div > div.job-limit.clearfix > span.red"));
                    locations = driver.findElements(By.cssSelector("div.info-primary > div.primary-wrapper > div.primary-box > div.job-title>span.job-area-wrapper>span.job-area"));
                    techs = driver.findElements(By.cssSelector("div.tags"));
                    requirements = driver.findElements(By.cssSelector("div.info-primary > div.primary-wrapper > div.primary-box > div.job-limit.clearfix > p"));
                    benefits = driver.findElements(By.cssSelector("div.info-desc"));
                    System.out.println("data2: " + benefits.size());
                }
                for (int i = 0; i < positions.size(); i++) {
                    BufferedWriter writer =new BufferedWriter(new FileWriter(file,true));
//                    String detail_str=positions.get(i).getAttribute("href");
//                    WebDriver next=new ChromeDriver(options);
//                    WebElement detail=null;
//                    try{
//                        next.get(detail_str);
//                        detail = next.findElement(By.cssSelector("div.text"));
//                    }
//                    catch(Exception e) {
//                        System.out.println("re-get");
//                        try {
//                            next.get(detail_str);
//                            detail = next.findElement(By.cssSelector("div.text"));
//                        } catch (Exception ee) {
//                            System.out.println("error2");
//                        }
//                    }
//                    String detaill="";if(detail!=null)detaill=detail.getText();
                    writer.newLine();
                    List<WebElement> tags=techs.get(i).findElements(By.cssSelector("span.tag-item"));
                    String temp="";
                    for(WebElement element:tags)temp+=element.getText();
                    String capital_scale=capitals_scales.get(i).getText().replace(profession.get(i).getText(),"");
                    ///////////////////////////////
                    int flag=0;boolean bre=true;
                    while(bre)
                    {if(capital_scale.charAt(flag)>='0'&&capital_scale.charAt(flag)<='9'){bre=false;} flag++;}
                    String capital=capital_scale.substring(0,flag-1);
                    String scale=capital_scale.substring(flag-1,capital_scale.length());
//                    if(detaill.equals("")) {
//                        try {
//                            sleep(10000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }

                    writer.write("Java" + "," +firms.get(i).getText()+","+profession.get(i).getText()+","+scale+","+capital+"," + salaries.get(i).getText() + ","+locations.get(i).getText().substring(0,2)+","+requirements.get(i).getText()+"," + benefits.get(i).getText().replace("，","") + ","+temp);
                    System.out.println(positions.get(i).getText() + "," +firms.get(i).getText()+","+profession.get(i).getText()+","+scale+","+capital+"," + salaries.get(i).getText() + ","+locations.get(i).getText().substring(0,2)+","+requirements.get(i).getText()+"," + benefits.get(i).getText().replace("，","") + ","+temp);
//                    next.close();
                    writer.close();
                }

                driver.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class spiderthread3 extends Thread{
    public void run()
    {
        ChromeOptions options = new ChromeOptions();
        String url_str="https://www.zhipin.com/c100010000-p100101/?page=";
        //              https://www.zhipin.com/c100010000-p100101/?page=3&ka=page-3
        int count=21;
        for(;count<=30;count++) {
            File file=new File("java.csv");
            try {
                WebDriver driver=new ChromeDriver(options);
                driver.get(url_str+count+"&ka=page-"+count);
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<WebElement> positions = driver.findElements(By.cssSelector("div.info-primary > div.primary-wrapper > div.primary-box > div.job-title>span.job-name>a"));
                List<WebElement> firms = driver.findElements(By.cssSelector("div.info-primary > div.info-company > div.company-text > h3.name > a"));
                List<WebElement> profession = driver.findElements(By.cssSelector("div.info-primary > div.info-company > div.company-text > p > a"));
                List<WebElement> capitals_scales = driver.findElements(By.cssSelector("div.info-primary > div.info-company > div.company-text > p"));
                List<WebElement> salaries = driver.findElements(By.cssSelector(" div.info-primary > div.primary-wrapper > div > div.job-limit.clearfix > span.red"));
                List<WebElement> locations = driver.findElements(By.cssSelector("div.info-primary > div.primary-wrapper > div.primary-box > div.job-title>span.job-area-wrapper>span.job-area"));
                List<WebElement> techs = driver.findElements(By.cssSelector("div.tags"));
                List<WebElement> requirements = driver.findElements(By.cssSelector("div.info-primary > div.primary-wrapper > div.primary-box > div.job-limit.clearfix > p"));
                List<WebElement> benefits = driver.findElements(By.cssSelector("div.info-desc"));
                System.out.println("data: " + benefits.size());
                if(benefits.size()==0)
                {
                    driver.get(url_str+count+"&ka=page-"+count);

                    positions = driver.findElements(By.cssSelector("div.info-primary > div.primary-wrapper > div.primary-box > div.job-title>span.job-name>a"));
                    firms = driver.findElements(By.cssSelector("div.info-primary > div.info-company > div.company-text > h3.name > a"));
                    profession = driver.findElements(By.cssSelector("div.info-primary > div.info-company > div.company-text > p > a"));
                    capitals_scales = driver.findElements(By.cssSelector("div.info-primary > div.info-company > div.company-text > p"));
                    salaries = driver.findElements(By.cssSelector(" div.info-primary > div.primary-wrapper > div > div.job-limit.clearfix > span.red"));
                    locations = driver.findElements(By.cssSelector("div.info-primary > div.primary-wrapper > div.primary-box > div.job-title>span.job-area-wrapper>span.job-area"));
                    techs = driver.findElements(By.cssSelector("div.tags"));
                    requirements = driver.findElements(By.cssSelector("div.info-primary > div.primary-wrapper > div.primary-box > div.job-limit.clearfix > p"));
                    benefits = driver.findElements(By.cssSelector("div.info-desc"));
                    System.out.println("data2: " + benefits.size());
                }
                for (int i = 0; i < positions.size(); i++) {
                    BufferedWriter writer =new BufferedWriter(new FileWriter(file,true));
//                    String detail_str=positions.get(i).getAttribute("href");
//                    WebDriver next=new ChromeDriver(options);
//                    WebElement detail=null;
//                    try{
//                        next.get(detail_str);
//                        detail = next.findElement(By.cssSelector("div.text"));
//                    }
//                    catch(Exception e) {
//                        System.out.println("re-get");
//                        try {
//                            next.get(detail_str);
//                            detail = next.findElement(By.cssSelector("div.text"));
//                        } catch (Exception ee) {
//                            System.out.println("error2");
//                        }
//                    }
//                    String detaill="";if(detail!=null)detaill=detail.getText();
                    writer.newLine();
                    List<WebElement> tags=techs.get(i).findElements(By.cssSelector("span.tag-item"));
                    String temp="";
                    for(WebElement element:tags)temp+=element.getText();
                    String capital_scale=capitals_scales.get(i).getText().replace(profession.get(i).getText(),"");
                    ///////////////////////////////
                    int flag=0;boolean bre=true;
                    while(bre)
                    {if(capital_scale.charAt(flag)>='0'&&capital_scale.charAt(flag)<='9'){bre=false;} flag++;}
                    String capital=capital_scale.substring(0,flag-1);
                    String scale=capital_scale.substring(flag-1,capital_scale.length());
//                    if(detaill.equals("")) {
//                        try {
//                            sleep(10000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
                    writer.write("Java" + "," +firms.get(i).getText()+","+profession.get(i).getText()+","+scale+","+capital+"," + salaries.get(i).getText() + ","+locations.get(i).getText().substring(0,2)+","+requirements.get(i).getText()+"," + benefits.get(i).getText().replace("，","") + ","+temp);
                    System.out.println(positions.get(i).getText() + "," +firms.get(i).getText()+","+profession.get(i).getText()+","+scale+","+capital+"," + salaries.get(i).getText() + ","+locations.get(i).getText().substring(0,2)+","+requirements.get(i).getText()+"," + benefits.get(i).getText().replace("，","") + ","+temp);
//                    next.close();
                    writer.close();
                }

                driver.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
