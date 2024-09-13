package com.bookBazaar.daoInterface;

import java.util.List;

import com.bookBazaar.entity.Download;

public interface IDownloadDao {
	List<Download> getAllDownloadLogs();
	List<Download> getDownloadLogsByUserId(int userId);
}
