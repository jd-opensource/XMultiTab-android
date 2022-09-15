# XMultiTab-android

---

## 1）项目介绍

**导航多Tab容器开源库**

**支持自定义：**

- 导航Tab子View 
- 导航Tab数量以及默认选中位置  
- 导航Tab高度以及异形Tab等

## 2）演示效果

**运行app模块下DEMO工程或者直接安装app目录下`app-debug.apk`安装包查看效果**

![](https://img13.360buyimg.com/imagetools/jfs/t1/190090/26/28199/565488/6322d2eaE2b0dfeb3/f7494a797c4fc184.gif)

## 3）使用说明

### 3.1、多Tab容器

##### 容器类
> MultiTabNavigationLayout   com.mumu.mutitab.MultiTabNavigationLayout

##### 使用示例

1.**与普通布局使用方式相同，在XML中定义或者代码动态创建即可**
```
 <com.mumu.mutitab.MultiTabNavigationLayout
        android:id="@+id/bottomLayout"
        android:layout_width="0dp"
        android:layout_height="50dp"
        android:clipChildren="false"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent" />
```

2.**支持配置**
```
  /**
     * 设置是否支持以选中Tab点击继续回调，默认不支持
     */
    fun setEnableRepeatCallBack(enableRepeatCallBack: Boolean) {
        ...
    }

    /**
     * 设置Tab切换监听
     */
    fun setTabChangedListener(tabChangedListener: OnTabChangedListener) {
        ...
    }
        
```

##### 注意事项
1.**如需支持异性Tab请设置MultiTabNavigationLayout容器及其父容器的`clipChildren`属性为`false`**

### 3.2 适配器--通用模式

##### 适配器类
> NavigationAdapter   com.mumu.mutitab.NavigationAdapter

##### 使用示例
1.**必选配置方法：**
```
abstract class NavigationAdapter {


    /**
     * 导航Tab View数量
     */
    abstract fun getCount(): Int

    /**
     * 默认选中位置
     */
    abstract fun getSelectIndex(): Int
    
    
      /**
     * 实例化单个TabView
     *
     * @param viewGroup The containing View .
     * @param position The  position to be instantiated.
     * @return View
     *
     */
    abstract fun createItemView(viewGroup: ViewGroup, position: Int): View    
    
}
```
2.**可选配置方法：**
```
abstract class NavigationAdapter {


   /**
     * 导航条背景色，默认白色，重写该方法改变导航条颜色
     */
    open fun getBackgroundColor(): Int {
       ...
    }

    /**
     * 重置Layout，宽度等分，高度充满
     * 重写该方法修改子View LayoutParams，如果子View没有LayoutParams需要自行创建，否则默认依然是等分
     * 注意如果想要使用异形Tab可以在此方法中设置异形Tab高度
     */
    open fun resetLayoutParams(itemView: View) {
       ...
    }

    /**
     * 是否等分，默认宽度等分，高度充满
     */
    open fun isEqually(): Boolean {
        return true
    }
    
    /**
     * 选中状态改变调用
     * 如果子View实现Checkable接口，则会调用Checkable的setChecked方法，如果没有实现可以选择在此方法中改变View状态
     * @param checked 选中true，反选false
     */
    open fun setChecked(itemView: View, checked: Boolean) {

    }
}
```

3.**使用参考（具体可参考如下两个类）**

> 简单模式适配器类：
>> SimpleNavigationAdapter   com.mumu.mutitab.SimpleNavigationAdapter

> 简单模式容器子View：
>> SimpleItemView   com.mumu.mutitab.SimpleItemView

### 3.3 适配器--简单模式

##### 简单模式适配器类
> SimpleNavigationAdapter   com.mumu.mutitab.SimpleNavigationAdapter

##### 简单模式容器子View
> SimpleItemView   com.mumu.mutitab.SimpleItemView

##### 使用示例

```
        //设置Tab切换回调监听
        mBottomLayout.setTabChangedListener(object : OnTabChangedListener {
            override fun onChanged(position: Int, isFromClick: Boolean) {
                mContentText.text = position.toString()
            }

        })
        //使用简单模式，只支持加载图片，需要创建IDisplayImage对象加载图片
        val display = object : IDisplayImage {
            override fun displayImage(imageUrl: String?, view: View) {
                JDImageLoader.display(imageUrl, view)
            }
        }
        //简单模式适配器
        val adapter = object : SimpleNavigationAdapter<TabData>(data, display) {
            override fun getSelectIndex(): Int {
                return 1
            }
        }
        mBottomLayout.setAdapter(adapter)
       
```
##### 说明

1.**简单模式下已经实现了createItemView方法默认创建的ImageView，以及getSelectIndex方法默认选中位置0**

2.**简单模式下使用SimpleNavigationAdapter仅需传递数据源集合（必须实现`ITabItem`接口）和 实现图片加载加载，详细使用可以参考Demo `MainActivity类`**

3.**简单模式如需自定义其他配置使用方式和普通模式下一致，具体可参考`3.2适配器--通用模式`相关配置方法**







