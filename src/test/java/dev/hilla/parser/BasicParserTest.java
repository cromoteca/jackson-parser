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
        var scanResult = parser.parseEndpoints(List.of(BasicEndpoint.class));
        // SimpleConsoleOutput.describe(scanResult);

        assertStreamEquals(Stream.of(BasicEndpoint.class.getName()),
                scanResult.endpoints().stream().map(e -> e.type().getName()),
                "Same endpoints");

        var exampleEndpoint = scanResult.endpoints().stream()
                .filter(e -> e.type().equals(BasicEndpoint.class))
                .findAny().orElseThrow();

        assertStreamEquals(Arrays.stream(BasicEndpoint.class.getMethods())
                        .filter(m -> m.isAnnotationPresent(ShouldBeParsed.class))
                        .map(Method::getName),
                exampleEndpoint.methods().stream().map(Method::getName),
                "Same methods in endpoint");

        assertStreamEquals(Arrays.stream(BasicEntities.class.getDeclaredClasses())
                        .filter(c -> c.isAnnotationPresent(ShouldBeParsed.class))
                        .map(Class::getName),
                scanResult.entities().stream().map(ScanResult.EntityClass::name),
                "Same entities");
    }

    private void assertStreamEquals(Stream<? extends CharSequence> expected,
                                    Stream<? extends CharSequence> actual, String message) {
        assertEquals(toString(expected), toString(actual), message);
    }

    private static String toString(Stream<? extends CharSequence> expected) {
        return expected.sorted().collect(Collectors.joining("\n"));
    }
}
