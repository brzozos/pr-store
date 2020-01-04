package pl.wwsi.pr.store.modules.orders.process.maps;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.wwsi.pr.store.modules.orders.process.maps.model.geocode.Geocode;

@Service
@RequiredArgsConstructor
@Profile({"sim"})
public class GeocodeFakeClientImpl implements GeocodeClient {
    @Override
    public Geocode getGeocode(String address) {
        return null;
    }
}
