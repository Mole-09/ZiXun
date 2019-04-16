package zixun.xcb.example.sdk.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/*
   图片工具类
 */
public class ImageUtils {

    /*
        拍照
     */
    public static final int REQUEST_CODE_FROM_CAMERA =1<<10;

    /*
       相册
     */
    public static final int REQUEST_CODE_FROM_ALBUM = 1<<12;

    /*
       裁剪
     */
    public static final int REQUEST_CODE_CROP_IMAGE =1<<14;

    /*
       存放拍照图片的uri地址
     */
    public static Uri imageUriFromCamera;

    /*
       存放裁剪图片的Uri地址
     */
    public static Uri cropImageUri;

    /*
       显示获取照片的不同方式对话框
     */
    public static void showImagePickDialog(final Activity activity){
        showImagePickDialog(activity,0);
    }

    /*
       显示获取照片不同方式对话框
     */
    public static void showImagePickDialog(final Activity activity,final int addRequest){
        String title ="选择获取图片方式";
        String[] items= new String[]{"拍照","相册"};
        new AlertDialog.Builder(activity).setTitle(title).setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        pickImageFromCamera(activity,addRequest);break;
                    case 1:
                        pickImageFromAlbum(activity,addRequest);break;
                        default:
                            break;
                }
            }
        }).setNegativeButton("取消",null).show();
    }

    /*
        打开相机拍照获取图片
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void pickImageFromCamera(final Activity activity,int addRequest){
        //先生成一个Uri地址用于存放拍照获取的图片
        imageUriFromCamera = createImageUri(activity);
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUriFromCamera);
        activity.startActivityForResult(intent,REQUEST_CODE_FROM_CAMERA+addRequest);
    }

    /*
       打开相机拍照获取图片
     */
    public static void pickImageFromCamera(final Activity activity){
        pickImageFromCamera(activity,0);
    }

    /*
       打开本地相册选取照片
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void pickImageFromAlbum(final Activity activity,int addRequest){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(intent,REQUEST_CODE_FROM_ALBUM+addRequest);
    }

    /*
       打开本地相册选取图片
     */
    public static void pickImageFromAlbum(final Activity activity){
        pickImageFromAlbum(activity,0);
    }

    /*
       压缩照片
       @param context context
       @param uri 图片Uri
       @param reqW 上传图片需要压缩的宽度
       @param reqH 上传图片需要压缩的高度
       @return 上传成功回调
     */
    public static Observable<byte[]> compressImage(final Context context, Uri uri, final int reqW, final int reqH){
        //先从本地获取图片，利用Glide压缩图片后获取byte[]
        return Observable.just(uri).flatMap(new Function<Uri, ObservableSource<byte[]>>() {
            @Override
            public ObservableSource<byte[]> apply(Uri uri) throws Exception {
                //在work线程中，同步压缩图片，然后Observable返回
                //即将Glide的回调封装成RxJava的Observable
                FutureTarget<byte[]> future = Glide.with(context).load(uri).asBitmap().toBytes().into(reqW,reqH);
                byte[] bytes;
                try{
                    bytes = future.get();
                }catch (Exception e){
                    //获取失败时，抛出runtime异常
                    //该异常会被Subscribe捕捉，进onError
                    throw  new RuntimeException(e);
                }
                return Observable.just(bytes);
            }
        });
    }

    /*
        图片剪裁
     */
    public static void cropImage(Activity activity,Uri srcUri){
        cropImageUri = createImageUri(activity);

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(srcUri,"image/*");
        intent.putExtra("crop","true");

        //宽高和比例都不设置时，裁剪框可以自行调整
        //只设置裁剪框宽高比（aspect）时，裁剪框比例固定不可调整，只能调整大小
        //裁剪后的生成图片宽高（output）的设置和裁剪框无关，只决定最终生成图片大小
        //裁剪框宽高比例可以和裁剪后生成图片的比例不同，此时已裁剪框的宽为准，按照裁剪宽高比例生成一个图片，改图和框选部分
        //可能不同，不同的情况可能是截取相框的一部分，也可能超出框选部分，向下延伸补足

        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);

        // outputX outputY 是裁剪后生成图片的宽高
        //		intent.putExtra("outputX", 300);
        //		intent.putExtra("outputY", 100);
        // return-data为true时,会直接返回bitmap数据,但是大图裁剪时会出现OOM,推荐下面为false时的方式
        // return-data为false时,不会返回bitmap,但需要指定一个MediaStore.EXTRA_OUTPUT保存图片uri

        intent.putExtra("return-data",false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,cropImageUri);
        activity.startActivityForResult(intent,REQUEST_CODE_CROP_IMAGE);
    }

    /*
       创建一条图片Uri，用于保存拍照后的图片
     */
    private static Uri createImageUri(Context context){
        String name = "boreImg"+System.currentTimeMillis();
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,name);
        values.put(MediaStore.Images.Media.DISPLAY_NAME,name+".jpg");
        values.put(MediaStore.Images.Media.MIME_TYPE,"image/jpg");
        Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        return uri;
    }

    /*
        删除一条图片
     */
    public static void deleteImageUri(Context context,Uri uri){
        context.getContentResolver().delete(uri,null,null);
    }

    /*
       用第三方应用app打开图片
     */
    public static void openImageByOtherApp(Context context,Uri imageUri){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(imageUri,"image/*");
        context.startActivity(intent);
    }

    /*
       根据Uri获取图片绝对路径，解决Android4.4以上版本Uri转换
     */
    public static String getImageAbsolutePath19(Context context,Uri imageUri){
        if(context==null||imageUri==null){
            return null;
        }
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context,imageUri)){
            if(isExternalStorageDocument(imageUri)){
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String types=split[0];
                if("primary".equalsIgnoreCase(types)){
                    return Environment.getExternalStorageDirectory()+"/"+split[1];
                }
            }else if(isDownloadsDocument(imageUri)){
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(id));
                return getDataColumn(context ,contentUri,null,null);
            }else if(isMediaDocument(imageUri)){
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split= docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if("image".equals(type)){
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                }else if("video".equals(type)){
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                }else if("audio".equals(type)){
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Video.Media._ID+"=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context,contentUri,selection,selectionArgs);
            }
        }

        //MediaStrore
        if("content".equalsIgnoreCase(imageUri.getScheme())){
            //Return the remotr address
            if(isGooglePhotoUri(imageUri)){
                return imageUri.getLastPathSegment();
            }
            return getDataColumn(context,imageUri,null,null);
        }

        //File
        else if("file".equalsIgnoreCase(imageUri.getScheme())){
            return imageUri.getPath();
        }
        return null;
    }


    private static String getDataColumn(Context context,Uri uri,String selection,String[] selectionArgs){
        Cursor cursor=null;
        String column = MediaStore.Video.Media.DATA;
        String[] projection = {column};
        try{
            cursor = context.getContentResolver().query(uri,projection,selection,selectionArgs,null);
            if(cursor !=null && cursor.moveToFirst()){
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        }finally {
            if(cursor!=null)
                cursor.close();
        }
        return null;
    }

    /*
        @param uri 要检测的Uri
        @return 这个Uri是否是由外部提供
     */
    private static boolean isExternalStorageDocument(Uri uri){
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /*
       @param Uri  要检测Uri
       @return
     */
    private static boolean isDownloadsDocument(Uri uri){
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /*
        @param Uri
        @return MediaProvider
     */
    public static boolean isMediaDocument(Uri uri){
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /*
        @param Uri
        @return
     */
    public static boolean isGooglePhotoUri(Uri uri){
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

}
