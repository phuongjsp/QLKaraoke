package levantuan.quanlykaraoke.entities;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "chi_tiet_vat_tu")
@Data
public class ChiTietVatTu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  columnDefinition = "int(11)")
    private long id;

    @Column(name = "so_luong_vat_tu",  nullable = false, length = 32)
    private int soLuongVatTu;

    @ManyToOne
    @JoinColumn(name = "phong", referencedColumnName = "id")
    private Phong phong;

    @ManyToOne
    @JoinColumn(name = "vat_tu", referencedColumnName = "id")
    private VatTu vatTu;



}
