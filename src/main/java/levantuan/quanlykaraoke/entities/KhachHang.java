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
    private int vipCount;
    /**
     *
     * 0 -- 100 point width 200k/point
     * Bronze 1 - 9  9 point :  200k -> 1m99 vnd  :           10% service 20m   book room
     * Silver 10 - 29 : 19 pint : 2m -> 5m99 vnd  : 10% room 15% service 50m   book room
     * Gold  30 - 59 :  29 point : 6m -> 11m99    : 15% room  20% service 1h30  book room
     * Platinum  60 - 99 : 39 point : 12m -> 19m99: 20% room  25% service 2h    book room
     * Diamond 100+ : 200m ++                     : 25% room  30% service 2h + (point - 100) minutes, max 3h => max =  160 point
     *
     * cancel room : vipcount -= 10*vipcount/100
     * vip count = 85 --- vipcount =
     * */

}
