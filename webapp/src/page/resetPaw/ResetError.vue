<template>
  <div class="reset_error">
    <Modal v-model="zhanshi" class="modal_box" width="250" :closable="false">
      <div class="mid">
        <div class="stuName" style>姓名：{{stuInfo.stuName}}</div>
        <div class="stuID" style>账号：{{stuInfo.stuID}}</div>
      </div>
      <div slot="footer" style="text-align:center;margin-top:-5px">
        <!-- <Button ghost type="success" shape="circle" icon="ios-checkmark-circle-outline"></Button> -->
        <div>
          <img :src="require('../../../static/img/error.png')" class="img" />
        </div>
        <div class="text">密码重置失败</div>
      </div>
    </Modal>
  </div>
</template>

<script>
export default {
  name: "ResetError",
  props: {
    zhanshi: { type: Boolean, default: false },
    stuInfo: {
      type: Object,
      default() {
        return {};
      }
    }
  },
  data() {
    return {};
  },
  methods: {}
};
</script>

<style scoped>
.mid {
  padding-left: 52px;
}
.modal_box /deep/ .ivu-modal-content {
  width: 288px !important;
  right: -44px;
  height: 338px;
  top: -58px;
}
.stuName {
  margin-top: 30px;
}
.stuID {
  margin-left: 10px;
  padding-top: 20px;
}
.stuName,
.stuID {
  margin-left: 15px;
  width: 220px;
  font-size: 13px;
}
.img {
  width: 58px;
  margin: 12px;
}
.text {
  margin-top: -12px;
  font-size: 18px;
}
.modal_box /deep/ .ivu-modal-footer {
  border-top: 0;
}
/* .close {
  width: 20px;
} */
</style>
