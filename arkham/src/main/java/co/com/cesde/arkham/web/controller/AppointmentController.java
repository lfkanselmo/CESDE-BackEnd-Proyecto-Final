package co.com.cesde.arkham.web.controller;

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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody @Valid AppointmentRegisterRecord appointmentRegisterRecord) {
        if (appointmentService.save(new Appointment(appointmentRegisterRecord)).isPresent()) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody @Valid AppointmentUpdateRecord appointmentUpdateRecord) {
        Optional<Appointment> appointmentOptional = appointmentService.getById(appointmentUpdateRecord.clientId());
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            if (appointmentUpdateRecord.appointmentDate() != null) {
                appointment.setAppointmentDate(appointmentUpdateRecord.appointmentDate());
            }

            if (appointmentUpdateRecord.startTime() != null) {
                appointment.setStartTime(appointmentUpdateRecord.startTime());
                appointment.setEndTime(appointmentUpdateRecord.startTime().plusHours(1));
            }

            if (appointmentService.update(appointment).isPresent()) {
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Appointment>> getAll(@PageableDefault(size = 10) Pageable pagination) {
        return appointmentService.getAll(pagination)
                .map(appointments -> new ResponseEntity<>(appointments, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentListRecord> getById(@PathVariable("id") Integer id) {
        return appointmentService.getById(id)
                .map(appointment -> new ResponseEntity<>(new AppointmentListRecord(appointment), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        if (appointmentService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/date")
    public ResponseEntity<List<AppointmentListRecord>> getByDate(@PathVariable("date") LocalDate date) {
        Optional<List<Appointment>> appointmentsOptional = appointmentService.getByAppointmentDate(date);
        return getReturnsToListRecord(appointmentsOptional);
    }

    @GetMapping("/property/{id}")
    public ResponseEntity<List<AppointmentListRecord>> getByProperty(@PathVariable("id") Integer propertyId) {
        Optional<List<Appointment>> appointmentsOptional = appointmentService.getByPropertyId(propertyId);
        return getReturnsToListRecord(appointmentsOptional);
    }


    private ResponseEntity<List<AppointmentListRecord>> getReturnsToListRecord(Optional<List<Appointment>> appointmentsOptional) {
        if (appointmentsOptional.isPresent()) {
            List<Appointment> appointmentList = appointmentsOptional.get();
            List<AppointmentListRecord> appointmentRecordList = appointmentList
                    .stream()
                    .map(AppointmentListRecord::new)
                    .toList();
            return new ResponseEntity<>(appointmentRecordList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
