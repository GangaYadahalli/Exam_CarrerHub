package com.hexaware.hosptialmanagementsystem.ui;
import com.hexaware.hospitalmanagementsystem.service.HospitalServiceImpl;
import com.hexaware.hospitalmanagementsystem.service.IHospitalServiceImpl;
import com.hexaware.hosptialmanagementsystem.entity.Appointment;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class MainModule {
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
      boolean flag = true;
		
		IHospitalServiceImpl service = new HospitalServiceImpl();
		
		while (flag) {

			System.out.println("*****Welcome to Hospital Mangement System*****");
			System.out.println("1.Get Appointment by appointementId");
			System.out.println("2. Appointment for patients based on patient id");
			System.out.println("3.Appointment for doctors by doctor id");
			System.out.println("4.Add into appointments ");
			System.out.println("5.Update appointments");
			System.out.println("6.Cancel Appointments");
			System.out.println("7. update by patient id");
			
			System.out.println("0.EXIT");

			int choice = scanner.nextInt();
			switch(choice)
			{
			case 1:
				System.out.println("Enter Appointmentid to Search ");

				int appointmentId =scanner.nextInt();
				
			 Appointment appointment =	service.getAppointmentById(appointmentId);
			
				
			System.out.println(appointment);
				break;
			case 2:
				System.out.println("Enter patient Id");
				int patientId=scanner.nextInt();
				List<Appointment> listPatient = service.getAppointmentsForPatient(patientId);

				for (Appointment appointment1 : listPatient) {
                                   
					System.out.println(appointment1);
				
				}
				break;
				
			case 3:
				System.out.println("Enter doctor Id");
				int doctorId=scanner.nextInt();
				List<Appointment> listDoctor = service.getAppointmentsForDoctor(doctorId);

				for (Appointment appointment1 : listDoctor) {
                                   

					System.out.println(appointment1);}
				break;
			case 4:
                Appointment  appointmentData=readAppointmentData();
				
				
				
				boolean isValid = HospitalServiceImpl.validateAppointment(appointmentData);

				if (isValid) {
					boolean success = service.scheduleAppointment(appointmentData);
					int count = success ? 1 : 0;
					if (count > 0) {

						System.out.println("Appointment added successfully..");
						//System.out.println(count);
					}
					else {
						System.err.println("Appointment Adding  Failed...");
					}
				} 
				else {
					System.err.println("Invalid Appointment Data");
				}
				break;
			case 5:
				Appointment update=updateAppointmentData();
				boolean isValid1 = HospitalServiceImpl.validateAppointment(update);
				if (isValid1) {
				boolean success = service.updateAppointment(update);
					int count=success?1:0;
					if (count> 0) {
						System.out.println("update successfully..");	
					}
					else {
						System.err.println("update  Failed...");
					}
				} else {
					System.err.println("Invalid  Data");
				}
				break;
			case 6:
				Appointment delete = deleteAppointmentData();
				boolean success1 = service.cancelAppointment(delete.getAppointmentId());
				int count = success1 ? 1 : 0;

				if (count > 0) {
				    System.out.println("Delete successfully..");
				} else {
				    System.err.println("Delete Failed...");
				}
             break;
			case 7:
				System.out.println("Enter patientId to Search");

				int patientId1 =scanner.nextInt();
				
			Appointment app =service.getAppointmentsForPatientDetails(patientId1);
			
			if(app != null)	
			System.out.println(app);
				break;
	
			case 0:
				flag =false;
				System.out.println("Log out");
				break;

			default:
				System.out.println("Invalid option");
			
			break;
	}

}
}
	public static Appointment readAppointmentData(){
		 Appointment appointment=new Appointment();	
	System.out.println("Enter AppointmentID=");
	appointment.setAppointmentId(scanner.nextInt());
	System.out.println("Enter  patientId=");
	appointment.setPatientId(scanner.nextInt());
	System.out.println("Enter  DoctorId=");
	appointment.setDoctorId(scanner.nextInt());
	System.out.println("Please enter the date in the format yyyy-MM-dd =");
	String inputDate = scanner.next(); 
   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
   try {
       java.util.Date parsedDate = formatter.parse(inputDate);
       appointment.setAppointmentDate(new Date(parsedDate.getTime()));
       System.out.println("Enter date: " + appointment.getAppointmentDate());
   } 
   catch (ParseException e) {
       System.err.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
       return null; 
   }
	System.out.println("Enter Description=");
	appointment.setDescription(scanner.next());
		
	return appointment;
}

public static Appointment updateAppointmentData()
{
Appointment appointment=new Appointment();	
		
		System.out.println("Enter  patientId=");
		appointment.setPatientId(scanner.nextInt());
		System.out.println("Enter  DoctorId=");
		appointment.setDoctorId(scanner.nextInt());
		System.out.println("Enter date =");
		String inputDate = scanner.next(); 
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            java.util.Date parsedDate = formatter.parse(inputDate);
            
            appointment.setAppointmentDate(new Date(parsedDate.getTime()));
        }
        catch (ParseException e) {
            System.err.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
            return null; 
        }
		System.out.println("Enter Description=");
		appointment.setDescription(scanner.next());
              System.out.println("Enter AppointmentID=");
		appointment.setAppointmentId(scanner.nextInt());

return appointment;
}
public static Appointment deleteAppointmentData()
{
Appointment appointment=new Appointment();	
		System.out.println("Enter AppointmentID=");
		appointment.setAppointmentId(scanner.nextInt());
return appointment;
}
}
