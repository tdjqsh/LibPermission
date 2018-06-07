#LibPermission

#使用AspectJ封装的android动态权限申请库

#集成方法：<br/>
  1：下载源码<br/>
  2：导入其中的libpermission库(注意：不是permissionlib，这个库是用来对比的测试的）<br/>
  3：在app的gradle里面加入：apply from : '../libpermission/libpermission.gradle'，不用在项目设置里面进行module依赖<br/>
  ![image](https://github.com/tdjqsh/LibPermission/blob/master/images/1.png)
  <br>
  4：在需要用到权限申请调用上写上权限检查的注解<br>
  ![image](https://github.com/tdjqsh/LibPermission/blob/master/images/3.png)
  5：很重要的，所有需要权限申请的activity，继承LPBaseActivity，建议下一个基类来统一下<br>
  ![image](https://github.com/tdjqsh/LibPermission/blob/master/images/2.png)<br>
  6：也是最重要的一点，我没有在fragment中测试使用，根据经验，<b>会报错。</b><br>
  
  <br><br><br><br>
  #<u>一次尝试，写的也不是很好。欢迎大侠大佬大神大哥小妹交流：tdqjsh@163.com</u>
