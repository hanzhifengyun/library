package com.hanzhifengyun.library.data;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.List;

/**
 * 数据库数据
 */
public class FengyunBaseSQLiteData extends LitePalSupport implements Serializable {

    private long id;

    private static final String WHERE_BASE_ID = " = ?";

    protected <T> T getDataFromSQLite(String keyId, Class<T> modelClass) {
        try {
            return LitePal.where(keyId + WHERE_BASE_ID, String.valueOf(id)).findFirst(modelClass);
        } catch (Exception e) {
            return null;
        }
    }

    protected <T> List<T> getDataListFromSQLite(String keyId, Class<T> modelClass) {
        try {
            return LitePal.where(keyId + WHERE_BASE_ID, String.valueOf(id)).find(modelClass);
        } catch (Exception e) {
            return null;
        }
    }

    protected void deleteFromSQLite(Class<?> modelClass) {
        try {
            LitePal.delete(modelClass, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
