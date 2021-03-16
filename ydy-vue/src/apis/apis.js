/**
 * @author Mr.peng
 * @description 该文件为接口请求方法的集成文件,以及请求的相关配置
 */
import axios from 'axios'
import apiUrls from './api-urls.js'
import { Loading } from 'element-ui';
import Cookies from "js-cookie"
import qs from 'qs';

axios.defaults.headers.post['Content-Type'] = 'application/json' //请求头
axios.defaults.headers.Accept = 'application/json' //请求头
axios.defaults.baseURL = '/api'
axios.defaults.withCredentials = true
axios.defaults.timeout = 60000

//loading效果,因为loading是单例，所以多次请求时，只有一个loading,只有都请求完成后，关闭loading,通过totalApis来判断多请求
let totalApis = 0
let load
// 请求拦截器
axios.interceptors.request.use(function (config) {
    totalApis++
    //加载框
    if (totalApis > 0) {
        load = Loading.service({ text: '请求中...' });
    }

    let token = Cookies.get('AuthToken')
    if (token) {//后端获取的header头部字段是 Authorization
        config.headers.Authorization = token
    }

    return config
}, function (error) {
    return Promise.reject(error)
})
// 响应拦截器
// 目前http 200会走response, 不是200都是走的error
// 所以异常提示情况，通过promise.reject到最外面的catch err里面弹出
axios.interceptors.response.use(function (response) {
    totalApis--
    if (totalApis <= 0) {
        load.close();
    }

    if (response.status === 200) {//http状态码200正常应答, 不管是get还是post,只要逻辑应答有code和msg字段，code不是200，就报错
        if(Object.prototype.hasOwnProperty.call(response.data, "code")
            && Object.prototype.hasOwnProperty.call(response.data, "msg")
            && response.data.code != 200
        ){
            return Promise.reject(response.data.msg);
        }
    }

    return response
}, function (error) {
    totalApis--
    if (totalApis <= 0) {
        load.close();
    }

    if (error.response && error.response.status === 401) {
        Cookies.remove("AuthToken");
        Cookies.remove("ddpAccount");
        sessionStorage.clear();
        window.location.href = "/login"; //没有使用router带redirect跳转，因为router 3.0.7以上版本会报错
        return Promise.reject("登录过期，请重新登录")
    }
    return Promise.reject(error)
})

// 公共方法提取
// axios.get或post 先走请求拦截器，然后是响应拦截器，拦截器不能阻塞。
// 然后根据响应拦截器的return确认走下面方法的then或者catch,最后到外部的then或者catch
const returnValue = (url, params, type) => {
    return new Promise((resolve, reject) => {
        if (type === 'get') {
            let reqUrl = url;
            if (Object.keys(params).length != 0) {
                reqUrl = url + '?' + qs.stringify(params);
            }
            axios.get(reqUrl)
                .then((res) => {
                    resolve(res)
                })
                .catch((err) => {
                    reject(err)
                })
        } else {
            axios.post(url, params)
                .then((res) => {
                    resolve(res)
                })
                .catch((err) => {
                    reject(err)
                })
        }
    })
}

export default {
    // 登录接口
    Login: (params) => { return returnValue(apiUrls.login.login, params) },
    LoginOut: (params) => { return returnValue(apiUrls.login.loginOut, params, 'get') },

    //首页
    IndexQuery: (params) => { return returnValue(apiUrls.index.query, params, 'get') }, //首页展示数据 GET
}