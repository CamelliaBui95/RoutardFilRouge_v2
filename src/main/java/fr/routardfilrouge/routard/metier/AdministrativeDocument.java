package fr.routardfilrouge.routard.metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


public class AdministrativeDocument {
    private IntegerProperty idDocument;
    private StringProperty documentName;

    @Setter
    @Getter
    private AdministrativeDocType documentType;

    public AdministrativeDocument() {
        idDocument = new SimpleIntegerProperty(0);
        documentName = new SimpleStringProperty("Document");
        documentType = new AdministrativeDocType();
    }

    public AdministrativeDocument(int idEntryDoc, String entryDocName, AdministrativeDocType entryDocType) {
        this.idDocument = new SimpleIntegerProperty(idEntryDoc);
        this.documentName = new SimpleStringProperty(entryDocName);
        this.documentType = entryDocType;
    }

    public int getIdDocument() {
        return idDocument.get();
    }

    public IntegerProperty idDocumentProperty() {
        return idDocument;
    }

    public void setIdDocument(int idDocument) {
        this.idDocument.set(idDocument);
    }

    public String getDocumentName() {
        return documentName.get();
    }

    public StringProperty documentNameProperty() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName.set(documentName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdministrativeDocument that = (AdministrativeDocument) o;
        return Objects.equals(idDocument.get(), that.idDocument.get()) && Objects.equals(documentName.get(), that.documentName.get()) && documentType.equals(that.documentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDocument, documentName, documentType);
    }

    @Override
    public String toString() {
        return this.documentName.get();
    }
}
