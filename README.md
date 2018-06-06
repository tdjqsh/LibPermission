#LibPermission

#使用AspectJ封装的android动态权限申请库

#集成方法：<br/>
  1：下载源码<br/>
  2：导入其中的libpermission库(注意：不是permissionlib，这个库是用来对比的测试的）<br/>
  3：在app的gradle里面加入：apply from : '../libpermission/libpermission.gradle'，不用在项目设置里面进行module依赖<br/>
  ![image](https://github.com/tdjqsh/LibPermission/blob/master/images/1.png)
  <br>
  4：在需要用到权限申请调用上写上权限检查的注解<br>
  
