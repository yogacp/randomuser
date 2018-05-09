package app.randomuser.tabsquare.vo.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USERS_DATA".
*/
public class UsersDataDao extends AbstractDao<UsersData, Long> {

    public static final String TABLENAME = "USERS_DATA";

    /**
     * Properties of entity UsersData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Resultid = new Property(0, Long.class, "resultid", true, "_id");
        public final static Property Data = new Property(1, String.class, "data", false, "DATA");
        public final static Property Page = new Property(2, String.class, "page", false, "PAGE");
        public final static Property LastUpdated = new Property(3, Long.class, "lastUpdated", false, "LAST_UPDATED");
    }


    public UsersDataDao(DaoConfig config) {
        super(config);
    }
    
    public UsersDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USERS_DATA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: resultid
                "\"DATA\" TEXT," + // 1: data
                "\"PAGE\" TEXT," + // 2: page
                "\"LAST_UPDATED\" INTEGER);"); // 3: lastUpdated
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_USERS_DATA__id ON \"USERS_DATA\"" +
                " (\"_id\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USERS_DATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UsersData entity) {
        stmt.clearBindings();
 
        Long resultid = entity.getResultid();
        if (resultid != null) {
            stmt.bindLong(1, resultid);
        }
 
        String data = entity.getData();
        if (data != null) {
            stmt.bindString(2, data);
        }
 
        String page = entity.getPage();
        if (page != null) {
            stmt.bindString(3, page);
        }
 
        Long lastUpdated = entity.getLastUpdated();
        if (lastUpdated != null) {
            stmt.bindLong(4, lastUpdated);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UsersData entity) {
        stmt.clearBindings();
 
        Long resultid = entity.getResultid();
        if (resultid != null) {
            stmt.bindLong(1, resultid);
        }
 
        String data = entity.getData();
        if (data != null) {
            stmt.bindString(2, data);
        }
 
        String page = entity.getPage();
        if (page != null) {
            stmt.bindString(3, page);
        }
 
        Long lastUpdated = entity.getLastUpdated();
        if (lastUpdated != null) {
            stmt.bindLong(4, lastUpdated);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UsersData readEntity(Cursor cursor, int offset) {
        UsersData entity = new UsersData( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // resultid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // data
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // page
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3) // lastUpdated
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UsersData entity, int offset) {
        entity.setResultid(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setData(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPage(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setLastUpdated(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UsersData entity, long rowId) {
        entity.setResultid(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UsersData entity) {
        if(entity != null) {
            return entity.getResultid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UsersData entity) {
        return entity.getResultid() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
