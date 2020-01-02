package pl.wwsi.pr.store.modules.orders.process;

import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.wwsi.pr.store.modules.orders.listener.model.NewOrderEvent;
import pl.wwsi.pr.store.modules.orders.model.Order;
import pl.wwsi.pr.store.modules.orders.model.OrderRoute;
import pl.wwsi.pr.store.modules.orders.model.OrderRoutesStep;
import pl.wwsi.pr.store.modules.orders.process.maps.GeocodeClient;
import pl.wwsi.pr.store.modules.orders.process.maps.RoutesClient;
import pl.wwsi.pr.store.modules.orders.process.maps.model.geocode.Geocode;
import pl.wwsi.pr.store.modules.orders.process.maps.model.routes.Routes;
import pl.wwsi.pr.store.modules.orders.process.maps.model.routes.RoutesStep;
import pl.wwsi.pr.store.modules.orders.repository.OrderRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class NewOrderProcessor {
    private final OrderRepository orderRepository;
    private final GeocodeClient geocodeClient;
    private final RoutesClient routesClient;

    public void process(final NewOrderEvent event) {
        Optional<Order> oOrder = orderRepository.findById(event.getOrderId());
        if (oOrder.isPresent()) {
            Order order = oOrder.get();

            Geocode geocode = geocodeClient.getGeocode(order.getUserAddress());
            Routes routes = routesClient.getRoutes(geocode);

            OrderRoute orderRoute = createOrderRoute(routes);

            order.setRoute(orderRoute);
            orderRepository.save(order);
        } else {
            log.info("Order not found {}", event.getOrderId());
        }
    }

    private OrderRoute createOrderRoute(final Routes routes) {
        // TODO handle when empty
        OrderRoute orderRoute = new OrderRoute();
        orderRoute.setDistance(routes.getFeatures().get(0).getProperties().getSegments().get(0).getDistance());
        orderRoute.setDuration(routes.getFeatures().get(0).getProperties().getSegments().get(0).getDuration());
        orderRoute.setSteps(
                routes.getFeatures().get(0).getProperties().getSegments().get(0).getSteps().stream()
                        .map(this::createOrderRoutesStep)
                        .collect(Collectors.toList()));
        return orderRoute;
    }

    private OrderRoutesStep createOrderRoutesStep(final RoutesStep routesStep) {
        OrderRoutesStep orderRoutesStep = new OrderRoutesStep();
        orderRoutesStep.setDistance(routesStep.getDistance());
        orderRoutesStep.setDuration(routesStep.getDuration());
        orderRoutesStep.setInstruction(routesStep.getInstruction());
        return orderRoutesStep;
    }
}
