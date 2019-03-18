package hw8;

import hw7.jdi.entites.*;

import java.util.Arrays;

public class TestData {
    private String[] summary;
    private Elements[] elements;
    private Color color;
    private Metal metals;
    private Vegetables[] vegetables;

    public TestData(String[] summary, Elements[] elements, Color color, Metal metals, Vegetables[] vegetables) {
        this.summary = summary;
        this.elements = elements;
        this.color = color;
        this.metals = metals;
        this.vegetables = vegetables;
    }


    public Summary createAndGetSummary() {
        return new Summary(summary);
    }

    public Submit createAndGetSubmit() {
        return new Submit(color, elements, metals, vegetables);
    }

    public String[] getSummary() {
        return summary;
    }

    public void setSummary(String[] summary) {
        this.summary = summary;
    }

    public Elements[] getElements() {
        return elements;
    }

    public void setElements(Elements[] elements) {
        this.elements = elements;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Metal getMetals() {
        return metals;
    }

    public void setMetals(Metal metals) {
        this.metals = metals;
    }

    public Vegetables[] getVegetables() {
        return vegetables;
    }

    public void setVegetables(Vegetables[] vegetables) {
        this.vegetables = vegetables;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "summary=" + Arrays.toString(summary) +
                ", elements=" + Arrays.toString(elements) +
                ", color=" + color +
                ", metals=" + metals +
                ", vegetables=" + Arrays.toString(vegetables) +
                '}';
    }
}
