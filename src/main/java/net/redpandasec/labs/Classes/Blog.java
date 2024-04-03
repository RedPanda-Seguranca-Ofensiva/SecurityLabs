package net.redpandasec.labs.Classes;

import java.io.Serializable;

public class Blog implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String body;

    public Blog(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
