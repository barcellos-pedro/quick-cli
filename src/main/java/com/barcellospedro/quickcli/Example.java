package com.barcellospedro.quickcli;

import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.barcellospedro.quickcli.FilesHelper.rootPackage;
import static java.text.MessageFormat.format;
import static java.util.stream.Collectors.joining;

@Command(command = "quick generate")
class Example {
    @Command(command = "greet", description = "Displays a welcoming message.")
    String greet(@Option(description = "Tell your name. E.g: --name John Doe",
            defaultValue = "Spring", longNames = "name", shortNames = 'n') String name
    ) {
        return "Greetings %s!".formatted(name);
    }

    @Command(command = "example", description = "Example of the current API")
    String example(@Option(longNames = "person", shortNames = 'p', required = true) String person) {
        return "Hello %s.".formatted(person);
    }

    @Command(command = "dto", description = "Generates a DTO")
    String createDTO(
            @Option(required = true, shortNames = 'n', longNames = "name") String className,
            @Option(shortNames = 'a', longNames = "attributes", description = "Class instance fields") List<String> attributes
    ) {
        System.out.println(format("Class: {0}, Fields: {1}", className, attributes));

        try (var file = FilesHelper.getWriter(className)) {
            file.write(createDto(className, attributes));
            return "DTO created!";
        } catch (IOException exception) {
            return exception.getMessage();
        }
    }

    private static String createDto(String className, List<String> attributes) {
        Map<String, String> classAttributes = new HashMap<>();
        setClassAttributes(attributes, classAttributes);
        System.out.println(classAttributes);
        String fields = getFields(classAttributes);
        return buildClassDefinition(className, fields);
    }

    private static void setClassAttributes(List<String> attributes, Map<String, String> classAttributes) {
        attributes.stream()
                .map(attr -> attr.split(":")) // E.g: name:String
                .map(attr -> new SimpleEntry<>(attr[1], attr[0])) // <type:field>
                .forEach(entry -> {
                    classAttributes.put(entry.getKey(), entry.getValue());
                });
    }

    private static String getFields(Map<String, String> attrsMap) {
        return attrsMap.entrySet()
                .stream()
                .map(entry -> format("private {0} {1}", entry.getKey(), entry.getValue()))
                .collect(joining(";\n\t")) + ";";
    }

    private static String buildClassDefinition(String className, String fields) {
        return format("""
                package {0};
                
                class {1} '{'
                    {2}
                '}'
                """, rootPackage(), className, fields);
    }


}
