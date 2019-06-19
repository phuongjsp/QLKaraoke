package levantuan.quanlykaraoke.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "khach_hang")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class KhachHang implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  columnDefinition = "int(11)")
    private long id;

    @Column(name = "ten_khach_hang",  nullable = false, length = 32)
    private String tenKhachHang;

    @Column(name = "ma_khach_hang", unique = true, length = 32)
        private String maKhachhang;

    @Column(name = "so_dien_thoai", nullable = false, length = 32)
    private int soDienThoai;

    @Column(name = "gioi_tinh", nullable = false,  columnDefinition = "bit default 0")
    private boolean gioiTinh;

    @Column(name = "cmnd", nullable = false, length = 20)
    private String cmnd;

    @Column(name = "vip_count")
    private int vipCount; //

}
