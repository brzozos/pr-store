package pl.wwsi.pr.store.modules.orders.process.maps;

import pl.wwsi.pr.store.modules.orders.process.maps.model.geocode.Geocode;
import pl.wwsi.pr.store.modules.orders.process.maps.model.routes.Routes;

public interface RoutesClient {

    Routes getRoutes(Geocode geocode);
}
