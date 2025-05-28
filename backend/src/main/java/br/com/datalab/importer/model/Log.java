package br.com.datalab.importer.model;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    private Date date;
    private String action;
}
