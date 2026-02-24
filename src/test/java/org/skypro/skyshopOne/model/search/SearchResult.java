package org.skypro.skyshopOne.model.search;


public class SearchResult {
    private final String id;
    private final String name;
    private final String getSearchableType;

    public SearchResult(String  id, String name, String getSearchableType) {
        this.id = id;
        this.name = name;
        this.getSearchableType = getSearchableType;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSearchableType() {
        return getSearchableType;
    }

    public static SearchResult fromSearchable (Searchable searchable) {
        return  new SearchResult(String.valueOf(searchable.getId()), searchable.getName(), searchable.getSearchableType());
    }
}
