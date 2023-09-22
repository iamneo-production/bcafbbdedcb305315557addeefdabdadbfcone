package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Medicine;
import com.examly.springapp.service.MedicineService;


@RestController
@RequestMapping("/medicine")
public class MedicineController {

	@Autowired
	private MedicineService medicineService;

	@PostMapping
	public ResponseEntity<Boolean> save(@RequestBody Medicine medicine) {

		boolean s = medicineService.saveMedicine(medicine);
		if (s) {
			return new ResponseEntity<>(s, HttpStatus.OK);
		}
		return new ResponseEntity<>(s, HttpStatus.ALREADY_REPORTED);
	}


	@GetMapping
	public ResponseEntity<List<Medicine>> getAll() {

		List<Medicine> medicines = medicineService.getAllMedicine();
		return new ResponseEntity<>(medicines, HttpStatus.OK);
	}

	@GetMapping("/{medicineId}")
	public ResponseEntity<Medicine> getById(@PathVariable int medicineId) {

		Medicine medicine = medicineService.getMedicineById(medicineId);
		return new ResponseEntity<>(medicine, HttpStatus.OK);
	}

	@GetMapping("/brand/{medicineBrand}")
    public ResponseEntity<List<Medicine>> findByBrand(@PathVariable String medicineBrand) {
		List<Medicine> medicine = medicineService.findByBrand(medicineBrand);
        if (medicine == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(medicine, HttpStatus.OK);
    }
	
	@GetMapping("/name/{medicineName}")
    public ResponseEntity<Medicine> findByName(@PathVariable String medicineName) {
        Medicine medicine = medicineService.findByName(medicineName);
        if (medicine == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(medicine, HttpStatus.OK);
    }
	
	@GetMapping("/{medicineName}/{medicineBrand}")
    public ResponseEntity<Medicine> findByNameAndBrand(
            @PathVariable String medicineName,
            @PathVariable String medicineBrand) {
        Medicine medicine = medicineService.findByNameAndBrand(medicineName, medicineBrand);
        if (medicine == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(medicine, HttpStatus.OK);
    }
}
