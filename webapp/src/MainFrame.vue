<template>
  <!-- <div class="main_container">
    <div class="layout-bd-right">
        <div class="layout-main">
          <transition name="fade" mode="out-in">
            <router-view></router-view>
          </transition>
        </div>
      </div>
  </div>-->

  <div id="app">
    <div>
      <router-view></router-view>
      <!-- <main /> -->
    </div>
  </div>
</template>

<script>
var bschLoaded = window.debugmode;
window.onBschLoad = function() {
  bschLoaded = true;
  window.debugmode = false;
};
import "./assets/edit.scss";
export default {
  data() {
    return {
      modList: [],
      intCount: 0,
      havaQuesionId: false,
      userId: ""
    };
  },
  watch: {},
  mounted() {
    var _this = this;
    _this.loadUserInfo();
  },
  methods: {
    loadUserInfo() {
      var _this = this;

      console.log(123);

      if (!bschLoaded) {
        setTimeout(() => {
          _this.loadUserInfo();
        }, 500);
        return;
      }
      $.get(
        _this.APIConfig.ResetPsd.GetUserInfo,
        function(resp) {
          if (resp.code == 0) {
            _this.CurUser.setCurUser(resp.data);
            _this.$router.replace("main");
          } else _this.$Message.info(resp.msg);
        },
        "json"
      ).error(function() {
        _this.$Message.error("载入用户信息失败");
      });
    }
  }
};
</script>
