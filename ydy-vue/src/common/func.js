export default {
    assignNeed: (orgin = {}, target = {}) => {
        var matchResults = {};
        Object.keys(target).forEach(key=>{
            if(!Object.prototype.hasOwnProperty.call(orgin, key)){
                console.log('orgin not existed key:'+key);
            }
            matchResults[key] = orgin[key];
        })
        return matchResults;
    },
    //js对象=赋值默认是引用赋值,下面方式是非引用拷贝
    cloneObjectPros: (obj) => {
        return JSON.parse(JSON.stringify(obj));
    },
    //时间格式转化
    timeFormat :(timeVal) =>{
        let date = new Date(Number(timeVal));
        let y = date.getFullYear();
        let MM = date.getMonth() + 1;
        MM = MM < 10 ? ('0' + MM) : MM;
        let d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        let h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        let m = date.getMinutes();
        m = m < 10 ? ('0' + m) : m;
        let s = date.getSeconds();
        s = s < 10 ? ('0' + s) : s;
        return y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s;
    },
    timeFormatToDate :(timeVal) =>{
        if(timeVal == null || timeVal == ''){return '';}
        let date = new Date(Number(timeVal));
        let y = date.getFullYear();
        let MM = date.getMonth() + 1;
        MM = MM < 10 ? ('0' + MM) : MM;
        let d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        return y + '-' + MM + '-' + d;
    },
    truncateObject :(obj, skipKeys) =>{
        for (var k in obj) {
            if(skipKeys.indexOf(k) > -1){
                continue;
            }
            var ctor = obj[k] && obj[k].constructor; 
            if  (ctor === Object) this.truncateObject(obj[k], skipKeys); 
            else if (ctor === Number) delete obj[k]; 
            else if (ctor)   obj[k] = ctor(); 
        }
    },
    checkStateFormatToName : (statusVal)=>{
        //0、待处理 3、重复 4、业务校验完成 5、真实性校验完成 7、业务校验失败 8、真实性校验失败',
        let statusName = statusVal;
        if(statusVal == 0) { statusName = '待处理' }
        else if(statusVal == 3) { statusName = '重复' }
        else if(statusVal == 4) { statusName = '业务校验完成' }
        else if(statusVal == 5) { statusName = '真实性校验完成' }
        else if(statusVal == 7) { statusName = '业务校验失败' }
        else if(statusVal == 8) { statusName = '真实性校验失败' }
        return statusName;
    },
    getRowCheckStatus : ()=>{
        return [{
            ID: '0',
            NAME: '待处理'
            }, {
            ID: '3',
            NAME: '重复'
            }, {
            ID: '4',
            NAME: '业务校验完成'
            }, {
            ID: '5',
            NAME: '真实性校验完成'
            }, {
            ID: '7',
            NAME: '业务校验失败'
            }, {
            ID: '8',
            NAME: '真实性校验失败'
            }
        ];
    }

}