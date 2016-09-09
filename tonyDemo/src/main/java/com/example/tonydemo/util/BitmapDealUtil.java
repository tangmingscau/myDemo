package com.example.tonydemo.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;

/**
 * Created by tony on 16-3-8.
 */
public class BitmapDealUtil  {
    private static final String TAG = BitmapDealUtil.class.getSimpleName();
    private static final int MAX_QUALITY = 100;
    private static final int SMALL_ICON_SIZE = 32;
    private static final float SCALE_1 = 0.1f;

    public static BitmapDealUtil bitmapDealUtil = null;

    public static BitmapDealUtil getInstance() {
        if (bitmapDealUtil == null) {
            bitmapDealUtil = new BitmapDealUtil();
        }
        return bitmapDealUtil;
    }


    public final byte[] getBitmapByte(Bitmap bm) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, MAX_QUALITY, out);
        return out.toByteArray();
    }

    public final Bitmap decodeByteBitmap(byte[] bytes) {
        if (bytes != null) {
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } else {
            return null;
        }
    }

    public final Drawable decodeBitmapToDrawableIcon(Bitmap bitmap) {
        Drawable drawable = null;
        Matrix matrix = new Matrix();
        matrix.setScale(SCALE_1, SCALE_1);
        bitmap = Bitmap.createScaledBitmap(bitmap, SMALL_ICON_SIZE, SMALL_ICON_SIZE, true);
        drawable = new BitmapDrawable(null, bitmap);
        return drawable;
    }

    /**
     * 压缩图片为所指定的宽高.
     */
    public final Drawable decodeBitmapToDrawIcon(Bitmap bitmap, int width, int height) {
        Drawable drawable = null;
        if (bitmap == null) {
            return null;
        }
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        drawable = new BitmapDrawable(null, bitmap);
        return drawable;
    }

    /**
     * 根据资源id解析成byte.
     */
    public final byte[] getReasourseToByte(Context context, int id) {
        byte[] bytes = null;
        try {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);
            bytes = getBitmapByte(bitmap);
        } catch (Exception e) {
            LogUtils.e(TAG, "%s", e.getMessage());
        }
        return bytes;
    }
}
