package pl.wwsi.pr.store.modules.orders.process.maps;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.wwsi.pr.store.modules.orders.process.maps.model.geocode.Geocode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Profile({"front"})
public class GeocodeClientImpl implements GeocodeClient {
    // TODO to properties
    private final String GEOCODE_PATH = "/geocode/search?api_key={api_key}&text={text}";
    private final String API_KEY = "5b3ce3597851110001cf6248d5b7b7a33f704948ae5a7543b019e14b";

    private final RestTemplate restTemplate;

    public Geocode getGeocode(String address) {
        try {
            Map<String, String> queryParams = new HashMap<>();
            queryParams.put("api_key", API_KEY);
            queryParams.put("text", URLEncoder.encode(address, StandardCharsets.UTF_8.toString()));

            return restTemplate.getForObject(GEOCODE_PATH, Geocode.class, queryParams);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Can not get geocode");
        }
    }
}

