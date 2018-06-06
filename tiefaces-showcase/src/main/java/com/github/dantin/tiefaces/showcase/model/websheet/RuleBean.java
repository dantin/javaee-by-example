package com.github.dantin.tiefaces.showcase.model.websheet;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class RuleBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public boolean checkRule1(double value) {
        return value > 0;
    }

    public boolean checkRule2(double value) {
        return value >0 && value < 500000;
    }
}
