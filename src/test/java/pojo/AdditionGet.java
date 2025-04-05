package pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdditionGet {

    @Builder.Default
    private String additional_info = "Extra info";

    @Builder.Default
    private int additional_number = 123;

    @Builder.Default
    private int id = 0;

}
