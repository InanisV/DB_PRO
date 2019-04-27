"""
filename: jd/spider.py
python: 3.7.0
description: 使用selenium搜索京东书籍
"""
#import for normal use
import random
import csv

#import for selenium
from selenium.webdriver import Chrome
from config import *
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.action_chains import ActionChains
from time import sleep
from selenium.common.exceptions import NoSuchElementException

urls = []
prices = []

def next_page(client, wait, page_num):

    # 确认完整加载网页，下拉到底部
    while len(client.find_elements_by_class_name('gl-item')) < 60:
        client.execute_script('window.scrollTo(0, document.body.scrollHeight)')
        sleep(1)
    print("[+] 第{}加载完成".format(page_num))

    # 解析数据
    parse_page(page_num, client)

    # 下一页
    page_num += 1
    if page_num > END_PAGE:
        print('前{}页爬取成功'.format(END_PAGE))
        return

    # 等待下一页输入框加载完成
    wait.until(
        EC.presence_of_element_located(
            (By.CSS_SELECTOR, '#J_bottomPage > span.p-skip > input')
        )
    )
    # print("[+] 下一页输入框加载完成")

    # 等待下一页输入框跳页按钮加载完成
    wait.until(
        EC.element_to_be_clickable(
            (By.CSS_SELECTOR, '#J_bottomPage > span.p-skip > a')
        )
    )
    # print("[+] 跳页按钮加载完成")

    # 输入页码
    input_ = client.find_element_by_css_selector('#J_bottomPage > span.p-skip > input')
    input_.clear()
    input_.send_keys(page_num)
    # print("[+] 输入页码完成")

    # 点击跳页
    input_.send_keys(Keys.ENTER)
    # print("[+] 点击跳页完成")

    # 等待下一页加载完成
    wait.until(
        EC.text_to_be_present_in_element(
            (By.CSS_SELECTOR, '#J_bottomPage > span.p-num > a.curr'),
            str(page_num)
        )
    )
    # print("[+] 下一页加载完成")

    # 跳下一页
    next_page(client, wait, page_num)


def parse_page(page_num, client):

    print("[+] 开始解析第{}页数据".format(page_num))
    items = client.find_elements_by_class_name('gl-item')
    index = 1

    global urls
    global prices
    for item in items:
        print("[{}] ".format(index), end="")
        # 在前面的基础上继续解析, 如果那个属性没有提取到，保证其他属性可以正常提取
        try:
            price = item.find_element_by_css_selector("div.p-price > strong > i").text
        except NoSuchElementException:
            price = None
        try:
            url = item.find_element_by_css_selector("div.p-img > a").get_attribute("href")
        except NoSuchElementException:
            url = None
        print("{}".format(url))
        urls.append(url)
        prices.append(price)
        index += 1

    print("[+] 解析第{}页数据完成".format(page_num))

def get_info(client, writer, cnt):
    items = client.find_element_by_css_selector("[class='parameter2 p-parameter-list']").find_elements_by_tag_name("li")
    # for item in items:
    try:
        name = items[0].get_attribute("title")
    except NoSuchElementException:
        name = None
    try:
        o_place = items[3].get_attribute("title")
    except NoSuchElementException:
        o_place = None
    p_time = random.randint(3, 30) * 10
    volume = random.randint(1, 3)
    r_condition = random.randint(0, 1)
    global prices
    price = prices[cnt]
    discount = 1
    catagory = "milk"
    writer.writerow({"产品名称": name, "原产地": o_place, "保质期": p_time, "体积": volume, "是否冷藏": r_condition, "价格": price, "折扣": discount, "类别": catagory})

def search(client, url, keyword,wait):
    # 打开链接
    client.get(url)
    # 等待加载输入框完成,等待id为q的加载
    wait.until(
        EC.presence_of_element_located(
            (By.ID, 'key')
        )
    )
    # print("[+] 搜索框加载完成")

    # 等待加载搜索按钮完成,等待css选择器满足条件,
    wait.until(
        EC.element_to_be_clickable(
            (By.CSS_SELECTOR, '#search > div > div.form > button > i')
        )
    )
    # print("[+] 搜索按钮加载完成")

    # 输入关键字
    input_ = client.find_element_by_id('key')
    input_.send_keys(keyword)
    # print("[+] 输入关键字完成")

    # 点击搜索
    botton = client.find_element_by_css_selector('#search > div > div.form > button > i')
    botton.click()
    print("[+] 点击搜索完成")

    # 准备csv
    csvFile = open("goods.csv", "w", encoding='utf-8', newline='')
    fileHeader = ["产品名称", "原产地", "保质期", "体积", "是否冷藏", "价格", "折扣", "类别"]
    writer = csv.DictWriter(csvFile, fileHeader)

    # 翻页
    page_num = 1
    next_page(client, wait, page_num)

    cnt = 0
    for site in urls:
        client.get(site)
        wait = WebDriverWait(client, 10)
        wait.until(
            EC.presence_of_element_located(
                (By.CSS_SELECTOR, "[class='parameter2 p-parameter-list']")
            )
        )
        get_info(client, writer, cnt)
        cnt = cnt + 1

    csvFile.close()

def main():
    #struct 定义

    # 创建一个浏览器
    client = Chrome()
    url = "http://www.jd.com"
    # 等待对象
    wait = WebDriverWait(client, 10)
    search(client, url, KEYWORD, wait)

if __name__ == '__main__':
    main()