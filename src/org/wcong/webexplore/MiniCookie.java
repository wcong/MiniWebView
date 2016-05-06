package org.wcong.webexplore;

import java.io.Serializable;

/**
 * Created by wcong on 2016/5/6.
 */
public class MiniCookie implements Serializable {
    private String name;
    private String value;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
