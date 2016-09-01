package commerce.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author chanwook
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChildCategoryDto implements Serializable {

    private long categoryId;

    private String displayName;
}
