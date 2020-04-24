<template>
  <div class="class_stu">
    <div class="stu_list" v-for="(item, index) in stuList" :key="index">
      <div class="list">
        <img :src="item.stuPhoto" class="stuImg" />

        <div class="name">{{item.stuName}}</div>

        <div class="password">{{item.stuID}}</div>

        <div class="icon" @click="iconClick(item)">
          <img :src="require('../../static/img/suo.png')" style="width:27px" />
        </div>
      </div>
      <!-- <div style="border-bottom:1px solid #e8eaec"></div> -->
      <divider />
    </div>
  </div>
</template>

<script>
export default {
  name: "classStu",
  props: {
    stuList: {
      type: Array,
      default() {
        return [];
      }
    }
  },
  data() {
    return {};
  },
  computed: {},
  methods: {
    iconClick(item) {
      this.$emit("rePaw", item);
    }
  }
};
</script>

<style scoped>
.stuImg {
  width: 55px;
  height: 55px;
  border: 1px solid #dddddd;
  border-radius: 50%;
  margin-top: 4px;
}
.list {
  display: flex;
  padding: 10px;
  height: 71px;
}
.name {
  width: 75px;
}
.password {
  width: 137px;
  padding-left: 22px;
}
/* .icon {
  width: 22px;
  height: 22px;
} */
.icon {
  width: 30px;
  margin-top: 18px;
  /* font-size: 40px; */
}
.name,
.password {
  line-height: 60px;
  word-break: keep-all;
}
.name,
.icon {
  margin-left: 35px;
}

.stu_list /deep/ .ivu-divider-horizontal {
  margin: 0;
  min-width: 80%;
  width: 80%;
  margin-left: 74px;
}
</style>
