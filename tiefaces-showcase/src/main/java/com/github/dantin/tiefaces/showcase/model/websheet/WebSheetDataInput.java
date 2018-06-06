package com.github.dantin.tiefaces.showcase.model.websheet;

import org.tiefaces.components.websheet.TieWebSheetBean;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.InputStream;

@Named
@ViewScoped
public class WebSheetDataInput extends TieWebSheetBean {
    private static final long serialVersionUID = 1L;

    @Override
    public void initialLoad() {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("META-INF/websheet/WebSheetDataInput.xlsx");
        loadWebSheet(stream);
    }
}
