package com.service.business;

import java.io.IOException;
import java.util.List;
import com.service.domain.Employee;
import com.service.domain.FileUploadResponse;
import com.service.domain.JDDetail;
import org.springframework.web.multipart.MultipartFile;
import com.service.domain.TalentResult;

public interface  EmployeeBO {


    FileUploadResponse jdUpload(MultipartFile multipartFile, String payload) throws Exception ;
    FileUploadResponse jdSubmit(MultipartFile multipartFile, String payload) throws Exception ;
    List<JDDetail>  jdList( ) throws Exception ;
    List<TalentResult>  talentResult(String param1, String param2) throws IOException ;
    Employee candidtateInfo(String param1, String param2) throws IOException ;

    List<Employee> getEmployees() ;
    Employee getEmployeeById(int id) ;
    FileUploadResponse uploadCV(MultipartFile multipartFile, String payload) throws IOException;

}
