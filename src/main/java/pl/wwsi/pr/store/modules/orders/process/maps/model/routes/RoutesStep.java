package pl.wwsi.pr.store.modules.orders.process.maps.model.routes;

import java.util.List;
import lombok.Data;

@Data
public class RoutesStep {
    private Double distance;
    private Double duration;
    private Integer type;
    private String instruction;
    private String name;
    private List<Integer> wayPoints;
}
