package nl.vintik.example.java.junit5;

import org.springframework.stereotype.Component;

import java.util.Random;

import static org.apache.commons.lang3.Validate.isTrue;

@Component
class RandomGenerator {
    private static final Random RANDOM = new Random();

    int getRandomInt(int upperBound) {
        isTrue(upperBound > 0, "Upper bound must be > 0 but was " + upperBound);
        return RANDOM.nextInt(upperBound);
    }
}