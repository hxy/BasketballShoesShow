package com.hy.tools;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Hashtable;

import com.hy.objects.CategoryObject;

public class CategoryCache {

    static private CategoryCache cache;// 一个Cache实例
    private Hashtable< String,CategoryRef> categoryRefs;// 用于Chche内容的存储
    private ReferenceQueue<CategoryObject> q;// 垃圾Reference的队列

    // 继承SoftReference，使得每一个实例都具有可识别的标识。
      private class CategoryRef extends SoftReference<CategoryObject> {
      public String _key = "";
      public CategoryRef(String key,CategoryObject object, ReferenceQueue<CategoryObject> q) {
       super(object, q);
       this._key = key;
     }
    }

   // 构建一个缓存器实例
     private CategoryCache() {
         categoryRefs = new Hashtable<String,CategoryRef>();
         q = new ReferenceQueue<CategoryObject>();

    }

     // 取得缓存器实例

 public static CategoryCache getInstance() {
     if (cache == null) {
        cache = new CategoryCache();
    }
     return cache;

 }

   // 以软引用的方式对一个Employee对象的实例进行引用并保存该引用
    public void cacheCategory(String key,CategoryObject object) {
     cleanCache();// 清除垃圾引用
     CategoryRef ref = new CategoryRef(key,object, q);
     categoryRefs.put(key, ref);
    }

    // 依据所指定的ID号，重新获取相应Employee对象的实例
     public CategoryObject getCategory(String key) {
         CategoryObject object = null;
         // 缓存中是否有该Employee实例的软引用，如果有，从软引用中取得。
        if (categoryRefs.containsKey(key)) {
            CategoryRef ref = (CategoryRef) categoryRefs.get(key);
            object = (CategoryObject) ref.get();
        }

 // 如果没有软引用，或者从软引用中得到的实例是null，重新构建一个实例，
 // 并保存对这个新建实例的软引用

// if (object == null) {
//     object = new Employee(ID);
//     System.out.println("Retrieve From EmployeeobjectCenter. ID=" + ID);
//     this.cacheEmployee(em);
//    }
    return object;
    }

 private void cleanCache() {
     CategoryRef ref = null;
    while ((ref = (CategoryRef) q.poll()) != null) {
        categoryRefs.remove(ref._key);
    }
    }

 // 清除Cache内的全部内容

 public void clearCache() {
    cleanCache();
     categoryRefs.clear();
     System.gc();
     System.runFinalization();
    }
}
