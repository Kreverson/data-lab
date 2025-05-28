package br.com.datalab.importer.model;

import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    private String name;
    private Boolean leader;
    private Set<Project> projects;
}
