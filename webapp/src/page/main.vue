<template>
  <div class="main">
    <!-- <top-nav-bar :claList="classList" @rightClick="rightClick" v-if="classList.length > 0" /> -->
    <div style="margin-top:-40px"></div>
    <div class="box">
      <class-stu :stuList="stuList" @rePaw="resetPaw" />
      <reset-paw :open="open" :stuInfo="stuInfo" @cancle="cancle" @reset="reset" v-if="open" />
      <reset-success :show="show" :stuInfo="paw" v-if="show" @close="close" />
      <!-- <reset-error :show="show" :stuInfo="paw" v-if="show" @close="close" />> -->
    </div>
  </div>
</template>

<script>
// import TopNavBar from "./navBar/TopNavBar";
import ClassStu from "./classStu";
import ResetPaw from "./resetPaw/ResetPaw";
import ResetSuccess from "./resetPaw/ResetSuccess";
// import ResetError from "./resetPaw/ResetError";

export default {
  name: "main",
  components: { ClassStu, ResetPaw, ResetSuccess },
  data() {
    return {
      open: false,
      show: false,
      classId: null,
      stuInfo: null,
      paw: null,
      classList: [],
      stuList: [],
      timeType: 0,
      typeText: ""
    };
  },
  watch: {
    $route(router) {
      if (typeof bsch != "undefined") {
        bsch.removeRightBtn();
        if (router.meta.title) bsch.setTitle(router.meta.title);
      } else {
        document.title = router.meta.title;
      }
    }
  },
  mounted() {
    this.getClassList();
  },
  created() {
    // if (typeof bsch != "undefined") {
    //   bsch.setTitleDrop;
    // }
  },
  methods: {
    rightClick(classId) {
      this.getClassStus(classId);
    },
    resetPaw(item) {
      this.stuInfo = item;
      //打开重置密码对话框
      this.open = true;
    },
    //关闭对话框
    cancle() {
      this.open = false;
    },
    close() {
      this.show = false;
    },
    //重置密码
    reset(stu) {
      this.open = false;
      console.log(typeof parseInt(stu.stuID));
      //数据库修改密码，修改成功返回true
      var _this = this;
      $.post(
        _this.APIConfig.ResetPsd.SavePwd,
        { userID: stu.userID, stuID: stu.stuID },
        function(resp) {
          console.log(resp);

          if (resp.code == 0) {
            return true;
          } else _this.$Message.info(resp.msg);
        },
        "json"
      ).error(function() {
        _this.$Message.info("重置密码失败");
      });

      //或者手动修改并展示
      //返回学生新密码

      this.paw = stu;
      //打开重置密码成功对话框
      this.show = true;

      //打开后3秒关闭对话框
      // setTimeout(() => {
      //   this.show = false;
      // }, 3000);
      this.$Modal.remove(); //全局关闭
    },
    //获取班级列表
    getClassList() {
      var _this = this;

      $.get(
        _this.APIConfig.ResetPsd.GetAllClassList,
        function(resp) {
          if (resp.code == 0) {
            if (resp.data.isClass == 1) {
              resp.data.list.forEach(item => {
                item.className = item.gradeName + item.className;
              });
              _this.classList = resp.data.list;
              _this.getClassStus(_this.classList[0].classID);
              _this.createAppSel();
            }

            // resp.data.forEach(item => {
            //   item.className = item.gradeName + item.className;
            // });
            // console.log(resp.data);

            // _this.classList = resp.data;
            // _this.getClassStus(_this.classList.classID);
            // //默认查询第一个班级的学生
            // _this.createAppSel();
            else if (resp.data.isClass == 0) {
              console.log("进入了");
              _this.$router.replace({
                path: "/Blank"
              });
            }
          } else {
            _this.$Message.info(resp.msg);
          }
        },
        "json"
      ).error(function() {
        _this.$Message.info("获取班级列表失败");
      });
    },
    //根据班级classId 获取班级学生
    getClassStus(classId) {
      var _this = this;

      $.get(
        _this.APIConfig.ResetPsd.GetAllStuList,
        { ClassID: classId },
        function(resp) {
          if (resp.code == 0) {
            _this.stuList = resp.data;
            _this.total = resp.data.size;
          } else _this.$Message.info(resp.msg);
        },
        "json"
      ).error(function() {
        _this.$Message.info("获取学生列表失败");
      });
    },

    //TopNavBar一
    // createRightMenu() {
    //   let that = this;
    //   this.typeText = this.classList[this.classList.classID - 1];
    //   if (typeof bsch != "undefined") {
    //     bsch.addRightTextBtn(this.typeText, () => {
    //       let arr = [];
    //       arr.push(...this.classList);
    //       bsch.setRightDrop(
    //         arr.join(","),
    //         index => {
    //           this.getClassList;
    //         },
    //         5
    //       );
    //     });
    //   }
    // },

    //TopNavBar二
    createAppSel: function() {
      var _this = this;
      if (typeof bsch != "undefined") {
        _this.typeText = _this.classList[_this.timeType].className;
        bsch.addRightTextBtn(_this.typeText, function() {
          var arr = [];
          _this.classList.forEach(element => {
            arr.push(element.className);
          });
          bsch.setRightDrop(
            arr.join(","),
            function(index) {
              _this.timeType = index;

              _this.createAppSel();
              _this.getClassStus(_this.classList[_this.timeType].classID);
            },
            5
          );
        });
      } else {
        _this.getClassStus(_this.classList[0].classID);
      }
    }
  }
};
</script>
<style scoped>
.box {
  margin-top: 44px;
}
</style>

