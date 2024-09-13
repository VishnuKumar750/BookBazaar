package com.bookBazaar.service;

import java.util.List;

import com.bookBazaar.ServiceInterface.IDownloadService;
import com.bookBazaar.daoImp.DownloadDao;
import com.bookBazaar.daoInterface.IDownloadDao;
import com.bookBazaar.entity.Download;

public class DownloadService implements IDownloadService {
	private IDownloadDao downloadDao;
	
	public DownloadService() {
		this.downloadDao = new DownloadDao(); 
	}
	

	@Override
    public List<Download> getAllDownloadLogs() {
        return downloadDao.getAllDownloadLogs();
    }

	@Override
    public List<Download> getDownloadLogsByUserId(int userId) {
        return downloadDao.getDownloadLogsByUserId(userId);
    }
}
