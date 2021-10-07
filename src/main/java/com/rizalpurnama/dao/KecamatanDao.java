package com.rizalpurnama.dao;

import com.rizalpurnama.entity.Kecamatan;
import com.rizalpurnama.entity.KotaKabupaten;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface KecamatanDao extends PagingAndSortingRepository<Kecamatan, String> {
    Iterable<Kecamatan> findByKotaKabupaten(KotaKabupaten kokab);
}
