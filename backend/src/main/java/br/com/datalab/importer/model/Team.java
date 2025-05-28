package br.com.datalab.importer.model;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    private String name;
    private Boolean leader;
    private List<Project> projects;
}
