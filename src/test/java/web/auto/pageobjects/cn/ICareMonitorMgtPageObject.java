package web.auto.pageobjects.cn;

import cucumber.api.DataTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import web.auto.runtime.LoadConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ICareMonitorMstPageObject extends BasePage {
    public ICareMonitorMstPageObject(WebDriver webDriver) {
        super(webDriver);
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

    public void goSpecificManagementPanel(String item) {
        selectHeader(1);
        basicOperation.findElementsByScript(LoadConfig.load("itemLeft") + item + LoadConfig.load("itemRight")).get(0).click();
    }

    private void selectHeader(Integer index) {
        Actions builder = new Actions(webDriver);
        WebElement headerBtn = basicOperation.findElementsByScript(LoadConfig.load("headerLeft") + index.toString() + LoadConfig.load("headerRight")).get(0);
        builder.moveToElement(headerBtn).build().perform();
    }

    public void clickManagementSubMenu(String menu) {
        basicOperation.findElementsByScript(LoadConfig.load("mgrSubMenuLeft") + menu + LoadConfig.load("mgrSubMenuRight")).get(0).click();
    }

    public void selectProfileSetting(String setting) {
        basicOperation.findElementsByScript(LoadConfig.load("profileSettingLeft") + setting + LoadConfig.load("profileSettingRight")).get(0).click();
    }

    public void searchWithText(String criteria) {
        basicOperation.findElementsByScript(LoadConfig.load("searchBox")).get(0).sendKeys(criteria);
        clickSearchBtn();
    }

    private void clickSearchBtn() {
        basicOperation.findElementsByScript(LoadConfig.load("searchBtn")).get(0).click();
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
            basicOperation.getFromByScript(LoadConfig.load("monitorTableLeft") + (i - 1) + LoadConfig.load("monitorTableRight"), result -> result.toString().equalsIgnoreCase(finalRecordString));
        }
    }

    public void checkStatus(String status) {
        String finalStatus = status.replaceAll(" ", "").replaceAll(":", "");
        basicOperation.getFromByScript(LoadConfig.load("monitorStatus"), result -> result.toString().equalsIgnoreCase(finalStatus));
    }

    public void clickMonitorDetailByIndex(String index) {
        basicOperation.findElementsByScript(LoadConfig.load("monitorItemDetailLeft") + index + LoadConfig.load("monitorItemDetailRight")).get(0).click();
    }

    public void checkMonitorDetailHintMessage(String msg) {
        basicOperation.getFromByScript(LoadConfig.load("monitorItemDetailHintMsg"), result -> result.toString().contains(msg));
    }

    public void checkWorkSpaceInfo(String msg) {
        if (checkWorkInfoExisted()) {
            basicOperation.getFromByScript(LoadConfig.load("workInfoLeft") + "0" + LoadConfig.load("workInfoRight"), result -> result.toString().contains(msg));
        }
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
        basicOperation.findElementsByScript(LoadConfig.load("cardTabToggleLeft") + status + LoadConfig.load("cardTabToggleRight")).get(0).click();
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

    public void switchPageTab(String tab) {
        basicOperation.findElementsByScript(LoadConfig.load("monitorMgtTabLeft") + tab + LoadConfig.load("monitorMgtTabRight")).get(0).click();
    }
}