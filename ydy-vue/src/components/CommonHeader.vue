<template>
  <div class="common-header-wrap">
    <div class="header-bg">
      <el-row :gutter="0" type="flex" justify="center" align="middle">
        <el-col :span="6" class="height-100">
          <a href="http://www.shanghaitrust.com/" target="_blank"><img class="logo" src="../assets/images/logo.png"/></a>
          <a href="/"><img class="sjjdlogo" src="../assets/images/sjjdlogo.png" /></a>
          <div style="clear: both;"></div>
        </el-col>
        <el-col :span="4" class="height-100"></el-col>
        <el-col :span="12" class="height-100">
          <div class="grid-content bg-bottomMaterial">
            <img class="u61" src="../assets/images/u61.png"/>
          </div>
        </el-col>
        <el-col :span="2" class="height-100"></el-col>
      </el-row>
    </div>
    <div class="header-menu" style="pointer-events: none;">
      <el-row :gutter="0" type="flex" justify="center">
        <el-col :span="6"><div class="height-100"></div></el-col>
        <el-col :span="18" style="pointer-events: auto;">
          <el-row :gutter="0" >
            <el-col :span="6" style="padding-top:25px;">
              <el-select style="margin-right:20px; width: 70%;" size="small" v-model="orgSelectedCode" placeholder="请选择机构" @change="changeOrganCode">
                <el-option
                    v-for="item in orgOptions"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                ></el-option>
              </el-select>
            </el-col>
            <el-col :span="9">
              <div>
                <el-link href="/" :underline="false" class="menu-button">首页</el-link>
                <el-link href="/dataDetails" :underline="false" class="menu-button">样本集明细</el-link>
                <el-link href="/offlineReleaseV1.0.0.rar" download="离线校验工具.rar" :underline="false" class="menu-button">数据离线校验</el-link>
                <el-link href="/organ" :underline="false" class="menu-button">合作机构管理</el-link>
                <el-link href="/flexible" :underline="false" class="menu-button">灵活报表</el-link>
              </div>
            </el-col>
            <el-col :span="7">
              <div class="height-50"></div>
            </el-col>
            <el-col :span="1" >
              <div style="text-align: right;">
                <el-dropdown class="account-button-dropdown">
                  <el-button class="account-button" type="normal" size="small">
                    <p :title="accountInfo" class="account-info">{{accountInfo}}</p>
                    <i class="el-icon-arrow-down el-icon--right"></i>
                  </el-button>
                  <el-menu :default-active="activeIndex"
                    :router="true"
                    class="el-menu-demo"
                    mode="horizontal"
                    background-color="#545c64"
                    text-color="#fff"
                    active-text-color="#ffd04b">
                  </el-menu>
                  <el-dropdown-menu
                      :default-active="$route.path"
                      :router="true"
                      class="el-menu-demo"
                      mode="horizontal">
                    <el-dropdown-item><el-link href="/user" :underline="false" icon="el-icon-s-custom">用户管理</el-link></el-dropdown-item>
                    <el-dropdown-item><el-link href="/authRole" :underline="false" icon="el-icon-s-check">角色权限</el-link></el-dropdown-item>
                    <el-dropdown-item><el-link href="/authReport" :underline="false" icon="el-icon-s-data">报表权限</el-link></el-dropdown-item>
                    <el-dropdown-item><el-link href="/myReport" :underline="false" icon="el-icon-s-grid">我的报表</el-link></el-dropdown-item>
                    <el-dropdown-item><el-link href="/dict" :underline="false" icon = "el-icon-s-tools">字典配置</el-link></el-dropdown-item>
                    <el-dropdown-item><el-link href="/resetpassword" :underline="false" icon="el-icon-s-claim">修改密码</el-link></el-dropdown-item>
                    <el-dropdown-item><el-link :underline="false" icon="el-icon-switch-button" @click="logout">退出登陆</el-link></el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
              </div>
            </el-col>
            <el-col :span="1">
              <div class="height-50"></div>
            </el-col>
          </el-row>
          <el-row :gutter="0" type="flex" justify="center">
            <el-col :span="24">
              <div>
                <el-button :class="['module-button', {'active': focusButtonIndex == 1}]" round @click="$router.push('/dataImport')">数据采集</el-button>
                <el-button :class="['module-button', {'active': focusButtonIndex == 2}]" round @click="$router.push('/dataSampling')">数据抽样</el-button>
                <el-button :class="['module-button', {'active': focusButtonIndex == 3}]" round @click="$router.push('/sampleAnalysis')">样本分析</el-button>
                <el-button :class="['module-button', {'active': focusButtonIndex == 4}]" round @click="$router.push('/analysisReport')">分析报告</el-button>
              </div>
            </el-col>
          </el-row>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
