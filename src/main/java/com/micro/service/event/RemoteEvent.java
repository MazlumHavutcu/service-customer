package com.micro.service.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

@NoArgsConstructor
@Data
public class RemoteEvent extends RemoteApplicationEvent {

    private static final long serialVersionUID = 4568065173733085381L;

    private String messageType;

    public RemoteEvent(final Object source,
                       final String id,
                       final String messageType) {
        super(source,
                id);
        this.messageType = messageType;
    }
}

enum EventType {
    RESTART,
    LOG;
}
