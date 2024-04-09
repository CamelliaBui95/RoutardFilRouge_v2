package fr.routardfilrouge.routard.metier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryReqType {
    private int idReqType;
    private String reqTypeName;
}
