package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageService {

    @Autowired
    PackageRepository packageRepository;

    public List<Package> findAllPackages() {
        return (List<Package>)packageRepository.findAll();
    }

    public Package findPackageById(Long id) {
        return packageRepository.findById(id).get();
    }
}
