# readme

项目使用指南

本人项目为前后端开发项目，只有前端或只有后端均不能实现项目功能，体态识别部分基于 [https://github.com/kennymckormick/pyskl](https://github.com/kennymckormick/pyskl)  实现，Test02是androidstudio工程文件，mysite是django工程文件

# 项目简介

![Snipaste_2023-06-04_15-58-42.png](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Snipaste_2023-06-04_15-58-42.png)

# 具体介绍

## 前端

### 开发框架介绍（预留，会有整体框架解析）

androidstudio

开发语言：Java，xml，js，html

![Snipaste_2023-06-04_16-00-37.png](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Snipaste_2023-06-04_16-00-37.png)

### 功能模块（这几个功能模块都放一页PPT）

- 开机动画
    
    ![2023-06-04_16-21-40.gif](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/2023-06-04_16-21-40.gif)
    
- 登录页面
    
    ![Snipaste_2023-06-04_16-25-40.png](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Snipaste_2023-06-04_16-25-40.png)
    
- 主页
    
    ![EBCD269D64C42C30DFE630A514D75AEE.jpg](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/EBCD269D64C42C30DFE630A514D75AEE.jpg)
    
- 体态分析
    
    ![9E3982BE045FB4DCA297D22EE80020D2.jpg](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/9E3982BE045FB4DCA297D22EE80020D2.jpg)
    
- 姿态分析
    
    ![553821B332F74591CF6F7235B925E2A6.jpg](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/553821B332F74591CF6F7235B925E2A6.jpg)
    
- 个人中心
    
    ![57312845661E888095890FD5F9E5506D.jpg](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/57312845661E888095890FD5F9E5506D.jpg)
    
- 设置
    
    ![FB6689EF32709E625E773C450C73505A.jpg](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/FB6689EF32709E625E773C450C73505A.jpg)
    

### 关键代码解析

两页ppt，图片太长就把每个函数分出来，挨个解析（解析内容之后我补充，预留）

![Untitled](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Untitled.png)

![Untitled](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Untitled%201.png)

## 后端

### 开发框架介绍

阿里云esc云服务器介绍 一页PPT

![Untitled](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Untitled%202.png)

![Untitled](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Untitled%203.png)

![Untitled](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Untitled%204.png)

nginx [Nginx 是什么、为什么、怎么用? - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/364588916)

uwsgi [uWSGI详解 - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/36448645)

用这两个链接里的内容解释一下这两东西的作用，每个一页PPT

- 使用和配置
    
    ![Untitled](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Untitled%205.png)
    
    ![Untitled](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Untitled%206.png)
    
    ![Untitled](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Untitled%207.png)
    

django内容，下图是代码文件结构，介绍。。。。[Django框架的项目结构详细解析_django 项目组成部分剖析_**kwargs的博客-CSDN博客](https://blog.csdn.net/liuxingxing_star/article/details/103963125)

![Untitled](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Untitled%208.png)

pyskl 介绍 （预留）

![Untitled](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Untitled%209.png)

HRnet（徐莘源补充）（预留）

### 功能实现（每个功能一页PPT）

- 登录
    
    流程图（预留）
    
- 体态分析算法（基于HRnet）
    
    徐莘源补充（预留）
    
- 姿态识别算法（基于pyskl）
    
    demoselekton解析（预留）
    

### 关键代码解析（留三页PPT）

![Untitled](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Untitled%2010.png)

## 开发环境

- 前端
    - Androidstudio 版本：Android Studio Electric Eel | 2022.1.1 Patch 2
    - 浏览器：Google
    - 开发语言：java，js，xml，html
- 后端
    - 阿里云esc云服务器 系统：Ubuntu 20.04 64位 （linux）
    - nginx 1.18.0
    - uwsgi 2.0.21
    - django 4.1
    - python 3.9.16
    - Mysql 8.0.33-0ubuntu0.20.04.2 for Linux on x86_64 ((Ubuntu))
    - pytorch 2.0.1
    
    # 项目文件结构
    
    ![Untitled](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Untitled%2011.png)
    
    test02为前端android代码，需用androidstudio打开，tutorial为后端代码
    
    ![Untitled](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Untitled%2012.png)
    
    hrnet为体态识别算法源码，pyskl为姿态识别算法源码，tutorial为django项目的主框架Loginserver和myApp为django项目的两个app模块
    
    # 在本地运行项目
    
    用命令行进入mange.py所在的文件目录，执行
    
    ```python
    python manage.py runsslserver 127.0.0.1:8000 --cert ssl_certificate.crt
    ```
    
    即可启动后端，再进入androidstudio中修改Login2activity的urlsUploadimageactivity的url为127.0.0.1:8000，推荐用androidstudio自带的device manage预览，也可点击build→build bundles→build apks生成apk文件
    
    ![Untitled](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Untitled%2013.png)
    
    # 在云服务器上运行后端项目
    
    首先确保云服务器满足安装配置，确保开放8000，443，8001，3546，90，20端口进入服务器控制台，点击远程连接
    
    ![Untitled](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Untitled%2014.png)
    
    使用以下指令查看当前是否运行uwsgi和nginx,如果运行了nginx就将其stop
    
    ```python
    ps aux|grep uwsgi
    ps aux|grep nginx
    
    nginx -s stop
    ```
    
    之后cd进入Django_upload,配置nignx和uwsgi
    
    ```python
    nginx -c mysite.conf
    uwsgi --ini uwsgi.ini
    ```
    
    最后再在前端配置url为你自己的url即可