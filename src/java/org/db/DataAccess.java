package org.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataAccess
{
    Connection conn;
    PreparedStatement insertModStmt;
    String insertModsql;
    PreparedStatement insertUnitStmt;
    String insertUnitsql;
    PreparedStatement stmt;
    String updateModsql;
    PreparedStatement updateModStmt;
    String updateUnitsql;
    PreparedStatement updateUnitStmt;
    String insertCoursesql;
    PreparedStatement insertModeStmt;
    String updateCoursesql;
    PreparedStatement updateCourseStmt;
   
    public DataAccess()
    {
       insertModsql = "insert into module(module_code, module_title_de, module_title_en, sprache, emp_sem, ects, belegnummer_his_pos, level, "
                + "verwendbarkeit_module, dauer_module, status, voraussetzungen_module, inhaltlich_voraussetzungen, "
                + "voraussetzungen_modulpruefung, modulpruefung, kompetenzen, inhalte_module, lehrformen_module, arbeitsaufwand, "
                + "haeufigkeit_angebots, modulkoordination, hinweise) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
       
       insertUnitsql = "insert into unit(unit_code, unit_title_de, unit_title_en, zugehorigen_module, lehrende, unit_inhalte, lehrform, unit_sws, "
               + "workload, praesenzzeit, pruengsvorbereitung, praxiszeit, selbststudium, unit_sprache, basis_literatur, "
               + "art_form_leistungsnachweises, bewertung_leistungsnachweises, hinweise, fachgruppe_short, fachgruppe) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
       updateModsql = "update module set module_title_de = ?, module_title_en = ?, sprache = ?, emp_sem = ?, ects = ?, "
               + "belegnummer_his_pos = ?, level = ?, verwendbarkeit_module = ?, dauer_module = ?, status = ?, voraussetzungen_module = ?, "
               + "inhaltlich_voraussetzungen = ?, voraussetzungen_modulpruefung = ?, modulpruefung = ?, kompetenzen = ?, inhalte_module = ?, "
               + "lehrformen_module = ?, arbeitsaufwand = ?, haeufigkeit_angebots = ?, modulkoordination = ?, hinweise = ? "
               + " where module_code = ?";
       
       updateUnitsql = "update unit set unit_title_de = ?, unit_title_en = ?, zugehorigen_module = ?, lehrende = ?, "
               + "unit_inhalte = ?, lehrform = ?, unit_sws = ?, workload = ?, praesenzzeit = ?, pruengsvorbereitung = ?, "
               + "praxiszeit = ?, selbststudium = ?, unit_sprache = ?, basis_literatur = ?, art_form_leistungsnachweises = ?, "
               + "bewertung_leistungsnachweises = ?, hinweise = ?, fachgruppe_short = ?, fachgruppe = ? where unit_code = ?";
       
       insertCoursesql = "insert into course(course_name) values(?)";    
    }
    public boolean CreateConnection(String url, String user, String password) 
    {
        conn = null;
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("Connection to db successful");
            return true;
        } 
        catch(Exception e)
        {
            //e.printStackTrace();
            return false;
        }     
    }

    public Connection getConn() {
        return conn;
    }
    
    public boolean insertCourse(ArrayList<String> params)
    {
        String insertStatement = "insert into course(course_code, course_name) values (?, ?)";
        try
        {
            stmt = conn.prepareStatement(insertStatement);
            for(int i=0;i<params.size();i++)
            {
                stmt.setString(i+1, params.get(i));
            }
            stmt.executeUpdate();
            return true;
        }
        catch(Exception e)
        {
            //e.printStackTrace();
            return false;
        }
    }
    
    public boolean insertDegree(String degreeName)
    {
        String insertStatement = "insert into degree(degree_name) values (?)";
        try
        {
            stmt = conn.prepareStatement(insertStatement);
            stmt.setString(1, degreeName);
            stmt.executeUpdate();
            return true;
        }
        catch(Exception e)
        {
            //e.printStackTrace();
            return false;
        }
    }

    public boolean insertModule(ArrayList<String> params)
    {
        try
        {           
            if(insertModStmt==null) insertModStmt = conn.prepareStatement(insertModsql);
            
            for(int i=0;i<params.size();i++)
            {
                insertModStmt.setString(i+1, params.get(i));
            }
            insertModStmt.executeUpdate();
            return true;
        }
        catch(Exception e)
        {
            ///e.printStackTrace();
            //System.out.println(params.get(6));
            return false;
        }
    }
    public boolean updateModule(ArrayList<String> params)
    {
        try
        {           
            if(updateModStmt==null) updateModStmt = conn.prepareStatement(updateModsql);
            int i;
            for(i=1;i<params.size();i++)
            {
                updateModStmt.setString(i, params.get(i));
            }
            updateModStmt.setString(i, params.get(0) );
            updateModStmt.executeUpdate();
            return true;
        }
        catch(Exception e)
        {
            //e.printStackTrace();
            return false;
            //System.out.println(params.get(6));
        }
    }
    public boolean updateUnit(ArrayList<String> params)
    {
        try
        {           
            if(updateUnitStmt==null) updateUnitStmt = conn.prepareStatement(updateUnitsql);
            int i;
            for(i=1;i<params.size();i++)
            {
                updateUnitStmt.setString(i, params.get(i));
            }
            updateUnitStmt.setString(i, params.get(0) );
            updateUnitStmt.executeUpdate();
            return true;
        }
        catch(Exception e)
        {
            //e.printStackTrace();
            return false;
            //System.out.println(params.get(6));
        }
    }
    public boolean insertUnit(ArrayList<String> params)
    {
        try
        {           
            if(insertUnitStmt==null) insertUnitStmt = conn.prepareStatement(insertUnitsql);
            
            for(int i=0;i<params.size();i++)
            {
                insertUnitStmt.setString(i+1, params.get(i));
            }
            insertUnitStmt.executeUpdate();
            return true;
        }
        catch(Exception e)
        {
            //e.printStackTrace();
            return false;
        }
    }
    public int getCourseID(String courseName)
    {
        int id = -1;
        String query = "select max(id) from course where course_name = ?";
        try
        {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, courseName);
            ResultSet rs = stmt.executeQuery();
            while( rs.next() )
            {
                id = rs.getInt(1);
            }
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
        return id;
    }
    
    public int getDegreeID(String degreeName)
    {
        int id = -1;
        String query = "select max(id) from degree where degree_name = ?";
        try
        {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, degreeName);
            ResultSet rs = stmt.executeQuery();
            while( rs.next() )
            {
                id = rs.getInt(1);
            }
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
        return id;
    }
    public int getCourseCount(String course_code)
    {
        int count = 0;
        String query = "select count(*) from course where course_code = ?";
        try
        {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, course_code);
            ResultSet rs = stmt.executeQuery();
            while( rs.next() )
            {
                count = rs.getInt(1);
            }
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
        return count;
    }
    public void insertDegreeCourse(int degreeID, String course_code)
    {
        String insertStatement = "insert into degreecourse(degree_id, course_code) values (?,?)";
        try
        {
            stmt = conn.prepareStatement(insertStatement);
            stmt.setInt(1, degreeID);
            stmt.setString(2, course_code);
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
    }
    public void insertCourseModule(ArrayList<String> params)
    {
        String insertStatement = "insert into coursemodule(course_code, module_code) values (?,?)";
        try
        {
            stmt = conn.prepareStatement(insertStatement);
            for(int i=0;i<params.size();i++)
            {
                stmt.setString(i+1, params.get(i));
            }
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
    }
    public void insertModuleUnit(ArrayList<String> params)
    {
        String insertStatement = "insert into moduleunit(module_code, unit_code) values (?,?)";
        try
        {
            stmt = conn.prepareStatement(insertStatement);
            for(int i=0;i<params.size();i++)
            {
                stmt.setString(i+1, params.get(i));
            }
            stmt.executeUpdate();
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
    }
    public int getCourseModuleCount(String course_code, String module_code)
    {
        String query = "select count(*) from coursemodule where course_code = ? and module_code = ?";
        int count = 0;
        try
        {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, course_code);
            stmt.setString(2, module_code);
            ResultSet rs = stmt.executeQuery();
            while( rs.next() )
            {
                count = rs.getInt(1);
            }
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
        return count;
    }
    public int getModuleUnitCount(String module_code, String unit_code)
    {
        String query = "select count(*) from moduleunit where module_code = ? and unit_code = ?";
        int count = 0;
        try
        {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, module_code);
            stmt.setString(2, unit_code);
            ResultSet rs = stmt.executeQuery();
            while( rs.next() )
            {
                count = rs.getInt(1);
            }
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
        return count;
    }
    public int getModuleCount(String moduleCode)
    {
        int count = 0;
        String query = "select count(*) from module where module_code = ?";
        try
        {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, moduleCode);
            ResultSet rs = stmt.executeQuery();
            while( rs.next() )
            {
                count = rs.getInt(1);
            }
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
        return count;
    }
    public int getUnitCount(String unit_code)
    {
        int count = 0;
        String query = "select count(*) from unit where unit_code = ?";
        try
        {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, unit_code);
            ResultSet rs = stmt.executeQuery();
            while( rs.next() )
            {
                count = rs.getInt(1);
            }
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
        return count;
    }
}
