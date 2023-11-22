package co.com.cesde.arkham.controller;

import co.com.cesde.arkham.dto.appointment.AppointmentListRecord;
import co.com.cesde.arkham.dto.appointment.AppointmentRegisterRecord;
import co.com.cesde.arkham.dto.appointment.AppointmentUpdateRecord;
import co.com.cesde.arkham.entity.Appointment;
import co.com.cesde.arkham.entity.Client;
import co.com.cesde.arkham.entity.Property;
import co.com.cesde.arkham.entity.User;
import co.com.cesde.arkham.repository.AppointmentRepository;
import co.com.cesde.arkham.repository.ClientRepository;
import co.com.cesde.arkham.repository.PropertyRepository;
import co.com.cesde.arkham.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PropertyRepository propertyRepository;

    @PostMapping("/save")
    public ResponseEntity<AppointmentListRecord> save(@RequestBody @Valid AppointmentRegisterRecord appointmentRegisterRecord,
                                                      UriComponentsBuilder uriComponentsBuilder) {
        Appointment saved = appointmentRepository.save(new Appointment(appointmentRegisterRecord));

        Property propertySaved = propertyRepository.getReferenceById(saved.getPropertyId());
        Client clientSaved = clientRepository.getReferenceById(saved.getClientId());
        User userSaved = userRepository.getReferenceById(saved.getUserId());
        saved.setUser(userSaved);
        saved.setProperty(propertySaved);
        saved.setClient(clientSaved);

        URI url = uriComponentsBuilder.path("/appointment/{id}").buildAndExpand(saved.getAppointmentId()).toUri();
        return ResponseEntity.created(url).body(new AppointmentListRecord(saved));
    }

    @PutMapping("/update")
    public ResponseEntity<AppointmentListRecord> update(@RequestBody @Valid AppointmentUpdateRecord appointmentUpdateRecord) {
        Appointment appointment = appointmentRepository.getReferenceById(appointmentUpdateRecord.appointmentId());
        if (appointmentUpdateRecord.date() != null) {
            appointment.setDate(appointmentUpdateRecord.date());
        }

        if (appointmentUpdateRecord.startTime() != null) {
            appointment.setStartTime(appointmentUpdateRecord.startTime());
            appointment.setEndTime(appointmentUpdateRecord.startTime().plusHours(1));
        }

        Appointment updated = appointmentRepository.save(appointment);

        return ResponseEntity.ok(new AppointmentListRecord(updated));
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentListRecord>> getAll(@PageableDefault() Pageable pagination) {
        Page<Appointment> all = appointmentRepository.findAll(pagination);
        Page<AppointmentListRecord> allPage = all.map(AppointmentListRecord::new);
        return ResponseEntity.ok(allPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentListRecord> getById(@PathVariable("id") Long appointmentId) {
        return ResponseEntity.ok(new AppointmentListRecord(appointmentRepository.getReferenceById(appointmentId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppointmentListRecord> delete(@PathVariable("id") Long id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/date")
    public ResponseEntity<List<AppointmentListRecord>> getByDate(@PathVariable("date") LocalDate date) {
        List<Appointment> appointments = appointmentRepository.getByDate(date);
        return getReturnsToListRecord(appointments);
    }

    @GetMapping("/property/{id}")
    public ResponseEntity<List<AppointmentListRecord>> getByProperty(@PathVariable("id") Long propertyId) {
        List<Appointment> appointments = appointmentRepository.getByPropertyId(propertyId);
        return getReturnsToListRecord(appointments);
    }


    private ResponseEntity<List<AppointmentListRecord>> getReturnsToListRecord(List<Appointment> appointments) {
        if (!appointments.isEmpty()) {
            List<AppointmentListRecord> appointmentRecordList = appointments
                    .stream()
                    .map(AppointmentListRecord::new)
                    .toList();
            return ResponseEntity.ok(appointmentRecordList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
