package ai.openfabric.api.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity()
public class Worker extends Datable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "of-uuid")
    @GenericGenerator(name = "of-uuid", strategy = "ai.openfabric.api.model.IDGenerator")
    @Getter
    @Setter
    public String id;

    public String name;

    public Integer port;

    public String status;

    public String image;

    public Date created;

    // Getters
    public String getName() {
        return name;
    }

    public int getPort() {
        return port;
    }

    public String getStatus() {
        return status;
    }
    public String getImage() {
        return image;
    }

    public Date getCreated() {
        return created;
    }


    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCreated(Date created) {
        this.created =created;
    }

    public String getDatasourceUrl() {

        return null;
    }
}
