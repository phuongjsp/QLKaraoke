package levantuan.quanlykaraoke.entities;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vip")
@Data
public class Vip implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  columnDefinition = "int(11)")
    private long id;

    @Column(name = "ten_loai_vip",  nullable = false, length = 32)
    private String tenLoaiVip;

    @Column(name = "muc_tich_luy",  nullable = false, length = 32)
    private String mucTichLuy;

    @Column(name = "hoa_don_giam",  nullable = false, length = 32)
    private String hoaDonGiam;

}
