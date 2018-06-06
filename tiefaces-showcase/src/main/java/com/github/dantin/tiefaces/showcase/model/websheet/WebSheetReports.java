package com.github.dantin.tiefaces.showcase.model.websheet;

import com.github.dantin.tiefaces.showcase.model.tablelookup.ItemList;
import org.tiefaces.components.websheet.TieWebSheetBean;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
@ViewScoped
public class WebSheetReports extends TieWebSheetBean {
    private static final long serialVersionUID = 1L;

    private List<Object> itemList = new ItemList().getItemList();

    public int getRowCount() {
        return itemList == null ? 0 : itemList.size();
    }

    public void setItemList(List<Object> itemList) {
        this.itemList = itemList;
    }

    public List<Object> getItemList() {
        return itemList;
    }

    @Override
    public void initialLoad() {
        Map<String, Object> context = new HashMap<>();
        context.put("items", itemList);
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("META-INF/websheet/PRICELIST.xlsx");
        loadWebSheet(stream, context);
    }
}
