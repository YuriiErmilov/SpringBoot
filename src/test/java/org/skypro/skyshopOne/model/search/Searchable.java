package org.skypro.skyshopOne.model.search;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public interface Searchable {
    @JsonIgnore
    String getSearchTerm();
    @JsonIgnore
    String getSearchableType();

    String getName();

    default String getStringRepresentation() {
        String var10000 = this.getName();
        return var10000 + " - " + this.getSearchTerm();
    }

    UUID getId();


}
