package com.pedro_henrique.Gerenciador_tarefas_domesticas.entities;

import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Enums.PriorityTarefa;
import com.pedro_henrique.Gerenciador_tarefas_domesticas.entities.Enums.StatusTarefa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_tarefa")
public class Tarefa {
    
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String task_name;

    @Enumerated(EnumType.STRING)
    private PriorityTarefa priorityTarefa;

    @Enumerated(EnumType.STRING)
    private StatusTarefa statusTarefa;

    @ManyToOne
    @JoinColumn(name = "responsible_id", nullable = false)
    private Pessoa responsible;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoriaTarefa category;

}
