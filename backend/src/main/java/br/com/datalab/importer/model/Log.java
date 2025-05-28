package br.com.datalab.importer.model;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    private LocalDate date;
    private String action;
}
