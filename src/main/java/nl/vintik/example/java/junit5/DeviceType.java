package nl.vintik.example.java.junit5;

import java.io.Serializable;

public enum DeviceType implements Serializable {
    DESKTOP,
    SEO,
    SEO_MOBILE;

    public boolean isSeo() {
        return this.equals(SEO) || this.equals(SEO_MOBILE);
    }

}