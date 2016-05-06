package org.wcong.webexplore;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;

/**
 * mini browser
 * Created by wcong on 2016/5/5.
 */
public class Browser extends Region {
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    private HBox toolBar;
    private InputBar inputBar;
    private ComboBox<String> comboBox = new ComboBox<>();

    {
        comboBox.getItems().add("web");
        comboBox.getItems().add("wap");
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                String item = comboBox.getSelectionModel().getSelectedItem();
                if (item.equals("web")) {
                }
            }
        });
    }

    private static String[] captions = new String[]{
            "扇贝",
            "微博",
            "知乎"
    };
    final Hyperlink[] hpls = new Hyperlink[captions.length];

    private static String[] urls = new String[]{
            "https://www.shanbay.com/",
            "http://www.weibo.com",
            "https://www.zhihu.com"
    };

    public Browser(CookieStore cookieStore) {
        CookieManager manager = new CookieManager(cookieStore, null);
        CookieHandler.setDefault(manager);

        //apply the styles
        setStyle("-fx-background-color: #666970;");
        // load the web page
        webEngine.load("http://www.baidu.com");

        inputBar = new InputBar(webEngine);
        getChildren().add(inputBar);

        //add the web view to the scene
        for (int i = 0; i < captions.length; i++) {
            final Hyperlink hpl = hpls[i] = new Hyperlink(captions[i]);
            final String url = urls[i];
            hpl.setStyle("-fx-font-size: 18;-fx-underline: false");
            hpl.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    webEngine.load(url);
                }
            });
        }
        toolBar = new HBox();
        toolBar.setStyle("-fx-base: #505359;\n" +
                "    -fx-background: #505359;\n" +
                "    -fx-shadow-highlight-color: transparent;\n" +
                "    -fx-spacing: 5;\n" +
                "    -fx-padding: 4 4 4 4; ");
        toolBar.getChildren().addAll(hpls);
        getChildren().add(toolBar);
        getChildren().add(browser);

    }


    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        double tbHeight = toolBar.prefHeight(w);
        double ibHeight = inputBar.prefHeight(w);
        layoutInArea(inputBar, 0, 0, w, ibHeight, 0, HPos.CENTER, VPos.CENTER);
        layoutInArea(browser, 0, ibHeight, w, h - tbHeight - ibHeight, 0, HPos.CENTER, VPos.CENTER);
        layoutInArea(toolBar, 0, h - tbHeight, w, tbHeight, 0, HPos.CENTER, VPos.CENTER);
    }

    @Override
    protected double computePrefWidth(double height) {
        return 750;
    }

    @Override
    protected double computePrefHeight(double width) {
        return 500;
    }
}
