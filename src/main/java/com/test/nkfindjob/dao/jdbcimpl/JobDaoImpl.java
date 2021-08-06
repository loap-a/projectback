package com.test.nkfindjob.dao.jdbcimpl;

import com.test.nkfindjob.dao.GenericDao;
import com.test.nkfindjob.dao.JobDao;
import com.test.nkfindjob.domain.po.Job;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl extends GenericBaseDao implements JobDao {
    @Override
    public Job findById(Integer id) {
        Job job=null;
        try {
            this.getConnection();
            String sql="select * from jobs where id=?";
            this.executeQuery(sql,id);
            if(rs!=null && rs.next()){
                job = new Job(rs.getInt("id"),rs.getString("title"),
                        rs.getString("comName"),rs.getString("wages"),rs.getString("city"),
                        rs.getString("qualification"),rs.getString("treatment"));
            }
            this.closeAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return job;

    }

    @Override
    public List<Job> findAll() {
        List<Job> jobs = null;
        try{
            this.getConnection();
            String sql="select * from jobs";
            this.executeQuery(sql);
            if(rs!=null){
                jobs=new ArrayList<>();
                while(rs.next()){
                    Job job = new Job(rs.getInt("id"),rs.getString("title"),
                            rs.getString("comName"),rs.getString("wages"),rs.getString("city"),
                            rs.getString("qualification"),rs.getString("treatment"));
                    jobs.add(job);
                }
            }
            this.closeAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return jobs;
    }

    @Override
    public List<Job> findBySQL(String sql, Object... params) {
        List<Job> jobs=null;
        try {
            this.getConnection();
            this.executeQuery(sql,params);
            if(rs!=null){
                jobs=new ArrayList<>();
                while(rs.next()){
                    Job job = new Job(rs.getInt("id"),rs.getString("title"),
                            rs.getString("comName"),rs.getString("wages"),rs.getString("city"),
                            rs.getString("qualification"),rs.getString("treatment"));
                    jobs.add(job);
                }
            }
            this.closeAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return jobs;
    }

    @Override
    public int insert(Job job) {
        int res=-1;
        try {
            this.getConnection();
            String sql = "insert into jobs values(null,?,?,?,?,?,?)";
            this.executeUpdate(sql,job.getTitle(),job.getComName(),job.getWages(),
                    job.getCity(),job.getQualification(),job.getTreatment());
            res = result;
            this.closeAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return res;
    }

    @Override
    public int update(Job job) {
        int res = -1;
        try {
            this.getConnection();
            String sql = "update jobs set title=?,comName=?," +
                    "wages=?,city=?,qualification=?,treatment=? where id=?";
            this.executeUpdate(sql,job.getTitle(),job.getComName(),job.getWages(),
                    job.getCity(),job.getQualification(),job.getTreatment(),job.getId());

            res = result;
            this.closeAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return res;
    }

    @Override
    public int delete(Integer id) {
        int res = -1;
        try {
            this.getConnection();
            String sql = "delete from jobs where id = ?";
            this.executeUpdate(sql,id);
            res = result;
            this.closeAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return res;
    }

    @Override
    public int delete(Job job) {
        return delete(job.getId());
    }
}
