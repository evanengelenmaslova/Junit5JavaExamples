package nl.vintik.example.java.junit5;

import java.util.List;

public class AbTestCase {

    public static String DEFAULT_VARIANT = "default variant";
    public static final AbTestVariant DEFAULT_TEST_VARIANT = new AbTestVariant(DEFAULT_VARIANT);

    private final String id;

    private final String name;

    private final List<AbTestVariant> variants;

    public AbTestCase(String id, String name, List<AbTestVariant> variants) {
        this.id = id;
        this.name = name;
        this.variants = variants;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<AbTestVariant> getVariants() {
        return variants;
    }

}