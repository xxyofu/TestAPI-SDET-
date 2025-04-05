package pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ListEntities {
    @Builder.Default
    private GetEntity[] entity = {};
}
