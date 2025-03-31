package org.skypro.skyshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.SplittableRandom;
import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;
    private final String title;
    private final String content;

    public Article(UUID id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return title + " " + content;
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "article";
    }
}
