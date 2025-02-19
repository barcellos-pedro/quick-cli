package com.barcellospedro.quickcli;

import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import java.io.IOException;

import static java.text.MessageFormat.format;

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

    @Command(command = "dto")
    String createDTO(
            @Option(required = true, shortNames = 'n', longNames = "name") String className,
            @Option(shortNames = 'r', longNames = "record", defaultValue = "false") Boolean useRecord
    ) {
        try (var file = FilesHelper.getWriter(className)) {
            file.write(content(className));
            return "DTO created!";
        } catch (IOException exception) {
            return exception.getMessage();
        }
    }

    private static String content(String className) {
        // TOOD: Check for boolean flag to use Records or POJOs
        return format("""
                package {0};
                
                record {1}(String name) '{}'
                """, FilesHelper.rootPackage(), className);
    }


}
