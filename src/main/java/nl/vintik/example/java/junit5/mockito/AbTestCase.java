package nl.vintik.example.java.junit5.mockito;

import java.util.List;

public class AbTestCase {
    public static String DEFAULT_VARIANT = "default variant";

    private final String id;

    private final String name;

    private final List<AbTestVariant> variants;

    public AbTestCase(String id, String name, List<AbTestVariant> variants) {
        this.id = id;
        this.name = name;
        this.variants = variants;
    }

}