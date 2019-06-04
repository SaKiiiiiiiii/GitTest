#!/usr/bin/env python
# -*- coding: utf-8 -*-

import cv2
import argparse

import sys
import  os
from tkinter import *
from tkinter import filedialog
import tkinter as tk
# sys.path.append(os.getcwd() + '/baiduFace')
from .faceDectectDemo import BaiduPicIndentify
from .faceMatch import BaiduFaceMatch
#import re

def parse_args(path):
    """
    Parse input arguments
    """
    #path = entry.get()
    #获取处理的视频名称,并创建文件夹
    end = "/"
    name = path[path.rfind(end):]
    name = name [1:]
    name = name [:-4]
    if not os.path.exists(name):
        os.mkdir(name)

    parser = argparse.ArgumentParser(description='Process pic')
    parser.add_argument('--input', help='video to process', dest='input', default=None, type=str)
    parser.add_argument('--output', help='pic to store', dest='output', default=None, type=str)
    # default为间隔多少帧截取一张图片
    parser.add_argument('--skip_frame', dest='skip_frame', help='skip number of video', default=100, type=int)
    # input为输入视频的路径 ，output为输出存放图片的路径
    args = parser.parse_args(
        ['--input', path, '--output', name])
    return args


def process_video(i_video, o_video, num):
    cap = cv2.VideoCapture(i_video)
    num_frame = cap.get(cv2.CAP_PROP_FRAME_COUNT)
    expand_name = '.jpg'
    if not cap.isOpened():
        print("Please check the path.")
    cnt = 0
    count = 0
    while 1:
        ret, frame = cap.read()
        cnt += 1
        #  how
        # many
        # frame
        # to
        # cut
        if cnt % num == 0:
            count += 1
            cv2.imwrite(os.path.join(o_video, str(count) + expand_name), frame)

        if not ret:
            break

def videoToPic():
    #视频--图片
    path = "/Users/tony/学习/Android/serverDjango/serverTest/baiduFace/software.mp4"
    args = parse_args(path)
    if not os.path.exists(args.output):
        os.makedirs(args.output)
    print('Called with args:')
    print(args)
    process_video(args.input, args.output, args.skip_frame)
    #识别
    end = "/"
    name = path[path.rfind(end):]
    name = name [1:]
    name = name [:-4]
    array_of_img = read_directory(name)
    array_of_data = []
    for i in range(len(array_of_img)):
        print(array_of_img[i])
        img_src = array_of_img[i]
        #  if img_src == 'exit':
        #      sys.exit()
        faceDetect = BaiduPicIndentify(name, img_src)
        array_of_data.append(faceDetect.detect_face())
    print(array_of_data)
    return array_of_data

# def upload_file():
#     selectFile = tk.filedialog.askopenfilename()  # askopenfilename 1次上传1个；askopenfilenames1次上传多个
#     entry.delete(0,END)
#     entry.insert(0, selectFile)

def read_directory(directory_name):
    array_of_img = [] # this if for store all of the image data
    # this loop is for read each image in this foder,directory_name is the foder name with images.
    for filename in os.listdir(r"./"+directory_name):

        array_of_img.append(filename)
    #print(array_of_img)
    return array_of_img

def process_fig():

    faceMatch = BaiduFaceMatch('p1.jpg')
    arrived_student = faceMatch.match_face()
    print(arrived_student)
    return arrived_student


#
#
# videoToPic()
# process_fig()



#
# root = Tk()
# root.geometry('550x400+550+230')
# root.title('智慧教室系统')
# #root.iconbitmap('play.jpg')
# #标签控件
# lable = Label(root,text='请选择要处理的课程视频:',font=('微软雅黑',10))
# lable.grid(row=0,column=0)
# # #选择文件
# # button2 = Button(root,text='...',width=10,font=('微软雅黑',10),command=upload_file)
# # button2.grid(row=0,column=2,sticky=E)
# # #输入控件
# # entry =Entry(root,width=20,font=('微软雅黑',12))
# # entry.grid(row=0,column=1)
# #text = Listbox(root,font=('微软雅黑',16),width=45,height=10)
# # columnspan组件所跨越的列数
# #text.grid(row=1,columnspan=2)
# #按钮控件
# button = Button(root,text='视频处理',width=10,font=('微软雅黑',10),command=videoToPic)
# button.grid(row=2,column=0,sticky=W)
# #按钮控件
# button3 = Button(root,text='点到',width=10,font=('微软雅黑',10),command=process_fig)
# button3.grid(row=3,column=0,sticky=W)
#
# root.mainloop()
#
#
