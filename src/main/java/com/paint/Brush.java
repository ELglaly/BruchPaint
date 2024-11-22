package com.paint;

import javafx.scene.paint.Color;

public class Brush {

    private int size;
    private Color color;
    public enum BrushType{CIRCLE,SQUARE, TRIANGLE};
    private BrushType type;

    public Brush() {
        size=1;
        color=Color.RED;
        type=BrushType.CIRCLE;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public BrushType getType() {
        return type;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setType(BrushType type) {
        this.type = type;
    }

    public void incrementSize()
    {
        size++;
    }
    public void decrementeSize()
    {
        size--;
        if(size==0)
            size=1;
    }
}

