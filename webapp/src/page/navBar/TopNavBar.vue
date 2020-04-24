<template>
  <div class="top_nav_bar">
    <nav-bar class="ito_top">
      <!-- <div slot="left" @click="backClick">
        <Icon type="md-arrow-back" />
      </div>-->
      <div slot="middle">密码管理</div>

      <dropdown class="top-right" slot="right" trigger="click" @on-click="itemClick">
        <a herf="javascript:void(0)">
          <span>{{name}}</span>
        </a>
        <dropdown-menu slot="list">
          <dropdown-item
            v-for="item in claList"
            :name="item.className"
            :key="item.classID"
          >{{item.className}}</dropdown-item>
        </dropdown-menu>
      </dropdown>
    </nav-bar>
  </div>
</template>

<script>
import NavBar from "./NavBar";
export default {
  name: "TopNavBar",
  components: { NavBar },
  props: {
    claList: {
      type: Array,
      default() {
        return [
          {
            className: "一年级一班",
            classID: "12131"
          }
        ];
      }
    }
  },
  data() {
    return {
      className: this.claList[0].className
    };
  },
  computed: {
    name() {
      return this.className;
    }
  },
  methods: {
    itemClick(name) {
      let obj = this.claList.find(clas => {
        return clas.className === name;
      });
      this.className = name;
      this.$emit("rightClick", obj.classID);
    }
  }
};
</script>

<style scope>
.top_nav_bar {
  background-color: blue;
}
.top-right span {
  font-size: 9px;
}
</style>
