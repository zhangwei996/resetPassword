<template>
  <div class="confirm">
    <!--自定义提示-->
    <Modal class-name="my-confirm" v-model="modal" :title="title || '提示'">
      <Alert type="warning" show-icon>
        <div v-if="msg">{{msg}}</div>
        <div v-else>
           {{'确定要删除吗？'}}
        </div>
        <span slot="desc">删除后不可恢复。</span>
      </Alert>
      <div slot="footer" v-if="type!='alert'">
        <i-Button type="primary" @click="ok">确定</i-Button>
        <i-Button type="ghost" @click="modal=false">取消</i-Button>
      </div>
      <div slot="footer" v-else>
        <i-Button type="primary" @click="ok">确定</i-Button>
      </div>
    </Modal>
    <!--自定义提示结束-->
  </div>
</template>

<script>
export default {
  props: {
    // 提示内容
    msg:{
        type: String
    },
    title: {
        type: String
    },
    type: {
        type: String
    },
    status: {
        type: Boolean
    },
  },
  data () {
    return {
			  modal:false,
			}
  },
  watch:{
    status(newVal,oldVal){
      this.modal=true;
    },
  },
  methods: {
    ok(){
      this.$emit('onClick');
      this.modal=false;
    },
    cancel (){
      this.modal=false;
    }
  }
}  
</script>
<style lang="scss">
  // 确认弹出框覆盖
  .my-confirm {
    .ivu-alert{
      margin-bottom: 0;
    }
    .ivu-alert .ivu-icon{
      top: 50%;
      left: 24px;
      margin-top: -16px;
      font-size: 28px;
    }
  }
</style>

