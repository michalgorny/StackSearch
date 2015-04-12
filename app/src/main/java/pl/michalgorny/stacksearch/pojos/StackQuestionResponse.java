package pl.michalgorny.stacksearch.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * POJO for response of Stack question list. Contains list of question and returned parameters from server
 */
@Data
public class StackQuestionResponse {
    @JsonProperty("items")
    private List<StackItem> items = new ArrayList<StackItem>();
    @JsonProperty("has_more")
    private Boolean hasMore;
    @JsonProperty("quota_max")
    private Integer quotaMax;
    @JsonProperty("quota_remaining")
    private Integer quotaRemaining;
}
