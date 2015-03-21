/*
 * 
 */
package com.hoangdv.spywareclient;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class LruBitmapCache extends LruCache<String, Bitmap> implements
		ImageCache {

	public static int getDefaultLruCacheSize() {
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		final int cacheSize = maxMemory / 8;
		return cacheSize;
	}

	public LruBitmapCache() {
		super(getDefaultLruCacheSize());
	}

	public LruBitmapCache(int sizeInKiloBytes) {
		super(sizeInKiloBytes);
	}

	@Override
	protected int sizeOf(String key, Bitmap value) {
		// TODO Auto-generated method stub
		return value.getRowBytes() * value.getHeight() / 1024;
	}

	@Override
	public Bitmap getBitmap(String arg0) {
		// TODO Auto-generated method stub
		return get(arg0);
	}

	@Override
	public void putBitmap(String arg0, Bitmap arg1) {
		// TODO Auto-generated method stub
		put(arg0, arg1);
	}

}
