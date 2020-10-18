package nl.vintik.example.java.junit5;

import java.io.Serializable;

public enum DeviceType implements Serializable {
    MOBILE,
    DESKTOP,
    TABLET,
    SEO_AGENT,
    SEO_AGENT_MOBILE,
    UNKNOWN;

    public boolean isSeoAgent() {
        return this.equals(SEO_AGENT) || this.equals(SEO_AGENT_MOBILE);
    }

    public boolean isMobile() {
        return this.equals(MOBILE) || this.equals(SEO_AGENT_MOBILE);
    }

    public boolean isDesktop() {
        return this.equals(DESKTOP) || this.equals(SEO_AGENT);
    }

    public boolean isTablet() {
        return this.equals(TABLET);
    }
}