package co.com.cesde.arkham.web.controller;

import co.com.cesde.arkham.domain.Appointment;
import co.com.cesde.arkham.domain.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/save")
    public ResponseEntity<Appointment> save (@RequestBody Appointment appointment){
        return appointmentService.save(appointment)
                .map(appointmentOptional -> new ResponseEntity<>(appointmentOptional, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    private ResponseEntity<Appointment> getById(@PathVariable("id") Integer id){
        return appointmentService.getById(id)
                .map(appointment -> new ResponseEntity<>(appointment, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        if(appointmentService.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/date")
    public ResponseEntity<List<Appointment>> getByDate(LocalDate date){
        return appointmentService.getByAppointmentDate(date)
                .map(appointments -> new ResponseEntity(appointments,HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
}
