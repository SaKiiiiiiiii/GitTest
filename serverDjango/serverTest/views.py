import json
import sys
import os
from django.shortcuts import render
from django.shortcuts import HttpResponse
sys.path.append(os.getcwd() + '/baiduFace')
from .baiduFace import videoToPic
# Create your views here.
def index(request):
    return HttpResponse('Hello World!')


def post(request):
    if request.method == 'POST':  # 当提交表单时
        faces = ["低头", "抬头", "交头接耳"]
        received_json_data = json.loads(request.body)
        print(received_json_data)
        Data = received_json_data['Data']
        # str = Data['a'] + Data['b']
        print("收到识别请求")
        array_of_data = videoToPic.videoToPic()
        # array_of_data = [[3, 1, 0, 0, 0, 0, 0, 0, 4, 0], [4, 0, 0, 0, 0, 0, 0, 0, 4, 1], [1, 3, 0, 0, 0, 0, 0, 0, 4, 1], [0, 3, 0, 0, 0, 0, 0, 1, 2, 3], [1, 1, 0, 0, 1, 0, 0, 0, 3, 0], [2, 1, 0, 0, 0, 0, 0, 0, 3, 0], [1, 0, 0, 0, 0, 0, 0, 0, 1, 0], [3, 2, 0, 0, 0, 0, 0, 0, 5, 1], [2, 0, 0, 0, 0, 1, 1, 0, 4, 1], [3, 0, 0, 0, 0, 1, 0, 0, 4, 0], [1, 1, 0, 0, 0, 0, 1, 0, 3, 0], [1, 4, 0, 0, 0, 0, 0, 0, 5, 1], [1, 2, 0, 0, 0, 0, 0, 0, 3, 1], [3, 1, 0, 0, 0, 0, 0, 2, 2, 1], [1, 3, 0, 0, 0, 0, 0, 0, 4, 1], [4, 0, 0, 0, 0, 0, 0, 0, 4, 2], [1, 1, 0, 0, 0, 0, 0, 0, 2, 0], [1, 3, 0, 0, 0, 0, 0, 0, 4, 1]]


        print(array_of_data)
        i = 0
        strs = "共有3位同学\n"
        for data in array_of_data:

            i = i + 1
            strs = strs + '第' + str(i) + "帧中"
            if data[0] != 0:
                strs = strs + "，有" + str(data[0]) + "位同学在笑"
            for j in range(7, 10):
                if data[j] != 0:
                    strs = strs + "，有" + str(data[j]) + "位同学在" + faces[j-7]
            strs = strs + '\n'
        print(strs)
    return HttpResponse(strs)

def check(request):
    if request.method == 'POST':  # 当提交表单时

        received_json_data = json.loads(request.body)
        print(received_json_data)
        Data = received_json_data['Data']
        # str = Data['a'] + Data['b']
        print("收到点到请求")
        str = "到场的同学有：\n"
        array_of_data = videoToPic.process_fig()
        for data in array_of_data:
            str = str + data + '\n'
        print(str)

    return HttpResponse(str)




def state(request):
    if request.method == 'POST':  # 当提交表单时
        received_json_data = json.loads(request.body)
        # print(received_json_data)
        Data = received_json_data['Data']
        str = Data
        print(str)
    return HttpResponse(str)
