package co.com.cesde.arkham.domain.controller;

import co.com.cesde.arkham.domain.Appointment;
import co.com.cesde.arkham.domain.dto.appointment.AppointmentListRecord;
import co.com.cesde.arkham.domain.dto.appointment.AppointmentRegisterRecord;
import co.com.cesde.arkham.domain.dto.appointment.AppointmentUpdateRecord;
import co.com.cesde.arkham.domain.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/save")
    public ResponseEntity<AppointmentListRecord> save(@RequestBody @Valid AppointmentRegisterRecord appointmentRegisterRecord,
                                                      UriComponentsBuilder uriComponentsBuilder) {
        Appointment saved = appointmentService.save(new Appointment(appointmentRegisterRecord));
        URI url = uriComponentsBuilder.path("/appointment/{id}").buildAndExpand(saved.getAppointmentId()).toUri();
        return ResponseEntity.created(url).body(new AppointmentListRecord(saved));
    }

    @PutMapping("/update")
    public ResponseEntity<AppointmentListRecord> update(@RequestBody @Valid AppointmentUpdateRecord appointmentUpdateRecord) {
        Optional<Appointment> appointmentOptional = appointmentService.getById(appointmentUpdateRecord.appointmentId());
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            if (appointmentUpdateRecord.date() != null) {
                appointment.setAppointmentDate(appointmentUpdateRecord.date());
            }

            if (appointmentUpdateRecord.startTime() != null) {
                appointment.setStartTime(appointmentUpdateRecord.startTime());
                appointment.setEndTime(appointmentUpdateRecord.startTime().plusHours(1));
            }

            Appointment updated = appointmentService.update(appointment);

            return ResponseEntity.ok(new AppointmentListRecord(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentListRecord>> getAll(@PageableDefault() Pageable pagination) {
        Page<Appointment> all = appointmentService.getAll(pagination);
        Page<AppointmentListRecord> allPage = all.map(AppointmentListRecord::new);
        return ResponseEntity.ok(allPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentListRecord> getById(@PathVariable("id") Long appointmentId) {
        return appointmentService.getById(appointmentId)
                .map(appointment -> ResponseEntity.ok(new AppointmentListRecord(appointment)))
                .orElse(ResponseEntity.noContent().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppointmentListRecord> delete(@PathVariable("id") Long id) {
        if (appointmentService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/date")
    public ResponseEntity<List<AppointmentListRecord>> getByDate(@PathVariable("date") LocalDate date) {
        List<Appointment> appointments = appointmentService.getByAppointmentDate(date);
        return getReturnsToListRecord(appointments);
    }

    @GetMapping("/property/{id}")
    public ResponseEntity<List<AppointmentListRecord>> getByProperty(@PathVariable("id") Long propertyId) {
        List<Appointment> appointments = appointmentService.getByPropertyId(propertyId);
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
