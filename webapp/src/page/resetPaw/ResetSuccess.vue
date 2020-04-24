<template>
  <div class="reset_success">
    <Modal
      v-model="show"
      @on-visible-change="modelChange"
      class="modal_box"
      width="250"
      :closable="false"
    >
      <!-- <div style="text-align:right;margin-top:-10px" @click="iconClose">
        <img :src="require('../../../static/img/close.png')" class="close" />
      </div>-->
      <div class="mid">
        <div class="stuName" style>姓名：{{stuInfo.stuName}}</div>
        <div class="stuID" style>账号：{{stuInfo.stuID}}</div>
        <div class="password" style>密码：{{stuInfo.stuID}}@bsch</div>
      </div>
      <div slot="footer" style="text-align:center;margin-top:-5px">
        <!-- <Button ghost type="success" shape="circle" icon="ios-checkmark-circle-outline"></Button> -->
        <div>
          <img :src="require('../../../static/img/success.png')" class="img" />
        </div>
        <div class="text">密码重置成功</div>
      </div>
    </Modal>
  </div>
</template>

<script>
export default {
  name: "ResetSuccess",
  props: {
    show: { type: Boolean, default: false },
    stuInfo: {
      type: Object,
      default() {
        return {};
      }
    }
  },
  data() {
    return {
      isModel: this.show
    };
  },
  created() {
    console.log(this.show);
  },
  methods: {
    modelChange(state) {
      this.isModel = state;
      this.$emit("close");
      //console.log(state);
    }
  }
};
</script>

<style scoped>
.mid {
  padding-left: 50px;
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
.password {
  margin-top: 20px;
}
.stuName,
.stuID,
.password {
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
.modal_box /deep/ .ivu-modal {
  height: 270px;
  width: 300px !important;
}
</style>
