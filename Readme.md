R# readme

项目使用指南

本人项目为前后端开发项目，只有前端或只有后端均不能实现项目功能，体态识别部分基于 https://github.com/kennymckormick/pyskl 实现，Test02是androidstudio工程文件，mysite是django工程文件

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
    
    ![Untitled](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Untitled.png)
    
    test02为前端android代码，需用androidstudio打开，tutorial为后端代码
    
    ![Untitled](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Untitled%201.png)
    
    hrnet为体态识别算法源码，pyskl为姿态识别算法源码，tutorial为django项目的主框架Loginserver和myApp为django项目的两个app模块
    
    # 在本地运行项目
    
    用命令行进入mange.py所在的文件目录，执行
    
    ```python
    python manage.py runsslserver 127.0.0.1:8000 --cert ssl_certificate.crt
    ```
    
    即可启动后端，再进入androidstudio中修改Login2activity的urlsUploadimageactivity的url为127.0.0.1:8000，推荐用androidstudio自带的device manage预览，也可点击build→build bundles→build apks生成apk文件
    
    ![Untitled](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Untitled%202.png)
    
    # 在云服务器上运行后端项目
    
    首先确保云服务器满足安装配置，确保开放8000，443，8001，3546，90，20端口进入服务器控制台，点击远程连接
    
    ![Untitled](readme%2079f73e0f03ce40c7ae1ed175b5d422e1/Untitled%203.png)
    
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
