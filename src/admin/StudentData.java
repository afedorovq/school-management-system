package admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentData {
    private final StringProperty ID;
    private final StringProperty FIRST_NAME;
    private final StringProperty LAST_NAME;
    private final StringProperty EMAIL;
    private final StringProperty DATE_OF_BIRTH;

    public StudentData(String id, String firstName, String lastName, String email, String dateOfBirth) {
        ID = new SimpleStringProperty(id);
        FIRST_NAME = new SimpleStringProperty(firstName);
        LAST_NAME = new SimpleStringProperty(lastName);
        EMAIL = new SimpleStringProperty(email);
        DATE_OF_BIRTH = new SimpleStringProperty(dateOfBirth);
    }

    public String getID() {
        return ID.get();
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public String getFIRST_NAME() {
        return FIRST_NAME.get();
    }

    public StringProperty FIRST_NAMEProperty() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME.set(FIRST_NAME);
    }

    public String getLAST_NAME() {
        return LAST_NAME.get();
    }

    public StringProperty LAST_NAMEProperty() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME.set(LAST_NAME);
    }

    public String getEMAIL() {
        return EMAIL.get();
    }

    public StringProperty EMAILProperty() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL.set(EMAIL);
    }

    public String getDATE_OF_BIRTH() {
        return DATE_OF_BIRTH.get();
    }

    public StringProperty DATE_OF_BIRTHProperty() {
        return DATE_OF_BIRTH;
    }

    public void setDATE_OF_BIRTH(String DATE_OF_BIRTH) {
        this.DATE_OF_BIRTH.set(DATE_OF_BIRTH);
    }
}
