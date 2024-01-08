package fr.routardfilrouge.routard.metier;

public class Language {
    private String idLanguage;
    private String languageName;
    public Language(String idLanguage, String languageName) {
        this.idLanguage = idLanguage;
        this.languageName = languageName;
    }

    public String getIdLanguage() {
        return idLanguage;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setIdLanguage(String idLanguage) {
        this.idLanguage = idLanguage;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    @Override
    public String toString() {
        return languageName;
    }
}
