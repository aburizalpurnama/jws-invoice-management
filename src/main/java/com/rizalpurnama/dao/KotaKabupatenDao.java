package com.rizalpurnama.dao;

import com.rizalpurnama.entity.KotaKabupaten;
import com.rizalpurnama.entity.Provinsi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Dictionary;

public interface KotaKabupatenDao extends PagingAndSortingRepository<KotaKabupaten, String> {

    Iterable<KotaKabupaten> findByProvinsi(Provinsi provinsi);
}
