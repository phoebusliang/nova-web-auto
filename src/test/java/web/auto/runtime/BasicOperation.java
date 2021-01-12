package web.auto.runtime;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BasicOperation {

    WebDriver driver = null;

    public BasicOperation(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> waitFor(Supplier<List<WebElement>> action, Predicate<List<WebElement>> checker, String error_template) {
        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < Long.parseLong(LoadConfig.load("timeout")) * 1000) {
            List<WebElement> elements = action.get();
            if (checker.test(elements)) {
                return elements;
            }
            sleepTimeout("interval");
        }
        throw new AssertionError(error_template);
    }

    public Object waitForText(Supplier<Object> action, Predicate<Object> checker, String error_template) {
        long start = System.currentTimeMillis();
        Object value;
        while (System.currentTimeMillis() - start < Long.parseLong(LoadConfig.load("timeout")) * 1000) {
            value = action.get();
            if (checker.test(value)) {
                return value;
            }
            sleepTimeout("interval");
        }
        throw new AssertionError(error_template);
    }

    public void sleepTimeout(String time) {
        try {
            Thread.sleep(Long.parseLong(LoadConfig.load(time)) * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<WebElement> findElementsByScript(String script) {
        final String actualScript = script.replace("$", "jQuery").concat(".toArray()");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Supplier<List<WebElement>> action = () -> (List<WebElement>) js.executeScript(actualScript);
        Predicate<List<WebElement>> checker = elements -> elements.size() > 0 && elements.get(0).isEnabled();
        return waitFor(action, checker, "Element not found!");
    }

    private List<WebElement> getElements(Object results) {
        if (results instanceof List) {
            return (List<WebElement>) results;
        } else if (results instanceof Map) {
            return (List<WebElement>) ((Map) (results)).values().stream()
                    .filter(o -> o instanceof WebElement)
                    .collect(Collectors.toList());
        }
        throw new IllegalArgumentException();
    }

    public void moveFocusToElement(String script) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String actualScript = script.replace("$", "jQuery") + ".focus().click()";
        js.executeScript(actualScript);
    }

    public List<WebElement> getElements(String type, String locator) {
        List<WebElement> elements = null;
        if ("css".equals(type)) {
            elements = driver.findElements(By.cssSelector(locator));
        } else if ("id".equals(type)) {
            elements = driver.findElements(By.id(locator));
        } else if ("xpath".equals(type)) {
            elements = driver.findElements(By.xpath(locator));
        } else {
            return null;
        }
        List<WebElement> finalElements = elements;
        Supplier<List<WebElement>> action = () -> (List<WebElement>) finalElements;
        Predicate<List<WebElement>> checker = eles -> eles.size() > 0 && eles.get(0).isEnabled();
        return waitFor(action, checker, "Element not found!");
    }

    public boolean notExistElement(String script) {
        try {
            final String actualScript = script.replace("$", "jQuery").concat(".toArray()");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Supplier<List<WebElement>> action = () -> (List<WebElement>) js.executeScript(actualScript);
            Predicate<List<WebElement>> checker = elements -> elements.size() > 0 && elements.get(0).isEnabled();
            List<WebElement> elements = action.get();
            if (checker.test(elements)) {

                return false;
            } else {
                return true;
            }
        } catch (Exception e) {

            return true;
        }


    }


    public Object getFromByScript(String script, Predicate<Object> verify) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Supplier<Object> action = () -> (Object) js.executeScript(script);
        return waitForText(action, verify, "Element not found!");
    }

    public JsonObject postAPI(String requestRoute, String requestBody, String key, String language) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpResponse response;
        JsonObject jsonObject = null;
        HttpEntity entity;
        String responseString;

        try {
            HttpPost request = new HttpPost(requestRoute);
            request.addHeader("x-api-key", LoadConfig.load(key));
            request.addHeader("Accept-Language", language);
            request.addHeader("Content-Type", "application/json");
            StringEntity requestEntity = new StringEntity(requestBody);
            request.setEntity(requestEntity);
            response = httpclient.execute(request);
            entity = response.getEntity();
            responseString = EntityUtils.toString(entity);
            jsonObject = new JsonParser().parse(responseString).getAsJsonObject();
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JsonObject getAPIResponse(String requestRoute, String key, String language) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpResponse response;
        JsonObject jsonObject = null;
        HttpEntity entity;
        String responseString;

        try {
            HttpGet request = new HttpGet(requestRoute);
            request.addHeader("x-api-key", LoadConfig.load(key));
            request.addHeader("Accept-Language", language);
            request.addHeader("Content-Type", "application/json");
            response = httpclient.execute(request);
            entity = response.getEntity();
            responseString = EntityUtils.toString(entity);
            jsonObject = new JsonParser().parse(responseString).getAsJsonObject();
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JsonObject sendAPIRequest(String requestRoute, String requestType, String requestBody, String key, String language) {
        JsonObject jsonObject = null;
        try {
            switch (requestType) {
                case "get":
                    return getAPIResponse(requestRoute, key, language);
                case "post":
                    return postAPI(requestRoute, requestBody, key, language);
                default:
                    return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public String formatChineseUrl(String url) {
        String regex = "[^\\x00-\\xff]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher mather = pattern.matcher(url);
        try {
            while (mather.find()) {
                url = url.replaceFirst(regex, URLEncoder.encode(mather.group(0), "UTF-8"));
            }
            return url;
        } catch (java.io.UnsupportedEncodingException e) {
            System.out.println("Error occured when format url:" + url);
            e.printStackTrace();
        }
        return null;
    }

    public Document getWebPageContent(String url) {
        url = formatChineseUrl(url);
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("Visit url " + url + " failed!");
            e.printStackTrace();
        }
        return null;
    }

    @Deprecated
    public ArrayList<Element> findStaticElement(String locator, Document pageContent) {
        try {
            Elements elements = pageContent.select(locator);
            ArrayList<Element> resultList = new ArrayList<Element>();
            elements.forEach(value -> resultList.add(value));
            return resultList;
        } catch (Exception e) {
            throw new AssertionError("element doesn't exist! Locate script is: " + locator);
        }
    }

    public boolean haveContent(String content) {
        return driver.getPageSource().contains(content);

    }


    public String getMetaTag(Document document, String attr) {
        Elements elements = document.select("meta[name=" + attr + "]");
        for (Element element : elements) {
            final String s = element.attr("content");
            if (s != null) return s;
        }
        return null;
    }

    public List<String> getEachElementAttr(String locator, Document pageContent, Function<Element, String> attribute) {
        try {
            Elements elements = pageContent.select(LoadConfig.load(locator));
            ArrayList<Element> resultList = new ArrayList<Element>();
            elements.forEach(value -> resultList.add(value));
            List<String> attributeList = resultList.stream().map(attribute).collect(Collectors.toList());
            return attributeList;
        } catch (Exception e) {
            throw new AssertionError("element doesn't exist! Locate script is: " + locator);
        }
    }

    public void checkStaticELement(Object value, Predicate<Object> verify) {
        try {
            Assert.assertTrue(verify.test(value));
        } catch (AssertionError e) {
            System.out.println("Actual result is: " + value);
            throw new AssertionError("Assert failed! Actual result is: " + value);
        }
    }
}
