package com.barcellospedro.quickcli;

import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

@Command
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
}
