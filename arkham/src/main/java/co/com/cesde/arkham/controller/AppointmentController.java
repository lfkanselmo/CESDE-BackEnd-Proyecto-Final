package co.com.cesde.arkham.controller;

import co.com.cesde.arkham.dto.appointment.AppointmentDateTimeRecord;
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
import co.com.cesde.arkham.service.pdfexport.ExportService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = "*")
public class AppointmentController {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private ExportService exportService;

    @PostMapping("/save")
    @Transactional
    public ResponseEntity<AppointmentListRecord> save(@RequestBody @Valid AppointmentRegisterRecord appointmentRegisterRecord,
                                                      UriComponentsBuilder uriComponentsBuilder) {

        Appointment appointment = appointmentRepository
                .getAppointmentByDateAndStartTime(
                        appointmentRegisterRecord.date(),
                        appointmentRegisterRecord.startTime()
                );

        if (appointment != null) {
            throw new ValidationException("Ya existe una cita a esta hora en esta fecha");
        }

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
    @Transactional
    public ResponseEntity<AppointmentListRecord> update(@RequestBody @Valid AppointmentUpdateRecord appointmentUpdateRecord) {
        Appointment appointment = appointmentRepository.getReferenceById(appointmentUpdateRecord.appointmentId());
        if (appointment != null) {
            Appointment appointmentByDateAndStartTime = appointmentRepository.getAppointmentByDateAndStartTime(appointmentUpdateRecord.date(), appointmentUpdateRecord.startTime());
            if (appointmentByDateAndStartTime == null) {
                if (appointmentUpdateRecord.date() != null) {
                    appointment.setDate(appointmentUpdateRecord.date());
                }

                if (appointmentUpdateRecord.startTime() != null) {
                    appointment.setStartTime(appointmentUpdateRecord.startTime());
                    appointment.setEndTime(appointmentUpdateRecord.startTime().plusHours(1));
                }

                Appointment updated = appointmentRepository.save(appointment);

                return ResponseEntity.ok(new AppointmentListRecord(updated));
            } else {
                throw new ValidationException("Esta hora y fecha no están disponibles");
            }
        }else {

            throw new ValidationException("No existe la cita que intenta modificar");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Page<AppointmentListRecord>> getAll(@PageableDefault() Pageable pagination) {
        Page<Appointment> all = appointmentRepository.findAll(pagination);
        Page<AppointmentListRecord> allPage = all.map(AppointmentListRecord::new);
        return ResponseEntity.ok(allPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentListRecord> getById(@PathVariable("id") Long appointmentId) {
        return ResponseEntity.ok(new AppointmentListRecord(appointmentRepository.getReferenceById(appointmentId)));
    }

    @DeleteMapping("delete/{id}")
    @Transactional
    public ResponseEntity<AppointmentListRecord> delete(@PathVariable("id") Long id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/date")
    public ResponseEntity<List<AppointmentListRecord>> getByDate(@RequestBody @Valid AppointmentDateTimeRecord appointmentDate) {
        LocalDate date = LocalDate.parse(appointmentDate.date());
        List<Appointment> appointments = appointmentRepository.getByDate(date);
        return getReturnsToListRecord(appointments);
    }

    @GetMapping("/property/{id}")
    public ResponseEntity<List<AppointmentListRecord>> getByProperty(@PathVariable("id") Long propertyId) {
        List<Appointment> appointments = appointmentRepository.getByPropertyId(propertyId);
        return getReturnsToListRecord(appointments);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<AppointmentListRecord>> getByClientId(@PathVariable("id") Long clientId) {
        LocalDate today = LocalDate.now();
        List<Appointment> appointments = appointmentRepository.getAppointmentByClientIdAndDate(clientId, today);
        return getReturnsToListRecord(appointments);
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> exportAppointment(
            @RequestParam
            Long appoinmentId,
            HttpServletResponse response) {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:h:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        return exportService.exportAppointment(appoinmentId, response);
    }


    private ResponseEntity<List<AppointmentListRecord>> getReturnsToListRecord(List<Appointment> appointments) {
        if (!appointments.isEmpty()) {
            List<AppointmentListRecord> appointmentRecordList = appointments
                    .stream()
                    .map(AppointmentListRecord::new)
                    .toList();
            return ResponseEntity.ok(appointmentRecordList);
        } else {
            throw new ValidationException("No encontrado");
        }
    }
}
