package com.projectsnovem.codesnippet.repository;

import com.projectsnovem.codesnippet.model.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface SnippetRepository extends JpaRepository <Snippet, Long> {
    List<Snippet> findByLanguage (String language); // method to find snippets by language
    List<Snippet> findByTitle (String title);// method to find snippets by language
}
