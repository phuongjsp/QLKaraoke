package levantuan.quanlykaraoke.entities;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tich_luy_vip")
@Data
public class TichLuyVip implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11)")
    private long id;

    @ManyToOne
    @JoinColumn(name = "ma_khach_hang", referencedColumnName = "id")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "tien_tich_luy", referencedColumnName = "id")
    private Vip vip;
}


