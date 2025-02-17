package com.barcellospedro.quickcli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

@SpringBootApplication
@CommandScan
public class QuickCliApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuickCliApplication.class, args);
    }
}
