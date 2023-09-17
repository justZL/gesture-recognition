from django.db import models

# Create your models here.
class Login(models.Model):
    ID=models.AutoField(primary_key=True)
    username=models.CharField(max_length=25,verbose_name='用户名')
    password=models.CharField(max_length=25,verbose_name='密码')
    adminname=models.CharField(max_length=25,verbose_name='用户姓名')