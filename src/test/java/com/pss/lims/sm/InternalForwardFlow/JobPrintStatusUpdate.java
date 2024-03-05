package com.pss.lims.sm.InternalForwardFlow;

import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pss.lims.login.SMLoginDetails;

public class JobPrintStatusUpdate extends SMLoginDetails{
		
	 		int limsSampleLoginId = 0;
	 		int printedBy = 0;
	 		PreparedStatement pstmt = null;
	 		
	 		DateFormat dateFormatYMD = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");	
	 		Date now = new Date();
	 		String vDateYMD = dateFormatYMD.format(now);
//	 		java.sql.Date date = new java.sql.Date(now.getTime());
	 		java.sql.Timestamp timestamp = new java.sql.Timestamp(now.getTime());
	 		
		    @Test
		    public void getARNUmberFromDataBase() {
		        try {
		            String query1 = "select ID from LIMS_SAMPLE_LOGIN where AR_NO = '"+properties.getProperty("Ar_Number_For_Print_Staus_Update")+"'";
		            statement = connection.createStatement();
		            rs = statement.executeQuery(query1);
		           
		            while(rs.next()){
		            	limsSampleLoginId = rs.getInt("ID");
	                System.out.println("limsSampleLoginId:- "+limsSampleLoginId);
		            }
//		        } catch (SQLException ex) {
//		           ex.printStackTrace();
//		        }
//		        
//		        try {
		            String query2 = "select IN_CHARGE  from LIMS_SAMPLE_TESTS where SAMPLE_ID = '"+limsSampleLoginId+"'";
		            statement = connection.createStatement();
		            rs = statement.executeQuery(query2);

		            while(rs.next()){
		               printedBy= rs.getInt("IN_CHARGE");
//
		               System.out.println("printedBy:- "+printedBy);
	                pstmt = connection.prepareStatement("update LIMS_SAMPLE_TESTS set JOB_STATUS = ?, PRINTED_BY = ?, PRINTED_ON = ? where SAMPLE_ID = ?");
		            //Set name value which you wants to update.
		            pstmt.setInt(1, 2);
		            pstmt.setInt(2, printedBy);
		            pstmt.setTimestamp(3,timestamp);
		            //Set id of record which you wants to update.
		            pstmt.setInt(4, limsSampleLoginId);
		            //To execute update query.   
		            pstmt.executeUpdate();
		            }
		            
		            
		        } catch (SQLException ex) {
		           ex.printStackTrace();
		        }
		    }

		}



//String EmpAddress=rs.getString(3); select * from LIMS_SAMPLE_TESTS WHERE SAMPLE_ID=104;
//String EmpDept=rs.getString("EmpDept");
//Double EmpSal= rs.getDouble(5);
//System.out.println(EmpId+"\t"+EmpName+"\t"+EmpAddress+"\t"+EmpSal+"\t"+EmpDept);

//ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));

//Since Java 8
//ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));