package com.mpcs.Controller;
import com.mpcs.Common.AuthenticationRequest;
import com.mpcs.Common.Response;
import com.mpcs.Dto.EmployeeRequest;
import com.mpcs.Model.Employee;
import com.mpcs.Service.EmployeeService;
import com.mpcs.ServiceImpl.MyUserDetails;
import com.mpcs.Utills.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class AdminController {
    Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private MyUserDetails myUserDetails;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtUtils jwtUtils;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> userAuthenticate(@RequestBody AuthenticationRequest authenticationRequest ) throws Exception {
        UserDetails user = myUserDetails.loadUserByUsername(authenticationRequest.getUserName());

        if(authenticationRequest.getUserName() != null && user != null){
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),
                                authenticationRequest.getPassword())
                );
            } catch (Exception e) {
                throw  new Exception("Bad Credentials, {}", e.getCause());
            }
        }else
            throw  new Exception("Bad Credentials User not exist, {}");
        final String jwt = jwtUtils.generateToken(authenticationRequest.getUserName());
        return ResponseEntity.ok(new Response(jwt));
    }
    @RequestMapping(value = "/employee/salary/save", method = RequestMethod.POST)
    public void SalaryCalculate(@RequestBody EmployeeRequest employeeRequest){
       try{
           employeeService.employeeSalarySave(employeeRequest);
       }catch (Exception e){
           logger.info("Employee save error");
       }
    }
    @RequestMapping(value = "find/all", method = RequestMethod.POST)
    public List<Employee> findAllEmployee(){
        Iterable<Employee> employees = null;
        try{
            employees = employeeService.findAllEmployee();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return (List<Employee>) employees;
    }

    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    public void mail(){
        try{
            employeeService.monthlySalaryDataGenerator();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
