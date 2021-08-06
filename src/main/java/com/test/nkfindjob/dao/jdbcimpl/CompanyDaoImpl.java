package com.test.nkfindjob.dao.jdbcimpl;

import com.test.nkfindjob.dao.CompanyDao;
import com.test.nkfindjob.domain.po.Company;
import com.test.nkfindjob.domain.po.Job;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl extends GenericBaseDao implements CompanyDao {
    @Override
    public Company findById(String s) {
        Company company=null;
        try {
            this.getConnection();
            String sql = "select * from companies where name=?";
            this.executeQuery(sql,s);
            if(rs!=null&&rs.next()){
                company=new Company(rs.getString("name"),rs.getString("industry"),
                        rs.getString("scale"),rs.getString("capital"));
            }
            this.closeAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return company;
    }

    @Override
    public List<Company> findAll() {
        List<Company> companies=null;
        try {
            this.getConnection();
            String sql="select * from companies";
            this.executeQuery(sql);
            this.closeAll();
            if(rs!=null){
                companies=new ArrayList<>();
                while(rs.next()){
                    Company company=new Company(rs.getString("name"),rs.getString("industry"),
                            rs.getString("scale"),rs.getString("capital"));
                    companies.add(company);
                }
            }
            this.closeAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return companies;
    }

    @Override
    public List<Company> findBySQL(String sql, Object... params) {
        List<Company> companies=null;
        try {
            this.getConnection();
            this.executeQuery(sql,params);
            if(rs!=null){
                companies=new ArrayList<>();
                while(rs.next()){
                    Company company=new Company(rs.getString("name"),rs.getString("industry"),
                            rs.getString("scale"),rs.getString("capital"));
                    companies.add(company);
                }
            }
            this.closeAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return companies;
    }

    @Override
    public int insert(Company company){
        int res=-1;
        try {
            this.getConnection();
            String sql = "insert into companies values(?,?,?,?)";
            this.executeUpdate(sql,company.getName(),company.getIndustry(),
                    company.getScale(),company.getCapital());
            res = result;
            this.closeAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return res;
    }

    @Override
    public int update(Company company) {
        int res=-1;
        try {
            this.getConnection();
            String sql = "update companies set industry=?, scale=?, capital=? where name=?";
            this.executeUpdate(sql,company.getIndustry(),
                    company.getScale(),company.getCapital(),company.getName());
            res = result;
            this.closeAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return res;
    }

    @Override
    public int delete(String s) {
        int res=-1;
        try {
            this.getConnection();
            String sql="delete from companies where name=?";
            this.executeUpdate(sql,s);
            res=result;
            this.closeAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    @Override
    public int delete(Company company) {
        return delete((company.getName()));
    }
}
