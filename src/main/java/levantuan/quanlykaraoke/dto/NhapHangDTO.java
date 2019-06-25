package levantuan.quanlykaraoke.dto;

import lombok.Data;

import java.util.List;

@Data
public class NhapHangDTO {
    private String donViDoiTac;

    private String diaChiDoiTac;

    private String nguoiDaiDien;

    private String thongTinNDD;

    private List<DichVuDTO> dichVus;

}
