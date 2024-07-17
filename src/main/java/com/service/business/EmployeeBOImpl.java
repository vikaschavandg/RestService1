package com.service.business;

import com.service.domain.AppConstant;
import com.service.domain.FileUploadResponse;
import com.service.domain.JDDetail;
import com.service.util.FileUploadUtil;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import java.io.*;
import java.io.BufferedReader;
import java.util.ArrayList;
import org.springframework.util.LinkedMultiValueMap;
import java.util.List;
import com.service.domain.Employee;
import com.service.domain.TalentResult;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class EmployeeBOImpl implements EmployeeBO {
    Logger logger  = LoggerFactory.getLogger(EmployeeBOImpl.class);

    public  FileUploadResponse  jdUpload(MultipartFile multipartFile, String payload)  throws Exception  {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();
        FileUploadUtil fileUploadUtil= new FileUploadUtil();
        Integer jdId = fileUploadUtil.getIntegerJsonValueFromString(payload,"$.job-id");
        //String hiringManager = fileUploadUtil.getStringJsonValueFromString(payload,"$.hiring-manager");
        //String jobTitle = fileUploadUtil.getStringJsonValueFromString(payload,"$.job-title");
       // fileUploadUtil.writeToExcel(jdId, AppConstant.JD_EXTRACT_UPLOAD_PATH+"/"+fileName,hiringManager,jobTitle);
        String filecode = FileUploadUtil.saveFile(fileName, multipartFile, AppConstant.JD_EXTRACT_UPLOAD_PATH,jdId);
        try{
            ProcessBuilder processBuilder = new ProcessBuilder("python", AppConstant.PYTHON_SCRIPT_PATH);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = bfr.readLine();
            logger.info("Python output  {} ", line);
            while ((line = bfr.readLine()) != null) {
                logger.info("Python output inside {} ", line);
            }
        } catch (Exception exp){
            exp.printStackTrace();
        }
        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setFileSize(size);
        response.setMessage("JD Details extracted successfully");
        return response;
    }


    public  FileUploadResponse  jdSubmit(MultipartFile multipartFile, String payload)  throws Exception  {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();
        FileUploadUtil fileUploadUtil= new FileUploadUtil();
        Integer jdId = fileUploadUtil.getIntegerJsonValueFromString(payload,"$.job-id");
        String jobTitle = fileUploadUtil.getStringJsonValueFromString(payload,"$.job-title");
        String hiringManager = fileUploadUtil.getStringJsonValueFromString(payload,"$.hiring-manager");
        fileUploadUtil.writeToExcel(jdId, AppConstant.JD_EXTRACT_UPLOAD_PATH+"/"+fileName,hiringManager,jobTitle);
        String filecode = FileUploadUtil.saveFile(fileName, multipartFile, AppConstant.JD_EXTRACT_UPLOAD_PATH,jdId);
        try{
            ProcessBuilder processBuilder = new ProcessBuilder("python", AppConstant.PYTHON_SCRIPT_PATH);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = bfr.readLine();
            logger.info("Python output  {} ", line);
            while ((line = bfr.readLine()) != null) {
                logger.info("Python output inside {} ", line);
            }
        } catch (Exception exp){
            exp.printStackTrace();
        }
        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setFileSize(size);
        response.setMessage("JD Uploaded successfully");
        return response;
    }


    public  List<JDDetail>  jdList()  throws Exception  {
        FileUploadUtil fileUploadUtil= new FileUploadUtil();
        List<JDDetail> jdDetailList =  fileUploadUtil.readExcel();
        return jdDetailList;
    }


    public  List<TalentResult>  talentResult(String param1, String param2)  throws IOException  {
        try{
            ProcessBuilder processBuilder = new ProcessBuilder("python", AppConstant.PYTHON_SCRIPT_PATH);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = bfr.readLine();
            logger.info("Python output  {} ", line);
            while ((line = bfr.readLine()) != null) {
                logger.info("Python output inside {} ", line);
            }
        } catch (Exception exp){
            exp.printStackTrace();
        }
        List<TalentResult> employees = new ArrayList<TalentResult>();
        return employees;
    }

    public  Employee candidtateInfo(String param1, String param2)  throws IOException  {
        try{
            ProcessBuilder processBuilder = new ProcessBuilder("python", AppConstant.PYTHON_SCRIPT_PATH);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = bfr.readLine();
            logger.info("Python output  {} ", line);
            while ((line = bfr.readLine()) != null) {
                logger.info("Python output inside {} ", line);
            }
        } catch (Exception exp){
            exp.printStackTrace();
        }
        Employee employee = getEmployeeById(1);  //getEmployees();
        Resource resource=null;
        FileInputStream resource1=null;
        byte[] fileContent =null;
        File folder = new File(AppConstant.CV_UPLOAD_PATH);
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.getName().startsWith(param1)) {
                 resource1 = new FileInputStream(AppConstant.CV_UPLOAD_PATH+"/"+fileEntry.getName());
                  File file = new File(AppConstant.CV_UPLOAD_PATH+"/"+fileEntry.getName());
                 fileContent = Files.readAllBytes(file.toPath());

            }
        }

        employee.setResource(resource1);
        employee.setBytes(fileContent);
        employee.setMultipartFile(null);

        //f (resource == null) {
       // //    return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
      //  }
