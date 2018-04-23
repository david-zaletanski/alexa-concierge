package net.dzale.alexa.concierge.location;

import com.google.maps.model.GeocodingResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the geocoding service.
 *
 * @author dzale
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GeocodingServiceTests {

    public static final String WRIGLEY_FIELD_ADDRESS = "1060 W Addison St, Chicago, IL 60613\n";
    public static final String WRIGLEY_FIELD_ADDRESS_VOICE_INPUT = "1060 West Addison Street Chicago Illinois";

    @Autowired
    GeocodingService geocodingService;

    @BeforeEach
    public void init() {
    }

    @Test
    public void exactAddressReturnsAtLeastOneResult() {
        GeocodingResult[] results = geocodingService.getGeocodingForAddress(WRIGLEY_FIELD_ADDRESS);

        assertTrue(results.length > 0, () -> "1 or more results should be returned.");
    }

    @Test
    public void voiceInputAddressReturnsAtLeastOneResult() {
        GeocodingResult[] results = geocodingService.getGeocodingForAddress(WRIGLEY_FIELD_ADDRESS_VOICE_INPUT);

        assertTrue(results.length > 0, () -> "1 or more results should be returned.");
    }

}
