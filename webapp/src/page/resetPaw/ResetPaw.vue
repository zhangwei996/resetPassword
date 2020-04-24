<template>
  <div class="reset_paw">
    <Modal v-model="open" class="modal_box" :closable="false" :mask-closable="false">
      <p slot="header" style="color:#f60;text-align:center">
        <span class="sure">确定重置密码?</span>
      </p>
      <div class="mid">
        <div class="stuName">姓名：{{stuInfo.stuName}}</div>
        <div class="stuID">账号：{{stuInfo.stuID}}</div>
      </div>
      <div slot="footer" class="modal_footer">
        <Button class="cancle" shape="circle" @click="cancle">取消</Button>
        <Button class="ok" shape="circle" @click="reset">确定</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
export default {
  name: "ResetPaw",
  props: {
    open: { type: Boolean, default: false },
    stuInfo: {
      type: Object,
      default() {
        return {
          stuID: "sfasf",
          stuName: ""
        };
      }
    }
  },
  data() {
    return {};
  },
  methods: {
    cancle() {
      this.$emit("cancle");
    },
    reset() {
      this.$emit("reset", this.stuInfo);
    }
  }
};
</script>

<style scoped>
.mid {
  text-align: left;
  padding-left: 50px;
}
.modal_box /deep/ .ivu-modal-content {
  width: 280px !important;
  right: -48px;
  height: 266px;
  top: -57px;
}
.stuName {
  padding-top: 0px;
}
.stuID {
  width: 200px;
  padding-top: 18px;
}
.stuName,
.stuID {
  font-size: 12px;
  padding-left: 20px;
}
.sure {
  font-size: 17px;
  color: rgb(22, 19, 17);
}
/* .ivu-modal-header {
  padding: 14px 16px;
  line-height: 1;
} */
.modal_box /deep/ .ivu-modal-header {
  padding: 14px 16px;
  line-height: 1;
  border-bottom: 0;
  padding-top: 40px;
}
.modal_box /deep/ .ivu-modal-footer {
  padding: 21px 18px 12px 18px;
  text-align: center;
  border-top: 0;
}
.cancle {
  height: 35px;
  width: 88px;
}
.ok {
  color: #fff;
  background-color: #a383f2;
  border-color: #a383f2;
  height: 35px;
  width: 88px;
  margin-left: 28px;
}
</style>
