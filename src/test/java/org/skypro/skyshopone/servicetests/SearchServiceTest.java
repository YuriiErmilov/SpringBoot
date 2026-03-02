package org.skypro.skyshopone.servicetests;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshopone.model.search.SearchResult;
import org.skypro.skyshopone.model.search.Searchable;
import org.skypro.skyshopone.service.SearchService;
import org.skypro.skyshopone.service.StorageService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    StorageService storageService;

    @InjectMocks
    SearchService searchService;

    @Test
    public void searchReturnEmptyWhenStorgeEmpty(){
        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());

        Collection<SearchResult> result = searchService.search("Кальян");

       assertTrue(result.isEmpty());
    }

    @Test
    public void searchReturnEmptyWhenNoMatches(){
        Searchable searchable = mock(Searchable.class);

        when(searchable.getName()).thenReturn("Чаша");
        when(storageService.getAllSearchables()).thenReturn(List.of(searchable));

        Collection<SearchResult> results = searchService.search("Кальян");

        assertTrue(results.isEmpty());
    }

    @Test
    public void searchReturnOneMatch(){
        UUID id = UUID.randomUUID();

        Searchable searchable = mock(Searchable.class);

        when(searchable.getId()).thenReturn(id);
        when(searchable.getName()).thenReturn("Кальян");
        when(searchable.getSearchableType()).thenReturn("PRODUCT");

        when(storageService.getAllSearchables()).thenReturn(List.of(searchable));

        Collection<SearchResult> result = searchService.search("Кальян");

        assertEquals(1, result.size());

        SearchResult searchResult = result.iterator().next();

        assertEquals(id, searchResult.getId());
        assertEquals("Кальян Alpha Hookah", searchResult.getName());
        assertEquals("PRODUCT", searchResult.getSearchableType());
    }

    @Test
    public void searchIsCaseInsensitive(){
        Searchable searchable = mock(Searchable.class);

        when(searchable.getName()).thenReturn("КАЛЬЯН");

        when(searchable.getSearchableType()).thenReturn("PRODUCT");

        Collection<SearchResult> results = searchService.search("кальян");

        assertEquals(1, results.size());
    }
}
