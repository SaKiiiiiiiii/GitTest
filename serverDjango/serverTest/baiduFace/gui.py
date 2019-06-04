# -*- coding:utf-8 -*-
from tkinter import *
from tkinter import filedialog
import tkinter as tk
import os
import urllib
import  requests
from faceMatch import BaiduFaceMatch
from faceDectectDemo import BaiduPicIndentify

def upload_file():
    selectFile = tk.filedialog.askopenfilename()  # askopenfilename 1次上传1个；askopenfilenames1次上传多个
    entry.insert(0, selectFile)

def facematch():
    img = entry.get()
   # img += '.jpg'
    baidufaceMatch = BaiduFaceMatch(img)
    baidufaceMatch.match_face()

#def facedect():


root = Tk()
root.geometry('550x400+550+230')
root.title('智慧教室系统')
#root.iconbitmap('play.jpg')
#标签控件
lable = Label(root,text='请选择要处理的课程视频:',font=('微软雅黑',10))
lable.grid(row=0,column=0)
#选择文件
button2 = Button(root,text='...',width=10,font=('微软雅黑',10),command=upload_file)
button2.grid(row=0,column=2,sticky=E)
#输入控件
entry =Entry(root,width=20,font=('微软雅黑',12))
entry.grid(row=0,column=1)
#text = Listbox(root,font=('微软雅黑',16),width=45,height=10)
# columnspan组件所跨越的列数
#text.grid(row=1,columnspan=2)
#按钮控件
button = Button(root,text='课程点到',width=10,font=('微软雅黑',10),command=facematch)
button.grid(row=2,column=0,sticky=W)

#button1 = Button(root,text='退出',width=10,font=('微软雅黑',10),command=root.quit)
#button1.grid(row=2,column=1,sticky=E)

button3 = Button(root,text='上课情况分析',width=10,font=('微软雅黑',10),command=facematch)
button3.grid(row=3,column=0,sticky=W)


root.mainloop()