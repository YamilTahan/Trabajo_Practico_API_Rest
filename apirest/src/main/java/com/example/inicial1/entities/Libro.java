package com.example.inicial1.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Audited
@Table(name = "Libro")
public class Libro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private int fecha;
    private String genero;
    private int paginas;
    private String autor;

    public Libro(Long id, String titulo, int fecha, String genero, int paginas, String autor, List<Autor> autores) {
        this.id = id;
        this.titulo = titulo;
        this.fecha = fecha;
        this.genero = genero;
        this.paginas = paginas;
        this.autor = autor;
        this.autores = autores != null ? autores : new ArrayList<>(); // Inicializa si es null
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name= "libro_autor",
            joinColumns = @JoinColumn(name="libro_id"), inverseJoinColumns = @JoinColumn(name="autor_id"))
    private List<Autor> autores = new ArrayList<>();
}