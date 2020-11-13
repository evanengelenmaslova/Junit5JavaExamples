package nl.vintik.example.java.junit5.mockito;

import java.util.List;
import java.util.Map;

public interface AbTestsContext {

    @Deprecated
    Boolean isActive(String abTestDefinition, AbTestType abTestType);

    List<String> getActiveVariantNames();

    Map<AbTestCase, AbTestVariant> setActiveVariantsForUser(String[] variantIds);

    Map<AbTestCase, AbTestVariant> setActiveVariantsForUser(Map<AbTestCase, AbTestVariant> variantsMap);

    Map<AbTestCase, AbTestVariant> determineVariantsForUser(String[] variantIds);
}
