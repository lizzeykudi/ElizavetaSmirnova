package hw7.jdi.entites;

public class Submit {
    Color color;
    Elements[] elements;
    Metal metal;
    Vegetables[] vegetables;

    public Submit(Color color, Elements[] elements, Metal metal, Vegetables[] vegetables) {
        this.color = color;
        this.elements = elements;
        this.metal = metal;
        this.vegetables = vegetables;
    }

    public Color getColor() {
        return color;
    }

    public Elements[] getElements() {
        return elements;
    }

    public Metal getMetal() {
        return metal;
    }

    public Vegetables[] getVegetables() {
        return vegetables;
    }
}
