package com.github.dantin.tiefaces.showcase.model.websheet;

import org.tiefaces.components.websheet.TieWebSheetBean;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.InputStream;

@ManagedBean
@ViewScoped
public class WebSheetReports_Config extends TieWebSheetBean {
    private static final long serialVersionUID = 1L;

    @Override
    public void initialLoad() {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("META-INF/websheet/PRICELIST.xlsx");
        loadWebSheet(stream);
    }
}
