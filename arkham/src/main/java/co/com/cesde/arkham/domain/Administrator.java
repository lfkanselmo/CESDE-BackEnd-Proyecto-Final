package co.com.cesde.arkham.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Administrator {
    private Long adminitratorId;
    private String administratorFirstName;
    private String administratorLastName;
    private String administratorPhone;
    private String administratorEmail;
    private Boolean active;
}
