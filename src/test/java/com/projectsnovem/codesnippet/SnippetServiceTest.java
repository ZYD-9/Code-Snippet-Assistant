package com.projectsnovem.codesnippet;

import com.projectsnovem.codesnippet.model.Snippet;
import com.projectsnovem.codesnippet.repository.SnippetRepository;
import com.projectsnovem.codesnippet.service.SnippetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SnippetServiceTest {

    @Mock
    private SnippetRepository snippetRepository;

    @InjectMocks
    private SnippetService snippetService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testGetAllSnippets(){
        Snippet snippet1 = new Snippet();
        Snippet snippet2 = new Snippet();
        List <Snippet> snippets = List.of(snippet1, snippet2);

        when(snippetRepository.findAll()).thenReturn(snippets);
        List<Snippet> result = snippetService.getAllSnippets();
        assertEquals(2,result.size());
    }

    @Test
    void testGetSnippetById(){
        Snippet snippet = new Snippet();
        snippet.setId(1L);
        when(snippetRepository.findById(1L)).thenReturn(java.util.Optional.of(snippet));
        Optional<Snippet> result= snippetService.getSnippetById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L,result.get().getId());

    }

    @Test
    void testGetSnippetByTitle(){
        Snippet snippet = new Snippet();
        snippet.setTitle("Promise");
        List<Snippet> snippets = List.of(snippet);
        when(snippetRepository.findByTitle("Promise")).thenReturn(snippets);
        List<Snippet> result = snippetService.getByTitle("Promise");
        assertEquals(1,result.size());
        assertEquals("Promise",result.get(0).getTitle());
    }

    @Test
    void testGetSnippetByLanguage(){
        Snippet snippet = new Snippet();
        snippet.setLanguage("Java");
        List<Snippet> snippets = List.of(snippet);
        when(snippetRepository.findByLanguage("Java")).thenReturn(snippets);
        List<Snippet> result = snippetService.getSnippetByLanguage("Java");
        assertEquals(1,result.size());
        assertEquals("Java",result.get(0).getLanguage());
    }

    @Test
    void testCreateSnippet(){
        Snippet snippet = new Snippet();
        when(snippetRepository.save(snippet)).thenReturn(snippet);

        Snippet result = snippetService.createSnippet(snippet);
        assertNotNull(result);
    }

    @Test
    void testUpdateSnippet(){
        Snippet existingSnippet = new Snippet();
        existingSnippet.setId(1L);
        Snippet updatedDetails = new Snippet();
        updatedDetails.setLanguage("Python");

        when(snippetRepository.findById(1L)).thenReturn(Optional.of(existingSnippet));
        when(snippetRepository.save(existingSnippet)).thenReturn(existingSnippet);

        Snippet result = snippetService.updateSnippet(1L, updatedDetails);
        assertEquals ("Python", result.getLanguage());
    }

    @Test
    void testDeleteSnippet(){
        doNothing().when(snippetRepository).deleteById(1L);
        snippetService.deleteSnippet(1L);
        verify(snippetRepository, times(1)).deleteById(1L);
    }

}
