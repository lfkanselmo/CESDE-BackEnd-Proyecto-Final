package co.com.cesde.arkham.service.pdfexport;

import co.com.cesde.arkham.entity.Appointment;
import co.com.cesde.arkham.entity.Client;
import co.com.cesde.arkham.entity.Property;
import co.com.cesde.arkham.entity.User;
import co.com.cesde.arkham.repository.AppointmentRepository;
import co.com.cesde.arkham.repository.ClientRepository;
import co.com.cesde.arkham.repository.PropertyRepository;
import co.com.cesde.arkham.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@Service
public class ExportService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PropertyRepository propertyRepository;

    @NotNull
    public ResponseEntity<Resource> exportAppointment(Long appoinmentId, HttpServletResponse response) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appoinmentId);
        if (appointmentOptional.isPresent()) {
            try {
                final Appointment appoinment = appointmentOptional.get();
                final File file = ResourceUtils.getFile("classpath:report.jrxml");
                final JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());

                final User user = userRepository.getReferenceById(appoinment.getUserId());
                final Property property = propertyRepository.getReferenceById(appoinment.getPropertyId());
                final Client client = clientRepository.getReferenceById(appoinment.getClientId());

                final HashMap<String, Object> parameters = new HashMap<>();
                final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                final DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
                parameters.put("appointmentId", appoinment.getAppointmentId());
                parameters.put("date", (formatterDate.format(appoinment.getDate())) + " " + (formatterTime.format(appoinment.getStartTime())));
                parameters.put("propertyId", property.getPropertyId());
                parameters.put("propertyType", property.getPropertyType().name());
                parameters.put("address", property.getAddress());
                parameters.put("district", property.getDistrict());
                parameters.put("city", property.getCity());
                parameters.put("clientFirstName", client.getClientFirstName());
                parameters.put("clientLastName", client.getClientLastName());
                parameters.put("clientPhone", client.getClientPhone());
                parameters.put("userFirstName", user.getUserFirstName());
                parameters.put("userLastName", user.getUserLastName());
                parameters.put("userPhone", user.getUserPhone());
                parameters.put("imageDir", "classpath:static/images/");

                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
                byte[] reportByte = JasperExportManager.exportReportToPdf(jasperPrint);

                String sdf = (new SimpleDateFormat("dd/MM/yyyy")).format(new Date());
                StringBuilder stringBuilder = new StringBuilder().append("ReportPDF: ");
                ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                        .filename(stringBuilder
                                .append(appoinment.getAppointmentId())
                                .append("generateDate:")
                                .append(sdf)
                                .append(".pdf")
                                .toString())
                        .build();

                HttpHeaders headers = new HttpHeaders();
                headers.setContentDisposition(contentDisposition);

                JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

                return ResponseEntity.ok().contentLength((long) reportByte.length)
                        .contentType(MediaType.APPLICATION_PDF)
                        .headers(headers).body(new ByteArrayResource(reportByte));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return ResponseEntity.noContent().build();
        }

        return null;
    }
}
