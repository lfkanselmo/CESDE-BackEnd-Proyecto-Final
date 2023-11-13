package co.com.cesde.arkham.domain.service;

import co.com.cesde.arkham.domain.Appointment;
import co.com.cesde.arkham.domain.repository.AppointmentRepository;
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

    public Optional<Appointment> save(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public Optional<Appointment> getById(Integer id){
        return appointmentRepository.getByAppointmentId(id);
    }

    public Boolean delete(Integer id){
        return getById(id).map(appointment -> {
            appointmentRepository.delete(id);
            return true;
        }).orElse(false);
    }

    public Optional<List<Appointment>> getByAppointmentDate(LocalDate date){
        return appointmentRepository.getByAppointmentDate(date);
    }

    public Optional<List<Appointment>> getByPropertyId(Integer propertyId) {
        return appointmentRepository.getByPropertyId(propertyId);
    }

    public Optional<Page<Appointment>> getAll(Pageable pagination) {
        return appointmentRepository.getAll(pagination);
    }

    public Optional<Appointment> update(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
}
