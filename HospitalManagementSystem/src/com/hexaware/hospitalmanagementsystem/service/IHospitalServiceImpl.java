package com.hexaware.hospitalmanagementsystem.service;

import java.util.List;

import com.hexaware.hospitalmanagementsystem.exception.PatientNumberNotFoundException;
import com.hexaware.hosptialmanagementsystem.entity.Appointment;

public interface IHospitalServiceImpl {
	Appointment getAppointmentById(int appointmentId);
	List<Appointment> getAppointmentsForPatient(int patientId);
	List<Appointment> getAppointmentsForDoctor(int doctorId);
	boolean scheduleAppointment(Appointment appointment);
	boolean updateAppointment(Appointment appointment);
	
	boolean cancelAppointment(int appointmentId);
	Appointment getAppointmentsForPatientDetails(int patientId) throws PatientNumberNotFoundException;

}
