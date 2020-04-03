package secure.open.standard.backend.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoobarEvent {

    public FoobarEvent(String p ) {
        this.payload =  p;
    }
    private String payload;

    private String id;
    private String source;

    private String key;

    private final String type = "foobar-event";

}
