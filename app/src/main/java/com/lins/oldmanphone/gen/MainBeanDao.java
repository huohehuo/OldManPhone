package com.lins.oldmanphone.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lins.oldmanphone.bean.MainBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MAIN_BEAN".
*/
public class MainBeanDao extends AbstractDao<MainBean, Long> {

    public static final String TABLENAME = "MAIN_BEAN";

    /**
     * Properties of entity MainBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Sex = new Property(2, String.class, "sex", false, "SEX");
        public final static Property Bgurl = new Property(3, String.class, "bgurl", false, "BGURL");
        public final static Property Phone = new Property(4, String.class, "phone", false, "PHONE");
        public final static Property Weixin = new Property(5, String.class, "weixin", false, "WEIXIN");
        public final static Property Zhifubao = new Property(6, String.class, "zhifubao", false, "ZHIFUBAO");
        public final static Property Qq = new Property(7, String.class, "qq", false, "QQ");
        public final static Property Sina = new Property(8, String.class, "sina", false, "SINA");
        public final static Property Taobao = new Property(9, String.class, "taobao", false, "TAOBAO");
    };


    public MainBeanDao(DaoConfig config) {
        super(config);
    }
    
    public MainBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MAIN_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"SEX\" TEXT," + // 2: sex
                "\"BGURL\" TEXT," + // 3: bgurl
                "\"PHONE\" TEXT," + // 4: phone
                "\"WEIXIN\" TEXT," + // 5: weixin
                "\"ZHIFUBAO\" TEXT," + // 6: zhifubao
                "\"QQ\" TEXT," + // 7: qq
                "\"SINA\" TEXT," + // 8: sina
                "\"TAOBAO\" TEXT);"); // 9: taobao
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MAIN_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MainBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(3, sex);
        }
 
        String bgurl = entity.getBgurl();
        if (bgurl != null) {
            stmt.bindString(4, bgurl);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(5, phone);
        }
 
        String weixin = entity.getWeixin();
        if (weixin != null) {
            stmt.bindString(6, weixin);
        }
 
        String zhifubao = entity.getZhifubao();
        if (zhifubao != null) {
            stmt.bindString(7, zhifubao);
        }
 
        String qq = entity.getQq();
        if (qq != null) {
            stmt.bindString(8, qq);
        }
 
        String sina = entity.getSina();
        if (sina != null) {
            stmt.bindString(9, sina);
        }
 
        String taobao = entity.getTaobao();
        if (taobao != null) {
            stmt.bindString(10, taobao);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MainBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(3, sex);
        }
 
        String bgurl = entity.getBgurl();
        if (bgurl != null) {
            stmt.bindString(4, bgurl);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(5, phone);
        }
 
        String weixin = entity.getWeixin();
        if (weixin != null) {
            stmt.bindString(6, weixin);
        }
 
        String zhifubao = entity.getZhifubao();
        if (zhifubao != null) {
            stmt.bindString(7, zhifubao);
        }
 
        String qq = entity.getQq();
        if (qq != null) {
            stmt.bindString(8, qq);
        }
 
        String sina = entity.getSina();
        if (sina != null) {
            stmt.bindString(9, sina);
        }
 
        String taobao = entity.getTaobao();
        if (taobao != null) {
            stmt.bindString(10, taobao);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public MainBean readEntity(Cursor cursor, int offset) {
        MainBean entity = new MainBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // sex
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // bgurl
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // phone
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // weixin
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // zhifubao
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // qq
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // sina
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // taobao
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MainBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSex(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setBgurl(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPhone(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setWeixin(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setZhifubao(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setQq(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setSina(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setTaobao(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(MainBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(MainBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}