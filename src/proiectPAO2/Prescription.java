package proiectPAO2;

import java.util.Arrays;

public class Prescription {

    private Patient patient;
    private String[] injections;
    private String[] pills;

    public Prescription(Patient patient, String[] injections, String[] pills) {
        this.patient = patient;
        this.injections = injections;
        this.pills = pills;
    }

    @Override
    public String toString() {
        return "Prescription " +
                "lastNamePatient='" + patient.getLastName() + '\'' +
                ", firstNamePatient='" + patient.getFirstName() + '\'' +
                ", injections=" + Arrays.toString(injections) +
                ", pills=" + Arrays.toString(pills) +
                ' ';
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String[] getInjections() {
        return injections;
    }

    public void setInjections(String[] injections) {
        this.injections = injections;
    }

    public String[] getPills() {
        return pills;
    }

    public void setPills(String[] pills) {
        this.pills = pills;
    }
}
