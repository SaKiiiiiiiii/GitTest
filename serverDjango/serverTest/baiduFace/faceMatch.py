from aip import AipFace
import requests
import base64
import json
import cv2
import sys

class BaiduFaceMatch:
    def __init__(self, img):
        self.AK = "tRGiY4wwhB34acaGldYxDijA"
        self.SK = "BHgyOXMGdgcDoGSoldAnDjHnKQoxUeau"
        self.img_src = img
        self.headers = {
            "Content-Type": "application/json; charset=UTF-8"
        }

    def get_accessToken(self):
        host = 'https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=' + self.AK + '&client_secret=' + self.SK
        response = requests.get(host, headers=self.headers)
        json_result = json.loads(response.text)
        return json_result['access_token']

    def img_to_BASE64(slef, path):
      #  path = "faceset\\" + path
        with open(path, 'rb') as f:
            base64_data = base64.b64encode(f.read())
            return base64_data

    def score_judge(self, score):
        if score > 80:
            return True
        else:
            return False

    def name_judge(self, username):
        if username == 'zxy':
            return '朱心媛'
        if username == 'frg':
            return '方睿鸽'
        if username == 'nhh':
            return '倪茴茴'
        if username == 'yyb':
            return '郁祎斌'
        if username == 'zs':
            return '张帅'
        if username == 'tn':
            return '童宁'
        if username == 'ysj':
            return '虞胜杰'
        if username == 'wlx':
            return '王力鑫'
        if username == 'ljr':
            return '娄嘉瑞'

    def student_id(self, username):
        if username == 'zxy':
            return '201526810532'
        if username == 'frg':
            return '201526810501'
        if username == 'nhh':
            return '201526810502'
        if username == 'yyb':
            return '201526810503'
        if username == 'zs':
            return '201526810504'
        if username == 'tn':
            return '2111812079'
        if username == 'ysj':
            return '2111812155'
        if username == 'wlx':
            return '2111812164'
        if username == 'ljr':
            return '2111812169'

    def match_face(self):

        arrived_student = []
        #人脸对比
        img_BASE64 = self.img_to_BASE64(self.img_src)
        request_url = "https://aip.baidubce.com/rest/2.0/face/v3/multi-search"
        post_data = {
            "image": img_BASE64,
            "image_type": "BASE64",
            "max_face_num": 10,
            "group_id_list": "student",
            "max_user_num":10
        }
        access_token = self.get_accessToken()

        request_url = request_url + "?access_token=" + access_token

        response = requests.post(url=request_url, data=post_data, headers=self.headers)

        json_result = json.loads(response.text)

        if json_result['error_msg'] != 'pic not has face':
            print("图片中包含", json_result['result']['face_num'], "张脸")
            image = cv2.imread(self.img_src)
            for j in range(len(json_result['result']['face_list'])):
                face_rectangle =json_result['result']['face_list'][j]['location']
                width = int(face_rectangle['width'])
                top = int(face_rectangle['top'])
                left = int(face_rectangle['left'])
                height = int(face_rectangle['height'])
                start = (left, top)
                end = (left + width, top + height)
                color = (55, 255, 155)
                thickness = 3
                cv2.rectangle(image, start, end, color, thickness)
            # cv2.imshow('点到', image)
            # cv2.waitKey(0)
            for n in range(len(json_result['result']['face_list'])):
                for i in range(len(json_result['result']['face_list'][n]['user_list'])):
                    if self.score_judge(json_result['result']['face_list'][n]['user_list'][i]['score']):
                        print(self.name_judge(json_result['result']['face_list'][n]['user_list'][i]['user_id']),"到了")
                        arrived_student.append((self.name_judge(json_result['result']['face_list'][n]['user_list'][i]['user_id'])+','+self.student_id(json_result['result']['face_list'][n]['user_list'][i]['user_id'])))

        return arrived_student










if __name__ == '__main__':
    img_src =input('图片名')
    baiduDetect = BaiduFaceMatch(img_src)
    arrived_student = baiduDetect.match_face()
    print(arrived_student)

