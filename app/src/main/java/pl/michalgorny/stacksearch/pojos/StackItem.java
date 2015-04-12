package pl.michalgorny.stacksearch.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Pojo for one item. It represents a item object from server response.
 */
@Data
public class StackItem {
    @JsonProperty("owner")
    private Owner owner;
    @JsonProperty("answer_count")
    private Integer answerCount;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("question_id")
    private Integer questionId;
    @JsonProperty("link")
    private String link;
    @JsonProperty("title")
    private String title;
}
