package dev.hilla.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hilla.parser.example.BasicEndpoint;
import dev.hilla.parser.example.BasicEntities;
import dev.hilla.parser.example.ShouldBeParsed;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static dev.hilla.parser.Parser.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        SimpleConsoleOutput.describe(parserResult);

        assertEquals(List.of(BasicEndpoint.class.getName()),
                parserResult.endpoints().stream().map(e -> e.type().getName()).sorted().toList(),
                "Same endpoints");

        var exampleEndpoint = parserResult.endpoints().stream()
                .filter(e -> e.type().getName().equals(BasicEndpoint.class.getName()))
                .findAny().orElseThrow();

        assertStreamEquals(Arrays.stream(BasicEndpoint.class.getMethods())
                        .filter(m -> m.isAnnotationPresent(ShouldBeParsed.class))
                        .map(Method::getName),
                exampleEndpoint.methods().stream().map(Method::getName),
                "Same methods in endpoint");

        assertStreamEquals(Arrays.stream(BasicEntities.class.getDeclaredClasses())
                        .filter(c -> c.isAnnotationPresent(ShouldBeParsed.class))
                        .map(Class::getName),
                parserResult.entities().stream().map(EntityClass::name),
                "Same entities");
    }

    private void assertStreamEquals(Stream<? extends CharSequence> expected,
                                    Stream<? extends CharSequence> actual, String message) {
        assertEquals(expected.sorted().collect(Collectors.joining("\n")),
                actual.sorted().collect(Collectors.joining("\n")), message);
    }
}
