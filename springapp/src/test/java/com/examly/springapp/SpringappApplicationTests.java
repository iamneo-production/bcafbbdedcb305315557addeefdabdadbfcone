package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
class SpringappApplicationTests {
	@Autowired
    private MockMvc mockMvc;

    
	@Test
	public void testPostData() throws Exception {
		String st = "{\"medicineId\": 2, \"medicineName\": \"asthalin\", \"medicineFor\": \"Cold\", \"medicineBrand\": \"cipla\", \"manufacturedIn\": \"chennai\", \"medicinePrice\": 37.0, \"expiryDate\": \"2020-03-03\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/medicine")
				.contentType(MediaType.APPLICATION_JSON)
				.content(st)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
	}
	

    @Test
    public void testGetByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/medicine/2")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetByBrand() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/medicine/brand/cipla")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn();
    }
    
    @Test
    public void testGetByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/medicine/name/asthalin")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    
    @Test
    public void testGetByBrandAndname() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/medicine/asthalin/cipla")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/medicine")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn();
    }
    

    private void checkClassExists(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " does not exist.");
        }
    }

    private void checkFieldExists(String className, String fieldName) {
        try {
            Class<?> clazz = Class.forName(className);
            clazz.getDeclaredField(fieldName);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            fail("Field " + fieldName + " in class " + className + " does not exist.");
        }
    }

	private void checkAnnotationExists(String className, String annotationName) {
		try {
			Class<?> clazz = Class.forName(className);
			ClassLoader classLoader = clazz.getClassLoader();
			Class<?> annotationClass = Class.forName(annotationName, false, classLoader);
			assertNotNull(clazz.getAnnotation((Class) annotationClass)); // Use raw type
		} catch (ClassNotFoundException | NullPointerException e) {
			fail("Class " + className + " or annotation " + annotationName + " does not exist.");
		}
	}
	

	 @Test
   public void testControllerClassExists() {
       checkClassExists("com.examly.springapp.controller.MedicineController");
   }

   @Test
   public void testRepoClassExists() {
       checkClassExists("com.examly.springapp.repository.MedicineRepo");
   }

   @Test
   public void testServiceClassExists() {
       checkClassExists("com.examly.springapp.service.MedicineService");
   }

   @Test
   public void testModelClassExists() {
       checkClassExists("com.examly.springapp.model.Medicine");
   }


   @Test
   public void testModelHasMedicineNameField() {
       checkFieldExists("com.examly.springapp.model.Medicine", "medicineName");
   }

   @Test
   public void testModelHasMedicineBrandForField() {
       checkFieldExists("com.examly.springapp.model.Medicine", "medicineBrand");
   }

   @Test
   public void testModelHasMedicineForField() {
       checkFieldExists("com.examly.springapp.model.Medicine", "medicineFor");
   }
   
   @Test
   public void testModelHasManufacturedForField() {
       checkFieldExists("com.examly.springapp.model.Medicine", "manufacturedIn");
   }
   
   @Test
   public void testModelHasMedicinePriceForField() {
       checkFieldExists("com.examly.springapp.model.Medicine", "medicinePrice");
   }
   
   @Test
   public void testModelHasExpiryDateForField() {
       checkFieldExists("com.examly.springapp.model.Medicine", "expiryDate");
   }

   @Test
   public void testModelHasEntityAnnotation() {
       checkAnnotationExists("com.examly.springapp.model.Medicine", "javax.persistence.Entity");
   }

   @Test
   public void testRepoHasRepositoryAnnotation() {
       checkAnnotationExists("com.examly.springapp.repository.MedicineRepo", "org.springframework.stereotype.Repository");
   }
   @Test
   public void testServiceHasServiceAnnotation() {
       checkAnnotationExists("com.examly.springapp.service.MedicineService", "org.springframework.stereotype.Service");
   }
   
   @Test
   public void testControllerHasRestControllerAnnotation() {
       checkAnnotationExists("com.examly.springapp.controller.MedicineController", "org.springframework.web.bind.annotation.RestController");
   }
   
   @Test 
   public void testControllerFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/controller"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }
   

	@Test 
   public void testModelFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/model"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }
   

	@Test 
   public void testRepositoryFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/repository"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }
   

	@Test 
   public void testServiceFolder() { 
       String directoryPath = "src/main/java/com/examly/springapp/service"; // Replace with the path to your directory 
       File directory = new File(directoryPath); 
       assertTrue(directory.exists() && directory.isDirectory()); 
   }
   


}
