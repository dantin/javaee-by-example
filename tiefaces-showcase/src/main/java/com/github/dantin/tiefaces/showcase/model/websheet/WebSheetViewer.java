package com.github.dantin.tiefaces.showcase.model.websheet;

import org.tiefaces.components.websheet.TieWebSheetBean;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.InputStream;

@Named
@ViewScoped
public class WebSheetViewer extends TieWebSheetBean {
    @Override
    public void initialLoad() {
        // NOTE: 'space' NOT support in filename.
        //String path = this.getClass().getClassLoader().getResource("META-INF/websheet/Simple-Budget-Planner.xlsx").getPath();
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("META-INF/websheet/Simple-Budget-Planner.xlsx");
        loadWebSheet(stream);
    }
}
