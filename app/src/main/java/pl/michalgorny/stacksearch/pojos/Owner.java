package pl.michalgorny.stacksearch.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * POJO class representing owner of post
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Owner {
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("profile_image")
    private String profileImage;
    @JsonProperty("display_name")
    private String displayName;
}
