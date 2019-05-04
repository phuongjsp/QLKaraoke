package doan.tuan.quanlykaraoke.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "persistent_logins")
public class Persistent_logins {
    @Id
    @Column(name = "series ")
    private String series;
    @Column(name = "username")
    private String username;
    @Column(name = "token ")
    private String token;
    @Column(name = "last_used ")
    private Date last_used;
}
