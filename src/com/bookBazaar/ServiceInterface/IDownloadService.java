package com.bookBazaar.ServiceInterface;

import java.util.List;

import com.bookBazaar.entity.Download;

public interface IDownloadService {
	List<Download> getAllDownloadLogs();
	List<Download> getDownloadLogsByUserId(int userId);
}
