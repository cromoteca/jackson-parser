package dev.hilla.parser.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hilla.parser.Parser;
import dev.hilla.parser.model.EntityClass;
import dev.hilla.parser.model.MethodClass;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicParserTest {

    @Test
    void basicParsing() {
        var parserResult = new Parser(new ObjectMapper())
                .parseEndpoints(List.of(BasicEndpoint.class));

        assertEquals(List.of(BasicEndpoint.class.getName()),
                parserResult.endpoints().stream().map(MethodClass::name).sorted().toList(),
                "Same endpoints");

        var exampleEndpoint = parserResult.endpoints().stream()
                .filter(e -> e.name().equals(BasicEndpoint.class.getName()))
                .findAny().orElseThrow();
        assertEquals(3, exampleEndpoint.methods().size(), "Method count in endpoint");

        assertEquals(Arrays.stream(BasicEntities.class.getDeclaredClasses())
                        .filter(c -> c.isAnnotationPresent(ShouldBeParsed.class))
                        .map(Class::getName).sorted().toList(),
                parserResult.entities().stream().map(EntityClass::name).sorted().toList(),
                "Same entities");
    }
}
