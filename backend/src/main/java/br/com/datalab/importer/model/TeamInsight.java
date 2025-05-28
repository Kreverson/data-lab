package br.com.datalab.importer.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamInsight {

    private String team;
    private int total_members;
    private int leaders;
    private int completed_projects;
    private double active_percentage;
}
