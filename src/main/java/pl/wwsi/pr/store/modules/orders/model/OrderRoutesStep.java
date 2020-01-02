package pl.wwsi.pr.store.modules.orders.model;

import lombok.Data;

@Data
public class OrderRoutesStep {
    private Double distance;
    private Double duration;
    private String instruction;
}
