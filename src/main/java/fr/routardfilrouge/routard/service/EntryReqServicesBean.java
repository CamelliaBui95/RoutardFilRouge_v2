package fr.routardfilrouge.routard.service;

import fr.routardfilrouge.routard.dao.DAOFactory;
import fr.routardfilrouge.routard.metier.*;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class EntryReqServicesBean {
    private ArrayList<EntryRequirement> entryReqs;
    private ObservableList<EntryRequirement> observableEntryReqs;

    private ArrayList<EntryReqType> entryReqTypes;
    private ObservableList<EntryReqType> observableEntryReqTypes;
    public EntryReqServicesBean() {
        //entryReqs = DAOFactory.getEntryRequirementDAO().getAll();
        //observableEntryReqs = FXCollections.observableArrayList(entryReqs);
        //observableEntryReqs.add(0, new AdministrativeDocument(0, "Documents (" + entryReqs.size() + ")", new AdministrativeDocType()));

        /*entryReqTypes = DAOFactory.getEntryReqTypeDAO().getAllDocTypes();
        observableEntryReqTypes = FXCollections.observableArrayList(entryReqTypes);
        observableEntryReqTypes.add(0, new AdministrativeDocType(0, "Types (" + entryReqTypes.size() + ")"));*/
    }
    public void fetchAdministrativeReqsForCountry(Country country) {
        ArrayList<CountryEntryRequirement> adminReqs = DAOFactory.getEntryRequirementDAO().getAdministrativeReqs(country);

        country.setAdministrativeReqs(adminReqs);
    }
    public void fetchMedicalReqsForCountry(Country country) {
        ArrayList<CountryEntryRequirement> medicalReqs = DAOFactory.getEntryRequirementDAO().getMedicalReqs(country);

        country.setMedicalReqs(medicalReqs);
    }

    public void fetchVisaExemptedCountries(Country country) {
        ArrayList<VisaExemptedCountry> visaExemptedCountries = DAOFactory.getVisaExemptionDAO().getLike(country);

        country.setVisaExemptedCountries(visaExemptedCountries);
    }


    public boolean postAdminReqForCountry(CountryEntryRequirement newAdminReq) {
        return DAOFactory.getEntryRequirementDAO().post(newAdminReq);
    }

    public boolean updateEntryReqForCountry(CountryEntryRequirement adminReq) {
        return DAOFactory.getEntryRequirementDAO().update(adminReq);
    }
}
