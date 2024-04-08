package fr.routardfilrouge.routard.dao;

import fr.routardfilrouge.routard.metier.AdministrativeDocType;
import fr.routardfilrouge.routard.metier.AdministrativeDocument;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AdministrativeDocumentDAO extends DAO<AdministrativeDocument, AdministrativeDocument>{
    @Override
    public ArrayList<AdministrativeDocument> getAll() {
        String query = "SELECT ID_DOCUMENT,\n" +
                "\t\tNOM_DOCUMENT,\n" +
                "\t\tT.ID_TYPE,\n" +
                "\t\tNOM_TYPE\n" +
                "FROM DOCUMENTS_ADMINISTRATIFS D\n" +
                "JOIN TYPE_DOCUMENT T ON T.ID_TYPE = D.ID_TYPE;";

        ArrayList<AdministrativeDocument> documents = new ArrayList<>();

        try(Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(query);

            while(rs.next()) {
                AdministrativeDocType type = new AdministrativeDocType(rs.getInt("ID_TYPE"), rs.getString("NOM_TYPE"));
                AdministrativeDocument doc = new AdministrativeDocument(rs.getInt("ID_DOCUMENT"), rs.getString("NOM_DOCUMENT"), type);

                documents.add(doc);
            }

            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return documents;
    }

    public ArrayList<AdministrativeDocType> getAllDocTypes() {
        String query = "SELECT * FROM TYPE_DOCUMENT;";
        ArrayList<AdministrativeDocType> types = new ArrayList<>();

        try(Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(query);

            while(rs.next())
                types.add(new AdministrativeDocType(rs.getInt("ID_TYPE"), rs.getString("NOM_TYPE")));

            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return types;
    }

    @Override
    public ArrayList<AdministrativeDocument> getLike(AdministrativeDocument searchObject) {
        return null;
    }

    @Override
    public boolean update(AdministrativeDocument object) {
        return false;
    }

    @Override
    public boolean post(AdministrativeDocument object) {
        return false;
    }

    @Override
    public boolean delete(AdministrativeDocument object) {
        return false;
    }
}