import Cookies from "js-cookie"
export default {
  props: {
    focusButtonIndex: Number
  },
  data() {
    return {
      activeIndex: '0',
      orgOptions: [],
      orgSelectedCode: '',
      accountInfo: ''
    };
  },
  methods: {
    //机构下拉切换,给全局变量organCode赋值
    changeOrganCode(value){
      console.log(sessionStorage.getItem('organCode'));
      sessionStorage.setItem('organCode', value);
    },
    getOrganCombox() {
      this.$api.OrganCombox({}).then(res => {
        this.orgOptions = res.data;
        this.orgSelectedCode = sessionStorage.getItem('organCode');
      }).catch(err => {
        this.$commsgbox.alert(err);
      });
    },
    logout() {
      this.$api.LoginOut({}).then(() => {
          Cookies.remove("AuthToken");
          Cookies.remove("ddpAccount");
          sessionStorage.clear();
          window.location.reload();
      }).catch(err => {
          this.$commsgbox.alert(err);
      });
    }
    
  },
  created: function () {
    this.getOrganCombox();
    this.accountInfo = Cookies.get('ddpAccount');
  }
};
</script>

<style scoped>
.common-header-wrap .header-bg{
  height:100px;
  width: 100%;
  background: -webkit-linear-gradient(top,#B50023,#D27468,#D27468) no-repeat;
  z-index: -1;
}
.common-header-wrap .header-bg .logo {
  height:100px;
  float: left;
  width: 55%;
}
.common-header-wrap .header-bg .sjjdlogo {
  height:70px;
  margin-top: 30px;
  width: 45%;
}
.common-header-wrap .header-bg .u61 {
  background-size: cover;
  background-position: center;
  height:70px;
  z-index:1;
}
.common-header-wrap .header-menu {
  margin-top: -100px;
  height:100px;
  width: 100%;
  z-index: 2;
}
.common-header-wrap .header-menu .menu-button {
  float: left;
  font-size: 1vw;
  color: white;
  margin-left: 10px;
  margin-top: 30px;
}
.common-header-wrap .header-menu .menu-button:hover {
  background-color: white;
  color:#B50023;
}
.common-header-wrap .header-menu .account-button-dropdown {
  margin-top: 10px;
  margin-right: 10px;
}
.common-header-wrap .header-menu .account-button-dropdown .account-button {
  background-color: transparent;
  margin-top: 15px;
  color: white;
  border: 0px;
  font-weight: bold;
}
.common-header-wrap .header-menu .account-button-dropdown .account-button:hover {
  background-color: white;
  color:#B50023;
}
.common-header-wrap .header-menu .account-button-dropdown .account-info {
  display: inline-block;
  font-size: 12px;
  width:50px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  text-align: left;
}
.common-header-wrap .header-menu .account-button-dropdown .el-menu.el-menu--horizontal{
    border-bottom: none;
}
.common-header-wrap .header-menu .module-button {
  background: #D27468;
  color: white;
  font-size: 18px;
  width: 20%;
  height: 32px;
  line-height: 32px;
  padding: 0px;
  border: 2px solid white;
  text-align: center;
  margin-top: 5px;
}
.common-header-wrap .header-menu .module-button:hover {
  background-color: white;
  border-color: white;
  color:  #D27468;
}
.common-header-wrap .header-menu .active {
  background-color: white;
  border-color: white;
  color:  #D27468;
}
.common-header-wrap .height-100 {
  height: 100px;
}
.common-header-wrap .height-50 {
  height: 50px;
}
</style>