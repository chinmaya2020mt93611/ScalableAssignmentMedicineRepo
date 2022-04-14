package com.scalable.assignment;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssignmentController {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@GetMapping("/medicine/getDetails")
	public String index() {
		String sql = "SELECT * FROM MEDICINE";
        
		List<Map<String, Object>> medicines = jdbcTemplate.queryForList(sql);
		StringBuilder sb = new StringBuilder();
		
		if (medicines!=null && !medicines.isEmpty()) {
			
			for (Map<String, Object> medicine : medicines) {
				
				for (Iterator<Map.Entry<String, Object>> it = medicine.entrySet().iterator(); it.hasNext();) {
					Map.Entry<String, Object> entry = it.next();
					String key = entry.getKey();
					Object value = entry.getValue();
					sb.append(key + " = " + value + "<br>");
				}
				sb.append("<br>");
				sb.append("------------------------------");
				sb.append("<br>");
			}
			
		}
		return sb.toString();
	}
	
	//CREATE TABLE COMPANY(PatientId INT PRIMARY KEY NOT NULL, NAME TEXT NOT NULL, AGE TEXT NOT NULL);
	
	
	  @GetMapping("/createTable") public void tableCreate() { 
		  String sql = "CREATE TABLE MEDICINE(MedicineId TEXT PRIMARY KEY NOT NULL, MedicineName TEXT NOT NULL, MedicineExpiry TEXT NOT NULL)";
	  
		  jdbcTemplate.execute(sql); 
	  }
	 
	
	// API: POST - /add/patientDetails
	@PostMapping("/add/medicineDetails")
	public void addPatient(@RequestBody MedicineRecord request, HttpServletResponse response) {
		String sql = "INSERT INTO MEDICINE VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, request.medicineId, request.medicineName, request.medicineExpiry);
	}
	
	@PostMapping("/fetch/patientDrugDetails")
	public void fetchDrug(String patientid) {
		
	}

}