package proiectPAO2;

import java.util.Scanner;

public class ClinicAdministrationService {

    static Scanner scanner = new Scanner(System.in);

    public Doctor readDoctor() {
        System.out.println("\n Se citeste parametri unui doctor.");
        System.out.println("Prenume");
        String lastName = scanner.nextLine();
        System.out.println("Nume");
        String firstName = scanner.nextLine();
        System.out.println("Varsta:");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Sex: ");
        String sex = scanner.nextLine();
        System.out.println("Specialitate: ");
        String speciality = scanner.nextLine();
        System.out.println("Cost consultatie la doctor: ");
        int consultation_cost = Integer.parseInt(scanner.nextLine());
        System.out.println("Inceputul schimbului: ");
        int shiftStart = Integer.parseInt(scanner.nextLine());
        System.out.println("Sfarsitul schimbului: ");
        int shiftEnd = Integer.parseInt(scanner.nextLine());
        Doctor d = new Doctor(lastName, firstName, age, sex, speciality, consultation_cost, shiftStart, shiftEnd);
        return d;
    }

    public static Nurse readNurse() {
        System.out.println("Prenume");
        String lastName = scanner.nextLine();
        System.out.println("Nume");
        String firstName = scanner.nextLine();
        System.out.println("Varsta:");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Sex: ");
        String sex = scanner.nextLine();
        System.out.println("Schimbulul: ");
        String shift = scanner.nextLine();
        Nurse n = new Nurse(lastName, firstName, age, sex, shift);
        return n;
    }

    public static Appointment readAppointment(DoctorsManagement doctorsManagement) {
        System.out.println("Se citesc parametrii programarii");
        System.out.println("Prenume pacient");
        String lastName = scanner.nextLine();
        System.out.println("Nume pacient");
        String firstName = scanner.nextLine();
        System.out.println("Varsta pacient:");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Sex pacient: ");
        String sex = scanner.nextLine();
        System.out.println("Data programarii:");
        int date = Integer.parseInt(scanner.nextLine());
        System.out.println("Ora programarii: ");
        int time = Integer.parseInt(scanner.nextLine());
        System.out.println("Prenume doctor:");
        String lastNameDoctor = scanner.nextLine();
        System.out.println("Nume doctor:");
        String firstNameDoctor = scanner.nextLine();
        System.out.println("1. Adult \n" + "2. Student \n" + "3. Pensioner ");
        int option = scanner.nextInt();
        Patient p = null;
        float price = 0;
        if (option == 1) {
            p = new AdultPatient(lastName, firstName, age, sex, doctorsManagement.getDoctor(firstNameDoctor, lastNameDoctor));
            System.out.println(doctorsManagement.getDoctor(firstNameDoctor, lastNameDoctor));
            price = doctorsManagement.consultationCost(p);
        } else if (option == 2) {
            p = new StudentPatient(lastName, firstName, age, sex, doctorsManagement.getDoctor(firstNameDoctor, lastNameDoctor));
            price = doctorsManagement.consultationCost(p) * 0.75f;
        } else if (option == 3) {
            p = new PensionerPatient(lastName, firstName, age, sex, doctorsManagement.getDoctor(firstNameDoctor, lastNameDoctor));
            price = doctorsManagement.consultationCost(p) * 0.5f;
        } else {
            System.out.println("Invalid");
        }
        System.out.println("Costul consultarii este: " + price);
        Appointment a = new Appointment(p, date, time);
        return a;
    }


    public static void changeAppointment(AppointmentsManagement appointmentsManagement) {
        System.out.println("Se va modifica o programare.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Prenumele pacientului");
        String lastName = scanner.nextLine();
        System.out.println("Numele pacientului");
        String firstName = scanner.nextLine();
        System.out.println("Data programarii initiale:");
        int date = scanner.nextInt();
        System.out.println("Ora programarii initiale:");
        int time = scanner.nextInt();
        System.out.println("Data programarii noi: ");
        int newDate = scanner.nextInt();
        System.out.println("Ora programarii noi: ");
        int newTime = scanner.nextInt();
        appointmentsManagement.changeAppointment(firstName, lastName, date, time, newDate, newTime);
        AuditService.logAuditFile("changeAppointment");
    }

    public static void printAppointments(AppointmentsManagement appointmentsManagement) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Se va afisa programarile pacientului");
        System.out.println("Prenume pacient:");
        String lastName = scanner.nextLine();
        System.out.println("Nume pacient:");
        String firstName = scanner.nextLine();
        System.out.println(lastName + " " + firstName + " are urmatoarele programari: ");
        appointmentsManagement.printPatientAppointments(lastName, firstName);
        AuditService.logAuditFile("printPatientAppointments");
    }

    public static void deleteAppointment( AppointmentsManagement appointmentsManagement) {
        System.out.println("Se va sterge o programare");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Prenumele pacientului");
        String lastName = scanner.nextLine();
        System.out.println("Numele pacientului");
        String firstName = scanner.nextLine();
        System.out.println("Data programarii");
        int date = scanner.nextInt();
        System.out.println("Ora programarii");
        int time = scanner.nextInt();
        appointmentsManagement.deleteAppointment(firstName, lastName, date, time);
        AuditService.logAuditFile("deleteAppointment");
    }

    public static void writePrescription(AppointmentsManagement appointmentsManagement) {
        System.out.println("Se adauga prescriere pentru pacient");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Prenume pacient");
        String lastName = scanner.nextLine();
        System.out.println("Nume pacient");
        String firstName = scanner.nextLine();
        System.out.println("Tastati injectiile recomandate");
        String line = scanner.nextLine();
        String[] injections = line.split(" ");
        System.out.println("Introduceti pastilele recomandate");
        String line2 = scanner.nextLine();
        String[] pills = line2.split(" ");
        Prescription prescription = new Prescription(appointmentsManagement.getPatient(lastName, firstName), injections, pills);
        appointmentsManagement.addPrescription(firstName, lastName, prescription);
        AuditService.logAuditFile("writePrescription");

    }




}
