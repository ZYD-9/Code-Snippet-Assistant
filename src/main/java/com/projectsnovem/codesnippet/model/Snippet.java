package com.projectsnovem.codesnippet.model;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import java.time.LocalDateTime;

@Entity
@Table(name = "snippets")
@Getter
@Setter
@NoArgsConstructor


public class Snippet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column (columnDefinition = "TEXT")
    private String codecontent;
    private String language;
    private LocalDateTime created = LocalDateTime.now();



}
