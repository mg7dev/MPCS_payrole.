package com.mpcs.ServiceImpl;

import com.mpcs.Dto.EmployeeRequest;
import com.mpcs.Model.Deductions;
import com.mpcs.Model.Employee;
import com.mpcs.Model.SalaryAllowances;
import com.mpcs.Model.SalaryDetails;
import com.mpcs.Repository.EmployeeRepository;
import com.mpcs.Repository.SalaryDetailsRepository;
import com.mpcs.Service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Value("${epf.employee.deduction}")
    private String eEDeduction;
    @Value("${epf.company.deduction}")
    private String eCDeduction;
    @Value("${etf.company.deduction}")
    private String etfVal;

    private final String FILE_HEADERS = "EMP_ID,NAME,Email,Basic_Salary,Total_Allowances,Total_Deductions,Total_Salary";
    private final String LINE_SEPARATOR = "\n";
    private final String COMMA_DELIMETER = ",";


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SalaryDetailsRepository salaryDetailsRepository;

    @Autowired
    private JavaMailSender mailSender;

    Iterable<Employee> employeeList = null;

    @Override
    public void employeeSalarySave(EmployeeRequest employeeRequest) {

        logger.info("Salary calculation starts in serviceImpl");
        Employee employee = new Employee();
        employee.setEmp_id(employeeRequest.getEmp_id());
        employee.setSalaryAllowances(employeeRequest.getSalaryAllowances());
        employee.setDeductions(employeeRequest.getDeductions());
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setBasicSalary(employeeRequest.getBasicSalary());
        employeeRepository.save(employee);
    }

    //scheduler
    @Override
    public void monthlySalaryDataGenerator() throws IOException {
        //Calculate salary and save
        calculateSalaryForEmployee();
    }

    private void calculateSalaryForEmployee() throws IOException {
        SalaryDetails salaryDetails = new SalaryDetails();
        List<SalaryAllowances> salaryAllowancesList = null;
        List<Deductions> deductionsList = null;
        double eEvalue = Double.parseDouble(eEDeduction);
        double cEvalue = Double.parseDouble(eCDeduction);
        double etfValue = Double.parseDouble(etfVal);
        double allowancesAmount = 0;
        double deduction = 0;
        double total = 0;
        //Calculate Salary
        employeeList = employeeRepository.findAll();
        if(employeeList != null){
            for(Employee employee : employeeList) {
                salaryDetails.setEmp_id(employee.getEmp_id());
                salaryDetails.setFirstName(employee.getFirstName());
                salaryDetails.setLastName(employee.getLastName());
                salaryDetails.setEmail(employee.getEmail());
                salaryDetails.setBasicSalary(employee.getBasicSalary());

                salaryAllowancesList = employee.getSalaryAllowances();
                if(!salaryAllowancesList.isEmpty()){
                    for(SalaryAllowances salaryAllowances : salaryAllowancesList){
                        //Add to basic
                        allowancesAmount = salaryAllowances.getAmount() + allowancesAmount;
                    }
                    salaryDetails.setAllowancesAmount(allowancesAmount);
                }else{
                    salaryDetails.setAllowancesAmount(0D);
                }

                //EPF
                //deduct from total
                double emplyeeDeduction = employee.getBasicSalary() + (employee.getBasicSalary() * eEvalue);
                double companyDeduction =  employee.getBasicSalary() + (employee.getBasicSalary() * cEvalue);
                salaryDetails.setEpf(emplyeeDeduction+companyDeduction);

                //ETF
                double etf = employee.getBasicSalary() + (employee.getBasicSalary() * etfValue);
                salaryDetails.setEtf(etf);

                //Deductions
                deductionsList = employee.getDeductions();
                if(!deductionsList.isEmpty()){
                    for(Deductions deductions : deductionsList){
                        deduction = deductions.getAmount() + deduction;
                    }
                    salaryDetails.setDeductions(deduction);
                }else{
                    salaryDetails.setDeductions(0D);
                }
                total = (employee.getBasicSalary() + allowancesAmount) - (deduction + etf);
                salaryDetails.setTotalSalary(total);
                salaryDetailsRepository.save(salaryDetails);

                File file = salarySheetGenerator(salaryDetails,salaryAllowancesList,deductionsList);
                
                //Send to email

                salaryMailSender(file, salaryDetails);
            }

        }
    }

    //Salary Sheet generator and send email
    public File salarySheetGenerator(SalaryDetails salaryDetails, List<SalaryAllowances> salaryAllowancesList,
                                     List<Deductions> deductionsList) throws IOException {
        File file = null;
        double allowancesAmount = 0;
        double deductAmount = 0;
        StringBuffer sb =  new StringBuffer();
        String fileName = String.valueOf(sb.append("Salary sheet of "+ salaryDetails.getEmp_id()));
       // String fileSavePath = String.valueOf(sb.append("D:\\Projects\\MPCS_PayRole\\src\\main\\resources\\SalarySheets"));
        String fileSavePath = "D:\\Projects\\MPCS_PayRole\\src\\main\\resources\\SalarySheets\\"+fileName+".csv";
        FileWriter fileWriter = new FileWriter(fileSavePath);
        fileWriter.append(FILE_HEADERS);
        fileWriter.append(LINE_SEPARATOR);
        fileWriter.append(String.valueOf(salaryDetails.getEmp_id()));
        fileWriter.append(COMMA_DELIMETER);
        fileWriter.append(salaryDetails.getFirstName() + " " + salaryDetails.getLastName());
        fileWriter.append(COMMA_DELIMETER);
        fileWriter.append(salaryDetails.getEmail());
        fileWriter.append(COMMA_DELIMETER);
        fileWriter.append(String.valueOf(salaryDetails.getBasicSalary()));
        fileWriter.append(COMMA_DELIMETER);
        for(SalaryAllowances salaryAllowances : salaryAllowancesList) {
            allowancesAmount  += salaryAllowances.getAmount();
            
        }
        fileWriter.write(String.valueOf(allowancesAmount));
        fileWriter.append(COMMA_DELIMETER);
        for(Deductions deductions : deductionsList) {
            deductAmount += deductions.getAmount();

        }
        fileWriter.append(String.valueOf(deductAmount));
        fileWriter.append(COMMA_DELIMETER);
        fileWriter.append(String.valueOf(salaryDetails.getTotalSalary()));
        fileWriter.flush();
        fileWriter.close();

        //Read file to send the mail
        file = new File(fileSavePath);
        if(!file.exists()){
            //File not exist
        }else {
            return file;
        }
        return file;
    }

    public void salaryMailSender(File file, SalaryDetails salaryDetails){

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(salaryDetails.getEmail()));
                mimeMessage.setFrom(new InternetAddress("mail@mycompany.com"));
                mimeMessage.setText(
                        "Dear "
                                + ", thank you for placing order. Your order number is");
            }
        };
        try {
            this.mailSender.send(preparator);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }


    @Override
    public Iterable<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }
}

