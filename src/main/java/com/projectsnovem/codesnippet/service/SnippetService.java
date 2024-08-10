package com.projectsnovem.codesnippet.service;
import com.projectsnovem.codesnippet.model.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projectsnovem.codesnippet.repository.SnippetRepository;
import java.util.List;
import java.util.Optional;

@Service
public class SnippetService {

    private final SnippetRepository snippetRepository;

    @Autowired
    public SnippetService(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository; // refers to the constructor of the SnippetRepository class
    }

    public List<Snippet> getAllSnippets() {
        return snippetRepository.findAll(); // returns all snippets
    }

    public Optional<Snippet> getSnippetById(Long id) {
        return snippetRepository.findById(id); // returns a snippet by id
    }

    public List<Snippet> getByTitle(String title) {
        return snippetRepository.findByTitle(title); // returns a snippet by title
    }
    
    public List<Snippet> getSnippetByLanguage(String language){
        return snippetRepository.findByLanguage(language); // returns a snippet by language
    }

    public Snippet createSnippet(Snippet snippet) {
        return snippetRepository.save(snippet); // adds a snippet
    }

    public Snippet updateSnippet(Long id, Snippet snippetDetails) {
        return snippetRepository.findById(id)
                .map(snippet -> {
                            snippet.setTitle(snippetDetails.getTitle());
                            snippet.setCodecontent(snippetDetails.getCodecontent());
                            snippet.setLanguage(snippetDetails.getLanguage());
                            return snippetRepository.save(snippet);
                        }).orElseThrow(()-> new RuntimeException("Snippet not found")); // updates a snippet
    }

    public void deleteSnippet(Long id) {
        snippetRepository.deleteById(id); // deletes a snippet by id
    }

    
}
