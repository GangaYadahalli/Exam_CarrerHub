package com.hexaware.hospitalmanagementsystem.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.hospitalmanagementsystem.util.DBConnection;
import com.hexaware.hosptialmanagementsystem.entity.Appointment;

public class HosptialDaoImpl implements IHospitalDaoImpl{
	private Connection conn;

	public HosptialDaoImpl() {

		conn = DBConnection.getDBConnection();

	}

	@Override
	public Appointment getAppointmentById(int appointmentId) {
		
		Appointment appointment = null;
        String query = "SELECT appointmentId, patientId, doctorId, appointmentDate, description FROM Appointment WHERE appointmentId = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, appointmentId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                
                int appointmentID=rs.getInt("appointmentId");
                int patientID=rs.getInt("patientId");
                  int doctorID=      rs.getInt("doctorId");
                   Date appDate=rs.getDate("appointmentDate");
                        String desp=rs.getString("description");
                
               
                        appointment = new Appointment(appointmentID,patientID ,doctorID ,appDate,desp );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointment; 
    }

	@Override
	public List<Appointment> getAppointmentsForPatient(int patientId) {
		 List<Appointment> appointments = new ArrayList<>();
	        String query = "SELECT appointmentId, patientId, doctorId, appointmentDate, description FROM Appointment WHERE patientId = ?";

	        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	            pstmt.setInt(1, patientId);
	            ResultSet rs = pstmt.executeQuery();

	            while (rs.next()) {
	                
	                Appointment appointment = new Appointment(
	                        rs.getInt("appointmentId"),
	                        rs.getInt("patientId"),
	                        rs.getInt("doctorId"),
	                        rs.getDate("appointmentDate"),
	                        rs.getString("description")
	                );
	                appointments.add(appointment);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }return appointments; 
	    }

	@Override
	public List<Appointment> getAppointmentsForDoctor(int doctorId) {
		
		List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT appointmentId, patientId, doctorId, appointmentDate, description FROM Appointment WHERE doctorId = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, doctorId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                
                Appointment appointment = new Appointment(
                        rs.getInt("appointmentId"),
                        rs.getInt("patientId"),
                        rs.getInt("doctorId"),
                        rs.getDate("appointmentDate"),
                        rs.getString("description")
                );
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return appointments; 
    }

	@Override
	public boolean scheduleAppointment(Appointment appointment) {
		String query = "INSERT INTO Appointment (appointmentId,patientId, doctorId, appointmentDate, description) VALUES (?,?, ?, ?, ?)";
        
        int count=0;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        	pstmt.setInt(1,appointment.getAppointmentId());
            pstmt.setInt(2, appointment.getPatientId());
            pstmt.setInt(3, appointment.getDoctorId());
            pstmt.setDate(4, appointment.getAppointmentDate());
            pstmt.setString(5, appointment.getDescription());

             count = pstmt.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean result=(count!=0);
        return result; 
    }

	@Override
	public boolean updateAppointment(Appointment appointment) {
		String query = "UPDATE Appointment SET patientId = ?, doctorId = ?, appointmentDate = ?, description = ? WHERE appointmentId = ?";
        
        int count=0;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, appointment.getPatientId());
            pstmt.setInt(2, appointment.getDoctorId());
            pstmt.setDate(3, appointment.getAppointmentDate());
            pstmt.setString(4, appointment.getDescription());
            pstmt.setInt(5, appointment.getAppointmentId()); 

            count = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean result=(count!=0);
        return result; 
    }

	@Override
	public boolean cancelAppointment(int appointmentId) {
		int count=0;
		 String query = "DELETE FROM Appointment WHERE appointmentId = ?";
	        
	        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	            pstmt.setInt(1, appointmentId); 

	            count = pstmt.executeUpdate();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        boolean result=(count!=0);
	        return result; 
	    
	
	}

	@Override
	public Appointment getAppointmentsForPatientDetails(int patientId) {
		String selectOne = "select * from Appointment where patientId = ?";
		PreparedStatement pstmt;

		Appointment app = null;
		try {
			pstmt = conn.prepareStatement(selectOne);

					pstmt.setInt(1, patientId);
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int appointmentId = rs.getInt("appointmentId");
				int PatientId=rs.getInt("patient iD");
				int doctorId=rs.getInt("doctorId");
				Date appointmentDate=rs.getDate("AppointmentDate");
				String description=rs.getString("Description");
				

				app = new Appointment(appointmentId,PatientId, doctorId, appointmentDate,description);

			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return app;
	}
		
	
}


