package hw7;

import hw7.jdi.entites.*;
import hw7.jdi.site.pages.metalsAndColors.MetalsAndColorsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static hw7.jdi.JDISite.*;

/*
TODO This wont work for me
java.lang.RuntimeException:
[00:45.925] io.github.bonigarcia.wdm.WebDriverManager.chromedriver()Lio/github/bonigarcia/wdm/WebDriverManager;

	at com.epam.jdi.light.common.Exceptions.exception(Exceptions.java:12)
	at com.epam.jdi.light.actions.ActionProcessor.jdiAround(ActionProcessor.java:47)
	at com.epam.jdi.light.elements.composite.WebPage.open(WebPage.java:105)
	at com.epam.jdi.light.elements.composite.WebPage.open(WebPage.java:111)
	at hw7.JDITest.jdiTest(JDITest.java:25)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.testng.internal.MethodInvocationHelper.invokeMethod(MethodInvocationHelper.java:124)
	at org.testng.internal.Invoker.invokeMethod(Invoker.java:583)
	at org.testng.internal.Invoker.invokeTestMethod(Invoker.java:719)
	at org.testng.internal.Invoker.invokeTestMethods(Invoker.java:989)
	at org.testng.internal.TestMethodWorker.invokeTestMethods(TestMethodWorker.java:125)
	at org.testng.internal.TestMethodWorker.run(TestMethodWorker.java:109)
	at org.testng.TestRunner.privateRun(TestRunner.java:648)
	at org.testng.TestRunner.run(TestRunner.java:505)
	at org.testng.SuiteRunner.runTest(SuiteRunner.java:455)
	at org.testng.SuiteRunner.runSequentially(SuiteRunner.java:450)
	at org.testng.SuiteRunner.privateRun(SuiteRunner.java:415)
	at org.testng.SuiteRunner.run(SuiteRunner.java:364)
	at org.testng.SuiteRunnerWorker.runSuite(SuiteRunnerWorker.java:52)
	at org.testng.SuiteRunnerWorker.run(SuiteRunnerWorker.java:84)
	at org.testng.TestNG.runSuitesSequentially(TestNG.java:1208)
	at org.testng.TestNG.runSuitesLocally(TestNG.java:1137)
	at org.testng.TestNG.runSuites(TestNG.java:1049)
	at org.testng.TestNG.run(TestNG.java:1017)
	at org.testng.IDEARemoteTestNG.run(IDEARemoteTestNG.java:72)
	at org.testng.RemoteTestNGStarter.main(RemoteTestNGStarter.java:123)


java.lang.RuntimeException:
None Driver has been found for current thread. Probably Fixture configuration is wrong.

	at com.epam.jdi.light.common.Exceptions.exception(Exceptions.java:12)
	at com.epam.jdi.light.driver.WebDriverFactory.close(WebDriverFactory.java:188)
	at hw7.TestInit.afterSuite(TestInit.java:17)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.testng.internal.MethodInvocationHelper.invokeMethod(MethodInvocationHelper.java:124)
	at org.testng.internal.MethodInvocationHelper.invokeMethodConsideringTimeout(MethodInvocationHelper.java:59)
	at org.testng.internal.Invoker.invokeConfigurationMethod(Invoker.java:458)
	at org.testng.internal.Invoker.invokeConfigurations(Invoker.java:222)
	at org.testng.internal.Invoker.invokeConfigurations(Invoker.java:142)
	at org.testng.SuiteRunner.privateRun(SuiteRunner.java:425)
	at org.testng.SuiteRunner.run(SuiteRunner.java:364)
	at org.testng.SuiteRunnerWorker.runSuite(SuiteRunnerWorker.java:52)
	at org.testng.SuiteRunnerWorker.run(SuiteRunnerWorker.java:84)
	at org.testng.TestNG.runSuitesSequentially(TestNG.java:1208)
	at org.testng.TestNG.runSuitesLocally(TestNG.java:1137)
	at org.testng.TestNG.runSuites(TestNG.java:1049)
	at org.testng.TestNG.run(TestNG.java:1017)
	at org.testng.IDEARemoteTestNG.run(IDEARemoteTestNG.java:72)
	at org.testng.RemoteTestNGStarter.main(RemoteTestNGStarter.java:123)
 */

public class JDITest extends TestInit {
    private String[] summary = new String[]{"3", "8"};
    private Color color = Color.Red;
    private Metal metal = Metal.Selen;
    private Vegetables[] vegetables = new Vegetables[]{Vegetables.Cucumber, Vegetables.Tomato};
    private Elements[] elements = new Elements[]{Elements.Water, Elements.Fire};

    @Test
    public void jdiTest() {
        List<String> expectedLog = new ArrayList<>();

        homePage.open();

        //1 Login on JDI site
        homePage.header.login(User.PITER_CHAILOVSKII);
        homePage.checkOpened();

        //2 Open Metal&Color page by Header menu
        homePage.header.headerMenu.metalsAndColors.click();
        metalAndColorsPage.checkOpened();

        //3 Submit form Metal&Color

        expectedLog.add(sum(summary));

        MetalsAndColorsPage.elements.select(elements);
        expectedLog.add(getExpectedLog(elements));

        MetalsAndColorsPage.colors.select(color);
        expectedLog.add(getExpectedLog(color));

        MetalsAndColorsPage.metals.select(metal);
        expectedLog.add(getExpectedLog(metal));

        Arrays.stream(MetalsAndColorsPage.vegetables.getSelected().split(", "))
                .forEach(string -> MetalsAndColorsPage.vegetables.select(string));
        Arrays.stream(vegetables)
                .forEach(vegetables -> MetalsAndColorsPage.vegetables.select(vegetables));
        expectedLog.add(getExpectedLog(vegetables));


        //4 Result sections should contains
        metalAndColorsPage.calculateButton.click();
        metalAndColorsPage.submitButton.click();
        Assert.assertTrue(MetalsAndColorsPage.results.values().containsAll(expectedLog));

    }

    private String sum(String... strings) {
        int sum = 0;
        for (String string : strings) {
            MetalsAndColorsPage.summary.select(string);
            sum += Integer.parseInt(string);
        }
        return "Summary: " + sum;
    }

    private String getExpectedLog(Enum... enums) {
        StringBuilder log = new StringBuilder(enums[0].getClass().getSimpleName() + ": ");
        for (Enum anEnum : enums) {
            log.append(", ").append(anEnum.name());
        }
        return log.toString().replaceFirst(", ", "");
    }
}
