package com.rizalpurnama.controller;

import com.rizalpurnama.dao.KecamatanDao;
import com.rizalpurnama.dao.KelurahanDao;
import com.rizalpurnama.dao.KotaKabupatenDao;
import com.rizalpurnama.dao.ProvinsiDao;
import com.rizalpurnama.entity.Kecamatan;
import com.rizalpurnama.entity.Kelurahan;
import com.rizalpurnama.entity.KotaKabupaten;
import com.rizalpurnama.entity.Provinsi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller @RequestMapping("/merchant")
public class MerchantController {

    @Autowired private ProvinsiDao provinsiDao;
    @Autowired private KotaKabupatenDao kotaKabupatenDao;
    @Autowired private KecamatanDao kecamatanDao;
    @Autowired private KelurahanDao kelurahanDao;

    @GetMapping("/info/view")
    public void displayMerchatInfo(){

    }

    @ModelAttribute("provinsiList")
    public Iterable<Provinsi> provinsiList(){
        return provinsiDao.findAll();
    }

    @GetMapping("/info/form")
    public ModelMap displyaMerchatInfoForm(
            @RequestParam(value = "provinsi", required = false) Provinsi provinsi,
            @RequestParam(value = "kotaKabupaten", required = false) KotaKabupaten kotaKabupaten,
            @RequestParam(value = "kecamatan", required = false)Kecamatan kecamatan,
            @RequestParam(value = "kelurahan", required = false)Kelurahan kelurahan){
        ModelMap mm = new ModelMap();

        if (provinsi != null){
            mm.addAttribute(provinsi);
            mm.addAttribute(kotaKabupatenDao.findByProvinsi(provinsi));
        }

        if (kotaKabupaten != null){
            mm.addAttribute(kotaKabupaten);
            mm.addAttribute(kecamatanDao.findByKotaKabupaten(kotaKabupaten));
        }

        if (kecamatan != null){
            mm.addAttribute(kecamatan);
            mm.addAttribute(kelurahanDao.findByKecamatan(kecamatan));
        }

        if (kelurahan != null){
            mm.addAttribute(kelurahan);
        }

        return mm;
    }

    @PostMapping("/info/form")
    public String proccessEditMerchantInfo(){
        return "redirect:view";
    }
}
