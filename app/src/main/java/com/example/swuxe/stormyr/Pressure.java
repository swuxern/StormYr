package com.example.swuxe.stormyr;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;


@Root(strict=false)
public class Pressure {
    @Attribute(required = false)
    String unit;

    @Attribute(required = false)
    double value;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
