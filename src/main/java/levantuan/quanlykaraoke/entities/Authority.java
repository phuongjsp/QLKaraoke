package levantuan.quanlykaraoke.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authority")
@Data
@NoArgsConstructor
public class Authority implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11)")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "authority")
    private String authority;

    public Authority(String username, String auth) {
        this.authority = auth;
        this.username = username;
    }
}
