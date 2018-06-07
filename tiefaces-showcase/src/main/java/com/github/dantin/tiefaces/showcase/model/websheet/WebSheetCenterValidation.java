package com.github.dantin.tiefaces.showcase.model.websheet;

import com.github.dantin.tiefaces.showcase.model.tablelookup.Item;
import org.tiefaces.components.websheet.TieWebSheetBean;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
public class WebSheetCenterValidation extends TieWebSheetBean {

    private static final long serialVersionUID = 1L;

    private List<Item> itemList = null;

    @Override
    public void initialLoad() {

        itemList = new ArrayList<>();
        itemList.add(new Item());
        Map<String, Object> context = new HashMap<>();
        context.put("items", itemList);
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("META-INF/websheet/ServerCenterValidation.xlsx");
        loadWebSheet(stream, context);
        this.setTieWebSheetValidationBean(new ValidationBean(), false);
    }
}
