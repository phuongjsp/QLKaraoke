package levantuan.quanlykaraoke.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "phong")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Phong implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  columnDefinition = "int(11)")
    private long id;

    @Column(name = "ma_phong", unique = true, nullable = false, length = 32)
    private String maPhong;

    @Column(name = "tinh_trang_phong",  nullable = false, length = 32)
    private int tinhTrangPhong;
//     1.đang trống
////            2. đã được đặt
////    3. đang dọn
    /// 4.Khách đã vào phòng


    @ManyToOne
    @JoinColumn(name = "loai_phong", referencedColumnName = "id")
    private LoaiPhong loaiPhong;

}
