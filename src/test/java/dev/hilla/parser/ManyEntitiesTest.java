package dev.hilla.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hilla.parser.annotations.Endpoint;
import dev.hilla.parser.annotations.Nonnull;
import dev.hilla.parser.entities.Airline;
import dev.hilla.parser.entities.Equipment;
import dev.hilla.parser.entities.Everybody;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

import static org.mockito.Mockito.*;

public class ManyEntitiesTest {

    private final Parser parser;

    public ManyEntitiesTest() {
        var messageConverter = mock(MappingJackson2HttpMessageConverter.class);
        doReturn(new ObjectMapper()).when(messageConverter).getObjectMapper();
        parser = new Parser(messageConverter);
    }

    @Test
    void shouldFindAllEntitiesInPackage() {
        var parserResult = parser.parseEndpoints(List.of(LocalEndpoint.class));
        SimpleConsoleOutput.describe(parserResult);
    }

    @Endpoint
    public static class LocalEndpoint {
        @Nonnull
        public List<Airline> getAirlines(@Nonnull Equipment equipment, Everybody everybody) {
            return List.of();
        }
    }
}
