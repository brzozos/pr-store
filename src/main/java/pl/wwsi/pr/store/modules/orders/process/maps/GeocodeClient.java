package pl.wwsi.pr.store.modules.orders.process.maps;

import pl.wwsi.pr.store.modules.orders.process.maps.model.geocode.Geocode;

public interface GeocodeClient {

    Geocode getGeocode(String address);
}
