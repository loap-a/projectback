package com.test.nkfindjob.dao;

import com.test.nkfindjob.dao.jdbcimpl.CompanyDaoImpl;
import com.test.nkfindjob.dao.jdbcimpl.JobDaoImpl;
import com.test.nkfindjob.domain.po.Company;
import com.test.nkfindjob.domain.po.Job;

import java.io.*;
import java.sql.SQLIntegrityConstraintViolationException;

public class importAllData {
    public static void main(String[] args) {
        JobDaoImpl jobDao=new JobDaoImpl();
        CompanyDaoImpl companyDao=new CompanyDaoImpl();
//        companyDao.insert(new Company("123","123","123","123"));
//        Company company=new Company("123","555","666","333");
//        if(companyDao.findById("123")==null) companyDao.insert(company);
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("Boss.csv"),"GB18030"));//liepin 2120
            String line="";
            while((line=br.readLine())!=null){
                System.out.println(line);
                String[] lineSpilt=line.split(",");
                String title=lineSpilt[0];
                String comName=lineSpilt[1];
                String industry=lineSpilt[2];
                String scale=lineSpilt[3];
                String capital=lineSpilt[4];
                String wages=lineSpilt[5];
                String city=lineSpilt[6];
                String qualification=lineSpilt[7];
                String treatment=lineSpilt[8];
                Job job=new Job(title,comName,city,wages,qualification,treatment);
                Company company=new Company(comName,industry,scale,capital);
                jobDao.insert(job);
                if(companyDao.findById(comName)==null)companyDao.insert(company);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(Exception e) {
            System.out.println("就是玩");
        }
    }

}
