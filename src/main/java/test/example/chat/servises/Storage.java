package test.example.chat.servises;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventBus;
import com.vaadin.flow.component.html.Div;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class Storage {


    @Getter
    private Queue<ChatMessage> messages = new ConcurrentLinkedQueue<>();
    private ComponentEventBus eventBus = new ComponentEventBus(new Div());


    public void addRecord(String user, String message) {
        messages.add(new ChatMessage(user,message));

    }

    @Getter
    @AllArgsConstructor
    public static class ChatMessage{
        private String name;
        private String message;

    }

    public static class ChatEvent extends ComponentEvent<Div>{

        public ChatEvent() {
            super(new Div(), false);
        }
    }
}
