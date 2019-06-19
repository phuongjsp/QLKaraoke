package levantuan.quanlykaraoke.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "chi_tiet_hoa_don")
@Data
public class ChiTietHoaDon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  columnDefinition = "int(11)")
    private long id;

    @Column(name = "so_luong_dich_vu", nullable = false, length = 32)
    private int  soLuongDichVu ;

    @Column(name = "hoa_don", nullable = false, length = 32)
    private Integer hoaDon;

    @ManyToOne
    @JoinColumn(name = "dich_vu", referencedColumnName = "id")
    private DichVu dichVu;



}
