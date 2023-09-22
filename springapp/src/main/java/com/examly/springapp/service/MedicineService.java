package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Medicine;
import com.examly.springapp.repository.MedicineRepo;


@Service
public class MedicineService{

	@Autowired
	private MedicineRepo medicineRepo;

	public boolean saveMedicine(Medicine medicine) {
		return medicineRepo.save(medicine) != null ? true : false;
	}

	public List<Medicine> getAllMedicine() {

		List<Medicine> list = medicineRepo.findAll();
		return list;
	}

	public Medicine getMedicineById(int id) {

		if (medicineRepo.existsById(id)) {
			Medicine t = medicineRepo.findById(id).get();
			return t;
		}

		return null;
	}
	
	public List<Medicine> findByBrand(String brand) {
        return medicineRepo.findByMedicineBrand(brand);
    }

    public Medicine findByName(String name) {
        return medicineRepo.findByMedicineName(name);
    }

    public Medicine findByNameAndBrand(String name, String brand) {
        return medicineRepo.findByMedicineNameAndMedicineBrand(name, brand);
    }

}
