package levantuan.quanlykaraoke.service;

import levantuan.quanlykaraoke.entities.VatTu;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VatTuService {

    Page<VatTu> getAllVatTu(Integer page, Integer size);

    VatTu updateVatTu(Long id, String ten);

    VatTu newVatTu(String vatTu);

    boolean xoaVatTu(Long id);

    List<VatTu> getAllVatTuByIdPhong(Long idPhong);
}
