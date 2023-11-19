package co.com.cesde.arkham.domain.service;

import co.com.cesde.arkham.domain.Appointment;
import co.com.cesde.arkham.domain.repository.AppointmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional
    public Appointment save(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public Optional<Appointment> getById(Long appointmentId){
        return appointmentRepository.getByAppointmentId(appointmentId);
    }

    @Transactional
    public Boolean delete(Long appointmentId){
        return getById(appointmentId).map(appointment -> {
            appointmentRepository.delete(appointmentId);
            return true;
        }).orElse(false);
    }

    public List<Appointment> getByAppointmentDate(LocalDate date){
        return appointmentRepository.getByAppointmentDate(date);
    }

    public List<Appointment> getByPropertyId(Long propertyId) {
        return appointmentRepository.getByPropertyId(propertyId);
    }

    public Page<Appointment> getAll(Pageable pagination) {
        return appointmentRepository.getAll(pagination);
    }

    @Transactional
    public Appointment update(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
}
