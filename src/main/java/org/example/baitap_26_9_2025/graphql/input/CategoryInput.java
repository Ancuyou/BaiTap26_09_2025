package org.example.baitap_26_9_2025.graphql.input;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CategoryInput(
        @JsonProperty("name") String name,
        @JsonProperty("images") String images
) {
    @JsonCreator
    public CategoryInput { }
}
