package com.projectsnovem.codesnippet.controller;

import com.projectsnovem.codesnippet.model.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projectsnovem.codesnippet.service.SnippetService;
import java.util.List;


@RestController
@RequestMapping("/api/snippets")
public class SnipppetController {

    private final SnippetService snippetService;

    @Autowired
    public SnipppetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @GetMapping
    public ResponseEntity<List<Snippet>> getAllSnippets() {
        return new ResponseEntity<>(snippetService.getAllSnippets(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Snippet> getSnipppetById(@PathVariable  Long id) {
       return snippetService.getSnippetById(id)
               .map(snippet -> new ResponseEntity<>(snippet, HttpStatus.OK))
               .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Snippet>> getSnippetByTitle(@PathVariable String title) {
        return new ResponseEntity<>(snippetService.getByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/language/{language}")
    public ResponseEntity<List<Snippet>> getSnippetByLanguage(@PathVariable String language) {
        return new ResponseEntity<>(snippetService.getSnippetByLanguage(language), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Snippet> createSnippet(@RequestBody Snippet snippet) {
        return new ResponseEntity<>(snippetService.createSnippet(snippet), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Snippet> updateSnippet(@PathVariable Long id, @RequestBody Snippet snippetDetails) {
        return new ResponseEntity<>(snippetService.updateSnippet(id, snippetDetails), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSnippet(@PathVariable Long id) {
        snippetService.deleteSnippet(id);
        return new ResponseEntity<>("Snippet deleted successfully",HttpStatus.NO_CONTENT);
    }


}
