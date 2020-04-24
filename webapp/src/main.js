import Vue from 'vue'
Vue.config.productionTip = false;

import 'babel-polyfill' //ie兼容处理

import Vconsole from 'vconsole'
// new Vconsole();

import iView from 'view-design';
import 'view-design/dist/styles/iview.css';
Vue.prototype.$iView = iView;
Vue.use(iView);
iView.Message.config({
  top: 50,
  duration: 2
});

import router from './router'

import {
  InfiniteScroll
} from 'mint-ui';
Vue.use(InfiniteScroll);

import APIConfig from './config/apiconfig'
Vue.prototype.APIConfig = APIConfig;

import CurUser from './model/userInfo'
Vue.prototype.CurUser = CurUser;


import '../static/css/index.css';
import "../static/js/jquery-1.9.1.min.js"
import "./utils/reset_jquery_http"
import "./utils/common"
import MainFrame from './MainFrame.vue'

var vue = new Vue({
  el: '#main_frame',
  router,
  template: '<MainFrame/>',
  components: {
    MainFrame
  },
})
