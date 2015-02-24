package org.p0gram3r.picarchive.util;

import java.sql.BatchUpdateException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;

import org.p0gram3r.jbulkurl.generator.GeneratedUrlHandler;
import org.p0gram3r.picarchive.dao.UrlDAO;
import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;

public class StoreUrlInDatabaseHandler implements GeneratedUrlHandler {
    private long successCount = 0, duplicateCount = 0;
    private UrlDAO dao;
    private LinkedList<String> buffer;
    private int maxBufferSize;

    public StoreUrlInDatabaseHandler(UrlDAO dao) {
        this.dao = dao;
        this.buffer = new LinkedList<String>();
        this.maxBufferSize = 500;
    }

    public long getSuccessCount() {
        return successCount;
    }

    public long getDuplicateCount() {
        return duplicateCount;
    }

    @Override
    public void handle(String generatedUrl) {
        buffer.add(generatedUrl);

        if (buffer.size() >= maxBufferSize) {
            flushBuffer();
            System.out.println("new / duplicate urls : " + successCount + " / " + duplicateCount);
        }
    }

    public void flushBuffer() {
        if (buffer.isEmpty()) {
            return;
        }

        try {
            dao.storeListOfNewUrl(buffer);
            successCount += buffer.size();
        }
        catch (UnableToExecuteStatementException e) {
            if (e.getCause() instanceof BatchUpdateException) {
                sendEachEntrySeparatelyToDao();
            }
            else {
                throw e;
            }
        }
        buffer.clear();
    }

    private void sendEachEntrySeparatelyToDao() {
        for (String url : buffer) {
            try {
                dao.storeNewUrl(url);
                successCount++;
            }
            catch (UnableToExecuteStatementException e) {
                if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                    duplicateCount++;
                }
                else {
                    throw e;
                }
            }
        }
    }
}