package studyfieldtechnologypvtltd.myralmdemo.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by SATHISH on 27-Jun-17.
 */

public class Person extends RealmObject {

    @PrimaryKey
    private long personid;
    private String personName, personEmail, personWebsite;
    private int personAge;

    public Person() {

    }

    public Person(long personid, String personName, String personEmail, String personWebsite, int personAge) {
        this.personid = personid;
        this.personName = personName;
        this.personEmail = personEmail;
        this.personWebsite = personWebsite;
        this.personAge = personAge;
    }

    public long getPersonid() {
        return personid;
    }

    public void setPersonid(long personid) {
        this.personid = personid;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonWebsite() {
        return personWebsite;
    }

    public void setPersonWebsite(String personWebsite) {
        this.personWebsite = personWebsite;
    }

    public int getPersonAge() {
        return personAge;
    }

    public void setPersonAge(int personAge) {
        this.personAge = personAge;
    }
}
