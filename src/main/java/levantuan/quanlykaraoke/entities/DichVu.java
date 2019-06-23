package levantuan.quanlykaraoke.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dich_vu")
@Data
public class DichVu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  columnDefinition = "int(11)")
    private long id;

    @Column(name = "ten_dich_vu", length = 32)
    private String tenDichVu;

    @Column(name = "gia", nullable = false, length = 32)
    private int gia;

    @Column(name = "don_vi_do", nullable = false, length = 32)
    private String donViDo;

    @Column(name = "so_luong_con",  length = 32)
    private int soLuongCon;

}
