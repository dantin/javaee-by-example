package com.github.dantin.poitl.showcase;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.*;
import com.deepoove.poi.util.BytePictureUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimpleTemplate {
    public static void main(String[] args) throws IOException {
        XWPFTemplate template = null;
        try (
                InputStream is = SimpleTemplate.class.getClassLoader().getResourceAsStream("templates/template.docx");
                OutputStream fileOut = new FileOutputStream("target/out_template.docx")
        ) {
            template = XWPFTemplate.compile(is).render(mockData());
            template.write(fileOut);
            fileOut.flush();
        } finally {
            if (template != null) {
                template.close();
            }
        }
    }

    private static Map<String, Object> mockData() {
        return new HashMap<String, Object>() {
            {
                put("header", "Created by Deepoove.");
                put("name", "POI-TL");
                put("word", "模版引擎");
                put("time", "2018-06-25");
                put("what",
                        "Java Word模板引擎： Minimal Microsoft word(docx) templating with {{template}} in Java. It works by expanding tags in a template using values provided in a JavaMap or JavaObject.");
                put("author", new TextRenderData("000000", "Sayi卅一"));
                put("introduce", "http://www.deepoove.com");
                put("portrait", new PictureRenderData(60, 60, ".png", BytePictureUtils.getUrlByteArray("https://avatars3.githubusercontent.com/u/1394854?v=3&s=40")));
                put("feature", new NumbericRenderData(new ArrayList<TextRenderData>() {
                    {
                        add(new TextRenderData("Plug-in grammar, add new grammar by yourself"));
                        add(new TextRenderData(
                                "Supports word text, local pictures, web pictures, table, list, header, footer..."));
                        add(new TextRenderData("Templates, not just templates, but also style templates"));
                    }
                }));
                put("solution_compare", new TableRenderData(new ArrayList<RenderData>() {{
                    add(new TextRenderData("d0d0d0", ""));
                    add(new TextRenderData("d0d0d0", "introduce"));
                }}, new ArrayList<Object>() {{
                    add("1;add new # gramer");
                    add("2;support insert table");
                    add("3;support more style");
                }}, "no datas", 7600));
            }
        };
    }
}
