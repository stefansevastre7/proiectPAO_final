package proiectPAO1;

import java.util.Collections;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        ClasaServiciu servicii = new ClasaServiciu();
        DoctorsManagement doctorsManagement = new DoctorsManagement();
        NursesManagement nursesManagement = new NursesManagement();
        AppointmentsManagement appointmentsManagement = new AppointmentsManagement();
        for(int i=0;i<3;i++) {
            Doctor d = servicii.readDoctor();
            doctorsManagement.addDoctor(d);
        }
        for(int i=0;i<2;i++) {
            Nurse n = servicii.readNurse();
            nursesManagement.addNurse(n);
        }
        for(int i=0;i<2;i++) {
            Appointment p = servicii.readAppointment(doctorsManagement);
            appointmentsManagement.addAppointment(p);
        }
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


        servicii.deleteAppointment(appointmentsManagement );


        appointmentsManagement.showAppointments();


        servicii.printAppointments(appointmentsManagement);


        servicii.writePrescription( appointmentsManagement);


        appointmentsManagement.showPatients();
        doctorsManagement.showClinicInfo();


    }
}
