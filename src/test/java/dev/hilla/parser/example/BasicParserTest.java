package dev.hilla.parser.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hilla.parser.Parser;
import dev.hilla.parser.model.EntityClass;
import dev.hilla.parser.model.MethodClass;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class BasicParserTest {

    private final Parser parser;

    public BasicParserTest() {
        var messageConverter = mock(MappingJackson2HttpMessageConverter.class);
        doReturn(new ObjectMapper()).when(messageConverter).getObjectMapper();
        parser = new Parser(messageConverter);
    }

    @Test
    void basicParsing() {
        var parserResult = parser.parseEndpoints(List.of(BasicEndpoint.class));

        assertEquals(List.of(BasicEndpoint.class.getName()),
                parserResult.endpoints().stream().map(MethodClass::name).sorted().toList(),
                "Same endpoints");

        var exampleEndpoint = parserResult.endpoints().stream()
                .filter(e -> e.name().equals(BasicEndpoint.class.getName()))
                .findAny().orElseThrow();

        assertEquals(Arrays.stream(BasicEndpoint.class.getMethods())
                        .filter(m -> m.isAnnotationPresent(ShouldBeParsed.class))
                        .map(Method::getName)
                        .sorted().toList(),
                exampleEndpoint.methods().stream().map(Method::getName).sorted().toList(),
                "Same methods in endpoint");

        assertEquals(Arrays.stream(BasicEntities.class.getDeclaredClasses())
                        .filter(c -> c.isAnnotationPresent(ShouldBeParsed.class))
                        .map(Class::getName).sorted().toList(),
                parserResult.entities().stream().map(EntityClass::name).sorted().toList(),
                "Same entities");
    }
}
