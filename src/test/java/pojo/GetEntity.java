package pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetEntity {
    @Builder.Default
    private int id = 0;

    @Builder.Default
    private AdditionGet addition = AdditionGet.builder().build();

    @Builder.Default
    private int[] important_numbers = {42, 87, 15};

    @Builder.Default
    private String title = "Title";

    @Builder.Default
    private boolean verified = true;
}
