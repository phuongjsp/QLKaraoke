package levantuan.quanlykaraoke.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "loai_phong")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class LoaiPhong implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  columnDefinition = "int(11)")
    private long id;
    @Column(name = "loai_phong", nullable = false, length = 32)
    private String loaiPhong;

    @Column(name = "tien_phong_tren_gio")
    private int tienPhongTrenGio;
}

