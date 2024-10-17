package com.hexaware.hospitalmanagementsystem.service;

import java.util.List;

import com.hexaware.hospitalmanagementsystem.dao.HosptialDaoImpl;
import com.hexaware.hospitalmanagementsystem.dao.IHospitalDaoImpl;
import com.hexaware.hospitalmanagementsystem.exception.PatientNumberNotFoundException;
import com.hexaware.hosptialmanagementsystem.entity.Appointment;

public class HospitalServiceImpl implements IHospitalServiceImpl {

	private IHospitalDaoImpl dao;

	public  HospitalServiceImpl(){

		dao = new HosptialDaoImpl();

	}

	@Override
	public Appointment getAppointmentById(int appointmentId) {
		
		return dao.getAppointmentById(appointmentId);
	}

	@Override
	public List<Appointment> getAppointmentsForPatient(int patientId) {
		return dao.getAppointmentsForPatient(patientId);
	}

	@Override
	public List<Appointment> getAppointmentsForDoctor(int doctorId) {
		
		return dao.getAppointmentsForDoctor(doctorId);
	}

	@Override
	public boolean scheduleAppointment(Appointment appointment) {
		
		return dao.scheduleAppointment(appointment);
	}
	
	
	public static boolean validateAppointment(Appointment appointment)
	{
		boolean flag=false;

		if(appointment.getAppointmentId()>1000 && appointment.getPatientId()>0&& appointment.getDoctorId()>100) {
			
			flag=true;
		}
		return flag;
	}

	@Override
	public boolean updateAppointment(Appointment appointment) {
		
		return dao.updateAppointment(appointment);
	}

	@Override
	public boolean cancelAppointment(int appointmentId) {
		
		return dao.cancelAppointment(appointmentId);
	}

	@Override
	public Appointment getAppointmentsForPatientDetails(int patientId) throws PatientNumberNotFoundException {
		Appointment appointment=dao.getAppointmentsForPatientDetails(patientId);
		if (appointment == null) {

			try {

				throw new PatientNumberNotFoundException();

			} catch (Exception e) {

				System.err.println("appointment Not Found patientid " + patientId);
			}

		}
		return appointment;

	
	}

}

