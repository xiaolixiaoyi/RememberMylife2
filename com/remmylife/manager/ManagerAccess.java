package com.remmylife.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.remmylife.dbacess.*;
import com.remmylife.diary.*;
import com.remmylife.manager.*;

public class ManagerAccess {
	
	UserManager userManager = new UserManager();
	DiaryManager diaryManager = new DiaryManager();
	TextDiaryManager textDiaryManager = new TextDiaryManager();
	ImageDiaryManager imageDiaryManager = new ImageDiaryManager();
	VoiceDiaryManager voiceDiaryManager = new VoiceDiaryManager();
	CommentManager commentManager = new CommentManager();
	
	public ManagerAccess() {
		super();
	}
	
	// user �Ĳ���
	public boolean loginByName(String userName, String password){
		return userManager.signin(userName, password);
	}
	
	public User getUser(User user){
		if(userManager.signup(user)){
			return userManager.getUser(user);
		}else{
			return null;
		}
	}
	
	public boolean saveUser(User user){
		return userManager.save(user);
	}
	
	public boolean deleteUser(User user){
		return userManager.delete(user);
	}
	
	// diary �Ĳ���
	
	public boolean deleteDiary(Diary diary){
		return diaryManager.delete(diary);
	}
	
	public boolean saveDiary(Diary diary){
		DiaryType type = diary.getType();
		switch(type){
		case TEXT_DIARY: return textDiaryManager.save(diary);
		case IMAGE_DIARY: return imageDiaryManager.save(diary);
		case VOICE_DIARY: return voiceDiaryManager.save(diary);
		}
		return false;
	}
	
	public void shareDiary(Diary diary, User self){
		diaryManager.shareDiary(diary, self);
	}
	
	public void unshareDiary(Diary diary, User self){
		diaryManager.unshareDiary(diary, self);
	}
	
	
	public ArrayList<Diary> getDiaryList(User self, boolean own){
		return diaryManager.getList(self, own);
	}
	
	public Diary getDiary(Diary diary){
		DiaryType type = diary.getType();
		switch(type){
		case TEXT_DIARY: return textDiaryManager.getDiary(diary);
		case IMAGE_DIARY: return imageDiaryManager.getDiary(diary);
		case VOICE_DIARY: return voiceDiaryManager.getDiary(diary);
		}
		return null;
	}
	
	// Comment
	public void saveComment(Diary diary,User user,String note){
		Comment comment = new Comment(user.getUserID(),diary.getId(),note);
		commentManager.save(comment);
	}
	
	public ArrayList<Comment> getComment(Diary diary){
		return commentManager.getCommentList(diary);
	}
	
	
	
	
	// search
	public ArrayList<Diary> searchByTitle(String title, User self, boolean own){
		String listByTitle= "Select * from diarylist where title like \"%"+title +"%\"";
		return diaryManager.search(listByTitle, self, own);
	}
	
	public ArrayList<Diary> searchByDate(Date date, User self, boolean own){ 
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");    
        String dateq = sDateFormat.format(date); 
        String listByTime= "Select * from diarylist where date like \""+ dateq +"%\"";
		return diaryManager.search(listByTime, self, own);
	}
	
	public ArrayList<Diary> searchByType(DiaryType type, User self, boolean own){
		String typeq = type.name();
		String listByTitle= "Select * from diarylist where  `TYPE` ="+ typeq ;
		return diaryManager.search(listByTitle, self, own);
	}
	
	public ArrayList<Diary> sortByDate(Date date, User self, boolean own){
		String listByTime= "SELECT * FROM diarylist ORDER BY date DESC ";
		return diaryManager.sort(listByTime, self, own);
	}
	
	public ArrayList<Diary> searchByContent(String content, User self, boolean own){
		ArrayList<Diary> diaryList = new ArrayList<Diary>();
		diaryList.addAll(textDiaryManager.searchByContent(content, self, own));
		diaryList.addAll(imageDiaryManager.searchByContent(content, self, own));
		diaryList.addAll(voiceDiaryManager.searchByContent(content, self, own));
		Diary diary = new Diary();
		for (int i=0; i< diaryList.size();i++){
			diary = diaryManager.getDiary(diaryList.get(i));
			diaryList.get(i).setUserid(diary.getUserid());
			diaryList.get(i).setType(diary.getType());
			diaryList.get(i).setTitle(diary.getTitle());
			diaryList.get(i).setCreateDate(diary.getCreateDate());
			diaryList.get(i).setLastSaveDate(diary.getLastSaveDate());
			diaryList.get(i).setWeather(diary.getWeather());
			diaryList.get(i).setShared(diary.isShared());
		}
		
		return diaryList;
		
	
	
	}





}
	


