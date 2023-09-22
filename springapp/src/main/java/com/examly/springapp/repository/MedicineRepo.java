package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Medicine;

@Repository
public interface MedicineRepo extends JpaRepository<Medicine, Integer> {
	List<Medicine> findByMedicineBrand(String medicineBrand);
    Medicine findByMedicineName(String medicineName);
    Medicine findByMedicineNameAndMedicineBrand(String medicineName, String medicineBrand);
}
