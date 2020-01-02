package pl.wwsi.pr.store.modules.orders.process.maps.model.routes;

import java.util.List;
import lombok.Data;

@Data
public class RoutesSegment {
    private Double distance;
    private Double duration;
    private List<RoutesStep> steps;
}
