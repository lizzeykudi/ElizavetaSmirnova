package hw2.ex2;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        XmlSuite regression = new XmlSuite();
        regression.setName("Regression");
        //regression.setFileName("C:\\Users\\Packard Bell\\Desktop\\Epam\\hw\\ElizavetaSmirnova\\src\\test\\resources\\hw2\\ex2\\Regression.xml");
        suites.add(regression);
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();

    }
}
