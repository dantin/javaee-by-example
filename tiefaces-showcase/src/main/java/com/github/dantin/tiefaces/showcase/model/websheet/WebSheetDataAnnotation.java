package com.github.dantin.tiefaces.showcase.model.websheet;

import com.github.dantin.tiefaces.showcase.model.datademo.Department;
import com.github.dantin.tiefaces.showcase.model.datademo.WebSheetDataDemo;
import org.tiefaces.components.websheet.TieWebSheetBean;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class WebSheetDataAnnotation extends TieWebSheetBean {

    private static final long serialVersionUID = 1L;

    @Override
    public void initialLoad()  {
        Map<String, Object> context = new HashMap<>();
        List<Department> departments = WebSheetDataDemo.createDepartments();
        context.put("departments", departments);
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("META-INF/websheet/DataCommentDemo.xlsx");
        loadWebSheet(stream, context);
    }
}
