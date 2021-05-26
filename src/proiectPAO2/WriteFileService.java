package proiectPAO2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

public class WriteFileService {
    private static WriteFileService instance;

    public static WriteFileService getInst() {
        if (instance == null)
            instance = new WriteFileService();
        return instance;
    }

    public void writeAppointmentsFile(AppointmentsManagement appointmentsManagement, DoctorsManagement doctorsManagement, String path) {

        File csvFile = new File(path);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(csvFile, true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            for (var appointment : appointmentsManagement.getAppointments())
            {
                var lastName = appointment.getPatient().getLastName();
                var firstName = appointment.getPatient().getFirstName();
                var age = appointment.getPatient().getAge();
                var sex = appointment.getPatient().getSex();
                var date = appointment.getDate();
                var time = appointment.getTime();
                var lastNameDoctor = appointment.getPatient().getDoctor().getLastName();
                var firstNameDoctor = appointment.getPatient().getDoctor().getLastName();
                bw.append(lastName + "," + firstName + "," + age + "," + sex + "," + lastNameDoctor + "," + firstNameDoctor + "," +
                        date + "," + time + '\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AuditService.logAuditFile("writeFileAppointments");
    }

    public void writePrescriptionFile(String path, AppointmentsManagement appointmentsManagement) {

        File csvFile = new File(path);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(csvFile, true));

            List<Patient> patients = new ArrayList<>();

            var prescriptions = appointmentsManagement.getPrescriptions();

            for (var prescription : prescriptions)
            {
                var lastName = prescription.getPatient().getLastName();
                var firstName = prescription.getPatient().getFirstName();
                var injections = prescription.getInjections();
                var pills = prescription.getPills();
                bw.append(lastName + "," + firstName + "," + "injecions: " + Arrays.toString(injections) + "," + "pills: " +
                        Arrays.toString(pills) + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AuditService.logAuditFile("writeFilePrescriptions");
    }
}
