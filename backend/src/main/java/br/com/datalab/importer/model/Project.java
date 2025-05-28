package br.com.datalab.importer.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    private String name;
    private Boolean completed;
}
