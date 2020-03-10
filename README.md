menu.setMode(SlidingMenu.LEFT);//设置左滑菜单
menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置滑动的屏幕范围，该设置为全屏区域都可以滑动
menu.setShadowDrawable(R.drawable.shadow);//设置阴影图片
menu.setShadowWidthRes(R.dimen.shadow_width);//设置阴影图片的宽度
menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//SlidingMenu划出时主页面显示的剩余宽度
menu.setBehindWidth(400);//设置SlidingMenu菜单的宽度
menu.setFadeDegree(0.35f);//SlidingMenu滑动时的渐变程度
menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);//使SlidingMenu附加在Activity上
menu.setMenu(R.layout.menu_layout);//设置menu的布局文件
menu.toggle();//动态判断自动关闭或开启SlidingMenu
menu.showMenu();//显示SlidingMenu
menu.showContent();//显示内容
menu.setOnOpenListener(onOpenListener);//监听slidingmenu打开
关于关闭menu有两个监听，简单的来说，对于menu close事件，一个是when,一个是after
menu.OnClosedListener(OnClosedListener);//监听slidingmenu关闭时事件
menu.OnClosedListener(OnClosedListener);//监听slidingmenu关闭后事件

左右都可以划出SlidingMenu菜单只需要设置
menu.setMode(SlidingMenu.LEFT_RIGHT);属性，然后设置右侧菜单的布局文件
menu.setSecondaryShadowDrawable(R.drawable.shadowright);//右侧菜单的阴影图片

设置SlidingMenu属性
sm = getSlidingMenu();
//如果只显示左侧菜单就是用LEFT,右侧就RIGHT，左右都支持就LEFT_RIGHT
sm.setMode(SlidingMenu.LEFT_RIGHT);//设置菜单滑动模式，菜单是出现在左侧还是右侧，还是左右两侧都有
sm.setShadowDrawable(R.drawable.shadow);//设置阴影的图片资源
sm.setShadowWidthRes(R.dimen.shadow_width);//设置阴影图片的宽度
//sm.setBehindWidth(200);//设置菜单的宽
sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);//SlidingMenu划出时主页面显示的剩余宽度
sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置滑动的区域

支持右侧划出菜单:
//SlidingMenu可以同时支持划出左右两侧的菜单，互不冲突，而且动画优美，体验良好。
sm.setSecondaryMenu(R.layout.menu_frame2);//设置右侧菜单
sm.setSecondaryShadowDrawable(R.drawable.shadowright);//设置右侧菜单阴影的图片资源
//右侧SlidingMenu的Fragment
getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame2, new SampleListFragment()).commit();

slidingMenu = getSlidingMenu();
//设置是左滑还是右滑，还是左右都可以滑
slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
//设置阴影宽度
slidingMenu.setShadowWidth(getWindowManager().getDefaultDisplay().getWidth() / 40);
//设置左菜单阴影图片
slidingMenu.setShadowDrawable(R.drawable.shadow);
//设置右菜单阴影图片
slidingMenu.setSecondaryShadowDrawable(R.drawable.right_shadow);
//设置菜单占屏幕的比例
slidingMenu.setBehindOffset(getWindowManager().getDefaultDisplay().getWidth() / 5);
//设置滑动时菜单的是否淡入淡出
slidingMenu.setFadeEnabled(true);
//设置淡入淡出的比例
slidingMenu.setFadeDegree(0.4f);
//设置滑动时拖拽效果
slidingMenu.setBehindScrollScale(0);
//设置要使菜单滑动，触碰屏幕的范围
slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);


当设置滑动屏幕范围为
slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

菜单内侧滑却无法关闭
menu.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);

当添加1中代码后菜单中内侧按钮失效

解决方案：

1、找到CustomViewAbove.java,搜索onTouchEvent函数，找到MotionEvent.ACTION_DOWN，修改break为return mQuickReturn;

2、找到CustomViewBehind.java,搜索onInterceptTouchEvent函数，将原先的：return !mChildrenEnabled;替换为：

return mViewAbove.onInterceptTouchEvent(e);

3、找到CustomViewBehind.java,搜索

onTouchEvent函数，将原先的：

return !mChildrenEnabled;替换为：

return mViewAbove.onTouchEvent(e);

4、找到你的菜单布局文件，在根布局上添加属性：

android:clickable="true"即可。

3.SlidingMenu实现沉浸式状态栏

找到SlidingMenu.java，将

private boolean mActionbarOverlay = false;

修改为true即可。