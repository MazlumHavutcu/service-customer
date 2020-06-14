package com.micro.service.event;

import com.micro.service.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RemoteEventListener {

    @Autowired
    private ConfigurableApplicationContext context;

    @EventListener(RemoteEvent.class)
    public void handle(final RemoteEvent event) {
        if (event.getMessageType().equals(EventType.RESTART.name()))
            restart();
    }

    private void restart() {
        ApplicationArguments args = context.getBean(ApplicationArguments.class);
        Thread thread = new Thread(() -> {
            context.close();
            context = SpringApplication.run(Application.class, args.getSourceArgs());
        });
        thread.setDaemon(false);
        thread.start();
    }
}
