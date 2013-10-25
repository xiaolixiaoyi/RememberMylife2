package com.remmylife.manager;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.remmylife.dbacess.DataManager;
import com.remmylife.diary.Diary;
import com.remmylife.diary.TextDiary;
import com.remmylife.diary.User;
import com.remmylife.diary.VoiceDiary;

public class VoiceDiaryManager extends DiaryManager {


	
	public VoiceDiaryManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VoiceDiaryManager(String driver, String url, String user,
			String password) {
		super(driver, url, user, password);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public boolean save(Diary diary) {
		
		super.save(diary);
		
		try {
			dataManager.connectToDatabase();
		} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
		int id = diary.getId();
		String note=((VoiceDiary)diary).getNote();
		byte[] voice=((VoiceDiary)diary).getVoice();

		if(id==0){
			id = super.getId();
			
			try{
				java.sql.Connection con = DriverManager.getConnection(this.url,this.user,this.password);
				java.sql.PreparedStatement pS = con.prepareStatement("insert into `voicelist`(id,note,voicedata) values ('"
						+ id +"', '"+ note +"', ?);");
									
				//File imgFile = new File("d:\\d.jpg");
				//InputStream iS = new FileInputStream(imgFile);
				InputStream iS= new ByteArrayInputStream(voice);
				pS.setBinaryStream(1, iS,(int)(voice.length));
				pS.executeUpdate();
				
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
			

		}else{
			deleteself(diary);
			try{
				java.sql.Connection con = DriverManager.getConnection(this.url,this.user,this.password);
				java.sql.PreparedStatement pS = con.prepareStatement("insert into `voicelist`(id,note,voicedata) values ('"
						+ id +"', '"+ note +"', ?);");
									
				//File imgFile = new File("d:\\d.jpg");
				//InputStream iS = new FileInputStream(imgFile);
				InputStream iS= new ByteArrayInputStream(voice);
				pS.setBinaryStream(1, iS,(int)(voice.length));
				pS.executeUpdate();
				
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	
	public boolean deleteself(Diary diary){
		int id = diary.getId();
		String del="delete from voicelist where diaryid ="+id;
		try {
			dataManager.connectToDatabase();
			dataManager.setUpdate(del);
			dataManager.disconnectFromDatabase();
			return true;			
		} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}


	}
	
	@Override
	public boolean delete(Diary diary){
		int id = diary.getId();
		String del="delete from voicelist where diaryid ="+id;
		try {
			dataManager.connectToDatabase();
			dataManager.setUpdate(del);
			dataManager.disconnectFromDatabase();
			return super.delete(diary);			
		} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}


	}

	
	@Override
	public Diary getDiary(Diary diary){
		String getlist="SELECT * FROM VoiceList where diaryid = " +diary.getId();
		try{
			dataManager.connectToDatabase();
			dataManager.setQuery(getlist);
			String note = (String)dataManager.getValueAt(0, 1);
					
			java.sql.Connection con = DriverManager.getConnection(this.url,this.user,this.password);
			java.sql.PreparedStatement pS = con.prepareStatement("SELECT * FROM voicelist where diaryid = "+diary.getId());
			ResultSet rS = pS.executeQuery();
			
			if(rS.next()){
				java.sql.Blob blob = rS.getBlob("voicedata");
				byte[]voice= blob.getBytes(1, (int) blob.length());
				dataManager.disconnectFromDatabase();
				return new VoiceDiary(diary,voice,note);
			}else{
				return null;
			}
		} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
			

	}
	

	
	public ArrayList<Diary> searchByContent(String content, User self, boolean own){ 
		String query="Select * from voicelist where `note` like \"%"+ content +"%\"";
		String listown=query+" and `userid`="+ self.getUserID();
		String listshare = query + " and `shared` = 1 and `userid`<>"+ self.getUserID();
		
		ArrayList<Diary> diaryList = new ArrayList<Diary>();
		try {
			dataManager.connectToDatabase();
			if(own==true){
				dataManager.setQuery(listown);
				int numberOfRow= dataManager.getRowCount();
				for(int i =0; i<numberOfRow; i++){
					TextDiary diary = new TextDiary();
					diary.setId(Integer.valueOf(dataManager.getValueAt(i, 0).toString()));
					diaryList.add(diary);
				}
				dataManager.disconnectFromDatabase();
			}else{
				dataManager.setQuery(listshare);
				int numberOfRow= dataManager.getRowCount();
				for(int i =0; i<numberOfRow; i++){
					TextDiary diary = new TextDiary();
					diary.setId(Integer.valueOf(dataManager.getValueAt(i, 0).toString()));
					diaryList.add(diary);
				}
				dataManager.disconnectFromDatabase();
			}
			
		} catch (Exception e) {
    		e.printStackTrace();
    	}
			return diaryList;
	}
	
	@Override
	public ArrayList<Diary> constructList(DataManager dataManager){
		ArrayList<Diary> diaryListOrigin = super.constructList(dataManager);
		ArrayList<Diary> diaryList = new ArrayList<Diary>();
		for(int i=0 ; i < diaryListOrigin.size(); i++){
			Diary diary =diaryListOrigin.get(i);
			try {
				dataManager.setQuery("SELECT * FROM voicelist where diaryid="+diary.getId());
				String note = (String)dataManager.getValueAt(0, 1);
				
				java.sql.Connection con = DriverManager.getConnection(this.url,this.user,this.password);
				java.sql.PreparedStatement pS = con.prepareStatement("SELECT * FROM voicelist where id ="+diary.getId());
				ResultSet rS = pS.executeQuery();
				if(rS.next()){
					java.sql.Blob blob2 = rS.getBlob("voicedata");
					byte[]voice= blob2.getBytes(1, (int) blob2.length());
					diaryList.add(new VoiceDiary(diary,voice,note));
				}
				
			} catch (Exception e) {
        		e.printStackTrace();
        	}
		}
		return diaryList;
	}
	
	
}
