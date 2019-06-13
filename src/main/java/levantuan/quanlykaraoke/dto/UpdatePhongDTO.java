package levantuan.quanlykaraoke.dto;

import levantuan.quanlykaraoke.entities.VatTu;
import lombok.Data;

@Data
public class UpdatePhongDTO {
    private Integer sl;
    private VatTu vattu;
}
