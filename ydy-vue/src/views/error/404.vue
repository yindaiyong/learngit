<template>
    <div>
        <div class="title"></div>
        <div class="info">
            <div class="txtbox">
                <p>对不起，您请求的页面不存在、或已被删除、或暂时不可用</p>
                <p class="paddingbox">请点击以下链接继续浏览网页</p>
                <p>
                    》
                    <a onclick="javascript:history.back(-1);return false;">返回上一个页面</a>
                    <br>
                </p>
                <p>
                    》
                    <a href="/">返回网站首页</a>
                </p>
            </div>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                
            }
        }
    }
</script>
<style lang="scss" scoped>
.title {
	width: 580px;
	height: 234px;
	margin: 50px auto 0 auto;
	background: url(../../assets/images/404title.png) no-repeat;
}

.info {
	width: 499px;
	height: 169px;
	margin: 10px auto 0 auto;
    text-align: left;
	background: url(../../assets/images/404info.png) no-repeat;
}

.info .txtbox {
	width: 390px;
	position: relative;
	top: 30px;
	left: 60px;
	color: #eee;
	font-size: 14px;
}

.info .txtbox p {
	margin: 5px 0;
	line-height: 18px;
}

.info .txtbox .paddingbox {
	padding-top: 15px;
}

.info .txtbox p a {
	color: #eee;
	text-decoration: none;
}

.info .txtbox p a:hover {
	color: #FC9D1D;
	text-decoration: underline;
} 
</style>
<style lang="scss">
html{
    background-color:#494949;
}
</style>