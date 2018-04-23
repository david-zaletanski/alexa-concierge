package net.dzale.alexa.concierge.location;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.mysql.jdbc.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Provides geocoding services that take an ambiguous input address and returns specific GPS coordinates.
 *
 * @author dzale
 */
@Service
public class GeocodingService {
    private static final Logger log = LoggerFactory.getLogger(GeocodingService.class);

    @Value("${alexa-concierge.integration.google.geocoding.apiKey}")
    private String googleGeocodingApiKey;

    /**
     * Attempts to geocode the provided address, returning an array of potential results if successful.
     *
     * @param address an address in the standard format of the addresses originating country
     * @return array containing closest matches or null if either none were found or an error occurred
     */
    public GeocodingResult[] getGeocodingForAddress(String address) {
        GeoApiContext context = getGeoApiContext();
        GeocodingResult[] results = null;

        try {
            results = GeocodingApi.geocode(context, address)
                    .await();
        } catch (ApiException ex) {
            log.error("Exception of type ApiException occurred while calling Google Geocoding API.", ex);
        } catch (InterruptedException ex) {
            log.error("Exception of type InturruptedException occurred while calling Google Geocoding API.", ex);
        } catch (IOException ex) {
            log.error("Exception of type IOException occurred while calling Google Geocoding API.", ex);
        }

        return results;
    }

    private GeoApiContext getGeoApiContext() throws IllegalArgumentException {
        if (StringUtils.isNullOrEmpty(googleGeocodingApiKey))
            throw new IllegalArgumentException("Configured value of Google Geocoding API Key was null or empty.");

        return new GeoApiContext.Builder()
                .apiKey(googleGeocodingApiKey)
                .build();
    }

}