/*
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);*/

        return employee;
    }


    public FileUploadResponse uploadCV(MultipartFile multipartFile, String payload) throws IOException  {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();
        String filecode = FileUploadUtil.saveFile(fileName, multipartFile,AppConstant.JD_SUBMIT_UPLOAD_PATH,0);

        try{
            ProcessBuilder processBuilder = new ProcessBuilder("python",  AppConstant.PYTHON_SCRIPT_PATH);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = bfr.readLine();
            logger.info("Python output  {} ", line);
            while ((line = bfr.readLine()) != null) {
                logger.info("Python output inside {} ", line);
            }
        } catch (Exception exp){
            exp.printStackTrace();
        }

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setFileSize(size);
        response.setMessage("CV Uploaded successfully");
        return response;
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        Employee emp = new Employee();
        emp.setId(1);
        emp.setFirstName("A1");
        emp.setLastName("A2");
        emp.setCorporateTitle("Analyst");
        emp.setLocation("India");
        employees.add(emp);

        emp = new Employee();
        emp.setId(1);
        emp.setFirstName("B1");
        emp.setLastName("B2");
        emp.setCorporateTitle("Analyst");
        emp.setLocation("Singapore");
        employees.add(emp);
        return employees;
    }

    public Employee getEmployeeById(int empid) {
        List<Employee> employees = getEmployees();
        List<Employee> eList  = employees.stream().filter(ee -> ee.getId() == empid).collect(Collectors.toList());
        return eList.get(0);
    }


    public void callPythonEndpoint(MultipartFile multipartFile,String payload)  throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("FILE", fileUploadUtil.getFile(multipartFile));
        body.add("SOURCE_ID", "3333");
        HttpEntity<MultiValueMap<String,Object>> requestEntity = new HttpEntity<>(body,headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(AppConstant.PYTHON_ENDPOINT_URL, requestEntity, String.class);
    }

   /* public void callPythonAsynchEndpoint(MultipartFile multipartFile,String payload)  throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("FILE", fileUploadUtil.getFile(multipartFile));
        body.add("SOURCE_ID", "3333");
        HttpEntity<MultiValueMap<String,Object>> requestEntity = new HttpEntity<>(body,headers);
        AsyncRestTemplate  restTemplate = new AsyncRestTemplate ();
        ListenableFuture<ResponseEntity<String>> response = restTemplate.postForEntity(AppConstant.PYTHON_ENDPOINT_URL, requestEntity, String.class);
*/
       /* futureEntity.addCallback(
                result -> {
                    // Handle success (result.getBody())
                },
                ex -> {
                    // Handle failure (ex.getMessage())
                }
        );*/

/*
        AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
        JSONObject json = new JSONObject();
        json.put("firstName","testUser");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<String>(json.toString(), headers);

        Class<String> responseType = String.class;
        ListenableFuture<ResponseEntity<String>> future = asyncRestTemplate.exchange("https://xxxxx.com/", HttpMethod.POST, requestEntity,responseType );


        try {
            //waits for the result
            ResponseEntity<String> entity = future.get();
            //prints body source code for the given URL
            log.info(entity.getBody());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    //}
}
