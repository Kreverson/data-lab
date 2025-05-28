package br.com.datalab.importer.model;

import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private UUID id;
    private String name;
    private int age;
    private int score;
    private Boolean active;
    private String country;
    private Team team;
    private List<Log> logs;
}
