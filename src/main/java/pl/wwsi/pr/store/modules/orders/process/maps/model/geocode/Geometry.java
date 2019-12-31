package pl.wwsi.pr.store.modules.orders.process.maps.model.geocode;

import java.util.List;
import lombok.Data;

@Data
public class Geometry {
    private List<Double> coordinates;
}
