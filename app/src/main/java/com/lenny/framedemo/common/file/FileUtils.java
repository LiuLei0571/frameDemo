package com.lenny.framedemo.common.file;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.FaceDetector;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.lenny.framedemo.project.App;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;

public class FileUtils {

    /**
     * 把图片压缩到200K
     *
     * @param oldpath
     *            压缩前的图片路径
     * @param newPath
     *            压缩后的图片路径
     * @return
     */
    /**
     * 把图片压缩到200K
     *
     * @param oldpath 压缩前的图片路径
     * @param newPath 压缩后的图片路径
     * @return
     */
    public static File compressFile(String oldpath, String newPath) {
        if (TextUtils.isEmpty(oldpath) || TextUtils.isEmpty(newPath)) {
            return null;
        }

        File f = new File(oldpath);
        if (!f.exists()) {
            return null;
        }

        Bitmap compressBitmap = FileUtils.decodeBitmapFromFile(oldpath);
        Bitmap newBitmap = ratingImage(oldpath, compressBitmap);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        newBitmap.compress(CompressFormat.PNG, 100, os);
        byte[] bytes = os.toByteArray();

        File file = null;
        try {
            file = FileUtils.getFileFromBytes(bytes, newPath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!newBitmap.isRecycled()) {
                newBitmap.recycle();
            }
            if (compressBitmap != null) {
                if (!compressBitmap.isRecycled()) {
                    compressBitmap.recycle();
                }
            }
        }
        return file;
    }

    private static Bitmap ratingImage(String filePath, Bitmap bitmap) {
        int degree = readPictureDegree(filePath);
        return rotaingImageView(degree, bitmap);
    }

    /**
     * 旋转图片
     *
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        // 创建新的图片
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 把字节数组保存为一个文件
     *
     * @param b
     * @param outputFile
     * @return
     */
    public static File getFileFromBytes(byte[] b, String outputFile) {
        File ret = null;
        BufferedOutputStream stream = null;
        try {
            ret = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(ret);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                }
            }
        }
        return ret;
    }

    /**
     * 图片压缩
     */
    public static Bitmap decodeBitmapFromFile(String imagePath) {
        if (!TextUtils.isEmpty(imagePath)) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;//不加载图片到内存，仅获得图片宽高
            BitmapFactory.decodeFile(imagePath, options);
            if (options.outHeight == -1 || options.outWidth == -1) {
                try {
                    ExifInterface exifInterface = new ExifInterface(imagePath);
                    int height = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, ExifInterface.ORIENTATION_NORMAL);//获取图片的高度
                    int width = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, ExifInterface.ORIENTATION_NORMAL);//获取图片的宽度
                    options.outWidth = width;
                    options.outHeight = height;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            options.inSampleSize = calculateInSampleSize(options);
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Config.RGB_565;
            return BitmapFactory.decodeFile(imagePath, options);

        } else {
            return null;
        }
    }

    /**
     * 计算获取新的采样率
     */
    private static int calculateInSampleSize(BitmapFactory.Options options) {
        final int REQUIRED_SIZE = 1350;
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > REQUIRED_SIZE || width > REQUIRED_SIZE) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) > REQUIRED_SIZE && (halfWidth / inSampleSize) > REQUIRED_SIZE) {
                inSampleSize *= 2;
            }

            long totalPixels = width * height / inSampleSize;

            final long totalReqPixelsCap = REQUIRED_SIZE * REQUIRED_SIZE * 2;
            while (totalPixels > totalReqPixelsCap) {
                inSampleSize *= 2;
                totalPixels /= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * 创建目录
     *
     * @param path
     */
    public static void setMkdirs(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        } else {
        }
    }

    /**
     * 获取目录名称
     *
     * @param url
     * @return FileName
     */
    public static String getFileName(String url) {
        if (TextUtils.isEmpty(url)) return new Date().getTime() + "";
        int lastIndexStart = url.lastIndexOf("/");
        if (lastIndexStart != -1) {
            return url.substring(lastIndexStart + 1, url.length());
        } else {
            return new Date().getTime() + "";
        }
    }

    /**
     * 删除该目录下的文件
     *
     * @param path
     */
    public static void delFile(String path) {
        if (!TextUtils.isEmpty(path)) {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * 删除该目录下的文件
     *
     * @param file
     */
    public static void delFile(File file) {
        if (file != null) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
    /**
     * 删除一组图片
     *
     */
    public static void delFiles(List<File> files) {
        for (File file : files) {
            if (file != null) {
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }
    public static boolean isExist(String url) {
        File file = new File(getCachePath(App.getContext()) + "/" + getFileName(url));
        return file.exists();
    }

    public static String getCachePath(@NonNull Context context) {
        File externalCacheDir = context.getExternalCacheDir();
        if (null != externalCacheDir) {
            String path = externalCacheDir.getAbsolutePath() + "/uploadImg/";
            setMkdirs(path);
            return path;
        } else {
            File cacheDir = context.getCacheDir();
            String path = cacheDir.getAbsolutePath() + "/uploadImg/";
            setMkdirs(path);
            return path;
        }
    }

    public static String getCachePath(@NonNull Context context, String fileLast) {
        File externalCacheDir = context.getExternalCacheDir();
        if (null != externalCacheDir) {
            String path = externalCacheDir.getAbsolutePath() + "/" + fileLast + "/";
            setMkdirs(path);
            return path;
        } else {
            File cacheDir = context.getCacheDir();
            String path = cacheDir.getAbsolutePath() + "/" + fileLast + "/";
            setMkdirs(path);
            return path;
        }
    }

    public static String getImgName(String fileType) {
        return new Date().getTime() + fileType;
    }

    /**
     * 写文件
     */
    public static void writeTxtFile(String content, String fileName) {
        File file = new File(getCachePath(App.getContext()), fileName);
        try {
            OutputStream outStream = new FileOutputStream(file);
            OutputStreamWriter out = new OutputStreamWriter(outStream);
            out.write(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将byte数组写入文件
     */
    public static void writeTxtFile(byte[] content, String fileName) {
        String filePath = getCachePath(App.getContext()) + fileName;
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(content);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static final float TARGET = 0.511f;  //脸部特征值基准

    /**
     * 人脸检测
     */
    public static boolean checkFace(int minFace, String filePath) {
        if (minFace <= 0) { //至少一张脸
            minFace = 1;
        }

        if (minFace > 10) { //至多10张脸
            minFace = 10;
        }

        int count; //识别出的人脸张数
        Bitmap b = BitmapFactory.decodeFile(filePath);
        Bitmap faceBitmap = b.copy(Config.RGB_565, true);
        b.recycle();
        int faceWidth = faceBitmap.getWidth();
        int faceHeight = faceBitmap.getHeight();
        FaceDetector fd;
        FaceDetector.Face[] faces = new FaceDetector.Face[minFace];

        try {
            fd = new FaceDetector(faceWidth, faceHeight, minFace);
            count = fd.findFaces(faceBitmap, faces);
        } catch (Exception e) {
            return false;
        }

        float faceMean = 0.0f;

        if (count >= minFace) {
            for (int i = 0; i < count; i++) {
                try {
                    faceMean = faceMean + faces[i].confidence();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (faceMean / count >= TARGET) {
                return true;
            }
        }

        return false;
    }

    /**
     * 照片压缩存放路径
     */
    public static String getAxdImgPath(Context context) {
        return getCachePath(context) + getImgName(".jpg");
    }
    public static String getAxdImgPath(Context context,int number) {
        return getCachePath(context) + number+getImgName(".jpg");
    }
}
