package proiectPAO2;

import java.util.Collections;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);




    public static void main(String[] args) {

        ClinicAdministrationService servicii = new ClinicAdministrationService();
        var filereader = ReadFileService.getInst();
        var filewriter = WriteFileService.getInst();

        String PATH = "src/proiectPAO2/Data/";
        String PATH2 = "src/proiectPAO2/results/";
        var doctorsManagement = filereader.readDoctors(PATH+"doctors.csv");
        var nursesManagement = filereader.readNurses(PATH+"assistants.csv");
        var appointmentsManagement = filereader.readAppointments(PATH+"appointments.csv");
        filereader.readPrescriptions(PATH+"prescriptions.csv",appointmentsManagement);

        filewriter.writeAppointmentsFile(appointmentsManagement,doctorsManagement,PATH2+"appointments.csv");
        filewriter.writePrescriptionFile(PATH2+"prescriptions.csv",appointmentsManagement);



//        for(int i=0;i<2;i++) {
//            Nurse n = servicii.readNurse();
//            nursesManagement.addNurse(n);
//        }

//        for(int i=0;i<2;i++) {
//            Appointment p = servicii.readAppointment(doctorsManagement);
//            appointmentsManagement.addAppointment(p);
//        }


        //Sort the ArayList with doctors
        Collections.sort(doctorsManagement.getDoctors(), new DoctorComparator());

        doctorsManagement.showDoctors();

        System.out.println("Enter the speciality");
        String speciality = scanner.next();
        doctorsManagement.showDoctorsSpeciality(speciality);


        System.out.println("Enter the doctor's last name: ");
        String lastName = scanner.next();
        System.out.println("Enter the doctor's first name: ");
        String firstName = scanner.next();
        doctorsManagement.showDoctorWorkingHours(lastName, firstName);


        nursesManagement.showNurses();


        servicii.changeAppointment(appointmentsManagement);


        servicii.deleteAppointment(appointmentsManagement);


        appointmentsManagement.showAppointments();


        servicii.printAppointments(appointmentsManagement);


        servicii.writePrescription( appointmentsManagement);


        appointmentsManagement.showPatients();
        doctorsManagement.showClinicInfo();


    }
}
