package org.skypro.skyshopone.model.search;

import java.util.UUID;

public interface Searchable {
    String getSearchTerm();
    String getSearchableType();

    String getName();

    default String getStringRepresentation() {
        String var10000 = this.getName();
        return var10000 + " - " + this.getSearchTerm();
    }

    UUID getId();


}
