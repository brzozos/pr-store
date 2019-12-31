package pl.wwsi.pr.store.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    // TODO to properties
    private final String ROOT_URI = "https://api.openrouteservice.org";

    @Bean
    public Jackson2JsonMessageConverter rabbitConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
//        disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        dateFormat = StdDateFormat().withTimeZone(TimeZone.getDefault())
//                .withColonInTimeZone(true)
        return objectMapper;
    }

    @Bean
    public RestTemplate restTemplate(MappingJackson2HttpMessageConverter httpMessageConverter) {
        return (new RestTemplateBuilder())
                .rootUri(ROOT_URI)
                .messageConverters(httpMessageConverter)
                .build();
    }

    @Bean
    public MappingJackson2HttpMessageConverter httpMessageConverter(ObjectMapper objectMapper) {
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }
}
