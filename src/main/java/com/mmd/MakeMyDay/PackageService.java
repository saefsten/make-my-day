package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackageService {

    @Autowired
    PackageRepository packageRepository;

    public List<Package> findAllPackages() {
        return (List<Package>)packageRepository.findAll();
    }
}
