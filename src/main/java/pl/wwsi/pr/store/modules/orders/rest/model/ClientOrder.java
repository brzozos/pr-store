package pl.wwsi.pr.store.modules.orders.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ClientOrder {
    @Id
    private String id;
    private String userName;
    private String userSurname;
    private String userAddress;
    private List<ClientProduct> products;
    private BigDecimal totalValue;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Warsaw")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime created;
}
