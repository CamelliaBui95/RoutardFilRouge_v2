package fr.routardfilrouge.routard.metier;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryRequirement {
    private int idEntryReq;
    private String entryReqName;
    private EntryReqType entryReqType;
}
