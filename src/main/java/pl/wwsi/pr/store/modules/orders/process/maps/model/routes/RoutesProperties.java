package pl.wwsi.pr.store.modules.orders.process.maps.model.routes;

import java.util.List;
import lombok.Data;

@Data
public class RoutesProperties {
    private List<RoutesSegment> segments;
}
