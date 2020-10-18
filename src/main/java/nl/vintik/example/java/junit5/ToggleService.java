package nl.vintik.example.java.junit5;

public interface ToggleService {

    boolean isEnabled(Toggle toggle);

    boolean isEnabled(String featureName);
}
