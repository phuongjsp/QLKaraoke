package levantuan.quanlykaraoke.service;

import levantuan.quanlykaraoke.entities.Vip;

import java.util.List;

public interface VipService {
    List<Vip> getAllVip();

    Vip updateVip(Vip vip);

    Vip newVip(Vip vatTu);

    boolean xoaVip(Long id);
}
