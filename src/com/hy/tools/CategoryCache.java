package com.hy.tools;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Hashtable;

import com.hy.objects.CategoryInfo;

public class CategoryCache {

    static private CategoryCache cache;// 一个Cache实例
    private Hashtable< String,CategoryRef> categoryRefs;// 用于Chche内容的存储
    private ReferenceQueue<CategoryInfo> q;// 垃圾Reference的队列

    // 继承SoftReference，使得每一个实例都具有可识别的标识。
      private class CategoryRef extends SoftReference<CategoryInfo> {
      public String _key = "";
      public CategoryRef(CategoryInfo info, ReferenceQueue<CategoryInfo> q) {
       super(info, q);
       _key = info.getTabaleName()+ ":" +info.getId();
     }
    }

   // 构建一个缓存器实例
     private CategoryCache() {
         categoryRefs = new Hashtable<String,CategoryRef>();
         q = new ReferenceQueue<CategoryInfo>();

    }

     // 取得缓存器实例

 public static CategoryCache getInstance() {
     if (cache == null) {
        cache = new CategoryCache();
    }
     return cache;

 }

   // 以软引用的方式对一个Employee对象的实例进行引用并保存该引用
    public void cacheCategory(CategoryInfo info) {
     cleanCache();// 清除垃圾引用
     CategoryRef ref = new CategoryRef(info, q);
     categoryRefs.put(ref._key, ref);
    }

    // 依据所指定的ID号，重新获取相应Employee对象的实例
     public CategoryInfo getCategory(String key) {
         CategoryInfo info = null;
         // 缓存中是否有该Employee实例的软引用，如果有，从软引用中取得。
        if (categoryRefs.containsKey(key)) {
            CategoryRef ref = (CategoryRef) categoryRefs.get(key);
            info = (CategoryInfo) ref.get();
        }

 // 如果没有软引用，或者从软引用中得到的实例是null，重新构建一个实例，
 // 并保存对这个新建实例的软引用

// if (info == null) {
//     info = new Employee(ID);
//     System.out.println("Retrieve From EmployeeInfoCenter. ID=" + ID);
//     this.cacheEmployee(em);
//    }
    return info;
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
