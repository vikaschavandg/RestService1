package com.service.business;


import java.util.List;
import com.service.domain.Employee;
import com.service.domain.FileUploadResponse;
import com.service.domain.JDDetail;
import org.springframework.web.multipart.MultipartFile;
import com.service.domain.TalentResult;

public interface  EmployeeBO {

    String jdUpload(MultipartFile multipartFile) throws Exception ;
    FileUploadResponse jdSubmit(String payload) throws Exception ;
    List<JDDetail>  jdList( ) throws Exception ;
    List<TalentResult>  talentResult(String jdId) throws Exception ;
    Employee candidtateInfo(String employeeId) throws Exception ;

}
