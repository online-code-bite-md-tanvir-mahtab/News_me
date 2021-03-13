package com.tanvircodder.newsme;

public class Util {
   private String title;
   private String source;
   private String imageURI;
   private String publish;

    public Util(String title, String source,String imageURI,String publish) {
        this.title = title;
        this.imageURI = imageURI;
        this.source = source;
        this.publish = publish;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getImageURI() {
        return imageURI;
    }

    public String getPublish() {
        return publish;
    }
}
