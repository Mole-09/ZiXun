package zixun.xcb.example.sdk.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import zixun.xcb.example.sdk.base.config.DBConfig;

/*
   数据库工具类
 */
public class DBUtils {
    public static final String CREATE_TABLE_IF_NOT_EXISTS ="create table if not exists %s"+
            "(id integer primary key autoincrement,key text unique,is_read integer)";

    private static DBUtils sDBUtils;
    private SQLiteDatabase mSQLiteDatabase;

    private DBUtils(Context context){
        mSQLiteDatabase=new DBHelper(context, DBConfig.DB_NAME+".db").getWritableDatabase();

    }

    public static synchronized DBUtils getDB(Context context){
        if(sDBUtils == null){
            synchronized (DBUtils.class){
                if(sDBUtils == null)
                    sDBUtils = new DBUtils(context);
            }
        }
        return sDBUtils;
    }

    /*
        插入一个item已读状态到数据表
        @param table 数据表名
        @param key key值
        @param value 数据值
        @return 插入结果
     */
    public boolean insertRead(final String table,String key,int value){
        Cursor cursor=mSQLiteDatabase.query(table,null,null,null,null,null,"id sec");
        //最多缓存200条
        if(cursor.getCount()>200 && cursor.moveToNext()){
            mSQLiteDatabase.delete(table,"id=?",new String[]{String.valueOf(cursor.getInt(cursor.getColumnIndex("id")))});
        }
        cursor.close();
        final ContentValues contentValues = new ContentValues();
        contentValues.put("key",key);
        contentValues.put("is_read",value);
        return (mSQLiteDatabase.insertWithOnConflict(table,null,contentValues,SQLiteDatabase.CONFLICT_REPLACE)>0);
    }

    /*
        判断item是否已经阅读过
        @param table 数据表名
        @param key key值
        @param value
        @return
     */
    public boolean isRead(String table,String key,int value){
        boolean isRead=false;
        Cursor cursor=mSQLiteDatabase.query(table,null,"key=?",new String[]{key},null,null,null);
        if(cursor.moveToNext()&&(cursor.getInt(cursor.getColumnIndex("is_read"))==value)){
            isRead=true;
        }
        cursor.close();
        return isRead;
    }

    public class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context,String name){
            super(context,name,null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            //onCreate()方法只有数据库第一次被创建时才会被调用，若数据库已经存在，此方法不会被调用
        }

        @Override
        public void onOpen(SQLiteDatabase db){
            super.onOpen(db);
            //数据库打开时就会被调用，将插入新表的操作方法到onOpen中
            db.execSQL(String.format(CREATE_TABLE_IF_NOT_EXISTS,DBConfig.TABLE_ZHIHU));
            db.execSQL(String.format(CREATE_TABLE_IF_NOT_EXISTS,DBConfig.TABLE_WANGYI));
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
            //只有数据库进行版本升级时被调用0
        }
    }


}