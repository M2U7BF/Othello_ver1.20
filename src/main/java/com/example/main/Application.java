package com.example.main;

import com.example.ui.view.View;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

import static com.example.ui.view.View.TITLE;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        builder.headless(false);
        context = builder.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            View frame = new View(TITLE);
            frame.setVisible(true);
        });
    }

    public static void restart() {
        ApplicationArguments args = context.getBean(ApplicationArguments.class);

        Thread thread = new Thread(() -> {
            context.close();
            SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
            builder.headless(false);
            context = builder.run(args.getSourceArgs());
        });

        thread.setDaemon(false);
        thread.start();
    }

    public static void shutdown() {
        context.close();
    }
}
