package proiectPAO3;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.Scanner;

    public class appointmentsDB {


        public static void addAppointment(String host, String username, String pass) {

            Connection con = null;
            try {
                con = DriverManager.getConnection(host, username, pass);
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter patient's last name");
                String lastName = scanner.nextLine();
                System.out.println("Enter patient's first name");
                String firstName = scanner.nextLine();
                System.out.println("Enter patient's sex (F/M)");
                String sex = scanner.nextLine();
                System.out.println("Enter patient's age");
                int age = scanner.nextInt();
                System.out.println("Enter doctor's first name");
                String doctorFirstName = scanner.next();
                System.out.println("Enter doctor's last name");
                String doctorLastName = scanner.next();
                System.out.println("Enter appointment's date");
                int date = scanner.nextInt();
                System.out.println("Enter appointment's time");
                int time = scanner.nextInt();

                // the mysql insert statement
                String query = " insert into appointments (lastName, firstName, age, sex, doctorFirstName, doctorLastName, date, time)"
                        + " values (?, ?, ?, ?, ?, ?, ?, ?)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString (1, lastName);
                preparedStmt.setString (2, firstName);
                preparedStmt.setInt   (3, age);
                preparedStmt.setString(4, sex);
                preparedStmt.setString    (5, doctorFirstName);
                preparedStmt.setString   (6, doctorLastName);
                preparedStmt.setInt   (7, date);
                preparedStmt.setInt   (8, time);

                // execute the preparedstatement
                preparedStmt.execute();
                con.close();
                System.out.println("Appointment added");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                FileWriter fw = new FileWriter(new File("src/proiectPAO3/ServiceAudit.csv"), true);
                Date d = new Date();
                //getTime() returns current time in milliseconds
                long t = d.getTime();
                //Passed the milliseconds to constructor of Timestamp class
                Timestamp ts = new Timestamp(t);
                String currentThreadName = Thread.currentThread().getName();
                fw.write("addAppointment" + "," +  ts.toString() + currentThreadName + "\n");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static String showAppointments(String host, String username, String pass) {
            try {
                Connection con = DriverManager.getConnection(host, username, pass);
                Statement stat = con.createStatement();
                String sql = "select * from appointments";
                ResultSet rs = stat.executeQuery(sql);
                String p = "";
                while (rs.next()) {

                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    int age = rs.getInt("age");
                    String sex = rs.getString("sex");
                    String doctorFirstName = rs.getString("doctorFirstName");
                    String doctorLastName = rs.getString("doctorLastName");
                    int date = rs.getInt("date");
                    int time = rs.getInt("time");

                    p += firstName + " " + lastName + " " + age + " " + sex + " " + doctorLastName + " " + doctorFirstName + " " + date + " "
                            + time + "\n";
                }
                con.close();
                try {
                    FileWriter fw = new FileWriter(new File("src/proiectPAO3/ServiceAudit.csv"), true);
                    Date d = new Date();
                    //getTime() returns current time in milliseconds
                    long t = d.getTime();
                    //Passed the milliseconds to constructor of Timestamp class
                    Timestamp ts = new Timestamp(t);
                    String currentThreadName = Thread.currentThread().getName();
                    fw.write("showAppointments" + "," +  ts.toString() + currentThreadName + "\n");
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return p;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return null;
            }
        }

        public static void updateAppointment (String host, String username, String pass) {

            Connection con = null;
            try {
                con = DriverManager.getConnection(host, username, pass);
                con.setAutoCommit(false);
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter patient's last name");
                String lastName = scanner.nextLine();
                System.out.println("Enter patient's first name");
                String firstName = scanner.nextLine();
                System.out.println("Enter appointment's new date");
                int newDate = scanner.nextInt();
                System.out.println("Enter appointment's new time");
                int newTime = scanner.nextInt();

                // create the java mysql update preparedstatement
                String query = "update clinic.appointments set date = ?, time = ? where lastName = ? and firstName = ?";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setInt   (1, newDate);
                preparedStmt.setInt   (2, newTime);
                preparedStmt.setString(3, lastName);
                preparedStmt.setString(4, firstName);

                // execute the java preparedstatement
                preparedStmt.executeUpdate();
                con.commit();
                preparedStmt.close();
                con.close();
                System.out.println("Appointment updated");
                try {
                    FileWriter fw = new FileWriter(new File("src/proiectPAO3/ServiceAudit.csv"), true);
                    Date d = new Date();
                    //getTime() returns current time in milliseconds
                    long t = d.getTime();
                    //Passed the milliseconds to constructor of Timestamp class
                    Timestamp ts = new Timestamp(t);
                    String currentThreadName = Thread.currentThread().getName();
                    fw.write("changeAppointment" + "," +  ts.toString() + currentThreadName + "\n");
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        public static void deleteAppointment (String host, String username, String pass) {

            Connection con = null;
            try {
                con = DriverManager.getConnection(host, username, pass);
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter patient's last name");
                String lastName = scanner.nextLine();
                System.out.println("Enter patient's first name");
                String firstName = scanner.nextLine();

                // create the mysql delete statement.
                String query = "delete from appointments where lastName = ? and firstName = ?";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1, lastName);
                preparedStmt.setString(2, firstName);

                // execute the preparedstatement
                preparedStmt.execute();
                preparedStmt.close();
                con.close();
                try {
                    FileWriter fw = new FileWriter(new File("src/proiectPAO3/ServiceAudit.csv"), true);
                    Date d = new Date();
                    //getTime() returns current time in milliseconds
                    long t = d.getTime();
                    //Passed the milliseconds to constructor of Timestamp class
                    Timestamp ts = new Timestamp(t);
                    String currentThreadName = Thread.currentThread().getName();
                    fw.write("deleteAppointment" + "," +  ts.toString() + currentThreadName + "\n");
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
