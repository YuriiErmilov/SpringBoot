package org.skypro.skyshopone.service;

import org.skypro.skyshopone.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService  {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String query) {
        return storageService.getAllSearchables().stream()
                .filter(item->item.getName().toLowerCase()
                        .contains(query.toLowerCase())).map(SearchResult::fromSearchable).collect(Collectors.toList());
    }
}
