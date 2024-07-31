package util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Create by zoush on 2022/10/24.
 * 756350775@qq.com
 */
public class ImageUtil {

    /**
     * 将图片转换成Base64编码的字符串
     */
    public static String imageToBase64(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try {
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data, Base64.NO_CLOSE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

    /**
     * 将Base64编码转换为图片
     *
     * @param base64Str
     * @param path
     * @return true
     */
    static byte[] data;

    public static boolean base64ToFile(String base64Str, String path) {
        try {
            data = Base64.decode(base64Str, Base64.NO_WRAP);
            for (int i = 0; i < data.length; i++) {
                if (data[i] < 0) {
                    //调整异常数据
                    data[i] += 256;
                }
            }
            OutputStream os = null;
            String SDPATH = "/sdcard";//Environment.getExternalStorageDirectory().getAbsolutePath();
            File navifolder = new File(SDPATH + "/face");
            if (!navifolder.exists() && !navifolder.isDirectory()) {
                navifolder.mkdirs();
            }
            os = new FileOutputStream(SDPATH + "/face/" + path);
            os.write(data);
            os.flush();
            os.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    static Bitmap decodedByte;
    static byte[] decodedString;

    /**
     * 将Base64编码转换为图片Bitmap
     */
    public static Bitmap base64ToBitmap(String base64Str) {
        decodedString = Base64.decode(base64Str, Base64.DEFAULT);
        decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;//matrixBitmap(decodedByte);
    }

    /**
     * 放大2倍
     *
     * @param bitmap
     * @return
     */
    public static Bitmap matrixBitmap(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(2f, 2f);
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizeBmp;
    }

    public static boolean saveBitmap(Context context, Bitmap bitmap, String path, Bitmap.CompressFormat format, int quality) {
        if (bitmap == null) {
            return false;
        }
        FileOutputStream out = null;
        try {
            String SDPATH = "/sdcard";//Environment.getExternalStorageDirectory().getAbsolutePath();
            File navifolder = new File(SDPATH + "/face");
            if (!navifolder.exists() && !navifolder.isDirectory()) {
                navifolder.mkdirs();
            }
            out = new FileOutputStream(path);
            boolean compress = bitmap.compress(format, quality, out);
            out.flush();

            //这里记得必须加入更新图片
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File savefile = new File(path);
            if (savefile.exists() && savefile.length() > 0) {
                Uri uri = Uri.fromFile(savefile);
                intent.setData(uri);
                context.sendBroadcast(intent);
            }
            return compress;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static Bitmap getPriviewPic(byte[] data, Camera camera) {//这里传入的data参数就是onpreviewFrame中需要传入的byte[]型数据
        Camera.Size previewSize = camera.getParameters().getPreviewSize();//获取尺寸,格式转换的时候要用到
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        YuvImage yuvimage = new YuvImage(
                data,
                ImageFormat.NV21,
                previewSize.width,
                previewSize.height,
                null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        yuvimage.compressToJpeg(new Rect(0, 0, previewSize.width, previewSize.height), 100, baos);// 80--JPG图片的质量[0-100],100最高
        byte[] rawImage = baos.toByteArray();
        //将rawImage转换成bitmap
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeByteArray(rawImage, 0, rawImage.length, options);
        return bitmap;
    }

    public static Bitmap adjustPhotoRotation(Bitmap bm, final int orientationDegree) {

        Matrix m = new Matrix();
        m.setRotate(orientationDegree, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
        float targetX, targetY;
        if (orientationDegree == 90) {
            targetX = bm.getHeight();
            targetY = 0;
        } else {
            targetX = bm.getHeight();
            targetY = bm.getWidth();
        }

        final float[] values = new float[9];
        m.getValues(values);

        float x1 = values[Matrix.MTRANS_X];
        float y1 = values[Matrix.MTRANS_Y];

        m.postTranslate(targetX - x1, targetY - y1);

        Bitmap bm1 = Bitmap.createBitmap(bm.getHeight(), bm.getWidth(), Bitmap.Config.ARGB_8888);

        Paint paint = new Paint();
        Canvas canvas = new Canvas(bm1);
        canvas.drawBitmap(bm, m, paint);


        return bm1;
    }

    /**
     * 二进制数据转Bitmap
     */

    public static Bitmap getBitmapFromByte(byte[] temp) {
        if (temp != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
            return bitmap;
        } else {
            return null;
        }

    }

    /**
     * 将图片转成byte数组
     *
     * @param bitmap 图片
     * @return 图片的字节数组
     */
    public static byte[] bitmap2Byte(Bitmap bitmap) {
        if (null == bitmap) throw new NullPointerException();
        // if (null == bitmap) return null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //把bitmap100%高质量压缩 到 output对象里
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        return outputStream.toByteArray();
    }

    /**
     * 将图片转成byte数组
     *
     * @param imageByte 图片
     * @return Base64 String
     */
    public static String byte2Base64(byte[] imageByte) {
        if (null == imageByte) return null;
        return Base64.encodeToString(imageByte, Base64.DEFAULT);
    }

    public static String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
