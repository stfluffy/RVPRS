package com.psuti.rvprs.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author Modenov D.A.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {

    @JsonView(View.Public.class)
    private Long id;

    @JsonView({View.Public.class, View.Update.class})
    @NotBlank
    private String name;

    @JsonView({View.Public.class, View.Update.class})
    private Integer price;

    @JsonView({View.Public.class, View.Update.class})
    private Integer quantity;

    @JsonView({View.Public.class, View.Update.class})
    private Long shelf;

}
