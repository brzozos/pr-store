package pl.wwsi.pr.store.modules.orders.model;

import java.util.List;
import lombok.Data;

@Data
public class OrderRoute {
    private Double distance;
    private Double duration;
    private List<OrderRoutesStep> steps;
}
