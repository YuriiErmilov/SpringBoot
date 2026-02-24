package org.skypro.skyshopOne.model.article;

import org.skypro.skyshopOne.model.search.Searchable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;
    private final String title;
    private final String text;


    public Article(UUID id,String title, String text) {
        this.title = title;
        this.text = text;
        this.id = id;
    }

    public String getSearchTerm() {
        return this.title + " " + this.text;
    }

    public String getSearchableType() {
        return "ARTICLE";
    }

    public String getName() {
        return this.title;
    }

    public String toString() {
        return "Название статьи" + this.title + "'Текст статьи" + this.text + "'";
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Article article = (Article)o;
            return Objects.equals(this.title, article.title);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hashCode(this.title);
    }

    public UUID getId() {
        return this.id;
    }
}
