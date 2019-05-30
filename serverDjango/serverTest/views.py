import json

from django.shortcuts import render
from django.shortcuts import HttpResponse
# Create your views here.
def index(request):
    return HttpResponse('Hello World!')


def post(request):
    if request.method == 'POST':  # 当提交表单时

        received_json_data = json.loads(request.body)
        print(received_json_data)
        Data = received_json_data['Data']
        str = Data['a'] + Data['b']
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
