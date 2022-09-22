package test.example.chat;

import com.github.rjeschke.txtmark.Processor;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import test.example.chat.servises.Storage;

@Route("")
@Push
public class ChatView extends VerticalLayout {

    private final Storage storage;

    public ChatView(Storage storage) {
        this.storage = storage;

//        new Grid<Storage.ChatMessage>();
        Grid<Storage.ChatMessage> grid = new Grid<>();
        grid.setItems(storage.getMessages());
        grid.addColumn(new ComponentRenderer<>(message -> new Html(renderRow(message))))
                .setAutoWidth(true)
                .setHeader("Hello people ..");
        TextField field = new TextField();

        add(
                new H3("Chat.."),
                grid,
                new HorizontalLayout() {{
                    add(
                            field,
                            new Button("Send ..") {{
                                addClickListener(click -> {
                                    storage.addRecord("", field.getValue());
                                    field.clear();
                                });
                                addClickShortcut(Key.ENTER);
                            }}
                    );

                }}

        );
        ;
    }

    private String renderRow(Storage.ChatMessage message) {
        return Processor.process(String.format("**%s**: %s", message.getName(),message.getMessage()));
    }
}
