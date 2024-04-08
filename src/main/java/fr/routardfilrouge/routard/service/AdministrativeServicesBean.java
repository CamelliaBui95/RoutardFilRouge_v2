package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.AdministrativeDocType;
import fr.routardfilrouge.routard.metier.AdministrativeDocument;
import fr.routardfilrouge.routard.metier.AdministrativeRequirement;
import fr.routardfilrouge.routard.metier.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class AdministrativeServicesBean {
    private ArrayList<AdministrativeDocument> adminDocs;
    private ObservableList<AdministrativeDocument> observableAdminDocs;

    private ArrayList<AdministrativeDocType> adminDocTypes;
    private ObservableList<AdministrativeDocType> observableAdminDocTypes;
    public AdministrativeServicesBean() {
        adminDocs = DAOFactory.getAdministrativeDocumentDAO().getAll();
        observableAdminDocs = FXCollections.observableArrayList(adminDocs);
        observableAdminDocs.add(0, new AdministrativeDocument(0, "Documents (" + adminDocs.size() + ")", new AdministrativeDocType()));

        adminDocTypes = DAOFactory.getAdministrativeDocumentDAO().getAllDocTypes();
        observableAdminDocTypes = FXCollections.observableArrayList(adminDocTypes);
        observableAdminDocTypes.add(0, new AdministrativeDocType(0, "Types (" + adminDocTypes.size() + ")"));
    }
    public void fetchAdministrativeReqsForCountry(Country country) {
        ArrayList<AdministrativeRequirement> adminReqs = DAOFactory.getAdministrativeRequirementDAO().getLike(country);

        country.setAdministrativeReqs(adminReqs);
    }
}
