package pl.wwsi.pr.store.modules.orders.process.maps;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.wwsi.pr.store.modules.orders.process.maps.model.geocode.Geocode;
import pl.wwsi.pr.store.modules.orders.process.maps.model.routes.Routes;

@Service
@RequiredArgsConstructor
public class RoutesClient {
    // TODO to properties
    private final String GEOCODE_PATH = "/v2/directions/driving-car?api_key={api_key}&start={start}&end={end}";
    private final String API_KEY = "5b3ce3597851110001cf6248d5b7b7a33f704948ae5a7543b019e14b";
    private final String START_COORDINATES = "20.9892789,52.249144";

    private final RestTemplate restTemplate;

    public Routes getRoutes(Geocode geocode) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("api_key", API_KEY);
        queryParams.put("start", START_COORDINATES);
        queryParams.put("end", getStringCoordinates(geocode));

        return restTemplate.getForObject(GEOCODE_PATH, Routes.class, queryParams);
    }

    private String getStringCoordinates(final Geocode geocode) {
        DecimalFormat df = new DecimalFormat("#.#######",
                DecimalFormatSymbols.getInstance(Locale.US));
        return geocode.getFeatures().get(0).getGeometry().getCoordinates().stream()
                .map(df::format)
                .collect(Collectors.joining(","));
    }
}

