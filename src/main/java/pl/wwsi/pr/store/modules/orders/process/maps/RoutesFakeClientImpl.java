package pl.wwsi.pr.store.modules.orders.process.maps;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.wwsi.pr.store.modules.orders.process.maps.model.geocode.Geocode;
import pl.wwsi.pr.store.modules.orders.process.maps.model.routes.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile({"sim"})
public class RoutesFakeClientImpl implements RoutesClient {

    @Override
    public Routes getRoutes(Geocode geocode) {

        Routes routes = new Routes();
        List<RoutesFeature> features = new ArrayList<>();

        RoutesFeature routesFeature = new RoutesFeature();
        RoutesProperties routesProperties = new RoutesProperties();
        List<RoutesSegment> routesSegments = new ArrayList<>();
        RoutesSegment routesSegment = new RoutesSegment();
        routesSegment.setDistance(3522.7);
        routesSegment.setDuration(399.8);

        List<RoutesStep> routesSteps = createRouteSteps();

        routesSegment.setSteps(routesSteps);
        routesSegments.add(routesSegment);
        routesProperties.setSegments(routesSegments);
        routesFeature.setProperties(routesProperties);
        features.add(routesFeature);
        routes.setFeatures(features);

        return routes;
    }

    private List<RoutesStep> createRouteSteps() {
        List<RoutesStep> routesSteps = new ArrayList<>();
        RoutesStep firstRouteStep = new RoutesStep();
        firstRouteStep.setDistance(59.3);
        firstRouteStep.setDuration(10.7);
        firstRouteStep.setType(11);
        firstRouteStep.setInstruction("Head northeast on Marka Edelmana");
        firstRouteStep.setName("Marka Edelmana");

        routesSteps.add(firstRouteStep);

        RoutesStep secondRouteStep = new RoutesStep();
        secondRouteStep.setDistance(164.7);
        secondRouteStep.setDuration(29.6);
        secondRouteStep.setType(1);
        secondRouteStep.setInstruction("Turn right");
        secondRouteStep.setName("-");

        routesSteps.add(secondRouteStep);
        return routesSteps;
    }

}
