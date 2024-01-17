/**
 * 
 * @authors Bob Joy
 * @date    2024-01-16 16:42:45
 * @version $Id$
 */

// 引入 axios
import axios from "axios";
//引入 element-ui 信息
import {Message} from "element-ui";
import router from "@/router";

// 创建axios实例对象
const request = axios.create({
    baseURL: 'http://127.0.0.1:8000', // 服务器后端地址
    timeout: 1000,
})

//请求拦截器
request.interceptors.request.use(config => {
    let token = window.localStorage.getItem('token') || window.sessionStorage.getItem('token')
    if (token) {
        config.headers['Authorization'] = 'Bearer ' + token;
    }
    return config;
}, error => {
    console.log(error);
    return Promise.reject(error)
})


// 报错响应
request.interceptors.response.use(
    response => {
        // 对响应数据做处理
        return response;
    },
    error => {
        if (error.response && error.response.status === 401) {
            Message({
                type: 'warning',
                message: '尚未登录，请登录！',
                offset: 54
            })
            router.push('/login');
        } else {
            return Promise.reject(error);
        }
    }
);

//传送json格式的get请求
export const getRequest = (url) => {
    return request.get(
        `${url}`,
    )
}

//传送json格式的post请求
export const postRequest = (url, params) => {
    return request.post(
        `${url}`,
        params,
    )
}

//传送json格式的put请求
export const putRequest = (url, params) => {
    return request.put(
        `${url}`,
        params,
    )
}

//传送json格式的delete请求
export const deleteRequest = (url, params) => {
    return request.delete(
        `${url}`,
        params,
    )
}
