package pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateEntity {
    @Builder.Default
    private Addition addition = Addition.builder().build();

    @Builder.Default
    private int[] important_numbers = {42, 87, 15};

    @Builder.Default
    private String title = "Title";

    @Builder.Default
    private boolean verified = true;
}
