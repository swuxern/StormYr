package com.example.swuxe.stormyr;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;


@Root(strict = false)
public class Temperature {

    @Attribute(required = false)
    String unit;

    @Attribute(required = false)
    int value;

    public String getUnit() {
        return unit;
    }

    public int getValue() {
        return value;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setValue(int value) {
        this.value = value;
    }


}

