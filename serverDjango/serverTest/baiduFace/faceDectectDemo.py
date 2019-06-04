# encoding:UTF-8

import base64
import json
import requests
import sys
import cv2
import os
from aip import AipFace



# this function is for read image,the input is directory name
def read_directory(directory_name):
    array_of_img = [] # this if for store all of the image data
    # this loop is for read each image in this foder,directory_name is the foder name with images.
    for filename in os.listdir(r"./"+directory_name):

        array_of_img.append(filename)
    #print(array_of_img)
    return  array_of_img


class BaiduPicIndentify:

    def __init__(self, folder, img):
        self.AK = "tRGiY4wwhB34acaGldYxDijA"

        self.SK = "BHgyOXMGdgcDoGSoldAnDjHnKQoxUeau"

        self.folder = folder
        self.img_src = img

        self.headers = {

            "Content-Type": "application/json; charset=UTF-8"

        }

    def get_accessToken(self):
        host = 'https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=' + self.AK + '&client_secret=' + self.SK

        response = requests.get(host, headers=self.headers)

        json_result = json.loads(response.text)

        return json_result['access_token']

    def img_to_BASE64(slef, folder, path):
        path = folder + "/" + path
        with open(path, 'rb') as f:
            base64_data = base64.b64encode(f.read())

            return base64_data

    def emotion_judge(self,data):
        emotion = data['type']
        probability = data['probability']
        if probability > 0.5:
            return emotion

    def angle_judge(self,data):
        if data > 30 :
            return True
        else:
            return False

    def rl_judge(self,data):
        if data > 30 or data < -30 :
            return True
        else:
            return False

    def emotion_cal(self, emotion,array_of_emotion):

        if emotion == 'happy':
            array_of_emotion[0] += 1
        if emotion == 'neutral':
            array_of_emotion[1] += 1
        if emotion == 'disgust':
            array_of_emotion[2] += 1
        if emotion == 'surprise':
            array_of_emotion[3] += 1
        if emotion == 'angry':
            array_of_emotion[4] += 1
        if emotion == 'sad':
            array_of_emotion[5] += 1
        if emotion == 'fear':
            array_of_emotion[6] += 1

    def detect_face(self):
        # 人脸检测与属性分析

        img_BASE64 = self.img_to_BASE64(self.folder, self.img_src)

        request_url = "https://aip.baidubce.com/rest/2.0/face/v3/detect"

        post_data = {

            "image": img_BASE64,

            "image_type": "BASE64",

            "face_field": "gender,age,beauty,gender,race,expression,emotion",

            "max_face_num": 10,

            "face_type": "LIVE"

        }

        access_token = self.get_accessToken()

        request_url = request_url + "?access_token=" + access_token

        response = requests.post(url=request_url, data=post_data, headers=self.headers)

        json_result = json.loads(response.text)

        if json_result['error_msg'] != 'pic not has face':
            num_jtje = 0
            num_dttt = 0
            num_happy = 0
            num_angry = 0
            num_disgust = 0
            num_fear = 0
            num_sad = 0
            num_surprise = 0
            num_netural = 0
            #数组10位数字分别代表7种表情、低头人数、抬头人数、交头接耳人数
            array_of_emotion = [0, 0, 0, 0, 0, 0, 0, 0, 0,0]
            print("图片中包含", json_result['result']['face_num'], "张脸")

            for i in range(json_result['result']['face_num']):
                print("第",i+1,"位表情: ", json_result['result']['face_list'][i]['emotion']['type'])
                self.emotion_cal(json_result['result']['face_list'][i]['emotion']['type'],array_of_emotion)

                print("第", i + 1, "俯仰角度：", json_result['result']['face_list'][i]['angle']['yaw'])
                if self.rl_judge(json_result['result']['face_list'][i]['angle']['yaw']):
                    print("该同学交头接耳")
                    array_of_emotion[9] += 1
                else:
                    print("正常")

                data = json_result['result']['face_list'][i]['emotion']
               # print(data)
                emotion = self.emotion_judge(data)

                if self.angle_judge(json_result['result']['face_list'][i]['angle']['pitch']):
                    print("该同学低头")
                    array_of_emotion[7] += 1
                else:
                    print("该同学抬头")
                    array_of_emotion[8] += 1

        return array_of_emotion



if __name__ == '__main__':
    folder = input('输入处理的课程名：')
    array_of_img = read_directory(folder)
    array_of_data = []
    for i in range(len(array_of_img)):
        print(array_of_img[i])
        img_src = array_of_img[i]
      #  if img_src == 'exit':
      #      sys.exit()
        baiduDetect = BaiduPicIndentify(folder, img_src)
        array_of_data.append(baiduDetect.detect_face())
    print(array_of_data )

    sys.exit()


