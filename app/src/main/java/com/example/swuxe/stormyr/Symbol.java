package com.example.swuxe.stormyr;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;


@Root(strict = false)
public class Symbol {

    @Attribute(required = false)
    String name;

    @Attribute(required=false)
    String var;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }
}
