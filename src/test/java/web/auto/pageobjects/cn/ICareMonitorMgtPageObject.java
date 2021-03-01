package web.auto.pageobjects.cn;

import cucumber.api.DataTable;
import org.apache.logging.log4j.core.impl.ReusableLogEventFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.auto.runtime.LoadConfig;

import javax.xml.transform.Result;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ICareMonitorMgtPageObject extends BasePage {
    public ICareMonitorMgtPageObject(WebDriver webDriver) {
        super(webDriver);
        waitPageLoaded();
    }

    public void checkNoTip() {
        basicOperation.findElementsByScript(LoadConfig.load("neverShow")).get(0).click();
    }

    public void closeTips(String str) {
        basicOperation.findElementsByScript(LoadConfig.load("closeTipsLeft") + str + LoadConfig.load("closeTipsRight")).get(0).click();
    }

    public void checkStatusDevicesNum(String status, String num) {
        basicOperation.getFromByScript(LoadConfig.load("careBarStatusLeft") + status + LoadConfig.load("careBarStatusRight"), result -> result.toString().equalsIgnoreCase(num));
    }

    public void clickManagementSubMenu(String menu) {
        basicOperation.findElementsByScript(LoadConfig.load("mgrSubMenuLeft") + menu + LoadConfig.load("mgrSubMenuRight")).get(0).click();
    }

    public void selectProfileSetting(String setting) {
        basicOperation.findElementsByScript(LoadConfig.load("profileSettingLeft") + setting + LoadConfig.load("profileSettingRight")).get(0).click();
    }

    public void searchWithText(String criteria) {
        waitPageLoaded();
        basicOperation.findElementsByScript(LoadConfig.load("searchBox")).get(0).sendKeys(criteria);
        clickSearchBtn();
    }

    private void clickSearchBtn() {
        basicOperation.findElementsByScript(LoadConfig.load("searchBtn")).get(0).click();
    }

    public void checkScreenRecordNum(String num) {
        basicOperation.getFromByScript(LoadConfig.load("screenRecordNum"), result -> result.toString().equalsIgnoreCase(num));
    }

    public void selectLang(String langItem) {
        Actions builder = new Actions(webDriver);
        WebElement lang = basicOperation.findElementsByScript(LoadConfig.load("langMenu")).get(0);
        builder.moveToElement(lang).build().perform();
        basicOperation.findElementsByScript(LoadConfig.load("langItemLeft") + langItem + LoadConfig.load("langItemRight")).get(0).click();
    }

    public void selectItem(String item) {
        basicOperation.findElementsByScript(LoadConfig.load("navLeft") + item + LoadConfig.load("navRight")).get(0).click();
    }

    public void checkMonitorManagementTable(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);

        for (int i = 1; i < rows.size(); i++) {
            List<String> row = rows.get(i);
            String recordString = "";
            for (String s : row) {
                recordString += s + "";
            }
            String finalRecordString = recordString;
            basicOperation.getFromByScript(LoadConfig.load("monitorTableLeft") + (i - 1) + LoadConfig.load("monitorTableRight"), result -> finalRecordString.replaceAll(" ", "").contains(result.toString().replaceAll(" ", "")));
        }
    }

    public void checkStatus(String status) {
        String finalStatus = status.replaceAll(" ", "").replaceAll(":", "");
        basicOperation.getFromByScript(LoadConfig.load("monitorStatus"), result -> result.toString().contains(finalStatus));
    }

    public void clickMonitorDetailByIndex(String index) {
        basicOperation.findElementsByScript(LoadConfig.load("monitorItemDetailLeft") + index + LoadConfig.load("monitorItemDetailRight")).get(0).click();
    }

    public void checkMonitorDetailHintMessage(String msg) {
        basicOperation.getFromByScript(LoadConfig.load("monitorItemDetailHintMsg"), result -> result.toString().replace("(\\r)(\\n)(+/g)", "").replaceAll(" ", "").equalsIgnoreCase(msg.replaceAll(" ", "")));
    }

    public void checkWorkSpaceInfo(String msg) {
        if (checkWorkInfoExisted()) {
            basicOperation.getFromByScript(LoadConfig.load("workInfoLeft") + "0" + LoadConfig.load("workInfoRight"), result -> result.toString().contains(msg));
        }
    }

    public void checkScreenBaseInfo(String msg) {
        basicOperation.getFromByScript(LoadConfig.load("screenBaseInfo"), result -> result.toString().contains(msg.replaceAll(" ", "")));
    }

    public void checkDeviceInfo(String msg) {
        if (checkWorkInfoExisted()) {
            basicOperation.getFromByScript(LoadConfig.load("workInfoLeft") + "1" + LoadConfig.load("workInfoRight"), result -> result.toString().contains(msg));
        } else {
            basicOperation.getFromByScript(LoadConfig.load("workInfoLeft") + "0" + LoadConfig.load("workInfoRight"), result -> result.toString().contains(msg));
        }
    }

    public Boolean checkWorkInfoExisted() {
        waitPageLoaded();
        int workSpaceSize = basicOperation.findElementsByScript(LoadConfig.load("workInfoSize")).size();
        return workSpaceSize == 2;
    }

    public void checkReceiveCardNum(String num) {
        basicOperation.getFromByScript(LoadConfig.load("receiveCardNum"), result -> result.toString().equalsIgnoreCase(num));
    }

    public void toggleCardTab(String status) {
        WebElement element = basicOperation.findElementsByScript(LoadConfig.load("cardTabToggleLeft") + status + LoadConfig.load("cardTabToggleRight")).get(0);
        (new WebDriverWait(webDriver, Integer.parseInt(LoadConfig.load("timeout")))).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void checkReceiveStatus(String status) {
        basicOperation.getFromByScript(LoadConfig.load("receiveCardStatus"), result -> result.toString().equalsIgnoreCase(status));
    }

    public void checkBasicInfo(String info) {
        basicOperation.getFromByScript(LoadConfig.load("receiveBasicInfo"), result -> result.toString().contains(info));
    }

    public void clickVisualRadio(String item) {
        basicOperation.findElementsByScript(LoadConfig.load("visualRadioLeft") + item + LoadConfig.load("visualRadioRight")).get(0).click();
    }

    public void checkTotalLineNum(String lines) {
        basicOperation.getFromByScript(LoadConfig.load("visualLinkNum"), result -> result.toString().equalsIgnoreCase(lines));
    }

    public void checkLineNumWithColor(String colorLine) {
        String[] lines = colorLine.split(",");
        List<String> lineList = new ArrayList<>();
        List<String> colors = Arrays.asList(LoadConfig.load("greenLine"), LoadConfig.load("yellowLine"), LoadConfig.load("redLine"));

        lineList = Arrays.asList(lines);

        for (int i = 0; i < lineList.size(); i++) {
            List<String> finalLineList = lineList;
            int finalI = i;
            basicOperation.getFromByScript(LoadConfig.load("visualColorLinkNumLeft") + colors.get(i) + LoadConfig.load("visualColorLinkNumRight"), result -> result.toString().equalsIgnoreCase(finalLineList.get(finalI)));
        }
    }

    public void checkCardNumWithStatus(String status, String num) {
        basicOperation.getFromByScript(LoadConfig.load("cardStatusNumLeft") + status + LoadConfig.load("cardStatusNumRight"), result -> result.toString().equalsIgnoreCase(num));
    }

    public void switchPageTab(String tab) {
        basicOperation.findElementsByScript(LoadConfig.load("monitorMgtTabLeft") + tab + LoadConfig.load("monitorMgtTabRight")).get(0).click();
    }

    public void getCheckReceiveCardTemp(String temps) {
        basicOperation.getFromByScript(LoadConfig.load("receiveCardTemp"), result -> result.toString().equalsIgnoreCase(temps));
    }

    public void checkReceiveCardVersion(String versions) {
        basicOperation.getFromByScript(LoadConfig.load("receiveCardVersion"), result -> result.toString().equalsIgnoreCase(versions));
    }

    public void selectSendCardByName(String deviceName) {
        basicOperation.findElementsByScript(LoadConfig.load("sendCardOptionNameLeft") + deviceName + LoadConfig.load("sendCardOptionNameRight")).get(0).click();
    }

    public void selectSendCardByIndex(String index) {
        basicOperation.findElementsByScript(LoadConfig.load("sendCardOptionIndex")).get(Integer.parseInt(index) - 1).click();
    }

    public void checkSelectionOfReceiveCardByName(String deviceName) {
        basicOperation.getFromByScript(LoadConfig.load("contentSendCardNameLeft") + deviceName + LoadConfig.load("contentSendCardNameRight"), result -> result.toString().equalsIgnoreCase("true"));
    }

    public void checkSelectionOfReceiveCardByIndex(String index) {
        basicOperation.getFromByScript(LoadConfig.load("contentSendCardSelectionIndexLeft") + String.valueOf(Integer.parseInt(index) - 1) + LoadConfig.load("contentSendCardSelectionIndexRight"), result -> result.toString().equalsIgnoreCase("true"));
    }

    public void clickContentSenderByIndex(String index) {
        basicOperation.findElementsByScript(LoadConfig.load("contentSendCardIndexLeft") + String.valueOf(Integer.parseInt(index) - 1) + LoadConfig.load("contentSendCardIndexRight")).get(0).click();
    }

    public void checkGeneralInfo(String cardInfo) {
        moveToSenderCard();
        String[] cards = cardInfo.split("\\^");
        List<String> cardList = new ArrayList<>();
        cardList = Arrays.asList(cards);

        for (int i = 0; i < cardList.size(); i++) {
            List<String> finalCardList = cardList;
            int finalI = i;
            basicOperation.getFromByScript(LoadConfig.load("generalInfoInDetail"), result -> result.toString().contains(finalCardList.get(finalI)));
        }
    }

    public void moveToSenderCard() {
        Actions action = new Actions(webDriver);
        List<WebElement> senderCards = basicOperation.findElementsByScript(LoadConfig.load("generalInfoCards"));
        for (WebElement card : senderCards) {
            action.moveToElement(card).build().perform();
        }
    }
}