package hw8;

import hw7.jdi.entites.Color;
import hw7.jdi.entites.Elements;
import hw7.jdi.entites.Metal;
import hw7.jdi.entites.Vegetables;

import java.util.ArrayList;
import java.util.Arrays;

public class TestData {
    private String[] summary;
    private Elements[] elements;
    private Color color;
    private Metal metals;
    private Vegetables[] vegetables;

    public TestData(Object summary, Object elements, Object color, Object metals, Object vegetables) {
        this.summary = (String[]) summary;
        this.elements = (Elements[]) elements;
        this.color = (Color) color;
        this.metals = (Metal) metals;
        this.vegetables = (Vegetables[]) vegetables;
    }

    public TestData(ArrayList<Object> arrayList) {
        this.summary = (String[]) arrayList.get(0);
        this.elements = (Elements[]) arrayList.get(1);
        this.color = (Color) arrayList.get(2);
        this.metals = (Metal) arrayList.get(3);
        this.vegetables = (Vegetables[]) arrayList.get(4);
        /*System.out.println(arrayList.size());
        System.out.println(this.toString());*/
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
