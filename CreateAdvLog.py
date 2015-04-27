#!/usr/bin/env python
#coding:utf-8
'''
Created on 20150423

@author: dell
'''
#String log = "20140117    03    183.19.140.101    2014-01-17 03:44:51    {EFBA4702-B9B2-929A-B76D-BC4AA4E84942}    5.32.1227.1111    716    917    {\"wid\":\"13\",\"aid\":\"101853\",\"vid\":\"1450739\",\"adid\":\"32540\",\"asid\":\"1\",\"aspid\":\"1\",\"mid\":\"16771\",\"ptime\":\"\",\"mg\":\"15,16,43,71,93,107,148,153,159,160,162,170,173,183,193\",\"ag\":\"4,20,28,104,157,213,1869,1909\",\"ecode\":\"0\",\"type\":\"1\",\"dpl\":\"0\",\"adpid\":\"0\",\"dsp\":\"0\"}    中国    广东省    肇庆市    NULL";
import os
import time
import uuid
import socket
import random
from random import randint
#print time.time()
#print time.localtime(time.time())
#currenttime = time.time()
#currentdate = time.strftime('%Y%m%d',time.localtime(currenttime))
#print currentdate
stringTab='	'
#currentdatetime = time.strftime('%Y%m%d %H-%M-%S',time.localtime(currenttime))
#print currentdatetime
#uuidstr=uuid.uuid4()
#print uuidstr
#localIP = socket.gethostbyname(socket.gethostname())
#print type(localIP)
#print localIP
#randomint = randint(1,255)
#print randomint
#randomip = '%d.%d.%d.%d' % (randint(1,255),randint(1,255),randint(1,255),randint(1,255))
#print randomip
provience=['河南省','河北省','山西省','山东省','吉林省','黑龙江省','辽宁省','内蒙古','新疆','北京','厦门']#11
citys=['安阳市','林州市','邯郸市','邢台市','保定市','新乡市','郑州市','开封市','乌鲁木齐市']#9
logfilepath = raw_input('请输入要监控的日志文件:')
logfile = open(logfilepath,'a')
count=10000000
while(count >0):
    count=count-1
    stringlog=''
    currenttime = time.time()
    currentdate = time.strftime('%Y%m%d',time.localtime(currenttime))
    stringlog=stringlog+currentdate+stringTab+'03'+stringTab
    randomip = '%d.%d.%d.%d' % (randint(1,255),randint(1,255),randint(1,255),randint(1,255))
    stringlog+=randomip+stringTab
    currentdatetime = time.strftime('%Y%m%d %H-%M-%S',time.localtime(currenttime))
    stringlog+=currentdatetime+stringTab
    uuidstr=str(uuid.uuid4())

    uuidstr='{'+uuidstr+'}'
    stringlog+=uuidstr+stringTab
    stringfixed='5.32.1227.1111	716	917'
    stringlog+=stringfixed+stringTab;
    stringJson1='{\"wid\":\"13\",\"aid\":\"101853\",\"vid\":\"1450739\",\"adid\":\"'
    randomInt =randint(1,100000)
    stringJson1+=str(randomInt)
    stringlog+=stringJson1
    stringJson2='\",\"asid\":\"1\",\"aspid\":\"1\",\"mid\":\"16771\",\"ptime\":\"\",\"mg\":\"15,16,43,71,93,107,148,153,159,160,162,170,173,183,193\",\"ag\":\"4,20,28,104,157,213,1869,1909\",\"ecode\":\"0\",\"type\":\"1\",\"dpl\":\"0\",\"adpid\":\"0\",\"dsp\":\"0\"}'
    stringlog+=stringJson2+stringTab
    stringlog+='中国'+stringTab
    randomInt =randint(0,10)
    stringlog+=provience[randomInt]+stringTab
    stringlog+=citys[randint(0,8)]+stringTab
    stringlog+='NULL'
    #print stringlog
    #logfile.write(stringlog)
    logfile.write(stringlog+'\r\n')
logfile.close()
    

    
    
