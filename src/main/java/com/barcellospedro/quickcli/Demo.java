package com.barcellospedro.quickcli;

import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import java.io.IOException;
import java.util.List;

@Command(command = "quick generate")
class Demo extends Commands {
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
    String dto(
            @Option(required = true, shortNames = 'n', longNames = "name") String className,
            @Option(shortNames = 'a', longNames = "attributes", description = "Class instance fields") List<String> attributes
    ) {
        System.out.println("Class: " + className);
        System.out.println("Attributes: " + attributes);

        try (var file = FilesHelper.getWriter(className)) {
            String content = getClassDefinition(className, attributes);
            file.write(content);
            return "DTO created!";
        } catch (IOException exception) {
            return exception.getMessage();
        }
    }
}
