import axios from 'axios';
import qs from 'qs';
import { Message, Loading } from 'element-ui'
import Vue from 'vue'
// let $loadingInstance = null

let loading //定义loading变量
//使用Element loading-start 方法
function startLoading() {
    loading = Vue.prototype.$loading({
        text: 'Loading',
        // fullscreen: false,
        // target: document.querySelector('.view-box')
    })
}

//使用Element loading-close 方法
function endLoading() {
    loading.close()
}
//那么 showLoading() hideLoading() 要干的事儿就是将同一时刻的请求合并。
//声明一个变量 count，每次调用showLoading方法 count + 1。
//调用hideLoading()方法，count - 1。count为 0 时，结束 loading。
let count = 0
// 开始loading
function showLoading() {
    if (count === 0) {
        startLoading()
    }
    count++
}
// 结束loading
function hideLoading() {
    if (count < 0) return
    count--
    if (count === 0) {
        endLoading()
    }
}

axios.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded' // application/x-www-form-urlencoded

// 创建axios实例
const http = axios.create({
    // baseURL: '/api', //api的base_url
    baseURL: 'http://localhost:8081/api', //api的base_url dev
    timeout: 6000000, //请求超时时间
    withCredentials: false, //跨域携带cookie
})
http.interceptors.request.use(
    config => {
        config.data = qs.stringify(config.data)
        return config;
    },
    err => {
        hideLoading()
        return Promise.reject(err);
    }
)

// 添加响应拦截器，这里的response是接受服务器返回后的结果，也可以在这里做状态判断
http.interceptors.response.use(
    response => {
        /**
         * 判断服务器请求是否成功
         * @method if
         * @param  {[type]} response [description]
         * @return {[type]}          [description]
         */
        hideLoading()
        if (response.data.code) {
            if (response.data.code != 200 ) {
                Message({
                    message: `${response.data.msg}`,
                    type: 'warning'
                })
            }
        }

        return response
    },
    err => {
        hideLoading()
        return Promise.reject(err)
    }
)

export default http