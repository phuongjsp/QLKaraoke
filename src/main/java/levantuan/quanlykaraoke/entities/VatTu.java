package levantuan.quanlykaraoke.entities;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vat_tu")
@Data
public class VatTu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  columnDefinition = "int(11)")
    private long id;

    @Column(name = "ten_vat_tu",  nullable = false, length = 32)
    private String tenVatTu;

    @Column(name = "ma_vat_tu", unique= true, length = 32)
    private String maVatTu;

    @Column(name = "so_luong_con", length = 11)
    private int soLuongVatTuCon;

}
